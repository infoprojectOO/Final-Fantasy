package helpout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Layer2 {
    private JFrame frame = new JFrame();
    private JLayeredPane lpane = new JLayeredPane();
    private JPanel panelBlue = new JPanel();
    private JPanel panelGreen = new JPanel();
    public Layer2()
    {
        //frame.setPreferredSize(new Dimension(600, 400));
        //lpane.setBounds(0, 0, 600, 400);
    	lpane.setPreferredSize(new Dimension(600,400));
//        lpane.setLayer(panelBlue, new Integer(0));
//        lpane.setLayer(panelGreen, new Integer(-1));
        panelBlue.setBackground(Color.BLUE);
        JButton b = new JButton("help");
        b.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		System.out.println("help !");
        	}
        });
        JButton b1 = new JButton("help1");
        panelBlue.add(b);
        panelBlue.setBounds(0, 0, 600, 400);
        panelBlue.setOpaque(true);
        Component[] c = panelBlue.getComponents();
        for (Component comp : c){
        	comp.setEnabled(false);
        }
        panelGreen.setBackground(Color.GREEN);
        panelGreen.setBounds(250, 20, 100, 100);
        panelGreen.setOpaque(true);
        panelGreen.add(b1);
        lpane.add(panelBlue, new Integer(0));
        lpane.add(panelGreen, new Integer(1));
        frame.getContentPane().add(lpane);
        frame.pack();
        frame.setVisible(true);
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Layer2();
    }

}