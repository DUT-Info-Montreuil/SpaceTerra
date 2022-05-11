package les_gens.spaceterra;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, escapePressed, eKeyPressed;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_Z) {
            upPressed = true;
        }
        else if(code == KeyEvent.VK_S) {
            downPressed = true;
        }
        else if(code == KeyEvent.VK_Q) {
            leftPressed = true;
        }
        else if(code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        else if(code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
        else if(code == KeyEvent.VK_ESCAPE) {
            System.out.println("a");
            escapePressed = true;
        }
        else if(code == KeyEvent.VK_E){
            eKeyPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_Z) {
            upPressed = false;
        }
        else if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        else if(code == KeyEvent.VK_Q) {
            leftPressed = false;
        }
        else if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        else if(code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
        else if(code == KeyEvent.VK_ESCAPE) {
            escapePressed = false;
        }
        else if(code == KeyEvent.VK_E){
            eKeyPressed = true;
        }
    }

}
