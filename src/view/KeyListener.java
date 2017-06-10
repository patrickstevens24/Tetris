/*
 * TCSS 305 - Winter 2016
 * Assignment 6 - Tetris
 */

package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.Board;

/**
 * Key listener class for the game movements.
 * 
 * @author Patrick Stevens
 * @version 1.0
 *
 */
public class KeyListener extends KeyAdapter {
    /**
     * The tetris board.
     */
    private final Board myBoard;
    /**
     * Constructs the key listeners.
     * 
     * @param theBoard 
     */
    public KeyListener(final Board theBoard) {
        super();
        myBoard = theBoard;

    }
    /**
     * Records the key pressed and moves the block accordingly.
     */
    @Override
    public void keyPressed(final KeyEvent theEvent) {
        switch (theEvent.getKeyCode()) {
            case KeyEvent.VK_D:
                myBoard.right();
                break;
                
            case KeyEvent.VK_A:
                myBoard.left();
                break;
            
            case KeyEvent.VK_S:
                myBoard.down();
                break;
                
            case KeyEvent.VK_SPACE:
                myBoard.drop();
                break;
                
            case KeyEvent.VK_W:
                myBoard.rotateCCW();
                break;
            
            case KeyEvent.VK_E:
                myBoard.rotateCW();
                break;

            default:
                break;
        }
    }

}
