//Checkers02
/**
 * 
 *@author Ori Weis
 *25.3.21
 * 
**/
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.event.*;
	
public class MessageBox implements ActionListener {
		private JFrame frame;
		
		private int response;
		
		private Object[] options = {"Yes","No","ask later"};
		
		//private int num=0;
		private JButton button2;
		private JButton button;
		
		//Construnctor
		public MessageBox() 
		{
		   try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			frame = new JFrame("Keep Playing Or Exit");
			
			frame.setLayout(new FlowLayout());
			
			button = new JButton(" Play Again ");
			button.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));

			//button.setAlignmentX((float) 10.00);
			//button.set("yellow");
			
			button2 = new JButton(" EXIT ");
			button2.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));

			
			button2.addActionListener(this);
			button.addActionListener(this);
			
			frame.add(button);
			frame.add(button2);
			frame.setSize(400, 400);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		
			public void actionPerformed(ActionEvent e) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 if(e.getSource() == this.button) 
				 {
					 this.response=JOptionPane.showOptionDialog(frame,"Would you like to Play Again ",null, JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[2]);
				 	 if(this.response==JOptionPane.YES_OPTION)
				 		 System.out.println("Choosen To Play Again");
				 	 else if(this.response==JOptionPane.NO_OPTION)
				 		 System.out.println("Bye Bye");
				 	//Check The "ask later" and what can i do about it 
				 }		
				else
					 if(e.getSource() == this.button2)
						 JOptionPane.showMessageDialog(frame, "Exiting ...");
			}
			
		}



