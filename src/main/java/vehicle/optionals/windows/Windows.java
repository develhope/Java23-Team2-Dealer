package vehicle.optionals.windows;

public class Windows {
    private boolean heatedWindows;
    private boolean tintedWindows;

    public Windows(WindowsBuilder builder) {
        this.heatedWindows = builder.isHeatedWindows();
        this.tintedWindows = builder.isTintedWindows();
    }

    public static WindowsBuilder builder() {
        return new WindowsBuilder();
    }

    @Override
    public String toString() {
        return "Windows{" +
                "heatedWindows= " + heatedWindows +
                ", tintedWindows= " + tintedWindows +
                '}';
    }
}
