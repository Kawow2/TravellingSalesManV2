package com.example.travellingsalesmanv3.Model.Tools;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.example.travellingsalesmanv3.Model.Tools.Tools.PATH;


public class MinRequiredVeh {
    public static void GetMinRequiredVehicules(Integer qtyHandledPerVehicle) throws FileNotFoundException {
        ArrayList<String> filesnames = new ArrayList<>();
        filesnames = Tools.getAllFilesName();

        ArrayList<Integer> minRequiredVeh = new ArrayList<>();
        for (String file : filesnames) {
            System.out.println("File N°" + minRequiredVeh.size());
            minRequiredVeh.add(ReadOneFile(file, qtyHandledPerVehicle));
        }
    }

    private static Integer ReadOneFile(String fileName, Integer qtyHandled) throws FileNotFoundException {
        int totalQuantity = 0;

        File doc = new File(PATH + fileName);
        Scanner obj = new Scanner(doc);
        obj.nextLine();
        while (obj.hasNextLine()) {
            String s = obj.nextLine();
            String[] words = s.split(";");
            totalQuantity += Integer.parseInt(words[3]);
        }
        float MinRequiredVeh = (float) totalQuantity / qtyHandled;
        MinRequiredVeh = (float) Math.ceil(MinRequiredVeh);
        System.out.println("Total Quantity = " + totalQuantity);
        System.out.println("Min. Required Vehicules = " + MinRequiredVeh);

        return (int) MinRequiredVeh;
    }
}
