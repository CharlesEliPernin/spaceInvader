package Engine;

import java.awt.*;

/**
 * Classe abstaite qui gere les objets du jeu.
 */
public abstract class GameObject {
    protected double x;
    protected  double y;
    protected Dimension dim;
    protected Rectangle hitBox;
    protected int speed;
    protected int health;
    protected int dmg;

    /**
     * constructeur de la classe
     * @param speed
     * @param health
     * @param damage
     * @param widht
     * @param height
     * @param x
     * @param y
     */
    public GameObject(int speed, int health, int damage, int widht, int height, int x, int y){
        this.speed = speed;
        this.health = health;
        this.dmg = damage;
        this.dim = new Dimension(widht,height);
        this.x = x;
        this.y = y;
        this.hitBox = new Rectangle(x,y,widht,height);
    }


    public void update(double d){
        y += speed*d;
        hitBox.setLocation((int)x,(int)y);
    }
    public void update(double d, InputHandler hp){
        y += speed*d;
        hitBox.setLocation((int)x,(int)y);
    }

    public abstract void render(Graphics g);

    public boolean isAlive(){
        return health > 0;
    }

    public void takeDmg(int i){
        this.health -= i;
    }




}
