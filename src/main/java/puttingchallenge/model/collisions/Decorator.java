package puttingchallenge.model.collisions;

/**
 * Represents a decorator for an ActiveBoundingBox.
 */
public abstract class Decorator implements ActiveBoundingBox {

    private final ActiveBoundingBox decorated;

    /**
     * @param box 
     *           the bounding box to decorate
     */
    public Decorator(final ActiveBoundingBox box) {
        this.decorated = box;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isColliding(final PassiveCircleBoundingBox circle) {
        return this.decorated.isColliding(circle);
    }

}
