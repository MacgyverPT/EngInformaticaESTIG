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
 * <a href="http://jtf.acm.org/javadoc/student/index.html">esta documenta?ão</a>.
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
 * @author Miguel Rosa (Nº 6219)
 * @version 03-11-2010 
 */
// Deve escrever o c?digo utilizando a l?ngua inglesa

public class Checkers extends GraphicsProgram 
{
    public void run()
    {
        //Square Position: XX * YY
        final int POS_X = 50;
        final int POS_Y = 50;
        //matrix size
        final int N_LINES = 8;
        final int N_COLUMNS = 8;
        //Square size. Width and Height is half value of total square size
        int squareSize = 100;
        int squareWidth = squareSize / 2;
        int squareHeight = squareSize / 2;
        //Circle size
        int pieceSize = squareSize - 35; //size = 65
        int pieceWidth = pieceSize / 2;
        int pieceHeight = pieceSize / 2;

        //starting the program
        IODialog dialog = new IODialog();
        int option = dialog.readInt("Select your game with...\n\n1) ... Original colors\n2) ... Inverted the original colors\n3) ... Images");

        //ask to user an option to star the drawing
        switch (option)
        {
            case 1: //Original Colors: Black/White squares and pieces
                this.drawBoard(N_COLUMNS, N_LINES, POS_X, POS_Y, squareWidth, squareHeight, pieceWidth, pieceHeight, squareSize, option);
                break;
            case 2: //Inverted the original colors
                this.drawBoard(N_COLUMNS, N_LINES, POS_X, POS_Y, squareWidth, squareHeight, pieceWidth, pieceHeight, squareSize, option);
                break;
            case 3:
                this.drawBasicBoard(N_COLUMNS, N_LINES, POS_X, POS_Y, squareWidth, squareHeight);
                this.drawLettersAndNumbers(N_COLUMNS, N_LINES, POS_X, POS_Y, squareWidth, squareHeight);
                this.drawPieceImages(N_COLUMNS, N_LINES, POS_X, POS_Y, squareWidth, squareHeight, pieceWidth, pieceHeight, squareSize);
                break;
        }
    }

    /**
     * This method draws all the components (board, pieces and labels (letters and numbers)). Basically, this method calls all the "main sub-methods":
     * (drawBasicBoard, drawLettersAndNumbers, drawPieces and drawInvertedPieces).
     * @param N_COLUMNS numbers of columns of the board (this value never change - constant)
     * @param N_LINES number of lines on the board position of X (this value never change - constant)
     * @param POS_X position of X (this value never change - constant)
     * @param POS_Y position of Y (this value never change - constant)
     * @param squareWidth width size of the square
     * @param squareHeight height size of the square
     * @param pieceWidth width size of the piece
     * @param pieceHeight height size of the piece
     * @param squareSize total size of the square, square of the board
     * @param option option selected by the user
     */
    public void drawBoard(final int N_COLUMNS, final int N_LINES, final int POS_X, final int POS_Y, int squareWidth, int squareHeight, int pieceWidth, int pieceHeight, int squareSize, int option)
    {
        this.drawBasicBoard(N_COLUMNS, N_LINES, POS_X, POS_Y, squareWidth, squareHeight);
        this.drawLettersAndNumbers(N_COLUMNS, N_LINES, POS_X, POS_Y, squareWidth, squareHeight);
        if(option == 1)
        {
            this.drawPieces(N_COLUMNS, N_LINES, POS_X, POS_Y, squareWidth, squareHeight, pieceWidth, pieceHeight, squareSize);
        }else{
            this.drawInvertedPieces(N_COLUMNS, N_LINES, POS_X, POS_Y, squareWidth, squareHeight, pieceWidth, pieceHeight, squareSize, option);
        }
    }

