package tsk.mgmt;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.*;
class AddTasks extends JPanel implements ActionListener, KeyListener
{
	JLabel l1 = new JLabel("ADD TASK");
	JLabel l2 = new JLabel("Task Name");
	JLabel l3 = new JLabel("Description");
	JLabel l4 = new JLabel("Date");
	JLabel l5 = new JLabel("Start Time");	//DOB
	JLabel l6 = new JLabel("End Time");		//Address
	JLabel l7 = new JLabel("UPDATE TASK");		//UPDATE TASK
	JTextField t1 = new JTextField();			//Task Name
	JTextField t2 = new JTextField();			//Task Details
	JButton b1 = new JButton("Add Task");
	JButton b2 = new JButton("Back");
	JButton b3 = new JButton("Reset");
	JButton b4 = new JButton("Update Task");
	JComboBox date,month,year;
	boolean leap = false, d29=false,d30=false,d31=false,gentid=false;
	JComboBox h,mi,mer;
	JComboBox h1,mi1,mer1;
	String[] d={"--Date--","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	String[] m={"--Month--","January","Febuary","March","April","May","June","July","August","September","October","November","December"};
	String[] y={"--Year--","2018","2019","2020"};
	String[] hour={"--Hour--","01","02","03","04","05","06","07","08","09","10","11","12"};
	String[] minute = {"--Minute--","00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"};
	String[] meridian={"--Select--","AM","PM"};
	int count = 5;
	String tid;
	JLabel msg1 = new JLabel("* Enter task name");
	JLabel msg2 = new JLabel("* Enter task description");
	JLabel msg3 = new JLabel("* Select date");
	JLabel msg4 = new JLabel("* Select Starting time");
	JLabel msg5 = new JLabel("* Select Ending time");
	BufferedImage img;
	JPanel p = new JPanel();
	AddTasks()
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
			img = ImageIO.read(Login.class.getResource(Database.add_task));
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
		t1.setBounds(620,90,340,30);		
		t2.setBounds(620,140,340,30);
		b1.setBounds(510,370,100,30);		//Add Task Button
		b2.setBounds(630,370,100,30);
		b3.setBounds(750,370,100,30);
		b4.setBounds(480,370,130,30);		//Update Task Button
		msg1.setBounds(963,100,120,10);
		msg2.setBounds(963,150,143,15);
		msg3.setBounds(963,205,100,10);
		msg4.setBounds(963,255,130,15);
		msg5.setBounds(963,305,130,15);
		l1.setSize(800,50);			//ADD TASK
		l2.setBounds(450,100,130,20);	
		l3.setBounds(450,150,150,20);	
		l4.setBounds(450,200,130,20);	
		l5.setBounds(450,250,130,20);	
		l6.setBounds(450,300,130,20);	
		l7.setSize(800,50);			//UPDATE TASK
		date=new JComboBox(d);
		date.setBounds(860,195,100,30);
		date.setEditable(false);
		month=new JComboBox(m);
		month.setBounds(740,195,105,30);
		month.setEditable(false);
		year=new JComboBox(y);
		year.setBounds(620,195,100,30);
		year.setEditable(false);
		date.setEnabled(false);
		month.setEnabled(false);
		h=new JComboBox(hour);
		h.setBounds(620,245,100,30);
		h.setEditable(false);
		mi=new JComboBox(minute);
		mi.setBounds(740,245,100,30);
		mi.setEditable(false);
		mer=new JComboBox(meridian);
		mer.setBounds(860,245,100,30);
		mer.setEditable(false);
		h1=new JComboBox(hour);
		h1.setBounds(620,295,100,30);
		h1.setEditable(false);
		mi1=new JComboBox(minute);
		mi1.setBounds(740,295,100,30);
		mi1.setEditable(false);
		mer1=new JComboBox(meridian);
		mer1.setBounds(860,295,100,30);
		mer1.setEditable(false);
	}
	public void setComponentsFont()
	{
		l1.setFont(new Font("Arial",Font.BOLD,50));
		l2.setFont(new Font("Arial",Font.BOLD,20));
		l3.setFont(new Font("Arial",Font.BOLD,20));
		l4.setFont(new Font("Arial",Font.BOLD,20));
		l5.setFont(new Font("Arial",Font.BOLD,20));
		l6.setFont(new Font("Arial",Font.BOLD,20));
		l7.setFont(new Font("Arial",Font.BOLD,50));
		t1.setFont(new Font("Arial",Font.BOLD,15));
		t2.setFont(new Font("Arial",Font.BOLD,15));
		b1.setFont(new Font("Arial",Font.BOLD,15));
		b2.setFont(new Font("Arial",Font.BOLD,15));
		b3.setFont(new Font("Arial",Font.BOLD,15));
		b4.setFont(new Font("Arial",Font.BOLD,15));
		date.setFont(new Font("Arial",Font.BOLD,13));
		month.setFont(new Font("Arial",Font.BOLD,13));
		year.setFont(new Font("Arial",Font.BOLD,13));
		h.setFont(new Font("Arial",Font.BOLD,13));
		mi.setFont(new Font("Arial",Font.BOLD,13));
		mer.setFont(new Font("Arial",Font.BOLD,13));
		h1.setFont(new Font("Arial",Font.BOLD,13));
		mi1.setFont(new Font("Arial",Font.BOLD,13));
		mer1.setFont(new Font("Arial",Font.BOLD,13));
		revalidate();
	}
	public void setComponentsColor()
	{
		l1.setForeground(Color.WHITE);
		l2.setForeground(Color.WHITE);
		l3.setForeground(Color.WHITE);
		l4.setForeground(Color.WHITE);
		l5.setForeground(Color.WHITE);
		l6.setForeground(Color.WHITE);
		l7.setForeground(Color.WHITE);
		msg1.setForeground(Color.RED);
		msg2.setForeground(Color.RED);
		msg3.setForeground(Color.RED);
		msg4.setForeground(Color.RED);
		msg5.setForeground(Color.RED);
		t1.setForeground(Color.BLACK);
		t2.setForeground(Color.BLACK);
	}
	public void addListeners()
	{
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		year.addActionListener(this);
		month.addActionListener(this);
		b1.addKeyListener(this);
		b2.addKeyListener(this);
		b3.addKeyListener(this);
		b4.addKeyListener(this);
	}
	public void setComponentsCursor()
	{
		b1.setCursor(Database.cur);
		b2.setCursor(Database.cur);
		b3.setCursor(Database.cur);
		b4.setCursor(Database.cur);
		date.setCursor(Database.cur);
		month.setCursor(Database.cur);
		year.setCursor(Database.cur);
		h.setCursor(Database.cur);
		mi.setCursor(Database.cur);
		mer.setCursor(Database.cur);
		h1.setCursor(Database.cur);
		mi1.setCursor(Database.cur);
		mer1.setCursor(Database.cur);
	}
	public void addPanel()
	{
		if(Database.uptask)		//Update Task
		{
			p.add(l7);
			add(b4);
			Database.uptask = false;
			Connection con = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			boolean b = true;
			try
			{
				con = DriverManager.getConnection(Database.url, Database.uname, Database.pass);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,"Cannot connect to the server.");
				b = false;
			}
			if(b)
			{
				try
				{
					stmt = con.prepareStatement("SELECT * FROM `taskdetails` WHERE `tid`= ? AND `active`=1");
					stmt.setString(1, Database.ttid);
					rs = stmt.executeQuery();
					rs.next();
					t1.setText(rs.getString("taskname"));
					t2.setText(rs.getString("description"));
					year.setSelectedItem(rs.getString("year"));
					month.setSelectedItem(rs.getString("month"));
					date.setSelectedItem(rs.getString("date"));
					h.setSelectedItem(rs.getString("shour"));
					mi.setSelectedItem(rs.getString("sminute"));
					mer.setSelectedItem(rs.getString("sm"));
					h1.setSelectedItem(rs.getString("ehour"));
					mi1.setSelectedItem(rs.getString("eminute"));
					mer1.setSelectedItem(rs.getString("sm"));
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(new AddTasks(), e, "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		else
		{
			p.add(l1);
			add(b1);
		}
		add(p);
		add(l2);
		add(l3);
		add(l4);
		add(l5);
		add(l6);
		add(t1);
		add(t2);
		add(b2);
		add(b3);
		add(date);
		add(month);
		add(year);
		add(h);
		add(mi);
		add(mer);
		add(h1);
		add(mi1);
		add(mer1);
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
    	if(e.getSource()==b1)
    	{	
    		if(e.getKeyCode() == KeyEvent.VK_ENTER)
    			b1.doClick();
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
    	else if(e.getSource()==b4)
    	{
    		if(e.getKeyCode() == KeyEvent.VK_ENTER)
    			b4.doClick();
    	}
    }
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)						//Add Task
		{
			remove(msg1);
			remove(msg2);
			remove(msg3);
			remove(msg4);
			remove(msg5);
			count = 5;
			if(t1.getText().equals(""))
			{
				add(msg1);
				repaint();
			}
			else
			{
				count--;
			}
			if(t2.getText().equals(""))
			{
				add(msg2);
				repaint();
			}
			else
			{
				count--;
			}
			if(date.getSelectedItem().equals("--Date--") || month.getSelectedItem().equals("--Month--") || year.getSelectedItem().equals("--Year--"))
			{
				add(msg3);
				repaint();
			}
			else
			{
				count--;
			}
			if(h.getSelectedItem().equals("--Hour--") || mi.getSelectedItem().equals("--Minute--") || mer.getSelectedItem().equals("--Select--"))
			{
				add(msg4);
				repaint();
			}
			else
			{
				count--;
			}
			if(h1.getSelectedItem().equals("--Hour--") || mi1.getSelectedItem().equals("--Minute--") || mer1.getSelectedItem().equals("--Select--"))
			{
				add(msg5);
				repaint();
			}
			else
			{
				count--;
			}
			if(count==0)
			{
				Connection con = null;
	            PreparedStatement stmt = null;
	            ResultSet rs = null;
	            boolean b = true;
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
	            		stmt = con.prepareStatement("SELECT `uid` FROM `userpass` WHERE `username`=? AND `active`=1");
	            		stmt.setString(1, Database.uss);
	            		rs = stmt.executeQuery();
	            		rs.next();
	            		String uid = rs.getString(1);
	            		genTID();
	            		if(gentid)
	            		{
	            			stmt = con.prepareStatement("INSERT INTO `taskdetails` (`tid`,`uid`,`taskname`,`description`,`date`,`month`,`year`,`shour`,`sminute`,`sm`,`ehour`,`eminute`,`em`,`submit`,`active`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,0,1)");
	            			stmt.setString(1, tid);
	            			stmt.setString(2, uid);
	            			stmt.setString(3, t1.getText());
	            			stmt.setString(4, t2.getText());
	            			stmt.setString(5,(String) date.getSelectedItem());
	            			stmt.setString(6,(String) month.getSelectedItem());
	            			stmt.setString(7,(String) year.getSelectedItem());
	            			stmt.setString(8,(String) h.getSelectedItem());
	            			stmt.setString(9,(String) mi.getSelectedItem());
	            			stmt.setString(10,(String) mer.getSelectedItem());
	            			stmt.setString(11,(String) h1.getSelectedItem());
	            			stmt.setString(12,(String) mi1.getSelectedItem());
	            			stmt.setString(13,(String) mer1.getSelectedItem());
	            			stmt.executeUpdate();
	            			JOptionPane.showMessageDialog(null,"Task Added Successfully");
	            			reset();
	            		}
	            	}
	            	catch(Exception ex)
	            	{
	            		JOptionPane.showMessageDialog(new AddTasks(), ex, "Error", JOptionPane.WARNING_MESSAGE);
	            	}
	            }
			}
		}
		else if(e.getSource()==b2)					//Back
		{
			Database.addtask = true;
			ViewTasks.main(new String[] {});
			((Window) getRootPane().getParent()).dispose();			//To close login frame inside JPanel class
		}
		else if(e.getSource()==b3)					//Reset
		{
			reset();
		}
		else if(e.getSource()==b4)					//Update Task
		{
			remove(msg1);
			remove(msg2);
			remove(msg3);
			remove(msg4);
			remove(msg5);
			count = 5;
			if(t1.getText().equals(""))
			{
				add(msg1);
				repaint();
			}
			else
			{
				count--;
			}
			if(t2.getText().equals(""))
			{
				add(msg2);
				repaint();
			}
			else
			{
				count--;
			}
			if(date.getSelectedItem().equals("--Date--") || month.getSelectedItem().equals("--Month--") || year.getSelectedItem().equals("--Year--"))
			{
				add(msg3);
				repaint();
			}
			else
			{
				count--;
			}
			if(h.getSelectedItem().equals("--Hour--") || mi.getSelectedItem().equals("--Minute--") || mer.getSelectedItem().equals("--Select--"))
			{
				add(msg4);
				repaint();
			}
			else
			{
				count--;
			}
			if(h1.getSelectedItem().equals("--Hour--") || mi1.getSelectedItem().equals("--Minute--") || mer1.getSelectedItem().equals("--Select--"))
			{
				add(msg5);
				repaint();
			}
			else
			{
				count--;
			}
			if(count==0)
			{
				Connection con = null;
	            PreparedStatement stmt = null;
	            boolean b = true;
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
	            		stmt = con.prepareStatement("UPDATE `taskdetails` SET `taskname` = ?, `description`= ?, `date`= ?, `month`=?, `year`=?, `shour`=?, `sminute`=?, `sm`=?, `ehour`=?, `eminute`=?, `em`=?   WHERE `tid` = ?");
	            		stmt.setString(1, t1.getText());
	            		stmt.setString(2, t2.getText());
	            		stmt.setString(3,(String) date.getSelectedItem());
	            		stmt.setString(4,(String) month.getSelectedItem());
	            		stmt.setString(5,(String) year.getSelectedItem());
	            		stmt.setString(6,(String) h.getSelectedItem());
	            		stmt.setString(7,(String) mi.getSelectedItem());
	            		stmt.setString(8,(String) mer.getSelectedItem());
	            		stmt.setString(9,(String) h1.getSelectedItem());
	            		stmt.setString(10,(String) mi1.getSelectedItem());
	            		stmt.setString(11,(String) mer1.getSelectedItem());
	            		stmt.setString(12, Database.ttid);
	            		stmt.executeUpdate();	//To execute above SQL Query
	            		JOptionPane.showMessageDialog(null,"Task Updated Successfully");
	            	}
	            	catch(Exception exd)
	            	{
	            		JOptionPane.showMessageDialog(new AddTasks(), exd, "Error", JOptionPane.WARNING_MESSAGE);
	            	}
	            }
			}
		}
		else if(e.getSource()==year)		//Year
		{
			if(year.getSelectedItem()=="--Year--")
			{
				month.setSelectedItem("--Month--");
				month.setEnabled(false);
				date.setSelectedItem("--Date--");
				date.setEnabled(false);
			}
			else
			{
				month.setEnabled(true);
				checkLeap();
				if(month.getSelectedItem()!="--Month--")
				{
					updateDate();
				}
			}
		}
		else if(e.getSource()==month)		//Month
		{
			if(month.getSelectedItem()=="--Month--")
			{
				date.setSelectedItem("--Date--");
				date.setEnabled(false);
			}
			else
			{
				updateDate();
			}
		}
	}
	public void reset()
	{
		leap = false;
		if(d29)
		{
			date.addItem("29");
			d29 = false;
		}
		if(d30)
		{
			date.addItem("30");
			d30 = false;
		}
		if(d31)
		{
			date.addItem("31");
			d31 = false;
		}
		date.setSelectedItem("--Date--");
		month.setSelectedItem("--Month--");
		year.setSelectedItem("--Year--");
		h.setSelectedItem("--Hour--");
		mi.setSelectedItem("--Minute--");
		mer.setSelectedItem("--Select--");
		h1.setSelectedItem("--Hour--");
		mi1.setSelectedItem("--Minute--");
		mer1.setSelectedItem("--Select--");
		t1.setText("");
		t2.setText("");
		remove(msg1);
		remove(msg2);
		remove(msg3);
		remove(msg4);
		remove(msg5);
		repaint();
	}
	public void checkLeap()
	{
		int n = Integer.parseInt((String) year.getSelectedItem());	//String to int
		if(n%4==0)					//Divisible by 4
		{
			if(n%100!=0)			//Not Divisible by 100
			{
				//Leap Year
				leap = true;
			}
			else					//Divisible by 100
			{
				if(n%400==0)
				{
					//Leap Year
					leap = true;
				}
				else
				{
					//Not a Leap Year
					leap = false;
				}
			}
		}
		else
		{
			//Not a Leap Year
			leap = false;
		}
	}
	public void updateDate()
	{
		if(d29)
		{
			date.addItem("29");
			d29 = false;
		}
		if(d30)
		{
			date.addItem("30");
			d30 = false;
		}
		if(d31)
		{
			date.addItem("31");
			d31 = false;
		}
		if(month.getSelectedItem()=="April" || month.getSelectedItem()=="June" || month.getSelectedItem()=="September" || month.getSelectedItem()=="November")
		{
			date.removeItem("31");
			d31 = true;
		}
		if(month.getSelectedItem()=="Febuary")
		{
			date.removeItem("30");
			date.removeItem("31");
			d30 = true;
			d31 = true;
			if(!leap)
			{
				date.removeItem("29");
				d29 = true;
			}	
		}
		date.setEnabled(true);
	}
	public void genTID()
	{
		Connection con = null;
        PreparedStatement stmt;
        ResultSet rs = null;
        boolean b = true;
        try
        {
        	con = DriverManager.getConnection(Database.url, Database.uname, Database.pass);
        }
		catch(Exception exc)
        {
			b = false;
			JOptionPane.showMessageDialog(null,"Cannot connect to the server.");
        }
        if(b==true)
        {
        	try
        	{
        		stmt = con.prepareStatement("SELECT `tid` FROM `taskdetails` ORDER BY `tid` DESC LIMIT 1");
        		rs = stmt.executeQuery();
        		if(rs.next())
        		{
        			String r1 = rs.getString("tid");
        			int ln = r1.length();
        			String snum = r1.substring(3,ln);
        			int n = Integer.parseInt(snum);
        			n++;
        			snum = Integer.toString(n);
        			tid = "TID"+snum;
        			gentid = true;
        		}
        		else
        		{
        			tid = "TID101";
        			gentid = true;
        		}
        	}
        	catch(Exception e)
        	{
        		gentid = false;
        		JOptionPane.showMessageDialog(new AddTasks(), e, "Error", JOptionPane.WARNING_MESSAGE);
        	}
        }
	}
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				JFrame f = new JFrame();
				f.setExtendedState(f.MAXIMIZED_BOTH);
				f.setVisible(true);
				f.setTitle("ADMINISTRATOR SECTION");
		        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		        f.add(new AddTasks());
			}
		});
	}
}
