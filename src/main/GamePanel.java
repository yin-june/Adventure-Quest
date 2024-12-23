package main;

import entity.*; 
import java.awt.*;
import javax.swing.*; 

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS 
    final int originalTileSize = 16; //16 x 16 tiles 
    final int scale =3; 
    
    public final int tileSize = originalTileSize * scale; //48x48
    final int maxScreenCol = 16; 
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //768 pixels
    final int screenHeight = tileSize *maxScreenRow; //576 pixels
    
    //FPS 
    int FPS = 60; 
    
    KeyHandler keyH = new KeyHandler(); 
    Thread gameThread; 
    Hero player; 
    StatsPanel statsPanel; 
    
    // set player's default position
    // int playerX = 100; 
    // int playerY = 100; 
    // int playerSpeed = 5; 
    
    public GamePanel(String name, int hp, int attackPower, String heroType){
        JPanel gameArea = new JPanel();
        gameArea.setBackground(Color.red);
        gameArea.setBounds(0,0,200,screenHeight);

        //gameArea.setPreferredSize(new Dimension(screenWidth-400, screenHeight));
        //gameArea.setDoubleBuffered(true);
        //gameArea.setVisible(true);
        //gameArea.addKeyListener(keyH);
        //gameArea.setFocusable(true);

         this.setPreferredSize(new Dimension(screenWidth, screenHeight)); 
         this.setBackground(Color.BLACK);
         this.setDoubleBuffered(true);
         this.addKeyListener(keyH);
         this.setFocusable(true);


        // Initialize player based on heroType
        switch (heroType) {
            case "Mage":
                player = new Mage(this, keyH, name, attackPower, hp);
                break;
            case "Warrior":
                player = new Warrior(this, keyH, name, attackPower, hp);
                break;
            case "Archer":
                player = new Archer(this, keyH, name, attackPower, hp);
                break;
            default:
                throw new IllegalArgumentException("Invalid hero type: " + heroType);
        }

        statsPanel = new StatsPanel(player); 
        statsPanel.setBounds(250,0,200,screenHeight);
        
        this.add(gameArea);
        this.add(statsPanel);
        setupFrame();
        
    }

    private void setupFrame(){
        JFrame gameFrame = new JFrame("Adventure Quest");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        gameFrame.setLayout(new BorderLayout());
        gameFrame.add(this, BorderLayout.CENTER);
        gameFrame.pack(); // window fits preferred size
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);

        startGameThread();
    }

    public void startGameThread(){
        gameThread = new Thread(this); 
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = Math.pow(10,9)/FPS; // 0.01666sec 
        double delta = 0;
        long lastTime = System.nanoTime(); 
        long currentTime; 
        long timer = 0; 
        int drawCount = 0; 
        
        // game loop
        while(gameThread != null){
            //System.out.println("loop is running");
            currentTime = System.nanoTime(); 
            delta += (currentTime - lastTime)/ drawInterval; 
            timer += (currentTime - lastTime); 
            lastTime = currentTime; 
            
            if(delta >= 1){
                // update infomation 
                update();
                // draw the screen with updated information 
                repaint(); // call paint component 
                delta--; 
                drawCount++; 
            }
            if(timer >= Math.pow(10,9)){
                //System.out.println("FPS: " + drawCount);
                drawCount = 0; 
                timer = 0; 
            }
        }
    }
    
    public void update(){
        player.update();
        statsPanel.updateStats(); 
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2= (Graphics2D)g;
        player.draw(g2);
        
        g2.dispose();
        
    }
 }
