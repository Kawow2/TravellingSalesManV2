package com.example.travellingsalesmanv3.Model.Algos;



import com.example.travellingsalesmanv3.Model.Fenetre.Fenetre;
import com.example.travellingsalesmanv3.Model.Structure.Map;
import com.example.travellingsalesmanv3.Model.TransfoElementaire.Relocate;
import com.example.travellingsalesmanv3.Model.TransfoElementaire.VoisinAlgo;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Exemple extends Algorithme {

    public Exemple(ArrayList<VoisinAlgo> lv) {
        super(lv);
    }

    @Override
    public Map lancer(Map map) {
        try {
            map = Exemple3(map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        this.fenetre.afficherMap(map);
        return map;
    }

    private Map Exemple3(Map map) throws Exception {
        Relocate relocate = new Relocate();
        relocate.relocateAll(map);

        return map;
    }



}
