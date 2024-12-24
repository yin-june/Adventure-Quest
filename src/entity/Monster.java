package entity;

public class Monster {
    String type;
    int hp;
    int attackPower;

    public Monster(String type, int hp, int attackPower) {
        this.type = type;
        this.hp = hp;
        this.attackPower = attackPower;
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

    
}
