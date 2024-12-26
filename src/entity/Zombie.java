package entity;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Zombie extends Monster{
    public Zombie(){
        super("Zombie", 40,10); 
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
