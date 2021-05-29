package fun.reallyisnt.oms.models;

import agones.dev.sdk.Sdk;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * This iterator wraps around the one returned by the
 * watchServer method to use our own model.
 */
public class IteratorWrapper implements Iterator<GameServer> {

    private final Iterator<Sdk.GameServer> iterator;

    public IteratorWrapper(Iterator<Sdk.GameServer> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public GameServer next() {
        return new GameServer(iterator.next());
    }

    @Override
    public void remove() {
        iterator.remove();
    }

    @Override
    public void forEachRemaining(Consumer<? super GameServer> action) {
        iterator.forEachRemaining(gameServer -> action.accept(new GameServer(gameServer)));
    }
}
