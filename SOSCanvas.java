/**
 * a class draws the game canvas and create its panels with components
 * author: Miray Ayerdem
 * version : 25/11/2020
 */
import javax.swing.*;
import cs101.sosgame.*;
import java.awt.*;

public class SOSCanvas extends JPanel {
    //Instances
    private int dimension;
    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    private final int SPACE = 50;
    private final double STRX = 2.5;
    private final double STRY = 2.5;
    private SOS obj;
    private int x , y;

    //Constructor
    SOSCanvas(SOS obj)  //takes the SOS Game pbject as a parameter
    {
        this.obj = obj;
        this.dimension = obj.getDimension();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(249, 124, 73));
        repaint();

    }
    //Accessor methods
    public int getStartOfX()
    {
        return Math.abs((SPACE * dimension)/2 - 250);
    }
    public int getStartOfY()
    {
        return Math.abs((SPACE * dimension)/2 - 250);
    }

    public int getEndOfX()
    {
        return getStartOfX() + (SPACE * dimension);
    }
    public int getEndOfY()
    {
        return getStartOfY() + (SPACE * dimension);
    }

    public int getSpace()
    {
        return SPACE;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        x = Math.abs((SPACE * dimension)/2 - 250); //initial x point to be centered the board
        y = Math.abs((SPACE * dimension)/2 - 250);  //initial y point to be centered the board

        //draw the horizontal lines
        for (int i = 0; i <= dimension; i++) {
            g.drawLine( x , y , x + SPACE * dimension, y);
            y = y + SPACE;
        }
        x = Math.abs((SPACE * dimension)/2 - 250);
        y = Math.abs((SPACE * dimension)/2 - 250);

        //draws the vertical lines
        for (int i = 0; i <= dimension; i++) {
            g.drawLine( x, y , x, y + SPACE * dimension);
            x = x + SPACE;
        }


        x = Math.abs((SPACE * dimension)/2 - 250);
        y = Math.abs((SPACE * dimension)/2 - 250);

        x =  x + (int)(SPACE/STRX); //the initial x point to draw the string
        y = y + (int)(SPACE/ STRY); //the initial y point to draw the string
        g.setFont(new Font("Verdana", Font.PLAIN, SPACE / 2 )); //setting string font and size

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if(obj.getCellContents(i, j) == 's')  //"S" letter is drew
                {
                    g.setColor(new Color(73, 102, 249 ));
                    g.drawString("S", x - 4, y + 12);
                }
                else if(obj.getCellContents(i, j) == 'o') //"O" letter is drew
                {
                    g.setColor(Color.WHITE);
                    g.drawString("O", x - 4, y + 12);

                }
                x = x + SPACE;
                if(j == dimension - 1) //if it is the last column of the row it pass the another row
                {
                    y = y + SPACE;
                    x = Math.abs((SPACE * dimension)/2 - 250) + ((int)(SPACE/STRX));
                }

            }
        }
        obj.printBoard(); //to test
    }
}
