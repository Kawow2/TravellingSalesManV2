package com.example.travellingsalesmanv3.Model.Algos;

import com.example.travellingsalesmanv3.Model.Fenetre.Fenetre;
import com.example.travellingsalesmanv3.Model.Structure.Map;
import com.example.travellingsalesmanv3.Model.Tools.Tools;
import com.example.travellingsalesmanv3.Model.TransfoElementaire.VoisinAlgo;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Tabu extends Algorithme {

    private static boolean TABUCONDITION = true;

    public Tabu(ArrayList<VoisinAlgo> listVoisins) {
        super(listVoisins);
    }

    @Override
    public Map lancer(Map map) {
        map = this.rechercheTabu(map);
        return map;
    }

    //todo opti ?
    private Map rechercheTabu(Map map) {
        System.out.println("debut tabou");
        TABUCONDITION = true;
        Map bestSolution = map.cloneMap();
        Map bestCandidat;
        ArrayList<Map> tabuclients = new ArrayList<>();
        tabuclients.add(map);
        while (TABUCONDITION) {
            ArrayList<Map> voisin = new ArrayList<>();
            for (VoisinAlgo voisinAlgo : this.voisins) {
                voisin.addAll(voisinAlgo.lancerToutVoisin(bestSolution));
            }

            bestCandidat = voisin.stream().filter(v -> !tabuclients.contains(v)).collect(Collectors.toList()).stream().findAny().orElse(null);

            if (bestCandidat == null) {
                TABUCONDITION = false;
                bestCandidat = bestSolution;
            }
            for (Map sCand : voisin) {
                if (!tabuclients.contains(sCand) && Tools.calculerDistanceTotal(sCand) < Tools.calculerDistanceTotal(bestCandidat)) {
                    bestCandidat = sCand;
                }
            }
            if (Tools.calculerDistanceTotal(bestCandidat) < Tools.calculerDistanceTotal(bestSolution)) {
                bestSolution = bestCandidat;
//                this.fenetre.afficherMap(bestSolution);
            }
            tabuclients.add(bestCandidat);
        }
//        this.fenetre.afficherMap(bestSolution);
        System.out.println("fin tabou");

        return bestSolution;
    }
}
