package entity;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Zombie extends Monster{
    public Zombie(int addedHp, int addedAttack){
        super("Zombie", 60+addedHp,30+addedAttack); //type, HP, attackPower
        getMonsterImage();
    }

    @Override
    public void getMonsterImage() {
        try {
            leftImage = ImageIO.read(new File("src/image/zombie_left.png"));
            rightImage = ImageIO.read(new File("src/image/zombie_right.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
