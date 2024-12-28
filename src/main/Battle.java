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
        hero.attack(monster);
        JOptionPane.showMessageDialog(null, "Monster received "+hero.getAttackPower()+" damage!");
        if (!monster.isAlive()) {
            JOptionPane.showMessageDialog(null, "You defeated the monster!");
            gamePanel.removeMonster(monster);
            gamePanel.requestFocusInWindow(); // Request focus after monster is defeated to receive key events 
        } else {
            monster.attack(hero);
            JOptionPane.showMessageDialog(null, "You received "+ monster.getAttackPower()+" damage!");
            if (!hero.isAlive()) {
                JOptionPane.showMessageDialog(null, "You were defeated by the monster!");
                gamePanel.endGame();
            } else {
                startBattle();
            }
        }
        
    }

    private void flee() {
        JOptionPane.showMessageDialog(null, "You fled from the battle!");
        gamePanel.requestFocusInWindow(); // Request focus after fleeing
        hero.moveToSafeLocation(); // Logic to move the hero to a safe location
        gamePanel.updateHeroPosition(hero); // Update the hero's position on the game panel
    }
    
    private void useItem() {
        // Logic to use an item
        JOptionPane.showMessageDialog(null, "You used an item!");
        startBattle();
    }
}
