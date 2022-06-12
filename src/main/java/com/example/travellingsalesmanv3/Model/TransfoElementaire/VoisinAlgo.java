package com.example.travellingsalesmanv3.Model.TransfoElementaire;


import com.example.travellingsalesmanv3.Model.Structure.Map;

import java.util.ArrayList;

public abstract class VoisinAlgo {


    public abstract Map lancer(Map map, int posA, int posB, int nbV, int nbv2);

    public abstract ArrayList<Map> lancerToutVoisin(Map map);

}
