/**
 * a class creates the panel and its components
 * author: Miray Ayerdem
 * version : 25/11/2020
 */
import cs101.sosgame.SOS;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SOSGUIPanel extends JPanel {
    //Instances
    private SOSCanvas canvas;
    private SOS game;
    private int space;
    private String p1Name, p2Name;
    private final int WIDTH = 500;
    private final int HEIGHT = 600;
    private final int COMPONENTS_HEIGHT = 100;
    private boolean oSelected, sSelected;
    private JPanel components;
    private JLabel p1, p2 , label;
    private JRadioButton oButton, sButton;
    private ActionListener buttonListener;
    private ButtonGroup buttonGroup;

    //Constructor
    SOSGUIPanel(SOS obj, String p1, String p2) //game obj, p1 name, p2 name as parameters
    {
       this.game = obj;
       this.p1Name = p1;
       this.p2Name = p2;
       canvas = new SOSCanvas(game);
       space = canvas.getSpace();
       label = new JLabel("SOS Game : " + p1 + " VS. " + p2);
       setPreferredSize(new Dimension(WIDTH, HEIGHT));
       buttonListener = new MyRadioButtonListener();
       buttonGroup = new ButtonGroup();
       createComponentsPanel();
       canvas.addMouseListener(new MyMouseListener());
       this.setLayout(new BorderLayout());
       this.add(label, BorderLayout.NORTH);
       this.add(canvas, BorderLayout.CENTER);
       this.add(components, BorderLayout.SOUTH);
    }

    /**
     * a method the create components to this panel
     */
    public void createComponentsPanel()
    {
        components = new JPanel();
        JPanel labelPanel = new JPanel();
        components.setPreferredSize(new Dimension(WIDTH, COMPONENTS_HEIGHT));
        components.setLayout(new BorderLayout());
        p1 = new JLabel(p1Name + ":" + game.getPlayerScore1());
        p2 = new JLabel(p2Name + ":" + game.getPlayerScore2());
        p1.setOpaque(true);
        p2.setOpaque(true);
        p1.setBackground (Color.GREEN);
        sButton = new JRadioButton("S");
        sButton.addActionListener(buttonListener); //adding listener
        buttonGroup.add(sButton); //adding button group to be able to choose only one of the buttons
        oButton = new JRadioButton("O");
        oButton.addActionListener(buttonListener); //adding listener
        buttonGroup.add(oButton);  //adding button group to be able to choose only one of the buttons
        sButton.setSelected(true);
        labelPanel.add(p1);
        labelPanel.add(sButton);
        labelPanel.add(oButton);
        labelPanel.add(p2);
        components.add(labelPanel, BorderLayout.CENTER);
        components.setBackground(Color.lightGray);


    }
    //inner class to listen the button actions
    class MyRadioButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == sButton) //if sButton is clicked, set the button as true
            {
                sButton.setSelected(true);
                oButton.setSelected(false);
            }
            else //if oButton is clicked, set the button as true
            {
                oButton.setSelected(true);
                sButton.setSelected(false);
            }

        }
    }
    class MyMouseListener extends MouseAdapter
    {
        SOSGUIPanel panel;

        @Override
        public void mousePressed(MouseEvent e)
        {
            String champion;
            if(e.getX() >= space && e.getX() <= canvas.getEndOfX() && e.getY() >= space && e.getY() <= canvas.getEndOfY()) //checks if the canvas contains the point
            {

                if(sButton.isSelected()) //play the s letter in the pressed row and column
                {
                    game.play('s', (int)(((e.getY() - canvas.getStartOfY()) / space)) + 1, (int)(((e.getX() - canvas.getStartOfX()) / space)) + 1);
                }
                else // play the o letter in the pressed row and column
                {
                    game.play('o',  (int)(((e.getY() - canvas.getStartOfY()) / space)) + 1, (int)(((e.getX() - canvas.getStartOfX()) / space)) + 1);
                }
                if(game.getTurn() == 1) // if the turn of first player his/her name is showed colorful
                {
                    p1.setBackground(Color.GREEN);
                    p2.setBackground(Color.WHITE);
                }
                else
                {
                    p2.setBackground(Color.GREEN);
                    p1.setBackground(Color.WHITE);
                }
                p1.setText(p1Name + ":" + game.getPlayerScore1()); //shows the score
                p2.setText(p2Name + ":" + game.getPlayerScore2()); //shows the score
                canvas.repaint(); //call the paintComponent method

                if(game.getPlayerScore1() > game.getPlayerScore2())
                {
                    champion = p1Name; //states the leader
                }
                else if(game.getPlayerScore2() > game.getPlayerScore1())
                {
                    champion = p2Name; //states the leader
                }
                else
                {
                    champion = "draw"; //there is no winner
                }
                if(game.isGameOver()  && !champion.equals("draw") ) //if the game is over, winner is declared with messageDialog
                {
                    JOptionPane.showMessageDialog(new JFrame() , "Winner is " + champion);
                }
                else if(game.isGameOver() && champion.equals("draw") )
                {
                    JOptionPane.showMessageDialog(new JFrame(), "It is a " + champion);
                }
            }
        }
    }

}
