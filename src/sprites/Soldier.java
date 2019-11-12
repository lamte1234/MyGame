package sprites;

import gameProperties.Properties;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;




public class Soldier extends Enemy {


    HealthBar healthBar;



    public Soldier(Pane layer, Image image,double x, double y, double velocity, double health)  {
        super(layer, image, x, y, velocity, health);

        this.killingScore = 1;
        this.killingGold = 20;
        this.costLives = 1;
    }

    @Override
    public void addToLayer() {
        super.addToLayer();

        healthBar = new HealthBar();

        this.layer.getChildren().add(healthBar);
    }

    @Override
    public void removeFromLayer(){
        super.removeFromLayer();

        this.layer.getChildren().remove(healthBar);
    }

    public double getRelativeHealth(){
        return getHealth() / Properties.soldierMaxHealth;
    }

    public void update() {
        super.update();

        healthBar.setValue(getRelativeHealth());

        healthBar.relocate(x , y - healthBar.getBoundsInLocal().getHeight());
    }


}