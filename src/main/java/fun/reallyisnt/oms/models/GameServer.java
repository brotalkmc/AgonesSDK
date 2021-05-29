package fun.reallyisnt.oms.models;

import agones.dev.sdk.Sdk;
import fun.reallyisnt.oms.models.meta.ObjectMeta;
import fun.reallyisnt.oms.models.spec.GameServerSpec;
import fun.reallyisnt.oms.models.status.Status;

public class GameServer {

    private final GameServerSpec spec;
    private final ObjectMeta meta;
    private final Status status;

    public GameServer(Sdk.GameServer gameServer) {
        this.spec = new GameServerSpec(gameServer);
        this.meta = new ObjectMeta(gameServer);
        this.status = new Status(gameServer);
    }

    public GameServerSpec getSpec() {
        return spec;
    }

    public Status getStatus() {
        return status;
    }

    public ObjectMeta getMeta() {
        return meta;
    }

    @Override
    public String toString() {
        return "GameServer{" +
                "spec=" + spec +
                ", meta=" + meta +
                ", status=" + status +
                '}';
    }
}
