import gameProperties.Properties;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sprites.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Game extends Application  {
    private double width = 1280 + 68;
    private double height = 768;

    int score = 0;
    int gold = Properties.startingGold;
    int heart = Properties.hearts;

    boolean wave[] = {true, false, false, false, false};
    int waves = 0;
    int dem = 0;
    int speed = 0;

    boolean check_1 = false;
    boolean check_2 = false;
    boolean check_3 = false;
    boolean check_4 = false;


    List<Enemy> enemies = new ArrayList<Enemy>();
    List<Tower> towers = new ArrayList<Tower>();


    boolean[] checkTowerLocationHasTower = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    boolean[] checkTowerLocationHasCannonTowerLevel1 = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    boolean[] checkTowerLocationHasRocketTowerLevel1 = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};

    Pane backgroundLayer;
    Pane playFieldLayer;
    Pane UILayer;
    Pane win;
    Pane lose;

    Text UIText = new Text();

    Image backgroundImage;

    Image cannonImage;
    Image cannon2Image;
    Image rocketImage;
    Image rocket2Image;

    Image soldierImage;
    Image tankImage;
    Image tank2Image;
    Image planeImage;
    Image plane2Image;

    Image winningImage;
    Image losingImage;


    Scene scene1,scene2,scene3;
    Stage window;


    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        window = stage;

        backgroundLayer = new Pane();
        playFieldLayer = new Pane();
        UILayer = new Pane();
        win = new Pane();
        lose = new Pane();


        root.getChildren().add(backgroundLayer);
        root.getChildren().add(playFieldLayer);
        root.getChildren().add(UILayer);


        playFieldLayer.setPrefSize(this.width, this.height);

        playFieldLayer.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            double x = e.getX();
            double y = e.getY();
            if (x >= 1285 && x <= 1341 && y >= 93 && y <= 147){
                check_1 = true;
                check_2 = false;
                check_3 = false;
                check_4 = false;
            }
            else if (x >= 1285 && x <= 1341 && y >= 182 && y <= 240){
                check_2 = true;
                check_1 = false;
                check_3 = false;
                check_4 = false;
            }
            else if (x >= 1285 && x <= 1341 && y >= 269 && y <= 334){
                check_3 = true;
                check_1 = false;
                check_2 = false;
                check_4 = false;
            }
            else if (x >= 1285 && x <= 1341 && y >= 366 && y <= 427){
                check_4 = true;
                check_1 = false;
                check_2 = false;
                check_3 = false;
            }

            if (checkTowerLocation(x, y, Properties.towerLocation) && gold >= Properties.cannon1Gold && check_1  && !checkTowerLocationHasTower[returnTowerLocation(x, y, Properties.towerLocation)]) {
                createTowerLevel1(Properties.towerLocation[returnTowerLocation(x, y, Properties.towerLocation)][0], Properties.towerLocation[returnTowerLocation(x, y, Properties.towerLocation)][1]);
                gold -= Properties.cannon1Gold;
                check_1 = false;
            }

            if (checkTowerLocation(x, y, Properties.towerLocation) && gold >= Properties.rocket1Gold && check_2 && !checkTowerLocationHasTower[returnTowerLocation(x, y, Properties.towerLocation)]) {
                createRocketLevel1(Properties.towerLocation[returnTowerLocation(x, y, Properties.towerLocation)][0], Properties.towerLocation[returnTowerLocation(x, y, Properties.towerLocation)][1]);
                gold -= Properties.rocket1Gold;
                check_2 = false;
            }

            if (checkTowerLocation(x, y, Properties.towerLocation) && gold >= Properties.cannon2Gold && check_3  && checkTowerLocationHasCannonTowerLevel1[returnTowerLocation(x, y, Properties.towerLocation)]) {
                createTowerLevel2(Properties.towerLocation[returnTowerLocation(x, y, Properties.towerLocation)][0], Properties.towerLocation[returnTowerLocation(x, y, Properties.towerLocation)][1]);
                gold -= Properties.cannon2Gold;
                check_3 = false;
            }

            if (checkTowerLocation(x, y, Properties.towerLocation) && gold >= Properties.rocket2Gold && check_4 && checkTowerLocationHasRocketTowerLevel1[returnTowerLocation(x, y, Properties.towerLocation)]) {
                createRocketLevel2(Properties.towerLocation[returnTowerLocation(x, y, Properties.towerLocation)][0], Properties.towerLocation[returnTowerLocation(x, y, Properties.towerLocation)][1]);
                gold -= Properties.rocket2Gold;
                check_4 = false;
            }
        });


        scene1 = new Scene(root, this.width, this.height);

        window.setTitle("TowerDefense");
        window.setScene(scene1);
        window.show();



        loadGame();

        // createStartScreen();
        // thread.sleep(2000);

        ImageView winningImgView = new ImageView(winningImage);
        ImageView losingImgView = new ImageView(losingImage);

        win.getChildren().add(winningImgView);
        lose.getChildren().add(losingImgView);

        scene2 = new Scene(win, this.width, this.height);
        scene3 = new Scene(lose, this.width, this.height);

        createBackgroundLayer();
        createPlayerLayer();
        createUILayer();

        AnimationTimer gameLoop = new AnimationTimer() {
                @Override
                public void handle(long l) {
                    spawnEnemies();

                    towers.forEach(tower -> tower.checkTarget());
                    towers.forEach(tower -> tower.findTarget(enemies));
                    towers.forEach(tower -> tower.move());

                    enemies.forEach(enemy -> enemy.move());

                    checkTowerAttack();// towers attack enemies

                    towers.forEach(tower -> tower.update());

                    enemies.forEach(enemy -> enemy.update());

                    checkEnemyAtDefensePoint(enemies); // decrease lives
                    increaseGoldAndScore(enemies); // increase gold and score
                    enemies.forEach(enemy -> enemy.checkRemovability());
                    removeEnemy(enemies);

                    updateUI();

                    if(checkWinningCondition()){
                        window.setScene(scene2);
                        stop();
                    }

                    if(checkLosingCondition()){
                        window.setScene(scene3);
                        stop();
                    }
                }
            };
        gameLoop.start();


    }

    private void loadGame() {
        backgroundImage = new Image(getClass().getResource("images/Background.png").toExternalForm());

        soldierImage = new Image(getClass().getResource("images/VietCongSoldier.png").toExternalForm());

        tankImage = new Image(getClass().getResource("images/Tank1.png").toExternalForm());
        tank2Image = new Image(getClass().getResource("images/Tank2.png").toExternalForm());

        planeImage = new Image(getClass().getResource("images/Plane1.png").toExternalForm());
        plane2Image = new Image(getClass().getResource("images/Plane2.png").toExternalForm());

        cannonImage = new Image(getClass().getResource("images/CannonTower1.png").toExternalForm());
        cannon2Image = new Image(getClass().getResource("images/CannonTower2.png").toExternalForm());
        rocketImage = new Image(getClass().getResource("images/MissileTower1.png").toExternalForm());
        rocket2Image = new Image(getClass().getResource("images/MissileTower2.png").toExternalForm());

        winningImage = new Image(getClass().getResource("images/Victory.png").toExternalForm());
        losingImage = new Image(getClass().getResource("images/GameOver.png").toExternalForm());
     }

    private void createBackgroundLayer(){
        ImageView imgView = new ImageView(backgroundImage);
        backgroundLayer.getChildren().add(imgView);
    }

    /* private void createStartScreen(){
        ImageView imageView = new ImageView(ScreenImage);
        backgroundLayer.getChildren().add(imageView);
    } */

    private void createPlayerLayer(){
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(10.0);
        dropShadow.setOffsetY(10.0);

        playFieldLayer.setEffect(dropShadow);
    }

    private void createUILayer(){
        UIText.setFont(Font.font(null, FontWeight.BOLD, 26));
        UIText.setStroke(Color.BLACK);
        UIText.setFill(Color.LIGHTGOLDENRODYELLOW);
        UIText.setText("Gold: " + Integer.toString(gold) + "\n" + "Score: " + Integer.toString(score) + "\n" + "Lives: " + Integer.toString(heart) + "\n" + "Wave: " + Integer.toString(waves + 1));
        UIText.relocate(0, 0);

        UILayer.getChildren().add(UIText);
    }

    private void checkTowerAttack() {
        for(Tower tower : towers){
            for(Enemy enemy : enemies){
                if(tower.hitTarget(enemy)){
                    enemy.getDamagedBy(tower);
                }
            }
        }

    }

    public void removeEnemy(List<Enemy> enemyList){
        Iterator<Enemy> iterator = enemyList.iterator();
        while( iterator.hasNext()) {
            Enemy enemy = iterator.next();

            if( enemy.isRemovable()) {

                enemy.removeFromLayer();

                iterator.remove();
            }
        }
    }



    int a = 50;
    private void spawnEnemies() {
        if (speed <= a) {
            speed++;
            return;
        }
        a = 50;
        speed = 0;

        Image img1 = soldierImage;
        Image img2 = tankImage;
        Image img4 = tank2Image;
        Image img3 = planeImage;
        Image img5 = plane2Image;

        // Wave 1
        if (wave[0]) {
            dem++;
            Soldier soldier = new Soldier(playFieldLayer, img1, Properties.spawnPoint[0][0], Properties.spawnPoint[0][1], Properties.soldierVelocity, Properties.soldierMaxHealth);
            enemies.add(soldier);
            if (dem >= 10) {
                wave[0] = false;
                wave[1] = true;
                waves++;
                dem = 0;
                a = 300;
            }
        }

        // Wave 2
        if (wave[1] && a == 50) {
            dem++;
            if (dem >= 0 && dem < 10) {
                Soldier soldier = new Soldier(playFieldLayer, img1, Properties.spawnPoint[0][0], Properties.spawnPoint[0][1], Properties.soldierVelocity, Properties.soldierMaxHealth);
                enemies.add(soldier);
            }
            if (dem >= 10 && dem < 15) {
                Tank tank = new Tank(playFieldLayer, img2, Properties.spawnPoint[0][0], Properties.spawnPoint[0][1], Properties.tankVelocity, Properties.tankMaxHealth);
                enemies.add(tank);
            }
            if (dem >= 15) {
                wave[1] = false;
                wave[2] = true;
                waves++;
                dem = 0;
                a = 300;
            }
        }

        // Wave 3
        if (wave[2]&& a == 50) {
            dem++;
            if (dem >= 0 && dem < 10) {
                Soldier soldier = new Soldier(playFieldLayer, img1, Properties.spawnPoint[0][0], Properties.spawnPoint[0][1], Properties.soldierVelocity, Properties.soldierMaxHealth);
                enemies.add(soldier);
            }
            if (dem >= 10 && dem < 20) {
                Tank tank = new Tank(playFieldLayer, img2, Properties.spawnPoint[0][0], Properties.spawnPoint[0][1], Properties.tankVelocity, Properties.tankMaxHealth);
                enemies.add(tank);
            }
            if (dem >= 20 && dem < 30) {
                Plane plane = new Plane(playFieldLayer, img3, Properties.spawnPoint[0][0], Properties.spawnPoint[0][1], Properties.planeVelocity, Properties.planeMaxHealth);
                enemies.add(plane);
            }
            if (dem >= 30) {
                wave[2] = false;
                wave[3] = true;
                waves++;
                dem = 0;
                a = 300;
            }
        }
        // wave 4
        if (wave[3]&& a == 50) {
            dem++;
            if (dem >= 0 && dem < 10) {
                Soldier soldier = new Soldier(playFieldLayer, img1, Properties.spawnPoint[0][0], Properties.spawnPoint[0][1], Properties.soldierVelocity, Properties.soldierMaxHealth);
                enemies.add(soldier);
            }
            if (dem >= 10 && dem < 20) {
                Tank tank = new Tank(playFieldLayer, img2, Properties.spawnPoint[0][0], Properties.spawnPoint[0][1], Properties.tankVelocity, Properties.tankMaxHealth);
                enemies.add(tank);
            }
            if (dem >= 20 && dem < 30) {
                Plane plane = new Plane(playFieldLayer, img3, Properties.spawnPoint[0][0], Properties.spawnPoint[0][1], Properties.planeVelocity, Properties.planeMaxHealth);
                enemies.add(plane);
            }
            if (dem >= 30 && dem < 40) {
                Tank tank2 = new Tank(playFieldLayer, img4, Properties.spawnPoint[0][0], Properties.spawnPoint[0][1], Properties.tank2Velocity, Properties.tank2MaxHealth);
                enemies.add(tank2);
            }
            if (dem >= 40) {
                wave[3] = false;
                wave[4] = true;
                waves++;
                dem = 0;
                a = 300;
            }
        }

        // wave 5
        if (wave[4] && a == 50) {
            dem++;
            if (dem >= 0 && dem < 10) {
                Soldier soldier = new Soldier(playFieldLayer, img1, Properties.spawnPoint[0][0], Properties.spawnPoint[0][1], Properties.soldierVelocity, Properties.soldierMaxHealth);
                enemies.add(soldier);
            }
            if (dem >= 10 && dem < 20) {
                Tank tank = new Tank(playFieldLayer, img2, Properties.spawnPoint[0][0], Properties.spawnPoint[0][1], Properties.tankVelocity, Properties.tankMaxHealth);
                enemies.add(tank);
            }
            if (dem >= 20 && dem < 30) {
                Plane plane = new Plane(playFieldLayer, img3, Properties.spawnPoint[0][0], Properties.spawnPoint[0][1], Properties.planeVelocity, Properties.planeMaxHealth);
                enemies.add(plane);
            }
            if (dem >= 30 && dem < 40) {
                Tank tank2 = new Tank(playFieldLayer, img4, Properties.spawnPoint[0][0], Properties.spawnPoint[0][1], Properties.tank2Velocity, Properties.tank2MaxHealth);
                enemies.add(tank2);
            }
            if (dem >= 40 && dem < 50) {
                Plane plane2 = new Plane(playFieldLayer, img5, Properties.spawnPoint[0][0], Properties.spawnPoint[0][1], Properties.plane2Velocity, Properties.plane2MaxHealth);
                enemies.add(plane2);
            }
            if (dem >= 50) {
                wave[3] = false;
                wave[4] = true;
                waves++;
            }
        }

    }


    private boolean checkTowerLocation(double x, double y, double[][] towerLocation){
        boolean flag = false;

        double xCode = x;
        double yCode = y;
        for(int i = 0; i < towerLocation.length; i++){
            if(xCode >= towerLocation[i][0] && xCode <= (towerLocation[i][0] + 64)){
                if (yCode >= towerLocation[i][1] && yCode <= (towerLocation[i][1] + 64)){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    private int returnTowerLocation(double x, double y, double[][] towerLocation){
        int index = 0;
        double xCode = x;
        double yCode = y;
        for(int i = 0; i < towerLocation.length; i++){
            if(xCode >= towerLocation[i][0] && xCode <= (towerLocation[i][0] + 64)){
                if (yCode >= towerLocation[i][1] && yCode <= (towerLocation[i][1] + 64)){
                    index = i;
                    break;
                }
            }
        }

        return index;
    }

    private void createTowerLevel1(double x, double y){
        Image img = cannonImage;

        double xCode = x;
        double yCode = y;

        CannonTower cannonTower = new CannonTower(playFieldLayer, img, xCode, yCode, 0);

        towers.add(cannonTower);

        // new
        this.checkTowerLocationHasTower[returnTowerLocation(x, y, Properties.towerLocation)] = true;
        this.checkTowerLocationHasCannonTowerLevel1[returnTowerLocation(x, y, Properties.towerLocation)] = true;
    }

    private void createTowerLevel2(double x, double y){
        Image img = cannon2Image;

        double xCode = x;
        double yCode = y;

        //new
        for(Tower tower : towers){
            if(tower.getCenterX() == xCode + 32 && tower.getCenterY() == yCode + 32){
                tower.setDamage(Properties.cannonTower2Damage);
                tower.setRange(Properties.cannonTower2Range);
                tower.setImage(img);
                tower.setBulletColor(Color.GOLD);
            }
        }

    }

    private void createRocketLevel1(double x, double y){
        double xCode = x;
        double yCode = y;
        Image img = rocketImage;

        RocketTower rocketTower = new RocketTower(playFieldLayer, img, xCode, yCode, 0);

        towers.add(rocketTower);

        //new
        this.checkTowerLocationHasTower[returnTowerLocation(x, y, Properties.towerLocation)] = true;
        this.checkTowerLocationHasRocketTowerLevel1[returnTowerLocation(x, y, Properties.towerLocation)] = true;
    }

    private void createRocketLevel2(double x, double y){
        double xCode = x;
        double yCode = y;
        Image img = rocket2Image;

        // new
        for(Tower tower : towers){
            if(tower.getCenterX() == xCode + 32 && tower.getCenterY() == yCode + 32){
                tower.setDamage(Properties.rocketTower2Damage);
                tower.setRange(Properties.rocketTower2Range);
                tower.setImage(img);
                tower.setBulletColor(Color.RED);
            }
        }
    }

    private void checkEnemyAtDefensePoint(List<Enemy> enemies){
        for(Enemy enemy : enemies){
            if(enemy.getY() >= Properties.defensePoint[0][1]){
                heart -= enemy.costLives;
            }
        }
    }

    private void updateUI(){
        if (waves < 5) UIText.setText("Gold: " + Integer.toString(gold) + "\n" + "Score: " + Integer.toString(score) + "\n" + "Lives: " + Integer.toString(heart) + "\n" + "Wave: " + Integer.toString(waves + 1));
        if (waves >= 5) UIText.setText("Gold: " + Integer.toString(gold) + "\n" + "Score: " + Integer.toString(score) + "\n" + "Lives: " + Integer.toString(heart) + "\n" + "Wave: " + Integer.toString(5));
    }

    private void increaseGoldAndScore(List<Enemy> enemies){
        for(Enemy enemy : enemies){
            if(!enemy.isAlive()){
                score += enemy.killingScore;
                gold += enemy.killingGold;
            }
        }
    }

    private boolean checkWinningCondition(){
        if(waves == 20){
            return true;
        }
        else return false;
    }

    private boolean checkLosingCondition(){
        if(heart <= 0){
            return true;
        }
        else{
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
