class CreateTareas < ActiveRecord::Migration[8.0]
  def change
    create_table :tareas do |t|
      t.string :titulo
      t.text :descripcion
      t.boolean :completada

      t.timestamps
    end
  end
end
