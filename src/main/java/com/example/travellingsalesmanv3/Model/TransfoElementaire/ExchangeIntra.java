package com.example.travellingsalesmanv3.Model.TransfoElementaire;

import com.example.travellingsalesmanv3.Model.Structure.Client;
import com.example.travellingsalesmanv3.Model.Structure.Map;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class ExchangeIntra extends VoisinAlgo {
    @Override
    public Map lancer(Map map, int posA, int posB, int nbV, int nbv2) {
        Random rnd = new Random();
        posB = rnd.nextInt(1, map.getVehicles().get(nbV).getClientsToDeliver().size() - 1);
        return ExchangeVoisinIntra(map, posA, posB, nbV);
    }

    @Override
    public ArrayList<Map> lancerToutVoisin(Map map) {
        return ExchangeListVoisinIntra(map);
    }

    public Map ExchangeVoisinIntra(Map map, int posA, int posB, int nbV) {
        LinkedList<Client> clients = map.getVehicles().get(nbV).getClientsToDeliver();
        Client cliA = clients.get(posA);
        clients.set(posA, clients.get(posB));
        clients.set(posB, cliA);
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

}