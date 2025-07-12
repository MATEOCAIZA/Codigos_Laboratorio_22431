require 'rails_helper'

RSpec.describe Tarea, type: :model do

  # Validaciones de presencia
  it { should validate_presence_of(:titulo) }
  it { should validate_presence_of(:descripcion) }

  # Validación de datos completos
  it "es válida con todos los atributos" do
    tarea = Tarea.new(titulo: "Comprar pan", descripcion: "Ir a la tienda", completada: false)
    expect(tarea).to be_valid
  end

  # Casos de ausencia de campos
  it "no es válida sin título" do
    tarea = Tarea.new(descripcion: "Sin título", completada: false)
    expect(tarea).not_to be_valid
  end

  it "no es válida sin descripción" do
    tarea = Tarea.new(titulo: "Tarea sin descripción", completada: false)
    expect(tarea).not_to be_valid
  end

  # Casos límite de campos vacíos
  it "no es válida si el título está en blanco" do
    tarea = Tarea.new(titulo: "", descripcion: "Descripción presente", completada: true)
    expect(tarea).not_to be_valid
  end

  it "no es válida si la descripción está en blanco" do
    tarea = Tarea.new(titulo: "Título presente", descripcion: "", completada: true)
    expect(tarea).not_to be_valid
  end

  # Validación del campo completada como booleano
  it "no es válida si completada no es booleano" do
    tarea = Tarea.new(titulo: "Tarea booleano", descripcion: "Descripción", completada: nil)
    expect(tarea).not_to be_valid
  end

  # Verificación de valores booleanos explícitos
  it "es válida si completada es true" do
    tarea = Tarea.new(titulo: "Tarea completa", descripcion: "Realizada", completada: true)
    expect(tarea).to be_valid
  end

  it "es válida si completada es false" do
    tarea = Tarea.new(titulo: "Tarea pendiente", descripcion: "Aún no realizada", completada: false)
    expect(tarea).to be_valid
  end

end
