package puttingchallenge.gameobjects;

/**
 *
 */
public class Bat {
    private final BatType type;

    Bat(final BatType type) {
        this.type = type;
    }
    /**
     * @return
     *      the bat type
     */
    BatType getType() {
        return this.type;
    }
}
