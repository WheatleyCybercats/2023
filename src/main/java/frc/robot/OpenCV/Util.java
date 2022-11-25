package frc.robot.OpenCV;

import org.opencv.core.Point;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Util {
    private Util() {
    }
    public static Mat processImage(final Mat mat) {
        final Mat processed = new Mat(mat.height(), mat.width(), mat.type());
        // Gaussian filter
        Imgproc.GaussianBlur(mat, processed, new Size(7, 7), 1);

        // Color -> black and white
        Imgproc.cvtColor(processed, processed, Imgproc.COLOR_RGB2GRAY);

        // Use Canny algorithm to find edges
        Imgproc.Canny(processed, processed, 200, 25);
        Imgproc.dilate(processed, processed, new Mat(), new Point(-1, -1), 1);
        return processed;
    }
    public static void findContour(final Mat processedImage, final Mat originalImage) {
        // Find contours of an image
        final List<MatOfPoint> allContours = new ArrayList<>();
        Imgproc.findContours(processedImage, allContours, new Mat(processedImage.size(), processedImage.type()), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE
        );
        // Filter out noise and display contour area value
        final List<MatOfPoint> filteredContours = allContours.stream().filter(contour -> {
                    final double value = Imgproc.contourArea(contour);
                    final Rect rect = Imgproc.boundingRect(contour);
                    var approx = new MatOfPoint2f();
                    Imgproc.approxPolyDP(new MatOfPoint2f(contour.toArray()), approx, 0.01 * Imgproc.arcLength(new MatOfPoint2f(contour.toArray()), true), true);
                    var corners = approx.toArray();


                    final boolean isNotNoise = value > 1000;

                    if (isNotNoise) {
                        Imgproc.putText (originalImage, "Area: " + (int) value, new Point(rect.x + rect.width, rect.y + rect.height), 2, 0.5, new Scalar(124, 252, 0), 1);

                        MatOfPoint2f dst = new MatOfPoint2f();
                        contour.convertTo(dst, CvType.CV_32F);
                        Imgproc.approxPolyDP(dst, dst, 0.02 * Imgproc.arcLength(dst, true), true);
                        Imgproc.putText (originalImage, "Points: " + dst.toArray().length, new Point(rect.x + rect.width, rect.y + rect.height + 15), 2, 0.5, new Scalar(124, 252, 0), 1);


                        /*if(dst.toArray().length != 4 || dst.toArray().length != 3 || dst.toArray().length != 5 || dst.toArray().length != 6 || dst.toArray().length != 8 || dst.toArray().length != 10 ){
                            Imgproc.putText (originalImage, "Circle", new Point(rect.x + rect.width, rect.y + rect.height + 30), 2, 0.5, new Scalar(124, 252, 0), 1);
                        }
                        else {
                            Imgproc.putText (originalImage, "Circle", new Point(rect.x + rect.width, rect.y + rect.height + 30), 2, 0.5, new Scalar(124, 252, 0), 1);
                        }

                         */
                    }

                    return isNotNoise;
                }).collect(Collectors.toList());

        // Mark contours
        Imgproc.drawContours(originalImage, filteredContours,
                -1, // Negative value indicates that we want to draw all of contours
                new Scalar(124, 252, 0), // Green color
                1
        );
    }
    public static void createJFrame(final JPanel... panels) {
        final JFrame window = new JFrame("Shape Detection");
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
        final BufferedImage image = Util.MatToBImage(mat);

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