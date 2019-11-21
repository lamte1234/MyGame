package sprites;

import gameProperties.Properties;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class RocketTower extends Tower {

    Bullet rocketBullet;

    public RocketTower(Pane layer, Image image, double x, double y, double rotation){
        super(layer, image, x, y, rotation);

        this.reverseVelocity = Properties.rocketTower1ReverseRotationVelocity;
        this.towerRange = Properties.rocketTower1Range;
        this.damage = Properties.rocketTower1Damage;
    }

    @Override
    public void addToLayer() {
        super.addToLayer();

        rocketBullet = new Bullet(5, 15, Color.WHITE, this);

        this.layer.getChildren().add(rocketBullet);
    }

    public void removeFromLayer(){
        super.removeFromLayer();

        this.layer.getChildren().remove(rocketBullet);
    }

    public void move(){
        super.move();

        this.rocketBullet.move();
    }

    public void update(){
        super.update();

        this.rocketBullet.update();
    }

    public void setBulletColor(Color color){
        this.rocketBullet.setColor(color);
    }
}
