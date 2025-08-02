const express = require('express');
const Reserva = require('../models/Reserva');
const authMiddleware = require('../middleware/authMiddleware');

const router = express.Router();

// Protege todas las rutas siguientes con autenticación
router.use(authMiddleware);

// Listar todas las reservas del usuario autenticado
router.get('/', async (req, res) => {
  const reservas = await Reserva.find({ usuario: req.userId });
  res.json(reservas);
});

// Crear nueva reserva
router.post('/', async (req, res) => {
  const { fecha, sala, hora } = req.body;

  // Validación de sala
  if (!sala || !['A', 'B', 'C'].includes(sala)) {
    return res.status(400).json({ msg: 'Sala inválida' });
  }

  // Validación de hora en formato 12h con AM/PM
  const hora12Regex = /^(0[1-9]|1[0-2]):[0-5][0-9]\s?(AM|PM)$/i;
  if (!hora12Regex.test(hora)) {
    return res.status(400).json({ msg: 'Formato de hora inválido. Use formato de 12 horas con AM/PM, ej: 03:30 PM' });
  }

  // Validación de que no sea domingo
  const fechaObj = new Date(`${fecha}T00:00:00Z`);
  const diaSemana = fechaObj.getUTCDay(); // 0 = domingo
  if (diaSemana === 0) {
    return res.status(400).json({ msg: 'No se permiten reservas los domingos' });
  }

  const nueva = new Reserva({
    usuario: req.userId,
    fecha,
    sala,
    hora
  });

  await nueva.save();
  res.status(201).json(nueva);
});

// Eliminar una reserva (solo si pertenece al usuario)
router.delete('/:id', async (req, res) => {
  const resultado = await Reserva.deleteOne({ _id: req.params.id, usuario: req.userId });

  if (resultado.deletedCount === 0) {
    return res.status(404).json({ msg: 'Reserva no encontrada o no autorizada' });
  }

  res.status(200).json({ msg: 'Reserva cancelada' });
});

module.exports = router;
