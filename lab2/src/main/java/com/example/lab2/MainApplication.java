package com.example.lab2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import javax.swing.*;

public class MainApplication extends JPanel implements ActionListener {
    private static int maxWidth = 0;
    private static int maxHeight = 0;

    private double angle = 0;
    private double alpha = 1;
    private double delta = 0.01;

    double trianglePoints[][] = {
            {10, -75},
            {-10, 145},
            {260, 130}
    };

    double polygonPoints[][] = {
            {-20, -25},
            {-240, -40},
            {-260, 105},
            {-145, 155},
    };

    double linePoints[][] = {
            {-167.5, -100},
            {-167.5, -160},
            {-155.5, -160},
            {97.5, -160},
            {97.5, -100},
            {87.5, -100},
            {87.5, -150},
            {-157.5, -150},
            {-157.5, -100},
    };

    private GeneralPath buildPolygon(double points[][]) {
        GeneralPath polygon = new GeneralPath();
        polygon.moveTo(points[0][0], points[0][1]);

        for (int k = 1; k < points.length; k++) {
            polygon.lineTo(points[k][0], points[k][1]);
        }
        polygon.closePath();

        return polygon;
    }

    public MainApplication() {
        Timer timer = new Timer(5, this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(new Color(0, 128, 255));
        g2d.clearRect(0, 0, maxWidth, maxHeight);

        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        rh.put(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY
        );
        g2d.setRenderingHints(rh);

        g2d.translate(maxWidth / 2, maxHeight / 2);

        // Frame
        g2d.setColor(new Color(234, 150, 200));
        BasicStroke bs = new BasicStroke(10,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND
        );
        g2d.setStroke(bs);
        g2d.drawRect(-500, -350, 1000, 700);

        // Animations
        g2d.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER,
                (float)alpha)
        );
        g2d.scale(0.75, 0.75);
        g2d.rotate(angle, -150, -135);

        // Main entities
        g2d.setColor(new Color(255, 255, 255));
        g2d.fillRect(-150, -135, 230, 240);

        GeneralPath polygon = this.buildPolygon(this.polygonPoints);
        g2d.setColor(new Color(4, 255, 128));
        g2d.fill(polygon);

        GeneralPath triangle = this.buildPolygon(this.trianglePoints);
        GradientPaint gp = new GradientPaint(
                5, 25,
                new Color(255,155,0),
                20, 2,
                new Color(200,100,40),
                true
        );
        g2d.setPaint(gp);
        g2d.fill(triangle);

        GeneralPath line = this.buildPolygon(this.linePoints);
        g2d.setColor(new Color(254, 255, 0));
        g2d.fill(line);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lab 2");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1200);
        frame.setLocationRelativeTo(null);
        //frame.setResizable(false);
        frame.add(new MainApplication());
        frame.setVisible(true);

        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (alpha < 0.01 || alpha > 0.99) {
            delta = -delta;
        }

        alpha += delta;
        angle -= 0.01;
        repaint();
    }
}