package vehicle.optionals.doors;

public class Doors {
    private boolean poweredTailGate;

    protected Doors(DoorsBuilder builder) {
        this.poweredTailGate = builder.isPoweredTailGate();
    }

    public static DoorsBuilder builder() {
        return new DoorsBuilder();
    }

    @Override
    public String toString() {
        return "Doors{" + "poweredTailGate= " + poweredTailGate + '}';
    }
}
