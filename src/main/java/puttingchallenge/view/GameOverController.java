package puttingchallenge.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Class that defines the controller that manages the {@link Scene} shown at the game over.
 * 
 */
public class GameOverController extends AbstractSceneController {
    /**
     * Method that handle the action on the new game button.
     * @param event
     */
    @FXML
    public void handleNewGame(final ActionEvent event) {
        this.getController().notifyEvent(event);
    }
    /**
     * Method that handle the action on the menu button.
     * @param event
     */
    @FXML
    public void handleToMenu(final ActionEvent event) {
        this.getController().notifyEvent(event);
    }
}
