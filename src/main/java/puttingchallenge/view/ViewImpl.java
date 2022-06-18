package puttingchallenge.view;

import puttingchallenge.core.GameEngine;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Objects;
import javafx.stage.Stage;

/**
 * Class that implements the view.
 */
public class ViewImpl implements View {

    private final GameEngine controller;
    private final Stage stage;
    private final SceneManager manager;

    /**
     * Build a new {@link ViewImpl}.
     * 
     * @param controller
     *                 {@link GameEngine},the main controller of the application
     * @param stage
     *                 Main {@link Stage} of the view
     */
    public ViewImpl(final GameEngine controller, final Stage stage) {
        this.controller = Objects.requireNonNull(controller);
        this.stage = Objects.requireNonNull(stage);
        this.manager = new SceneManagerImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildView() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final double width = screen.getWidth();
        final double height = screen.getHeight();
        this.stage.setMinWidth(width / 2);
        this.stage.setMinHeight(height / 2);
        this.loadScene(SceneType.MAIN_MENU);
        this.stage.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadScene(final SceneType typeScene) {
        final var currentScene = this.manager.getSceneByType(typeScene);
        this.stage.setScene(currentScene);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
    }

}
