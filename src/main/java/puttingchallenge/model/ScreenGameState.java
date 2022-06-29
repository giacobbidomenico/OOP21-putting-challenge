package puttingchallenge.model;

import puttingchallenge.model.events.GameEvent;
import puttingchallenge.model.events.Mediator;
import puttingchallenge.model.events.ModelEventType;

/**
 * Class that represent all the not-in-game states of the application.
 */
public class ScreenGameState extends AbstractGameState {
    private Mediator mediator;
    /**
     * Create a {@link ScreenGameState} object.
     * @param manager
     * @param status
     */
    public ScreenGameState(final GameStateManager manager, final GameStatus status) {
        super(manager, status);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void receiveEvents() {
        // TODO Auto-generated method stub
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setMediator(final Mediator mediator) {
        this.mediator = mediator;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyEvent(final GameEvent event) {
        // TODO Auto-generated method stub
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyEvents(final ModelEventType eventType) {
        // TODO Auto-generated method stub
    }


}
