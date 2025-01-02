package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Item {
    private String name;
    private int x;        // x-coordinate 
    private int y;        // y-coordinate 
    private BufferedImage itemImage; // item image 

    // 构造方法
    public Item(JPanel gp, String name) throws IOException {
        this.name = name;
        direction(); // random direction
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }
    
    public BufferedImage getItemImage() {
        return itemImage;
    }

    public void setItemImage(BufferedImage itemImage) {
        this.itemImage = itemImage;
    }

    // random direction
    public void direction() {
        Random r = new Random();
        this.x = r.nextInt(GamePanel.SCREEN_WIDTH - 50); 
        this.y = r.nextInt(GamePanel.SCREEN_HEIGHT - 50); 
    }

    // draw item 
    public void draw(Graphics2D g) {
        try {
            switch (name){
                case "axe":
                    itemImage = ImageIO.read(new File("src/image/axe.png"));
                    break;
                case "dagger":
                    itemImage = ImageIO.read(new File("src/image/dagger.png"));
                    break;
                case "potion":
                    itemImage = ImageIO.read(new File("src/image/potion.png"));
                    break;
                default:
                    System.out.println("Don't have this item!");
            }
        } catch (IOException e) {
            System.out.println("Image file not exist!");
        }
        g.drawImage(itemImage, x, y, null);

    }
}
