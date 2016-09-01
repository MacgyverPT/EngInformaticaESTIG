import acm.graphics.*;
import acm.program.*;
import acm.gui.*; 
import acm.util.*; 
import acm.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;

/** Este código utiliza a <a href="http://jtf.acm.org/index.html">
 * biblioteca ACM Java Task Force</a> para a qual existe
 * <a href="http://jtf.acm.org/javadoc/student/index.html">esta documentação</a>.
 * <p>
 * Esta classe é um exemplo de um "GraphicsProgram": um programa com
 * uma janela na qual é possível desenhar objectos gráficos. 
 * <p>
 * Este template para o BlueJ foi criado na 
 * <a href="http://www.estig.ipbeja.pt">
 * Escola Superior de Tecnologia e Gestão</a> do
 * Instituto Politécnico de Beja, por João Paulo Barros, 
 * em 2007/03/23. 
 * Esta é a versão de 2011/10/13.
 * <hr>
 * Código base para o TP1 de P1 de 2011/2012
 * @author Miguel Rosa
 * @version December 8th, 2011 
 */
// Deve escrever o código utilizando a língua inglesa

public class Graph extends GraphicsProgram 
{
    /** É aqui que os objectos da classe Graph 
     *  começam realmente a fazer coisas. 
     *  Coloque o seu código entre as chavetas após o run().
     */    
    public void run()
    {
        IODialog dlg = new IODialog();
        String function = dlg.readLine("Insert the Function: ");
        double min = dlg.readDouble("Min value: ");
        double max = dlg.readDouble("Max value: ");
        double step = dlg.readDouble("Step: ");

        this.plot(300, 300, function, min, max, step);
        //        cx,  cy, function, min, max, step
        //this.plot(300, 300, "x ", 0, 9, 0.5);
    }

    /**
     * Plots a graph (without axis)
     * @param cx X coordinate for center of the plot
     * @param cy Y coordinate for center of the plot
     * @param min min value for the function
     * @param max max value for the function
     * @param step values for the function
     */
    public void plot(int cx, int cy, String function, double min, double max, double step)
    {
        final double PLOT_QUADRANT_SIZE = 300;
        final double H_SIZE = PLOT_QUADRANT_SIZE / 2;
        final double V_SIZE = PLOT_QUADRANT_SIZE / 2;

        FunctionEval fe = new FunctionEval(function);
        double sx = H_SIZE / (max - min);
        double sy = V_SIZE / this.scaleY(fe, min, max, step);

        sx = sy = Math.min(sx, sy);

        for (double x = min; x <= max; x += step)
        {
            double xp = cx + x * sx;
            double yp = cy - fe.eval(x) * sy;
            this.add( new GLine(xp, yp, xp, yp) );
        }

        this.drawCompletePlot(cx, cy, min, max, sx, step, H_SIZE, V_SIZE);
        this.labelToPoints(max, min, step, V_SIZE);
    }
    
    /**
     * This method show how many points have the function
     * @param max max value for the function
     * @param min min value for the function
     * @param step values for the function
     * @param H_SIZE value of the horizontal size for the plo
     * @param V_SIZE value of the vertical size for the plot
     */
    public void labelToPoints(double max, double min, double step, double V_SIZE){
        GLabel pointsLabel = new GLabel("Total de pontos da funcao: " + ((max - min) / step + 1) , 20, 400);
        this.add(pointsLabel);
    }

    /**
     * Draw a complete Plot (Axis and Scale)
     * @param cx X coordinate for center of the plot
     * @param cy Y coordinate for center of the plot
     * @param min min value for the function
     * @param max max value for the function
     * @param step values for the function
     * @param H_SIZE value of the horizontal size for the plot
     * @param V_SIZE value of the vertical size for the plot
     */
    public void drawCompletePlot(int cx, int cy, double min, double max, double sx, double step, double H_SIZE, double V_SIZE )
    {
        this.drawAxis(cx, cy, H_SIZE, V_SIZE);  
        this.drawScale(cx, cy, sx, H_SIZE, V_SIZE);  
    }

    /**
     * Draw the axis
     * @param cx X coordinate for center of the plot
     * @param cy Y coordinate for center of the plot
     * @param H_SIZE value of the horizontal size for the plot
     * @param V_SIZE value of the vertical size for the plot
     */
    public void drawAxis(double cx, double cy, double H_SIZE, double V_SIZE)
    {
        this.add( new GLine( cx - H_SIZE, cy, cx + H_SIZE, cy ) );
        this.add( new GLine( cx, cy - V_SIZE, cx, cy + V_SIZE ) );
        
        this.drawArrowsForAxis(cx, cy, H_SIZE, V_SIZE);
    }

    /**
     * Draw the arrows for Axis
     * @param cx X coordinate for center of the plot
     * @param cy Y coordinate for center of the plot
     * @param H_SIZE value of the horizontal size for the plot
     * @param V_SIZE value of the vertical size for the plot
     */
    public void drawArrowsForAxis(double cx, double cy, double H_SIZE, double V_SIZE)
    {
        //X
        this.add(new GLine( (cx + H_SIZE), (cy - 5), (cx + H_SIZE) + 5, cy ) );
        this.add(new GLine( (cx + H_SIZE), (cy + 5), (cx + H_SIZE) + 5, cy) );
        // Y
        this.add(new GLine( (cx + 5), (cy - V_SIZE), cx, (cy - V_SIZE) - 5) );
        this.add(new GLine( (cx - 5), (cy - V_SIZE), cx, (cy - V_SIZE) - 5) );
    }

    /**
     * Draw the plot's scale
     * @param cx X coordinate for center of the plot
     * @param cy Y coordinate for center of the plot
     * @param H_SIZE value of the horizontal size for the plot
     * @param V_SIZE value of the vertical size for the plot
     */
    public void drawScale(double cx, double cy, double sx, double H_SIZE, double V_SIZE)
    {
        for(double x = cx - H_SIZE; x < cx + H_SIZE; x += sx)
        {
            this.add(new GLine(x,cy - 2, x , cy + 2));
            //this.drawLabel(x - 2, cx , cx + 2) ;
        }
    }
    
    /**
     * draw labels on axis
     * @param cx X coordinate for center of the plot
     * @param cy Y coordinate for center of the plot
     * @param aux aux variable
     */
    public void drawLabel(double cx, double cy, double aux)
    {
        GLabel label = new GLabel("" + aux, cx - 5, cy);
        label.setFont(new Font("TimesNewRoman", Font.BOLD, 8));
        this.add(label);
    }

    /**
     * ??????
     */
    double scaleY(FunctionEval fe, double min, double max, double step)
    {
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;

        for (double x = min; x <= max; x += step)
        {
            double yp = fe.eval(x);
            if (yp > maxY) maxY = yp;
            if (yp < minY) minY = yp;
        }
        return maxY - minY;
    }

} 