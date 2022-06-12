package com.example.travellingsalesmanv3.Model.TransfoElementaire;

public enum ListVoisins {
    OPT,
    EXCHANGEINTRA,
    EXCHANGEINTER,
    RELOCATE;

    public static VoisinAlgo getAlgorithmeVoisin(ListVoisins algo) throws Exception {
        switch (algo) {
            case EXCHANGEINTRA:
                return new ExchangeIntra();
            case EXCHANGEINTER:
                return new ExchangeInter();
            case RELOCATE:
                return new Relocate();
            case OPT:
                return new OPT();
            default:
                throw new Exception("Algorithme inconnu");
        }
    }
}
