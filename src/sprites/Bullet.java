package sprites;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;



public class Bullet implements GameEntity  {
    Tower firingTower;


    Pane layer;

    Image image;
    ImageView imageView;


    double x ;
    double y ;

    double velocity;



    public Bullet(Pane layer, Image image, double velocity, Tower firingTower){
        this.layer = layer;

        this.image = image;
        this.imageView = new ImageView(image);

        this.firingTower = firingTower;


        this.x  = firingTower.getCenterX() - image.getWidth() / 2 ;
        this.y = firingTower.getCenterY()  - image.getHeight() / 2;

        this.velocity = velocity;



        this.layer.getChildren().add(imageView);
    }

    public boolean checkBulletCanReMove(){
        double distanceGo = Math.sqrt(Math.pow(this.getCenterX() - this.firingTower.getCenterY(), 2) + Math.pow(this.getCenterY() - firingTower.getCenterY(), 2));
        if(distanceGo >= firingTower.getRange()){
            return true;
        }
        else if(this.firingTower.getTarget().contain(this.getCenterX(), this.getCenterY())){
            return true;
        }
        else return false;
    }

    public void move(){
        double rotation = this.firingTower.rotation;

        if(firingTower.getTarget() != null) {
            double vel_x = velocity * Math.cos(Math.toRadians(rotation));
            double vel_y = velocity * Math.sin(Math.toRadians(rotation));
            if(checkBulletCanReMove()){
                x = firingTower.getCenterX() - image.getWidth() / 2;
                y = firingTower.getCenterY()  - image.getHeight() / 2;
            }
            else {
                x += vel_x;
                y += vel_y;
            }
        }
    }

    public void update(){
        this.imageView.relocate(x, y);

    }

    public double getCenterX(){
        return x + this.image.getWidth() / 2;
    }

    public double getCenterY(){
        return y + this.image.getHeight() / 2;
    }
}
