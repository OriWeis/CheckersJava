import java.awt.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Menu extends JPanel{
    private JLabel photobg;
    private JButton onev1,aib;
    private Image img, btn1Image,btn2Image;
    public boolean ispvp = false, ispvAi = false;
    //public Rummikub gamePanel;
    JPanel panel;
    JFrame fr;

    
    public Menu(JFrame frame)
    {
        super();
        this.fr=frame;
        this.setPreferredSize(new Dimension(650,750));
        //this.setBounds(0, 0, 1300, 750);
        this.setLayout(null);
        
        /**
         * The Following Should Be Generic
         */
        img = new ImageIcon("F:\\Java\\Project\\Graphics\\Game.png").getImage();
        this.onev1 = new JButton(new ImageIcon("F:\\Java\\Project\\Graphics\\ORIWEIS1V1.png"));
        this.aib = new JButton(new ImageIcon("F:\\Java\\Project\\Graphics\\ORIWEIS1VPC.png"));

        
        onev1.setBounds(100, 500, 170, 140);
        //onev1.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));

        aib.setBounds(400, 500, 170, 140);
        //aib.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));



        onev1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Checkers c1=new Checkers(0);
                fr.dispose();
            }
        });
        
        
        aib.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Checkers c1=new Checkers(1);
                fr.dispose();
            }
        });
    
        
        this.add(onev1);
        this.add(aib);


    }
       public void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }
}