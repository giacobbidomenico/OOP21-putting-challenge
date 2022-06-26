package puttingchallenge.model;

import puttingchallenge.model.events.ModelEventType;

/**
 * Class that represent all the not in-game state of the application.
 */
public class ScreenGameState extends AbstractGameState {
    /**
     * 
     * @param manager
     * @param status
     */
    public ScreenGameState(final GameStateManager manager, final GameStatus status) {
        super(manager, status);
    }

    @Override
    public void notifyEvents(final ModelEventType eventType) {
        // TODO Auto-generated method stub
    }

    @Override
    public void receiveEvents() {
        // TODO Auto-generated method stub
    }


}
