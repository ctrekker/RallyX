import java.awt.*;

public class LevelManager {
    public static int TILE_SIZE=85;

    private static int currentLevel=0;
    private static byte[][][] levelData={
        {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0},
            {0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1},
            {1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1},
            {1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1},
            {1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1},
            {1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0},
            {0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1},
            {1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1},
            {1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1},
            {1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1},
            {1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        }
    };
    private static WorldTile[][][] tiles=new WorldTile[levelData.length][levelData[0].length][levelData[0][0].length];
    public static void init() {
        for(int z=0; z<levelData.length; z++) {
            for(int y=0; y<levelData[z].length; y++) {
                for(int x=0; x<levelData[z][y].length; x++) {
                    tiles[z][y][x]=new WorldTile(levelData[z][y][x]);
                    tiles[z][y][x].setX(x);
                    tiles[z][y][x].setY(y);
                }
            }
        }
    }


    public static WorldTile getWorldTile(int x, int y) {
        try {
            return tiles[currentLevel][y][x];
        } catch(ArrayIndexOutOfBoundsException e) {
            WorldTile bush=new WorldTile(WorldTile.BARRIER);
            bush.setBush(true);
            bush.setX(x);
            bush.setY(y);
            return bush;
        }
    }

    public static int getWorldWidth() {
        return levelData[currentLevel][0].length;
    }
    public static int getWorldHeight() {
        return levelData[currentLevel].length;
    }
    public static void nextLevel() {
        currentLevel++;
    }
    public static int getLevel() {
        return currentLevel;
    }
    public static void setLevel(int level) {
        currentLevel=level;
    }

    public static WorldTile[][] getVisibleRegion(Player player) {
        WorldTile[][] out=new WorldTile[(int)((double)GUIRunner.RENDER_HEIGHT/TILE_SIZE)+3][(int)((double)GUIRunner.RENDER_WIDTH/TILE_SIZE)+4];

        for(int y=0; y<out.length; y++) {
            for(int x=0; x<out[y].length; x++) {
                int actualX=x-out[y].length/2+player.getLevelX();
                int actualY=y-out.length/2+player.getLevelY()+1;
                out[y][x]=getWorldTile(actualX, actualY);
            }
        }

        return out;
    }

    public static void drawTile(WorldTile current, Player player, Graphics2D g2) {
        current.draw((current.getX()-player.getLevelX())*LevelManager.TILE_SIZE+player.getLevelOffsetX()+GUIRunner.RENDER_WIDTH/2, (current.getY()-player.getLevelY())*LevelManager.TILE_SIZE+player.getLevelOffsetY()+GUIRunner.RENDER_HEIGHT/2, g2);
    }
}
