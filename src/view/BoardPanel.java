/*
 * TCSS 305 - Winter 2016
 * Assignment 6 - Tetris
 */

package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.JPanel;

import model.Block;
import model.Board;

/**
 * Class that constructs the tetris board.
 * 
 * @author Patrick Stevens
 * @version 1.0
 *
 */
public class BoardPanel extends JPanel implements Observer {
    /**
     * Auto generated serial number.
     */
    private static final long serialVersionUID = 7784901084261352281L;
    /**
     * Board height.
     */
    private static final int BOARD_HEIGHT = 19;
    /**
     * Board width.
     */
    private static final int BOARD_WIDTH = 9;
    /**
     * List of blocks for the board.
     */
    private List<?> myBlock;


    /**
     * 
     * Constructs a new board panel.
     */
    public BoardPanel() {
        
        super();
        setBackground(Color.BLACK);
        
        myBlock = new ArrayList<>();
        
        final Board board = new Board();
        board.addObserver(this);
        setVisible(true);
        
        
    }
    /**
     * Draws the pieces on the board.
     * @param theGraphics
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        final Iterator<?> itr = myBlock.iterator();
        
        final Random random = new Random();
        final int red = random.nextInt(256);
        final int green = random.nextInt(256);
        final int blue = random.nextInt(256);

        final int location = getWidth() / 10;
        for (int i = BOARD_HEIGHT; i >= 0; i--) {
            final Block[] row = (Block[]) itr.next();
            
            for (int a = BOARD_WIDTH; a >= 0; a--) {
                if (row[a] != null) {

                    final Color randomColor = new Color(red, green, blue);
                    
                    g2d.setPaint(randomColor);

                    g2d.fill3DRect(a * location + 1, i * location 
                                   + 1, location - 1, location - 1, true);
                    g2d.setPaint(Color.BLACK);
                }
            }  
        }
    }
    /**
     * Observer method that receives info.
     * @param theBoard
     * @param theObject being sent
     */
    @Override
    public void update(final Observable theBoard, final Object theObject) {
        if (theBoard instanceof Board) {
            if (theObject instanceof List<?>) {
                myBlock = (List<?>) theObject;
                repaint();
            }
        }
    }
}
