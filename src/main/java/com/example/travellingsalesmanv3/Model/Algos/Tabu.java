package com.example.travellingsalesmanv3.Model.Algos;

import com.example.travellingsalesmanv3.Model.Structure.Map;
import com.example.travellingsalesmanv3.Model.Tools.Tools;
import com.example.travellingsalesmanv3.Model.TransfoElementaire.VoisinAlgo;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Tabu extends Algorithme {

    private static boolean TABUCONDITION = true;
    private static int tailleTabou = 100;

    public Tabu(ArrayList<VoisinAlgo> listVoisins) {
        super(listVoisins);
    }

    @Override
    public Map lancer(Map map, String fileName) {
        map = this.rechercheTabu(map, fileName);
        return map;
    }

    @Override
    public String toString() {
        return "TABU";
    }

    private Map rechercheTabu(Map map, String fileName) {
        TABUCONDITION = true;
        var listToWrite = new ArrayList<Double>();
        int nbSolution = 0;
        int count = 0;
        Map bestSolution = map.cloneMap();
        Map bestCandidat;
        ArrayList<Double> tabuclients = new ArrayList<>();
        tabuclients.add(Tools.calculerDistanceTotal(map));
        while (TABUCONDITION) {
            count++;
            ArrayList<Map> voisin = new ArrayList<>();
            for (VoisinAlgo voisinAlgo : this.voisins)
                voisin.addAll(voisinAlgo.lancerToutVoisin(bestSolution));

            nbSolution += voisin.size();

            bestCandidat = voisin.stream().filter(v -> !tabuclients.contains(Tools.calculerDistanceTotal(v))).collect(Collectors.toList()).stream().findAny().orElse(null);

            if (bestCandidat == null) {
                TABUCONDITION = false;
                bestCandidat = bestSolution;
            }
            for (Map sCand : voisin)
                if (!tabuclients.contains(Tools.calculerDistanceTotal(sCand)) && Tools.calculerDistanceTotal(sCand) < Tools.calculerDistanceTotal(bestCandidat))
                    bestCandidat = sCand;

            Double d = Tools.calculerDistanceTotal(bestCandidat);
            listToWrite.add(d);

            if (Tools.calculerDistanceTotal(bestCandidat) < Tools.calculerDistanceTotal(bestSolution)) {
                bestSolution = bestCandidat;
            }

            tabuclients.add(Tools.calculerDistanceTotal(bestCandidat));
            if (count % 100 == 0)
                System.out.println(nbSolution);

        }


        super.WriteToFile(fileName, listToWrite);
        System.out.println("Nombre de solutiosn générées : " + nbSolution);


        return bestSolution;
    }
}
