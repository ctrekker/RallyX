public class LevelManager {
    private static int currentLevel=0;
    private static byte[][][] levelData={
        {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
            {1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
            {1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
            {1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
            {1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        }
    };
    private static WorldTile[][][] tiles=new WorldTile[levelData.length][levelData[0].length][levelData[0][0].length];
    private static void init() {
        for(int z=0; z<levelData.length; z++) {
            for(int y=0; y<levelData[z].length; y++) {
                for(int x=0; x<levelData[z][y].length; x++) {
                    tiles[z][y][x]=new WorldTile(levelData[z][y][x]);
                }
            }
        }
    }


    private static WorldTile getWorldTile(int x, int y) {
        return tiles[currentLevel][y][x];
    }

    private static int getWorldWidth() {
        return levelData[currentLevel][0].length;
    }
    private static int getWorldHeight() {
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
}
