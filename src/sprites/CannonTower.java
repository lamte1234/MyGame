package sprites;

import gameProperties.Properties;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

public class CannonTower extends Tower {

    Bullet bullet;

    public CannonTower(Pane layer, Image image, double x, double y, double rotation){
        super(layer, image, x, y, rotation);

        this.reserveVelocity = Properties.cannonTower1ReserveRotationVelocity;
        this.towerRange = Properties.cannonTower1Range;
        this.damage = Properties.cannonTower1Damage;
    }

    @Override
    public void addToLayer() {
        super.addToLayer();

        bullet = new Bullet();

        this.layer.getChildren().add(bullet);
    }

    public void removeFromLayer(){
        if(this.target == null) {
            this.layer.getChildren().remove(bullet);
        }
    }

    public void update(){
        super.update();

        this.bullet.setValue(calculateDistanceToTarget());

        this.bullet.relocate(x + 32 + 32 * Math.cos(Math.toRadians(rotation) + Math.PI * 3/2), y + 32 + 32 * Math.sin(Math.toRadians(rotation) + Math.PI * 3/2));

        Rotate rotate = new Rotate();

        rotate.setPivotX(this.bullet.x);
        rotate.setPivotY(this.bullet.y / 2);
        rotate.setAngle(rotation + 270);

        this.bullet.bulletLine.getTransforms().add(rotate);
    }
}
