# AgonesSDK

An SDK powered by gRPC and Protobuf to communicate with the Agones sidecar.

## Example
This example covers a bit of the instantiation of the SDK and all the calls it currently covers

```java
package net.brotalk;

import net.brotalk.AgonesSDK;
import net.brotalk.alpha.Alpha;
import models.net.brotalk.GameServer;
import io.grpc.stub.StreamObserver;

import java.util.List;

public class AgonesExample {

    public static void main(String[] args) {
        // You can instantiate with the default connections details("localhost" and whichever port is set by Agones in the environment variable usually 9357)
        AgonesSDK agonesSDK = new AgonesSDK();

        // Or you can use your own connection details
        agonesSDK = new AgonesSDK("localhost", 9357);

        // Base calls
        agonesSDK.ready();
        agonesSDK.health();
        agonesSDK.reserve(100);
        agonesSDK.allocate();
        agonesSDK.shutdown();

        // Configuration calls
        GameServer gameServer = agonesSDK.gameServer();

        // You can watch the Game Server with a StreamObserver<GameServer> directly in the method
        // or you can also implement it in your own object
        agonesSDK.watchGameServer(new StreamObserver<>() {
            @Override
            public void onNext(GameServer gameServer) {
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onCompleted() {
            }
        });

        agonesSDK.setLabel("test.com/testLabel", "test");
        agonesSDK.setAnnotation("test.com/testAnnotation", "test");

        // Alpha features (currently just PlayerTracking)
        Alpha alpha = agonesSDK.alpha();

        alpha.setPlayerCapacity(25);

        boolean didRegister = alpha.playerConnect("1234");
        boolean wasRegistered = alpha.playerDisconnect("1234");
        boolean isPlayerConnected = alpha.isPlayerConnected("1234");

        long playerCapacity = alpha.getPlayerCapacity();
        long playerCount = alpha.getPlayerCount();

        List<String> players = alpha.getPlayerList();
    }
}

```