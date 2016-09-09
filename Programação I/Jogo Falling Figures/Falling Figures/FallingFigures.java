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
 * @author Miguel Rosa (6219)
 * @version (número de versão ou data) 
 */
// Deve escrever o código utilizando a língua inglesa
public class FallingFigures extends GraphicsProgram
{
   private int             objectSize;
   private RandomGenerator rgen = RandomGenerator.getInstance();
   private double          a;
   private double          b;
   private int             count;
   private GLabel          countLabel;
   private GOval           circle;
   private GRect           square;
   private int             c;
   private int             nPlays;
   private boolean moving;
   
   public FallingFigures()
   {
      this.addMouseListeners();
      this.c = rgen.nextInt(0, 1);
      this.count = 0;
      this.countLabel = new GLabel(count + "", 0, 130);
      this.countLabel.setFont("SansSerif-50"); // fonte maior
      this.circle = new GOval(0, 0, objectSize, objectSize);
      this.circle.setColor(Color.BLACK);
      this.circle.setFilled(true);
      this.circle.setFillColor(Color.ORANGE);
      this.square = new GRect(0, 0, objectSize, objectSize);
      this.square.setColor(Color.BLACK);
      this.square.setFilled(true);
      this.square.setFillColor(Color.GREEN);
   }

   public void run()
   {
      int nPlays = this.askInt("Indique o nº de Jogadas:", 5, 30);
      int objectSize = this.askInt("Indique o tamanho dos objectos:",
               10, 100);

      this.add(this.countLabel);
      this.waitForClick();
      this.circle = new GOval(0, 0, objectSize, objectSize);
      this.circle.setColor(Color.BLACK);
      this.circle.setFilled(true);
      this.circle.setFillColor(Color.ORANGE);
      this.square = new GRect(0, 0, objectSize, objectSize);
      this.square.setColor(Color.BLACK);
      this.square.setFilled(true);
      this.square.setFillColor(Color.GREEN);
     
      for (int n = 0; n < nPlays; n++)
      {
         this.c = rgen.nextInt(0, 1);
         this.objectMaker(c);
         this.movingObjects();
      }
   }

   private void movingObjects()
   {
      this.moving = true;
      while (this.moving)
      {
         this.circle.move(0, 15);
         this.square.move(0, 15);
         this.pause(100);
         if (this.circle.getY() >= this.getHeight()
                  || this.square.getY() >= this.getHeight())
         {
            this.moving = false;
         }
      }
   }

   private int askInt(String message, int min, int max)
   {
      IODialog dlg = new IODialog();
      int res = 0;
      do
      {
         res = dlg.readInt(message + " (" + min + "-" + max + "):",
                  min, max);
      } while (res < min || res > max);
      return res;
   }

   private void objectMaker(int c)
   {
      this.c = rgen.nextInt(0, 1);
      double a = rgen.nextDouble(0, 600);
      double b = rgen.nextDouble(0, 600);

      if (this.c == 1)
      {
         this.circle.setLocation(a, 0);
         this.add(this.circle);
      }else
      {
         this.square.setLocation(b, 0);
         this.add(this.square);
      }
   }

   public void mousePressed(MouseEvent e)
   {
      double x = e.getX();
      double y = e.getY();
      this.testClick(x, y);
      if (this.circle.contains(x, y) || this.square.contains(x, y))
      {
         this.count = this.count + 1;
         this.countLabel.setLabel(this.count + "");
         this.moving = false;
         this.remove(this.circle);
         this.remove(this.square);

      }
   }

   private void incrementCounter()
   {
      this.countLabel.setLabel(this.count + "");
   }

   private void testClick(double x, double y)
   {
      if (this.circle.contains(x, y))
      {
         this.incrementCounter();
      }
      if (this.square.contains(x, y))
      {
         this.incrementCounter();
      }
   }
}
