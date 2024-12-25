package main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class InventoryPanel extends JPanel{
    static JPanel mainInventory = new JPanel();
    JPanel[] inventory = new JPanel[3];
    Item axeItem = new Item(this,"axe");
    Item daggeerItem = new Item(this,"dagger");
    Item potionItem = new Item(this,"potion");

    public InventoryPanel() throws IOException {
        this.setLayout(new GridLayout(1,5));
        this.setPreferredSize(new Dimension(-1,150));
        for (int i = 0; i < 3; i++) {
            inventory[i] = new JPanel();
            inventory[i].setPreferredSize(new Dimension(100,100));
            inventory[i].setLayout(new FlowLayout());
            mainInventory.add(inventory[i]);
        }
        inventory[0].add(new JLabel(addItem(axeItem)));
        inventory[1].add(new JLabel(addItem(daggeerItem)));
        inventory[2].add(new JLabel(addItem(potionItem)));
    }

    public ImageIcon addItem(Item item){
        switch (item.getName()){
            case "axe":
                return new ImageIcon("src/image/axe.png");
            case "dagger":
                return new ImageIcon("src/image/dagger.png");
            case "potion":
                return new ImageIcon("src/image/potion.png");
            default:
                return null;
        }
    }

    public static JPanel getMainInventory(){
        return mainInventory;
    }
}
