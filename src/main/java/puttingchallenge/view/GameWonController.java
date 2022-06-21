package puttingchallenge.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
/**
 * Class that defines the controller that manages the {@link Scene} shown when the user wins the game.
 * 
 */
public class GameWonController extends AbstractSceneController {
    /**
     * Method that handle the action on the menu button.
     * @param event
     */
    @FXML
    public void handleToMenu(final ActionEvent event) {
        this.getController().notifyEvent(event);
    }

}
