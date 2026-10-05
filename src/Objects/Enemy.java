package Objects;

import Engine.GameLoop;
import Engine.GameObject;
import Engine.InputHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Enemy extends GameObject {

    BufferedImage sprite;
    public Enemy(int speed, int health, int damage, int widht, int height, int x, int y) throws IOException {
        super(speed, health, damage, widht, height, x, y);
            sprite = ImageIO.read(new File("/home/LeSaucissonSec/Desktop/BUT_INFO/Coo/SpaceInvader/enemy.png"));
    }

    public void render(Graphics g) {
        g.drawImage(sprite,(int)x,(int)y,100,90,null);
    }

}
