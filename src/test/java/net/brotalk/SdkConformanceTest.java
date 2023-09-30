package net.brotalk;

import net.brotalk.models.GameServer;
import io.grpc.stub.StreamObserver;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SdkConformanceTest {

    private final AgonesSDK agonesSDK = new AgonesSDK("localhost", 9357);

    @Test
    public void ready() {
        agonesSDK.ready();
    }

    @Test
    public void configTests() {
        GameServer gameServer = agonesSDK.gameServer();

        agonesSDK.setAnnotation("reallyisnt.fun/hi", gameServer.getMeta().getUid());
        agonesSDK.setLabel("reallyisnt.fun/hi", String.valueOf(gameServer.getMeta().getCreationTimestamp()));
    }

    @Test
    public void allocate() {
        agonesSDK.allocate();
    }

    @Test
    public void getGameServer() {
        System.out.println(agonesSDK.gameServer().toString());
    }

    @Test
    public void health() {
        agonesSDK.health();
    }

    @Test
    public void shutdown() {
        agonesSDK.shutdown();
    }

    @Test
    public void watch() {
        agonesSDK.watchGameServer(new StreamObserver<>() {
            @Override
            public void onNext(GameServer value) {
                System.out.println("Received GameServer while watching");
                System.out.println(value.toString());
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
            }
        });
    }

    @Test
    public void reserve() {
        agonesSDK.reserve(10);
    }

    @Test
    public void playerTests() {
        agonesSDK.alpha().setPlayerCapacity(25);
        Assert.assertEquals(agonesSDK.alpha().getPlayerCapacity(), 25);

        Assert.assertTrue(agonesSDK.alpha().playerConnect("1234"));
        Assert.assertTrue(agonesSDK.alpha().isPlayerConnected("1234"));
        Assert.assertEquals(agonesSDK.alpha().getPlayerCount(), 1);

        List<String> players = agonesSDK.alpha().getPlayerList();
        Assert.assertTrue(players.contains("1234"));
        Assert.assertEquals(1, players.size());

        Assert.assertTrue(agonesSDK.alpha().playerDisconnect("1234"));
    }
}
