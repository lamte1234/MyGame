package sprites;

import gameProperties.Properties;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class RocketTower_2 extends Tower {

    Bullet rocketBullet;

    public RocketTower_2(Pane layer, Image image, double x, double y, double rotation){
        super(layer, image, x, y, rotation);

        this.reverseVelocity = Properties.rocketTower2ReverseRotationVelocity;
        this.towerRange = Properties.rocketTower2Range;
        this.damage = Properties.rocketTower2Damage;
    }

    @Override
    public void addToLayer() {
        super.addToLayer();

        rocketBullet = new Bullet(5, 15, Color.RED, this);

        this.layer.getChildren().add(rocketBullet);
    }

    public void move(){
        super.move();

        this.rocketBullet.move();
    }

    public void update(){
        super.update();

        this.rocketBullet.update();
    }
}
