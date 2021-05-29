package fun.reallyisnt.oms.models.spec;

import agones.dev.sdk.Sdk;

public class GameServerSpec {

    private final Health health;

    public GameServerSpec(Sdk.GameServer gameServer) {
        this.health = new Health(gameServer);
    }

    public Health getHealth() {
        return health;
    }
}
