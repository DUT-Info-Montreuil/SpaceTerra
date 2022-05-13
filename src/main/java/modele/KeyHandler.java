package modele;

import javafx.scene.Scene;

public class KeyHandler {

   public static void keyTyped(Scene scene) {
       scene.setOnKeyTyped(e -> {
           if(e.getCharacter().equals("d")) {
               System.out.println("Key Pressed: avancer : " + e.getCharacter());
           }
           else if (e.getCharacter().equals("q")) {
               System.out.println("Key Pressed: reculer : " + e.getCharacter());
           }

       });
   }



}
