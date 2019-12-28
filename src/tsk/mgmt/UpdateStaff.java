package tsk.mgmt;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.*;
class UpdateStaff extends JPanel implements ActionListener, KeyListener
{
	JRadioButton r1 = new JRadioButton("Male");
	JRadioButton r2 = new JRadioButton("Female");
	ButtonGroup gender = new ButtonGroup();
	JLabel l1=new JLabel("UPDATE STAFF");
	JLabel l2=new JLabel("Name");
	JLabel l3=new JLabel("Father's Name");
	JLabel l4=new JLabel("Gender");
	JLabel l5=new JLabel("Date of Birth");	//DOB
	JLabel l6=new JLabel("Address");		//Address
	JLabel l7=new JLabel("City");			//City
	JLabel l8=new JLabel("Contact Number");	//Contact Number
	JLabel l9=new JLabel("Email");
	JLabel l10=new JLabel("Username");
	JLabel l11=new JLabel("Password");
	JTextField t1=new JTextField();			//Name
	JTextField t2=new JTextField();			//Father's Name
	JTextField t3=new JTextField();			//Address
	JTextField t4=new JTextField();			//City
	JTextField t5=new JTextField();			//Contact Number
	JTextField t6=new JTextField();			//Email
	JTextField t7=new JTextField();			//Username
	JPasswordField t8=new JPasswordField();	//Password
	JButton b1=new JButton("Update");
	JButton b2=new JButton("Back");
	JButton b3=new JButton("Reset");
	JButton b4 = new JButton("Change Username");
	JButton b5 = new JButton("Submit");
	JButton b6 = new JButton("Cancel");
	JComboBox date,month,year;
	String uid,temp_username;
	String[] d={"--Date--","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	String[] m={"--Month--","January","Febuary","March","April","May","June","July","August","September","October","November","December"};
	String[] y = new String[49];
	boolean leap = false, d29=false,d30=false,d31=false;
	JCheckBox showpass = new JCheckBox("Show Password");
	JLabel msg1 = new JLabel("* Enter Name");
	JLabel msg2 = new JLabel("* Enter Father's Name");
	JLabel msg3 = new JLabel("* Enter Address");
	JLabel msg4 = new JLabel("* Enter City");
	JLabel msg5 = new JLabel("* Enter Contact Number");
	JLabel msg6 = new JLabel("* Enter Email");
	JLabel msg8 = new JLabel("* Enter Password");
	JLabel msg9 = new JLabel("*Enter a valid 10 digit number");
	JLabel gen = new JLabel("* Select Gender");
	JLabel dob = new JLabel("* Enter DOB");
	int count = 10,cc=0;
	boolean b = true, genuid = false;
	BufferedImage img;
	JPanel p = new JPanel();
	UpdateStaff()
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
			img = ImageIO.read(Login.class.getResource(Database.add_staff));
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
		y[0]="--Year--";
		for(int i=2000,j=1;i>=1953;i--,j++)
		{
			y[j]=Integer.toString(i-1);
		}
		setSize(Database.contentpane_width,Database.contentpane_height);
		setVisible(true);
		setLayout(null);
		p.setLayout(new FlowLayout(FlowLayout.CENTER));
		p.setSize(Database.contentpane_width,60);
		p.setOpaque(false);		//To make transparent JPanel
		l1.setSize(800,50);		//ADD STAFF
		l2.setBounds(450,100,130,20);	//Name
		l4.setBounds(450,150,130,20);	//Gender
		l5.setBounds(450,200,150,20);	//DOB
		l3.setBounds(450,250,150,20);	//Father's Name
		l6.setBounds(450,300,130,20);	//Address
		l7.setBounds(450,350,130,23);	//City
		l8.setBounds(450,400,170,20);	//Contact Number
		l9.setBounds(450,450,170,20);	//Email
		l10.setBounds(450,500,130,20);	//Username
		l11.setBounds(450,550,130,20);	//Password
		showpass.setBounds(620,580,140,14);
		date=new JComboBox(d);
		date.setBounds(860,190,100,30);
		date.setEditable(false);
		month=new JComboBox(m);
		month.setBounds(737,190,105,30);
		month.setEditable(false);
		year=new JComboBox(y);
		year.setBounds(620,190,100,30);
		year.setEditable(false);
		month.setEnabled(false);
		date.setEnabled(false);
		r1.setBounds(620,145,100,30);			
		r2.setBounds(720,145,100,30);
		gender.add(r1);
		gender.add(r2);
		t1.setBounds(620,90,250,30);		//Name
		t2.setBounds(620,245,250,30);		//Father's Name
		t3.setBounds(620,290,250,30);		//Address
		t4.setBounds(620,340,250,30);		//City
		t5.setBounds(620,390,250,30);		//Contact Number
		t6.setBounds(620,440,250,30);		//Email
		t7.setBounds(620,490,250,30);		//Username
		t8.setBounds(620,540,250,30);		//Password
		b1.setBounds(480,620,100,30);
		b2.setBounds(600,620,100,30);
		b3.setBounds(720,620,100,30);
		b4.setBounds(880,490,170,30);
		b5.setBounds(880,490,90,30);
		b6.setBounds(980,490,90,30);
		t7.setEditable(false);
	}
	public void setComponentsFont()
	{
		date.setFont(new Font("Arial",Font.BOLD,13));
		month.setFont(new Font("Arial",Font.BOLD,13));
		year.setFont(new Font("Arial",Font.BOLD,13));
		revalidate();
		repaint();
		l1.setFont(new Font("Arial",Font.BOLD,50));
		l2.setFont(new Font("Arial",Font.BOLD,20));
		l3.setFont(new Font("Arial",Font.BOLD,20));
		l4.setFont(new Font("Arial",Font.BOLD,20));
		l5.setFont(new Font("Arial",Font.BOLD,20));
		l6.setFont(new Font("Arial",Font.BOLD,20));
		l7.setFont(new Font("Arial",Font.BOLD,20));
		l8.setFont(new Font("Arial",Font.BOLD,20));
		l9.setFont(new Font("Arial",Font.BOLD,20));
		l10.setFont(new Font("Arial",Font.BOLD,20));
		l11.setFont(new Font("Arial",Font.BOLD,20));
		showpass.setFont(new Font("Arial",Font.BOLD,14));
		t1.setFont(new Font("Arial",Font.BOLD,15));
		t2.setFont(new Font("Arial",Font.BOLD,15));
		t3.setFont(new Font("Arial",Font.BOLD,15));
		t4.setFont(new Font("Arial",Font.BOLD,15));
		t5.setFont(new Font("Arial",Font.BOLD,15));
		t6.setFont(new Font("Arial",Font.BOLD,15));
		t7.setFont(new Font("Arial",Font.BOLD,15));
		t8.setFont(new Font("Arial",Font.BOLD,15));
		r1.setFont(new Font("Arial",Font.PLAIN,20));
		r2.setFont(new Font("Arial",Font.PLAIN,20));
		b1.setFont(new Font("Arial",Font.BOLD,15));
		b2.setFont(new Font("Arial",Font.BOLD,15));
		b3.setFont(new Font("Arial",Font.BOLD,15));
		b4.setFont(new Font("Arial",Font.BOLD,15));
		b5.setFont(new Font("Arial",Font.BOLD,15));
		b6.setFont(new Font("Arial",Font.BOLD,15));
	}
	public void setComponentsCursor()
	{
		b1.setCursor(Database.cur);
		b2.setCursor(Database.cur);
		b3.setCursor(Database.cur);
		b4.setCursor(Database.cur);
		b5.setCursor(Database.cur);
		b6.setCursor(Database.cur);
		showpass.setCursor(Database.cur);
		r1.setCursor(Database.cur);
		r2.setCursor(Database.cur);
		date.setCursor(Database.cur);
		month.setCursor(Database.cur);
		year.setCursor(Database.cur);
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
		l8.setForeground(Color.WHITE);
		l9.setForeground(Color.WHITE);
		l10.setForeground(Color.WHITE);
		l11.setForeground(Color.WHITE);
		r1.setForeground(Color.WHITE);
		r2.setForeground(Color.WHITE);
		showpass.setForeground(Color.WHITE);
		t1.setForeground(Color.BLACK);
		t2.setForeground(Color.BLACK);
		t3.setForeground(Color.BLACK);
		t4.setForeground(Color.BLACK);
		t5.setForeground(Color.BLACK);
		t6.setForeground(Color.BLACK);
		t7.setForeground(Color.BLACK);
		t8.setForeground(Color.BLACK);
	}
	public void addListeners()
	{
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		year.addActionListener(this);
		month.addActionListener(this);
		showpass.addActionListener(this);
		t8.addKeyListener(this);
		t5.addKeyListener(this);
		b1.addKeyListener(this);
		b2.addKeyListener(this);
		b3.addKeyListener(this);
		b4.addKeyListener(this);
		b5.addKeyListener(this);
		b6.addKeyListener(this);
		showpass.addKeyListener(this);
	}
	public void addPanel()
	{
		Connection con = null;
        PreparedStatement stmt;
        ResultSet rs = null;
        boolean bc = true;
        try
        {
        	con = DriverManager.getConnection(Database.url, Database.uname, Database.pass);
        }
		catch(Exception exc)
        {
			JOptionPane.showMessageDialog(null,"Cannot connect to the server.");
			bc = false;
        }
        if(bc)
        {
        	try 
        	{
        		stmt = con.prepareStatement("SELECT `name`,`gender`,`date`,`month`,`year`,`fname`,`address`,`city`,`contact`,`email`,`username`,`password` FROM `userpass` WHERE `uid` = ? AND `active` = 1 ");
        		stmt.setString(1, Database.sl_uid);
        		rs = stmt.executeQuery();
        		rs.next();
        		t1.setText(rs.getString("name"));				//Name
        		if((rs.getString("gender")).equals("Male"))		//Gender
					r1.setSelected(true);
				else
					r2.setSelected(true);
        		year.setSelectedItem(rs.getString("year"));
				month.setSelectedItem(rs.getString("month"));
				date.setSelectedItem(rs.getString("date"));
				t2.setText(rs.getString("fname"));		//Father's Name
				t3.setText(rs.getString("address"));	//Address
				t4.setText(rs.getString("city"));		//City
				t5.setText(rs.getString("contact"));	//Contact
				t6.setText(rs.getString("email"));		//Email
				t7.setText(rs.getString("username"));	//Username
				t8.setText(rs.getString("password"));	//Password
        	}
        	catch(Exception e)
        	{
        		JOptionPane.showMessageDialog(new UpdateStaff(), e, "Error", JOptionPane.WARNING_MESSAGE);
        	}
        }
		p.add(l1);
		add(p);
		add(l2);
		add(l3);
		add(l4);
		add(l5);
		add(l6);
		add(l7);
		add(l8);
		add(l9);
		add(l10);
		add(l11);
		add(t1);
		add(t2);
		add(t3);
		add(t4);
		add(t5);
		add(t6);
		add(t7);
		add(t8);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(r1);
		add(r2);
		add(date);
		add(month);
		add(year);
		add(showpass);
		repaint();
	}
	public void keyTyped(KeyEvent e) 
    {
		if(e.getSource()==t5)
		 {
			char input = e.getKeyChar();
			cc = t5.getText().length();
			if(cc<=10 && cc>=0)
			{
				cc++;
				if((input<'0' || input>'9') && input!='\b')
				{
					e.consume();
					cc--;
				}
			}
			if(input=='\b')
			{
				cc--;
			}
			if(cc>=11)
			{
				e.consume();
			}
		}
    }
    public void keyReleased(KeyEvent e) 
    {
    	
    }
    public void keyPressed(KeyEvent e) 
    {
    	if(e.getSource()==t8  || e.getSource()==b1)
    	{	
    		if(e.getKeyCode() == KeyEvent.VK_ENTER)
    			b1.doClick();							//Add Staff
    	}
    	else if(e.getSource()==b2)
    	{
    		if(e.getKeyCode() == KeyEvent.VK_ENTER)
    			b2.doClick();							//Back
    	}
    	else if(e.getSource()==b3)
    	{
    		if(e.getKeyCode() == KeyEvent.VK_ENTER)
    			b3.doClick();							//Reset
    	}
    	else if(e.getSource()==showpass)
    	{
    		if(e.getKeyCode() == KeyEvent.VK_ENTER)
    			showpass.doClick();
    	}
    }
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)		//Update Staff
		{
			remove(msg1);
			remove(msg2);
			remove(msg3);
			remove(msg4);
			remove(msg5);
			remove(msg6);
			remove(msg8);
			remove(msg9);
			remove(gen);
			remove(dob);
			repaint();
			count = 9;
			if(t1.getText().equals(""))				//Name
			{
				msg1.setBounds(875,100,100,10);
				msg1.setForeground(Color.RED);
				add(msg1);
				repaint();
			}
			else
			{
				count--;
			}
			if(t2.getText().equals(""))				//Father's Name
			{
				msg2.setBounds(875,255,130,10);
				msg2.setForeground(Color.RED);
				add(msg2);
				repaint();
			}
			else
			{
				count--;
			}
			if((r1.isSelected()==false) && (r2.isSelected()==false))	//Gender
			{
				gen.setBounds(825,155,130,10);
				gen.setForeground(Color.RED);
				add(gen);
				repaint();
			}
			else
			{
				count--;
			}
			if(date.getSelectedItem().equals("--Date--") || month.getSelectedItem().equals("--Month--") || year.getSelectedItem().equals("--Year--"))
			{
				dob.setBounds(970,205,100,10);
				dob.setForeground(Color.RED);
				add(dob);
				repaint();
			}
			else
			{
				count--;
			}
			if(t3.getText().equals(""))				//Address
			{
				msg3.setBounds(875,300,100,10);
				msg3.setForeground(Color.RED);
				add(msg3);
				repaint();
			}
			else
			{
				count--;
			}
			if(t4.getText().equals(""))				//City
			{
				msg4.setBounds(875,350,100,13);
				msg4.setForeground(Color.RED);
				add(msg4);
				repaint();
			}
			else
			{
				count--;
			}
			if(t5.getText().equals(""))				//Contact Number
			{
				msg5.setBounds(875,400,140,13);
				msg5.setForeground(Color.RED);
				add(msg5);
				repaint();
			}
			else
			{
				count--;
			}
			if(t6.getText().equals(""))				//Email
			{
				msg6.setBounds(875,450,140,13);
				msg6.setForeground(Color.RED);
				add(msg6);
				repaint();
			}
			else
			{
				count--;
			}
			if(t8.getText().equals(""))				//Password
			{
				msg8.setBounds(875,550,140,13);
				msg8.setForeground(Color.RED);
				add(msg8);
				repaint();
			}
			else
			{
				count--;
			}
			
			
			if(count==0)
			{
				boolean cv = true;
				if(t5.getText().length()!=10)	//Contact Number Validation
				{
					msg9.setBounds(875,400,170,13);
					msg9.setForeground(Color.RED);
					add(msg9);
					repaint();
					cv = false;
				}
				if(cv==true)
				{
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
						String g = null;
						if(r1.isSelected()==true)
							g = "Male";
						else
							g = "Female";
						try
						{
							stmt = con.prepareStatement("UPDATE `userpass` SET `name` = ?, `gender` = ?, `date` = ?, `month` = ?, `year` = ?, `fname` = ?, `address` = ?, `city` = ?, `contact` = ?, `email` = ?, `password` = ?  WHERE `uid` = ? AND `active` = 1");
							stmt.setString(1, t1.getText());						//Name
							stmt.setString(2, g);
							stmt.setString(3,(String) date.getSelectedItem());
							stmt.setString(4,(String) month.getSelectedItem());
							stmt.setString(5,(String) year.getSelectedItem());
							stmt.setString(6, t2.getText());						//Father's Name
							stmt.setString(7, t3.getText());						//Address
							stmt.setString(8, t4.getText());						//City
							stmt.setString(9, t5.getText());						//Contact
							stmt.setString(10, t6.getText());						//Email
							stmt.setString(11, t8.getText());						//Password
							stmt.setString(12, Database.sl_uid);					//UID
							stmt.executeUpdate();
							JOptionPane.showMessageDialog(null,"Information Updated Successfully");
						}
						catch(Exception exx)
						{
							JOptionPane.showMessageDialog(new UpdateStaff(), exx, "Error", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			}
		}
		else if(e.getSource()==b2)		//Back
		{
			ViewStaff.main(new String[] {});
			((Window) getRootPane().getParent()).dispose();			//To close login frame inside JPanel class
		}
		else if(e.getSource()==b3)		//Reset
		{
			reset();
		}
		else if(e.getSource()==b4)		//Change Username
		{
			temp_username = t7.getText();
			t7.setEditable(true);
			t1.setEnabled(false);
			t2.setEnabled(false);
			t3.setEnabled(false);
			t4.setEnabled(false);
			t5.setEnabled(false);
			t6.setEnabled(false);
			t8.setEnabled(false);
			showpass.setEnabled(false);
			date.setEnabled(false);
			month.setEnabled(false);
			year.setEnabled(false);
			r1.setEnabled(false);
			r2.setEnabled(false);
			b1.setEnabled(false);
			b2.setEnabled(false);
			b3.setEnabled(false);
			remove(b4);
			add(b5);		//Submit
			add(b6);		//Cancel
			repaint();
		}
		else if(e.getSource()==b5)		//Submit
		{
			Connection con = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			boolean temp = true;
			try
			{
				con = DriverManager.getConnection(Database.url, Database.uname, Database.pass);
			}
			catch(Exception exc)
			{
				JOptionPane.showMessageDialog(null,"Cannot connect to the server.");
				temp = false;
			}
			if(temp==true)
			{
				try
				{
					stmt = con.prepareStatement("SELECT `uid` FROM `userpass` WHERE `username`=? AND `active`=1");
					stmt.setString(1, t7.getText());
					rs = stmt.executeQuery();
					if(rs.next())
            		{
						if(t7.getText().equalsIgnoreCase("admin"))
						{
							JOptionPane.showMessageDialog(null,"Username reserved.\nChange Username");
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Username already exists.\nChange Username");
						}
            		}
					else
					{
						try
						{
							stmt = con.prepareStatement("UPDATE `userpass` SET `username` = ? WHERE `uid` = ? AND `active`=1");
							stmt.setString(1, t7.getText());
							stmt.setString(2, Database.sl_uid);
							stmt.executeUpdate();
							JOptionPane.showMessageDialog(null,"Username Updated Successfully");
							temp_username = t7.getText();
						}
						catch(Exception ex)
						{
							JOptionPane.showMessageDialog(new UpdateStaff(), ex, "Error", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
				catch(Exception exp)
				{
					JOptionPane.showMessageDialog(new UpdateStaff(), exp, "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		else if(e.getSource()==b6)		//Cancel
		{
			t7.setText(temp_username);
			t7.setEditable(false);
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			t5.setEnabled(true);
			t6.setEnabled(true);
			t8.setEnabled(true);
			showpass.setEnabled(true);
			date.setEnabled(true);
			month.setEnabled(true);
			year.setEnabled(true);
			r1.setEnabled(true);
			r2.setEnabled(true);
			b1.setEnabled(true);
			b2.setEnabled(true);
			b3.setEnabled(true);
			add(b4);
			remove(b5);		//Submit
			remove(b6);		//Cancel
			repaint();
		}
		else if(e.getSource()==showpass)
		{
			if(showpass.isSelected())
			{
                t8.setEchoChar((char) 0);
            } 
			else 
			{
                t8.setEchoChar('*');
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
		count = 10;
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
		month.setEnabled(false);
		date.setEnabled(false);
		r1.setSelected(false);
		r2.setSelected(false);
		t1.setText("");
		t2.setText("");
		t3.setText("");
		t4.setText("");
		t5.setText("");
		t6.setText("");
		t8.setText("");
		if(showpass.isSelected())
        	showpass.doClick();
		gender.clearSelection();
		
		remove(msg1);
		remove(msg2);
		remove(msg3);
		remove(msg4);
		remove(msg5);
		remove(msg6);
		
		remove(msg8);
		remove(msg9);
		remove(gen);
		remove(dob);
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
		        f.add(new UpdateStaff());
			}
		});
	}
}