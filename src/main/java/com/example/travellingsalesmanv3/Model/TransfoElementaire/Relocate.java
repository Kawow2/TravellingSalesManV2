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

    public ArrayList<Map> relocateAll(Map map) {
        ArrayList<Map> neighbours = new ArrayList<>();

        for (int indexVehicle = 0; indexVehicle < map.getVehicles().size(); indexVehicle++) {
            Vehicle vehicle = map.getVehicles().get(indexVehicle);
            LinkedList<Client> clients = vehicle.getClientsToDeliver();
            int clientsListLength = clients.size();
            for (int index = 1; index < clientsListLength - 1; index++)
                for (int subIndex = index + 1; subIndex < clientsListLength - 1; subIndex++) {
                    LinkedList<Client> clientsBuffer = clients;
                    vehicle.setClientsToDeliver(clientsBuffer);
                    neighbours.add(relocateOnce(map.cloneMap(), index, subIndex, indexVehicle));
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
