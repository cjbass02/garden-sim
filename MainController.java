package garden_sim;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    private static MainController theController = null;

    Stage stage;
    Scene scene;

    @FXML
    private Pane fieldPane;

    @FXML
    private Button startSimulation;

    @FXML
    private TextField niceFlowerField = new TextField();
    @FXML
    private TextField bumbleBeeField = new TextField();
    @FXML
    private TextField meanFlowerField = new TextField();
    @FXML
    private TextField honeyBeeField = new TextField();

    private ArrayList<Bee> bees = new ArrayList<>();
    private ArrayList<Flower> flowers = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        System.out.println("Initializing");
        theController = this;
        System.out.println(fieldPane == null);
    }

    @FXML
    public void startSimulation() throws FileNotFoundException {
        makeFlowers();
        makeBees();

        flowers.forEach(flower -> this.instanceAddChild(flower.image));
        bees.forEach(bee -> {
            this.instanceAddChild(bee.image);
            bee.setTargetFlower(bee.selectTargetFlower(flowers));
        });
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("This is a garden simulator, it will simulate the relationship between two types of bees and flowers.");
        a.setHeaderText("Simulation Starting");
        a.showAndWait();
        startSimulation.setDisable(true);
    }

    public void setEventHandlers() {
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.SPACE) {
                //System.out.println("Advance Simulation");
                this.advanceSimulation();
            }
        });
    }

    public void advanceSimulation() {
        ArrayList<Bee> rem = new ArrayList<>();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        if (bees.size() != 0) {
            bees.forEach(bee -> {
                this.instanceRemoveChild(bee.image);
                bee.update();
                if (bee.healthbar.getCurrentHealth() > 0) {
                    this.instanceAddChild(bee.image);
                    bee.targetFlower = (bee.targetFlower == null) ? bee.selectTargetFlower(flowers) : bee.targetFlower;
                } else {
                    bee.isDead = true;
                    bee.toBack();
                    rem.add(bee);
                    //bees.remove(bee);
                }
            });
            bees.removeAll(rem);
            if (bees.size() == 0) {
                a.setHeaderText("Simulation Ended");
                a.setContentText("All bees have died");
                a.showAndWait();
                System.exit(0);
            }
        }

    }

    public void makeFlowers() throws FileNotFoundException {
        //int numFlowers = 8;
        int numNiceFlowers;
        int numMeanFlowers;
        try {
            numNiceFlowers = Integer.parseInt(niceFlowerField.getText());
            numMeanFlowers = Integer.parseInt(meanFlowerField.getText());
        } catch (NumberFormatException e) {
            numNiceFlowers = 2;
            numMeanFlowers = 2;
        }
        niceFlowerField.setDisable(true);
        meanFlowerField.setDisable(true);

        for(int i = 0; i < numMeanFlowers; i++) {
            Flower cur = new MeanFlower();
            theController.flowers.add(cur);
        }
        for(int i = 0; i < numNiceFlowers; i++) {
            Flower cur = new NiceFlower();
            theController.flowers.add(cur);
        }
    }

    private void makeBees() throws FileNotFoundException {
        int numBees;
        try {
            numBees = Integer.parseInt(honeyBeeField.getText());
        } catch (NumberFormatException e) {
            numBees = 1;
        }
        honeyBeeField.setDisable(true);
        bumbleBeeField.setDisable(true);
        if(numBees == 0) {
            numBees =1;
        }
        //int numBees = 1;
        for(int i = 0; i < numBees; i++) {
            Bee cur = new HoneyBee();

            bees.add(cur);
        }
    }

    public static MainController getTheController() {
        return (theController == null) ? new MainController()  : theController;
    }

    private void instanceAddChild(Node node) {
        fieldPane.getChildren().add(node);
    }

    private void instanceRemoveChild(Node node) {
        fieldPane.getChildren().remove(node);
    }

    public void setStage(Stage primary){
        this.stage = primary;
    }

    public int getFieldSizeX() {
        return (int) fieldPane.getWidth();
    }

    public int getFieldSizeY() {
        return (int) fieldPane.getHeight();
    }

}
