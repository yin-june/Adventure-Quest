package main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class InventoryPanel extends JPanel {
    private DefaultListModel<BufferedImage> inventoryModel;
    private JList<BufferedImage> inventoryList;

    public InventoryPanel() {
        this.setPreferredSize(new Dimension(150, -1));
        this.setLayout(new BorderLayout());

        JLabel inventoryLabel = new JLabel("Inventory");
        inventoryLabel.setHorizontalAlignment(SwingConstants.CENTER);

        inventoryModel = new DefaultListModel<>();
        inventoryList = new JList<>(inventoryModel);

        // 设置自定义渲染器
        inventoryList.setCellRenderer(new ImageCellRenderer());

        JScrollPane scrollPane = new JScrollPane(inventoryList);

        this.add(inventoryLabel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void addItemToInventory(BufferedImage itemImage) {
        inventoryModel.addElement(itemImage); // 添加图像到模型中
    }

    // 自定义渲染器类
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
                // 设置图像作为标签的图标
                setIcon(new ImageIcon(value));
            } else {
                setIcon(null);
            }

            // 设置选中状态的背景颜色
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
