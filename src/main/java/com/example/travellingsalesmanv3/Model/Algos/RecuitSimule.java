package com.example.travellingsalesmanv3.Model.Algos;

import com.example.travellingsalesmanv3.Model.Structure.Map;
import com.example.travellingsalesmanv3.Model.Tools.Tools;
import com.example.travellingsalesmanv3.Model.TransfoElementaire.VoisinAlgo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RecuitSimule extends Algorithme {
    private static int N1 = 100;
    private static int N2 = 10_000;
    private static double mu = 0.9;


    public RecuitSimule(ArrayList<VoisinAlgo> listVoisins) {
        super(listVoisins);
    }

    @Override
    public Map lancer(Map map, String fileName) {
        map = this.recuitSimule(map,fileName);
        return map;
    }

    @Override
    public String toString()
    {
        return "RECUIT";
    }

    private Map recuitSimule(Map map,String fileName) {
        int nbSolution = 0;
        int nbTemp = (int) (Math.log(Math.log(0.8) / Math.log(0.01)) / Math.log(mu)) * 3;
        double temperature = 50;
        var listToWrite = new ArrayList<Double>();
        Random rnd = new Random();
        Map cloneMap = map.cloneMap();

        double fitnessBestSolution = Tools.calculerDistanceTotal(cloneMap);
        Map nextVoisin = null;

        try {
            for (int k = 0; k < nbTemp; k++) {
                for (int l = 1; l < N2; l++) {
                    cloneMap = map.cloneMap();
                    int algoV = rnd.nextInt(this.voisins.size());
                    VoisinAlgo v = this.voisins.get(algoV);


                    //choix du voisin random
                    int nbV = rnd.nextInt(map.getVehicles().size());
                    int nbV2 = rnd.nextInt(map.getVehicles().size());
                    int posA = rnd.nextInt(1, map.getVehicles().get(nbV).getClientsToDeliver().size() - 1);
                    int posB = rnd.nextInt(1, map.getVehicles().get(nbV2).getClientsToDeliver().size() - 1);
                    Map randomVoisin = v.lancer(map, posA, posB, nbV, nbV2);
                    double fitnessVoisin = Tools.calculerDistanceTotal(randomVoisin);
                    nbSolution++;

                    var diffFitness = fitnessVoisin - fitnessBestSolution;

                    if (k == 1 && l == 1) {
                        temperature = -(Math.abs(diffFitness)) / Math.log(0.8);
                    }

                    //si voisin est meilleur
                    double proba = rnd.nextDouble();
                    if (diffFitness <= 0 || proba <= Math.exp(-diffFitness / temperature)) {
                        nextVoisin = randomVoisin;
                        if (fitnessBestSolution != fitnessVoisin)
                            listToWrite.add(fitnessBestSolution);

                        fitnessBestSolution = fitnessVoisin;
                    } else {
                        nextVoisin = cloneMap;
                    }
                    map = nextVoisin;

                }
                temperature = mu * temperature;
            }

            super.WriteToFile(fileName,listToWrite);
            System.out.println("Nombre de solutions générées : "+nbSolution);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}