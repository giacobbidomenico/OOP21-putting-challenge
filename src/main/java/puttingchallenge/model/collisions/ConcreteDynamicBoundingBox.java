package puttingchallenge.model.collisions;

/**
 * It wraps an ActiveBoundingBox so that its collision can be tested to a PassiveCircleBoundingBox dynamically.
 */
public class ConcreteDynamicBoundingBox implements DynamicBoundingBox {

    private final ActiveBoundingBox box;

    /**
     * Builds a new {@link ConcreteDynamicBoundingBox}.
     * @param box is the active bounding box wrapped
     */
    public ConcreteDynamicBoundingBox(final ActiveBoundingBox box) {
        this.box = box;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollisionTest collidesWith(final PassiveCircleBoundingBox circle) {
        return null;
    }


}
