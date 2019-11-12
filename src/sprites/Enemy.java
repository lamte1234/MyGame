package sprites;

import gameProperties.Properties;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class Enemy implements GameEntity  {
        Image image;

        ImageView imageView;

        Pane layer;

        public int killingScore;
        public int killingGold;
        public double costLives;

        double x;
        double y;

        double velocity;

        double health;

        double width;
        double height;

        boolean removable = false;

        int pathPosCount = 0;

        public Enemy(){

        }

        public Enemy(Pane layer, Image image,double x, double y, double velocity, double health){
            this.layer = layer;
            this.image = image;
            this.velocity = velocity;
            this.x = x;
            this.y = y;
            this.health = health;

            this.imageView = new ImageView(image);
            this.imageView.relocate(x , y);

            this.width = image.getWidth();
            this.height = image.getHeight();

            addToLayer();
        }

        public Pane getLayer() {
            return layer;
        }

        public void setLayer(Pane layer){
            this.layer = layer;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getVelocity() {
            return velocity;
        }

        public void setVelocity(double velocity){
            this.velocity = velocity;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        public double getWidth() {
            return width;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        public double getHeight() {
            return height;
        }

        public void addToLayer(){
            this.layer.getChildren().add(this.imageView);
        }

        public void removeFromLayer() {
            this.layer.getChildren().remove(this.imageView);
        }

        public void setHealth(double health){
            this.health = health;
        }

        public double getHealth(){
            return this.health;
        }

        public boolean isRemovable(){
            return this.removable;
        }

        public void setRemovable(boolean removable){
            this.removable = removable;
        }

        public void move() {

            if(pathPosCount + 1 > 9){
               return;
            }

            double x1 = Properties.path[pathPosCount][0];
            double y1 = Properties.path[pathPosCount][1];

            double x2 = Properties.path[pathPosCount + 1][0];
            double y2 = Properties.path[pathPosCount + 1][1];

            if(x2 == x1){
                if(y2 > y1){ // go down
                    imageView.setRotate(90);
                    y += velocity;
                }
                if(y2 < y1){ // go up
                    imageView.setRotate(270);
                    y -= velocity;
                }
                if(y == y2){
                    pathPosCount ++;
                }
            }

            if(y2 == y1) {
                if (x2 > x1) { // go right
                    imageView.setRotate(0);
                    x += velocity;
                }
                if (x2 < x1) { // go left
                    imageView.setRotate(180);
                    x -= velocity;
                }
                if(x == x2){
                    pathPosCount ++;
                }
            }

        }

        public boolean isAlive(){
            if(this.health > 0){
                return true;
            }
            else return false;
        }

        public ImageView getImageView(){
            return imageView;
        }

        public void update(){
            imageView.relocate(x, y);
        }

        public boolean collidesWith(){
            // handle collision
            return false;
        }

        public void checkRemovability(){
            if(this.x == Properties.defensePoint[0][0] && this.y >= Properties.defensePoint[0][1]){
                setRemovable(true);
            }
            if(!isAlive()){
                setRemovable(true);
            }
        }

        public double getCenterX(){
            return this.x + this.width / 2;
        }

        public double getCenterY(){
            return this.y + this.height / 2;
        }

        public void getDamagedBy(Tower tower){
            this.health -= tower.getDamage();
        }
}