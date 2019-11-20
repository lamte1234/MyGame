package sprites;

import gameProperties.Properties;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class CannonTower_2 extends Tower{
    Bullet cannonBullet;


    public CannonTower_2(Pane layer, Image image, double x, double y, double rotation){
        super(layer, image, x, y, rotation);

        this.reverseVelocity = Properties.cannonTower2ReverseRotationVelocity;
        this.towerRange = Properties.cannonTower2Range;
        this.damage = Properties.cannonTower2Damage;
    }

    @Override
    public void addToLayer() {
        super.addToLayer();

        cannonBullet = new Bullet(4, 4, Color.GOLD, this);

        this.layer.getChildren().add(cannonBullet);
    }

    public void move(){
        super.move();

        this.cannonBullet.move();
    }

    public void update(){
        super.update();

        this.cannonBullet.update();
    }
}