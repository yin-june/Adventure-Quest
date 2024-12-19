package entity;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel; 
import main.KeyHandler; 

public class Mage extends Hero{
    
    public Mage(GamePanel gp, KeyHandler keyH){
        super(gp, keyH); 
        
    }
    
    @Override
    public void getPlayerImage(){
        try{
            downImage = ImageIO.read(getClass().getResourceAsStream("/player/mage_down.png")); 
            upImage = ImageIO.read(getClass().getResourceAsStream("/player/mage_up.png")); 
            leftImage = ImageIO.read(getClass().getResourceAsStream("/player/mage_left.png")); 
            rightImage = ImageIO.read(getClass().getResourceAsStream("/player/mage_right.png")); 
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}