package main;

import java.io.IOException;
import javax.swing.JFrame;

public class main {
    public static void main(String[] args) throws IOException {
        new menu().mainFrame(); 
        
        //uncomment to view character + movement 
        /* 
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); 
        
        window.pack(); // window fits preferred size 
        
        window.setLocationRelativeTo(null);
        window.setVisible(true); 
        
        gamePanel.startGameThread(); 
        */

    }
    
}
