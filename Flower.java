package garden_sim;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class Flower {

    protected final int x;
    protected final int y;
    protected final ImageView image;

    public Flower(String imagePath) throws FileNotFoundException {
        double imageHeight = 0.15*MainController.getTheController().getFieldSizeY();

        this.x = (int) (Math.random() * MainController.getTheController().getFieldSizeX() * 0.80 + imageHeight*0.5);
        this.y = (int) (Math.random() * MainController.getTheController().getFieldSizeY() * 0.70 + imageHeight*0.5);
        image = new ImageView(new Image(new FileInputStream(imagePath)));

        image.setFitHeight(imageHeight);
        image.setPreserveRatio(true);

        image.setX(this.x);
        image.setY(this.y);
    }

    /**
     * gets the change in health it has on a bee that is on it.
     * @return an int (positive or negative) representing the effect it has on the health of a Bee.
     */
    public abstract int getHealthEffect();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
