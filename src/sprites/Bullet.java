package sprites;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;


public class Bullet extends Pane {
    Rectangle bulletLine;

    double height = 3;

    double width = 0;

    double x = 0;
    double y = 0;

    public Bullet(){


        this.bulletLine = new Rectangle(x, y, width, this.height);
        this.bulletLine.setFill(Color.RED);


        getChildren().add(bulletLine);
    }

    public void setValue(double value){
        this.bulletLine.setWidth(value);
    }
}
