package com.example.travellingsalesmanv3.Model.Tools;

import com.example.travellingsalesmanv3.Model.Algos.RecuitSimule;
import com.example.travellingsalesmanv3.Model.Algos.Tabu;
import com.example.travellingsalesmanv3.Model.Fenetre.Fenetre;
import com.example.travellingsalesmanv3.Model.Structure.Map;
import com.example.travellingsalesmanv3.Model.TransfoElementaire.ListVoisins;
import com.example.travellingsalesmanv3.Model.TransfoElementaire.VoisinAlgo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import static com.example.travellingsalesmanv3.Model.Tools.Tools.generateurSolutionAleatoire;
import static com.example.travellingsalesmanv3.Model.TransfoElementaire.ListVoisins.getAlgorithmeVoisin;

public class GetResults {
    public static void calcSolutionsForAlgos(Fenetre fenetre) throws FileNotFoundException {
        ArrayList<String> filesnames = new ArrayList<>();
        filesnames = Tools.getAllFilesName();

        ArrayList<Integer> minRequiredVeh = new ArrayList<>();
        ArrayList<VoisinAlgo> AlgosTE = new ArrayList<>();
        try {
            AlgosTE.add(getAlgorithmeVoisin(ListVoisins.EXCHANGE));
            AlgosTE.add(getAlgorithmeVoisin(ListVoisins.OPT));
            AlgosTE.add(getAlgorithmeVoisin(ListVoisins.RELOCATE));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Tabu tabu = new Tabu(AlgosTE);
        RecuitSimule rec = new RecuitSimule(AlgosTE);

        for (String file:filesnames) {
            Random rnd = new Random();
            Map currentMap = generateurSolutionAleatoire(Tools.ReadOneFile(file), rnd);
            Map tabuMap = currentMap.cloneMap();
            Map recuitMap = currentMap.cloneMap();
            tabu.lancer(tabuMap,file);
            rec.lancer(recuitMap,file);
        }

//        Random rnd = new Random();
//        Map currentMap = generateurSolutionAleatoire(Tools.ReadOneFile("A3605.txt"), rnd);
//        Map tabuMap = currentMap.cloneMap();
//        Map recuitMap = currentMap.cloneMap();
//        tabu.lancer(tabuMap);
//        rec.lancer(recuitMap);
    }
}
