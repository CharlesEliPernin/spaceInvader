package Engine;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe reprenant le joueur du jeu.
 */
public class Player extends GameObject {

    private ArrayList<Missile> missiles;
    private double cdr;

    private BufferedImage sprite;

    /**
     * Constructeur de la classe
     * @param speed
     * @param health
     * @param damage
     * @param widht
     * @param height
     * @param x
     * @param y
     * @throws IOException
     */
    public Player(int speed, int health, int damage, int widht, int height, int x, int y) throws IOException {
        super(speed, health, damage, widht, height, x, y);
        sprite = ImageIO.read(new File("/home/LeSaucissonSec/Desktop/BUT_INFO/Coo/SpaceInvader/player.png"));
        missiles = new ArrayList<Missile>();
        cdr = 0.0;
    }

    @Override
    public void update(double d, InputHandler hp) {
        if (hp.isKeyPressed(KeyEvent.VK_LEFT)){
            x = Math.max(0,Math.min(x - speed * d, 1820));
        }
        if (hp.isKeyPressed(KeyEvent.VK_RIGHT)){
            x = Math.max(0,Math.min(x + speed * d, 1820));
        }
        if (hp.isKeyPressed(KeyEvent.VK_UP)){
            y = Math.max(0,Math.min(y - speed * d, 990));
        }
        if (hp.isKeyPressed(KeyEvent.VK_DOWN)){
            y = Math.max(0,Math.min(y + speed * d, 990));
        }

        for(Missile m : missiles){
            m.update(d);
        }

        //gestion des tirs
        cdr = (cdr+d);
        if(cdr >2){
            cdr%=2;
            missiles.add(new Missile(30,9999,dmg,5,20,(int)x+48,(int)y-5));
        }

        hitBox.setLocation((int)x,(int)y);


    }

    public void render(Graphics g) {
        g.drawImage(sprite,(int)x,(int)y,100,90,null);
        for(Missile m : missiles){
            m.render(g);
        }
    }


    public ArrayList<Missile> getMissiles(){
        return missiles;
    }

    public void removeMissile(Missile m){
        missiles.remove(m);
    }
}