    /**
     * This method draws all the labels (calls the sub-methods drawLetters and drawNumbers)
     * @param N_COLUMNS numbers of columns of the board (this value never change - constant)
     * @param N_LINES number of lines on the board position of X (this value never change - constant)
     * @param POS_X position of X (this value never change - constant)
     * @param POS_Y position of Y (this value never change - constant)
     * @param squareWidth width size of the square
     * @param squareHeight height size of the square
     */
    public void drawLettersAndNumbers(final int N_COLUMNS, final int N_LINES, final int POS_X, final int POS_Y, int squareWidth, int squareHeight)
    {
        this.drawLetters(POS_X, POS_Y, squareWidth, squareHeight);
        this.drawNumbers(N_LINES, POS_X, POS_Y, squareWidth, squareHeight);
    }

    /**
     * This method draws all the pieces (calls the sub-methods drawBlackPieces and drawWhitePieces)
     * @param N_COLUMNS numbers of columns of the board (this value never change - constant)
     * @param N_LINES number of lines on the board position of X (this value never change - constant)
     * @param POS_X position of X (this value never change - constant)
     * @param POS_Y position of Y (this value never change - constant)
     * @param squareWidth width size of the square
     * @param squareHeight height size of the square
     * @param pieceWidth width size of the piece
     * @param pieceHeight height size of the piece
     * @param squareSize total size of the square, square of the board
     */
    public void drawPieces(final int N_COLUMNS, final int N_LINES, final int POS_X, final int POS_Y, int squareWidth, int squareHeight, int pieceWidth, int pieceHeight, int squareSize)
    {
        this.drawBlackPiece(POS_X, POS_Y, squareWidth, squareHeight, pieceWidth, pieceHeight, squareSize);
        this.drawWhitePiece(POS_X, POS_Y, squareWidth, squareHeight, pieceWidth, pieceHeight, squareSize);
    }

    /**
     * This method draws all the inverted color of the pieces (call the sub-methods drawInvertedBlackPieces and drawInvertedWhitePieces)
     * @param N_COLUMNS numbers of columns of the board (this value never change - constant)
     * @param N_LINES number of lines on the board position of X (this value never change - constant)
     * @param POS_X position of X (this value never change - constant)
     * @param POS_Y position of Y (this value never change - constant)
     * @param squareWidth width size of the square
     * @param squareHeight height size of the square
     * @param pieceWidth width size of the piece
     * @param pieceHeight height size of the piece
     * @param squareSize total size of the square, square of the board
     * @param option option selected by the user
     */
    public void drawInvertedPieces(final int N_COLUMNS, final int N_LINES, final int POS_X, final int POS_Y, int squareWidth, int squareHeight, int pieceWidth, int pieceHeight, int squareSize, int option)
    {
        this.drawInvertedBlackPiece(POS_X, POS_Y, squareWidth, squareHeight, pieceWidth, pieceHeight, squareSize);
        this.drawInvertedWhitePiece(POS_X, POS_Y, squareWidth, squareHeight, pieceWidth, pieceHeight, squareSize);
    }

    /**
     * This method draws imagens in place of the pieces. To drawing the images, this method calls the 2 sub-methods: drawTopPiece (to draw the top pieces)
     * and drawBottomPieces (to draw the bottom pieces).
     * @param N_COLUMNS numbers of columns of the board (this value never change - constant)
     * @param N_LINES number of lines on the board position of X (this value never change - constant)
     * @param POS_X position of X (this value never change - constant)
     * @param POS_Y position of Y (this value never change - constant)
     * @param squareWidth width size of the square
     * @param squareHeight height size of the square
     * @param pieceWidth width size of the piece
     * @param pieceHeight height size of the piece
     * @param squareSize total size of the square, square of the board
     */
    public void drawPieceImages(final int N_COLUMNS, final int N_LINES, final int POS_X, final int POS_Y, int squareWidth, int squareHeight, int pieceWidth, int pieceHeight, int squareSize)
    {
        this.drawTopPiece(POS_X, POS_Y, squareWidth, squareHeight, pieceWidth, pieceHeight, squareSize);
        this.drawBottomPiece(POS_X, POS_Y, squareWidth, squareHeight, pieceWidth, pieceHeight, squareSize);
    }

