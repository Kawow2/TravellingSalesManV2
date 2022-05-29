package com.example.travellingsalesmanv3.Model.Algos;

import com.example.travellingsalesmanv3.Model.Fenetre.Fenetre;
import com.example.travellingsalesmanv3.Model.Structure.Map;
import com.example.travellingsalesmanv3.Model.TransfoElementaire.VoisinAlgo;

import java.util.ArrayList;
import java.util.Random;

public class RecuitSimule extends Algorithme {
    private static int N1 = 100;
    private static int N2 = 100;

    public RecuitSimule(ArrayList<VoisinAlgo> listVoisins) {
        super(listVoisins);
    }

    @Override
    public Map lancer(Map map) {
        return null;
    }


    private Map recuitSimule(Map map, double tailleInitial) {
        Random rnd = new Random();
        Map cloneMap = map.cloneMap();
        int i = 0;
        for (int k = 0; k < N1; k++) {
            for (int l = 1; l < N2; k++) {
                int vehicleSelect = rnd.nextInt(map.getVehicles().size());
                //int

            }
        }

        return null;
    }
}
