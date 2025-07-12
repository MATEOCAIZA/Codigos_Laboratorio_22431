# Tareas CRUD - Ruby on Rails

## Descripción
Este proyecto es una aplicación CRUD (Crear, Leer, Actualizar y Eliminar) desarrollada en **Ruby on Rails** que permite gestionar tareas. Los campos principales de cada tarea son: **título**, **descripción** y **estado** (completada o no completada). La aplicación sigue la arquitectura **MVC** (Modelo, Vista, Controlador) y está documentada utilizando **Rswag** para la generación de la API.

## Tecnologías Utilizadas
- Ruby on Rails 8.0.2
- Ruby 3.4.3
- MySQL como base de datos
- Tailwind CSS para el diseño (opcional)
- RSpec para pruebas unitarias y de integración
- Rswag para la documentación de la API

## Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/usuario/tareas_crud.git
   cd tareas_crud


2. Instala las dependencias:
    ```bash
    bundle install

3. Configura la base de datos en config/database.yml y ejecuta las migraciones:
    ```bash
    rails db:create
    rails db:migrate

4. Instala Tailwind CSS (opcional):

    ```bash
    rails tailwindcss:install

5. Inicia el servidor:

    ```bash
    rails server


Uso
Accede a la aplicación en localhost:3000/tareas

Operaciones CRUD
Crear una nueva tarea: Formulario para añadir una tarea con título, descripción y estado.

Ver una tarea existente: Mostrar los detalles de una tarea específica.

Editar una tarea: Modificar los datos de una tarea.

Eliminar una tarea: Eliminar una tarea del sistema.


Pruebas
Las pruebas se realizan utilizando RSpec y están ubicadas en la carpeta spec/.

6. Ejecutar Pruebas Unitarias

    ```bash
    bundle exec rspec

Documentación de la API con Rswag
Para acceder a la documentación de la API, inicia el servidor y visita:
localhost:3000/api-docs

7. Generar la documentación de la API

    ```bash
    rails rswag:specs:swaggerize


Estructura del Proyecto
app/models/tarea.rb - Modelo de la tarea

app/controllers/tareas_controller.rb - Controlador de tareas

app/views/tareas/ - Vistas para el CRUD

spec/ - Pruebas unitarias y de integración

Contribuciones
Las contribuciones son bienvenidas. Por favor, abre un issue o un pull request para cualquier mejora o corrección.



