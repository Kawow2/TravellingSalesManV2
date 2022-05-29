package com.example.travellingsalesmanv3.Model.Algos;

import com.example.travellingsalesmanv3.Model.Fenetre.Fenetre;
import com.example.travellingsalesmanv3.Model.TransfoElementaire.VoisinAlgo;

import java.util.ArrayList;

public enum ListAlgo {
    Exemple,
    RECUIT,
    TABU;

    public static Algorithme getAlgorithme(ListAlgo algo, ArrayList<VoisinAlgo> voisinAlgo) throws Exception {
        switch (algo) {
            case Exemple:
                return new Exemple(voisinAlgo);
            case TABU:
                return new Tabu(voisinAlgo);
            case RECUIT:
                return new RecuitSimule(voisinAlgo);
            default:
                throw new Exception("Algorithme inconnu");
        }
    }
}
