package vehicle.optionals.seats;

public class SeatsBuilder {
    private boolean leatherSeats;
    private boolean heatedSeats;

    public boolean isHeatedSeats() {
        return heatedSeats;
    }

    public boolean isLeatherSeats() {
        return leatherSeats;
    }

    public SeatsBuilder setHeatedSeats(boolean heatedSeats) {
        this.heatedSeats = heatedSeats;
        return this;
    }

    public SeatsBuilder setLeatherSeats(boolean leatherSeats) {
        this.leatherSeats = leatherSeats;
        return this;
    }

    public SeatsBuilder() {
    }

    public Seats build() {
        return new Seats(this);
    }
}
