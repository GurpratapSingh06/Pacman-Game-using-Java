package pacmangame;

import javax.swing.*;
import java.awt.*;

public class PacmanGame extends JFrame {
    
    public PacmanGame() {
        setTitle("Pac-Man Game - Press Arrow Keys to Move");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PacmanGame();
        });
    }
}
