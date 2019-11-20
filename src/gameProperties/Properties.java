package gameProperties;

public class Properties {
    public static final double spawnPoint[][] = {{2 * 64 - 32, 12 * 64 - 32}};

    public static final double defensePoint[][] = {{12 * 64 - 32, 12 * 64 + 32}};

    public static final double path[][] = {{2 * 64 - 32, 12 * 64 + 32},
                                            {2 * 64 - 32, 8 * 64 - 32},
                                            {5 * 64 - 32, 8 * 64 - 32},
                                            {5 * 64 - 32, 3 * 64 - 32},
                                            {13 * 64 - 32, 3 * 64 - 32},
                                            {13 * 64 - 32, 2 * 64 - 32},
                                            {16 * 64 - 32, 2 * 64 - 32},
                                            {16 * 64 - 32, 8 * 64 - 32},
                                            {12 * 64 - 32, 8 * 64 - 32},
                                            {12 * 64 - 32, 12 * 64 + 32}};

    public static final double soldierMaxHealth = 120;
    public static final double soldierVelocity = 2.0;

    public static final double tankMaxHealth = 435;
    public static final double tankVelocity = 1.0;
    public static final double tank2MaxHealth = 435*2;
    public static final double tank2Velocity = 1.0;

    public static final double planeMaxHealth = 260;
    public static final double planeVelocity = 4.0;
    public static final double plane2MaxHealth = 260*2;
    public static final double plane2Velocity = 4.0;

    public static final double towerLocation[][] = {{3 * 64 , 9 * 64},
                                                    {4 * 64, 9 * 64},
                                                    {6 * 64 , 7 * 64},
                                                    {3 * 64 , 6 * 64},
                                                    {3 * 64 , 4 * 64},
                                                    {6 * 64 , 4 * 64},
                                                    {8 * 64 , 4 * 64},
                                                    {4 * 64 , 1 * 64},
                                                    {7 * 64 , 1 * 64},
                                                    {11 * 64 , 1 * 64},
                                                    {15 * 64 , 0 * 64},
                                                    {17 * 64, 1 * 64},
                                                    {14 * 64, 3 * 64},
                                                    {17 * 64, 5 * 64},
                                                    {14 * 64, 6 * 64},
                                                    {11 * 64, 6 * 64},
                                                    {13 * 64, 9 * 64},
                                                    {17 * 64, 8 * 64}};

    public static final double cannonTower1Range = 200;
    public static final double cannonTower1Damage = 1.0;
    public static final double cannonTower1ReverseRotationVelocity = 10;
    public static final double cannon1Gold = 200;

    public static final double cannonTower2Range = 250;
    public static final double cannonTower2Damage = 1.5;
    public static final double cannonTower2ReverseRotationVelocity = 10;
    public static final double cannon2Gold = 500;

    public static final double rocketTower1Range = 300;
    public static final double rocketTower1Damage = 2.0;
    public static final double rocketTower1ReverseRotationVelocity = 10;
    public static final int rocket1Gold = 400;

    public static final double rocketTower2Range = 400;
    public static final double rocketTower2Damage = 3.0;
    public static final double rocketTower2ReverseRotationVelocity = 10;
    public static final int rocket2Gold = 800;


    public static final int startingGold = 5000;


    public static final int hearts = 20;



}