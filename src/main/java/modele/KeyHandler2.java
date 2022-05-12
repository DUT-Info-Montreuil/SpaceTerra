package modele;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class KeyHandler2 {

   public static void keyTyped(Scene scene) {
       scene.setOnKeyTyped(e -> {
           if (e.getCode().equals("d")) {
               System.out.println("Key Pressed: " + e.getCode());
           }
           else if (e.getCode().equals("q")) {
               System.out.println("Key Pressed: " + e.getCode());
           }
       });
   }



}
