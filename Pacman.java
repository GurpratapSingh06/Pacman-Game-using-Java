package pacmangame;

public class Pacman {
    private int x;
    private int y;
    private int direction;
    private boolean alive;
    
    public Pacman(int x, int y) {
        this.x = x;
        this.y = y;
        this.direction = 0;
        this.alive = true;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getDirection() {
        return direction;
    }
    
    public boolean isAlive() {
        return alive;
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
