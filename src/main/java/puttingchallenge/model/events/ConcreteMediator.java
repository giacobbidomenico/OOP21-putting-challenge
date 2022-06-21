package puttingchallenge.model.events;

import java.util.HashSet;
import java.util.Set;

import puttingchallenge.model.GameEvent;

/**
 * Implementation of {@link Mediator}.
 */
public class ConcreteMediator implements Mediator {

    private final Set<Colleague> colleagues;

    /**
     * Build a new {@link ConcreteMediator}.
     */
    public ConcreteMediator() {
        this.colleagues = new HashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addColleague(final Colleague newColleague) {
        this.colleagues.add(newColleague);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeColleague(final Colleague toRemove) {
        this.colleagues.remove(toRemove);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyColleagues(final GameEvent<?, ?> event, final Colleague sender) {
        this.colleagues.stream().filter(x -> !x.equals(sender)).forEach(x -> x.notifyEvent(event));
    }

}
