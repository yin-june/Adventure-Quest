package main;

import entity.Hero;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*; 

public class GameStatus {
    private static final String HIGH_SCORES_FILE = "high_scores.txt";
    private final JFrame gameFrame;
    private final Hero hero; 

    public GameStatus(JFrame gameFrame, Hero hero) {
        this.gameFrame = gameFrame;
        this.hero = hero;
    }

    public void showGameOver(int score) {
        JOptionPane.showMessageDialog(gameFrame, "Game Over!\nYour Score: " + score, "Game Over", JOptionPane.PLAIN_MESSAGE);
        saveHighScore(hero.getName(), score);
        showHighScores();
        System.exit(0);
    }

    // Method to display Victory
    public void showVictory(int score) {
        JOptionPane.showMessageDialog(gameFrame, "Victory!\nYour Score: " + score, "Victory", JOptionPane.PLAIN_MESSAGE);
        saveHighScore(hero.getName(), score);
        showHighScores();
        System.exit(0);
    }

    // Method to save high score
    private void saveHighScore(String name, int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGH_SCORES_FILE, true))) {
            writer.write(name + ", " + score + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to show high scores
    public void showHighScores() {
        List<String> highScores = getHighScores();
        StringBuilder scoresText = new StringBuilder("~~~~~~~ High Scores ~~~~~~~\n");
        if(highScores.isEmpty()) {
            scoresText.append("No high scores yet!\n");
        }
        // only show top 5 scores
        if(highScores.size() > 5) {
            highScores = highScores.subList(0, 5);
        }
        for (int i = 0; i < highScores.size(); i++) {
            scoresText.append(i + 1).append(") ").append(highScores.get(i)).append("\n");
        }
        scoreboard(scoresText.toString());
    }

    // Method to get the high scores
    private List<String> getHighScores() {
        List<String> highScores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGH_SCORES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                highScores.add(line);
            }
        } catch (IOException e) { // If the file does not exist, return an empty list
            System.out.println("File does not exist. No high scores yet. ");
        }
        highScores.sort((a, b) -> {
            int scoreA = Integer.parseInt(a.split(", ")[1]);
            int scoreB = Integer.parseInt(b.split(", ")[1]);
            return Integer.compare(scoreB, scoreA);
        });
        return highScores;
    }

    private void scoreboard(String message) {
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("OptionPane.messageFont", new Font("Copperplate Gothic Light", Font.BOLD, 14));
        UIManager.put("Button.background", new Color(60, 46, 30));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", new Font("Arial", Font.BOLD, 14));

        JOptionPane.showMessageDialog(gameFrame, message, "High Scores", JOptionPane.PLAIN_MESSAGE);

        // Restore default look
        UIManager.put("Panel.background", null);

    }

}
