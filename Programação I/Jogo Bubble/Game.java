import java.awt.Color;
import java.awt.event.MouseEvent;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import acm.io.*;
import acm.gui.*;
import java.applet.*;
import javax.swing.*;

/**
 * BubbleShooter main class. This class call's all class to "create" the game
 * 
 * @author Miguel Rosa (6219)
 * @version January 26th, 2012
 */
public class Game extends GraphicsProgram
{
    private AudioClip shootingBubbleSound;
    private BubbleWorld bubbleGrid;
    private Bubble shootingBubble;
    private double bubbleSize;
    private double angle;
    private int auxColors;
    public final int N_COLORS;

    /**
     * Game's construtor
     */
    public Game(){
        //this.bubbleSize = 90;
        final String shootingBubbleSound = "./sounds/WindowsNotify.wav";

        this.addMouseListeners();
        this.shootingBubbleSound = MediaTools.loadAudioClip(shootingBubbleSound);
        this.N_COLORS = this.getUserColors(auxColors);
    }

    /**
     * Main method. This method run's the game. First the game ask to user how many lines and columns he want. After (and using
     * that parameters), the game creates the bubble grid (on top) with bubbles and the launching bubble on bottom. The user also
     * insert how many rounds he want to conclue the game. The game runs until the runds are different from 0.
     */
    public void run()
    {
        this.createEntireWorld();
        
        int answer;
        int rounds = this.askUser("How many rounds you want?");

        JOptionPane.showMessageDialog(null, "Start game", "alert", JOptionPane.OK_OPTION);
        while( (rounds + 1) != 0)
        {
            this.waitForClick();
            this.shootingBubbleSound.play();
            while (!this.bubbleCollision())
            {
                this.moveBubble();
                this.pause(1);
            }
            rounds--;
            this.createShootingBubble();

            if(rounds == 0)
            {
                JOptionPane.showMessageDialog(null, "Game Over", "alert", JOptionPane.OK_OPTION);
                break;
            }
        }
    }

    /**
     * This method create the entire world. Call the sub-methods:
     * . createBubbleGrid
     * . createShootingBubble
     */
    public void createEntireWorld()
    {
        this.createBubbleGrid();
        this.createShootingBubble();
    }

    /**
     * Create the grid with the bubbles and colors. The user insert how many lines and columns he want
     * and using that value, the program create the bubbles grid (using the sub-method called "draw" from BubbleWorld class)
     */
    private void createBubbleGrid(){
        int auxLines, auxColumns;

        do{
            auxLines = this.askUser("How many LINES you want?\n(choose between 2 and 4)");
            if(auxLines < 2 || auxLines > 4){
                JOptionPane.showMessageDialog(null, "Wrong value.\nInsert value between 2 and 4.", "alert", JOptionPane.OK_OPTION);
            }
        }while(auxLines < 2 || auxLines > 4);

        do{
            auxColumns = this.askUser("How many COLUMNS you want?\n(choose between 10 and 20)");
            if(auxColumns < 10 || auxColumns > 20){
                JOptionPane.showMessageDialog(null, "Wrong value.\nInsert value between 10 and 20.", "alert", JOptionPane.OK_OPTION);
            }
        }while(auxColumns < 10 || auxColumns > 20);

        final int LINES = auxLines;
        final int COLUMNS = auxColumns;

        this.bubbleGrid = new BubbleWorld(this.getBubbles(LINES, COLUMNS, N_COLORS));
        this.add(this.bubbleGrid);
        this.bubbleGrid.draw(0, 0);      
    }
    
    /**
     * Method to ask some parameters to user
     * @return msg message to the user
     */
    public int askUser(String msg)
    {
        IODialog dialog = new IODialog();
        return dialog.readInt(msg);
    }
    
    /**
     * This method ask the user how many colors he want
     */
    private int getUserColors(int auxColors)
    {
        do
        {
            auxColors = this.askUser("How many COLOURS you want?\n(choose between 2 and 8)");
            if(auxColors < 2 || auxColors > 8)
            {
                JOptionPane.showMessageDialog(null, "Wrong value.\nInsert value between 2 and 8.", "alert", JOptionPane.OK_OPTION);
            }
        }
        while(auxColors < 2 || auxColors > 8);
        return auxColors;
    }
    
    /**
     * This method gets the bubble's grid (bubbles with size and color)
     * @param lines
     * @param columns
     * @return bubbles grid
     */
    private Bubble[][] getBubbles(int lines, int columns, final int N_COLORS)
    {
        int chooseColor;
        final int N_LINES = lines;
        final int N_COLUMNS = columns;
        this.bubbleSize = this.getWidth() / (double) N_COLUMNS;

        RandomGenerator gen = RandomGenerator.getInstance();

        Bubble[][] bubbles = new Bubble[N_LINES][N_COLUMNS];
        for (int line = 0; line < N_LINES; line++)
        {
            for (int column = 0; column < N_COLUMNS; column++)
            {
                final Color BUBBLE_COLOR = BubbleWorld.bubbleColors[gen.nextInt(0, N_COLORS - 1)]; //sort the color from array
                bubbles[line][column] = new Bubble(bubbleSize, bubbleSize, BUBBLE_COLOR); //create the bubble with size and color
            }
        }
        return bubbles;
        }

    /**
     * Create the shootingBubble (size, position and colors)
     */
    private void createShootingBubble(){
        RandomGenerator gen = RandomGenerator.getInstance();

        final double SIZE = this.bubbleSize;
        final double X = this.getWidth() / 2;
        final double Y = this.getHeight() - SIZE;

        final Color BUBBLE_COLOR = BubbleWorld.bubbleColors[gen.nextInt(0, N_COLORS - 1)];

        this.shootingBubble = new Bubble(SIZE, SIZE, BUBBLE_COLOR);
        this.add(this.shootingBubble, X, Y);
    }

    /**
     * This method calculate the bubble's moviment
     */
    private void moveBubble()
    {
        final int BUBBLE_MOVIMENT = 1;

        if(this.shootingBubble.getX() <= 0)
        {
            this.angle = 180 - this.angle;
        }

        if(this.shootingBubble.getX() >= this.getWidth())
        {
            this.angle = 180 - this.angle;
        }

        if(this.shootingBubble.getY() >= this.getHeight())
        {
            this.angle = 360 - this.angle;
        }

        this.shootingBubble.movePolar(BUBBLE_MOVIMENT, angle);
    }

    /**
     * Check the collision between the bubbles
     * @return false if the shooting bubble cannot found the bubble on top
     */
    private boolean bubbleCollision(){
        for(int line = 0; line < this.bubbleGrid.getBubbles().length; line++)
        {
            for(int column = 0; column < this.bubbleGrid.getBubbles()[line].length; column++)
            {
                //if(this.bubbleGrid.getBubbles()[line][column].contains(this.shootingBubble.getX(), this.shootingBubble.getY()))
                if(this.bubbleGrid.getBubbles()[line][column].getBounds().intersects(this.shootingBubble.getBounds()))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method "find" the position of the mouse click
     * @param ev MouseEvent
     */
    public void mousePressed(MouseEvent ev){
        int xPOS = ev.getX();
        int yPOS= ev.getY();

        this.angle = GMath.angle(this.shootingBubble.getX(), this.shootingBubble.getY(), xPOS, yPOS);
    }
}
