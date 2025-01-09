package main;

import entity.Hero;
import entity.Monster;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        int choice = JOptionPane.showOptionDialog(null, "Choose your action: ", "Battle",
        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

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
                                    monster.getType() + " HP: " + monster.getHp(), "Attack", JOptionPane.PLAIN_MESSAGE);
        if (!monster.isAlive()) {
            JOptionPane.showMessageDialog(null, "You defeated the " +monster.getType()+ "!","Monster defeated", JOptionPane.PLAIN_MESSAGE);
            gamePanel.removeMonster(monster);
        } else {
            monster.attack(hero);
            gamePanel.updateStatsDisplay();
            System.out.println("Hero HP: " + hero.getHp());
            JOptionPane.showMessageDialog(null, "You received "+ monster.getAttackPower()+" damage!", "Monster Attack", JOptionPane.PLAIN_MESSAGE);
            if (!hero.isAlive()) {
                JOptionPane.showMessageDialog(null, "You were defeated by the "+ monster.getType()+ "!", "Game Over", JOptionPane.PLAIN_MESSAGE);
                gamePanel.endGame();
            } else {
                startBattle();
            }
        }
        
    }

    private void flee() {
        JOptionPane.showMessageDialog(null, "You fled from the battle!","Flee", JOptionPane.PLAIN_MESSAGE);
        hero.moveToSafeLocation(); // Logic to move the hero to a safe location
        gamePanel.updateHeroPosition(hero); // Update the hero's position on the game panel
    }
    
    private void useItem() {
        // Get the inventory panel from the gamePanel instance
        InventoryPanel inventory = gamePanel.getInventoryPanel();
        List<String> availableItems = inventory.getItems();

        if (availableItems.isEmpty()) {
            JOptionPane.showMessageDialog(null,  "You have no items to use!", "Inventory", JOptionPane.PLAIN_MESSAGE);
            startBattle();
            return;
        }

        // Use a Set to remove duplicates
        Set<String> uniqueItems = new HashSet<>(availableItems);
        // Convert the set of unique items to an array
        String[] itemsArray = uniqueItems.toArray(new String[0]);
        
        // Let the player choose from the available items
        int itemChoice = JOptionPane.showOptionDialog(null, "Choose an item: ", "Inventory",
        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, itemsArray, itemsArray[0]);

        if (itemChoice == JOptionPane.CLOSED_OPTION) { 
            startBattle();
            return;
        }

        String selectedItem = itemsArray[itemChoice];
        System.out.println("Selected Item: " + selectedItem); // Debugging statement

        switch (selectedItem) {
            case "axe":
                JOptionPane.showMessageDialog(null,"You used an Axe!", "Item Used", JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showMessageDialog(null, "You've gained 30 attack power!", "Item Used", JOptionPane.PLAIN_MESSAGE);
                hero.setAttackPower(hero.getAttackPower() + 30); 
                break;
            case "dagger":
                JOptionPane.showMessageDialog(null, "You used a Dagger!", "Item Used", JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showMessageDialog(null, "You've gained 20 attack power!", "Item Used", JOptionPane.PLAIN_MESSAGE);
                hero.setAttackPower(hero.getAttackPower() + 20); 
                break;
            case "potion":
                JOptionPane.showMessageDialog(null, "You used a Potion!", "Item Used", JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showMessageDialog(null, "You're healed with 50 HP!", "Item Used", JOptionPane.PLAIN_MESSAGE); 
                hero.setHp(hero.getHp() + 50); 
                break;
            default:
                System.out.println("Invalid item selected!");
                return;
        }
        gamePanel.updateStatsDisplay();
        inventory.removeItem(selectedItem); // Remove the used item from the inventory
        
        startBattle();
    }
}
