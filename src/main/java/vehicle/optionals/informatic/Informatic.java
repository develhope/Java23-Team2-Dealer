package vehicle.optionals.informatic;

public class Informatic {
    private boolean rearebackCamera;
    private boolean parkingSensors;

    protected Informatic(InformaticBuilder builder) {
        this.parkingSensors = builder.isParkingSensors();
        this.rearebackCamera = builder.isRearebackCamera();
    }

    public static InformaticBuilder builder() {
        return new InformaticBuilder();
    }

    @Override
    public String toString() {
        return "Informatic{" + "rearebackCamera= " + rearebackCamera + ", parkingSensors= " + parkingSensors + '}';
    }
}
