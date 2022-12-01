package frc.robot.OpenCV.ObjectRecognition;

import nu.pattern.OpenCV;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.dnn.Dnn;
import org.opencv.dnn.Net;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import org.tensorflow.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static org.opencv.core.CvType.CV_32F;
import static org.opencv.highgui.HighGui.toBufferedImage;

public class Main {
    static ArrayList<String> objectNames = new ArrayList<String>();
    static String ClassPath = "C:\\Users\\Peter Ouyang\\IdeaProjects\\FRC\\src\\main\\java\\frc\\robot\\OpenCV\\ObjectRecognition\\DetectionClasses";

    static HashMap<String, Color> objectNameAndColor = new HashMap<String, Color>();
    public static void main(String[] args) {
        // Load OpenCV
        OpenCV.loadLocally();
        init();
        System.out.println(objectNames.toString());

        JPanel cameraFeed = new JPanel();
        JPanel processedFeed = new JPanel();
        createJFrame(cameraFeed, processedFeed);

        Random r = new Random();
        for (String objectName : objectNames) {
            objectNameAndColor.put(objectName, new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
        }

        Net model = Dnn.readNet("yolov3-tiny.weights", "yolov3-tiny.cfg");

        model.setPreferableBackend(Dnn.DNN_BACKEND_CUDA);
        model.setPreferableTarget(Dnn.DNN_TARGET_CUDA);

        final VideoCapture camera = new VideoCapture(0);
        double min_confidence = 0.6;

        while(camera.isOpened()){
            Mat frame = new Mat();
            camera.read(frame);
            drawImage(frame, cameraFeed);
            int width = frame.width();
            int height = frame.height();
            Mat blob = Dnn.blobFromImage(frame, 1/255.0, new Size(width, height), new Scalar(0, 0, 0), true, false);
            model.setInput(blob);
            Mat output = model.forward();
            //Mat result = new Mat(output.size(), CV_32F);
                double confidence = output.get(0, 2)[0];
                if (confidence > min_confidence) {
                    int classId = (int) output.get(0, 1)[0];
                    String className = objectNames.get(classId);
                    int x = (int) (output.get(0, 3)[0] * width);
                    int y = (int) (output.get(0, 4)[0] * height);
                    int w = (int) (output.get(0, 5)[0] * width);
                    int h = (int) (output.get(0, 6)[0] * height);
                    Imgproc.rectangle(output, new org.opencv.core.Point(x, y), new org.opencv.core.Point(x + w, y + h), new Scalar(0, 255, 0), 2);
                    Imgproc.putText(output, className, new org.opencv.core.Point(x, y), 1, 1, new Scalar(0, 255, 0), 2);
                }


            //processedFeed.getGraphics().drawImage(toBufferedImage(output), 0, 0, processedFeed.getWidth(), processedFeed.getHeight(), null);
        }
    }

    public static void init() {
        // Load OpenCV
        OpenCV.loadLocally();
        try{
            File file = new File(ClassPath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                objectNames.add(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void createJFrame(final JPanel... panels) {
        final JFrame window = new JFrame("Object Recognition");
        window.setSize(new Dimension(panels.length * 640, 480));
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setLayout(new GridLayout(1, panels.length));

        for (final JPanel panel : panels) {
            window.add(panel);
        }

        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void drawImage(final Mat mat, final JPanel panel) {
        // Get buffered image from mat frame
        final BufferedImage image = MatToBImage(mat);

        // Draw image to panel
        final Graphics graphics = panel.getGraphics();
        graphics.drawImage(image, 0, 0, panel);
    }

    private static BufferedImage MatToBImage(final Mat mat) {
        // Create buffered image
        final BufferedImage bufferedImage = new BufferedImage(mat.width(), mat.height(), mat.channels() == 1 ? BufferedImage.TYPE_BYTE_GRAY : BufferedImage.TYPE_3BYTE_BGR);

        // Write data to image
        final WritableRaster raster = bufferedImage.getRaster();
        final DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        mat.get(0, 0, dataBuffer.getData());

        return bufferedImage;
    }

}
