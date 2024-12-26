package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Monster extends entity {
    String type;
    int hp;
    int attackPower;

    public Monster(String type, int hp, int attackPower) {
        this.type = type;
        this.hp = hp;
        this.attackPower = attackPower;
        this.direction = "right";
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    public void attack(Hero player) {
        player.takeDamage(this.attackPower);
    }
    
    public String getType(){
        return type;
    }
    
    public int getHp(){
        return hp; 
    }
    
    public int getAttackPower(){
        return attackPower; 
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public abstract void getMonsterImage();

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "left":
                image = leftImage;
                break;
            case "right":
                image = rightImage;
                break;
        }
        g2.drawImage(image, x, y, null);
    }
    
}
