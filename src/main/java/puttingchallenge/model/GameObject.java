package puttingchallenge.model;
import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;

/**
 * Class that implements an element of the game.
 */
public interface GameObject {
    /**
     * types of the game objects.
     */
    enum GameObjectType { BALL }
    /**
     * Sets the coordinates corresponding to the position of the element.
     * @param x
     *          abscissa of the position
     * @param y
     *          ordinate of the position
     */
    void setPosition(double x, double y);
    /**
     * Sets the velocity of the element.
     * @param x
     *          x-component of the vector
     * @param y
     *          y-component of the vector
     */
    void setVector(double x, double y);
    /**
     * @return the type of the element.
     */
    GameObjectType getType();
    /**
     * @return the coordinates of the element.
     */
    Point2D getPosition();
    /**
     * @return the velocity of the element.
     */
    Vector2D getVelocity();
}
