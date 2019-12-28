package tsk.mgmt;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.*;
class ChangePassword extends JPanel implements ActionListener, KeyListener
{
	JLabel l1 = new JLabel("CHANGE PASSWORD");
	JLabel l = new JLabel("Username");              //Username
	JLabel l2 = new JLabel("Enter old password");
	JLabel l3 = new JLabel("Enter new password");
	JLabel l4 = new JLabel("Confirm new password");
	JTextField t = new JTextField();		        //Username
	JPasswordField t1 = new JPasswordField();		    //Old Username
	JPasswordField t2 = new JPasswordField();		    //New Username
	JPasswordField t3 = new JPasswordField();		    //Confirm new Username
	JButton b1 = new JButton("Change Password");
	JButton b2 = new JButton("Reset");
	JButton b3 = new JButton("Back");
	JCheckBox check1 = new JCheckBox("Show Password");
	JLabel msg1 = new JLabel("*Enter old password");
	JLabel msg2 = new JLabel("*Enter new password");
	JLabel msg3 = new JLabel("*Confirm new password");
	JLabel msg4 = new JLabel("*Enter valid password");
	int count = 3;
	BufferedImage img;
	JPanel p = new JPanel();
	ChangePassword()
	{
		Database.setFeel();
		loadImage();
		setLocationAndSize();
		setComponentsFont();
		setComponentsColor();
		addListeners();
		setComponentsCursor();
		addPanel();
	}
	public void loadImage()
	{
		try 
		{
			img = ImageIO.read(Login.class.getResource(Database.change_password));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, Database.width, Database.height, this);
		
	}
	public void setLocationAndSize()
	{
		setSize(Database.width,Database.height);
		setVisible(true);
		setLayout(null);
		p.setLayout(new FlowLayout(FlowLayout.CENTER));
		p.setSize(Database.width,40);
		p.setOpaque(false);		//To make transparent JPanel
		b1.setBounds(90,310,170,30);		//Change Password
		b2.setBounds(400,310,100,30);		//Reset
		b3.setBounds(280,310,100,30);		//Back
		check1.setBounds(235,260,150,30);
		l1.setSize(400,30);
		l2.setBounds(10,120,200,20);
		l3.setBounds(10,170,200,20);
		l4.setBounds(10,230,230,20);
		l.setBounds(10,70,200,20);
		t.setBounds(240,65,200,30);
		t.setText("admin");
		t.setEditable(false);
		t1.setBounds(240,115,200,30);
		t2.setBounds(240,165,200,30);
		t3.setBounds(240,225,200,30);
	}
	public void setComponentsFont()
	{
		b1.setFont(new Font("Arial",Font.BOLD,15));
		b2.setFont(new Font("Arial",Font.BOLD,15));
		b3.setFont(new Font("Arial",Font.BOLD,15));
		l1.setFont(new Font("Arial",Font.BOLD,30));
		l.setFont(new Font("Arial",Font.BOLD,20));
		l2.setFont(new Font("Arial",Font.BOLD,20));
		l3.setFont(new Font("Arial",Font.BOLD,20));
		l4.setFont(new Font("Arial",Font.BOLD,20));
		check1.setFont(new Font("Arial",Font.BOLD,15));
		t1.setFont(new Font("Arial",Font.BOLD,15));
		t2.setFont(new Font("Arial",Font.BOLD,15));
		t3.setFont(new Font("Arial",Font.BOLD,15));
		t.setFont(new Font("Arial",Font.BOLD,15));
	}
	public void setComponentsColor()
	{
		t1.setForeground(Color.BLACK);
		t2.setForeground(Color.BLACK);
		t3.setForeground(Color.BLACK);
		t.setForeground(Color.BLACK);
		l1.setForeground(Color.WHITE);
		l.setForeground(Color.WHITE);
		l2.setForeground(Color.WHITE);
		l3.setForeground(Color.WHITE);
		l4.setForeground(Color.WHITE);
		check1.setForeground(Color.WHITE);
	}
	public void addListeners()
	{
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		check1.addActionListener(this);
		t3.addKeyListener(this);
		check1.addKeyListener(this);
		b1.addKeyListener(this);
		b2.addKeyListener(this);
		b3.addKeyListener(this);
	}
	public void setComponentsCursor()
	{
		b1.setCursor(Database.cur);
		b2.setCursor(Database.cur);
		b3.setCursor(Database.cur);
		check1.setCursor(Database.cur);
	}
	public void addPanel()
	{
		p.add(l1);
		add(p);
		add(l2);
		add(l3);
		add(l4);
		add(l);
		add(check1);
		add(t1);
		add(t2);
		add(t3);
		add(t);
		add(b1);
		add(b2);
		add(b3);
		repaint();
	}
	public void keyTyped(KeyEvent e) 
    {
    	
    }
    public void keyReleased(KeyEvent e) 
    {
    	
    }
    public void keyPressed(KeyEvent e) 
    {
    	if(e.getSource()==b1 || e.getSource()==t3)
    	{	
    		if(e.getKeyCode() == KeyEvent.VK_ENTER)
    			b1.doClick();
    	}
    	else if(e.getSource()==check1)
    	{
    		if(e.getKeyCode() == KeyEvent.VK_ENTER)
    			check1.doClick();
    	}
    	else if(e.getSource()==b2)
    	{	
    		if(e.getKeyCode() == KeyEvent.VK_ENTER)
    			b2.doClick();
    	}
    	else if(e.getSource()==b3)
    	{	
    		if(e.getKeyCode() == KeyEvent.VK_ENTER)
    			b3.doClick();
    	}
    }
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)			//Change Password
		{
			count = 3;
			remove(msg1);
			remove(msg2);
			remove(msg3);
			remove(msg4);
			repaint();
			if(t1.getText().equals(""))
        	{
        		msg1.setBounds(445,125,120,15);
				msg1.setForeground(Color.RED);
				add(msg1);
				repaint();
        	}
        	else
			{
				count--;
			}
			if(t2.getText().equals(""))
        	{
        		msg2.setBounds(445,175,125,15);
				msg2.setForeground(Color.RED);
				add(msg2);
				repaint();
        	}
        	else
			{
				count--;
			}
			if(t3.getText().equals(""))
        	{
        		msg3.setBounds(445,235,140,15);
				msg3.setForeground(Color.RED);
				add(msg3);
				repaint();
        	}
        	else
			{
				count--;
			}
			if(count==0)
			{
				boolean b = true;
				Connection con = null;
	            PreparedStatement stmt = null;
	            ResultSet rs = null;
	            try
	            {
	            	con = DriverManager.getConnection(Database.url, Database.uname, Database.pass);
	            }
				catch(Exception exc)
	            {
					JOptionPane.showMessageDialog(null,"Cannot connect to the server.");
					b = false;
	            }
	            if(b==true)
	            {
	            	try
	            	{
	            		stmt = con.prepareStatement("SELECT `password` FROM `userpass` WHERE `username`= BINARY 'admin'");
	    				rs = stmt.executeQuery();
	    				rs.next();
	    				if(rs.getString(1).equals(t1.getText()))
	    				{
	    					if(t2.getText().equals(t3.getText()))
	    					{
	    						stmt = con.prepareStatement("UPDATE `userpass` SET `password` = ? WHERE `username` = 'admin'");
	    						stmt.setString(1, t2.getText());
	    						stmt.executeUpdate();
	    						JOptionPane.showMessageDialog(null,"Password Updated Successfully");
	    						t1.setText("");
	    						t2.setText("");
	    						t3.setText("");
	    						check1.setSelected(false);
	    					}
	    					else
	    					{
	    						JOptionPane.showMessageDialog(null,"New Password does not matched");
	    					}
	    				}
	    				else
	    				{
	    					msg4.setBounds(445,125,130,15);
	    					msg4.setForeground(Color.RED);
	    					add(msg4);
	    					repaint();
	    				}
	            	}
	            	catch(Exception ex)
	            	{
	            		JOptionPane.showMessageDialog(new ChangePassword(), ex, "Error", JOptionPane.WARNING_MESSAGE);
	            	}
	            }
			}
		}
		else if(e.getSource()==b2)		  //Reset
		{
			t1.setText("");
			t2.setText("");
			t3.setText("");
			if(check1.isSelected())
            	check1.doClick();
			remove(msg1);
			remove(msg2);
			remove(msg3);
			remove(msg4);
			repaint();
		}
		else if(e.getSource()==b3)		//Back
		{
			((Window) getRootPane().getParent()).dispose();			//To close login frame inside JPanel class
		}
		else if(e.getSource()==check1) 		//Show Password
		{
            if(check1.isSelected())
			{
            	t1.setEchoChar((char) 0);
            	t2.setEchoChar((char) 0);
            	t3.setEchoChar((char) 0);
            } 
			else 
			{
				t1.setEchoChar('*');
				t2.setEchoChar('*');
				t3.setEchoChar('*');
            }
        }
	}
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				JFrame f = new JFrame();
				f.setTitle("ADMINISTRATOR SECTION");
		        f.setVisible(true);
		        f.setSize(620,420);
		        f.setLocationRelativeTo(null);
		        f.setDefaultCloseOperation(f.DO_NOTHING_ON_CLOSE);
		        f.setResizable(false);
				f.add(new ChangePassword());
			}
		});
	}
}