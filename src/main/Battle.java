package main;

import entity.Hero;
import entity.Monster; 
import javax.swing.*; 

public class Battle {
    private Hero hero; 
    private Monster monster; 
    private GamePanel gamePanel; 
    
    public Battle(Hero hero, Monster monster, GamePanel gamePanel){
        this.hero = hero;
        this.monster = monster;
        this.gamePanel = gamePanel;
    }
    
    public void startBattle() {
        // Display battle options
        String[] options = {"Attack", "Flee", "Use Item"};
        int choice = JOptionPane.showOptionDialog(null, "Choose your action:", "Battle",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                attack();
                break;
            case 1:
                flee();
                break;
            case 2:
                useItem();
                break;
        }
    }
    
    private void attack() {
        System.out.println("init Monster HP: " + monster.getHp());
        hero.attack(monster);
        gamePanel.updateStatsDisplay();
        System.out.println("final Monster HP: " + monster.getHp());
        JOptionPane.showMessageDialog(null, monster.getType()+ " received "+hero.getAttackPower()+" damage!\n"+
                                    monster.getType() + " HP: " + monster.getHp());
        if (!monster.isAlive()) {
            JOptionPane.showMessageDialog(null, "You defeated the " +monster.getType()+ "!");
            gamePanel.removeMonster(monster);
        } else {
            monster.attack(hero);
            gamePanel.updateStatsDisplay();
            System.out.println("Hero HP: " + hero.getHp());
            JOptionPane.showMessageDialog(null, "You received "+ monster.getAttackPower()+" damage!");
            if (!hero.isAlive()) {
                JOptionPane.showMessageDialog(null, "You were defeated by the "+ monster.getType()+ "!");
                gamePanel.endGame();
            } else {
                startBattle();
            }
        }
        
    }

    private void flee() {
        JOptionPane.showMessageDialog(null, "You fled from the battle!");
        hero.moveToSafeLocation(); // Logic to move the hero to a safe location
        gamePanel.updateHeroPosition(hero); // Update the hero's position on the game panel
    }
    
    private void useItem() {
        // Let the player choose from the three items: dagger, axe, potion
        String[] items = {"Axe", "Dagger", "Potion"};
        int itemChoice = JOptionPane.showOptionDialog(null, "Choose an item to use:", "Use Item",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, items, items[0]);

        switch (itemChoice) {
            case 0:
                JOptionPane.showMessageDialog(null, "You used an Axe!");
                // Add logic for using a dagger
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "You used a Dagger!");
                // Add logic for using an axe
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "You used a Potion!");
                // Add logic for using a potion
                break;
        }
        startBattle();
    }
}
