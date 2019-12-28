package tsk.mgmt;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.*;		
class ViewTask extends JPanel implements ActionListener, KeyListener, MouseListener
{
    JTable table;
    JScrollPane pane;
    JTableHeader Theader;
    DefaultTableCellRenderer centerRenderer;
	JLabel l1=new JLabel("VIEW TASK");
	JButton b1 = new JButton("Back");
	DefaultTableModel model = new DefaultTableModel();
	Object[] columns = {"TID","Task Name","Description","Date","Start Time","End Time","Submit"};
	BufferedImage img;
	JPanel p = new JPanel();
	ViewTask()
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
        // Change JTable Row Height
        table.setRowHeight(30);							//Height of the row
		//Set Column size
		table.getColumnModel().getColumn(0).setPreferredWidth(2);		//Serial Number
		table.getColumnModel().getColumn(1).setPreferredWidth(100);		//Task Name	
		table.getColumnModel().getColumn(2).setPreferredWidth(450);		//Description
		table.getColumnModel().getColumn(3).setPreferredWidth(70);		//Date
		table.getColumnModel().getColumn(4).setPreferredWidth(50);		//Start Time
		table.getColumnModel().getColumn(5).setPreferredWidth(50);		//End Time
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
        pane.setBounds(6,80,1356,500);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		b1.setBounds(Database.contentpane_width/2-90,Database.contentpane_height-80,120,30);		//Back
		l1.setSize(800,50);
	}
	public void setComponentsFont()
	{
		b1.setFont(new Font("Arial",Font.BOLD,20));
		l1.setFont(new Font("Arial",Font.BOLD,50));
		table.setFont(new Font("Arial",Font.BOLD,15));
		Theader.setFont(new Font("Tahome",Font.BOLD,15)); // font name style size
	}
	public void setComponentsColor()
	{
		b1.setBackground(new Color(214,217,223));
		l1.setForeground(Color.WHITE);
		table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        Theader.setBackground(Color.RED); // change the Background color
        Theader.setForeground(Color.WHITE); // change the Foreground
	}
	public void addListeners()
	{
		b1.addActionListener(this);
		b1.addKeyListener(this);
		table.addMouseListener(this);
	}
	public void setComponentsCursor()
	{
		b1.setCursor(Database.cur);
		table.setCursor(Database.cur);
	}
	public void addPanel()
	{
		update_table();
        add(pane);
		p.add(l1);
		add(p);
		add(b1);
		repaint();
	}
	public void update_table()
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
				stmt = con.prepareStatement("SELECT * FROM `taskdetails` WHERE `uid` = ? AND `active`=1");
				stmt.setString(1, Database.login_uid);
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
				JOptionPane.showMessageDialog(new ViewTask(), e, "Error", JOptionPane.WARNING_MESSAGE);
			}
        }
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
    }
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)		//Back
		{
			StaffLogin.main(new String[] {});
			((Window) getRootPane().getParent()).dispose();			//To close login frame inside JPanel class
		}
	}
	public void mouseClicked(MouseEvent e) 
	{
		   if (e.getClickCount() == 2) 
		   {
		      JTable target = (JTable)e.getSource();
		      int row = target.getSelectedRow();
		      String selected_tid = table.getModel().getValueAt(row, 0).toString();
		      String status = table.getModel().getValueAt(row, 6).toString();			//To know status of submission
		      int submit = 0;
		      if(status.equals("Submitted"))	//i.e 1
		    	  submit = 0;	
		      else								//i.e 0
		    	  submit = 1;
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
						stmt = con.prepareStatement("UPDATE `taskdetails` SET `submit`=? WHERE `tid` = ? AND `active`=1");
						stmt.setInt(1, submit);
						stmt.setString(2, selected_tid);
						stmt.executeUpdate();
						if(submit==0)
							JOptionPane.showMessageDialog(null, "Task UnSubmitted Successfully!!");
						else
							JOptionPane.showMessageDialog(null, "Task Submitted Successfully!!");
						//Refresh JTable
						model.setRowCount(0);
						update_table();
		    	  }
		    	  catch(Exception exc)
		    	  {
		    		  JOptionPane.showMessageDialog(new ViewTask(), exc, "Error", JOptionPane.WARNING_MESSAGE);
		    	  }
		      }
		   }
	}
	public void mouseExited(MouseEvent e)
	{
		
	}
	public void mousePressed(MouseEvent e)
	{
		
	}
	public void mouseEntered(MouseEvent e)
	{
		
	}
	public void mouseReleased(MouseEvent e)
	{
		
	}
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				JFrame f = new JFrame();
				f.setExtendedState(f.MAXIMIZED_BOTH);
				f.setVisible(true);
				f.setTitle("STAFF SECTION");
		        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		        f.add(new ViewTask());
			}
		});
	}
}