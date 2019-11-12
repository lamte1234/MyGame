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

    public static final double planeMaxHealth = 260;
    public static final double planeVelocity = 4.0;

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
    public static final double cannonTower1ReserveRotationVelocity = 10;
    public static final double cannon1Gold = 200;

    public static final double rocketTower1Range = 300;
    public static final double rocketTower1Damage = 2.0;
    public static final double rocketTower1ReserveRotationVelocity = 10;
    public static final int rocket1Gold = 400;


    public static final int startingGold = 600;


    public static final int hearts = 20;

    public static final int waves[][] = {{23, 0, 0},
                                         {18, 5, 0},
                                         {15, 5, 3},
                                         {10, 8, 5},
                                         {5, 10, 8}};

}