package tsk.mgmt;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

public class Database 
{
	static String url     =   "jdbc:mysql://localhost:3306/taskmanagement?useSSL=false";
	static String uname   =   "root";
	static String pass    =   "";	//root123
	static Cursor cur     =   new Cursor(Cursor.HAND_CURSOR);
	
	//For ViewTask.java
	static String login_uid    = null;
	static String sl_uid 	   = null;
	static String sl_tid       = null;
	
	static boolean temp = false;
	
	//For ViewTasks.java
	static String uss      = null;
	static boolean addtask = false;
	static boolean uptask  = false;
	static String ttid     = null;
	
	//For Maximized JFrames
	static int contentpane_width  = 0;
	static int contentpane_height = 0;
	
	//For ChangePassword.java
	static int width  = 0;
	static int height = 0;
	
	//Background Images URL for each JFrame
	static String login 			= "/img/login.jpg";
	static String admin_login 		= "/img/admin.jpg";
	static String staff_login		= "/img/staff.jpg";
	static String add_staff 		= "/img/add.jpg";
	static String update_staff 		= "/img/add.jpg";
	static String add_task 			= "/img/add.jpg";
	static String view_task 		= "/img/add.jpg";
	static String update_task 		= "/img/add.jpg";
	static String update_profile 	= "/img/add.jpg";
	static String change_password 	= "/img/add.jpg";
	static String view_staff 		= "/img/add.jpg";
	static String close 			= "/img/close.jpg";
	
	//For JFrames
	static void setFeel()
	{
		try 
        {
            // Set System L&F
	    	UIManager.put("nimbusBase", new Color(51,98,140));		//Default Value
        	UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } 
        catch (UnsupportedLookAndFeelException e)
		{
        	// handle exception
        }
        catch (ClassNotFoundException e) 
		{
        	// handle exception
        }
        catch (InstantiationException e) 
		{
        	// handle exception
        }
        catch (IllegalAccessException e) 
		{
        	// handle exception
        }
	}
	
	//For JTables
	static void setFeelTable()
	{
		try 
        {
            // Set System L&F	
	    	UIManager.put("nimbusBase", Color.BLUE);		//For changing JTable header colour
        	UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } 
        catch (UnsupportedLookAndFeelException e)
		{
        	// handle exception
        }
        catch (ClassNotFoundException e) 
		{
        	// handle exception
        }
        catch (InstantiationException e) 
		{
        	// handle exception
        }
        catch (IllegalAccessException e) 
		{
        	// handle exception
        }
	}
	
	//For Admin Stats
	static int noOfUsers,taskAlloted,taskCompleted,taskPending;
	
	//For staff Stats
	static int taskReceived,taskCompleted1,taskPending1;
	
	static void adminInformation()			//For admin Stats
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
			JOptionPane.showMessageDialog(null,"Cannot connect to the server.");
			b = false;
        }
        if(b==true)
        {
        	try 
        	{
        		stmt = con.prepareStatement("SELECT COUNT(*) FROM `userpass` WHERE `active`=1");
        		rs = stmt.executeQuery();
        		rs.next();
        		noOfUsers = rs.getInt(1)-1;
        		stmt = con.prepareStatement("SELECT COUNT(*) FROM `taskdetails` WHERE `active`=1");
        		rs = stmt.executeQuery();
        		rs.next();
        		taskAlloted = rs.getInt(1);
        		stmt = con.prepareStatement("SELECT COUNT(*) FROM `taskdetails` WHERE `active`=1 AND `submit`=1");
        		rs = stmt.executeQuery();
        		rs.next();
        		taskCompleted = rs.getInt(1);
        		taskPending = taskAlloted-taskCompleted;
        	}
        	catch(Exception ex) 
        	{
        		JOptionPane.showMessageDialog(new Login(), ex, "Error", JOptionPane.WARNING_MESSAGE);
        	}
        }
	}
	
	static void staffInformation()		//For a particular staff stats
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
			JOptionPane.showMessageDialog(null,"Cannot connect to the server.");
			b = false;
        }
        if(b==true)
        {
        	try 
        	{
        		stmt = con.prepareStatement("SELECT COUNT(*) FROM `taskdetails` WHERE `active`=1 AND `uid`=?");
        		stmt.setString(1, Database.login_uid);
        		rs = stmt.executeQuery();
        		rs.next();
        		taskReceived = rs.getInt(1);
        		stmt = con.prepareStatement("SELECT COUNT(*) FROM `taskdetails` WHERE `active`=1 AND `uid`=? AND `submit`=1");
        		stmt.setString(1, Database.login_uid);
        		rs = stmt.executeQuery();
        		rs.next();
        		taskCompleted1 = rs.getInt(1);
        		taskPending1 = taskReceived-taskCompleted1;
        	}
        	catch(Exception ex) 
        	{
        		JOptionPane.showMessageDialog(new Login(), ex, "Error", JOptionPane.WARNING_MESSAGE);
        	}
        }
	}
}