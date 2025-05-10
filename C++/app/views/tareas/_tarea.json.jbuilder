json.extract! tarea, :id, :titulo, :descripcion, :completada, :created_at, :updated_at
json.url tarea_url(tarea, format: :json)
