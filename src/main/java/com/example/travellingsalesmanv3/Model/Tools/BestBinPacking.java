package com.example.travellingsalesmanv3.Model.Tools;

import com.example.travellingsalesmanv3.Model.Structure.Client;
import com.example.travellingsalesmanv3.Model.Structure.Map;
import com.example.travellingsalesmanv3.Model.Structure.Vehicle;

import java.util.ArrayList;

public class BestBinPacking {
    public BestBinPacking() {

    }

    public int BestBinPacking(Map m, int nbFile) {

        int valueMAX = new Vehicle().getCapaciteRestant();
        ArrayList<Client> clients = m.getClients();
        ArrayList<Integer> spaceRemainingInVehicle = new ArrayList<>();
        int nb = 0;


        int totalQuantity = m.getClients().stream().mapToInt(o -> o.getValue()).sum();

        for (int i = 0; i < m.getClients().size(); i++) {
            int min = new Vehicle().getCapaciteRestant() + 1;
            int indexBestVehicule = 0;
            for (int j = 0; j < nb; j++) {
                if (spaceRemainingInVehicle.get(j) >= clients.get(j).getValue()
                        && spaceRemainingInVehicle.get(j) - clients.get(j).getValue() < min) {
                    indexBestVehicule = j;
                    min = spaceRemainingInVehicle.get(j) - clients.get(j).getValue();
                }
            }

            if (min == valueMAX + 1) {
                spaceRemainingInVehicle.add(nb, valueMAX - clients.get(i).getValue());
                nb++;
            } else {
                spaceRemainingInVehicle.set(indexBestVehicule, spaceRemainingInVehicle.get(indexBestVehicule) - clients.get(i).getValue());
            }
        }

        System.out.println("File n°" + nbFile);
        System.out.println("Total quantity : " + totalQuantity);
        System.out.println("Min Required Vehicules : " + nb);
        return nb;
    }

    public int BestBinPackingV2(Map m, int nbFile) {

        var listVehicule = new ArrayList<Vehicle>(m.getClients().size());
        listVehicule.add(new Vehicle());
        int totalQuantity = m.getClients().stream().mapToInt(o -> o.getValue()).sum();


        for (Client cli : m.getClients()) {
            var valuesRemaining = new ArrayList<Integer>(100);
            for (int j = 0; j < 100; j++)
                valuesRemaining.add(0);

            for (Vehicle v : listVehicule) {
                var indexVeh = listVehicule.indexOf(v);

                if (cli.getValue() < v.getCapaciteRestant()) {
                    var placeRestante = v.getCapaciteRestant() - cli.getValue();
                    valuesRemaining.set(indexVeh, placeRestante);
                }
            }

            var placeVehicule = valuesRemaining.stream().filter(i -> i > 0).min(Integer::compare).orElse(-1);
            var indexMin = valuesRemaining.indexOf(placeVehicule);

            if (indexMin == -1) {
                var v = new Vehicle();
                v.addClients(cli);
                v.setCapaciteRestant(v.getCapaciteRestant() - cli.getValue());
                listVehicule.add(v);
            } else {
                var v = listVehicule.get(indexMin);
                v.addClients(cli);
                v.setCapaciteRestant(v.getCapaciteRestant() - cli.getValue());
            }
        }

        System.out.println("File n°" + nbFile);
        System.out.println("Total quantity : " + totalQuantity);
        System.out.println("Min Required Vehicules : " + listVehicule.size());
        return listVehicule.size();
    }
}
