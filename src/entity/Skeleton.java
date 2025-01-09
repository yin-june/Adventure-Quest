package entity;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Skeleton extends Monster{
    public Skeleton(int addedHp, int addedAttack){
        super("Skeleton", 50+addedHp, 20+addedAttack); //type, HP, attackPower
        getMonsterImage();
    }

    @Override
    public void getMonsterImage() {
        try {
            leftImage = ImageIO.read(new File("src/image/skeleton1.png"));
            rightImage = ImageIO.read(new File("src/image/skeleton2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
