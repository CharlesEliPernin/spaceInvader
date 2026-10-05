package Engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class InputHandler implements KeyListener
{
    private Set<Integer> keyPressed = new HashSet<>();

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keyPressed.add(keyEvent.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keyPressed.remove(keyEvent.getKeyCode());
    }

    public boolean isKeyPressed(int keyCode){
        return keyPressed.contains(keyCode);
    }


}
