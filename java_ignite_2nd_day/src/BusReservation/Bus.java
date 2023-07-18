package BusReservation;

import java.util.ArrayList;

public class Bus {
    private Destination destination;
    private ArrayList<Passenger> passengers;
    private int size;

    public Bus(Destination destination, int size) {
        this.destination = destination;
        passengers = new ArrayList<>();
        this.size = size;
    }

    public void insertPassenger(Passenger passenger) {
        if (passenger.getDestination() != destination) {
            System.out.println("This bus is not going to this destination.");
            return;
        }
        if (passengers.size() > size) {
            passengers.add(passenger);
            System.out.println("Passenger is added.");
        } else {
            System.out.println("Bus is full.");
        }
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }
}
