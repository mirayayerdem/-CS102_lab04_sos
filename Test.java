
import cs101.sosgame.SOS;

import javax.swing.*;
import java.awt.*;

public class Test {
    public static void main(String[] args) {

        String size = JOptionPane.showInputDialog(new JFrame() , "Board Size:");
        int dimension = Integer.parseInt(size);
        String p1 = JOptionPane.showInputDialog(new JFrame(), "Enter the name of the first player: ");
        String p2 = JOptionPane.showInputDialog(new JFrame(), "Enter the mane of the second player: ");
        JFrame frame = new JFrame();
        frame.setTitle("SOS Game");
        SOS a = new SOS(dimension);
        SOSGUIPanel panelgui = new SOSGUIPanel(a,p1,p2);
        SOSCanvas b = new SOSCanvas(a);
        frame.getContentPane().add(panelgui);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();


    }
}
