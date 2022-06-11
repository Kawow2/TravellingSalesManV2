package com.example.travellingsalesmanv3.Model.TransfoElementaire;

import com.example.travellingsalesmanv3.Model.Structure.Client;
import com.example.travellingsalesmanv3.Model.Structure.Map;
import com.example.travellingsalesmanv3.Model.Structure.Vehicle;

import java.util.ArrayList;
import java.util.LinkedList;

public class Exchange extends VoisinAlgo {
    @Override
    public Map lancer(Map map, int posA, int posB, int nbV) {
        return ExchangeVoisinIntra(map, posA, posB, nbV);
    }

    @Override
    public ArrayList<Map> lancerToutVoisin(Map map) {
        ArrayList<Map> list = ExchangeListVoisinInter(map);
        list.addAll(ExchangeListVoisinIntra(map));
        return list;
    }

    public Map ExchangeVoisinIntra(Map map, int posA, int posB, int nbV) {
        LinkedList<Client> clients = map.getVehicles().get(nbV).getClientsToDeliver();
        Client cliA = clients.get(posA);
        clients.set(posA, clients.get(posB));
        clients.set(posB, cliA);
        return map;
    }

    public Map ExchangeVoisinInter(Map map, int posA, int posB, int nbVA, int nbVB) {
        int capA = map.getVehicles().get(nbVA).getCapaciteRestant();
        int capB = map.getVehicles().get(nbVB).getCapaciteRestant();
        LinkedList<Client> clients = map.getVehicles().get(nbVA).getClientsToDeliver();
        LinkedList<Client> clients2 = map.getVehicles().get(nbVB).getClientsToDeliver();

        if (capA < clients2.get(posB).getValue()-capB && capB < clients.get(posA).getValue()-capA)
            return null;

        Client cliA = clients.get(posA);
        clients.set(posA, clients2.get(posB));
        clients2.set(posB, cliA);
        return map;
    }

    public ArrayList<Map> ExchangeListVoisinIntra(Map map) {
        ArrayList<Map> maps = new ArrayList<>();
        for (int k = 0; k <= map.getVehicles().size() - 1; k++) {
            LinkedList<Client> clients = map.getVehicles().get(k).getClientsToDeliver();
            for (int i = 1; i < clients.size() - 1; i++) {
                for (int j = i + 1; j < clients.size() - 1; j++) {
                    Map mapClone = map.cloneMap();
                    Map m = ExchangeVoisinIntra(mapClone, i, j, k);
                    maps.add(m);
                }
            }
        }

        return maps;
    }

    public ArrayList<Map> ExchangeListVoisinInter(Map map) {

        ArrayList<Map> maps = new ArrayList<>();

        for (int k = 0; k <= map.getVehicles().size() - 1; k++) {
            Vehicle v = map.getVehicles().get(k);
            for (int i = 1; i < v.getClientsToDeliver().size() - 1; i++) {
                for (int k2 = k + 1; k2 <= map.getVehicles().size() - 2; k2++) {
                    Vehicle v2 = map.getVehicles().get(k2);
                    for (int j = 1; j < v2.getClientsToDeliver().size() - 1; j++) {
                        Map mapClone = map.cloneMap();
                        Map m = this.ExchangeVoisinInter(mapClone, i, j, k, k2);
                        if (m != null)
                            maps.add(m);
                    }
                }
            }

        }
        return maps;
    }

}