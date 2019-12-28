package tsk.mgmt;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.*;		
class ViewTasks extends JPanel implements ActionListener, KeyListener
{
    JTable table;
    JTableHeader Theader;
    JScrollPane pane;
    DefaultTableCellRenderer centerRenderer;
	JLabel l1 = new JLabel("VIEW TASKS");
	JLabel l2 = new JLabel("Select username: ");
	JLabel msg1 = new JLabel("Select username!!");
	JLabel msg2 = new JLabel();
	JComboBox user = new JComboBox();
	JButton b1 = new JButton("Back");
	JButton b2 = new JButton("Delete");
	JButton b3 = new JButton("Update");
	JButton b4 = new JButton("Submit");
	JButton b5 = new JButton("Reset");
	JButton b6 = new JButton("Add Task");
	DefaultTableModel model = new DefaultTableModel();
	Object[] columns = {"TID","Task Name","Description","Date","Start Time","End Time","Status"};
	BufferedImage img;
	JPanel p = new JPanel();
	ViewTasks()
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
			img = ImageIO.read(Login.class.getResource(Database.update_task));
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
		model.setColumnIdentifiers(columns);
		table=new JTable()
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
        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setFont(new Font("Arial",Font.BOLD,15));
        table.setRowHeight(30);							//Height of the row
		//Set Column size
		table.getColumnModel().getColumn(0).setPreferredWidth(2);		//TID
		table.getColumnModel().getColumn(1).setPreferredWidth(100);		//Task Name	
		table.getColumnModel().getColumn(2).setPreferredWidth(450);		//Description
		table.getColumnModel().getColumn(3).setPreferredWidth(70);		//Date
		table.getColumnModel().getColumn(4).setPreferredWidth(50);		//Start Time
		table.getColumnModel().getColumn(5).setPreferredWidth(50);		//End Time
		table.getColumnModel().getColumn(5).setPreferredWidth(50);		//Status
		//Change JTable Header Font Name, Color
		Theader = table.getTableHeader();
		//Center allignment of the JTable Header
		((DefaultTableCellRenderer)Theader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER); // center header text
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
		// create JScrollPane
        pane = new JScrollPane(table);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		l1.setSize(800,50);
		l2.setBounds(10,Database.contentpane_height-110,180,20);
		b4.setBounds(370,Database.contentpane_height-115,120,30);
		b5.setBounds(500,Database.contentpane_height-115,120,30);
		msg1.setBounds(200,Database.contentpane_height-80,200,20);
		msg2.setBounds(10,60,200,15);
		pane.setBounds(6,80,1356,500);
		b1.setBounds(560,Database.contentpane_height-50,120,30);		//Back
		b2.setBounds(400,Database.contentpane_height-50,120,30);		//Delete
		b3.setBounds(720,Database.contentpane_height-50,120,30);		//Update
		b6.setBounds(880,Database.contentpane_height-50,120,30);		//Add Task
		b2.setEnabled(false);
		b3.setEnabled(false);
		b6.setEnabled(false);
		user.setBounds(180,Database.contentpane_height-115,180,30);
		user.addItem("--Select username--");
		user.setSelectedIndex(0);
		addUsername();
	}
	public void setComponentsFont()
	{
		l1.setFont(new Font("Arial",Font.BOLD,50));
		l2.setFont(new Font("Arial",Font.BOLD,20));
		b1.setFont(new Font("Arial",Font.BOLD,20));
		b2.setFont(new Font("Arial",Font.BOLD,20));
		b3.setFont(new Font("Arial",Font.BOLD,20));
		b4.setFont(new Font("Arial",Font.BOLD,20));
		b5.setFont(new Font("Arial",Font.BOLD,20));
		b6.setFont(new Font("Arial",Font.BOLD,20));
		//Change JTable Header font
		Theader.setFont(new Font("Tahome",Font.BOLD,15)); // font name style size
		user.setFont(new Font("Arial",Font.BOLD,15));
		msg1.setFont(new Font("Arial",Font.BOLD,15));
		msg2.setFont(new Font("Arial",Font.BOLD,15));
	}
	public void setComponentsColor()
	{
		Theader.setBackground(Color.RED); // change the Background color
        Theader.setForeground(Color.WHITE); // change the Foreground
		b1.setBackground(new Color(214,217,223));
		b2.setBackground(new Color(214,217,223));
		b3.setBackground(new Color(214,217,223));
		b4.setBackground(new Color(214,217,223));
		b5.setBackground(new Color(214,217,223));
		b6.setBackground(new Color(214,217,223));
		l1.setForeground(Color.WHITE);
		l2.setForeground(Color.WHITE);
		user.setBackground(new Color(214,217,223));
		msg1.setForeground(Color.WHITE);
		msg2.setForeground(Color.WHITE);
	}
	public void addListeners()
	{
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b1.addKeyListener(this);
		b2.addKeyListener(this);
		b3.addKeyListener(this);
		b4.addKeyListener(this);
		b5.addKeyListener(this);
		b6.addKeyListener(this);
	}
	public void setComponentsCursor()
	{
		b1.setCursor(Database.cur);
		b2.setCursor(Database.cur);
		b3.setCursor(Database.cur);
		b4.setCursor(Database.cur);
		b5.setCursor(Database.cur);
		b6.setCursor(Database.cur);
		user.setCursor(Database.cur);
		table.setCursor(Database.cur);
	}
	public void addPanel()
	{
		if(Database.addtask)
		{
			user.setSelectedItem(Database.uss);
			b4.doClick();            //Submit
			Database.addtask = false;
			Database.uss = null;
		}
        add(pane);
		p.add(l1);
		add(l2);
		add(user);
		add(p);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		add(b6);
		repaint();
	}
	public void addUsername()
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
				stmt = con.prepareStatement("SELECT `username` FROM `userpass` WHERE `uid` NOT IN('UID100') AND `active`=1 ORDER BY `username`");
				rs = stmt.executeQuery();
				while(rs.next())
				{
					user.addItem(rs.getString(1));
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(new ViewTasks(), e, "Error", JOptionPane.WARNING_MESSAGE);
			}
        }
	}
	public void update_table(String s)
	{
		model.setRowCount(0);
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
				stmt = con.prepareStatement("SELECT `uid` FROM `userpass` WHERE `username` = ? AND `active`=1");
				stmt.setString(1, s);
				rs = stmt.executeQuery();
				rs.next();
				String uid =rs.getString(1);
				stmt = con.prepareStatement("SELECT * FROM `taskdetails` WHERE `uid` = ? AND `active`=1");
				stmt.setString(1, uid);
				rs = stmt.executeQuery();
				int i = 0;
				while(rs.next())
				{
					String tid = rs.getString("tid");
					String tname = rs.getString("taskname");
					String description = rs.getString("description");
					String date = rs.getString("date");
					String month = rs.getString("month");
					String year = rs.getString("year");
					String shour = rs.getString("shour");
					String sminute = rs.getString("sminute");
					String sm = rs.getString("sm");
					String ehour = rs.getString("ehour");
					String eminute = rs.getString("eminute");
					String em = rs.getString("em");
					String st = shour+":"+sminute+" "+sm;
					String et = ehour+":"+eminute+" "+em;
					String dob = date+"-"+month+"-"+year;
					String submit = "Not Submitted";
					if(rs.getInt("submit")==1)
						submit = "Submitted";
					else
						submit = "Not Submitted";
					model.addRow(new Object[]{tid,tname, description, dob, st, et,submit});
					i++;
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(new ViewTasks(), e, "Error", JOptionPane.WARNING_MESSAGE);
			}
        }
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
    	else if(e.getSource()==b5)
    	{
    		if(e.getKeyCode() == KeyEvent.VK_ENTER)
    			b5.doClick();
    	}
    	else if(e.getSource()==b6)
    	{
    		if(e.getKeyCode() == KeyEvent.VK_ENTER)
    			b6.doClick();
    	}
    }
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)		//Back
		{
			AdminLogin.main(new String[] {});
			((Window) getRootPane().getParent()).dispose();			//To close login frame inside JPanel class
		}
		else if(e.getSource()==b2)	//Delete
		{
			int row = 0;
			boolean bf = true;
			String sl_tid = null;
			try	//get selected row
			{
				row = table.getSelectedRow();
				sl_tid = table.getModel().getValueAt(row, 0).toString();		//Here 0 is the 0th column
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
				if(b)
		        {
					try
					{
						stmt = con.prepareStatement("UPDATE `taskdetails` SET `active`=0 WHERE `tid` = ?");
						stmt.setString(1, sl_tid);
						stmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "Task Deleted Successfully");
						model.removeRow(row);
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(new ViewTasks(), ex, "Error", JOptionPane.WARNING_MESSAGE);
					}
		        }
				
			}
		}
		else if(e.getSource()==b3)		//Update
		{
			int row = 0;
			boolean bf = true;
			try	//get selected row
			{
				row = table.getSelectedRow();
				Database.ttid = table.getModel().getValueAt(row, 0).toString();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Select row");
				bf = false;
			}
			if(bf)
			{
				Database.uss = (String) user.getSelectedItem();
				Database.uptask = true;
				AddTasks.main(new String[] {});
				((Window) getRootPane().getParent()).dispose();			//To close login frame inside JPanel class
			}
		}
		else if(e.getSource()==b4)		//Submit
		{
			if(user.getSelectedItem().equals("--Select username--"))
			{
				model.setRowCount(0);
				b2.setEnabled(false);
				b3.setEnabled(false);
				b6.setEnabled(false);
				add(msg1);
				repaint();
			}
			else
			{
				user.setEnabled(false);
				user.setBackground(new Color(214,217,223));
				String s = (String) user.getSelectedItem();
				remove(msg1);
				remove(msg2);
				msg2.setText("Username: "+s);
				add(msg2);
				repaint();
				b2.setEnabled(true);
				b3.setEnabled(true);
				b4.setEnabled(false);
				b6.setEnabled(true);
				update_table(s);
			}
		}
		else if(e.getSource()==b5)		//Reset
		{
			model.setRowCount(0);
			b2.setEnabled(false);
			b3.setEnabled(false);
			b4.setEnabled(true);
			b6.setEnabled(false);
			user.setSelectedIndex(0);
			user.setEnabled(true);
			remove(msg1);
			remove(msg2);
			repaint();
		}
		else if(e.getSource()==b6)		//Add Task
		{
			Database.uss = (String) user.getSelectedItem();
			AddTasks.main(new String[] {});
			((Window) getRootPane().getParent()).dispose();			//To close login frame inside JPanel class
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
		        f.add(new ViewTasks());
			}
		});
	}
}