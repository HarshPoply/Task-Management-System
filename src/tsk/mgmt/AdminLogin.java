package tsk.mgmt;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
class AdminLogin extends JPanel
{
	
	JLabel l1 = new JLabel("ADMINISTRATOR SECTION");
	JLabel l2 = new JLabel("Welcome: ADMINISTRATOR");
	JLabel l3;
	BufferedImage img;
	JPanel p = new JPanel();
	JPanel information = new JPanel();
	AdminLogin()
	{
		Database.setFeel();
		loadImage();
		Database.adminInformation();
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
			img = ImageIO.read(Login.class.getResource(Database.admin_login));
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
		information.setLocation(0, Database.contentpane_height/2-170);
		information.setSize(Database.contentpane_width,240);
		information.setOpaque(false);
		l1.setSize(800,50);
		l2.setBounds(200,100,200,20);
		l3 =  new JLabel("<html>"
				+ "Total Number of Staff Members Registered&nbsp;: <u>"+Database.noOfUsers+"</u><br><br>"
				+ "Total Number of Task Alloted&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: <u>"+Database.taskAlloted+"</u><br><br>"
				+ "Total Number of Task Completed&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: <u>"+Database.taskCompleted+"</u><br><br>"
				+ "Total Number of Task Pending&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: <u>"+Database.taskPending+"</u></html>");
	}
	public void setComponentsFont()
	{
		l1.setFont(new Font("Arial",Font.BOLD,50));
		l2.setFont(new Font("Arial",Font.BOLD,15));
		l3.setFont(new Font("Arial",Font.BOLD,25));
	}
	public void setComponentsColor()
	{
		l1.setForeground(Color.WHITE);
		l2.setForeground(Color.WHITE);
		l3.setForeground(Color.WHITE);
	}
	public void addListeners()
	{
		
	}
	public void setComponentsCursor()
	{
		
	}
	public void addPanel()
	{
		information.add(l3);
		p.add(l1);
		add(information);
		add(p);
		repaint();
	}
	public static void main(String[] args)
	{
			JFrame f = new JFrame();
			f.setExtendedState(f.MAXIMIZED_BOTH);
			f.setVisible(true);
			f.setTitle("ADMINISTRATOR SECTION");
		    f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		    f.add(new AdminLogin());
		    JMenu tasks = new JMenu("Tasks");
		    JMenu staff = new JMenu("Staff");
		    JMenu exit = new JMenu("Account");
		    JMenuItem i1,i3,i4,i5,i6;
		    JMenuBar mb = new JMenuBar();
		    i1 = new JMenuItem("View Tasks");  
		    i3 = new JMenuItem("Add Staff");  
		    i4 = new JMenuItem("View Staff");
		    i5 = new JMenuItem("Change Password");
		    i6 = new JMenuItem("Logout");
			tasks.add(i1);
		    staff.add(i3);
		    staff.add(i4);
		    exit.add(i5);
		    exit.add(i6);
		    mb.add(tasks);  
		    mb.add(staff);
		    mb.add(exit);
		    tasks.setFont(new Font("Arial",Font.BOLD,14));
		    staff.setFont(new Font("Arial",Font.BOLD,14));
		    exit.setFont(new Font("Arial",Font.BOLD,14));
		    i1.setFont(new Font("Arial",Font.BOLD,14));
		    i3.setFont(new Font("Arial",Font.BOLD,14));
		    i4.setFont(new Font("Arial",Font.BOLD,14));
		    i5.setFont(new Font("Arial",Font.BOLD,14));
		    i6.setFont(new Font("Arial",Font.BOLD,14));
		    i1.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e)
		    	{
		    		ViewTasks.main(new String[] {});
					f.dispose();			//To close login frame inside JPanel class
		    	}
		    });
		    i3.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e)
		    	{
		    		AddStaff.main(new String[] {});
					f.dispose();			//To close login frame inside JPanel class
		    	}
		    });
		    i4.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e)
		    	{
		    		ViewStaff.main(new String[] {});
					f.dispose();			//To close login frame inside JPanel class
		    	}
		    });
		    i5.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e)
		    	{
		    		ChangePassword.main(new String[] {});
		    	}
		    });
		    i6.addActionListener(new ActionListener() {
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