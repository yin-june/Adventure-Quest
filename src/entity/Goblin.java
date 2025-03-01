package entity;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Goblin extends Monster{
    public Goblin(int addedHp, int addedAttack){
        super("Goblin", 70+addedHp, 40+addedAttack); //type, HP, attackPower 
        getMonsterImage();
    }

    @Override
    public void getMonsterImage() {
        try {
            leftImage = ImageIO.read(new File("src/image/goblin_left.png"));
            rightImage = ImageIO.read(new File("src/image/goblin_right.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
