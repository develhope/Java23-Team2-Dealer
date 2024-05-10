package vehicle.optionals.doors;

public class DoorsBuilder {
    private boolean poweredTailGate;

    public boolean isPoweredTailGate() {
        return poweredTailGate;
    }

    public DoorsBuilder setPoweredTailGate(boolean poweredTailGate) {
        this.poweredTailGate = poweredTailGate;
        return this;
    }

    public DoorsBuilder() {
    }

    @Override
    public String toString() {
        return "DoorsBuilder{" + "poweredTailGate=" + poweredTailGate + '}';
    }
}
