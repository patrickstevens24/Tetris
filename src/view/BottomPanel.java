/*
 * TCSS 305 - Winter 2016
 * Assignment 6 - Tetris
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
/**
 * 
 * @author Patrick Stevens
 * @version 1.0
 *
 */
public class BottomPanel extends JPanel {
    
    /**
     * 
     */
    private static final long serialVersionUID = -1630580172992933274L;
    /**
     * 
     */
    private static final Dimension DEFAULT_SIZE = new Dimension(100, 20);
    /**
     * 
     */
    private static final int BOT_X = 130;
    /**
     * 
     */
    private static final int BOT_Y = 14;
    /**
     * 
     */
    public BottomPanel() {
        super();
        setPreferredSize(DEFAULT_SIZE);
        setBackground(Color.RED);
    }
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setColor(Color.WHITE);

        g2d.drawString("T  E  T  R  I  S", BOT_X, BOT_Y);
    }

}
