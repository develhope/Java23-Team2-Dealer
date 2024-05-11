package vehicle.optionals.windows;

public class WindowsBuilder {
    private boolean heatedWindows;
    private boolean tintedWindows;

    public boolean isHeatedWindows() {
        return heatedWindows;
    }

    public boolean isTintedWindows() {
        return tintedWindows;
    }

    public WindowsBuilder setHeatedWindows(boolean heatedWindows) {
        this.heatedWindows = heatedWindows;
        return this;
    }

    public WindowsBuilder setTintedWindows(boolean tintedWindows) {
        this.tintedWindows = tintedWindows;
        return this;
    }

    public WindowsBuilder() {
    }

    public Windows build() {
        return new Windows(this);
    }
}
