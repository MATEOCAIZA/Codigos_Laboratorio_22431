require 'swagger_helper'

RSpec.describe 'API de Tareas', type: :request do

  path '/tareas' do
    get 'Obtiene la lista de tareas' do
      tags 'Tareas'
      produces 'application/json'

      response '200', 'Lista de tareas' do
        run_test!
      end
    end

    post 'Crea una nueva tarea' do
      tags 'Tareas'
      consumes 'application/json'
      parameter name: :tarea, in: :body, schema: {
        type: :object,
        properties: {
          titulo: { type: :string },
          descripcion: { type: :string },
          completada: { type: :boolean }
        },
        required: ['titulo', 'descripcion']
      }

      response '201', 'Tarea creada' do
        let(:tarea) { { titulo: 'Tarea de prueba', descripcion: 'Descripción de la tarea', completada: false } }
        run_test!
      end
    end
  end

  path '/tareas/{id}' do
    parameter name: :id, in: :path, type: :string, description: 'ID de la tarea'

    get 'Obtiene una tarea por ID' do
      tags 'Tareas'
      produces 'application/json'

      response '200', 'Tarea encontrada' do
        schema type: :object,
               properties: {
                 id: { type: :integer },
                 titulo: { type: :string },
                 descripcion: { type: :string },
                 completada: { type: :boolean }
               },
               required: ['id', 'titulo', 'descripcion']

        let(:id) { Tarea.create(titulo: 'Tarea de ejemplo', descripcion: 'Descripción', completada: false).id }
        run_test!
      end

      response '404', 'Tarea no encontrada' do
        let(:id) { 'invalido' }
        run_test!
      end
    end

    patch 'Actualiza una tarea' do
      tags 'Tareas'
      consumes 'application/json'
      parameter name: :tarea, in: :body, schema: {
        type: :object,
        properties: {
          titulo: { type: :string },
          descripcion: { type: :string },
          completada: { type: :boolean }
        },
        required: ['titulo', 'descripcion']
      }

      response '200', 'Tarea actualizada' do
        let(:id) { Tarea.create(titulo: 'Tarea anterior', descripcion: 'Descripción antigua', completada: false).id }
        let(:tarea) { { titulo: 'Tarea actualizada', descripcion: 'Nueva descripción', completada: true } }
        run_test!
      end

      response '404', 'Tarea no encontrada' do
        let(:id) { 'invalido' }
        run_test!
      end
    end

    delete 'Elimina una tarea' do
      tags 'Tareas'
      response '204', 'Tarea eliminada' do
        let(:id) { Tarea.create(titulo: 'Eliminar tarea', descripcion: 'Eliminar esta tarea', completada: false).id }
        run_test!
      end

      response '404', 'Tarea no encontrada' do
        let(:id) { 'invalido' }
        run_test!
      end
    end
  end
end
