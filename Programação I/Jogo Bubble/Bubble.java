import java.awt.Color;
import acm.graphics.GCompound;
import acm.graphics.GOval;

/**
 * This class create one bubble with all parameters (size, color...).
 * 
 * @author Miguel Rosa (6219)
 * @version January 26th, 2012
 */
public class Bubble extends GCompound
{
    private GOval circle;
    final private Color CIRCLE_COLOR;
    final private double WIDTH;
    final private double HEIGHT;

    /**
     * Bubble class construtor
     * @param width bubble's width
     * @param height bubble's height
     * @param color bubble's color
     */
    public Bubble(double width, double height, Color color){
        this.CIRCLE_COLOR = color;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.drawCircle(this.WIDTH, this.HEIGHT);
    }

    /**
     * This method draw a circle (bubble)
     * @param width bubble's width
     * @param height bubble's height
     */
    private void drawCircle(double width, double height){
        this.circle = new GOval(width, height);
        this.circle.setFillColor(this.CIRCLE_COLOR);
        this.circle.setFilled(true);
        this.add(this.circle);
    }

    /**
     * This method gets de bubble's width
     */
    public double getWidth(){
        return this.WIDTH;
    }

    /**
     * This method gets bubble's height
     */
    public double getHeight(){
        return this.HEIGHT;
    }
}
