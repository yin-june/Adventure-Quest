package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import main.*;

public abstract class Hero extends entity {
    GamePanel gp;
    KeyHandler keyH;
    public String name;
    private int attackPower;
    private int hp;
    private String heroType;

    //BufferedImage upImage, downImage, leftImage, rightImage;

    public Hero(GamePanel gp, KeyHandler keyH, String name, int attackPower, int hp, String heroType) {
        this.gp = gp;
        this.keyH = keyH;
        this.name = name;
        this.attackPower = attackPower;
        this.hp = hp;
        this.heroType = heroType;
        setDefaultValues();
        getPlayerImage();
        displayStats();
    }

    public void setDefaultValues() {
        x = 0;
        y = 100;
        speed = 4;
        direction = "down";
    }
    
    public abstract void getPlayerImage();

    public void update() {
        if (keyH.upPressed && y > 0) {
            direction = "up";
            y -= speed;
        } else if (keyH.downPressed && y < (GamePanel.SCREEN_HEIGHT - 50)) {
            direction = "down";
            y += speed;
        } else if (keyH.leftPressed && x > 0) {
            direction = "left";
            x -= speed;
        } else if (keyH.rightPressed && x<(GamePanel.SCREEN_WIDTH - 40)) {
            direction = "right";
            x += speed;
        }
        
        // check for collision with monsters 
        Monster monster = gp.checkMonsterCollision(this);
        if (monster != null) {
            JOptionPane.showMessageDialog(null, "You encountered a "+ monster.getType() + "!\n"+
                    monster.getType() + " HP: " + monster.getHp(), "Monster Encounter", JOptionPane.PLAIN_MESSAGE);
            new Battle(this, monster, gp).startBattle();
        }
    }

     public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = upImage;
                break;
            case "down":
                image = downImage;
                break;
            case "left":
                image = leftImage;
                break;
            case "right":
                image = rightImage;
                break;
        }
        g2.drawImage(image, x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }

    // Player Stats
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getHeroType() {
        return heroType;
    }

    public void setHeroType(String heroType) {
        this.heroType = heroType;
    }
    
    public void attack(Monster target) {
        target.takeDamage(this.attackPower);
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }
    
    public boolean isAlive() {
        return this.hp > 0;
        }
        
    public String displayStats(){
        return "~~~ Battle Status ~~~\n" + 
                "\nLevel: " + gp.getDifficulty() + "\n" +
                "\nName: "+ name + 
                "\nHero Type: "+heroType+ 
                "\nHP: " +hp+
                "\nAttack Power: "+ attackPower +"\n";
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, GamePanel.TILE_SIZE/2, GamePanel.TILE_SIZE/2);
    }

    public void moveToSafeLocation() {
        x = 0;
        y = 100;
    }

    
    
}
