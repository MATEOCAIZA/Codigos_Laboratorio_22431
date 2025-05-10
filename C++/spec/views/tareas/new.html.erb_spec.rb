require 'rails_helper'

RSpec.describe "tareas/new", type: :view do
  before(:each) do
    assign(:tarea, Tarea.new(
      titulo: "MyString",
      descripcion: "MyText",
      completada: false
    ))
  end

  it "renders new tarea form" do
    render

    assert_select "form[action=?][method=?]", tareas_path, "post" do

      assert_select "input[name=?]", "tarea[titulo]"

      assert_select "textarea[name=?]", "tarea[descripcion]"

      assert_select "input[name=?]", "tarea[completada]"
    end
  end
end
