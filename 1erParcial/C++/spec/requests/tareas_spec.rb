require 'rails_helper'

RSpec.describe "Tareas", type: :request do
  let(:valid_attributes) do
    { titulo: "Tarea Test", descripcion: "Descripción de prueba", completada: false }
  end

  let(:invalid_attributes) do
    { titulo: "", descripcion: "", completada: false }
  end

  describe "GET /index" do
    it "muestra la lista de tareas" do
      get tareas_path
      expect(response).to have_http_status(200)
    end
  end

  describe "POST /tareas" do
    it "crea una nueva tarea" do
      expect {
        post tareas_path, params: { tarea: valid_attributes }
      }.to change(Tarea, :count).by(1)
      expect(response).to redirect_to(Tarea.last)
    end

    it "no crea una tarea con parámetros inválidos" do
      expect {
        post tareas_path, params: { tarea: invalid_attributes }
      }.not_to change(Tarea, :count)
      expect(response).to have_http_status(422)
    end
  end

  describe "PATCH /update" do
    let(:tarea) { Tarea.create! valid_attributes }

    it "actualiza una tarea existente" do
      patch tarea_path(tarea), params: { tarea: { titulo: "Actualizado" } }
      tarea.reload
      expect(tarea.titulo).to eq("Actualizado")
    end
  end

  describe "DELETE /destroy" do
    let!(:tarea) { Tarea.create! valid_attributes }

    it "elimina la tarea" do
      expect {
        delete tarea_path(tarea)
      }.to change(Tarea, :count).by(-1)
    end
  end
end
