package vehicle.optionals.wheels;

public class WheelsBuilder {
    private boolean alloyWheels;
    private boolean allWheelDrives;

    public boolean isAlloyWheels() {
        return alloyWheels;
    }

    public boolean isAllWheelDrives() {
        return allWheelDrives;
    }

    public WheelsBuilder setAlloyWheels(boolean alloyWheels) {
        this.alloyWheels = alloyWheels;
        return this;
    }

    public WheelsBuilder setAllWheelDrives(boolean allWheelDrives) {
        this.allWheelDrives = allWheelDrives;
        return this;
    }

    public WheelsBuilder() {
    }

    @Override
    public String toString() {
        return "WheelsBuilder{" + "alloyWheels=" + alloyWheels + ", allWheelDrives=" + allWheelDrives + '}';
    }
}
