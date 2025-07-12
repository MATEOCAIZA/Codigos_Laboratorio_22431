require 'rails_helper'

RSpec.describe "tareas/index", type: :view do
  before(:each) do
    assign(:tareas, [
      Tarea.create!(
        titulo: "Titulo",
        descripcion: "MyText",
        completada: false
      ),
      Tarea.create!(
        titulo: "Titulo",
        descripcion: "MyText",
        completada: false
      )
    ])
  end

  it "renders a list of tareas" do
    render
    cell_selector = 'div>p'
    assert_select cell_selector, text: Regexp.new("Titulo".to_s), count: 2
    assert_select cell_selector, text: Regexp.new("MyText".to_s), count: 2
    assert_select cell_selector, text: Regexp.new(false.to_s), count: 2
  end
end
