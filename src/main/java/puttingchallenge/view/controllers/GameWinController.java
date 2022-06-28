package puttingchallenge.view.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import puttingchallenge.model.events.GameEvent;
import puttingchallenge.model.events.GameEventImpl;
import puttingchallenge.model.events.GameEventType;

/**
 * Class that defines the controller that manages the {@link Scene} shown when the user wins the game.
 */
public class GameWinController extends AbstractSceneController {

    /**
     * Method that handle the action on the menu button.
     * 
     * @param e
     *          unused
     */
    @FXML
    public void backToMenu(final ActionEvent e) {
        final GameEvent event = new GameEventImpl(GameEventType.SHOW_MAIN_MENU);
        super.getMediator().notifyColleagues(event, this);
    }

}
