package puttingchallenge.model.gameobjects;

/**
 * Enumeration for all the types of bat.
 */
public enum BatType {
    /**
     * Hybrid bat.
     */
    HYBRID(1.4),
    /**
     * Wedge bat.
     */
    WEDGE(0.6),
    /**
     * Iron bat.
     */
    IRON(0.8),
    /**
     * Putter bat.
     */
    PUTTER(0.2),
    /**
     * Wood bat.
     */
    WOOD(2);

    private final double strength;

    BatType(final double strength) {
        this.strength = strength;
    }
    /**
     * Get the strength of the bat: the more it is, the further it can shoot.
     * @return
     *      the strength of the bat 
     */
    public double getStrength() {
        return this.strength;
    }
}
