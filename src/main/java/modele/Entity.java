package modele;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public abstract class Entity {

    private int life;
    private int speed;
    private Hitbox hitbox;
    private Image image;
    private final int jumpHeight = 20;
    public int vitesseY = jumpHeight;
    private boolean isJumping = false;

    public static double g = 5;

    private boolean gravity;

    public Terrain getTerrain() {
        return terrain;
    }

    private Terrain terrain;

    public Entity(int vie, int vitesse, Hitbox hitbox, String path, Terrain terrain) {
        this.life = vie;
        this.speed = vitesse;
        this.hitbox = hitbox;
        this.image = new Image(String.valueOf(getClass().getResource(path)));
        this.terrain = terrain;
        gravity = true;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(String path) {
        this.image = new Image(String.valueOf(getClass().getResource(path)));
    }


    public boolean sideRightCollisions(Pane panneau) {
        /*
        if ((hitbox.getY().intValue() > block.getHitY() && hitbox.getY().intValue() <= block.getHitY() + block.getTile().getHitbox().getHeight()) || (hitbox.getY().intValue() + hitbox.getHeight() > block.getHitY() && hitbox.getY().intValue() + hitbox.getHeight() <= block.getHitY() + block.getTile().getHitbox().getHeight())) {
            if (hitbox.getX().intValue() <= block.getHitX() + 2 + block.getTile().getHitbox().getWidth() && hitbox.getX().intValue() >= block.getHitX() + block.getTile().getHitbox().getWidth() - block.getInsideOffset()) { // cote droit d'un block
                hitbox.setX(block.getHitX() + block.getTile().getHitbox().getWidth() + 2);
                return -1; // Player blocked on left
            } else if (hitbox.getX().intValue() + hitbox.getWidth() >= block.getHitX() - 2 && hitbox.getX().intValue() + hitbox.getWidth() <= block.getHitX() + block.getInsideOffset()) { // cote gauche d'un block
                hitbox.setX(block.getHitX() - hitbox.getWidth() - 2);
                return 1; // Player blocked on right
            }
        }
         */


        Block blockVerifBottomRight = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth() + this.speed, hitbox.getY().intValue() + hitbox.getHeight() - 1); // on enlève 1 pixel pour pas qu'il détecte le block de diagonal bas droite
        Block blockVerifBottomQuarterRight = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth() + this.speed, hitbox.getY().intValue() + hitbox.getHeight() * 3 / 4); //met un point en bas à droite à 3/4 de la hauteur du joueur
        Block blockVerifUpRight = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth() + this.speed, hitbox.getY().intValue() + 1); // on ajoute 1 pixel pour pas qu'il détecte le block de diagonal haut gauche
        Block blockVerifUpQuarterRight = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth() + this.speed, hitbox.getY().intValue() + hitbox.getHeight() * 1 / 10); //met un point en haut à droite à 1/10 de la hauteur du joueur (car 1/4 était trop bas pour la tête du joueur)


        if (blockVerifBottomRight != null && blockVerifBottomQuarterRight != null) {
            if (blockVerifBottomQuarterRight.getTile().getHitbox().isSolid()) {
                hitbox.setX(hitbox.getX().intValue() - ((hitbox.getX().intValue() + hitbox.getWidth()) - blockVerifBottomQuarterRight.getX()));
                Rectangle r = new Rectangle();
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.ALICEBLUE);
                r.setX(blockVerifBottomRight.getHitX());
                r.setY(blockVerifBottomRight.getHitY());
                r.setWidth(32);
                r.setHeight(32);
                panneau.getChildren().add(r);
                //  System.out.println("basDroite");
                //hitbox.setX(blockVerifBottomLeft.getHitX() + blockVerifBottomLeft.getTile().getHitbox().getWidth() + 2);
                return true;
            }
        }


        if (blockVerifUpRight != null && blockVerifUpQuarterRight != null) {
            if (blockVerifUpQuarterRight.getTile().getHitbox().isSolid()) {
                hitbox.setX(hitbox.getX().intValue() - ((hitbox.getX().intValue() + hitbox.getWidth()) - blockVerifUpQuarterRight.getX()));
                Rectangle r = new Rectangle();
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.BLACK);
                r.setX(blockVerifUpRight.getHitX());
                r.setY(blockVerifUpRight.getHitY());
                r.setWidth(32);
                r.setHeight(32);
                panneau.getChildren().add(r);
                //System.out.println("hautDroite");
                //hitbox.setX(blockVerifBottomLeft.getHitX() + blockVerifBottomLeft.getTile().getHitbox().getWidth() + 2);
                return true;
            }
        }




