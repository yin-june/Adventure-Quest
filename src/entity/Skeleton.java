package entity;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Skeleton extends Monster{
    public Skeleton(){
        super("Skeleton", 30, 10); 
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
