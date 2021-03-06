package sprites;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;




public class Tank extends Enemy {


    HealthBar healthBar;

    double maxHealth;

    public Tank(Pane layer, Image image,double x, double y, double velocity, double health)  {
        super(layer, image, x, y, velocity, health);

        this.maxHealth = health;

        this.killingScore = 2;
        this.killingGold = 50;
        this.costLives = 2;
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
        return getHealth() / this.maxHealth;
    }

    public void update() {
        super.update();

        healthBar.setValue(getRelativeHealth());

        healthBar.relocate(x , y - healthBar.getBoundsInLocal().getHeight());
    }


}