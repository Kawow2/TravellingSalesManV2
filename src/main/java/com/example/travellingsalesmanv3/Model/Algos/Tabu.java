package com.example.travellingsalesmanv3.Model.Algos;

import com.example.travellingsalesmanv3.Model.Structure.Map;
import com.example.travellingsalesmanv3.Model.Tools.Tools;
import com.example.travellingsalesmanv3.Model.TransfoElementaire.VoisinAlgo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Tabu extends Algorithme {

    private static boolean TABUCONDITION = true;
    private static int tailleTabou = 10;

    public Tabu(ArrayList<VoisinAlgo> listVoisins) {
        super(listVoisins);
    }

    @Override
    public Map lancer(Map map,String fileName) {
        map = this.rechercheTabu(map,fileName);
        return map;
    }

    @Override
    public String toString()
    {
        return "TABU";
    }

    private Map rechercheTabu(Map map,String fileName) {
        TABUCONDITION = true;
        var listToWrite = new ArrayList<Double>();

        Map bestSolution = map.cloneMap();
        Map bestCandidat;
        ArrayList<Map> tabuclients = new ArrayList<>();
        tabuclients.add(map);
        while (TABUCONDITION) {
            ArrayList<Map> voisin = new ArrayList<>();
            for (VoisinAlgo voisinAlgo : this.voisins)
                voisin.addAll(voisinAlgo.lancerToutVoisin(bestSolution));

            bestCandidat = voisin.stream().filter(v -> !tabuclients.contains(v)).collect(Collectors.toList()).stream().findAny().orElse(null);

            if (bestCandidat == null) {
                TABUCONDITION = false;
                bestCandidat = bestSolution;
            }
            for (Map sCand : voisin)
                if (!tabuclients.contains(sCand) && Tools.calculerDistanceTotal(sCand) < Tools.calculerDistanceTotal(bestCandidat))
                    bestCandidat = sCand;

            if (Tools.calculerDistanceTotal(bestCandidat) < Tools.calculerDistanceTotal(bestSolution))
                bestSolution = bestCandidat;
            if (tabuclients.size() == tailleTabou)
                tabuclients.remove(tabuclients.get(0));
            tabuclients.add(bestCandidat);

        }

        String s = "";
        for (Map m : tabuclients) {
            Double d = Tools.calculerDistanceTotal(m);
            if (listToWrite.contains(d))
                listToWrite.add(Tools.calculerDistanceTotal(m));
        }

        super.WriteToFile(fileName,listToWrite);





        return bestSolution;
    }
}
