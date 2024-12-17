/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;

public class main {
    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        window.setResizable(false); 
        window.setTitle("Adventure Quest"); 
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose your game pattern: ");
        System.out.println("1. Text Game");
        System.out.println("2. GUI Game");
        int choice = sc.nextInt();
        if(choice == 2){
            new menu().mainFrame();
        }
        sc.close(); 
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); 
        
        window.pack(); // window fits preferred size 
        
        window.setLocationRelativeTo(null);
        window.setVisible(true); 
        
        gamePanel.startGameThread(); 
    }
    
}
