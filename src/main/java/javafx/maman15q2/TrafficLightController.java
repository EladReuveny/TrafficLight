/**
 * Maman15 Q2
 * Controller class for the TrafficLight application.
 * @author Elad Reuveny
 */

package javafx.maman15q2;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TrafficLightController {
    private static final int TRAFFIC_LIGHT_SIZE = 60;
    private static final double TRAFFIC_LIGHT_MARGIN = 10.0;

    @FXML
    private Canvas canvas;

    private GraphicsContext gc;
    private final TrafficLight[] trafficLights = new TrafficLight[4];

    /**
     * Initializes the controller and starts the traffic lights.
     */
    public void initialize() {
        gc = canvas.getGraphicsContext2D();

        for (int i = 0; i < trafficLights.length; i++) {
            trafficLights[i] = new TrafficLight(2000, 3000, i, this);
        }

        for (TrafficLight trafficLight : trafficLights) {
            trafficLight.start();
        }
    }

    /**
     * Draws the traffic lights on the canvas.
     */
    public void drawTrafficLights() {
        double size = TRAFFIC_LIGHT_SIZE;
        double margin = TRAFFIC_LIGHT_MARGIN;

        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();

        double[] positionsX = {
                (canvasWidth - size) / 2,  // Center horizontally
                (canvasWidth - size) / 2,  // Center horizontally
                margin,                    // Left margin
                canvasWidth - margin - size // Right margin
        };
        double[] positionsY = {
                margin,                     // Top margin
                canvasHeight - margin - size - 115, // Bottom margin
                (canvasHeight - size) / 2,  // Center vertically
                (canvasHeight - size) / 2   // Center vertically
        };

        for (int i = 0; i < positionsX.length; i++) {
            double x = positionsX[i];
            double y = positionsY[i];
            drawTrafficLight(x, y, size, i);
        }
    }

    /**
     * Draws a single traffic light on the canvas.
     *
     * @param x    The x-coordinate of the top-left corner of the traffic light.
     * @param y    The y-coordinate of the top-left corner of the traffic light.
     * @param size The size (width and height) of the traffic light.
     * @param i    The index of the traffic light.
     */
    private void drawTrafficLight(double x, double y, double size, int i) {
        // Current traffic light is indicating red light to vehicles.
        if (trafficLights[i].isVehicleRed()) {
            // Draw red circle
            gc.setFill(Color.RED);
            gc.fillOval(x, y, size, size);

            // Draw gray circle right beneath the red circle
            double grayY = y + size;
            gc.setFill(Color.LIGHTGRAY);
            gc.fillOval(x, grayY, size, size);

            // Draw gray rectangle right beneath the gray circle
            double greenY = grayY + size;
            double rectWidth = size * 0.4; // Adjust the width to make it more vertical
            double rectHeight = size * 0.6; // Adjust the height to make it more vertical
            gc.setFill(Color.LIGHTGRAY);
            gc.fillRect(x + (size - rectWidth) / 2, greenY, rectWidth, rectHeight);

            // Draw green rectangle right beneath the gray rectangle
            double yellowX = x + (size - rectWidth) / 2; // Use the same x-coordinate as the green rectangle
            double yellowY = greenY + rectHeight; // Position it below the green rectangle
            gc.setFill(Color.LIGHTGREEN);
            gc.fillRect(yellowX, yellowY, rectWidth, rectHeight);
        }

        // Current traffic light is indicating green light to vehicles
        else {
            // Draw gray circle
            gc.setFill(Color.LIGHTGRAY);
            gc.fillOval(x, y, size, size);

            // Draw green circle right beneath the gray circle
            double grayY = y + size;
            gc.setFill(Color.LIGHTGREEN);
            gc.fillOval(x, grayY, size, size);

            // Draw red rectangle right beneath the green circle
            double greenY = grayY + size;
            double rectWidth = size * 0.4; // Adjust the width to make it more vertical
            double rectHeight = size * 0.6; // Adjust the height to make it more vertical
            gc.setFill(Color.RED);
            gc.fillRect(x + (size - rectWidth) / 2, greenY, rectWidth, rectHeight);

            // Draw gray rectangle beneath the red rectangle
            double yellowX = x + (size - rectWidth) / 2; // Use the same x-coordinate as the green rectangle
            double yellowY = greenY + rectHeight; // Position it below the green rectangle
            gc.setFill(Color.LIGHTGRAY);
            gc.fillRect(yellowX, yellowY, rectWidth, rectHeight);
        }
    }
}
