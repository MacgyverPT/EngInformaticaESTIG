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
 * Instituto Politécnico de Beja, por Joao Paulo Barros, 
 * em 2007/03/23. 
 * Esta é a versão de 2008/09/28.
 * <hr>
 * @author Miguel Rosa
 * @version 2008/11/10
 */
 // Deve escrever o código utilizando a língua inglesa

public class Illusions extends GraphicsProgram 
{
    /** É aqui que os objectos da classe Illusions 
     *  começam realmente a fazer coisas. 
     *  Coloque o seu código entre as chavetas.
     */    
    public void run()
    {
        // ILLUSION 1
                
        final int N_BARS = 14;
        final int X_MARGIN = 20;
        final int Y_MARGIN =10;
        final int BAR_WIDTH = 20;
        final int BAR_HEIGHT = 300;
        final int BAR_INTERVAL = 50;
        final int LINE_X_OFFSET = BAR_INTERVAL / 2;
        final int SLOPE = 140;
        final int END_BAR_SLOPE =  Y_MARGIN + BAR_HEIGHT + SLOPE / 2;
        int nLines = this.readInt("Indique quantidade de linhas por barra: ",
                                  1, 100);
        final int Y_LINE_INTERVAL =
                (int)( (double)BAR_HEIGHT / (nLines + 1) + 0.5 );
        for(int b = 0; b < N_BARS; b++)
        {
            // draw lines
            int barX = X_MARGIN + b * (BAR_WIDTH + BAR_INTERVAL);
            for(int ln = 1; ln <= nLines; ln++) // to avoid +1 in ln
            {
                int y = END_BAR_SLOPE - ln * Y_LINE_INTERVAL;
                this.add(new GLine(barX - LINE_X_OFFSET,
                                   y,
                                   barX + BAR_WIDTH + LINE_X_OFFSET,
                                   y - SLOPE));
            }
            // draw bar
            GRect bar = new GRect(barX, Y_MARGIN, BAR_WIDTH, BAR_HEIGHT);
            bar.setFilled(true);
            bar.setFillColor(Color.YELLOW);
            this.add(bar);
        }
        
        this.add(new GLabel("Click to continue", this.getWidth() / 2 - 20, this.getHeight() - 20));
        this.waitForClick();
        this.removeAll();
        
        
        // ILLUSION 2
        final int N_HORIZONTAL_BARS = 7;
        final int N_VERTICAL_BARS = 8;
        final int BAR_THICKNESS = 8;
        
        int squareSize = this.readInt("Indique comprimento do lado do quadrado: ", 10, 100);
        final int BAR_STEP =  squareSize + BAR_THICKNESS;
        final int MARGIN = BAR_STEP / 2;
        
        final int SQUARE_WIDTH = BAR_STEP * N_VERTICAL_BARS;
        final int SQUARE_HEIGHT = BAR_STEP * N_HORIZONTAL_BARS;
        
        // black "square"
        GRect board = new GRect(0, 0, SQUARE_WIDTH, SQUARE_HEIGHT);
        board.setFilled(true);
        board.setFillColor(Color.BLACK);
        this.add(board);

        for(int hb = 0; hb < N_HORIZONTAL_BARS; hb++)
        {
            int y = MARGIN + hb * BAR_STEP;
            GRect bar = new GRect(0, y, SQUARE_WIDTH, BAR_THICKNESS);
            bar.setFilled(true);
            bar.setFillColor(Color.GRAY);
            bar.setColor(Color.GRAY);
            this.add(bar);
        }
        
        for(int vb = 0; vb < N_VERTICAL_BARS; vb++)
        {
            int x = MARGIN + vb * BAR_STEP;
            GRect bar = new GRect(x, 0, BAR_THICKNESS, SQUARE_HEIGHT);
            bar.setFilled(true);
            bar.setFillColor(Color.GRAY);
            bar.setColor(Color.GRAY);
            this.add(bar);
        }
        
        for(int hb = 0; hb < N_HORIZONTAL_BARS; hb++)
        {
            for(int vb = 0; vb < N_VERTICAL_BARS; vb++)
            {
                int x = MARGIN + vb * BAR_STEP;
                int y = MARGIN + hb * BAR_STEP;
                GOval ball = new GOval(x, y, BAR_THICKNESS, BAR_THICKNESS);
                ball.setFilled(true);
                ball.setFillColor(Color.WHITE);
                ball.setColor(Color.WHITE);
                this.add(ball);
            }
        }
        
        this.add(new GLabel("Click to continue", this.getWidth() / 2 - 20, this.getHeight() - 20));
        this.waitForClick();
        this.removeAll();
        
        // ILLUSION 3
        final int N_RADIUS = 40;
        final int RADIUS_LENGTH = 200;
        final int RADIUS_THICKNESS = 3;
        final int STEP = 360 / N_RADIUS;
        final int CX = this.getWidth() / 2;
        final int CY = this.getHeight() / 2;
        for(int alfa = 0; alfa < 180; alfa += STEP)
        {
            GPolygon diam = new GPolygon();
            diam.addVertex(-RADIUS_LENGTH, -RADIUS_THICKNESS);
            diam.addVertex( RADIUS_LENGTH, -RADIUS_THICKNESS);
            diam.addVertex( RADIUS_LENGTH,  RADIUS_THICKNESS);
            diam.addVertex(-RADIUS_LENGTH,  RADIUS_THICKNESS);
            diam.rotate(alfa);
            diam.setFilled(true);
            diam.setFillColor(Color.BLACK);
            diam.setColor(Color.BLACK);
            this.add(diam, CX, CY);
        }

        final int N_RED_BARS = 6;
        final int RED_BAR_STEP = (RADIUS_LENGTH * 2 ) /  (N_RED_BARS + 1);
        final int x = CX - (int)(2.5 * RED_BAR_STEP) - RADIUS_THICKNESS;
        final int y = CY - RADIUS_LENGTH;

        for(int nb = 0; nb < N_RED_BARS; nb++)
        {
            GRect bar = new GRect(x + nb * RED_BAR_STEP, y, RADIUS_THICKNESS, RADIUS_LENGTH * 2);
            bar.setFilled(true);
            bar.setFillColor(Color.RED);
            bar.setColor(Color.RED);
            this.add(bar);
        }
    }
} 