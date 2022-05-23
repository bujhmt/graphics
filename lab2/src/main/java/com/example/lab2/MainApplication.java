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

    double crossPoints[][] = {
            {47, 42},
            {49, 42},
            {49, 47},
            {54, 47},
            {54, 49},
            {49, 49},
            {49, 54},
            {47, 54},
            {47, 49},
            {42, 49},
            {42, 47},
            {47, 47}
    };

    public MainApplication() {
        Timer timer = new Timer(5, this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.BLACK);
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
        g2d.setColor(new Color(255, 140, 0));
        BasicStroke frameStroke = new BasicStroke(4,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_BEVEL
        );
        g2d.setStroke(frameStroke);
        g2d.drawRect(-350, -225, 700, 450);

        // Blur animation
        g2d.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER,
                (float)alpha)
        );

        // Rotate animation
        g2d.rotate(angle);

        BasicStroke baseStroke = new BasicStroke(49,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_BEVEL
        );
        g2d.setStroke(baseStroke);

        // Primitives
        g2d.setColor(Color.BLUE);
        g2d.fillOval(0, 0, 96, 96);

        g2d.setColor(Color.BLACK);
        g2d.drawOval(8, 8, 80, 80);

        g2d.setColor(Color.RED);
        g2d.fillOval(16, 16, 64, 64);

        g2d.setColor(Color.BLACK);
        g2d.drawOval(24, 24, 48, 48);

        GradientPaint gp = new GradientPaint(5, 25,
                Color.YELLOW, 20, 2, Color.BLUE, true);
        g2d.setPaint(gp);
        g2d.fillOval(32, 32, 32, 32);

        g2d.setColor(Color.BLACK);
        g2d.drawOval(40, 40, 16, 16);

        // Non-primitive
        GeneralPath cross = new GeneralPath();
        cross.moveTo(crossPoints[0][0], crossPoints[0][1]);

        for (int k = 1; k < crossPoints.length; k++) {
            cross.lineTo(crossPoints[k][0], crossPoints[k][1]);
        }

        cross.closePath();

        g2d.fill(cross);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("lab 2");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
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
        angle += 0.01;
        repaint();
    }
}