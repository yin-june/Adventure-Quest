package entity;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;


public class Archer extends Hero {
    public Archer(GamePanel gp, KeyHandler keyH) {
        super(gp, keyH);
    }

    @Override
    public void getPlayerImage() {
        try {
            downImage = ImageIO.read(getClass().getResourceAsStream("/player/archer_down.png"));
            upImage = ImageIO.read(getClass().getResourceAsStream("/player/archer_up.png"));
            leftImage = ImageIO.read(getClass().getResourceAsStream("/player/archer_left.png"));
            rightImage = ImageIO.read(getClass().getResourceAsStream("/player/archer_right.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
