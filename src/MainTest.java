import Engine.GameLoop;
import Engine.GameObject;
import Engine.GameWindow;
import Engine.Player;
import Objects.Enemy;

import java.io.IOException;
import java.util.ArrayList;

public class MainTest {
    public static void main(String[] args) throws IOException {

        GameWindow gw = new GameWindow();
        Enemy e1 = new Enemy(20,10,10,100,90,100,120);
        Enemy e2 = new Enemy(20,10,10,100,90,550,40);
        Enemy e3 = new Enemy(20,10,10,100,90,1400,0);
        Player p = new Player(800,10,10,100,90,800,800);
        ArrayList<GameObject> list = new ArrayList<GameObject>();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        GameLoop gl = new GameLoop(gw,list,p);
        Thread t = new Thread(gl);
        t.start();


    }
}
