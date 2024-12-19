package entity;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Warrior extends Hero {
    public Warrior(GamePanel gp, KeyHandler keyH) {
        super(gp, keyH);
    }

    @Override
    public void getPlayerImage() {
        try {
            downImage = ImageIO.read(getClass().getResourceAsStream("/player/warrior_down.png"));
            upImage = ImageIO.read(getClass().getResourceAsStream("/player/warrior_up.png"));
            leftImage = ImageIO.read(getClass().getResourceAsStream("/player/warrior_left.png"));
            rightImage = ImageIO.read(getClass().getResourceAsStream("/player/warrior_right.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
