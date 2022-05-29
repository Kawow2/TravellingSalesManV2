package com.example.travellingsalesmanv3.Model.Structure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Map {
    private ArrayList<Client> clients;
    private ArrayList<Vehicle> vehicles;

    public Map(ArrayList<Client> clients, ArrayList<Vehicle> vehicles) {
        this.clients = clients;
        this.vehicles = vehicles;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Map cloneMap() {

        ArrayList<Vehicle> veh = new ArrayList<>();
        for (Vehicle v : this.vehicles) {
            Vehicle vehicle = new Vehicle();
            vehicle.setCapaciteRestant(v.getCapaciteRestant());
            vehicle.setClientsToDeliver(new LinkedList<Client>(v.getClientsToDeliver()));
            veh.add(vehicle);
        }

        return new Map(new ArrayList<>(this.clients), veh);
    }

    public boolean verifierVehiculeCercle() {
        return !this.getVehicles().stream().map(vehicle -> vehicle.getClientsToDeliver().getFirst().equals(vehicle.getClientsToDeliver().getLast())).collect(Collectors.toList()).contains(false);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Map map = (Map) o;

        //Permet d'être certain de la liste des clients de la map mais ralentis les algos et cette liste n'est jamais changé.
        //if (clients != null ? !clients.equals(map.clients) : map.clients != null) return false;
        return vehicles != null ? vehicles.equals(map.vehicles) : map.vehicles == null;
    }

    @Override
    public int hashCode() {
        int result = clients != null ? clients.hashCode() : 0;
        result = 31 * result + (vehicles != null ? vehicles.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Map{" +
                "clients=" + clients +
                ", vehicles=" + vehicles +
                '}';
    }
}
