package com.example.travellingsalesmanv3.Model.TransfoElementaire;

public enum ListVoisins {
    OPT,
    EXCHANGE,
    RELOCATE;

    public static VoisinAlgo getAlgorithmeVoisin(ListVoisins algo) throws Exception {
        switch (algo) {
            case EXCHANGE:
                return new Exchange();
            case RELOCATE:
                return new Relocate();
            case OPT:
                return new OPT();
            default:
                throw new Exception("Algorithme inconnu");
        }
    }
}
