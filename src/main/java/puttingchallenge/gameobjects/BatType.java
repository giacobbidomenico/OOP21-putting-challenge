package puttingchallenge.gameobjects;
/**
 * Enumeration for all the types of bat.
 */
public enum BatType {
    /**
     * Hybrid bat.
     */
    HYBRID(0.7),
    /**
     * Wedge bat.
     */
    WEDGE(0.3),
    /**
     * Iron bat.
     */
    IRON(0.4),
    /**
     * Putter bat.
     */
    PUTTER(0.1),
    /**
     * Wood bat.
     */
    WOOD(1);

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
