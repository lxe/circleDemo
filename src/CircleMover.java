import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: lxe
 * Date: 3/25/13
 * Time: 9:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class CircleMover extends JComponent implements KeyListener {
    int x, y, velocity;
    boolean right, left, up, down;

    public CircleMover() {
        setPreferredSize(new Dimension(640, 480));
        velocity = 5;

        java.util.Timer keyTimer = new java.util.Timer();

        keyTimer.schedule(new TimerTask() {
            public void run() {
                checkKeyStrokes();
            }
        }, 0, 20);
    }

    public void checkKeyStrokes() {
        if (right) x += velocity;
        if (left)  x -= velocity;
        if (up)    y -= velocity;
        if (down)  y += velocity;
        repaint();
    }

    public void toggleKey(KeyEvent e, boolean pressed) {
        switch (e.getKeyCode()) {
            case 37: left  = pressed; break;
            case 38: up    = pressed; break;
            case 39: right = pressed; break;
            case 40: down  = pressed; break;
            default: break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        toggleKey(e, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggleKey(e, false);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D gg = (Graphics2D)g;

        gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        gg.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, 60, 60);
        gg.fill(circle);
    }

    public static void main (String args[]) {
        CircleMover cm = new CircleMover();
        JFrame mainWindow = new JFrame();

        mainWindow.add(cm);
        mainWindow.pack();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainWindow.addKeyListener(cm);
        mainWindow.setVisible(true);
    }
}