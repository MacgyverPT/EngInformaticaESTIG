import acm.graphics.*;
import acm.program.*;
import acm.gui.*;
import acm.util.*;
import acm.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;

/** Este codigo utiliza a <a href="http://jtf.acm.org/index.html">
 * biblioteca ACM Java Task Force</a> para a qual existe
 * <a href="http://jtf.acm.org/javadoc/student/index.html">esta documentacao</a>.
 * <p>
 * Esta classe e um exemplo de um "GraphicsProgram": um programa com
 * uma janela na qual e possivel desenhar objectos graficos. 
 * <p>
 * Este template para o BlueJ foi criado na 
 * <a href="http://www.estig.ipbeja.pt">
 * Escola Superior de Tecnologia e Gestao</a> do
 * Instituto Politecnico de Beja, por Joao Paulo Barros, 
 * em 2007/03/23. 
 * Esta e a versao de 2008/09/28.
 * <hr>
 * @author miguel rosa 
 * @version 18-12-2008 
 */
// Deve escrever o codigo utilizando a lingua inglesa
public class Game extends GraphicsProgram
{

   private G3DRect racketL;
   private G3DRect racketR;
   private GOval   ball;
   private boolean leftUp;
   private boolean leftDown;
   private boolean rightUp;
   private boolean rightDown;
   private GRect   fieldLine;
   private GRect   fieldLine1;
   private GRect   fieldLine2;
   private GRect   fieldLine3;
   private GRect   fieldLine4;
   private GRect   scoreLine1;
   private GRect   scoreLine2;
   private GObject cP1;
   private GObject cP2;
   private GObject cP3;
   private GObject cP4;
   private int     dx;
   private int     dy;
   private GLabel  countLabelL;
   private GLabel  countLabelR;
   private int     countL;
   private int     countR;
   private GImage  image1;
   private GImage  image2;
   private GImage  image3;
   private int     bG;
   private int     r;
   private int     b;

   public Game()
   {
      this.countL = 0;
      this.countR = 0;
   }

   /**
    * Handles event generated when a key is pressed
    * @param e the KEYEvent to handle
    */
   public void keyPressed(KeyEvent e)
   {
      int kc = e.getKeyCode();
      switch (kc)
      {
         case KeyEvent.VK_Q:
            this.leftUp = true;
            break;
         case KeyEvent.VK_A:
            this.leftDown = true;
            break;
         case KeyEvent.VK_UP:
            this.rightUp = true;
            break;
         case KeyEvent.VK_DOWN:
            this.rightDown = true;
            break;
      }
   }

   /**
    * Handles event generated when a key is released
    * @param e the KEYEvent to handle
    */
   public void keyReleased(KeyEvent e)
   {
      int kc = e.getKeyCode();
      switch (kc)
      {
         case KeyEvent.VK_Q:
            this.leftUp = false;
            break;
         case KeyEvent.VK_A:
            this.leftDown = false;
            break;
         case KeyEvent.VK_UP:
            this.rightUp = false;
            break;
         case KeyEvent.VK_DOWN:
            this.rightDown = false;
            break;
      }
   }

   /**
    * Allows the player to select a backgroung image
    */
   private void backGround(int bG)
   {
      if (bG == 1)
      {
         this.image1 = new GImage("/backgrounds/backGround1.jpg");
         this.image1.setSize(this.getWidth(), this.getHeight());
         this.image1.sendBackward();
         this.add(this.image1);
      }
      if (bG == 2)
      {
         this.image2 = new GImage("/backgrounds\backGround2.jpg");
         this.image2.setSize(this.getWidth(), this.getHeight());
         this.image2.sendBackward();
         this.add(this.image2);
      }
      if (bG == 3)
      {
         this.image3 = new GImage("/backgrounds\backGround3.jpg");
         this.image3.setSize(this.getWidth(), this.getHeight());
         this.image3.sendBackward();
         this.add(this.image3);
      }
   }

