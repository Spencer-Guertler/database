package dataController;

import java.sql.*;

import javax.swing.JOptionPane;

public class DataController 
{
	private String connectionString;
	private Connection dataConnection;
	
	public DataController()
	{
		connectionString = "jdbc:mysql://localhost/morning_starter?user=root";
		
		checkDriver();
		setupConnection();
		displaySQLErrors(null);
	}
	
	private void checkDriver()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (Exception currentException)
		{
			System.err.println("Unable to load the driver");
			System.err.println("check that the connector .jar file is loaded as an external JAR in the build path");
			System.err.println("the original .jar should be in the C:/MySQL/ folder");
			System.exit(1);
		}
	}
	
	public void setupConnection()
	{
		try
		{
			dataConnection = DriverManager.getConnection(connectionString);
		}
		catch(SQLException currentException)
		{
			displaySQLErrors(currentException);
		}
	}
	
	public void displaySQLErrors(SQLException current)
		{
			JOptionPane.showMessageDialog(null, "SQL Message is: " + current.getMessage());
			JOptionPane.showMessageDialog(null, "SQL State is: " + current.getSQLState());
			JOptionPane.showMessageDialog(null, "Java error code is: " + current.getErrorCode());
		}
}
