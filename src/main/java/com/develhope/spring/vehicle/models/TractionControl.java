package com.develhope.spring.vehicle.models;

import com.develhope.spring.model.vehicle.optionals.Optionals;

public class TractionControl implements Optionals {
    private boolean enabled;

    public TractionControl(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "TractionControl{" +
                "enabled=" + enabled +
                '}';
    }
}
