package vehicle.optionals.seats;

public class Seats {
    private boolean leatherSeats;
    private boolean heatedSeats;

    public Seats(SeatsBuilder builder) {
        this.leatherSeats = builder.isLeatherSeats();
        this.heatedSeats = builder.isHeatedSeats();
    }

    public static SeatsBuilder builder() {
        return new SeatsBuilder();
    }

    @Override
    public String toString() {
        return "Seats{" + "leatherSeats=" + leatherSeats + ", heatedSeats=" + heatedSeats + '}';
    }
}
