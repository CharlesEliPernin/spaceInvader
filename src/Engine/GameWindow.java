package Engine;

import javax.swing.*;
import java.awt.*;

/**
 * Classe qui gère la fenêtre et ses paramètres.
 */
public class GameWindow {
    private JFrame fenetre;
    private Canvas canvas;
    private InputHandler hip;

    /**
     * Methode principale de la classe qui genere la fenetre
     */
    public GameWindow(){
        //init des variables
        this.fenetre = new JFrame("SpaceInvader");
        fenetre.setUndecorated(true);
        this.canvas = new Canvas();
        this.hip = new InputHandler();

        //creation de la fenetre
        fenetre.setSize(1920,1080);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);
        fenetre.setPreferredSize(new Dimension(1920,1080));
        //set la fenetre dans la dimension preferee
        fenetre.pack();

        // gestion du canvas pour l'affichage
        canvas.setSize(new Dimension(1920,1080));
        canvas.setBackground(Color.BLUE);
        fenetre.add(canvas);

        canvas.addKeyListener(hip);
        canvas.setFocusable(true);
        canvas.requestFocus();


        GraphicsDevice[] s = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        s[0].setFullScreenWindow(fenetre);

    }

    /**
     * Methode pour afficher la fenetre
     */
    public void displayWindow(){
        fenetre.setVisible(true);
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public InputHandler getHip(){
        return hip;
    }



}
