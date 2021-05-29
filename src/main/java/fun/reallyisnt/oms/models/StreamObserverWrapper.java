package fun.reallyisnt.oms.models;

import agones.dev.sdk.Sdk;
import io.grpc.stub.StreamObserver;

public class StreamObserverWrapper implements StreamObserver<Sdk.GameServer> {

    private final StreamObserver<GameServer> streamObserver;

    public StreamObserverWrapper(StreamObserver<GameServer> streamObserver) {
        this.streamObserver = streamObserver;
    }

    @Override
    public void onNext(Sdk.GameServer value) {
        streamObserver.onNext(new GameServer(value));
    }

    @Override
    public void onError(Throwable t) {
        streamObserver.onError(t);
    }

    @Override
    public void onCompleted() {
        streamObserver.onCompleted();
    }
}
