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
 * Esta é a vers�o de 2008/09/28.
 * <hr>
 * @author Miguel Nuno Rosa
 * @number 6219
 * @version 30-10-2009
 */
// Deve escrever o c�digo utilizando a l�ngua inglesa
public class House extends GraphicsProgram
{
   final int UNIT               = 10;
   final int HOUSE_FLOOR        = UNIT * 40;      //400
   final int HOUSE_WALL         = HOUSE_FLOOR / 2; //200
   final int TRIANGLE_HEIGHT    = HOUSE_WALL / 2; //100
   final int WINDOW_SQUARE_SIZE = UNIT * 2;       //20

   public void run()
   {
      GTurtle turtle = new GTurtle();
      this.add(turtle, 110, 450);

      turtle.penDown();
      this.drawHouse(turtle);
   }

   /**
    * "Main" method. this method regroup all the sub-methods (methods to draw the walls, roof, windows and door).
    */
   public void drawHouse(GTurtle turtle)
   {
      this.drawBasicHouse(turtle);
      this.drawRoof(turtle);
      this.drawWindows(turtle);
      this.drawDoor(turtle);
   }

   /**
    * Method to draw the house walls
    */
   public void drawBasicHouse(GTurtle turtle)
   {
      for (int i = 0; i < 2; i++)
      {
         turtle.forward(HOUSE_FLOOR);
         turtle.left(90);
         turtle.forward(HOUSE_WALL);
         turtle.left(90);
      }
      turtle.left(90);
      turtle.forward(HOUSE_WALL);
      turtle.right(90);
   }

   /**
    * Method to draw the Roof
    */
   public void drawRoof(GTurtle turtle)
   {
      turtle.left(20);
      turtle.forward(HOUSE_WALL + (UNIT + 3));
      turtle.right(40);
      turtle.forward(HOUSE_WALL + (UNIT + 3));
   }

   /**
    * Method to group all methods to draw the windows
    */
   public void drawWindows(GTurtle turtle)
   {
      this.moveToLeftWindow(turtle);
      this.drawWindow(turtle);
      this.moveToRightWindow(turtle);
      this.drawWindow(turtle);
   }

   /**
    * This method draw the little square for the window grid
    */
   public void drawLittleSquare(GTurtle turtle)
   {
      for (int i = 0; i < 4; i++)
      {
         turtle.forward(WINDOW_SQUARE_SIZE);
         turtle.left(90);
      }
   }

   /**
    * Method to draw the window (grid)
    */
   public void drawWindow(GTurtle turtle)
   {
      for (int i = 0; i < 3; i++)
      {
         for (int j = 0; j < 4; j++)
         {
            turtle.penDown();
            this.drawLittleSquare(turtle);
            turtle.forward(WINDOW_SQUARE_SIZE);
            turtle.penUp();
         }
         this.moveNewLine(turtle);
      }
   }

   /**
    * Method to jump to another line. It's like I press the "enter" key
    */
   public void moveNewLine(GTurtle turtle)
   {
      turtle.penUp();
      turtle.left(180);
      turtle.forward(WINDOW_SQUARE_SIZE * 4);
      turtle.left(90);
      turtle.forward(WINDOW_SQUARE_SIZE);
      turtle.left(90);
   }

   /**
    * Method to move the turtle to the left window
    */
   public void moveToLeftWindow(GTurtle turtle)
   {
      turtle.penUp();
      turtle.right(160);
      turtle.forward(HOUSE_FLOOR);
      turtle.left(90);
      turtle.forward(UNIT * 7); //down the wall
      turtle.left(90);
      turtle.forward(UNIT * 4); //move to position to draw the window
   }

   /**
    * Move the turtle to the Right Window
    */
   public void moveToRightWindow(GTurtle turtle)
   {
      turtle.forward(HOUSE_WALL + (UNIT * 3));
      turtle.left(90);
      turtle.forward((UNIT * 4) + WINDOW_SQUARE_SIZE);
      turtle.right(90);
      turtle.penDown();
   }

   /**
    * Method to group all sub-methods (draw the door and knob)
    */
   public void drawDoor(GTurtle turtle)
   {
      this.moveToDrawDoor(turtle);
      this.drawTheDoor(turtle);
      this.moveToKnob(turtle);
      this.drawKnob(turtle);
   }

   /**
    * method to move the turtle to position to draw the door
    */
   public void moveToDrawDoor(GTurtle turtle)
   {
      turtle.penUp();
      turtle.right(90);
      turtle.forward(UNIT * 7);
      turtle.right(90);
      turtle.forward(UNIT * 10);
      turtle.right(90);
      turtle.penDown();
   }

   /**
    * Method to draw the door
    */
   public void drawTheDoor(GTurtle turtle)
   {
      turtle.forward(WINDOW_SQUARE_SIZE * 5);
      turtle.right(90);
      turtle.forward(WINDOW_SQUARE_SIZE * 3);
      turtle.right(90);
      turtle.forward(WINDOW_SQUARE_SIZE * 5);
   }

   /**
    * Move the turtle to position to draw the knob
    */
   public void moveToKnob(GTurtle turtle)
   {
      turtle.penUp();
      turtle.right(90);
      turtle.forward(WINDOW_SQUARE_SIZE * 2);// walk to left
      turtle.right(90);
      turtle.forward((WINDOW_SQUARE_SIZE * 2)
               + (WINDOW_SQUARE_SIZE / 2)); //move to local to draw the knob
      turtle.penDown();
   }

   /**
    * Method to draw the Knob
    */
   public void drawKnob(GTurtle turtle)
   {
      for (int i = 0; i < 36; i++)
      {
         turtle.forward(1);
         turtle.left(10);
      }
      turtle.setColor(Color.BLACK);
      turtle.penUp();
      turtle.left(180);
      turtle.forward((WINDOW_SQUARE_SIZE * 2)
               + (WINDOW_SQUARE_SIZE / 2));
      turtle.right(90);
   }

}