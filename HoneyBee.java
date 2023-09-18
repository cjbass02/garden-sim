package garden_sim;

import java.io.FileNotFoundException;
import java.util.List;

public class HoneyBee extends Bee {
    private int currentFlower = 0;

    public HoneyBee() throws FileNotFoundException {
        super("src/garden_sim/bee-1.jpg", 1);
    }

    @Override
    public Flower selectTargetFlower(List<Flower> flowers) {
        int index = (int) (flowers.size() * Math.random());
        while(currentFlower == index){
            index = (int) (flowers.size() * Math.random());
        }
        currentFlower = index;
        //System.out.println("Selected Flower: " + index);

        return flowers.get(index);
    }

    @Override
    protected double getNextX() {
        if (targetFlower != null) {
            return ((this.x < targetFlower.getX()) ? this.x+10 : this.x-10);

        }

        return this.x;
    }

    @Override
    protected double getNextY() {
        if (targetFlower != null) {
            double slope;
            if(targetFlower.getX() != this.x) {
                slope = Math.abs((1.0*targetFlower.getY()-(this.y)) / (1.0*targetFlower.getX()-(this.x)));
            } else {
                slope = 1;
            }
            System.out.println("_____________");
            System.out.println("Target X: " + targetFlower.getX());
            System.out.println("This X: " + this.x);
            System.out.println("Target Y: " + targetFlower.getY());
            System.out.println("This Y: " + this.y);
            System.out.println("Slope: " + slope);
            if(10*slope > 30 ) {
                return (this.y < targetFlower.getY()) ? this.y+1*slope : this.y+1*-slope;
            }
            return (this.y < targetFlower.getY()) ? this.y+10*slope : this.y+10*-slope;



//            double slope = ((double)targetFlower.getY()-(this.y)) / ((double)targetFlower.getX()-(this.x));
//            return this.y + 10*slope;
        }
        return this.y;
    }
}
