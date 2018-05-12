import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Car {
    private static final double DEFAULT_WIDTH=-1;
    private static final double DEFAULT_HEIGHT=-1;
    private static final double DEFAULT_SCALE=0.2;

    protected static Image redCar=null;
    protected static Image blueCar=null;

    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected int direction=0;
    protected int speed=400;
    public Car(double x, double y) {
        this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
    public Car(double x, double y, double width, double height) {
        // Assign instance variables
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;

        // Attempt to retrieve images from within jar file
        try {
            if(redCar==null) {
                redCar=ImageIO.read(getClass().getResourceAsStream("img/RedCar.png"));
            }
            if(blueCar==null) {
                blueCar=ImageIO.read(getClass().getResourceAsStream("img/BlueCar.png"));
            }
        } catch(IOException e) {
            // Show a message if it doesn't work
            System.out.println("There was an error reading car resources!");
            if(GUIRunner.DEBUG_MODE) e.printStackTrace();
            System.exit(1);
        }

        if(this.width==-1) {
            this.width=redCar.getWidth(null)*DEFAULT_SCALE;
        }
        if(this.height==-1) {
            this.height=redCar.getHeight(null)*DEFAULT_SCALE;
        }
    }
    public void draw(Graphics2D g2) {
        g2.rotate(Math.toRadians(direction*90), x, y);
        g2.drawImage(redCar, (int)(x-width/2), (int)(y-height/2), (int)width, (int)height, null);
        g2.rotate(-Math.toRadians(direction*90), x, y);
    }
    public void move(int dx, int dy) {
        int wX=getLevelX();
        int wY=getLevelY();
        int oX=getLevelOffsetX();
        int oY=getLevelOffsetY();
        int maxO=LevelManager.TILE_SIZE/2;
        WorldTile up=LevelManager.getWorldTile(wX, wY-1);
        WorldTile left=LevelManager.getWorldTile(wX-1, wY);
        WorldTile down=LevelManager.getWorldTile(wX, wY+1);
        WorldTile right=LevelManager.getWorldTile(wX+1, wY);
        WorldTile[] neighbors={up, left, down, right};

        // Check for upwards collision
//        System.out.println((direction==3)+","+(oX>=0)+","+left.isBarrier()+","+(Math.abs(oX)>maxO-width/1.5));
        if(direction==0&&oY>=0&&up.isBarrier()&&Math.abs(oY)>maxO-height/1.8) {
            turnRandomly(neighbors, up);
        }
        else if(direction==2&&oY<=0&&down.isBarrier()&&Math.abs(oY)>maxO-height/1.8) {
            turnRandomly(neighbors, down);
        }
        else if(direction==3&&oX>=0&&left.isBarrier()&&Math.abs(oX)>maxO-width/1.3) {
            turnRandomly(neighbors, left);
        }
        else if(direction==1&&oX<=0&&right.isBarrier()&&Math.abs(oX)>maxO-width/1.3) {
            turnRandomly(neighbors, right);
        }
        else {
            x += dx * ((double) speed / GUIRunner.fps);
            y += dy * ((double) speed / GUIRunner.fps);
        }
    }
    public boolean canTurnLeft() {
        return !LevelManager.getWorldTile(getLevelX()-1, getLevelY()).isBarrier();
    }
    public boolean canTurnRight() {
        return !LevelManager.getWorldTile(getLevelX()+1, getLevelY()).isBarrier();
    }
    public boolean canTurnUp() {
        return !LevelManager.getWorldTile(getLevelX(), getLevelY()-1).isBarrier();
    }
    public boolean canTurnDown() {
        return !LevelManager.getWorldTile(getLevelX(), getLevelY()+1).isBarrier();
    }

    private void turnRandomly(WorldTile[] neighbors, WorldTile not) {
        int index;
        do {
            index=(int)(Math.random()*neighbors.length);
        } while(neighbors[index]==not||neighbors[index].isBarrier());

        WorldTile towards=neighbors[index];
        int oX=towards.getX()-getLevelX();
        int oY=towards.getY()-getLevelY();

        if(oX==0&&oY==-1) direction=0;
        else if(oX==-1&&oY==0) direction=3;
        else if(oX==0&&oY==1) direction=2;
        else if(oX==1&&oY==0) direction=1;
    }

    public int getLevelX() {
        return (int)(x/LevelManager.TILE_SIZE);
    }
    public int getLevelY() {
        return (int)(y/LevelManager.TILE_SIZE);
    }
    public int getLevelOffsetX() {
        return (int)(-(x%LevelManager.TILE_SIZE-LevelManager.TILE_SIZE/2));
    }
    public int getLevelOffsetY() {
        return (int)(-(y%LevelManager.TILE_SIZE-LevelManager.TILE_SIZE/2));
    }

    // Various getters and setters
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getSpeed() {
        return speed;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }
    public int getDirection() {
        return direction;
    }
}
