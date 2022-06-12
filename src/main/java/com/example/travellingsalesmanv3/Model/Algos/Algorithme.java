package com.example.travellingsalesmanv3.Model.Algos;


import com.example.travellingsalesmanv3.Model.Fenetre.Fenetre;
import com.example.travellingsalesmanv3.Model.Structure.Map;
import com.example.travellingsalesmanv3.Model.Tools.Tools;
import com.example.travellingsalesmanv3.Model.TransfoElementaire.VoisinAlgo;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Algorithme {
    private static final String FILEPATH  = "src/main/java/com/example/travellingsalesmanv3/Model/Results/";

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
    public abstract Map lancer(Map map, String fileName);

    public void WriteToFile(String filename,ArrayList<Double> values)
    {
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(FILEPATH + filename + ".txt");
            for (Double d : values) {
                String s = "";
                s = d.toString().replace(".", ",") + "\n";
                myWriter.write(s);

            }
            myWriter.close();

        }
        catch (IOException e) {
                e.printStackTrace();
            }
    }
}
