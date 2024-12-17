/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
 
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class menu {
    JFrame mainFrame = new JFrame("AdventureQuestGame");
    JButton enterGame = new JButton("Enter Game");
    JButton exitGame = new JButton("Exit Game");
    JFrame createHeroFrame = new JFrame("CreateYourHero");
    JFrame ChooseYourGameLevelFrame = new JFrame("ChooseYourGameLevel");

    BufferedImage enterBackground;
    BufferedImage createHeroBackground;
    BufferedImage chooseLevelBackground;

    private class EnterBackground extends JPanel{
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(enterBackground != null){
                g.drawImage(enterBackground,0,0,getWidth(),getHeight(),this);
            }
        }
    }
    EnterBackground enterCanvas = new EnterBackground();

    private class CreateHeroCanvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (createHeroBackground != null) {
                g.drawImage(createHeroBackground, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
    CreateHeroCanvas createCanvas = new CreateHeroCanvas();

    private class ChooseLevelCanvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (chooseLevelBackground != null) {
                g.drawImage(chooseLevelBackground, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
    ChooseLevelCanvas chooseLevelCanvas = new ChooseLevelCanvas();

    public void mainFrame() throws IOException {
        Button(enterGame);
        enterGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(false);
                try {
                    createHeroFrame();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        Button(exitGame);
        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        enterBackground = ImageIO.read(new File("src\\image\\dungeon1.jpg"));

        // Add a label for "adventure quest"
        JLabel titleLabel = new JLabel("Adventure Quest", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Matura MT Script Capitals", Font.BOLD, 50)); // Set font 
        titleLabel.setForeground(Color.YELLOW); // Set text color 
        titleLabel.setBackground(Color.BLACK); // Set background color 
        titleLabel.setOpaque(true); // Make the background color visible
        titleLabel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5)); // Set border color 
        mainFrame.add(titleLabel, BorderLayout.NORTH); // Add the label to the top of the frame
        mainFrame.add(enterCanvas);
        
        Box enterPanel = Box.createVerticalBox();
        enterCanvas.setLayout(new GridBagLayout());
        enterPanel.add(enterGame);
        enterPanel.add(Box.createVerticalStrut(20)); // Increase the space between buttons
        enterPanel.add(exitGame);
        enterCanvas.add(enterPanel);
        enterCanvas.repaint();

        mainFrame.setSize(768,576); 
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    public void createHeroFrame() throws IOException {
        JTextField nameField = new JTextField(30);
        JTextField HPField = new JTextField(30);
        JTextField AttackPowerField = new JTextField(30);
        JButton createHeroButton = new JButton("Create Hero");

        Button(createHeroButton);
        createHeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String HP = HPField.getText();
                String AttackPower = AttackPowerField.getText();
                try {
                    if (name.isEmpty() || HP.isEmpty() || AttackPower.isEmpty()) {
                        JOptionPane.showMessageDialog(createHeroFrame, "The field cannot be empty!");
                        return;
                    }
                    int hpValue = Integer.parseInt(HP);
                    int attackPowerValue = Integer.parseInt(AttackPower);
                    if (hpValue < 50 || hpValue > 150) {
                        JOptionPane.showMessageDialog(createHeroFrame, "HP value must be between 50 and 150!");
                        return;
                    }
                    if (attackPowerValue < 10 || attackPowerValue > 30) {
                        JOptionPane.showMessageDialog(createHeroFrame, "Attack Power must be between 10 and 30!");
                        return;
                    }
                        JOptionPane.showMessageDialog(createHeroFrame, "You create your hero successfully!");
                        createHeroFrame.setVisible(false);
                        ChooseYourGameLevelFrame();
                }catch (NumberFormatException | IOException a){
                    JOptionPane.showMessageDialog(createHeroFrame, "HP and Attack Power must be valid numbers!");
                }
            }
        });

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel("Enter your hero's name: ");
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JPanel HPPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel HPLabel = new JLabel("Enter your hero's HP(50-150): ");
        HPPanel.add(HPLabel);
        HPPanel.add(HPField);

        JPanel AttackPowerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel AttackPowerLabel = new JLabel("Enter your hero's Attack Power(10-30): ");
        AttackPowerPanel.add(AttackPowerLabel);
        AttackPowerPanel.add(AttackPowerField);

        Box createFrameBox = Box.createVerticalBox();
        createFrameBox.add(namePanel);
        createFrameBox.add(Box.createVerticalStrut(50));
        createFrameBox.add(HPPanel);
        createFrameBox.add(Box.createVerticalStrut(50));
        createFrameBox.add(AttackPowerPanel);
        createFrameBox.add(Box.createVerticalStrut(50));
        createFrameBox.add(createHeroButton);
       
        createCanvas.setLayout(new GridBagLayout());
        createCanvas.add(createFrameBox);
        createCanvas.repaint();

        createHeroBackground = ImageIO.read(new File("src\\image\\dungeon3.jpg"));
        createHeroFrame.add(createCanvas);
        createHeroFrame.setSize(768,576);
        createHeroFrame.setResizable(false);
        createHeroFrame.setVisible(true);
        createHeroFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void ChooseYourGameLevelFrame() throws IOException {
        JButton easy = new JButton("Easy");
        JPanel easyPanel = new JPanel();
        easyPanel.add(easy);
        Button(easy);

        JButton medium = new JButton("Medium");
        JPanel mediumPanel = new JPanel();
        mediumPanel.add(medium);
        Button(medium);

        JButton difficult = new JButton("Difficult");
        JPanel difficultPanel = new JPanel();
        difficultPanel.add(difficult);
        Button(difficult);

        Box chooseLevelBox = Box.createVerticalBox();

        JLabel chooseLevelLabel = new JLabel("Level", SwingConstants.CENTER);
        chooseLevelLabel.setFont(new Font("ROG Fonts", Font.BOLD, 30));
        chooseLevelLabel.setForeground(Color.WHITE); // Set the color of the text
        chooseLevelLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Align the label at the center
        chooseLevelBox.add(chooseLevelLabel);
        chooseLevelBox.add(Box.createVerticalStrut(20)); // Add some space below the label

        chooseLevelBox.add(easyPanel);
        chooseLevelBox.add(Box.createVerticalStrut(40));
        chooseLevelBox.add(mediumPanel);
        chooseLevelBox.add(Box.createVerticalStrut(40));
        chooseLevelBox.add(difficultPanel);

        chooseLevelBackground = ImageIO.read(new File("src\\image\\dungeon3.jpg"));
        chooseLevelCanvas.setLayout(new GridBagLayout());
        chooseLevelCanvas.add(chooseLevelBox);
        ChooseYourGameLevelFrame.add(chooseLevelCanvas);

        ChooseYourGameLevelFrame.setVisible(true);
        ChooseYourGameLevelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ChooseYourGameLevelFrame.setSize(new Dimension(768,576));
        ChooseYourGameLevelFrame.setResizable(false);
    }

    public void Button(JButton button){
        button.setBackground(new Color(60,46,30));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 20));
        button.setPreferredSize(new Dimension(200, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /*
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose your game pattern: ");
        System.out.println("1. Text Game");
        System.out.println("2. GUI Game");
        int choice = sc.nextInt();
        if(choice == 2){
            new game().mainFrame();
        }
        sc.close(); 
    }*/
}
