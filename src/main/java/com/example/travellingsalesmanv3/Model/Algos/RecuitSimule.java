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
    private static int N2 = 10000;
    private static double temperature = 300;
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
        int nbTemp = (int) (Math.log(Math.log(0.8) / Math.log(0.01)) / Math.log(mu)) * 3;
        double temperature = 0;

        var listToWrite = new ArrayList<Double>();
        Random rnd = new Random();
        Map cloneMap = map.cloneMap();

        Map bestSolution = cloneMap.cloneMap();
        double minFitness = Integer.MAX_VALUE;
        double fitnessBestSolution = Tools.calculerDistanceTotal(cloneMap);
        Map nextVoisin = null;

        try {
            for (int k = 0; k < nbTemp; k++) {
                for (int l = 1; l < N2; l++) {
                    cloneMap = map.cloneMap();
                    int algoV = rnd.nextInt(this.voisins.size());
                    VoisinAlgo v = this.voisins.get(algoV);

                    ArrayList<Map> voisin = new ArrayList<>();
                    voisin.addAll(v.lancerToutVoisin(map));

                    //choix du voisin random
                    if (voisin.size() <= 1)
                        System.out.println(v.getClass().toString());
                    Map randomVoisin = voisin.get(rnd.nextInt(voisin.size() - 1));
                    double fitnessVoisin = Tools.calculerDistanceTotal(randomVoisin);

                    var diffFitness = fitnessVoisin - fitnessBestSolution;
                    // Check pk fichier A6009 diffFitness = fitnessVoisin - fitnessBestSolution = 0
                    if (k == 1 && l == 1) {
                        temperature = -(Math.abs(diffFitness)) / Math.log(0.8);
                        System.out.println(temperature);
                    }

                    //si voisin est meilleur
                    double proba = rnd.nextDouble();
                    if (diffFitness <= 0 || proba <= Math.exp(-diffFitness / temperature)) {
                        nextVoisin = randomVoisin;
                        fitnessBestSolution = fitnessVoisin;
                    } else {
                        nextVoisin = cloneMap;
                    }
                    map = nextVoisin;
                    Double d = Tools.calculerDistanceTotal(map);
                    if (!listToWrite.contains(d))
                        listToWrite.add(d);
                }
                temperature = mu * temperature;
                System.out.println(k);
            }

           super.WriteToFile(fileName,listToWrite);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}