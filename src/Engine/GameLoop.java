package Engine;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class GameLoop implements Runnable{
    private boolean running = false;
    private GameWindow gw;
    private BufferStrategy bs;
    private ArrayList<GameObject> objects;
    private Player p;

    public GameLoop(GameWindow g, ArrayList<GameObject> objs, Player p) {
        this.gw = g;
        this.bs = createBufferStrategy();
        this.objects = objs;
        this.p = p;
    }

    public BufferStrategy createBufferStrategy() {
        Canvas c = gw.getCanvas();
        c.createBufferStrategy(2);
        return c.getBufferStrategy();
    }

    public void update(double s) {
        p.update(s, gw.getHip());
        for (GameObject go : objects){
            for(Missile m : p.getMissiles()){
                m.hit(go);
                if (go.isAlive()){
                    go.update(s, gw.getHip());
                }

            }

        }
        objects.removeIf(gameObject -> !gameObject.isAlive());
        p.getMissiles().removeIf(Missile -> Missile.hit);

    }
    public void render(){
        Graphics graph = bs.getDrawGraphics();
        graph.setColor(Color.BLACK);
        graph.clearRect(0,0,1920,1080);

        for (GameObject go : objects){
            go.render(graph);
        }
        p.render(graph);

        graph.dispose();
        bs.show();
    };

    public void run() {
        double accumulator = 0.0;
        double step = 1.0 / 60.0;
        double lastTime = System.nanoTime();
        double margin = 2.0;

        gw.displayWindow();
        running = true;

        while (running) {
            long currentTime = System.nanoTime();
            double frameTime = (currentTime - lastTime) / 1_000_000_000.0;


            accumulator += frameTime;

            while (accumulator >= step) {
                update(step);
                accumulator -= step;
            }

            render();

            lastTime = currentTime;
            currentTime = System.nanoTime();
            frameTime = (currentTime - lastTime) / 1_000_000_000.0;

            try {
                double remaining = (step - frameTime) * 1000.0;
                if (remaining > 0) {
                    Thread.sleep(Math.round(remaining - margin));
                }
                remaining -= margin;
                currentTime = System.nanoTime();
                while (remaining >0){
                    remaining -= (System.nanoTime() - currentTime) / 1_000_000.0;
                    currentTime = System.nanoTime();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