/*
        if (blockVerifUpLeft != null) {
            if (blockVerifUpLeft.getTile().getHitbox().isSolid()) {


                /*
                Rectangle r = new Rectangle();
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.BLUE);
                r.setX(blockVerifUpLeft.getHitX());
                r.setY(blockVerifUpLeft.getHitY());
                r.setWidth(32);
                r.setHeight(32);
                panneau.getChildren().add(r);
                 */
        // hitbox.setX(blockVerifUpLeft.getHitX() + blockVerifUpLeft.getTile().getHitbox().getWidth() + 2);
        /*
                return -1;
            }
        }

        if (blockVerifBottomRight != null) {
            if (blockVerifBottomRight.getTile().getHitbox().isSolid()) {
                // hitbox.setX(blockVerifBottomRight.getHitX() - hitbox.getWidth() - 2);
                hitbox.setX(hitbox.getX().intValue() - ((hitbox.getX().intValue() + hitbox.getWidth()) - blockVerifBottomRight.getHitX()) - 1);
                Rectangle r = new Rectangle();
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.BURLYWOOD);
                r.setX(blockVerifBottomRight.getHitX());
                r.setY(blockVerifBottomRight.getHitY());
                r.setWidth(32);
                r.setHeight(32);
                panneau.getChildren().add(r);
                System.out.println("basDroit");
                return -1;
            }
        }
        if (blockVerifUpRight != null) {
            if (blockVerifUpRight.getTile().getHitbox().isSolid()) {
                Rectangle r = new Rectangle();
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.FUCHSIA);
                r.setX(blockVerifUpRight.getHitX());
                r.setY(blockVerifUpRight.getHitY());
                panneau.getChildren().add(r);
                // hitbox.setX(blockVerifUpRight.getHitX() - hitbox.getWidth() - 2);
                return -1;
            }
        }
*/
        return false;
    }

    public boolean sideLeftCollision(Pane panneau) {
        Block blockVerifBottomLeft = terrain.getBlock(hitbox.getX().intValue() - this.speed, hitbox.getY().intValue() + hitbox.getHeight()); // on ajoute 1 pixel pour pas qu'il détecte le block de diagonal bas gauche
        Block blockVerifBottomQuarterLeft = terrain.getBlock(hitbox.getX().intValue() - this.speed, hitbox.getY().intValue() + hitbox.getHeight() * 3 / 4); //met un point en bas à gauche à 3/4 de la hauteur du joueur
        Block blockVerifUpLeft = terrain.getBlock(hitbox.getX().intValue() - this.speed, hitbox.getY().intValue() + 1); // on ajoute 1 pixel pour pas qu'il détecte le block de diagonal haut droite
        Block blockVerifUpQuarterLeft = terrain.getBlock(hitbox.getX().intValue() - this.speed, hitbox.getY().intValue() + hitbox.getHeight() * 1 / 10); //met un point en haut à gauche à 1/10 de la hauteur du joueur (car 1/4 était trop bas pour la tête du joueur)

        if (blockVerifBottomLeft != null && blockVerifBottomQuarterLeft != null) {
            if (blockVerifBottomQuarterLeft.getTile().getHitbox().isSolid()) {
                hitbox.setX(hitbox.getX().intValue() + ((blockVerifBottomQuarterLeft.getHitX() + blockVerifBottomQuarterLeft.getTile().getHitbox().getWidth()) - hitbox.getX().intValue()));
                //System.out.println(terrain.getBlocks().indexOf(blockVerifBottomLeft));
                Rectangle r = new Rectangle();
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.BLACK);
                r.setX(blockVerifBottomLeft.getHitX());
                r.setY(blockVerifBottomLeft.getHitY());
                r.setWidth(32);
                r.setHeight(32);
                panneau.getChildren().add(r);
                //System.out.println("basGauche");
                //hitbox.setX(blockVerifBottomLeft.getHitX() + blockVerifBottomLeft.getTile().getHitbox().getWidth() + 2);
                return true;
            }
        }

        if (blockVerifUpLeft != null && blockVerifUpQuarterLeft != null) {
            if (blockVerifUpQuarterLeft.getTile().getHitbox().isSolid()) {
                hitbox.setX(hitbox.getX().intValue() + ((blockVerifUpQuarterLeft.getHitX() + blockVerifUpQuarterLeft.getTile().getHitbox().getWidth()) - hitbox.getX().intValue()));
                Rectangle r = new Rectangle();
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.BLACK);
                r.setX(blockVerifUpLeft.getHitX());
                r.setY(blockVerifUpLeft.getHitY());
                r.setWidth(32);
                r.setHeight(32);
                panneau.getChildren().add(r);
                //System.out.println("hautGauche");
                //hitbox.setX(blockVerifBottomLeft.getHitX() + blockVerifBottomLeft.getTile().getHitbox().getWidth() + 2);
                return true;
            }
        }

        return false;

    }

    public boolean upCollisions() {


        Block blockVerifUpQuarterLeft = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth() * 1 / 4, hitbox.getY().intValue() - this.vitesseY); // met un point en haut à 1/4 de la largeur du joueur
        Block blockVerifUpQuarterRight = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth() * 3 / 4, hitbox.getY().intValue() - this.vitesseY);// met un point en haut à 3/4 de la largeur du joueur
        Block blockVerifUpLeft = terrain.getBlock(hitbox.getX().intValue() + 1, hitbox.getY().intValue() - this.vitesseY); // on ajoute 1 pixel pour pas qu'il détecte le block de diagonal haut gauche
        Block blockVerifUpRight = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth() - 1, hitbox.getY().intValue() - this.vitesseY); // on enlève 1 pixel pour pas qu'il détecte le block de diagonal haut droite

        if (blockVerifUpLeft != null && blockVerifUpQuarterLeft != null) {
            if (blockVerifUpQuarterLeft.getTile().getHitbox().isSolid()) {
                hitbox.setY(hitbox.getY().intValue() + (hitbox.getY().intValue() - (blockVerifUpQuarterLeft.getHitY() + blockVerifUpQuarterLeft.getTile().getHitbox().getHeight())));
                return true;
            }
        }
        if (blockVerifUpRight != null && blockVerifUpQuarterRight != null) {
            if (blockVerifUpQuarterRight.getTile().getHitbox().isSolid()) {
                hitbox.setY(hitbox.getY().intValue() + (hitbox.getY().intValue() - (blockVerifUpQuarterRight.getHitY() + blockVerifUpQuarterRight.getTile().getHitbox().getHeight())));
                return true;
            }
        }


        return false;
    }


    public boolean isGrounded() {
/*
        if(block != null){
            if(hitbox.getY().intValue() + hitbox.getHeight() >= block.getHitY() && hitbox.getY().intValue() + hitbox.getHeight() <= block.getHitY() + block.getInsideOffset())
                if((hitbox.getX().intValue() >= block.getHitX() && hitbox.getX().intValue() < block.getHitX() + block.getTile().getHitbox().getWidth()) || (hitbox.getX().intValue() + hitbox.getWidth() >= block.getHitX() && hitbox.getX().intValue() + hitbox.getWidth() < block.getHitX() + block.getTile().getHitbox().getWidth())){
                    hitbox.getY().set(block.getHitY() - hitbox.getHeight());
                    return true;
                }
        }

        return false;
*/
        Block blockVerifBottomLeft = terrain.getBlock(hitbox.getX().intValue() + 1, hitbox.getY().intValue() + hitbox.getHeight() + (int) g);  // on ajoute 1 pixel pour pas qu'il détecte le block de diagonal bas gauche
        Block blockVerifBottomQuarterLeft = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth() * 1 / 4, hitbox.getY().intValue() + hitbox.getHeight() + (int) g); // met un point en bas à 1/4 de la largeur du joueur
        Block blockVerifBottomRight = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth() - 1, hitbox.getY().intValue() + hitbox.getHeight() + (int) g); // on enlève 1 pixel pour pas qu'il détecte le block de diagonal bas droite
        Block blockVerifBottomQuarterRight = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth() * 3 / 4, hitbox.getY().intValue() + hitbox.getHeight() + (int) g); // met un point en bas à 3/4 de la largeur du joueur

        if (blockVerifBottomQuarterLeft != null && blockVerifBottomLeft != null) {
            if (blockVerifBottomQuarterLeft.getTile().getHitbox().isSolid()) {
                hitbox.getY().set(blockVerifBottomLeft.getHitY() - hitbox.getHeight());
                // System.out.println("gravTrue");
                return true;
            }
        }

        if (blockVerifBottomQuarterRight != null && blockVerifBottomRight != null) {
            if (blockVerifBottomQuarterRight.getTile().getHitbox().isSolid()) {
                hitbox.getY().set(blockVerifBottomRight.getHitY() - hitbox.getHeight());
                // System.out.println("gravTrue");
                return true;
            }
        }
        // System.out.println("gravFalse");
        return false;
    }

    public void applyGrav() {
        gravity = true;
        hitbox.getY().set(hitbox.getY().getValue() + g);
        System.out.println(g);
        g += 0.05;
    }

    public abstract void movement(Player player, boolean leftCheck, boolean rightCheck);

    public int distanceToBlock(Block b) {
        double centerPX = this.hitbox.getX().intValue() + this.hitbox.getWidth() / 2; //centre du joueur en x
        double centerPY = this.hitbox.getY().intValue() + this.hitbox.getHeight() / 2; //centre du joueur en y
        double centerBX = b.getHitX() + b.getTile().getHitbox().getWidth() / 2; //centre du block en x
        double centerBY = b.getHitY() + b.getTile().getHitbox().getHeight() / 2; // centre du block en y
        double sqrt = Math.sqrt(Math.pow(centerBX - centerPX, 2.0) + Math.pow(centerBY - centerPY, 2.0));
        System.out.println("distance block : " + sqrt / 32);
        return (int) sqrt / 32; //distance euclidienne / 32 pour avoir une distance en blocks
    }

    public boolean isGravity() {
        return gravity;
    }

    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }

    public void jump() {
        gravity = false;
        this.isJumping = true;
        getHitbox().setY(getHitbox().getY().intValue() - --vitesseY);
        if (vitesseY <= 0) {
            stopJump();
        }
    }

    public void stopJump() {
        if (this.isGrounded()) {
            setJumping(false);
            setVitesseY(jumpHeight);
        }
    }

    public int getJumpHeight() {
        return jumpHeight;
    }

    public int getVitesseY() {
        return vitesseY;
    }

    public void setVitesseY(int vitesseY) {
        this.vitesseY = vitesseY;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }
}
