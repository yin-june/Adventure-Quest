package main;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class InventoryPanel extends JPanel{
    JTextField inventoryTitle = new JTextField("Inventory");
    JPanel textPanel = new JPanel();
    JPanel[] inventory = new JPanel[3];
    Item axeItem = new Item(this,"axe");
    Item daggerItem = new Item(this,"dagger");
    Item potionItem = new Item(this,"potion");

    public InventoryPanel() throws IOException {
        this.setPreferredSize(new Dimension(-1,150));
        Box verticalBox = Box.createVerticalBox();
        textPanel.add(inventoryTitle);
        verticalBox.add(textPanel);
        for (int i = 0; i < 3; i++) {
            inventory[i] = new JPanel();
            inventory[i].setPreferredSize(new Dimension(32,64));
            verticalBox.add(inventory[i]);
        }
        inventoryTitle.setEditable(false);
        inventory[0].add(new JLabel(addItem(axeItem)));
        inventory[1].add(new JLabel(addItem(daggerItem)));
        inventory[2].add(new JLabel(addItem(potionItem)));
        this.add(verticalBox);
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
}
