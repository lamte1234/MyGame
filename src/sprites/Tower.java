package sprites;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.List;

public abstract class Tower implements GameEntity {

    Enemy target;

    Image image;
    ImageView imageView;

    Pane layer;


    double x;
    double y;

    double reverseVelocity;
    double rotation;


    double damage;

    double width;
    double height;

    double targetAngle = 0;
    double currentAngle = 0;

    double distanceToTarget;

    double towerRange ;
    boolean withinFiringRange = false;

    boolean removable = false;

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

    public void removeFromLayer(){
        this.layer.getChildren().remove(this.imageView);
    }

    public void setDamage(double damage){
        this.damage = damage;
    }

    public void setImage(Image image){
        this.layer.getChildren().remove(imageView);

        this.image = image;
        this.imageView = new ImageView(image);

        this.imageView.relocate(x, y);
        this.layer.getChildren().add(imageView);

    }

    public void setRange(double range){
        this.towerRange = range;
    }


    public double getRange(){
        return this.towerRange;
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

            double angleToTarget = Math.atan2(distanceY, distanceX);

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

            currentAngle = currentAngle + angleDifferent / reverseVelocity;

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

    public double getRotation(){
        return this.rotation;
    }

    public double calculateDistanceToTarget(){
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

    public boolean isRemovable(){
        return this.removable;
    }

    public void setRemovable(boolean removable){
        this.removable = removable;
    }

    public abstract void setBulletColor(Color color);
}
