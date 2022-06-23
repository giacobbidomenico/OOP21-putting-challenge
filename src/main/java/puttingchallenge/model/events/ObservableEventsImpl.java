package puttingchallenge.model.events;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that implements an event observable of type {@link ModelEventType}.
 * @param <A>
 */
public class ObservableEventsImpl<A> implements ObservableEvents<A> {
    private final List<ObserverEvents<A>> observers;

    /**
     * Build a new {@link ObservableEventsImpl}.
     * 
     */
    public ObservableEventsImpl() {
        this.observers = new LinkedList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObserver(final ObserverEvents<A> observer) {
        this.observers.add(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeObserver(final ObserverEvents<A> observer) {
        this.observers.remove(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<A> eventsRecieved() {
        return this.observers.stream()
                .flatMap(e -> e.getEvents().stream())
                .collect(Collectors.toList());
    }
}
