# TravellingSalesManV2

Ce code représente notre travail réalise pour la matière d'optimisation discrète. BIGEARD Antoine, TRICOT Yann et
CHEVREAUD Alexandre

## Lancement du programme

Pour lancer ce programme, il faut cloner le projet github puis lancer avec inteliJ le programme. La version a utiliser
est JAVA 18. Le programme est compatible pour windows et mac (M1+ compris).

## Explication de la partie graphique

Lors du lancement de l'application, une fenêtre graphique s'affichera.

Sur la droite de la fenêtre graphique (la partie rouge), nous pouvons retrouver plusieurs éléments.

- Le premier est un dropdown permet de choisir entre les algorithmes Tabou, Recuit et Exemple (Exemple affiche seulement
  la carte aléatoire générer)
- Le deuxième est aussi un dropdown permettant de choisir le fichier sur lequel nous effectuons nos algorithmes.
- Nous avons ensuite des radios bouttons permettant de choisir les algorithmes de voisinages qui devront être pris en
  compte

La partie de gauche, elle, est consacré à l'affichage des résultats. Ils sont affiché sous formes de graphes avec écrit
en dessous le nombre de véhicule compris dans le graphique, la fitness et le nombre de clients .

## Modification de certains paramètres

Certains paramètre ne sont pas modifiable directement depuis la fenêtre graphique. Il est donc nécessaire de les
modifiers depuis le code.

Pour modifier les paramètres mu et le nombre d'itération de recuit il faut aller dans la classe RecuitSimule.java et les
2 paramètres se trouveront en variable de classe.

Pour modifier la température de recuit, il faut aller dans le méthode lancer() de recuit, et la variable température
devra être modifiée.
