package fun.reallyisnt.oms.health;

import agones.dev.sdk.Sdk;

public class HealthStreamObserver implements io.grpc.stub.StreamObserver<agones.dev.sdk.Sdk.Empty> {

    @Override
    public void onNext(Sdk.Empty value) {
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onCompleted() {

    }
}
