package garden_sim;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;

public class BumbleBee extends Bee {

    public BumbleBee() throws FileNotFoundException {
        super("src/garden_sim/bee-2.jpg", 1);
    }

    @Override
    public Flower selectTargetFlower(List<Flower> flowers) {
        return null;
    }

    @Override
    protected double getNextX() {
        return 0;
    }

    @Override
    protected double getNextY() {
        return 0;
    }
}
