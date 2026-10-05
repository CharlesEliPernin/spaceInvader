package Engine;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private JFrame fenetre;
    private Canvas canvas;
    private InputHandler hip;

    public GameWindow(){
        this.fenetre = new JFrame("SpaceInvader");
        fenetre.setUndecorated(true);
        this.canvas = new Canvas();
        this.hip = new InputHandler();

        fenetre.setSize(1920,1080);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);
        fenetre.setPreferredSize(new Dimension(1920,1080));
        fenetre.pack();

        canvas.setSize(new Dimension(1920,1080));
        canvas.setBackground(Color.BLUE);
        fenetre.add(canvas);

        canvas.addKeyListener(hip);
        canvas.setFocusable(true);
        canvas.requestFocus();


        GraphicsDevice[] s = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        s[0].setFullScreenWindow(fenetre);

    }

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
