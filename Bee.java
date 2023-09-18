package garden_sim;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public abstract class Bee {

    protected double x;
    protected double y;
    public Flower targetFlower;
    protected Healthbar healthbar;
    protected ImageView image;
    protected boolean isDead;

    protected final double perTickHealthDecrease;

    // the radius around the center of a flower where a bee is considered 'on the flower'.
    protected final int ON_FLOWER_THRESHOLD;

    public Bee(String imagePath, double perTickHealthDecrease) throws FileNotFoundException {
        double imageHeight = 0.15*MainController.getTheController().getFieldSizeY();
        double imageWidth = 0.15*MainController.getTheController().getFieldSizeX();

        ON_FLOWER_THRESHOLD = (int) imageHeight-20;

        this.perTickHealthDecrease = perTickHealthDecrease;

        this.x = (int) (Math.random() * MainController.getTheController().getFieldSizeX() * 0.80 + imageHeight*0.5);
        this.x = (int) (Math.random() * MainController.getTheController().getFieldSizeX() * 0.80 + imageWidth*0.5);
        this.y = (int) (Math.random() * MainController.getTheController().getFieldSizeY() * 0.70 + imageHeight*0.5);
        image = new ImageView(new Image(new FileInputStream(imagePath)));

        image.setFitHeight(imageHeight);
        image.setPreserveRatio(true);

        image.setX(this.x);
        image.setY(this.y);

        this.healthbar = new Healthbar(100);
        this.isDead = false;
    }

    public abstract Flower selectTargetFlower(List<Flower> flowers);

    public void setTargetFlower(Flower targetFlower) {
        this.targetFlower = targetFlower;
    }

    /**
     * Determines the next x and y of this bee, and updates the x and y attributes accordingly.
     * Updates the health-bar.
     */
    public void update() {
        this.x = getNextX();
        this.y = getNextY();

        this.image.setX(this.x);
        this.image.setY(this.y);

        this.healthbar.update(-1*perTickHealthDecrease);

        if (targetFlower != null && getDistanceToTargetFlower() <= ON_FLOWER_THRESHOLD) {
            this.image.setX(targetFlower.getX());
            this.image.setY(targetFlower.getY());
            this.healthbar.update(targetFlower.getHealthEffect());
            targetFlower = null;
        }
        System.out.println("X: " + x);
        System.out.println("Y: " + y);
    }

    protected int getDistanceToTargetFlower() {
        if (this.targetFlower != null) {
            return ((int) Math.sqrt(
                    (x-targetFlower.getX())*(x-targetFlower.getX())
                            +
                            (y-targetFlower.getY())*(y-targetFlower.getY()))
            );
        }
        return 0;
    }

    protected abstract double getNextX();

    protected abstract double getNextY();

    protected void toBack(){
        image.toBack();
    }


}
