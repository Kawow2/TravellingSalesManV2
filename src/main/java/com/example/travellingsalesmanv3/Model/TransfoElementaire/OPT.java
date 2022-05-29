package com.example.travellingsalesmanv3.Model.TransfoElementaire;

import com.example.travellingsalesmanv3.Model.Structure.Client;
import com.example.travellingsalesmanv3.Model.Structure.Map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class OPT extends VoisinAlgo {
    @Override
    public Map lancer(Map map, int posA, int posB, int nbV) {
        return OPTOnce(map, posA, posB, nbV);
    }

    @Override
    public ArrayList<Map> lancerToutVoisin(Map map) {
        return OPTAll(map);
    }

    public Map OPTOnce(Map map, int posA, int posB, int nbV) {
        LinkedList<Client> clients = map.getVehicles().get(nbV).getClientsToDeliver();
        List<Client> clientCopy = new ArrayList<>(clients.subList(posA + 1, posB + 1));
        Collections.reverse(clientCopy);
        List<Client> finalList = new ArrayList<>(clients.subList(0, posA + 1));
        finalList.addAll(clientCopy);
        finalList.addAll(clients.subList(posB + 1, clients.size()));
        map.getVehicles().get(nbV).setClientsToDeliver(new LinkedList<Client>(finalList));
        return map;
    }

    //INTER
    public ArrayList<Map> OPTAll(Map map) {
        ArrayList<Map> maps = new ArrayList<>();
        for (int k = 0; k <= map.getVehicles().size() - 1; k++) {
            LinkedList<Client> clients = map.getVehicles().get(k).getClientsToDeliver();
            if (clients.size() >= 4)
                for (int i = 1; i < clients.size() - 1; i++) {
                    Client cl1 = clients.get(i);
                    Client cl2 = clients.get(i + 1);

                    for (int j = i + 2; j < clients.size() - 2; j++) {
                        Client cl3 = clients.get(j);
                        Client cl4 = clients.get(j + 1);

                        if (AreTwoEdgeDisjoint(cl1, cl2, cl3, cl4)) {
                            Map mapClone = map.cloneMap();
                            Map m = OPTOnce(mapClone, i, j, k);
                            maps.add(m);

                        }
                    }
                }
        }
        return maps;
    }

    public boolean AreTwoEdgeDisjoint(Client cl1, Client cl2, Client cl3, Client cl4) {
        if (AreTwoClientEquals(cl1, cl2) || AreTwoClientEquals(cl3, cl4)) {
            return false;
        }

        if (AreTwoClientEquals(cl1, cl3) || AreTwoClientEquals(cl1, cl4) || AreTwoClientEquals(cl2, cl3) || AreTwoClientEquals(cl2, cl4))
            return false;
        else
            return true;
    }

    public boolean AreTwoClientEquals(Client cl1, Client cl2) {
        if (cl1.getPoint().x == cl2.getPoint().x && cl1.getPoint().y == cl2.getPoint().y)
            return true;
        else
            return false;
    }
}