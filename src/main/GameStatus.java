package main;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameStatus {
    private static final String HIGH_SCORES_FILE = "high_scores.txt";
    private final JFrame gameFrame;

    public GameStatus(JFrame gameFrame) {
        this.gameFrame = gameFrame;
    }


    public void showGameOver(int score) {
        JOptionPane.showMessageDialog(gameFrame, "Game Over!\nYour Score: " + score, "Game Over", JOptionPane.PLAIN_MESSAGE);
        saveHighScore(score);
        showHighScores();
        System.exit(0);
    }

    // Method to display Victory
    public void showVictory(int score) {
        JOptionPane.showMessageDialog(gameFrame, "Victory!\nYour Score: " + score, "Victory", JOptionPane.PLAIN_MESSAGE);
        saveHighScore(score);
        showHighScores();
        System.exit(0);
    }

    // Method to save high score
    private void saveHighScore(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGH_SCORES_FILE, true))) {
            writer.write(score + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to show high scores
    private void showHighScores() {
        List<Integer> highScores = getHighScores();
        StringBuilder scoresText = new StringBuilder("High Scores:\n");
        for (int i = 0; i < highScores.size(); i++) {
            scoresText.append(i + 1).append(") ").append(highScores.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(gameFrame, scoresText.toString(), "High Scores", JOptionPane.PLAIN_MESSAGE);
    }

    // Method to get the high scores
    private List<Integer> getHighScores() {
        List<Integer> highScores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGH_SCORES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                highScores.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(highScores, Collections.reverseOrder());
        return highScores;
    }
}
