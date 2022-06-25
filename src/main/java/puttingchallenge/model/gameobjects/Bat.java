package puttingchallenge.model.gameobjects;
/**
 * Class that represent the bat that a player can use.
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
