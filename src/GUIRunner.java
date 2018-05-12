import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GUIRunner extends JFrame {
    public static final boolean DEBUG_MODE = true;
    public static final int RENDER_WIDTH=640;
    public static final int RENDER_HEIGHT=480;
    public static final double fps=60;

    public static void main(String[] args) {
        LevelManager.init();
        new GUIRunner();
    }

    GameGraphics graphics=new GameGraphics();

    public GUIRunner() {
        setTitle("RallyX");
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(graphics);
        addKeyListener(graphics);

        setVisible(true);
    }

    private class GameGraphics extends Component implements KeyListener {
        /*
        direction:
        0=up
        1=left
        2=down
        3=right
         */
        private int requestedDirection=-1;
        private boolean firstTime=true;

        Player player;

        private BufferedImage graphicsImg;

        public GameGraphics() {
            ScheduledExecutorService executor=Executors.newScheduledThreadPool(4);
            executor.scheduleAtFixedRate(new Runnable() {
                public void run() {
                    repaint();
                }
            }, 0, (int)(1000.0/fps), TimeUnit.MILLISECONDS);
        }
        @Override
        public void paint(Graphics g) {
            if(graphicsImg==null) {
                graphicsImg=new BufferedImage(RENDER_WIDTH, RENDER_HEIGHT, BufferedImage.TYPE_INT_RGB);
            }
            if(firstTime) {
                player=new Player(5*90+45, 5*90+45);

                firstTime=false;
            }

            // Handle key events/changes
            if(requestedDirection!=-1) {
                player.setDirection(requestedDirection);
                requestedDirection=-1;
            }
            switch(player.getDirection()) {
                case 0:
                    player.move(0, -1);
                    break;
                case 1:
                    player.move(1, 0);
                    break;
                case 2:
                    player.move(0, 1);
                    break;
                case 3:
                    player.move(-1, 0);
                    break;
            }

            Graphics2D g2=(Graphics2D)graphicsImg.getGraphics();
            g2.setBackground(Color.BLUE);
            g2.clearRect(0, 0, RENDER_WIDTH, RENDER_HEIGHT);

            WorldTile[][] tiles=LevelManager.getVisibleRegion(player);
            for(int y=0; y<tiles.length; y++) {
                for(int x=0; x<tiles[y].length; x++) {
                    WorldTile current=tiles[y][x];
                    if(current==null) continue;
                    LevelManager.drawTile(current, player, g2);
                }
            }
            player.draw(g2);

            // Draw the offscreen canvas to the main canvas
            g.drawImage(graphicsImg, 0, 0, getWidth(), getHeight(), null);
        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            switch(keyEvent.getKeyCode()) {
                case KeyEvent.VK_A:
                    if(player.canTurnLeft()) requestedDirection=3;
                    break;
                case KeyEvent.VK_D:
                    if(player.canTurnRight())requestedDirection=1;
                    break;
                case KeyEvent.VK_W:
                    if(player.canTurnUp())requestedDirection=0;
                    break;
                case KeyEvent.VK_S:
                    if(player.canTurnDown())requestedDirection=2;
                    break;
            }
        }
        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }
        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }
    }
}
