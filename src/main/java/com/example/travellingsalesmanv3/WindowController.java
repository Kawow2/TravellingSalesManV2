package com.example.travellingsalesmanv3;

import com.example.travellingsalesmanv3.Model.Algos.Algorithme;
import com.example.travellingsalesmanv3.Model.Algos.ListAlgo;
import com.example.travellingsalesmanv3.Model.Structure.Client;
import com.example.travellingsalesmanv3.Model.Structure.Map;
import com.example.travellingsalesmanv3.Model.Structure.Vehicle;
import com.example.travellingsalesmanv3.Model.Tools.Tools;
import com.example.travellingsalesmanv3.Model.TransfoElementaire.Exchange;
import com.example.travellingsalesmanv3.Model.TransfoElementaire.OPT;
import com.example.travellingsalesmanv3.Model.TransfoElementaire.Relocate;
import com.example.travellingsalesmanv3.Model.TransfoElementaire.VoisinAlgo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class WindowController {
    private static final int zoom = 5;
    List<Color> allColors;
    private String currentFile;
    private ListAlgo currentAlgo;
    private ArrayList<Boolean> voisinsAlgo = new ArrayList<>();
    @FXML
    private Button runButton;
    @FXML
    private RadioButton ExchangeRadioButton;
    @FXML
    private RadioButton OPTRadioButton;
    @FXML
    private RadioButton RelocateRadioButton;
    @FXML
    private Canvas canva;
    @FXML
    private Label fitnessLabel;
    private Map currentMap;
    @FXML
    private Label nbClientLabel;

    @FXML
    private Label nbVehicleLabel;


    @FXML
    private ComboBox<ListAlgo> listAlgoComboBox;

    @FXML
    private ComboBox<String> listFilesComboBox;

    @FXML
    public void initialize() {
        initComboBox();
        initVariables();
        currentMap = UpdateMap();
        lancerAlgorithme();

    }

    /**
     * OPT correspond à index 0, relocate à 1 et exchange à 2
     *
     * @param event
     */
    @FXML
    void selectOPT(ActionEvent event) {
        this.voisinsAlgo.set(0, !this.voisinsAlgo.get(0));

    }

    @FXML
    void selectRelocate(ActionEvent event) {
        this.voisinsAlgo.set(1, !this.voisinsAlgo.get(1));

    }

    @FXML
    void selectExchange(ActionEvent event) {
        this.voisinsAlgo.set(2, !this.voisinsAlgo.get(2));
    }

    private void initVariables() {
        this.currentFile = this.listFilesComboBox.getItems().get(0);
        this.currentAlgo = this.listAlgoComboBox.getItems().get(0);
        this.voisinsAlgo.add(false);
        this.voisinsAlgo.add(false);
        this.voisinsAlgo.add(false);
        allColors = this.getAllColors();
    }


    private ArrayList<VoisinAlgo> initListVoisins() {
        var listVoisins = new ArrayList<VoisinAlgo>();
        if (this.voisinsAlgo.get(0))
            listVoisins.add(new OPT());
        if (this.voisinsAlgo.get(1))
            listVoisins.add(new Relocate());
        if (this.voisinsAlgo.get(2))
            listVoisins.add(new Exchange());
        return listVoisins;
    }

    @FXML
    void changeCurrentAlgo(ActionEvent event) {
        this.currentAlgo = this.listAlgoComboBox.getValue();
    }

    @FXML
    void changeCurrentFile(ActionEvent event) {
        this.currentFile = this.listFilesComboBox.getValue();
        System.out.println(this.currentFile);
    }

    private void lancerAlgorithme() {
        currentMap = UpdateMap();
        clearDraw();
        var listVoisins = initListVoisins();
        Algorithme algorithme = null;
        try {
            algorithme = ListAlgo.getAlgorithme(this.currentAlgo, listVoisins);
        } catch (Exception e) {
            e.printStackTrace();
        }
        currentMap = algorithme.lancer(currentMap);
        this.nbVehicleLabel.setText(String.valueOf(currentMap.getVehicles().size()));
        this.nbClientLabel.setText(String.valueOf(currentMap.getClients().size() - 1));
        drawCurrentMapInCanva();
    }

    private void drawCurrentMapInCanva() {
        GraphicsContext gc = canva.getGraphicsContext2D();
        this.fitnessLabel.setText(String.valueOf(Tools.calculerDistanceTotal(currentMap)));
        var j = 0;
        for (Vehicle v : currentMap.getVehicles()) {
            var color = allColors.get(j);
            var clients = v.getClientsToDeliver();
            var c1 = clients.get(0);

            gc.beginPath();
            gc.moveTo(c1.getPoint().x * zoom, c1.getPoint().y * zoom);

            for (int i = 1; i < clients.size(); i++) {
                var current = clients.get(i);
                gc.lineTo(current.getPoint().x * zoom, current.getPoint().y * zoom);
            }
            gc.closePath();
            gc.setStroke(color);
            gc.stroke();
            j++;
        }

        for (Client c : currentMap.getClients()) {
            if (currentMap.getClients().indexOf(c) == 0)
                gc.fillOval((c.getPoint().x * zoom) - 6, (c.getPoint().y * zoom) - 6, 20, 20);
            else
                gc.fillOval((c.getPoint().x * zoom) - 3, (c.getPoint().y * zoom) - 3, 10, 10);
        }
    }

    private void clearDraw() {
        GraphicsContext gc = canva.getGraphicsContext2D();
        gc.clearRect(0, 0, canva.getWidth(), canva.getHeight());
    }

    @FXML
    void runAlgo(ActionEvent event) {
        lancerAlgorithme();
    }

    /**
     * Génèré des couelurs utilisés pour le graphe
     *
     * @return
     */
    private List<Color> getAllColors() {
        List<Color> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.ORANGE);
        colors.add(Color.VIOLET);
        colors.add(Color.OLIVEDRAB);
        colors.add(Color.DARKGREY);
        colors.add(Color.YELLOWGREEN);
        colors.add(Color.DARKBLUE);
        colors.add(Color.ORANGERED);
        colors.add(Color.TURQUOISE);
        colors.add(Color.DARKSALMON);
        colors.add(Color.BROWN);
        colors.add(Color.SANDYBROWN);
        colors.add(Color.FUCHSIA);
        return colors;
    }


    /**
     * remplace la current Map
     *
     * @return
     */
    private Map UpdateMap() {
        Random rnd = new Random();
        try {
            return Tools.generateurSolutionAleatoire(Tools.ReadOneFile(currentFile), rnd);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    private void initComboBox() {
        listFilesComboBox.getItems().removeAll(listFilesComboBox.getItems());
        listFilesComboBox.getItems().addAll(Tools.getAllFilesName());
        listAlgoComboBox.getItems().removeAll(listAlgoComboBox.getItems());
        listAlgoComboBox.getItems().addAll(ListAlgo.values());
    }

}