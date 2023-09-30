package net.brotalk.models.status;

import agones.dev.sdk.Sdk;

import java.util.List;
import java.util.stream.Collectors;

public class Status {

    private final String state;
    private final String address;
    private final List<Port> ports;

    public Status(Sdk.GameServer gameServer) {
        Sdk.GameServer.Status status = gameServer.getStatus();

        this.state = status.getState();
        this.address = status.getAddress();
        this.ports = status.getPortsList().stream().map(port -> new Port(port.getName(), port.getPort())).collect(Collectors.toList());
    }

    public String getState() {
        return state;
    }

    public String getAddress() {
        return address;
    }

    public List<Port> getPorts() {
        return ports;
    }

    @Override
    public String toString() {
        return "Status{" +
                "state='" + state + '\'' +
                ", address='" + address + '\'' +
                ", ports=" + ports +
                '}';
    }
}
