package net.brotalk;

import agones.dev.sdk.SDKGrpc;
import agones.dev.sdk.Sdk;
import net.brotalk.alpha.Alpha;
import net.brotalk.health.HealthStreamObserver;
import net.brotalk.models.GameServer;
import net.brotalk.models.StreamObserverWrapper;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class AgonesSDK {

    private final SDKGrpc.SDKBlockingStub stub;
    private final SDKGrpc.SDKStub asyncStub;
    private final ManagedChannel channel;

    private final Alpha alpha;

    /**
     * This creates a connection with the environment
     * variables provided by Kubernetes set by Agones.
     */
    public AgonesSDK() {
        this(Integer.parseInt(System.getenv().getOrDefault("AGONES_SDK_GRPC_PORT", "9357")));
    }

    public AgonesSDK(int port) {
        this("localhost", port);
    }

    public AgonesSDK(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        this.asyncStub = SDKGrpc.newStub(channel);
        this.stub = SDKGrpc.newBlockingStub(channel);
        this.alpha = new Alpha(channel);
    }

    /**
     * This method tells Agones that the Game Server is ready
     * to take player connections.
     *
     * @see <a href="https://agones.dev/site/docs/guides/client-sdks/#ready">https://agones.dev/site/docs/guides/client-sdks/</a>
     */
    public void ready() {
        stub.ready(Sdk.Empty.getDefaultInstance());
    }

    /**
     * This method sends a single ping to designate that the
     * Game Server is alive and healthy.
     *
     * @see <a href="https://agones.dev/site/docs/guides/client-sdks/#health">https://agones.dev/site/docs/guides/client-sdks/</a>
     */
    public void health() {
        StreamObserver<Sdk.Empty> healthStream = asyncStub.health(new HealthStreamObserver());
        healthStream.onNext(Sdk.Empty.newBuilder().build());
        healthStream.onCompleted();
    }

    /**
     * This method will move the GameServer into the Reserved state
     * for the specified number of seconds (0 is forever), and then
     * it will be moved back to Ready state.
     *
     * @param seconds the amount of seconds to reserve for
     * @see <a href="https://agones.dev/site/docs/guides/client-sdks/#reserveseconds">https://agones.dev/site/docs/guides/client-sdks/</a>
     */
    public void reserve(long seconds) {
        stub.reserve(Sdk.Duration.newBuilder().setSeconds(seconds).build());
    }

    /**
     * This method will move the GameServer into the Allocated status.
     *
     * @see <a href="https://agones.dev/site/docs/guides/client-sdks/#allocate">https://agones.dev/site/docs/guides/client-sdks/</a>
     */
    public void allocate() {
        stub.allocate(Sdk.Empty.getDefaultInstance());
    }

    /**
     * This tells Agones to shut down the currently running game server.
     * The GameServer state will be set Shutdown and the backing Pod will
     * be Terminated.
     *
     * @see <a href="https://agones.dev/site/docs/guides/client-sdks/#shutdown">https://agones.dev/site/docs/guides/client-sdks/</a>
     */
    public void shutdown() {
        stub.shutdown(Sdk.Empty.getDefaultInstance());
    }

    /**
     * This returns most of the backing GameServer configuration and Status.
     *
     * @return backing GameServer stored in Kubernetes
     * @see <a href="https://agones.dev/site/docs/guides/client-sdks/#gameserver">https://agones.dev/site/docs/guides/client-sdks/</a>
     */
    public GameServer gameServer() {
        return new GameServer(stub.getGameServer(Sdk.Empty.getDefaultInstance()));
    }

    /**
     * This executes the passed in callback with the current GameServer
     * details whenever the underlying GameServer configuration is updated.
     *
     * @param streamObserver the streamobserver to call
     * @see <a href="https://agones.dev/site/docs/guides/client-sdks/#watchgameserverfunctiongameserver">https://agones.dev/site/docs/guides/client-sdks/</a>
     */
    public void watchGameServer(StreamObserver<GameServer> streamObserver) {
        asyncStub.watchGameServer(Sdk.Empty.getDefaultInstance(), new StreamObserverWrapper(streamObserver));
    }

    /**
     * This will set a Label value on the backing GameServer record
     * that is stored in Kubernetes.
     *
     * @param label the label on the backing record
     * @param value the value to set on the record
     * @see <a href="https://agones.dev/site/docs/guides/client-sdks/#setlabelkey-value">https://agones.dev/site/docs/guides/client-sdks/</a>
     */
    public void setLabel(String label, String value) {
        stub.setLabel(Sdk.KeyValue.newBuilder().setKey(label).setValue(value).build());
    }

    /**
     * This will set an Annotation value on the backing GameServer
     * record that is stored in Kubernetes.
     *
     * @param annotation the label on the backing record
     * @param value      the value to set on the record
     * @see <a href="https://agones.dev/site/docs/guides/client-sdks/#setannotationkey-value">https://agones.dev/site/docs/guides/client-sdks/</a>
     */
    public void setAnnotation(String annotation, String value) {
        stub.setAnnotation(Sdk.KeyValue.newBuilder().setKey(annotation).setValue(value).build());
    }

    /**
     * This shuts down the connection to gRPC
     *
     * @param shutdownNow whether to terminate this
     *                    connection instantly
     */
    public void shutdownConnection(boolean shutdownNow) {
        if(shutdownNow) {
            channel.shutdown();
        } else {
            channel.shutdownNow();
        }
    }

    public Alpha alpha() {
        return alpha;
    }
}
