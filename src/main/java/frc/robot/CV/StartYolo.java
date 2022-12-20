package frc.robot.CV;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

//TODO: Check if attributes array is working

public class StartYolo {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("python", "Yolov7/detect.py", "--source", "1", "--conf-thres", "0.5", "--nosave", "--weights", "Yolov7/yolov7.pt");
            Process process = pb.start();
            BufferedReader sc = new BufferedReader(new InputStreamReader(process.getInputStream()));
            System.out.println("Initiated main BufferedReader");
            BufferedReader sc2 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            System.out.println("Initiated error BufferedReader");
            String line;
            ArrayList<Item> itemsDetected = new ArrayList<>();
            System.out.println("Initiated ArrayList");
            while ((line = sc.readLine()) != null) {
                    System.out.println(line);
                    String[] objects = line.split("\\|");
                    try {
                        for (String s : objects) {
                            String[] attributes = s.split(" ");//Split the string into an array of attributes(name, center, BBOXP1, BBOXP2, confidence)
                            String[] center = attributes[1].split(",");//Split the center into an array of x and y
                            String[] BBOXP1 = attributes[2].split(",");//Split the BBOXP1 into an array of x and y
                            String[] BBOXP2 = attributes[3].split(",");//Split the BBOXP2 into an array of x and y
                            Item item = new Item(attributes[0], new int[]{Integer.parseInt(center[0]), Integer.parseInt(center[1])}, new int[]{Integer.parseInt(BBOXP1[0]), Integer.parseInt(BBOXP1[1])}, new int[]{Integer.parseInt(BBOXP2[0]), Integer.parseInt(BBOXP2[1])}, attributes[4]);
                            itemsDetected.add(item);
                            sc.close();
                            System.out.println(item.getName());
                        }
                        for (Item object : itemsDetected) {
                            System.out.println(object.getName());
                        }
                    } catch (Exception e) {
                        System.out.println("Unable to parse line");
                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
