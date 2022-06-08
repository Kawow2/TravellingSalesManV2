package com.example.travellingsalesmanv3.Model.TransfoElementaire;

import com.example.travellingsalesmanv3.Model.Structure.Client;
import com.example.travellingsalesmanv3.Model.Structure.Map;
import com.example.travellingsalesmanv3.Model.Structure.Vehicle;

import java.util.ArrayList;
import java.util.LinkedList;

public class Relocate extends VoisinAlgo {
    public Relocate() {
    }

    public Map relocateOnce(Map map, int clientToRelocate, int posToRelocate, int posVehicle) {
        Vehicle vehicle = map.getVehicles().get(posVehicle);
        LinkedList<Client> clients = vehicle.getClientsToDeliver();
        Client c = clients.remove(clientToRelocate);
        clients.add(posToRelocate, c);
        return map;
    }

    public Map relocateOnceInter(Map map, int clientToRelocate, int posToRelocate, int posVehicle, int posVehicle2) {
        Vehicle vehicle = map.getVehicles().get(posVehicle);
        Vehicle vehicle2 = map.getVehicles().get(posVehicle2);
        LinkedList<Client> clients = vehicle.getClientsToDeliver();
        LinkedList<Client> clients2 = vehicle2.getClientsToDeliver();
        Client c = clients.remove(clientToRelocate);
        clients2.add(posToRelocate, c);
        vehicle.setCapaciteRestant(vehicle.getCapaciteRestant() + c.getValue());
        vehicle2.setCapaciteRestant(vehicle2.getCapaciteRestant() - c.getValue());
        return map;
    }

    public ArrayList<Map> relocateAll(Map map) {
        ArrayList<Map> neighbours = new ArrayList<>();

        for (int indexVehicle = 0; indexVehicle < map.getVehicles().size(); indexVehicle++) {
            Vehicle vehicle = map.getVehicles().get(indexVehicle);
            LinkedList<Client> clients = vehicle.getClientsToDeliver();
            int clientsListLength = clients.size();
            for (int indexVehicle2 = 0; indexVehicle2 < map.getVehicles().size(); indexVehicle2++) {
                Vehicle vehicle2 = map.getVehicles().get(indexVehicle2);
                LinkedList<Client> clients2 = vehicle2.getClientsToDeliver();
                int clientsListLength2 = clients2.size();
                for (int index = 1; index < clientsListLength - 1; index++)
                    for (int subIndex = 1; subIndex < clientsListLength2 - 1; subIndex++) {
                        LinkedList<Client> clientsBuffer = clients;
                        vehicle.setClientsToDeliver(clientsBuffer);
                        if (vehicle2.getCapaciteRestant() > clients.get(index).getValue())
                            neighbours.add(relocateOnceInter(map.cloneMap(), index, subIndex, indexVehicle, indexVehicle2));
                    }
            }

        }
        return neighbours;
    }

    @Override
    public Map lancer(Map map, int posA, int posB, int nbV) {
        return this.relocateOnce(map, posA, posB, nbV);
    }

    @Override
    public ArrayList<Map> lancerToutVoisin(Map map) {
        return this.relocateAll(map);
    }
}
