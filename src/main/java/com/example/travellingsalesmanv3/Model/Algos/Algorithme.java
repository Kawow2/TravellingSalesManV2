package com.example.travellingsalesmanv3.Model.Algos;


import com.example.travellingsalesmanv3.Model.Fenetre.Fenetre;
import com.example.travellingsalesmanv3.Model.Structure.Map;
import com.example.travellingsalesmanv3.Model.TransfoElementaire.VoisinAlgo;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class Algorithme {

    protected ArrayList<VoisinAlgo> voisins;

    public Algorithme(ArrayList<VoisinAlgo> listVoisins) {
        this.voisins = listVoisins;
    }

    /***
     * En gros le but de cette méthode c'est de donnée le voisin d'une map à chaque fois
     * @param map carte dont on doit trouver le voisin
     * @return Map voisin de la map précedente
     * @throws FileNotFoundException
     */
    public abstract Map lancer(Map map);
}
