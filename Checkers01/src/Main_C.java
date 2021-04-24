import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main_C {

	public static void main(String[] args){
		double d1=0-(Double.MAX_VALUE);
		//System.out.println(d1);
		JFrame f1=new JFrame();
		Menu m1=new Menu(f1);
		f1.getContentPane().add(m1);
	    f1.pack();
	    f1.setVisible(true);
		
		//Checkers c1=new Checkers(0);
		//MessageBox db = new MessageBox();
		//matrix m1=new matrix();
		//m1.print_mat();
	}
	
}
