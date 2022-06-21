
package puttingchallenge.view;

import puttingchallenge.model.events.Colleague;

/**
 * View of the application.
 */
public interface View extends Colleague {

    /**
     * Configure the view and show it on the screen.
     */
    void buildView();

    /**
     * Renders the state of any {@link GameObject} in the current scene.
     */
    void render();
}
