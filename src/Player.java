import java.awt.*;

public class Player extends Car {
    public Player(double x, double y) {
        super(x, y);
    }
    public Player(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.rotate(Math.toRadians(direction*90), GUIRunner.RENDER_WIDTH/2, GUIRunner.RENDER_HEIGHT/2);
        g2.drawImage(blueCar, (int)(GUIRunner.RENDER_WIDTH/2-width/2), (int)(GUIRunner.RENDER_HEIGHT/2-height/2), (int)width, (int)height, null);
        g2.rotate(Math.toRadians(-direction*90), GUIRunner.RENDER_WIDTH, GUIRunner.RENDER_HEIGHT);
    }
}
