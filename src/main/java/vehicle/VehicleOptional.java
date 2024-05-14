package vehicle;

public class VehicleOptional {
    private boolean infotainment;
    private boolean adaptiveCruiseControl;
    private boolean fullLEDLights;
    private boolean parkingSensors;
    private boolean heatedSeats;
    private boolean safetyDevices;
    private boolean headUpDisplay;
    private boolean keylessSystem;

    public VehicleOptional() {
        this.infotainment = false;
        this.adaptiveCruiseControl = false;
        this.fullLEDLights = false;
        this.parkingSensors = false;
        this.heatedSeats = false;
        this.safetyDevices = false;
        this.headUpDisplay = false;
        this.keylessSystem = false;
    }

    public void setInfotainment(boolean infotainment) {
        this.infotainment = infotainment;
    }

    public void setAdaptiveCruiseControl(boolean adaptiveCruiseControl) {
        this.adaptiveCruiseControl = adaptiveCruiseControl;
    }

    public void setFullLEDLights(boolean fullLEDLights) {
        this.fullLEDLights = fullLEDLights;
    }

    public void setParkingSensors(boolean parkingSensors) {
        this.parkingSensors = parkingSensors;
    }

    public void setHeatedSeats(boolean heatedSeats) {
        this.heatedSeats = heatedSeats;
    }

    public void setSafetyDevices(boolean safetyDevices) {
        this.safetyDevices = safetyDevices;
    }

    public void setHeadUpDisplay(boolean headUpDisplay) {
        this.headUpDisplay = headUpDisplay;
    }

    public void setKeylessSystem(boolean keylessSystem) {
        this.keylessSystem = keylessSystem;
    }

    public boolean getInfotainment() {
        return infotainment;
    }

    public boolean getAdaptiveCruiseControl() {
        return adaptiveCruiseControl;
    }

    public boolean getFullLEDLights() {
        return fullLEDLights;
    }

    public boolean getParkingSensors() {
        return parkingSensors;
    }

    public boolean getHeatedSeats() {
        return heatedSeats;
    }

    public boolean getSafetyDevices() {
        return safetyDevices;
    }

    public boolean getHeadUpDisplay() {
        return headUpDisplay;
    }

    public boolean getKeylessSystem() {
        return keylessSystem;
    }
}
