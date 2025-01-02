package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class InventoryPanel extends JPanel {
    private DefaultListModel<BufferedImage> inventoryModel;
    private JList<BufferedImage> inventoryList;
    private final List<String> items;
    private final Map<String, List<BufferedImage>> itemImageMap;

    public InventoryPanel() {
        items = new ArrayList<>();
        itemImageMap = new HashMap<>();

        this.setPreferredSize(new Dimension(150, -1));
        this.setLayout(new BorderLayout());

        JLabel inventoryLabel = new JLabel("Inventory");
        inventoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inventoryLabel.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 16));
        inventoryLabel.setForeground(Color.WHITE);

        inventoryModel = new DefaultListModel<>();
        inventoryList = new JList<>(inventoryModel);

        // Set the custom cell renderer for the list
        inventoryList.setCellRenderer(new ImageCellRenderer());

        JScrollPane scrollPane = new JScrollPane(inventoryList);

        // Set background color for the panel and the list
        this.setBackground(Color.BLACK);
        inventoryList.setBackground(Color.GRAY);

        this.add(inventoryLabel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void addItemToInventory(String item) {
        items.add(item);
        JLabel itemLabel = new JLabel(item);
        add(itemLabel);
        revalidate();
        repaint();
    }

    public List<String> getItems() {
        return items;
    }

    public void addItemToInventory(BufferedImage itemImage, String itemName) {
        inventoryModel.addElement(itemImage); // add image to model
        items.add(itemName); // add item name to list
        itemImageMap.computeIfAbsent(itemName, k -> new ArrayList<>()).add(itemImage); // Map item name to image list
    }
    
    public void removeItem(String itemName) {
        // Remove the first occurrence of the item name from the list
        items.remove(itemName);
        // Remove the corresponding image from the model using the map
        List<BufferedImage> images = itemImageMap.get(itemName);
        if (images != null && !images.isEmpty()) {
            BufferedImage itemImage = images.remove(0);
            inventoryModel.removeElement(itemImage);
            if (images.isEmpty()) {
                itemImageMap.remove(itemName);
            }
        }
        revalidate();
        repaint();
    }

    // Custom cell renderer for displaying images in the list
    private static class ImageCellRenderer extends JLabel implements ListCellRenderer<BufferedImage> {
        public ImageCellRenderer() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
        }

        @Override
        public Component getListCellRendererComponent(
                JList<? extends BufferedImage> list,
                BufferedImage value,
                int index,
                boolean isSelected,
                boolean cellHasFocus
        ) {
            if (value != null) {
                // set icon 
                setIcon(new ImageIcon(value));
            } else {
                setIcon(null);
            }

            // Set background and foreground colors 
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            return this;
        }
    }
}
