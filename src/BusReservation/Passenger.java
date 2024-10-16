package BusReservation;

public class Passenger {
    private String name;
    private MainClass.Destination destination;

    public Passenger(String name, MainClass.Destination destination) {
        this.name = name;
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MainClass.Destination getDestination() {
        return destination;
    }

    public void setDestination(MainClass.Destination destination) {
        this.destination = destination;
    }
}
