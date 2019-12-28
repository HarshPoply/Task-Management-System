package tsk.mgmt;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.*;
class StaffLogin extends JPanel
{
	JLabel l;
	JLabel l1=new JLabel("STAFF SECTION");
	JLabel l2;
	String name;
	BufferedImage img;
	JPanel p = new JPanel();
	JPanel information = new JPanel();
	StaffLogin()
	{
		Database.setFeel();
		loadImage();
		Database.staffInformation();
		setLocationAndSize();
		setComponentsFont();
		setComponentsColor();
		addListeners();
		setComponentsCursor();
		addPanel();
	}
	public void assign(String p)
	{
		name = p;
	}
	public void loadImage()
	{
		try 
		{
			img = ImageIO.read(Login.class.getResource(Database.staff_login));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, Database.contentpane_width, Database.contentpane_height, this);
		
	}
	public void setLocationAndSize()
	{
		setSize(Database.contentpane_width,Database.contentpane_height);
		setVisible(true);
		setLayout(null);
		p.setLayout(new FlowLayout(FlowLayout.CENTER));
		p.setSize(Database.contentpane_width,60);
		p.setOpaque(false);		//To make transparent JPanel
		information.setLayout(new FlowLayout(FlowLayout.CENTER));
		information.setLocation(0, Database.contentpane_height/2-130);
		information.setSize(Database.contentpane_width,240);
		information.setOpaque(false);
		l2 = new JLabel("<html>"
				+ "Total Number of Task Received&nbsp;&nbsp;: <u>"+Database.taskReceived+"</u><br><br>"
				+ "Total Number of Task Submitted&nbsp;: <u>"+Database.taskCompleted1+"</u><br><br>"
				+ "Total Number of Task Pending&nbsp;&nbsp;&nbsp;&nbsp;: <u>"+Database.taskPending1+"</u></html>");
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean b = true;
		try
		{
			con = DriverManager.getConnection(Database.url, Database.uname, Database.pass);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,"Cannot connect to the server.");
			b = false;
		}
		if(b==true)
		{
			try
			{
				stmt = con.prepareStatement("SELECT `name` FROM `userpass` WHERE `uid`=? AND `active`=1");
				stmt.setString(1, Database.login_uid);
				rs = stmt.executeQuery();
				rs.next();
				assign(rs.getString("name"));
			}
			catch(Exception er)
			{
				JOptionPane.showMessageDialog(new StaffLogin(), er, "Error", JOptionPane.WARNING_MESSAGE);
			}
		}
		l = new JLabel("Welcome: "+name);
		l.setBounds(200,100,200,20);
		l1.setSize(800,50);						//STAFF SECTION
	}
	public void setComponentsFont()
	{
		l.setFont(new Font("Arial",Font.BOLD,20));
		l1.setFont(new Font("Arial",Font.BOLD,50));
		l2.setFont(new Font("Arial",Font.BOLD,25));
	}
	public void setComponentsColor()
	{
		l.setForeground(Color.WHITE);
		l1.setForeground(Color.WHITE);
		l2.setForeground(Color.WHITE);
	}
	public void addListeners()
	{
	}
	public void setComponentsCursor()
	{
	}
	public void addPanel()
	{
		information.add(l2);
		add(l);
		add(information);
		p.add(l1);
		add(p);
		repaint();
	}
	public static void main(String[] args)
	{
		JFrame f = new JFrame();
		f.setExtendedState(f.MAXIMIZED_BOTH);
		f.setVisible(true);
		f.setTitle("STAFF SECTION");
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.add(new StaffLogin());
		JMenuBar mb = new JMenuBar(); 
		JMenu view = new JMenu("View");
		JMenu account = new JMenu("Account");
		JMenuItem i1,i2,i3;
		i1 = new JMenuItem("View Task");
		i2 = new JMenuItem("Update Profile");
		i3 = new JMenuItem("Logout");
		view.add(i1);
		account.add(i2);
		account.add(i3);
		mb.add(view);
		mb.add(account);
		view.setFont(new Font("Arial",Font.BOLD,14));
		account.setFont(new Font("Arial",Font.BOLD,14));
		i1.setFont(new Font("Arial",Font.BOLD,14));
		i2.setFont(new Font("Arial",Font.BOLD,14));
		i3.setFont(new Font("Arial",Font.BOLD,14));
		i1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		ViewTask.main(new String[] {});
				f.dispose();			//To close login frame inside JPanel class
	    	}
	    });
		i2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		UpdateProfile.main(new String[] {});
				f.dispose();			//To close login frame inside JPanel class
	    	}
	    });
		i3.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		JOptionPane.showMessageDialog(null, "Logged Out Successfully");
	    		Login.main(new String[] {});
				f.dispose();			//To close login frame inside JPanel class
	    	}
	    });
		f.setJMenuBar(mb);
	}
}
