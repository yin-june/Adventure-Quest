package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Item {
    private String name = "dagger";
    private int x;        // 物品的 x 坐标
    private int y;        // 物品的 y 坐标
    private BufferedImage itemImage; // 物品图片

    // 构造方法
    public Item(JPanel gp, String name) throws IOException {
        this.name = name;
        direction(); // 随机生成初始位置
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

    // 随机方向（随机位置）
    public void direction() {
        Random r = new Random();
        this.x = r.nextInt(GamePanel.SCREEN_WIDTH); // 假设屏幕宽度为 768
        this.y = r.nextInt(GamePanel.SCREEN_HEIGHT); // 假设屏幕高度为 576
    }

    // 绘制物品
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
