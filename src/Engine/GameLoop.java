package Engine;

import Objects.Missile;
import Objects.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

/**
 * Classe principale du jeu, c'est elle qui gère la boucle d'execution du jeu.
 */
public class GameLoop implements Runnable{
    private boolean running = false;
    private GameWindow gw;
    private BufferStrategy bs;
    private ArrayList<GameObject> objects;
    private Player p;

    /**
     * Constrcuteur de la classe
     * @param g la fenetre du jeu
     * @param objs la liste des objets en jeu
     * @param p le joueur
     */
    public GameLoop(GameWindow g, ArrayList<GameObject> objs, Player p) {
        this.gw = g;
        this.bs = createBufferStrategy();
        this.objects = objs;
        this.p = p;
    }

    /**
     * Methode qui permet de creer la bufferStrategy
     * @return
     */
    public BufferStrategy createBufferStrategy() {
        Canvas c = gw.getCanvas();
        c.createBufferStrategy(2);
        return c.getBufferStrategy();
    }

    /**
     * Methode qui permet de mettre a jour les objets et le joueur
     * @param s
     */
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
        // objets detuits sont supprimes de la liste pour economiser des ressources
        objects.removeIf(gameObject -> !gameObject.isAlive());
        p.getMissiles().removeIf(Missile -> Missile.hit);

    }

    /**
     * Methode appellee apres render() pour mettre a jour la fenetre
     */
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

    /**
     * Methode qui permet de lancer le jeu
     */
    public void run() {
        double accumulator = 0.0;
        // on vise 60 fps
        double step = 1.0 / 60.0;
        double lastTime = System.nanoTime();
        // marge de temps entre deux frames pour la fluidite
        double margin = 2.0;

        gw.displayWindow();
        running = true;

        // boucle du jeu
        while (running) {
            //calcul du temps de frame
            long currentTime = System.nanoTime();
            double frameTime = (currentTime - lastTime) / 1_000_000_000.0;
            //on ajoute le temps de frame a l'accumulateur
            accumulator += frameTime;

            //on met a jour le jeu
            while (accumulator >= step) {
                update(step);
                accumulator -= step;
            }

            //mise a jour de la fenetre
            render();

            // on calule a nouveau le temps de frame pour ajuster le temps a dormir en fonction du temps d'execution des methodes du jeu
            lastTime = currentTime;
            currentTime = System.nanoTime();
            frameTime = (currentTime - lastTime) / 1_000_000_000.0;

            try {
                // temps restant a dormir avant la prochaine frame
                double remaining = (step - frameTime) * 1000.0;
                // on utilise thread sleep pour passer le plus gros du temps car ce n'est pas précis
                if (remaining > 0) {
                    Thread.sleep(Math.round(remaining - margin));
                }
                remaining -= margin;
                currentTime = System.nanoTime();
                //pour le reste du temps on boucle car plus précis plus gourmand en ressource CPU
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
