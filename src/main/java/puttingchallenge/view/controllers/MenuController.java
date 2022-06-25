package puttingchallenge.view.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import puttingchallenge.model.events.GameEvent;
import puttingchallenge.model.events.GameEventImpl;
import puttingchallenge.model.events.GameEventType;

/**
 * Class that defines the controller that manages the {@link Scene} related to the main menu.
 * 
 */
public class MenuController extends AbstractSceneController {

    /**
     * Request the interruption of application execution.
     * 
     * @param e
     *          unused
     */
    @FXML
    public void quitGame(final ActionEvent e) {
        final GameEvent event = new GameEventImpl(GameEventType.QUIT);
        super.getMediator().notifyColleagues(event, this);
    }

    /**
     * Request the start of the game.
     * 
     * @param e
     *          unused
     */
    @FXML
    public void startGame(final ActionEvent e) {
        final GameEvent event = new GameEventImpl(GameEventType.START);
        super.getMediator().notifyColleagues(event, this);
    }

}
