require 'rails_helper'

RSpec.feature "Gestión de Tareas", type: :feature do
  scenario "Crear una tarea desde la interfaz" do
    visit new_tarea_path
    fill_in "Titulo", with: "Tarea UI"
    fill_in "Descripcion", with: "Prueba desde Capybara"
    click_button "Create Tarea"
    expect(page).to have_content("Tarea was successfully created")
  end
end
