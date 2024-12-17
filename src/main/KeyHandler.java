/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    public boolean upPressed, downPressed, leftPressed, rightPressed; 
    
    
    @Override
    public void keyTyped(KeyEvent e){
        
    }
    @Override
    public void keyPressed(KeyEvent e){
        int code =e.getKeyCode(); //returns the number of the key pressed
        if(code == KeyEvent.VK_UP){
            upPressed =true; 
        }
        if(code == KeyEvent.VK_DOWN){
            downPressed = true; 
        }
        if(code == KeyEvent.VK_LEFT){
            leftPressed = true; 
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPressed = true; 
        }
            
    }
    @Override
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode(); 
        if(code == KeyEvent.VK_UP){
            upPressed = false; 
        }
        if(code == KeyEvent.VK_DOWN){
            downPressed = false; 
        }
        if(code == KeyEvent.VK_LEFT){
            leftPressed = false; 
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPressed = false; 
        }
    }
    
}
