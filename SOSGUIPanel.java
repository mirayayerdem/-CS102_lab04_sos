import cs101.sosgame.SOS;

import javax.swing.*;
import javax.xml.ws.Action;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SOSGUIPanel extends JPanel {
    private SOSCanvas canvas;
    private SOS game;
    private int space;
    private String p1Name;
    private String p2Name;
    private final int WIDTH = 500;
    private final int HEIGHT = 600;
    private final int COMPONENTS_HEIGHT = 100;
    private final ImageIcon logo = new ImageIcon(getClass().getResource("wall13.png"));
    private boolean oSelected, sSelected;
    private JPanel components;
    private JLabel p1, p2;
    private JRadioButton oButton, sButton;
    private ActionListener buttonListener;
    private MyMouseListener mListener;
    private ButtonGroup buttonGroup;

    SOSGUIPanel(SOS obj, String p1, String p2)
    {
       this.game = obj;
       this.p1Name = p1;
       this.p2Name = p2;
       canvas = new SOSCanvas(game);
       space = canvas.getSpace();
       setPreferredSize(new Dimension(WIDTH, HEIGHT));
       buttonListener = new MyRatioButtonListener();
       buttonGroup = new ButtonGroup();
       createComponentsPanel();
       canvas.addMouseListener(new MyMouseListener());
       this.setLayout(new BorderLayout());
       this.add(new JLabel(logo), BorderLayout.NORTH);
       this.add(canvas, BorderLayout.CENTER);
       this.add(components, BorderLayout.SOUTH);
    }
    public void createComponentsPanel()
    {
        components = new JPanel();
        components.setPreferredSize(new Dimension(WIDTH, COMPONENTS_HEIGHT));
        components.setLayout(new FlowLayout());
        p1 = new JLabel(p1Name + ":" + game.getPlayerScore1());
        p2 = new JLabel(p2Name + ":" + game.getPlayerScore2());
        p1.setOpaque(true);
        p2.setOpaque(true);
        p1.setBackground (Color.GREEN);
        sButton = new JRadioButton();
        sButton.addActionListener(buttonListener);
        buttonGroup.add(sButton);
        oButton = new JRadioButton();
        oButton.addActionListener(buttonListener);
        buttonGroup.add(oButton);
        sButton.setSelected(true);
        components.add(p1);
        components.add(sButton);
        components.add(oButton);
        components.add(p2);


    }
    class MyRatioButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == sButton)
            {
                sButton.setSelected(true);
                oButton.setSelected(false);
            }
            else
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
            if(e.getX() >= space && e.getX() <= canvas.getEndOfX() && e.getY() >= space && e.getY() <= canvas.getEndOfY())
            {
                if(sButton.isSelected())
                {
                    game.play('s', (int)(((e.getY() - space) / space)) + 1, (int)(((e.getX() - space) / space)) + 1);
                }
                else
                {
                    game.play('o',  (int)(((e.getY() - space) / space)) + 1, (int)(((e.getX() - space) / space)) + 1);
                }
                if(game.getTurn() == 1)
                {
                    p1.setBackground(Color.GREEN);
                    p2.setBackground(Color.WHITE);
                }
                else
                {
                    p2.setBackground(Color.GREEN);
                    p1.setBackground(Color.WHITE);
                }
                p1.setText(p1Name + ":" + game.getPlayerScore1());
                p2.setText(p2Name + ":" + game.getPlayerScore2());
                canvas.repaint();
                game.printBoard();
                if(game.getPlayerScore1() > game.getPlayerScore2())
                {
                    champion = p1Name;
                }
                else if(game.getPlayerScore2() > game.getPlayerScore1())
                {
                    champion = p2Name;
                }
                else
                {
                    champion = "draw";
                }
                if(game.isGameOver()  && !champion.equals("draw") )
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
