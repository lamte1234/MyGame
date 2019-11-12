package sprites;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;



public class HealthBar extends Pane {
    Rectangle outsideRect;
    Rectangle insideRect;

    double height = 5;

    double outsideWidth = 50;
    double insideWidth = 49;

    double x = 0;
    double y = 0;

    public HealthBar(){
        this.outsideRect = new Rectangle(x, y, outsideWidth, height);
        this.insideRect = new Rectangle(x, y, insideWidth, height);

        this.outsideRect.setFill(Color.RED);
        this.outsideRect.setStroke(Color.BLACK);
        this.outsideRect.setStrokeWidth(2);
        this.outsideRect.setStrokeType(StrokeType.OUTSIDE);

        this.insideRect.setFill(Color.LIMEGREEN);
        this.insideRect.setStrokeType(StrokeType.OUTSIDE);

        getChildren().addAll(outsideRect, insideRect);
    }

    public void setValue(double value){
        this.insideRect.setWidth(value * this.insideWidth);
    }
}
