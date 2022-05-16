package vue;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import modele.Player;

public class PlayerView {

    private ImageView spritePlayer;
    private Player player;
    Pane panneauDeJeu;

    private final double x = 10;

    private final double y = 2030;

    public PlayerView(Player player, Pane panneauDeJeu){
        this.player = player;
        this.player.setX(x);
        this.player.setY(y);
        this.panneauDeJeu = panneauDeJeu;
        this.spritePlayer = new ImageView(player.getImage());
        this.spritePlayer.xProperty().bind(player.xProperty());
        this.spritePlayer.yProperty().bind(player.yProperty());
    }
    public void displayPlayer(){
        panneauDeJeu.getChildren().add(spritePlayer);
    }


}