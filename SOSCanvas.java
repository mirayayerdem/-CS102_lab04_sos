import javax.swing.*;
import cs101.sosgame.*;
import java.awt.*;

public class SOSCanvas extends JPanel {
    private int dimension;
    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    private final int STRX = 5;
    private final int STRY = 10;
    private SOS obj;
    private final int SPACE = 50;
    private int x;
    private int y;

    SOSCanvas(SOS obj) {
        this.obj = obj;
        this.dimension = obj.getDimension();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(new BorderLayout());
        repaint();


    }
    public int getEndOfX()
    {
        return SPACE + (SPACE * dimension);
    }
    public int getEndOfY()
    {
        return SPACE + (SPACE * dimension);
    }

    public int getSpace()
    {
        return SPACE;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(204,204,204));
        x = SPACE;
        y = SPACE;

        for (int i = 0; i <= dimension; i++) {
            g.drawLine(x, y, (x + (SPACE * dimension)), y);
            y = y + SPACE;
        }

        x = SPACE;
        y = SPACE;
        for (int i = 0; i <= dimension; i++) {
            g.drawLine(x, y, x, (y + (SPACE * dimension)));
            x = x + SPACE;
        }
        x = SPACE + ((SPACE - STRX) / 2);
        y = SPACE + ((SPACE - STRY) / 2);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if(obj.getCellContents(i, j) == 's')
                {
                    g.setColor(Color.BLACK);
                    g.drawString("S", x, y);
                }
                else if(obj.getCellContents(i, j) == 'o')
                {
                    g.setColor(Color.RED);
                    g.drawString("O", x, y);
                }
                x = x + SPACE;
                if(j == dimension - 1)
                {
                    y = y + SPACE;
                    x = SPACE + ((SPACE - STRX) / 2);
                }

            }
        }
    }
}
