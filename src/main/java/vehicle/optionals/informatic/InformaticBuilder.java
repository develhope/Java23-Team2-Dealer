package vehicle.optionals.informatic;

public class InformaticBuilder {
    private boolean rearebackCamera;
    private boolean parkingSensors;

    public boolean isParkingSensors() {
        return parkingSensors;
    }

    public boolean isRearebackCamera() {
        return rearebackCamera;
    }

    public InformaticBuilder setParkingSensors(boolean parkingSensors) {
        this.parkingSensors = parkingSensors;
        return this;
    }

    public InformaticBuilder setRearebackCamera(boolean rearebackCamera) {
        this.rearebackCamera = rearebackCamera;
        return this;
    }

    public InformaticBuilder() {
    }

    public Informatic build() {
        return new Informatic(this);
    }
}