   /**
    * Allows the player to select the rackets color
    */
   private void racketColor(int r)
   {
      if (r == 1)
      {
         this.racketL = new G3DRect(50, 0, 10, 50);
         this.racketL.setColor(Color.WHITE);
         this.racketL.setFillColor(Color.GREEN);
         this.racketL.setFilled(true);
         this.add(this.racketL);

         this.racketR = new G3DRect(this.getWidth() - 60, 0, 10, 50);
         this.racketR.setFillColor(Color.GREEN);
         this.racketR.setColor(Color.WHITE);
         this.racketR.setFilled(true);
         this.add(this.racketR);
      }
      if (r == 2)
      {
         this.racketL = new G3DRect(50, 0, 10, 50);
         this.racketL.setColor(Color.WHITE);
         this.racketL.setFillColor(Color.ORANGE);
         this.racketL.setFilled(true);
         this.add(this.racketL);

         this.racketR = new G3DRect(this.getWidth() - 60, 0, 10, 50);
         this.racketR.setFillColor(Color.ORANGE);
         this.racketR.setColor(Color.WHITE);
         this.racketR.setFilled(true);
         this.add(this.racketR);
      }
      if (r == 3)
      {
         this.racketL = new G3DRect(50, 0, 10, 50);
         this.racketL.setColor(Color.WHITE);
         this.racketL.setFillColor(Color.BLACK);
         this.racketL.setFilled(true);
         this.add(this.racketL);

         this.racketR = new G3DRect(this.getWidth() - 60, 0, 10, 50);
         this.racketR.setFillColor(Color.BLACK);
         this.racketR.setColor(Color.WHITE);
         this.racketR.setFilled(true);
         this.add(this.racketR);
      }
   }

   private void ballSelection(int b)
   {
      if (b == 1)
      {
         this.ball = new GOval(this.fieldLine.getX() - 3.5,
                  this.fieldLine.getHeight() / 2, 12, 12);
         this.ball.setColor(Color.WHITE);
         this.ball.setFillColor(Color.BLACK);
         this.ball.setFilled(true);
         this.add(this.ball);
      }
      if (b == 2)
      {
         this.ball = new GOval(this.fieldLine.getX() - 3.5,
                  this.fieldLine.getHeight() / 2, 12, 12);
         this.ball.setColor(Color.WHITE);
         this.ball.setFillColor(Color.YELLOW);
         this.ball.setFilled(true);
         this.add(this.ball);
      }
      if (b == 3)
      {
         this.ball = new GOval(this.fieldLine.getX() - 3.5,
                  this.fieldLine.getHeight() / 2, 12, 12);
         this.ball.setColor(Color.WHITE);
         this.ball.setFillColor(Color.RED);
         this.ball.setFilled(true);
         this.add(this.ball);
      }
   }

   /**
    * Specifies the gaming area
    */
   private void gamingArea()
   {
      this.fieldLine = new GRect((this.getWidth() / 2), 0, 5, this
               .getHeight());
      this.fieldLine.setFillColor(Color.BLACK);
      this.fieldLine.setFilled(true);
      this.add(this.fieldLine);

      this.fieldLine1 = new GRect((this.getWidth() / 2), 5);
      this.fieldLine1.setFillColor(Color.BLACK);
      this.fieldLine1.setFilled(true);
      this.add(this.fieldLine1);

      this.fieldLine2 = new GRect((this.getWidth() / 2), 0, (this
               .getWidth() / 2), 5);
      this.fieldLine2.setFillColor(Color.BLACK);
      this.fieldLine2.setFilled(true);
      this.add(this.fieldLine2);

      this.fieldLine3 = new GRect(0, this.getHeight() - 5, (this
               .getWidth() / 2), 5);
      this.fieldLine3.setFillColor(Color.BLACK);
      this.fieldLine3.setFilled(true);
      this.add(this.fieldLine3);

      this.fieldLine4 = new GRect((this.getWidth() / 2), this
               .getHeight() - 5, this.getWidth(), 5);
      this.fieldLine4.setFillColor(Color.BLACK);
      this.fieldLine4.setFilled(true);
      this.add(this.fieldLine4);

      this.scoreLine1 = new GRect(5, this.getHeight());
      this.scoreLine1.setFillColor(Color.BLACK);
      this.scoreLine1.setFilled(true);
      this.add(this.scoreLine1);

      this.scoreLine2 = new GRect(this.getWidth() - 5, 0, 5, this
               .getWidth());
      this.scoreLine2.setFillColor(Color.BLACK);
      this.scoreLine2.setFilled(true);
      this.add(this.scoreLine2);
   }

