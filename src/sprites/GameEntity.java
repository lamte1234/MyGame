package sprites;

interface GameEntity{
    public abstract void move();
    public abstract void update();
    public abstract double getCenterX();
    public abstract double getCenterY();
}