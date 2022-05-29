package com.example.travellingsalesmanv3.Model.Structure;

import java.util.LinkedList;

public class Vehicle {
    private static final int CAPACITE_TOTAL = 100;
    private LinkedList<Client> clientsToDeliver = new LinkedList<>();
    private int capaciteRestant = CAPACITE_TOTAL;

    public LinkedList<Client> getClientsToDeliver() {
        return clientsToDeliver;
    }

    public void setClientsToDeliver(LinkedList<Client> clientsToDeliver) {
        this.clientsToDeliver = clientsToDeliver;
    }

    public void addClients(Client client) {
        clientsToDeliver.add(client);
    }

    public String ToStringVehicle() {
        String s = "";
        for (Client c : clientsToDeliver) {
            s += ToStringPoint(c.getPoint());

        }
        return s;
    }

    private String ToStringPoint(Point p) {
        return "[" + p.x + " " + p.y + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        if (capaciteRestant != vehicle.capaciteRestant) return false;
        return clientsToDeliver != null ? clientsToDeliver.equals(vehicle.clientsToDeliver) : vehicle.clientsToDeliver == null;
    }

    @Override
    public int hashCode() {
        int result = clientsToDeliver != null ? clientsToDeliver.hashCode() : 0;
        result = 31 * result + capaciteRestant;
        return result;
    }

    public int getCapaciteRestant() {
        return capaciteRestant;
    }

    public void setCapaciteRestant(int capaciteRestant) {
        this.capaciteRestant = capaciteRestant;
    }
}
