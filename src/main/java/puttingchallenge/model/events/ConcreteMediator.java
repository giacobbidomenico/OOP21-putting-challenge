package puttingchallenge.model.events;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Implementation of {@link Mediator}.
 */
public class ConcreteMediator implements Mediator {

    private final List<Colleague> colleagues;

    /**
     * Build a new {@link ConcreteMediator}.
     */
    public ConcreteMediator() {
        this.colleagues = new CopyOnWriteArrayList<>();
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
    public void notifyColleagues(final GameEvent event, final Colleague sender) {
        this.colleagues.stream().filter(x -> !x.equals(sender)).forEach(x -> x.notifyEvent(event));
    }

}
