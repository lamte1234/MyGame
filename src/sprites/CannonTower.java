package sprites;

import gameProperties.Properties;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

public class CannonTower extends Tower {



    public CannonTower(Pane layer, Image image, double x, double y, double rotation){
        super(layer, image, x, y, rotation);


        this.reserveVelocity = Properties.cannonTower1ReserveRotationVelocity;
        this.towerRange = Properties.cannonTower1Range;
        this.damage = Properties.cannonTower1Damage;
    }


}
