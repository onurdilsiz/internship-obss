package BusReservation;

import java.util.ArrayList;

public class Bus {
    private MainClass.Destination destination;

    public Bus(MainClass.Destination destination, ArrayList passengers) {
        this.destination = destination;
        this.passengers = passengers;
        this.capacity = 1;
    }

    private ArrayList passengers;
    private int capacity;

    public MainClass.Destination getDestination() {
        return destination;
    }

    public void insertPassenger(Passenger p) {
        if(p.getDestination()!= destination){
            System.out.println("Destinations do not match");
        } else if (capacity<=0) {
            System.out.println("Bus is full");
        }else {
            passengers.add(p);
            capacity--;
            System.out.println("Passenger inserted");

        }

    }

    public void setDestination(MainClass.Destination destination) {
        this.destination = destination;
    }

    public ArrayList getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList passengers) {
        this.passengers = passengers;
    }
}
