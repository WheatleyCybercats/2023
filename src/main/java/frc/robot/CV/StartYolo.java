package frc.robot.CV;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class StartYolo {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("python", "Yolov7/detect.py", "--source", "1", "--conf-thres", "0.5", "--nosave", "--weights", "Yolov7/yolov7.pt");
            Process process = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader br2 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            HashMap<String, Object> itemsDetected = new HashMap<>();
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                if (!line.contains("|")) {
                    continue;
                }
                    String[] objects = line.split("\\|");
                    for (String s : objects) {
                        String[] attributes = s.split(" ");//Split the string into an array of attributes(name, center, BBOXP1, BBOXP2, confidence)
                        String PLCCoord = attributes[1].substring(1, attributes[1].length() - 1);//Get the center coordinates
                        String[] center = PLCCoord.split(",");//Split the center coordinates into an array of x and y
                        String BBOXP1Coord = attributes[2].substring(1, attributes[2].length() - 1);//Get the BBOXP1 coordinates
                        String[] BBOXP1 = BBOXP1Coord.split(",");//Split the BBOXP1 coordinates into an array of x and y
                        String BBOXP2Coord = attributes[3].substring(1, attributes[3].length() - 1);//Get the BBOXP2 coordinates
                        String[] BBOXP2 = BBOXP2Coord.split(",");//Split the BBOXP2 coordinates into an array of x and y
                        //double Confidence = Double.parseDouble(attributes[4]);//Confidence
                        itemsDetected.put(attributes[0], new Object(attributes[0], new int[]{Integer.parseInt(center[0]), Integer.parseInt(center[1])}, new int[]{Integer.parseInt(BBOXP1[0]), Integer.parseInt(BBOXP1[1])}, new int[]{Integer.parseInt(BBOXP2[0]), Integer.parseInt(BBOXP2[1])}, attributes[4]));
                    }
                    for (String s : itemsDetected.keySet()) {
                        System.out.println(itemsDetected.get(s).toString());
                    }
                while ((line = br2.readLine()) != null) {
                    System.out.println("Error " + line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
