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
    public static void main(String[] args) {
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
        0=left
        1=right
        2=up
        3=down
         */
        private int direction=-1;
        private int requestedDirection=-1;
        private final double fps=60;

        private BufferedImage graphicsImg;

        public GameGraphics() {
            ScheduledExecutorService executor=Executors.newScheduledThreadPool(4);
            executor.scheduleAtFixedRate(new Runnable() {
                public void run() {
                    repaint();
                }
            }, 1000, (int)(1000.0/fps), TimeUnit.MILLISECONDS);
        }
        @Override
        public void paint(Graphics g) {
            if(graphicsImg==null) {
                graphicsImg=new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            }

            Graphics2D g2=(Graphics2D)graphicsImg.getGraphics();
            g2.setBackground(Color.BLACK);
            g2.clearRect(0, 0, getWidth(), getHeight());

            // Draw the offscreen canvas to the main canvas
            g.drawImage(graphicsImg, 0, 0, null);
        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            switch(keyEvent.getKeyCode()) {
                case KeyEvent.VK_A:
                    requestedDirection=0;
                    break;
                case KeyEvent.VK_D:
                    requestedDirection=1;
                    break;
                case KeyEvent.VK_W:
                    requestedDirection=2;
                    break;
                case KeyEvent.VK_S:
                    requestedDirection=3;
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
