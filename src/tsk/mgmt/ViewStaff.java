package tsk.mgmt;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.*;		
import java.sql.*;
class ViewStaff extends JPanel implements ActionListener, KeyListener
{
    JTable table;
    JScrollPane pane;
    DefaultTableModel model = new DefaultTableModel();
    DefaultTableCellRenderer centerRenderer;
    JTableHeader header = new JTableHeader();
	JLabel l1=new JLabel("VIEW STAFF");
	JButton b1 = new JButton("Back");
	JButton b2 = new JButton("Update");
	JButton b3 = new JButton("Add Staff");
	JButton b4 = new JButton("Delete Staff");
	Object[] columns = {"UID","Name","Gender","DOB","Father's Name","Address","City","Contact Number","Email","Username","Password"};
	//Rows data
	String uid ="";
	String name = "";
	String fname = "";
	String gender = "";
	String date = "";
	String month = "";
	String year = "";
	String dob = "";
	String address = "";
	String city = "";
	String cnumber = "";
	String email = "";
	String username = "";
	String password = "";
	//Background image
	BufferedImage img;
	JPanel p = new JPanel();
	ViewStaff()
	{
		Database.setFeelTable();
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
			img = ImageIO.read(Login.class.getResource(Database.view_staff));
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
		table = new JTable()
		{
			public boolean isCellEditable(int data,int columns)
			{
				return false;
			}
			
			//To multicolor the rows
			public Component prepareRenderer(TableCellRenderer r,int data, int columns)
			{
				Component co= super.prepareRenderer(r,data,columns);		//The variable c has got the value of the cells of the table
				if(data%2==0)
					co.setBackground(Color.WHITE);
				else
					co.setBackground(Color.LIGHT_GRAY);
				
				if(isCellSelected(data,columns))
					co.setBackground(Color.RED);
				
				return co;
			}
		};
		//To show lines in betwwen the columns
		table.setShowGrid(true);
		//Set Default JTable model
		table.setModel(model);
		model.setColumnIdentifiers(columns);
		//Height of the row
        table.setRowHeight(30);							
		//Set Column size
		table.getColumnModel().getColumn(0).setPreferredWidth(80);		//Serial Number
		table.getColumnModel().getColumn(1).setPreferredWidth(180);		//Name	
		table.getColumnModel().getColumn(2).setPreferredWidth(70);		//Gender
		table.getColumnModel().getColumn(3).setPreferredWidth(170);		//DOB	
		table.getColumnModel().getColumn(4).setPreferredWidth(180);		//Father's Name
		table.getColumnModel().getColumn(5).setPreferredWidth(200);		//Address
		table.getColumnModel().getColumn(6).setPreferredWidth(100);		//City
		table.getColumnModel().getColumn(7).setPreferredWidth(140);		//Contact Number
		table.getColumnModel().getColumn(8).setPreferredWidth(150);		//Email
		table.getColumnModel().getColumn(9).setPreferredWidth(110);		//Username
		table.getColumnModel().getColumn(10).setPreferredWidth(110);	//Password
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		//Change JTable Header Font Name, Color
		header = table.getTableHeader();
		//Center alignment of the JTable Header
		((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER); // center header text
		//Center alignment of the cell values
		centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(6).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(7).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(8).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(9).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(10).setCellRenderer( centerRenderer );
		// create JScrollPane
        pane = new JScrollPane(table);
        pane.setBounds(6,80,1356,500);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		l1.setSize(800,50);
		b2.setBounds(400,Database.contentpane_height-70,120,30);		//Update
		b1.setBounds(550,Database.contentpane_height-70,120,30);		//Back
		b3.setBounds(700,Database.contentpane_height-70,140,30);		//Add Staff
		b4.setBounds(870,Database.contentpane_height-70,140,30);		//Delete Staff
	}
	public void setComponentsFont()
	{
		l1.setFont(new Font("Arial",Font.BOLD,50));
		b1.setFont(new Font("Arial",Font.BOLD,20));
		b2.setFont(new Font("Arial",Font.BOLD,20));
		b3.setFont(new Font("Arial",Font.BOLD,20));
		b4.setFont(new Font("Arial",Font.BOLD,20));
		table.setFont(new Font("Arial",Font.BOLD,15));
		header.setFont(new Font("Tahome",Font.BOLD,15)); // font name style size
	}
	public void setComponentsColor()
	{
		l1.setForeground(Color.WHITE);
		b2.setBackground(new Color(214,217,223));
		b1.setBackground(new Color(214,217,223));
		b3.setBackground(new Color(214,217,223));
		b4.setBackground(new Color(214,217,223));
        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        header.setBackground(Color.RED); // change the Background color
        header.setForeground(Color.WHITE); // change the Foreground
	}
	public void addListeners()
	{
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
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
		table.setCursor(Database.cur);
	}
	public void addPanel()
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
		if(b)
        {
			try
			{
				stmt = con.prepareStatement("SELECT * FROM `userpass` WHERE `uid` NOT IN('UID100') AND `active` = 1");
				rs = stmt.executeQuery();
				int i = 0;
				while(rs.next())
				{
				uid      = rs.getString("uid");
				name     = rs.getString("name");
				gender 	 = rs.getString("gender");
				date 	 = rs.getString("date");
				month	 = rs.getString("month"); 
				year 	 = rs.getString("year");
				dob 	 = date+"-"+month+"-"+year;
				fname 	 = rs.getString("fname");
				address  = rs.getString("address");
				city  	 = rs.getString("city");
				cnumber  = rs.getString("contact");
				email 	 = rs.getString("email");
				username = rs.getString("username");
				password = rs.getString("password");
				
				model.addRow(new Object[]{uid,name, gender, dob, fname, address, city, cnumber, email, username, password});
				i++; 
				}
				
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(new ViewStaff(), e, "Error", JOptionPane.WARNING_MESSAGE);
			}
        }
        add(pane);
		p.add(l1);
		add(p);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
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
		if(e.getSource()==b1)				//Back
		{	
			AdminLogin.main(new String[] {});
			((Window) getRootPane().getParent()).dispose();			//To close login frame inside JPanel class
		}
		else if(e.getSource()==b2)				//Update
		{
			int row;
			boolean bf = true;
			try	//get selected row
			{
				row = table.getSelectedRow();
				Database.sl_uid = table.getModel().getValueAt(row, 0).toString();		//Here 0 is the 1st column
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Select row");
				bf = false;
			}
			if(bf)
			{
				UpdateStaff.main(new String[] {});
				((Window) getRootPane().getParent()).dispose();			//To close login frame inside JPanel class
			}
			
		}
		else if(e.getSource()==b3)				//Add Staff
		{
			Database.temp = true;
			AddStaff.main(new String[] {});
			((Window) getRootPane().getParent()).dispose();			//To close login frame inside JPanel class
		}
		else if(e.getSource()==b4)				//Delete Staff
		{
			int row = 0;
			String uid = null;
			String uss = null;
			boolean bf = true;
			try	//get selected row
			{
				row = table.getSelectedRow();
				uid = table.getModel().getValueAt(row, 0).toString();		//Here 0 is the 1st column
				uss = table.getModel().getValueAt(row, 9).toString();		//Here 9 is the 10th column
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Select row");
				bf = false;
			}
			if(bf)
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
						stmt = con.prepareStatement("UPDATE `userpass` SET `active` = 0  WHERE `uid` = ? AND `active` = 1");
						stmt.setString(1, uid);
						stmt.executeUpdate();
						stmt = con.prepareStatement("UPDATE `taskdetails` SET `active` = 0  WHERE `uid` = ? AND `active` = 1");
						stmt.setString(1, uid);
						stmt.executeUpdate();
						JOptionPane.showMessageDialog(null,"Username: "+uss+" deleted successfully.\nAll task information related to this user are also deleted.");
						model.removeRow(row);
					}
					catch(Exception exc)
					{
						JOptionPane.showMessageDialog(new ViewStaff(), exc, "Error", JOptionPane.WARNING_MESSAGE);
					}
				}
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
		        f.add(new ViewStaff());
			}
		});
	}
}