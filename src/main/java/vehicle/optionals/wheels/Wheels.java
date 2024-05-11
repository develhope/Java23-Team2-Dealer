package vehicle.optionals.wheels;

public class Wheels {
    private boolean alloyWheels;
    private boolean allWheelDrives;

    protected Wheels(WheelsBuilder builder) {
        this.alloyWheels = builder.isAlloyWheels();
        this.allWheelDrives = builder.isAlloyWheels();
    }

    public static WheelsBuilder builder() {
        return new WheelsBuilder();
    }

    @Override
    public String toString() {
        return "Wheels{" + "alloyWheels= " + alloyWheels + ", allWheelDrives= " + allWheelDrives + '}';
    }
}
