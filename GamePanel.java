package pacmangame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.RenderingHints;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    
    private static final int BLOCK_SIZE = 30;
    private static final int N_BLOCKS = 19;
    private static final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    
    private static final int STATE_TUTORIAL = 0;
    private static final int STATE_LOADING = 1;
    private static final int STATE_PLAYING = 2;
    private static final int STATE_GAME_OVER = 3;
    
    private int gameState = STATE_TUTORIAL;
    private Timer timer;
    private Pacman pacman;
    private Ghost[] ghosts;
    private Maze maze;
    private int score;
    private int lives;
    private boolean inGame;
    private int pacmanAnimPos = 0;
    private int nextPacmanDirection = 0;
    private int moveCounter = 0;
    private int pelletsRemaining = 0;
    private int loadingProgress = 0;
    private long loadingStartTime = 0;
    
    public GamePanel() {
        setPreferredSize(new Dimension(SCREEN_SIZE, SCREEN_SIZE + 40));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        setDoubleBuffered(true);
        
        timer = new Timer(100, this);
        timer.start();
    }
    
    private void initGame() {
        lives = 3;
        score = 0;
        inGame = true;
        nextPacmanDirection = 0;
        moveCounter = 0;
        
        maze = new Maze(N_BLOCKS);
        pacman = new Pacman(1, 1);
        
        ghosts = new Ghost[4];
        ghosts[0] = new Ghost(9, 9, new Color(255, 0, 0));
        ghosts[1] = new Ghost(9, 10, new Color(255, 184, 255));
        ghosts[2] = new Ghost(8, 9, new Color(0, 255, 255));
        ghosts[3] = new Ghost(10, 10, new Color(255, 184, 82));
        
        gameState = STATE_PLAYING;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        switch (gameState) {
            case STATE_TUTORIAL:
                drawTutorial(g2d);
                break;
            case STATE_LOADING:
                drawLoading(g2d);
                break;
            case STATE_PLAYING:
                drawGame(g2d);
                break;
            case STATE_GAME_OVER:
                drawGameOver(g2d);
                break;
        }
    }
    
    private void drawTutorial(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, SCREEN_SIZE, SCREEN_SIZE);
        
        g.setColor(new Color(255, 255, 0));
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString("PAC-MAN", 140, 80);
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Controls:", 80, 150);
        
        g.drawString("Arrow Keys - Move Pac-Man", 80, 190);
        g.drawString("Space - Restart Game", 80, 220);
        
        g.setColor(new Color(200, 200, 200));
        g.drawString("Objective:", 80, 280);
        
        g.setColor(Color.WHITE);
        g.drawString("Collect all pellets (dots)", 80, 310);
        g.drawString("Avoid ghosts!", 80, 340);
        g.drawString("Power pellets make ghosts edible", 80, 370);
        
        g.setColor(new Color(255, 255, 0));
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Press SPACE to Continue", 100, 450);
        
        g.setColor(new Color(100, 100, 100));
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Press SPACE to Start Game", 120, SCREEN_SIZE - 20);
    }
    
    private void drawLoading(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, SCREEN_SIZE, SCREEN_SIZE);
        
        g.setColor(new Color(255, 255, 0));
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("Loading...", 140, 200);
        
        int barWidth = 300;
        int barHeight = 40;
        int barX = (SCREEN_SIZE - barWidth) / 2;
        int barY = 300;
        
        g.setColor(new Color(50, 50, 50));
        g.fillRect(barX, barY, barWidth, barHeight);
        
        g.setColor(new Color(0, 255, 0));
        int progressWidth = (barWidth * loadingProgress) / 100;
        g.fillRect(barX, barY, progressWidth, barHeight);
        
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(2));
        g.drawRect(barX, barY, barWidth, barHeight);
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString(loadingProgress + "%", barX + barWidth + 20, barY + 30);
        
        g.setColor(new Color(200, 200, 200));
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString("Initializing Maze...", 120, 400);
        g.drawString("Loading Ghosts...", 120, 430);
        g.drawString("Preparing Game...", 120, 460);
    }
    
    private void drawGame(Graphics2D g) {
        drawMaze(g);
        
        if (inGame) {
            drawGhosts(g);
            drawPacman(g);
        }
        
        drawScore(g);
    }
    
    private void drawMaze(Graphics2D g) {
        for (int i = 0; i < N_BLOCKS; i++) {
            for (int j = 0; j < N_BLOCKS; j++) {
                int tile = maze.getTile(i, j);
                
                if (tile == 1) {
                    g.setColor(new Color(33, 33, 222));
                    g.fillRect(j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                    g.setColor(new Color(66, 66, 255));
                    g.setStroke(new BasicStroke(2));
                    g.drawRect(j * BLOCK_SIZE + 1, i * BLOCK_SIZE + 1, BLOCK_SIZE - 2, BLOCK_SIZE - 2);
                } else if (tile == 0) {
                    g.setColor(new Color(200, 150, 100));
                    g.fillOval(j * BLOCK_SIZE + 13, i * BLOCK_SIZE + 13, 4, 4);
                } else if (tile == 2) {
                    g.setColor(new Color(200, 150, 100));
                    for (int x = j * BLOCK_SIZE + 8; x < (j + 1) * BLOCK_SIZE; x += 6) {
                        for (int y = i * BLOCK_SIZE + 8; y < (i + 1) * BLOCK_SIZE; y += 6) {
                            g.fillOval(x, y, 2, 2);
                        }
                    }
                }
            }
        }
    }
    
    private void drawScore(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("SCORE: " + score, 20, SCREEN_SIZE + 25);
        g.drawString("LIVES: " + lives, SCREEN_SIZE / 2 - 40, SCREEN_SIZE + 25);
    }
    
    private void drawPacman(Graphics2D g) {
        int x = pacman.getX() * BLOCK_SIZE;
        int y = pacman.getY() * BLOCK_SIZE;
        
        g.setColor(new Color(255, 255, 0));
        
        int mouthOpen = (pacmanAnimPos % 12 < 6) ? 30 : 0;
        int startAngle = 45 + mouthOpen;
        int arcAngle = 270 - (2 * mouthOpen);
        
        switch (pacman.getDirection()) {
            case 0:
                g.fillArc(x + 2, y + 2, BLOCK_SIZE - 4, BLOCK_SIZE - 4, startAngle, arcAngle);
                break;
            case 1:
                g.fillArc(x + 2, y + 2, BLOCK_SIZE - 4, BLOCK_SIZE - 4, startAngle - 90, arcAngle);
                break;
            case 2:
                g.fillArc(x + 2, y + 2, BLOCK_SIZE - 4, BLOCK_SIZE - 4, startAngle - 180, arcAngle);
                break;
            case 3:
                g.fillArc(x + 2, y + 2, BLOCK_SIZE - 4, BLOCK_SIZE - 4, startAngle - 270, arcAngle);
                break;
        }
        
        pacmanAnimPos++;
    }
    
    private void drawGhosts(Graphics2D g) {
        for (Ghost ghost : ghosts) {
            int x = ghost.getX() * BLOCK_SIZE;
            int y = ghost.getY() * BLOCK_SIZE;
            
            // Set color based on vulnerability
            if (ghost.isVulnerable()) {
                g.setColor(new Color(100, 150, 255));  // Blue when edible
            } else {
                g.setColor(ghost.getColor());  // Original color
            }
            
            // Draw ghost body
            g.fillRoundRect(x + 2, y + 2, BLOCK_SIZE - 4, BLOCK_SIZE - 4, 8, 8);
            
            // Draw eyes (white)
            g.setColor(Color.WHITE);
            g.fillOval(x + 6, y + 8, 5, 6);
            g.fillOval(x + 19, y + 8, 5, 6);
            
            // Draw pupils (black)
            g.setColor(Color.BLACK);
            g.fillOval(x + 8, y + 10, 2, 2);
            g.fillOval(x + 21, y + 10, 2, 2);
        }
    }
    
    private void drawGameOver(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, SCREEN_SIZE, SCREEN_SIZE);
        
        drawMaze(g);
        
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0, 0, SCREEN_SIZE, SCREEN_SIZE);
        
        if (pelletsRemaining == 0 && inGame) {
            g.setColor(new Color(255, 255, 0));
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("YOU WIN!", 150, 200);
        } else {
            g.setColor(new Color(255, 0, 0));
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("GAME OVER", 120, 200);
        }
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("FINAL SCORE: " + score, 150, 280);
        
        g.setColor(new Color(255, 255, 0));
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Press SPACE to return to tutorial", 110, 380);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameState == STATE_LOADING) {
            if (loadingStartTime == 0) {
                loadingStartTime = System.currentTimeMillis();
            }
            
            long elapsed = System.currentTimeMillis() - loadingStartTime;
            loadingProgress = Math.min(100, (int) (elapsed / 30));
            
            if (loadingProgress >= 100) {
                initGame();
                loadingStartTime = 0;
                loadingProgress = 0;
            }
            repaint();
        } else if (gameState == STATE_PLAYING && inGame) {
            moveCounter++;
            
            movePacman();
            
            if (moveCounter % 3 == 0) {
                moveGhosts();
            }
            
            checkCollisions();
            checkWinCondition();
            repaint();
        } else {
            repaint();
        }
    }
    
    private void movePacman() {
        int newX = pacman.getX();
        int newY = pacman.getY();
        
        switch (nextPacmanDirection) {
            case 0: newX++; break;
            case 1: newY++; break;
            case 2: newX--; break;
            case 3: newY--; break;
        }
        
        if (newX >= 0 && newX < N_BLOCKS && newY >= 0 && newY < N_BLOCKS) {
            if (maze.getTile(newY, newX) != 1) {
                pacman.setDirection(nextPacmanDirection);
                pacman.setPosition(newX, newY);
                
                int tile = maze.getTile(newY, newX);
                if (tile == 0) {
                    score += 10;
                    maze.setTile(newY, newX, 3);
                } else if (tile == 2) {
                    score += 50;
                    maze.setTile(newY, newX, 3);
                    for (Ghost ghost : ghosts) {
                        ghost.setVulnerable(true);
                    }
                }
            }
        }
    }
    
    private void moveGhosts() {
        for (Ghost ghost : ghosts) {
            if (ghost.isVulnerable()) {
                ghost.decrementVulnerableTime();
            }
            
            int pacX = pacman.getX();
            int pacY = pacman.getY();
            int ghostX = ghost.getX();
            int ghostY = ghost.getY();
            
            int newX = ghostX;
            int newY = ghostY;
            
            int diffX = pacX - ghostX;
            int diffY = pacY - ghostY;
            
            boolean moved = false;
            
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (diffX > 0 && ghostX + 1 < N_BLOCKS && maze.getTile(ghostY, ghostX + 1) != 1) {
                    newX = ghostX + 1;
                    moved = true;
                } else if (diffX < 0 && ghostX - 1 >= 0 && maze.getTile(ghostY, ghostX - 1) != 1) {
                    newX = ghostX - 1;
                    moved = true;
                } else if (diffY > 0 && ghostY + 1 < N_BLOCKS && maze.getTile(ghostY + 1, ghostX) != 1) {
                    newY = ghostY + 1;
                    moved = true;
                } else if (diffY < 0 && ghostY - 1 >= 0 && maze.getTile(ghostY - 1, ghostX) != 1) {
                    newY = ghostY - 1;
                    moved = true;
                }
            } else {
                if (diffY > 0 && ghostY + 1 < N_BLOCKS && maze.getTile(ghostY + 1, ghostX) != 1) {
                    newY = ghostY + 1;
                    moved = true;
                } else if (diffY < 0 && ghostY - 1 >= 0 && maze.getTile(ghostY - 1, ghostX) != 1) {
                    newY = ghostY - 1;
                    moved = true;
                } else if (diffX > 0 && ghostX + 1 < N_BLOCKS && maze.getTile(ghostY, ghostX + 1) != 1) {
                    newX = ghostX + 1;
                    moved = true;
                } else if (diffX < 0 && ghostX - 1 >= 0 && maze.getTile(ghostY, ghostX - 1) != 1) {
                    newX = ghostX - 1;
                    moved = true;
                }
            }
            
            if (moved) {
                ghost.setPosition(newX, newY);
            } else {
                moveGhostRandom(ghost);
            }
        }
    }
    
    private void moveGhostRandom(Ghost ghost) {
        int direction = (int) (Math.random() * 4);
        
        for (int i = 0; i < 4; i++) {
            int tryDir = (direction + i) % 4;
            int newX = ghost.getX();
            int newY = ghost.getY();
            
            switch (tryDir) {
                case 0:
                    if (newX + 1 < N_BLOCKS && maze.getTile(newY, newX + 1) != 1) {
                        ghost.setPosition(newX + 1, newY);
                        return;
                    }
                    break;
                case 1:
                    if (newY + 1 < N_BLOCKS && maze.getTile(newY + 1, newX) != 1) {
                        ghost.setPosition(newX, newY + 1);
                        return;
                    }
                    break;
                case 2:
                    if (newX - 1 >= 0 && maze.getTile(newY, newX - 1) != 1) {
                        ghost.setPosition(newX - 1, newY);
                        return;
                    }
                    break;
                case 3:
                    if (newY - 1 >= 0 && maze.getTile(newY - 1, newX) != 1) {
                        ghost.setPosition(newX, newY - 1);
                        return;
                    }
                    break;
            }
        }
    }
    
    private void checkCollisions() {
        for (Ghost ghost : ghosts) {
            if (pacman.getX() == ghost.getX() && pacman.getY() == ghost.getY()) {
                if (ghost.isVulnerable()) {
                    score += 200;
                    ghost.reset();
                    ghost.setVulnerable(false);
                } else {
                    lives--;
                    if (lives <= 0) {
                        inGame = false;
                        gameState = STATE_GAME_OVER;
                    } else {
                        pacman.setPosition(1, 1);
                    }
                }
            }
        }
    }
    
    private void checkWinCondition() {
        pelletsRemaining = 0;
        for (int i = 0; i < N_BLOCKS; i++) {
            for (int j = 0; j < N_BLOCKS; j++) {
                if (maze.getTile(i, j) == 0 || maze.getTile(i, j) == 2) {
                    pelletsRemaining++;
                }
            }
        }
        
        if (pelletsRemaining == 0) {
            inGame = false;
            gameState = STATE_GAME_OVER;
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_SPACE) {
            switch (gameState) {
                case STATE_TUTORIAL:
                    gameState = STATE_LOADING;
                    loadingStartTime = System.currentTimeMillis();
                    break;
                case STATE_GAME_OVER:
                    gameState = STATE_TUTORIAL;
                    break;
            }
        } else if (gameState == STATE_PLAYING && inGame) {
            switch (key) {
                case KeyEvent.VK_LEFT:
                    nextPacmanDirection = 2;
                    break;
                case KeyEvent.VK_RIGHT:
                    nextPacmanDirection = 0;
                    break;
                case KeyEvent.VK_UP:
                    nextPacmanDirection = 3;
                    break;
                case KeyEvent.VK_DOWN:
                    nextPacmanDirection = 1;
                    break;
            }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}
    
    @Override
    public void keyTyped(KeyEvent e) {}
}