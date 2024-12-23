package main;

import entity.Hero;
import java.awt.*;
import javax.swing.*;

public class StatsPanel extends JPanel{
    private Hero player;
    private JLabel nameLabel;
    private JLabel hpLabel;
    private JLabel attackPowerLabel;

    public StatsPanel(Hero player) {
        this.player = player;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(200, 0)); // Fixed width for the stats panel
        this.setBackground(Color.PINK);


        nameLabel = new JLabel("Name: " + player.getName());
        hpLabel = new JLabel("HP: " + player.getHp());
        attackPowerLabel = new JLabel("Attack Power: " + player.getAttackPower());

        this.add(nameLabel);
        this.add(hpLabel);
        this.add(attackPowerLabel);
    }

    public void updateStats() {
        nameLabel.setText("Name: " + player.getName());
        hpLabel.setText("HP: " + player.getHp());
        attackPowerLabel.setText("Attack Power: " + player.getAttackPower());
    }
}
