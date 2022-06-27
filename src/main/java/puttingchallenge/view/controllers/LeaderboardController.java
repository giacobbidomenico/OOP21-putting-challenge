package puttingchallenge.view.controllers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import puttingchallenge.core.FileManager;
import puttingchallenge.model.events.GameEvent;
import puttingchallenge.model.events.GameEventImpl;
import puttingchallenge.model.events.GameEventType;

/**
 * Class that defines the controller that manages the {@link Scene} related to the leader board.
 * 
 */
public class LeaderboardController extends AbstractSceneController {

    @FXML private TableView<Score> table;
    @FXML private TableColumn<Score, Integer> attempts;
    @FXML private TableColumn<Score, String> scores;

    /**
     * Builds {@link LeaderboardController} and initializes the table.
     */
    public void init() {

        this.attempts.setCellValueFactory(new PropertyValueFactory<Score, Integer>("Attempt"));
        this.scores.setCellValueFactory(new PropertyValueFactory<Score, String>("Score"));

        final List<Score> scores = new ArrayList<>();
        try (BufferedReader f = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(FileManager.LEADERBOARD_FILE)))) {
            String line;
            int counter = 1;
            while ((line = f.readLine()) != null) {
                scores.add(new Score(line, counter));
                counter++;
            }
            this.table.setItems(null);
        } catch (IOException e) {
            scores.clear();
        }
        table.getItems().setAll(scores);
    }

    /**
     * Request the interruption of application execution.
     * @param e unused
     */
    @FXML
    public void quitGame(final ActionEvent e) {
        final GameEvent event = new GameEventImpl(GameEventType.QUIT);
        super.getMediator().notifyColleagues(event, this);
    }

    /**
     * Simple class that models the score.
     */
    private class Score {
        private final String scoreValue;
        private final Integer number;

        /**
         * @param score
         * @param number position in the table
         */
        Score(final String score, final Integer number) {
            this.scoreValue = score;
            this.number = number;
        }

        /**
         * @return score
         */
        public String getScore() {
            return this.scoreValue;
        }

        /**
         * @return position in the table
         */
        public Integer getNumber() {
            return this.number;
        }
    }
}
