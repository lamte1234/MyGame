package sprites;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.List;

public class Tower implements GameEntity {

    Enemy target;

    Image image;
    ImageView imageView;

    Pane layer;



    double x;
    double y;

    double reserveVelocity;
    double rotation;


    double damage;

    double width;
    double height;

    double targetAngle = 0;
    double currentAngle = 0;

    double distanceToTarget;

    double towerRange ;
    boolean withinFiringRange = false;

    public Tower(Pane layer, Image image, double x, double y, double rotation) {
        this.layer = layer;

        this.image = image;
        this.imageView = new ImageView(image);

        this.x = x;
        this.y = y;


        this.width = image.getWidth();
        this.height = image.getHeight();

        this.imageView.relocate(x, y);
        this.imageView.setRotate(rotation);

        addToLayer();
    }

    public void addToLayer(){
        this.layer.getChildren().add(this.imageView);
    }

    public double getCenterX() {
        return this.x + this.width / 2;
    }

    public double getCenterY(){
        return this.y + this.width / 2;
    }

    public void move(){

        Tower follower = this;

        withinFiringRange = false;

        if(target != null){
            double distanceX = target.getCenterX() - follower.getCenterX();
            double distanceY = target.getCenterY() - follower.getCenterY();

            double angleToTarget = Math.atan2(distanceY, distanceX) + Math.PI / 2;

            double targetAngle = Math.toDegrees(angleToTarget);
            double currentAngle = follower.rotation;

            if(Math.abs(currentAngle) > 360) {
                if(currentAngle < 0){
                    currentAngle = currentAngle % 360 + 360;
                }
                else{
                    currentAngle = currentAngle % 360;
                }
            }

            double angleDifferent = targetAngle - currentAngle;

            if(Math.abs(angleDifferent) < 180){

            }
            else{
                if(angleDifferent > 0){
                    targetAngle -= 360;
                }
                else{
                    targetAngle += 360;
                }
            }

            angleDifferent = targetAngle - currentAngle;

            currentAngle = currentAngle + angleDifferent / reserveVelocity;

            follower.rotation = currentAngle;

            withinFiringRange = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2) ) <= this.towerRange;
        }


    }

    public void setTarget(Enemy target){
        this.target = target;
    }

    public Enemy getTarget(){
        return this.target;
    }

    public void checkTarget() {

        if( target == null) {
            return;
        }

        if( !target.isAlive() || target.isRemovable()) {
            setTarget( null);
            return;
        }

        double distanceX = target.getCenterX() - getCenterX();
        double distanceY = target.getCenterY() - getCenterY();

        double distanceTotal = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        if( Double.compare( distanceTotal, towerRange) > 0) {
            setTarget( null);
        }

    }

    public void findTarget( List<Enemy> targetList) {

        if( getTarget() != null) {
            return;
        }

        Enemy closestTarget = null;
        double closestDistance = 0.0;

        for (Enemy target: targetList) {

            if (!target.isAlive())
                continue;


            double distanceX = target.getCenterX() - getCenterX();
            double distanceY = target.getCenterY() - getCenterY();

            double distanceTotal = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

            if( Double.compare( distanceTotal, towerRange) > 0) {
                continue;
            }

            if (closestTarget == null) {

                closestTarget = target;
                closestDistance = distanceTotal;

            } else if (Double.compare(distanceTotal, closestDistance) < 0) {

                closestTarget = target;
                closestDistance = distanceTotal;

            }
        }

        setTarget(closestTarget);

    }

    public void update(){
        imageView.setRotate(rotation);
    }

    public boolean hitTarget(Enemy enemy){
        return target == enemy && withinFiringRange;
    }

    public double getDamage(){
        return this.damage;
    }

    public double calculateDistanceToTarget(){ // for bullet : set width;
        if(target != null) {

            Tower follower = this;

            double distanceX = target.getCenterX() - follower.getCenterX();
            double distanceY = target.getCenterY() - follower.getCenterY();

            double distanceToTarget = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2)) - 32;

            return distanceToTarget;
        }
        else{
            return 0;
        }
    }
}