    /**
     * This method draws the basic board (a grid) and paint it with chess pattern
     * @param N_COLUMNS numbers of columns of the board (this value never change - constant)
     * @param N_LINES number of lines on the board position of X (this value never change - constant)
     * @param POS_X position of X (this value never change - constant)
     * @param POS_Y position of Y (this value never change - constant)
     * @param squareWidth width size of the square
     * @param squareHeight height size of the square
     * @param pieceWidth width size of the piece
     * @param pieceHeight height size of the piece
     * @param squareSize total size of the square, square of the board
     */
    public void drawBasicBoard(final int N_COLUMNS, final int N_LINES, final int POS_X, final int POS_Y, int squareWidth, int squareHeight)
    {
        for(int i = 0; i < N_COLUMNS; i++)
        {
            for(int j = 0; j < N_LINES; j++)
            {
                GRect square = new GRect(POS_X + (squareWidth * i), POS_Y + (squareHeight * j), squareWidth, squareHeight);

                this.add(square);
                //if ( (i % 2 != 0 && j % 2 == 0) || (i % 2 == 0 && j % 2 != 0) )
                if ( (i + j) % 2 != 0 )
                {
                    square.setFilled(true);
                    square.setFillColor(Color.LIGHT_GRAY);
                }else{
                    square.setFilled(true);
                    square.setFillColor(Color.WHITE);
                }
            }
        }
    }

