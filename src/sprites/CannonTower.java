package sprites;

import gameProperties.Properties;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class CannonTower extends Tower {

    Bullet cannonBullet;


    public CannonTower(Pane layer, Image image, double x, double y, double rotation){
        super(layer, image, x, y, rotation);

        this.reverseVelocity = Properties.cannonTower1ReverseRotationVelocity;
        this.towerRange = Properties.cannonTower1Range;
        this.damage = Properties.cannonTower1Damage;
    }

    @Override
    public void addToLayer() {
        super.addToLayer();

        cannonBullet = new Bullet(3, 3, Color.BLACK, this);

        this.layer.getChildren().add(cannonBullet);
    }

    public void removeFromLayer(){
        super.removeFromLayer();

        this.layer.getChildren().remove(cannonBullet);
    }

    public void move(){
        super.move();

        this.cannonBullet.move();
    }

    public void update(){
        super.update();

        this.cannonBullet.update();
    }

    public Bullet getCannonBullet(){
        return this.cannonBullet;
    }

    public void setBulletColor(Color color){
        this.cannonBullet.setColor(color);
    }
}
