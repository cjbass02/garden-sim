package garden_sim;

import java.io.FileNotFoundException;

public class NiceFlower extends Flower{

    public NiceFlower() throws FileNotFoundException {
        super("src/garden_sim/coneflower.jpg");
    }

    /**
     * gets the change in health it has on a bee that is on it.
     *
     * @return an int (positive or negative) representing the effect it has on the health of a Bee.
     */
    @Override
    public int getHealthEffect() {
        return 10;
    }
}
