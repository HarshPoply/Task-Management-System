package tsk.mgmt;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
public class GetDimensions 
{
	public static void main(String[] args) 
	  {
	    JFrame frame = new JFrame();
	    Database.setFeel();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    frame.setVisible(true);
	    System.out.print(frame.getInsets());
	    
	    //Width and Height of Maximized JFrame
	    Database.contentpane_width = (int) frame.getContentPane().getSize().getWidth();
	    Database.contentpane_height = (int) frame.getContentPane().getSize().getHeight();
		frame.dispose();
		
		
		JFrame f = new JFrame();
		f.setSize(620,420);				//ChangePassword JFrame
	    f.setVisible(true);
	    
	    //Width and Height of Change Password JFrame
	    Database.width = (int) f.getContentPane().getSize().getWidth();
	    Database.height = (int) f.getContentPane().getSize().getHeight();
		f.dispose();
		Login.main(new String[] {});	
	  }
}
