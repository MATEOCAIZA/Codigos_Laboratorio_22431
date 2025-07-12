require 'rails_helper'

RSpec.describe "tareas/edit", type: :view do
  let(:tarea) {
    Tarea.create!(
      titulo: "MyString",
      descripcion: "MyText",
      completada: false
    )
  }

  before(:each) do
    assign(:tarea, tarea)
  end

  it "renders the edit tarea form" do
    render

    assert_select "form[action=?][method=?]", tarea_path(tarea), "post" do

      assert_select "input[name=?]", "tarea[titulo]"

      assert_select "textarea[name=?]", "tarea[descripcion]"

      assert_select "input[name=?]", "tarea[completada]"
    end
  end
end
