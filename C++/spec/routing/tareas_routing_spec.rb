require "rails_helper"

RSpec.describe TareasController, type: :routing do
  describe "routing" do
    it "routes to #index" do
      expect(get: "/tareas").to route_to("tareas#index")
    end

    it "routes to #new" do
      expect(get: "/tareas/new").to route_to("tareas#new")
    end

    it "routes to #show" do
      expect(get: "/tareas/1").to route_to("tareas#show", id: "1")
    end

    it "routes to #edit" do
      expect(get: "/tareas/1/edit").to route_to("tareas#edit", id: "1")
    end


    it "routes to #create" do
      expect(post: "/tareas").to route_to("tareas#create")
    end

    it "routes to #update via PUT" do
      expect(put: "/tareas/1").to route_to("tareas#update", id: "1")
    end

    it "routes to #update via PATCH" do
      expect(patch: "/tareas/1").to route_to("tareas#update", id: "1")
    end

    it "routes to #destroy" do
      expect(delete: "/tareas/1").to route_to("tareas#destroy", id: "1")
    end
  end
end
