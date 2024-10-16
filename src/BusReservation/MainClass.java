package BusReservation;

import Lab.Circle;
import Lab.Rectangle;
import Lab.ThePen;

import java.util.ArrayList;

public class MainClass {

    public enum Destination {
        ISTANBUL, ADANA, ANKARA
    }
    public static void main(String[] args) {

        Bus bus1 =new Bus(Destination.ADANA, new ArrayList());
        Passenger p1 = new Passenger("Ali",Destination.ADANA);
        bus1.insertPassenger(new Passenger("Ali",Destination.ADANA));
        bus1.insertPassenger(new Passenger("Veli",Destination.ANKARA));
        bus1.insertPassenger(new Passenger("Mehmet",Destination.ADANA));



    }
}
