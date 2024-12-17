/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Warrior extends entity {
    GamePanel gp; 
    KeyHandler keyH; 
    
    public Warrior(GamePanel gp, KeyHandler keyH){
        this.gp = gp; 
        this.keyH = keyH; 
        
        setDefaultValues(); 
        getPlayerImage(); 
    }
    
    public void setDefaultValues(){
        x = 100; 
        y = 100; 
        speed = 4; 
        direction = "down"; 
    }
    
    public void getPlayerImage(){
        try{
            
            warrior_down = ImageIO.read(getClass().getResourceAsStream("/player/warrior_down.png")); 
            warrior_up = ImageIO.read(getClass().getResourceAsStream("/player/warrior_up.png")); 
            warrior_left = ImageIO.read(getClass().getResourceAsStream("/player/warrior_left.png")); 
            warrior_right = ImageIO.read(getClass().getResourceAsStream("/player/warrior_right.png")); 
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void update(){
        if(keyH.upPressed==true){
            direction = "up"; 
            y -= speed;    
        }
        else if (keyH.downPressed==true){
            direction = "down"; 
            y += speed; 
        }
        else if (keyH.leftPressed ==true){
            direction = "left"; 
            x -= speed; 
        }
        else if(keyH.rightPressed==true){
            direction = "right"; 
            x += speed; 
        }
    }
    public void draw(Graphics2D g2){
        //g2.setColor(Color.MAGENTA);
        //g2.fillRect(x,y, gp.tileSize, gp.tileSize); // x,y, width, height
        BufferedImage image = null; 
        
        switch(direction){
            case("up"):
                image = warrior_up; 
                break; 
            case("down"):
                image = warrior_down; 
                break; 
            case("left"):
                image = warrior_left; 
                break;
            case("right"):
                image = warrior_right; 
                break;  
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
