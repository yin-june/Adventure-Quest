package main;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class Item {
    private String name;  // 物品名称
    private int effect;   // 物品效果
    private int x;        // 物品的 x 坐标
    private int y;        // 物品的 y 坐标
    private BufferedImage itemImage; // 物品图片

    // 构造方法
    public Item(GamePanel gp,String name, int effect) throws IOException {
        this.name = name;
        this.effect = effect;
        direction(); // 随机生成初始位置
    }

    // 获取名称
    public String getName() {
        return name;
    }

    // 获取效果
    public int getEffect() {
        return effect;
    }

    // 随机方向（随机位置）
    public void direction() {
        Random r = new Random();
        this.x = r.nextInt(768); // 假设屏幕宽度为 768
        this.y = r.nextInt(576); // 假设屏幕高度为 576
    }

    // 绘制物品
    public void draw(Graphics2D g) throws IOException {
        switch (name){
            case "axe":
                itemImage = ImageIO.read(new File("/image/axe.png"));
                break;
            case "dagger":
                itemImage = ImageIO.read(new File("/image/dagger.png"));
                break;
            case "potion":
                itemImage = ImageIO.read(new File("/image/potion.png"));
                break;
            default:
                System.out.println("Don't have this item!");

        }
        g.drawImage(itemImage, x, y, null);
    }
}
