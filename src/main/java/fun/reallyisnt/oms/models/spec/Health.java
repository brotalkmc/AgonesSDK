package fun.reallyisnt.oms.models.spec;

import agones.dev.sdk.Sdk;

public class Health {

    private final boolean disabled;
    private final int periodSeconds;
    private final int failureThreshold;
    private final int initialDelaySeconds;

    public Health(Sdk.GameServer gameServer) {
        Sdk.GameServer.Spec.Health health = gameServer.getSpec().getHealth();

        this.disabled = health.getDisabled();
        this.periodSeconds = health.getPeriodSeconds();
        this.failureThreshold = health.getFailureThreshold();
        this.initialDelaySeconds = health.getInitialDelaySeconds();
    }

    public boolean isDisabled() {
        return disabled;
    }

    public int getPeriodSeconds() {
        return periodSeconds;
    }

    public int getFailureThreshold() {
        return failureThreshold;
    }

    public int getInitialDelaySeconds() {
        return initialDelaySeconds;
    }

    @Override
    public String toString() {
        return "Health{" +
                "disabled=" + disabled +
                ", periodSeconds=" + periodSeconds +
                ", failureThreshold=" + failureThreshold +
                ", initialDelaySeconds=" + initialDelaySeconds +
                '}';
    }
}
