package Engine;

import java.awt.*;

public class Missile extends GameObject{
    protected boolean hit;
    public Missile(int speed, int health, int damage, int widht, int height, int x, int y) {
        super(speed, health, damage, widht, height, x, y);
        hit = false;
    }

    @Override
    public void update(double d) {
        y -= speed -d;
        hitBox.setLocation((int)x,(int)y);
    }

    @Override
    public void update(double d, InputHandler hp) {
        y -= speed -d;
        hitBox.setLocation((int)x,(int)y);
    }

    public void hit(GameObject enemy){
        if(this.hitBox.intersects(enemy.hitBox)) {
            enemy.takeDmg(dmg);
            hit = true;
        }


    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect((int)x,(int)y, dim.width, dim.height);
    }
}