package pacmangame;

import java.awt.Color;

public class Ghost {
    private int x;
    private int y;
    private int startX;
    private int startY;
    private Color color;
    private boolean vulnerable;
    private int vulnerableTime;
    
    public Ghost(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.startX = x;
        this.startY = y;
        this.color = color;
        this.vulnerable = false;
        this.vulnerableTime = 0;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public Color getColor() {
        return color;
    }
    
    public boolean isVulnerable() {
        return vulnerable;
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setVulnerable(boolean vulnerable) {
        this.vulnerable = vulnerable;
        if (vulnerable) {
            this.vulnerableTime = 50;
        }
    }
    
    public void decrementVulnerableTime() {
        if (vulnerableTime > 0) {
            vulnerableTime--;
            if (vulnerableTime == 0) {
                vulnerable = false;
            }
        }
    }
    
    public void reset() {
        this.x = startX;
        this.y = startY;
    }
}
