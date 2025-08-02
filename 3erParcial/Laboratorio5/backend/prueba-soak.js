import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    vus: 10,              // 10 usuarios concurrentes constantes
    duration: '10m',      // prueba de 10 minutos continuos
    thresholds: {
        http_req_duration: ['p(95)<600'],
        http_req_failed: ['rate<0.05']
    }
};

function generarCorreo() {
    return `user_${__VU}_${__ITER}@test.com`;
}

// Fechas válidas (no domingos)
function generarFecha() {
    let fecha;
    do {
        const hoy = new Date();
        hoy.setDate(hoy.getDate() + Math.floor(Math.random() * 10));
        fecha = hoy.toISOString().split('T')[0];
    } while (new Date(fecha).getUTCDay() === 0);
    return fecha;
}

// Horas válidas (formato 12h AM/PM)
function generarHora() {
    const horas = ['09:00 AM', '10:30 AM', '03:30 PM', '04:15 PM'];
    return horas[Math.floor(Math.random() * horas.length)];
}

// Salas válidas (letras)
function generarSala() {
    const salas = ['A', 'B', 'C'];
    return salas[Math.floor(Math.random() * salas.length)];
}

export default function () {
    const correo = generarCorreo();
    const contrasenia = '12345';

    const payload = JSON.stringify({
        correo: correo,
        contraseña: contrasenia,
    });

    const headers = {
        'Content-Type': 'application/json',
    };

    const resRegister = http.post('http://localhost:3000/api/auth/register', payload, { headers });

    check(resRegister, {
        'Registro exitoso': (r) => r.status === 201,
    });

    sleep(1);


    const resLogin = http.post('http://localhost:3000/api/auth/login', payload, { headers });

    check(resLogin, {
        'Login exitoso': (r) => r.status === 200 && r.json('token') !== undefined,
        'Token presente': (r) => !!r.json('token'),
    });

    const token = resLogin.json('token');
    if (!token) return;

    // GET a reservas del usuario autenticado
    const getReservas = http.get('http://localhost:3000/api/reservas', {
        headers: {
            'Authorization': `Bearer ${token}`,
        },
    });
  
    check(getReservas, {
        'Obtener Reservas existoso': (r) => r.status === 200,
    });


    const reservaPayload = JSON.stringify({
        fecha: generarFecha(),
        hora: generarHora(),
        sala: generarSala(),
    });

    const reservaRes = http.post('http://localhost:3000/api/reservas', reservaPayload, {
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
        },
    });

    check(reservaRes, {
        'Reserva creada (201)': (r) => r.status === 201,
    });

    // Mostrar error en consola
    if (reservaRes.status !== 201) {
        console.log(`⚠️ Error reserva (${reservaRes.status}): ${reservaRes.body}`);
    }

    sleep(1);
}
