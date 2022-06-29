package puttingchallenge.view;

import java.io.FileNotFoundException;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import puttingchallenge.model.gameobjects.GameObject;
import puttingchallenge.view.controllers.LevelController;

/**
 *
 */
public class EnvironmentScene extends Scene {

    private final LevelController levelController = new LevelController();

    /**
     * {@link Scene} corresponding to the game {@link Environment}.
     * 
     * @param w
     *        width of the {@link Scene}
     * @param h
     *        height of the {@link Scene}
     * @param pathBackground
     *        path of the background {@link Image}
     * @param objs
     *        {@link List} of the {@link GameObject}s, presents in the actual
     *        {@link Environment}
     * @throws FileNotFoundException 
     */
    public EnvironmentScene(final double w, 
                           final double h,
                           final String pathBackground,
                           final List<GameObject> objs) throws FileNotFoundException {
        super(new AnchorPane(), w, h);
        final AnchorPane layout = (AnchorPane) this.getRoot();
        this.getRoot();
        final Button button = new Button("Quit");
        button.setOnAction(levelController::handle);
        final var posWButton = w * (0.1);
        button.setLayoutX(w - posWButton);
        final Label score = new Label();
        final var posWScore = w * (0.3);
        score.setLayoutX(posWScore);
        final Label lives = new Label();
        final var posWLives = w * (0.4);
        score.setLayoutX(posWLives);
        final Canvas canvas = new Canvas(w, h);
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, levelController);
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, levelController);
        final Image backgroundImage = new Image(pathBackground);
        final BackgroundImage bi = new BackgroundImage(backgroundImage, 
                                                       BackgroundRepeat.NO_REPEAT, 
                                                       BackgroundRepeat.NO_REPEAT, 
                                                       BackgroundPosition.DEFAULT,
                                                       new BackgroundSize(w, h, false, false, false, false));
        layout.setBackground(new Background(bi));
        layout.getChildren().add(canvas);
        layout.getChildren().add(button);
        layout.getChildren().add(score);
        layout.getChildren().add(lives);
        levelController.init(this, objs, canvas.getGraphicsContext2D(), score, lives);
    }

    /**
     * @return the {@link LevelController}, the {@link SceneController} that manage 
     *         the {@link EnvironmentScene}
     */
    public LevelController getController() {
        return levelController;
    }
}
