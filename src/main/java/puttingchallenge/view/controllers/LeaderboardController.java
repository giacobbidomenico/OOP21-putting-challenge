package puttingchallenge.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import puttingchallenge.model.events.GameEvent;
import puttingchallenge.model.events.GameEventImpl;
import puttingchallenge.model.events.GameEventType;

/**
 * Class that defines the controller that manages the {@link Scene} related to the leader board.
 * 
 */
public class LeaderboardController extends AbstractSceneController {

    @FXML private TableView<String> table;

    /**
     * 
     */
    public LeaderboardController() {
    }

    /**
     * Request the interruption of application execution.
     */
    @FXML
    public void quitGame() {
        final GameEvent event = new GameEventImpl(GameEventType.QUIT);
        super.getMediator().notifyColleagues(event, this);
    }
}
