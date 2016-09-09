import java.awt.Color;
import acm.graphics.GCompound;

/**
 * This class create the bubble world (grid with bubbles with various colours).
 * 
 * @author Miguel Rosa (6219)
 * @version January 26th, 2012
 */
public class BubbleWorld extends GCompound
{
    private Bubble[][] bubbles;
    public static final Color[] bubbleColors = {Color.YELLOW, Color.GREEN, Color.RED, Color.CYAN,
                                                Color.BLUE, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE};

    /**
     * BubbleWorld class construtor
     * @param Bubbles[][] array with bubbles
     */
    public BubbleWorld(Bubble[][] bubbles){
        this.bubbles = bubbles;
    }

    /**
     * This method draws a grid with bubbles
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void draw(int x, int y){
        for(int line = 0; line < this.bubbles.length; line++)
        {
            for(int column = 0; column < this.bubbles[line].length; column++)
            {
                final double BUBBLE_WIDTH = this.bubbles[line][column].getWidth();
                final double BUBBLE_HEIGHT = this.bubbles[line][column].getHeight();
                this.add(this.bubbles[line][column], x + (column * BUBBLE_WIDTH),
                                                     y + (line * BUBBLE_HEIGHT) );
            }
        }
    }

    /**
     * This method gets the bubbles
     */
    public Bubble[][] getBubbles(){
        return this.bubbles;
    }
}
