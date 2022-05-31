package com.example.travellingsalesmanv3.Model.Algos;

import com.example.travellingsalesmanv3.Model.Structure.Map;
import com.example.travellingsalesmanv3.Model.Tools.Tools;
import com.example.travellingsalesmanv3.Model.TransfoElementaire.VoisinAlgo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RecuitSimule extends Algorithme {
    private static int N1 = 100;
    private static int N2 = 100;
    private static double temperature = 1;
    private static double mu = 0.9;

    public RecuitSimule(ArrayList<VoisinAlgo> listVoisins) {
        super(listVoisins);
    }

    @Override
    public Map lancer(Map map) {
        map = this.recuitSimule(map);
        return map;
    }


    private Map recuitSimule(Map map) {
        long startTime = System.nanoTime();
        Random rnd = new Random();
        Map cloneMap = map.cloneMap();

        Map bestSolution = cloneMap.cloneMap();
        double minFitness = Integer.MAX_VALUE;
        double fitnessBestSolution = Tools.calculerDistanceTotal(cloneMap);
        Map nextVoisin = null;

        try {
            FileWriter myWriter = new FileWriter("src/main/java/com/example/travellingsalesmanv3/Model/Results/RECUIT_" + UUID.randomUUID() + ".txt");
            for (int k = 0; k < N1; k++) {
                for (int l = 1; l < N2; l++) {
                    ArrayList<Map> voisin = new ArrayList<>();
                    for (VoisinAlgo voisinAlgo : this.voisins)
                        voisin.addAll(voisinAlgo.lancerToutVoisin(cloneMap));

                    //choix du voisin random
                    Map randomVoisin = voisin.get(rnd.nextInt(voisin.size() - 1));
                    double fitnessVoisin = Tools.calculerDistanceTotal(randomVoisin);

                    var diffFitness = fitnessVoisin - fitnessBestSolution;

                    //si voisin est meilleur
                    if (diffFitness <= 0) {
                        nextVoisin = randomVoisin;
                        if (fitnessVoisin < minFitness) {
                            fitnessBestSolution = fitnessVoisin;
                            bestSolution = randomVoisin;
                        }
                    } else {
                        double proba = rnd.nextDouble();
                        var a = Math.exp(-diffFitness / temperature);
                        if (proba <= Math.exp(-diffFitness / temperature)) {
                            nextVoisin = randomVoisin;
                        } else { nextVoisin = cloneMap; }
                    }
                    cloneMap = nextVoisin;
                    myWriter.write(Double.toString(Tools.calculerDistanceTotal(cloneMap)) + "\n");

                }
                temperature = mu * temperature;
    //            long locElapsedTime = System.nanoTime() - startTime;
    //            long locDurationInMs = TimeUnit.MILLISECONDS.convert(locElapsedTime, TimeUnit.NANOSECONDS);
    //            System.out.println("Exec time: " + locDurationInMs + "ms");
            }
            long elapsedTime = System.nanoTime() - startTime;
            long durationInMs = TimeUnit.MILLISECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
            System.out.println("Total exec. time: " + durationInMs + "ms");

            myWriter.write(Long.toString(durationInMs) + "\n");
            myWriter.close();
        }catch (IOException e){}
        return bestSolution;
    }
}
