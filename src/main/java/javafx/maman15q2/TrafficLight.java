/**
 * Maman15 Q2
 * Represents a traffic light that controls the flow of vehicles and pedestrians.
 * @author Elad Reuveny
 */
package javafx.maman15q2;

import javafx.application.Platform;

public class TrafficLight extends Thread {
    private final int vehicleGreenTime;
    private final int vehicleRedTime;
    private boolean isVehicleRed;
    private final TrafficLightController controller;

    /**
     * Constructs a TrafficLight object with the specified green and red times.
     *
     * @param vehicleGreenTime the duration of the green light for vehicles
     * @param vehicleRedTime   the duration of the red light for vehicles
     * @param index            the index of the traffic light
     * @param controller       the controller for updating the GUI
     */
    public TrafficLight(int vehicleGreenTime, int vehicleRedTime, int index, TrafficLightController controller) {
        if (index == 0 || index == 1) {
            this.isVehicleRed = true;
        } else {
            this.isVehicleRed = false;
        }
        this.vehicleGreenTime = vehicleGreenTime;
        this.vehicleRedTime = vehicleRedTime;
        this.controller = controller;
    }

    /**
     * Checks if the traffic light is currently showing the red light for vehicles.
     *
     * @return true if the traffic light is red for vehicles, false otherwise
     */
    public boolean isVehicleRed() {
        return isVehicleRed;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (isVehicleRed)
                    Thread.sleep(vehicleRedTime);
                else
                    Thread.sleep(vehicleGreenTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isVehicleRed = !isVehicleRed;
            Platform.runLater(() -> {
                // Call your draw function here to update the GUI
                controller.drawTrafficLights();
            });
        }
    }
}
