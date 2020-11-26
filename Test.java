
import cs101.sosgame.SOS;
/**
 * a class test the SOS Game user interface
 * author: Miray Ayerdem
 * version : 25/11/2020
 */
import javax.swing.*;
import java.awt.*;


public class Test {

    public static void main(String[] args) {
        //Properties
        JFrame frame;
        JLabel label;
        String size, p1, p2;
        int dimension;
        SOS a;
        SOSGUIPanel panelgui;
        //JOptionPane'  input dialogs to take parameters to SOSGuiPanel object
        size = JOptionPane.showInputDialog(new JFrame() , "Board Size:"); //input dialog to take the dimension of the board
        dimension = Integer.parseInt(size);
        p1 = JOptionPane.showInputDialog(new JFrame(), "Enter the name of the first player: "); //p1's name
        p2 = JOptionPane.showInputDialog(new JFrame(), "Enter the name of the second player: "); //p2's name


        frame = new JFrame();
        frame.setTitle("SOS Game");
        a = new SOS(dimension);
        panelgui = new SOSGUIPanel(a,p1,p2);
        frame.getContentPane().add(panelgui);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();


    }
}
