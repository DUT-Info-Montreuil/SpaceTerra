package vue;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import modele.Player;

public class PlayerView {

    private ImageView spritePlayer;
    private Player player;
    Pane panneauDeJeu;

    public PlayerView(Player player, Pane panneauDeJeu){
        this.player = player;
        this.panneauDeJeu = panneauDeJeu;
        //this.spritePlayer = new ImageView(player.getImage());
        //this.spritePlayer.xProperty().bind(player.getHitbox().getX().subtract(player.getImage().getWidth()/2 - player.getHitbox().getWidth()/2));
        //this.spritePlayer.yProperty().bind(player.getHitbox().getY().subtract(player.getImage().getHeight() - player.getHitbox().getHeight()));
    }
    public void displayPlayer(){
        //panneauDeJeu.getChildren().add(spritePlayer);
    }
}