   public void run()
   {
      int time = this
               .askInt(
                        "Please Specify The Necessary Score For The Game To End",
                        10, 500);
      int bG = this
               .askInt(
                        "Please Select The Background Image That You Would Like To Use",
                        1, 3);
      int r = this.askInt("Please Select The Rackets Color", 1, 3);
      int b = this.askInt("Please Select The Ball Color", 1, 3);

      this.backGround(bG);
      this.racketColor(r);
      this.gamingArea();
      this.ballSelection(b);

      this.countLabelL = new GLabel(countL + "",
               (this.getWidth() / 2) - 63, 50);
      this.countLabelL.setFont("SansSerif-25"); // fonte maior
      this.countLabelL.setColor(Color.WHITE);
      this.countLabelR = new GLabel(countR + "",
               (this.getWidth() / 2) + 25, 50);
      this.countLabelR.setColor(Color.WHITE);
      this.countLabelR.setFont("SansSerif-25"); // fonte maior
      this.add(this.countLabelL);
      this.add(this.countLabelR);

      this.addKeyListeners();

      RandomGenerator rgen = RandomGenerator.getInstance();

      this.dx = this.dy = 1;

      if (rgen.nextBoolean())
      {
         this.dx = -this.dx;
      }

      if (rgen.nextBoolean())
      {
         this.dy = -this.dy;
      }

      this.waitForClick();

  while (true)
      {
         this.testGoal();
         this.moveBall(time);
         this.moveRacket();
         this.pause(3);
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

   /**
    * Contains the information about how the ball should move
    */
   private void moveBall(int time)

   {
      this.cP1 = this
               .getElementAt(this.ball.getX(), this.ball.getY());
      this.cP2 = this.getElementAt(this.ball.getX()
               + this.ball.getWidth(), this.ball.getY());
      this.cP3 = this.getElementAt(this.ball.getX(), this.ball.getY()
               + this.ball.getHeight());
      this.cP4 = this.getElementAt(this.ball.getX()
               + this.ball.getWidth(), this.ball.getY()
               + this.ball.getHeight());

      if (cP1 == fieldLine1)
      {
         dy = -dy;
      }
      if (cP1 == racketL || cP3 == racketL)
      {
         dx = -dx;
      }
      if (cP2 == racketR || cP4 == racketR)
      {
         dx = -dx;
      }
      if (cP2 == fieldLine2)
      {
         dy = -dy;
      }
      if (cP4 == fieldLine4)
      {
         dy = -dy;
      }
      if (cP3 == fieldLine3)
      {
         dy = -dy;
      }
      this.ball.move(dx, dy);
   }

   /**
    * Moves rackets according to key pressed and not yet released
    */
   private void moveRacket()
   {
      if (leftUp
               && this.racketL.getY() >= this.fieldLine1.getHeight())
      {
         this.racketL.move(0, -2);
      }
      if (leftDown
               && (this.racketL.getY() + this.racketL.getHeight() <= this.fieldLine3
                        .getY()))
      {
         this.racketL.move(0, 2);
      }
      if (rightUp
               && this.racketR.getY() >= this.fieldLine2.getHeight())
      {
         this.racketR.move(0, -2);
      }
      if (rightDown
               && (this.racketR.getY() + this.racketR.getHeight() <= this.fieldLine4
                        .getY()))
      {
         this.racketR.move(0, 2);
      }
   }

   /**
    * Tests if a goal has been scored for both players
    */
   private void testGoal()
   {
      if (cP1 == scoreLine1)
      {
         this.incrementCounterR();
         this.ball.setLocation(this.fieldLine.getX() - 3.5,
                  this.fieldLine.getHeight() / 2);
         this.add(this.ball);
      }
      if (cP2 == scoreLine2)
      {
         this.incrementCounterL();
         this.ball.setLocation(this.fieldLine.getX() - 3.5,
                  this.fieldLine.getHeight() / 2);
         this.add(this.ball);
      }
   }

   /**
    * Updates the left player score counter
    */
   private void incrementCounterL()
   {
      this.countL = this.countL + 1;
      this.countLabelL.setLabel(this.countL + "");
   }

   /**
    * Updates the right player score counter
    */
   private void incrementCounterR()
   {
      this.countR = this.countR + 1;
      this.countLabelR.setLabel(this.countR + "");
   }
}