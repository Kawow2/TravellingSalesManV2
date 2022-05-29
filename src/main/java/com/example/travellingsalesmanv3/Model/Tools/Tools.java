package com.example.travellingsalesmanv3.Model.Tools;

import com.example.travellingsalesmanv3.Model.Structure.Client;
import com.example.travellingsalesmanv3.Model.Structure.Map;
import com.example.travellingsalesmanv3.Model.Structure.Point;
import com.example.travellingsalesmanv3.Model.Structure.Vehicle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Tools {
    public final static String PATH = "\\src\\main\\java\\com\\example\\travellingsalesmanv3\\Model\\files\\";

    public static ArrayList<Client> ReadOneFile(String fileName) throws FileNotFoundException {
        ArrayList<Client> clients = new ArrayList<>();
        String path = System.getProperty("user.dir");
        var str = path + PATH;
        File doc = new File(str + fileName );
        Scanner obj = new Scanner(doc);
        obj.nextLine();
        while (obj.hasNextLine()) {
            String s = obj.nextLine();
            String[] words = s.split(";");
            Client c = new Client(new Point(Integer.parseInt(words[1]), Integer.parseInt(words[2])), Integer.parseInt(words[3]), words[0]);
            clients.add(c);
        }

        return clients;
    }

    private static double CalculateDistance(Point p1, Point p2) {
        int xa = p1.x;
        int xb = p2.x;
        int ya = p1.y;
        int yb = p2.y;
        double d1 = Math.pow(xb - xa, 2);
        double d2 = Math.pow(yb - ya, 2);
        double sum = d1 + d2;
        double distance = Math.sqrt(sum);
        return distance;
    }

    public static double calculerDistanceTotal(Map map) {
        if (map == null)
        {
            var a = 1;

        }
        ArrayList<Vehicle> vehicles = map.getVehicles();
        double valeurTotal = vehicles.stream().mapToDouble(
                vehicle -> {
                    double distanceVehicle = 0;
                    LinkedList<Client> clients = vehicle.getClientsToDeliver();
                    for (int i = 0; i < clients.size() - 1; i++) {
                        distanceVehicle += CalculateDistance(clients.get(i).getPoint(), clients.get(i + 1).getPoint());
                    }
                    return distanceVehicle;
                }
        ).sum();
        return valeurTotal;
    }

    public static ArrayList<String> getAllFilesName() {
        String path = System.getProperty("user.dir");
        var str = path + "\\src\\main\\java\\com\\example\\travellingsalesmanv3\\Model\\files";
        File folder = new File(str);
        ArrayList<String> filesnames = new ArrayList<>();
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                filesnames.add(listOfFiles[i].getName());
            }
        }
        return filesnames;
    }

    public static Map generateurSolutionAleatoire(ArrayList<Client> clients, Random rnd) {
        ArrayList<Client> listClients = new ArrayList<>(clients);
        listClients.add(listClients.get(0));
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        Vehicle v = new Vehicle();
        vehicles.add(v);
        v.addClients(clients.remove(0));
        while (clients.size() != 0) {
            int c = rnd.nextInt(clients.size());
            Client client = clients.remove(c);
            if (v.getCapaciteRestant() < client.getValue()) {
                v.setCapaciteRestant(v.getCapaciteRestant() - listClients.get(0).getValue());
                v.addClients(listClients.get(0));
                v = new Vehicle();
                vehicles.add(v);
                v.setCapaciteRestant(v.getCapaciteRestant() - listClients.get(0).getValue());
                v.addClients(listClients.get(0));
            }
            v.setCapaciteRestant(v.getCapaciteRestant() - client.getValue());
            v.addClients(client);
        }
        v.addClients(listClients.get(0));
        return new Map(listClients, vehicles);
    }
}