    /**
     * This method draws the basic black piece (requirement 2 - essential).
     * @param POS_X position of X (this value never change - constant)
     * @param POS_Y position of Y (this value never change - constant)
     * @param squareWidth width size of the square
     * @param squareHeight height size of the square
     * @param pieceWidth width size of the piece
     * @param pieceHeight height size of the piece
     * @param squareSize total size of the square, square of the board
     */
    public void drawBlackPiece(final int POS_X, final int POS_Y, int squareWidth, int squareHeight, int pieceWidth, int pieceHeight, int squareSize)
    {
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if ( (i + j) % 2 != 0)
                {
                    //draw the piece's base
                    GOval basePiece = new GOval( ( (POS_X / 2) + ( (POS_X / 2) + 6) ) + (squareWidth * i), 
                            ( (POS_Y / 2) + ( (POS_Y / 2) + 6) ) + (squareHeight * j),
                            pieceWidth + (pieceWidth / 6),
                            pieceHeight);                            

                    basePiece.setFilled(true);
                    basePiece.setFillColor(Color.BLACK);
                    basePiece.setColor(Color.BLACK);
                    this.add(basePiece);

                    //draw the piece's top
                    GOval topPiece = new GOval( ( (POS_X / 2) + ( (POS_X / 2) + 18) ) + (squareWidth * i),
                            ( (POS_Y / 2) + ( (POS_Y / 2) + 16) ) + (squareHeight * j),
                            squareSize / 8, 
                            squareSize / 8);

                    topPiece.setFilled(true);
                    topPiece.setFillColor(Color.WHITE);
                    topPiece.setColor(Color.BLACK);
                    topPiece.sendToFront();
                    this.add(topPiece);
                }
            }
        }
    }

    /**
     * This method draws the basic black piece with the inverted color (on this case, with white color (requirement 8 - not essential)).
     * @param POS_X position of X (this value never change - constant)
     * @param POS_Y position of Y (this value never change - constant)
     * @param squareWidth width size of the square
     * @param squareHeight height size of the square
     * @param pieceWidth width size of the piece
     * @param pieceHeight height size of the piece
     * @param squareSize total size of the square, square of the board
     */
    public void drawInvertedBlackPiece(final int POS_X, final int POS_Y, int squareWidth, int squareHeight, int pieceWidth, int pieceHeight, int squareSize)
    {
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if ( (i + j) % 2 != 0)
                {
                    //draw the piece's base
                    GOval basePiece = new GOval( ( (POS_X / 2) + ( (POS_X / 2) + 6) ) + (squareWidth * i), 
                            ( (POS_Y / 2) + ( (POS_Y / 2) + 6) ) + (squareHeight * j),
                            pieceWidth + (pieceWidth / 6),
                            pieceHeight);                            

                    basePiece.setFilled(true);
                    basePiece.setFillColor(Color.WHITE);
                    basePiece.setColor(Color.BLACK);
                    this.add(basePiece);

                    //draw the piece's top
                    GOval topPiece = new GOval( ( (POS_X / 2) + ( (POS_X / 2) + 18) ) + (squareWidth * i),
                            ( (POS_Y / 2) + ( (POS_Y / 2) + 16) ) + (squareHeight * j),
                            squareSize / 8, 
                            squareSize / 8);

                    topPiece.setFilled(true);
                    topPiece.setFillColor(Color.BLACK);
                    topPiece.setColor(Color.BLACK);
                    topPiece.sendToFront();
                    this.add(topPiece);
                }
            }
        }
    }

    /**
     * This method draws the basic white piece (requirement 2 - essential).
     * @param POS_X position of X (this value never change - constant)
     * @param POS_Y position of Y (this value never change - constant)
     * @param squareWidth width size of the square
     * @param squareHeight height size of the square
     * @param pieceWidth width size of the piece
     * @param pieceHeight height size of the piece
     * @param squareSize total size of the square, square of the board
     */
    public void drawWhitePiece(final int POS_X, final int POS_Y, int squareWidth, int squareHeight, int pieceWidth, int pieceHeight, int squareSize)
    {
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if ( (i + j) % 2 == 0)
                {
                    GOval basePiece2 = new GOval( (POS_X / 2) + ( (POS_X / 2) + 6) + (squareWidth * i),
                            (POS_Y * 5) + (squareHeight + 8) + (squareHeight * j),
                            pieceWidth + (pieceWidth / 6),
                            pieceHeight);

                    basePiece2.setFilled(true);
                    basePiece2.setFillColor(Color.WHITE);
                    basePiece2.setColor(Color.BLACK);                     
                    this.add(basePiece2);

                    GOval topPiece2 = new GOval( (POS_X + 7) + (squareHeight / 4) + (squareWidth * i),
                            ( (POS_Y * 4) + (POS_Y + squareHeight) + 6) + (squareHeight / 4) + (squareHeight * j),
                            squareSize / 8,
                            squareSize / 8);

                    topPiece2.setFilled(true);
                    topPiece2.setFillColor(Color.BLACK);
                    topPiece2.setColor(Color.BLACK);
                    this.add(topPiece2);

                }
            }
        }
    }

    /**
     * This method draws the basic white piece with the inverted color (on this case, with black color (requirement 8 - not essential)).
     * @param POS_X position of X (this value never change - constant)
     * @param POS_Y position of Y (this value never change - constant)
     * @param squareWidth width size of the square
     * @param squareHeight height size of the square
     * @param pieceWidth width size of the piece
     * @param pieceHeight height size of the piece
     * @param squareSize total size of the square, square of the board
     */
    public void drawInvertedWhitePiece(final int POS_X, final int POS_Y, int squareWidth, int squareHeight, int pieceWidth, int pieceHeight, int squareSize)
    {
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if ( (i + j) % 2 == 0)
                {
                    GOval basePiece2 = new GOval( (POS_X / 2) + ( (POS_X / 2) + 6) + (squareWidth * i),
                            (POS_Y * 5) + (squareHeight + 8) + (squareHeight * j),
                            pieceWidth + (pieceWidth / 6),
                            pieceHeight);

                    basePiece2.setFilled(true);
                    basePiece2.setFillColor(Color.BLACK);
                    basePiece2.setColor(Color.BLACK);                     
                    this.add(basePiece2);

                    GOval topPiece2 = new GOval( (POS_X + 7) + (squareHeight / 4) + (squareWidth * i),
                            ( (POS_Y * 4) + (POS_Y + squareHeight) + 6) + (squareHeight / 4) + (squareHeight * j),
                            squareSize / 8,
                            squareSize / 8);

                    topPiece2.setFilled(true);
                    topPiece2.setFillColor(Color.WHITE);
                    topPiece2.setColor(Color.BLACK);
                    this.add(topPiece2);

                }
            }
        }
    }

    /**
     * This method draws the piece with an image, ie image instead of two GOval. This sub-method draw the top Pieces
     * @param POS_X position of X (this value never change - constant)
     * @param POS_Y position of Y (this value never change - constant)
     * @param squareWidth width size of the square
     * @param squareHeight height size of the square
     * @param pieceWidth width size of the piece
     * @param pieceHeight height size of the piece
     * @param squareSize total size of the square, square of the board
     */
    public void drawTopPiece(final int POS_X, final int POS_Y, int squareWidth, int squareHeight, int pieceWidth, int pieceHeight, int squareSize)
    {
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if ( (i + j) % 2 != 0)
                {
                    //draw the image in the pieces
                    GImage basePiece = new GImage("../images/img1.jpg", 100, 100);
                    basePiece.setBounds( ( (POS_X / 2) + ( (POS_X / 2) + 10) ) + (squareWidth * i), 
                        ( (POS_Y / 2) + ( (POS_Y / 2) + 10) ) + (squareHeight * j),
                        pieceWidth + (pieceWidth / 6),
                        pieceHeight);                            

                    basePiece.setSize(pieceWidth, pieceHeight); 
                    this.add(basePiece);
                }
            }
        }
    }

    /**
     * This method draws the piece with image, ie image instead of two GOval. This sub-method draw de bottom pieces
     * @param POS_X position of X (this value never change - constant)
     * @param POS_Y position of Y (this value never change - constant)
     * @param squareWidth width size of the square
     * @param squareHeight height size of the square
     * @param pieceWidth width size of the piece
     * @param pieceHeight height size of the piece
     * @param squareSize total size of the square, square of the board
     */
    public void drawBottomPiece(final int POS_X, final int POS_Y, int squareWidth, int squareHeight, int pieceWidth, int pieceHeight, int squareSize)
    {
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if ( (i + j) % 2 == 0)
                {
                    //draw the image in the pieces
                    GImage basePiece = new GImage("../images/img2.jpg", 100, 100);
                    basePiece.setBounds( (POS_X / 2) + ( (POS_X / 2) + 10) + (squareWidth * i),
                        (POS_Y * 5) + (squareHeight + 10) + (squareHeight * j),
                        pieceWidth + (pieceWidth / 6),
                        pieceHeight);                          

                    basePiece.setSize(pieceWidth, pieceHeight); 
                    this.add(basePiece);
                }
            }
        }
    }

    /**
     * this methos draw the numbers on left side of the board
     * @param N_LINES number of the lines in board (this value never change - constant)
     * @param POS_X position of X (this value never change - constant)
     * @param POS_Y position of Y (this value never change - constant)
     * @param squareWidth width size of the square
     * @param squareHeight height size of the square
     */
    public void drawNumbers(final int N_LINES, final int POS_X, final int POS_Y, int squareWidth, int squareHeight)
    {
        int count = 8;
        for(int i = 0; i < N_LINES; i++)
        {
            GLabel numbers = new GLabel("" + count, POS_X - (squareWidth / 2), (POS_Y + (squareHeight / 2)) + (squareWidth * i) );
            numbers.setFont(new Font("Serif", Font.BOLD, 20));
            this.add(numbers);
            count--;
        }
    }

    /**
     * This method draw the letters (A to H) under the Board
     * @param POS_X position of X (this value never change - constant)
     * @param POS_Y position of Y (this value never change - constant)
     * @param squareWidth width size of the square
     * @param squareHeight height size of the square
     */
    public void drawLetters(final int POS_X, final int POS_Y, int squareWidth, int squareHeight)
    {
        for(char c = 'A'; c <= 'H'; c++)
        {
            int i = c - 'A';
            GLabel letters = new GLabel(c + "", (POS_X + (squareWidth / 2) + ((squareWidth * 2) / 2) * i) , (POS_Y + ((squareWidth * 2) * 4)) + 25);
            letters.setFont(new Font("Serif", Font.BOLD, 20));
            this.add(letters);
        }
    }

}