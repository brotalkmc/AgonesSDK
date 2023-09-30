package net.brotalk.models.status;

public class Port {

    private final String name;
    private final int port;

    public Port(String name, int port) {
        this.name = name;
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return "Port{" +
                "name='" + name + '\'' +
                ", port=" + port +
                '}';
    }
}
