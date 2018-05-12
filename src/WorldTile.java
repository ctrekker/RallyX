import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class WorldTile {
    public static final byte ROAD=0;
    public static final byte BARRIER=1;

    private static Color roadColor=new Color(213, 159, 77);
    private static Color barrierColor=new Color(25, 145, 20);
    private static Image smoke=null;
    private static Image dirt=null;
    private static Image flag=null;
    private static Image bush=null;

    private byte type;
    private boolean isBush=false;
    private int x;
    private int y;
    public WorldTile(byte type) {
        this.type=type;

        try {
            if (smoke==null) {
                smoke=ImageIO.read(getClass().getResourceAsStream("img/Smoke.png"));
            }
            if (dirt==null) {
                dirt=ImageIO.read(getClass().getResourceAsStream("img/Dirt.png"));
            }
            if (flag==null) {
                flag=ImageIO.read(getClass().getResourceAsStream("img/Flag.png"));
            }
            if (bush==null) {
                bush=ImageIO.read(getClass().getResourceAsStream("img/Bush.png"));
            }
        } catch(IOException e) {
            // Show a message if it doesn't work
            System.out.println("There was an error reading world tile resources!");
            if(GUIRunner.DEBUG_MODE) e.printStackTrace();
            System.exit(1);
        }
    }

    public void draw(int x, int y, Graphics2D g2) {
        int cX=x-LevelManager.TILE_SIZE/2;
        int cY=y-LevelManager.TILE_SIZE/2;
        // Handle roads
        if(type==0) {
            g2.setPaint(roadColor);
        }
        // Handle barriers
        else if(type==1&&!isBush) {
            g2.setPaint(barrierColor);
        }
        // Handle bushes
        else if(type==1) {
            g2.drawImage(bush, cX, cY, LevelManager.TILE_SIZE, LevelManager.TILE_SIZE, null);
        }

        if(type<2&&!isBush) g2.fillRect(cX, cY, LevelManager.TILE_SIZE, LevelManager.TILE_SIZE);
    }

    public byte getType() {
        return type;
    }
    public void setType(byte type) {
        this.type = type;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public boolean isBush() {
        return isBush;
    }
    public void setBush(boolean bush) {
        isBush = bush;
    }

    public boolean isBarrier() {
        return type==BARRIER;
    }
}
