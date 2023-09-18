package garden_sim;

import java.io.FileNotFoundException;

public class MeanFlower extends Flower{

    public MeanFlower() throws FileNotFoundException {
        super("src/garden_sim/nightshade.jpg");
    }

    /**
     * gets the change in health it has on a bee that is on it.
     *
     * @return an int (positive or negative) representing the effect it has on the health of a Bee.
     */
    @Override
    public int getHealthEffect() {
        return -10;
    }
}
