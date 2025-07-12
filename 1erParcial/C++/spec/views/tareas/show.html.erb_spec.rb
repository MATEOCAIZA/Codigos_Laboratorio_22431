require 'rails_helper'

RSpec.describe "tareas/show", type: :view do
  before(:each) do
    assign(:tarea, Tarea.create!(
      titulo: "Titulo",
      descripcion: "MyText",
      completada: false
    ))
  end

  it "renders attributes in <p>" do
    render
    expect(rendered).to match(/Titulo/)
    expect(rendered).to match(/MyText/)
    expect(rendered).to match(/false/)
  end
end
