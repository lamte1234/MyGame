package sprites;

import gameProperties.Properties;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class RocketTower extends Tower {

    public RocketTower(Pane layer, Image image, double x, double y, double rotation){
        super(layer, image, x, y, rotation);

        this.reserveVelocity = Properties.rocketTower1ReserveRotationVelocity;
        this.towerRange = Properties.rocketTower1Range;
        this.damage = Properties.rocketTower1Damage;
    }

}
