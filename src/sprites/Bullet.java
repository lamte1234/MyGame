package sprites;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Bullet extends Pane implements GameEntity  {
    Tower firingTower;


    Circle bullet;
    double radius;

    double x ;
    double y ;

    double reverseVelocity;

    public Bullet(double radius,double reverseVelocity, Color color, Tower firingTower){
        this.radius = radius;

        this.reverseVelocity = reverseVelocity;

        this.firingTower = firingTower;

        this.x = firingTower.getCenterX() - this.radius;
        this.y = firingTower.getCenterY() - this.radius;
        bullet = new Circle(x, y, radius);

        bullet.setFill(color);

        getChildren().add(bullet);
    }

    public boolean checkBulletCanReMove(){
        if(this.firingTower.getTarget() != null) {
            double distanceGo = Math.sqrt(Math.pow(this.getCenterX() - this.firingTower.getCenterY(), 2) + Math.pow(this.getCenterY() - firingTower.getCenterY(), 2));
            if (distanceGo >= firingTower.getRange()) {
                return true;
            } else if (this.firingTower.getTarget().contain(this.getCenterX(), this.getCenterY())) {
                return true;
            } else return false;
        }
        else return true;
    }

    public void move(){
        if(firingTower.getTarget() != null){
            double difX = this.firingTower.getTarget().getCenterX() - this.firingTower.getCenterX();
            double difY = this.firingTower.getTarget().getCenterY() - this.firingTower.getCenterY();

            double vel_x = difX / reverseVelocity;
            double vel_y = difY / reverseVelocity;

            x += vel_x;
            y += vel_y;
            if(checkBulletCanReMove()){
                x = firingTower.getCenterX() - this.radius;
                y = firingTower.getCenterY()  - this.radius;
            }
        }
        else{
            x = firingTower.getCenterX() - this.radius;
            y = firingTower.getCenterY() - this.radius;
        }
    }

    public void update(){
        this.bullet.relocate(x, y);


    }

    public double getCenterX(){
        return x + this.radius / 2;
    }

    public double getCenterY(){
        return y + this.radius / 2;
    }
}
