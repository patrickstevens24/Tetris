/*
 * TCSS 305 - Winter 2016
 * Assignment 6 - Tetris
 */

package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;









import model.Board;


/**
 * The Tetris GUI.
 * 
 * @author Patrick Stevens
 * @version 1.0
 *
 */
public class TetrisGUI implements Observer {
    /**
     * Initial delay for the timer.
     */
    private static final int INITIAL_DELAY = 750;
    /**
     * Default size of frame.
     */
    private static final Dimension DEFAULT_SIZE = new Dimension(350, 533);
    /**
     * Level timer delay difference.
     */
    private static final int LEVEL_DELAY = 75;
    /**
     * the current delay.
     */
    private static int myDelay;
    /**
     * the timer.
     */
    private static Timer myTimer;
    /**
     * the main frame.
     */
    private final JFrame myFrame;
    /**
     * the tetris board.
     */
    private final Board myBoard;
    /**
     * the side panel with score and next piece.
     */
    private final SidePanel myNext;
    /**
     * the key listener.
     */
    private final KeyListener myListen;
    /**
     * the new game.
     */
    private JMenuItem myNewGame;
    /**
     * the current score.
     */
    private final BottomPanel myScore;
    /**
     * the listener for the pause key.
     */
    private final Pause myPauseListen;




    /**
     * Constructs a tetris game.
     */
    public TetrisGUI() {
        myFrame = new JFrame("Tetris");
        myBoard = new Board();
        myNext = new SidePanel(myBoard);
        myListen = new KeyListener(myBoard);
        myScore = new BottomPanel();
        myPauseListen = new Pause();

        start();
    }
    /**
     * starts the tetris game.
     */
    private void start() {
        myDelay = INITIAL_DELAY;
        myTimer = new Timer(INITIAL_DELAY, new SetTimer());
        final ImageIcon image = new ImageIcon("images/tetris2.jpg");
        myFrame.setIconImage(image.getImage());
        
        final BoardPanel board = new BoardPanel();
        myBoard.addObserver(board);
        myBoard.addObserver(this);
        final JPanel next = new JPanel();
        next.add(myNext, BorderLayout.NORTH);

        myFrame.addKeyListener(myListen);
        myFrame.addKeyListener(myPauseListen); 
        myFrame.setJMenuBar(menuBar());
        
        myFrame.add(next, BorderLayout.EAST);
        myFrame.add(board, BorderLayout.CENTER);
        myFrame.add(myScore, BorderLayout.SOUTH);

        
        myFrame.setSize(DEFAULT_SIZE);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setBackground(Color.RED);
        myFrame.setResizable(false);
        
        myTimer.start();
        myBoard.newGame();
        myFrame.setVisible(true);
    }

    /**
     * Actions when the pause button is pressed.
     */
    private void pauseGame() {
        if (myTimer.isRunning()) {
            myTimer.stop();
            JOptionPane.showMessageDialog(myFrame, "Paused", "PAUSED", 
                                          JOptionPane.INFORMATION_MESSAGE);
            myFrame.removeKeyListener(myListen);
        } else {
            myTimer.start();
            myFrame.addKeyListener(myListen);
        }
    }
    /**
     * Creates the menuBar.
     * @return the menuBar
     */
    private JMenuBar menuBar() {
        final JMenuBar menu = new JMenuBar();
        final JMenu file = new JMenu("File");
        file.add(newGame());
        file.add(endGame());
        file.add(exitGame());
        
        final JMenu info = new JMenu("Info");
        info.add(scoreWork());
        info.add(controlWork());
        
        menu.add(file);
        menu.add(info);


        return menu;
        
    }
    /**
     * Menu item how the controls work.
     * @return Controls info
     */
    private JMenuItem controlWork() {
        final JMenuItem control = new JMenuItem("Controls");
        
        final StringBuilder sb = new StringBuilder(135);
        sb.append("\tMOVE LEFT:  A\nMOVE RIGHT:  D\nDROP ONE:  S\n"
                        + "ROTATE CCW:  W\nROTATE CW:  E"
                        + "\nDROP:  Space\nPAUSE: P");
        
        control.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(myFrame, sb, "CONTROLS", 
                                              JOptionPane.INFORMATION_MESSAGE);
            }
            
        });
        return control;
    }
    /**
     * Menu item the score info.
     * @return Score info
     */
    private JMenuItem scoreWork() {
        final JMenuItem score = new JMenuItem("How Scoring Works");
        final StringBuilder sb = new StringBuilder(219);
        sb.append("For the number of lines completed\nthe scoring is as follows...\n\n"
                        + "Lines:     1            2             3               4\n\n"
                        + "Level N:   40xN    100xN   300xN   1200xN\n"
                        + "\n*You must complete 4 rows to move to next level*");
        score.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(myFrame, sb, "HOW SCORING WORKS", 
                                              JOptionPane.INFORMATION_MESSAGE);
            }
            
        });
        return score;
    }
    /**
     * menu item the exit button.
     * @return the exit action
     */
    private JMenuItem exitGame() {
        final JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myFrame.dispatchEvent(new WindowEvent(myFrame,
                                                          WindowEvent.WINDOW_CLOSING));
            }
            
        });
        return exit;
    }
    /**
     * The action when ending the game.
     */
    private void endAction() {
        myTimer.stop();
        JOptionPane.showMessageDialog(myFrame, "Game Over", "GAME OVER", 
                                      JOptionPane.INFORMATION_MESSAGE);
        myFrame.removeKeyListener(myListen);
        myNewGame.setEnabled(true);
    }
    /**
     * Menu item the end game button.
     * @return the endgame action
     */
    private JMenuItem endGame() {
        final JMenuItem end = new JMenuItem("End Game");
        end.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                endAction();
            }
        });
        return end;
    }
    /**
     * Method to set up a new game.
     */
    private void newGameAction() {
        myNext.resetScore();
        myBoard.newGame();
        myTimer.start();
        myFrame.addKeyListener(myListen);
        myDelay = INITIAL_DELAY;
        myNewGame.setEnabled(false);
    }
    
    
    /**
     * Menu item for the new game.
     * @return the newgame action
     */
    private JMenuItem newGame() {
        myNewGame = new JMenuItem("New Game");
        myNewGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                newGameAction();
            }
        });
       
        return myNewGame;
        
    }
    /**
     * Method when you level up. 
     */
    protected static void levelUp() {
        myDelay -= LEVEL_DELAY;
        myTimer.setDelay(myDelay);       
    }
    
    /**
     * Observer update method receives info. 
     * @param theBoard
     * @param theObject
     */
    @Override
    public void update(final Observable theBoard, final Object theObject) {
        if (theBoard instanceof Board) {
            if (theObject instanceof Boolean) {
                if ((boolean) theObject) {
                    endAction();
                    myNewGame.setEnabled(true);
                }
            }
        } 
    }
    /**
     * Inner class that sets the timer.
     * @author Patrick Stevens
     *
     */
    private class SetTimer implements ActionListener {
        /**
         * 
         */
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myBoard.step();
            myNewGame.setEnabled(false);     
        }
    }
    /**
     * Inner class that sets up the pause key.
     * @author Patrick
     *
     */
    public class Pause extends KeyAdapter {
        /**
         * The P key pressed.
         * @param theE
         */
        @Override
        public void keyPressed(final KeyEvent theE) {
            if (theE.getKeyCode() == KeyEvent.VK_P) {
                
                pauseGame();
            }
        }
    }
    

}
    