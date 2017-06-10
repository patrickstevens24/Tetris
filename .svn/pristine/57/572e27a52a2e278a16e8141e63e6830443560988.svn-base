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

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Board;
import model.Point;
import model.TetrisPiece;
/**
 * Class that sets up the panel with the next piece and the score. 
 * 
 * @author Patrick Stevens
 * @version 1.0
 * 
 */
public class SidePanel extends JPanel implements Observer {

    /**
     * Auto Generated serial number.
     */
    private static final long serialVersionUID = 168426035370641970L;
    /**
     * default dimension.
     */
    private static final Dimension DEFAULT_SIZE = new Dimension(100, 450);
    /**
     * the score x.
     */
    private static final int SCORE_X = 27;
    /**
     * the score infos y.
     */
    private static final int SCORE_INFO_X = 3;
    /**
     * the score infos x.
     */
    private static final int SCORE_INFO_Y = 330;
    /**
     * the score space between numbers.
     */
    private static final int SCORE_INFO_DIF = 20;
    /**
     * the default frozen points number.
     */
    private static final int FROZEN_DEFAULT = -2;
    /**
     * default height.
     */
    private static final int DEF_HEIGHT = 350;
    /**
     * height of block.
     */
    private static final int BLOCK_HEIGHT = 20;
    /**
     * x and y of block.
     */
    private static final int BLOCK_XY = 19;
    /**
     * move the block on the screen.
     */
    private static final int FIT_PANEL = 6;
    /**
     * next piece x.
     */
    private static final int NEXT_X = 15;
    /**
     * next piece y.
     */
    private static final int NEXT_Y = 115;
    /**
     * points for one line.
     */
    private static final int LINE_ONE = 40;
    /**
     * points for two lines.
     */
    private static final int LINE_TWO = 100;
    /**
     * points for three lines.
     */
    private static final int LINE_THREE = 300;
    /**
     * points for four lines.
     */
    private static final int LINE_FOUR = 1200;
    /**
     * level three.
     */
    private static final int LEVEL_THREE = 3;
    /**
     * level four.
     */
    private static final int LEVEL_FOUR = 4;
    /**
     * mod 5.
     */
    private static final int MOD_FIVE = 5;
    
    
    /**
     * The next Tetris Piece.
     */
    private TetrisPiece myNextPiece;
    /**
     * The point of the piece.
     */
    private Point[] myPoint;
    /**
     * The completed lines.
     */
    private int myCompleted;
    /**
     * The displayed completed lines.
     */
    private int myTotalCompleted;
    /**
     * The score. 
     */
    private int myScore;
    /**
     * The score from the frozen blocks.
     */
    private int myFrozenScore;
    /**
     * The level.
     */
    private int myLevel;
    /**
     * The next level.
     */
    private int myNextLevel;
    
    
    /**
     * Sets the default for the variables and constructs the side panel.
     * 
     * @param theBoard 
     */
    public SidePanel(final Board theBoard) {
        
        super();
        myNextLevel = 0;
        myLevel = 1;
        myFrozenScore = -1;
        myCompleted = 0;
        myScore = 0;
        final Board board = theBoard;
        board.addObserver(this);
        setPreferredSize(DEFAULT_SIZE);
        setBackground(Color.BLACK);
        
    }
    /**
     * Draws the next block and the score board.
     * @param theGraphics
     * 
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        
        g2d.setColor(Color.WHITE);
        g2d.drawString("SCORE", SCORE_X, SCORE_INFO_Y);
        g2d.drawString(gameScore(), SCORE_INFO_X , SCORE_INFO_Y + (SCORE_INFO_DIF * 2));
        g2d.drawString(gameLevel(), SCORE_INFO_X , SCORE_INFO_Y 
                       + (SCORE_INFO_DIF * SCORE_INFO_X));
        g2d.drawString(clearedLines(), SCORE_INFO_X , SCORE_INFO_Y + SCORE_INFO_DIF);
        
        
        g2d.drawString("NEXT PIECE", NEXT_X, NEXT_Y);
        
        if (myNextPiece != null) {
            myPoint = myNextPiece.getPoints();
            for (int i = 0; i < myPoint.length; i++) {
                
                g2d.setPaint(Color.RED);
                g2d.fillRect((int) (myPoint[i].x() * BLOCK_HEIGHT + getWidth() / FIT_PANEL), 
                             getHeight() - myPoint[i].y() * BLOCK_HEIGHT 
                             - DEF_HEIGHT, BLOCK_XY, BLOCK_XY);

                
            }
        }
    }
    /**
     * Method to reset the score board.
     */
    protected void resetScore() {
        myScore = 0;
        myTotalCompleted = 0;
        myFrozenScore = FROZEN_DEFAULT;
        myCompleted = 0;
        myLevel = 1;
        repaint();
    }
    /**
     * Method that calculates the games score.
     * @return the game score
     */
    private String gameScore() {
        
        if (myCompleted == 1) {
            myScore += LINE_ONE * myLevel;
        } else if (myCompleted == 2) {
            myScore += LINE_TWO * myLevel;
        } else if (myCompleted == LEVEL_THREE) {
            myScore += LINE_THREE * myLevel;
        } else if (myCompleted == LEVEL_FOUR) {
            myScore += LINE_FOUR * myLevel;
        }
        if (myFrozenScore >= 0) {
            myScore += myFrozenScore * LEVEL_FOUR;
        }
        myFrozenScore = 0;

        
        return "Score: " + myScore;
    }
    /**
     * method that calculates the games level.
     * @return the game level
     */
    private String gameLevel() {
        myNextLevel += myCompleted;
        if (myNextLevel % MOD_FIVE == 0 && myNextLevel != 0) {
            myLevel++;
            myNextLevel = 0;
            TetrisGUI.levelUp();
        } 
        return "Level: " + myLevel;
    }
    /**
     * Method that calculates the completed lines.
     * @return String of completed lines
     */
    private String clearedLines() {
        if (myCompleted >= 1) {
            myTotalCompleted += myCompleted;
        }
        myCompleted = 0;
        return "Cleared Lines: " + myTotalCompleted;
    }
    
    /**
     * Observer that receives information set to it.
     * 
     * @param theBoard
     * @param theObject
     */
    @Override
    public void update(final Observable theBoard, final Object theObject) {
        if (theBoard instanceof Board) {
            if (theObject instanceof TetrisPiece) {
                myNextPiece = (TetrisPiece) theObject; 
                repaint();
                myFrozenScore++;
            } else if (theObject instanceof List<?>) {
                repaint();
            } else if (theObject instanceof Boolean) {
                repaint();
            } else {
                myCompleted = ((Object[]) theObject).length;
            }
        }
    }
}
