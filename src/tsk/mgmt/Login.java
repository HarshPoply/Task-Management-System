package tsk.mgmt;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.*;
public class Login extends JPanel implements ActionListener, KeyListener, MouseListener
{
	JLabel l1 = new JLabel("USERNAME");
    JLabel l2 = new JLabel("PASSWORD");
    JLabel team = new JLabel("<html><u>TEAM</u></html>");
	JLabel l3 = new JLabel("TASK MANAGEMENT SYSTEM");
	JLabel close;
	JLabel c = new JLabel("<html><u>TEAM</u></html>");
	JLabel info = new JLabel("<html><u>Login Module</u></html>");
	JLabel info1 = new JLabel(": Gursewak Singh");
	JLabel info2 = new JLabel("Krishan Chandra Kumar Gautum");
	JLabel info3 = new JLabel("<html><u>Admin Module</u></html>");
	JLabel info4 = new JLabel(": Anshu Soni");
	JLabel info5 = new JLabel("Harsh Poplay (Team Leader)");
	JLabel info6 = new JLabel("<html><u>Staff Module</u></html>");
	JLabel info7 = new JLabel(": Geeta Kumari");
	JLabel info8 = new JLabel("Jasmeen Kaur");
    JTextField t1 = new JTextField();
    JPasswordField t2 = new JPasswordField();
    JButton b1 = new JButton("LOGIN");
    JButton b2 = new JButton("RESET");
    JCheckBox check1 = new JCheckBox("Show Password");
	Font font=new Font("Arial",Font.BOLD,15);
	Font font1=new Font("Arial",Font.BOLD,25);
	int count = 2;
	boolean b = true;
	JLabel msg1 = new JLabel("* Enter Username");
	JLabel msg2 = new JLabel("* Enter Password");
	BufferedImage img;
	ImageIcon im;
	JPanel p = new JPanel();		//Task Management System JLabel
	JPanel p1 = new JPanel();		//Team JLabel
	JPanel p2 = new JPanel();		//Team Info
	Login()
	{
		setSize(Database.contentpane_width,Database.contentpane_height);
		setVisible(true);
		setLayout(null);
		p.setLayout(new FlowLayout(FlowLayout.CENTER));
		p.setSize(Database.contentpane_width,60);
		p.setOpaque(false);		//To make transparent JPanel
		
		p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		p1.setBounds((Database.contentpane_width-70),(Database.contentpane_height-30),70,30);
		p1.setOpaque(false);
		
		p2.setLayout(null);
		p2.setSize(500,250);
		p2.setLocation(50,Database.contentpane_height-270);
		
		im = new ImageIcon(getClass().getResource("/img/close.jpg"));
		close = new JLabel(im);
		close.setBounds(470,-2,30,30);
		
		c.setBounds(190,0,200,30);
		c.setFont(new Font("Arial",Font.BOLD,30));
		
		//Login Module
		info.setBounds(10,50,500,23);
		info.setFont(new Font("Arial",Font.BOLD,20));
		
		//: Gursewak Singh
		info1.setBounds(155,50,500,23);
		info1.setFont(new Font("Arial",Font.BOLD,20));
		
		//Krishan Chandra Kumar Gautum
		info2.setBounds(170,80,500,23);
		info2.setFont(new Font("Arial",Font.BOLD,20));
		
		//Admin Module
		info3.setBounds(10,110,500,23);
		info3.setFont(new Font("Arial",Font.BOLD,20));
		
		//: Anshu Soni
		info4.setBounds(155,110,500,23);
		info4.setFont(new Font("Arial",Font.BOLD,20));
		
		//Jasmeen
		info5.setBounds(170,140,500,23);
		info5.setFont(new Font("Arial",Font.BOLD,20));
		
		//Staff Module
		info6.setBounds(10,170,500,23);
		info6.setFont(new Font("Arial",Font.BOLD,20));
		
		//: Geeta Kumari
		info7.setBounds(155,170,500,23);
		info7.setFont(new Font("Arial",Font.BOLD,20));
		
		//Harsh Poplay
		info8.setBounds(170,200,500,23);
		info8.setFont(new Font("Arial",Font.BOLD,20));
		loadImage();
		Database.setFeel();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
	}
	public void loadImage()
	{
		try 
		{
			img = ImageIO.read(Login.class.getResource(Database.login));
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
        l1.setBounds(170,245,150,25);
        l2.setBounds(170,295,150,20);
        l3.setSize(600,40);				//TASK MANAGEMENT SYSTEM
        t1.setBounds(350,240,200,30);
        t2.setBounds(350,290,200,30);
        check1.setBounds(350,325,150,30);
        b1.setBounds(260,370,100,30);
        b2.setBounds(380,370,100,30);
        team.setForeground(Color.YELLOW);
		t1.setFont(font);
		t2.setFont(font);
		l1.setFont(font1);
		l2.setFont(font1);
		l1.setForeground(Color.WHITE);
		l2.setForeground(Color.WHITE);
		b1.setFont(font);
		b2.setFont(font);
		b1.setCursor(Database.cur);
		b2.setCursor(Database.cur);
		team.setCursor(Database.cur);
		close.setCursor(Database.cur);
		check1.setCursor(Database.cur);
		l3.setFont(new Font("Arial",Font.BOLD,50));
		l3.setForeground(Color.WHITE);
		check1.setFont(font);
		check1.setForeground(Color.WHITE);
		t1.setForeground(Color.BLACK);
		t2.setForeground(Color.BLACK);
    }
    public void addComponentsToContainer() 
	{
        add(l1);
        add(l2);
        add(t1);
        add(t2);
        add(check1);
        add(b1);
        add(b2);
		p.add(l3); 
		add(p);
		p1.add(team);
		p2.add(close);
		p2.add(c);
		p2.add(info);
		p2.add(info1);
		p2.add(info2);
		p2.add(info3);
		p2.add(info4);
		p2.add(info5);
		p2.add(info6);
		p2.add(info7);
		p2.add(info8);
		add(p1);
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
    	if(e.getSource()==b2)
    	{	
    		if(e.getKeyCode() == KeyEvent.VK_ENTER)
    			b2.doClick();
    	}
    	else if(e.getSource()==b1 || e.getSource()==t2)
    	{
    		if(e.getKeyCode() == KeyEvent.VK_ENTER)
    			b1.doClick();
    	}
    	else if(e.getSource()==check1)
    	{
    		if(e.getKeyCode() == KeyEvent.VK_ENTER)
    			check1.doClick();
    	}
    }
    public void addActionEvent() 
	{
        b1.addActionListener(this);
        b2.addActionListener(this);
        check1.addActionListener(this);
        t2.addKeyListener(this);
		b1.addKeyListener(this);
		b2.addKeyListener(this);
		check1.addKeyListener(this);
        team.addMouseListener(this);
        close.addMouseListener(this);
    }
    public void mouseEntered(MouseEvent e)
	{
    	if(e.getSource()==team)
    	{
    		team.setForeground(Color.RED);
    		repaint();
    	}
	}
	public void mouseExited(MouseEvent e)
	{
		if(e.getSource()==team)
    	{
    		team.setForeground(Color.YELLOW);
    		repaint();
    	}
	}
	public void mousePressed(MouseEvent e)
	{
		
	}
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource()==team)
		{
			add(p2);
			repaint();
		}
		else if(e.getSource()==close)
		{
			remove(p2);
			repaint();			
		}
		
	}
	public void mouseReleased(MouseEvent e)
	{
		
	}
    public void actionPerformed(ActionEvent e)
    {
    	if(e.getSource()==b1) 		//LOGIN
		{
        	count = 2;
        	remove(msg1);
			remove(msg2);
			repaint();
        	if(t1.getText().equals(""))
        	{
        		msg1.setBounds(553,245,100,10);
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
        		msg2.setBounds(553,300,100,10);
				msg2.setForeground(Color.RED);
				add(msg2);
				repaint();
        	}
        	else
			{
				count--;
			}
        	
        	if(count==0)
        	{
        		remove(msg1);
    			remove(msg2);
    			repaint();
        		String userText = t1.getText();
                String pwdText = t2.getText();
                Connection con = null;
                PreparedStatement stmt;
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
                		stmt = con.prepareStatement("SELECT `uid` FROM `userpass` WHERE BINARY `username`=? AND BINARY `password`=? AND `active`=1");
                		stmt.setString(1, t1.getText());
                		stmt.setString(2, t2.getText());
                		rs = stmt.executeQuery();
                		if(rs.next())
                		{
                			Database.login_uid = rs.getString(1);
                			JOptionPane.showMessageDialog(null,"Login Successful");
                			if(Database.login_uid.equals("UID100"))
                			{
                				
                				AdminLogin.main(new String[] {});
                				((Window) getRootPane().getParent()).dispose();			//To close login frame inside JPanel class
                			}
                			else
                			{
                				StaffLogin.main(new String[] {});
                				((Window) getRootPane().getParent()).dispose();			//To close login frame inside JPanel class
                			}
                		}
                		else
                		{
                			JOptionPane.showMessageDialog(null,"Invalid Username or Password");
                		}
                	}
                	catch(Exception ex) 
                	{
                		JOptionPane.showMessageDialog(new Login(), ex, "Error", JOptionPane.WARNING_MESSAGE);
                	}
                }
        	} 
        }
        else if(e.getSource()==b2) 	//Reset
		{
            t1.setText("");
            t2.setText("");
            if(check1.isSelected())
            	check1.doClick();
            count = 2;
            remove(msg1);
			remove(msg2);
			repaint();
        }
        else if(e.getSource()==check1) 
		{
            if(check1.isSelected())
			{
                t2.setEchoChar((char) 0);
            } 
			else 
			{
                t2.setEchoChar('*');
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
				f.setTitle("TASK MANAGEMENT");
		        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		        f.add(new Login());
			}
		});
	}
}