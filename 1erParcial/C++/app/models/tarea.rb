class Tarea < ApplicationRecord
    validates :titulo, presence: true
    validates :descripcion, presence: true
    validates :completada, inclusion: { in: [true, false], message: "debe ser verdadero o falso" }
  end
  