package dataController;

import java.sql.*;

import javax.swing.JOptionPane;

import dataModel.Person;
/**
 * 
 * @author Spencer Guertler
 *
 */
public class DBController
{
	private String connectionString;
	private Connection databaseConnection;
	
	public DBController()
	{
		connectionString = "jdbc:mysql://localhost/morning_starter?user=root";

		checkDriver();
		setupConnection();
	}
	/**
	 * closes the connection to the database to avoid corruption 
	 */
	public void closeConnection()
	{
		try
		{
			databaseConnection.close();
		}
		catch (SQLException sqlError)
		{
			displaySQLErrors(sqlError);
		}
	}
	/**
	 *checks to see that the driver is loaded 
	 */
	private void checkDriver()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (Exception currentException)
		{
			System.err.println("Check that the driver is loaded");
			System.exit(1);
		}
	}
	/**
	 * Sets up the connection to the database
	 */
	public void setupConnection()
	{
		try
		{
			databaseConnection = DriverManager.getConnection(connectionString);
		}
		catch (SQLException sqlError)
		{
			displaySQLErrors(sqlError);
		}

	}
	
	public void createDatabase()
	{
		closeConnection();
		try
		{
			Statement createDatabaseStatement = databaseConnection.createStatement();
		}
		catch(SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}
	
	/**
	 * creates a table in the database
	 * @param database the database that the table is being put in
	 * @param tableName the name for the table
	 */
	public void createTable(String database, String tableName)
	{
		closeConnection();
		int queryIndex = connectionString.indexOf("?");
		String connectionStart = connectionString.substring(0, queryIndex);
		String connectionEnd = connectionString.substring(queryIndex);
		connectionString = connectionStart + database + connectionEnd;
		
		setupConnection();
		
		try
		{
			Statement createTableStatement = databaseConnection.createStatement();
			String mySQLStatement = "CREATE TABLE `"+ database +"`.`"+ tableName +"` (`test_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ," +
					 													"`test_name` VARCHAR(50)NOT NULL)"+
					 													"ENGINE = INNODB;";
		}
		catch(SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}
	
	public void deleteDatabase()
	{
		closeConnection();
		try
		{
			Statement deleteDatabaseStatement = databaseConnection.createStatement();
			
			int result = deleteDatabaseStatement.executeUpdate("DROP DATABASE GRAVEYARD;");
			deleteDatabaseStatement.close();
		}
		catch(SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}
	
	/**
	 * Displays the errors if they happen
	 * @param currentException the exception that happened
	 */
	public void displaySQLErrors(SQLException currentException)
	{
		JOptionPane.showMessageDialog(null, "the SQLException is: " + currentException.getMessage());
		JOptionPane.showMessageDialog(null, "the SQL state is: " + currentException.getSQLState());
		JOptionPane.showMessageDialog(null, "The SQL error code is" + currentException.getErrorCode());
	}
	
	/**
	 * creates a person table on the supplied database. Onley creates the table if it does not exsist. 
	 * Defines table structure to have an ID, name, birth, death, children, marital status, and age. 
	 * If there is a problem it will call the displaySQLErrors method
	 * @param database The database to build the table on
	 */
	public void createPeopleTable(String database)
	{
		closeConnection();
		int queryIndex = connectionString.indexOf("?");
		String connectionStart = connectionString.substring(0, queryIndex);
		String connectionEnd = connectionString.substring(queryIndex);
		connectionString = connectionStart + database + connectionEnd;
		
		setupConnection();
		
		
		try
		{
			Statement createTableStatement = databaseConnection.createStatement();
			String createMyPersonTable = "CREATE TABLE IF NOT EXISTS `" + database + "`.`people`" +
											"(" +
												"`person_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
												"`person_name` VARCHAR (50) NOT NULL," +
												"`person_birth_date` VARCHAR (30)," +
												"`person_death_date`VARCHAR (30)," +
												"`person_is_married`BOOL,"+
												"`person_has_chidren`BOOL," +
												"`person_age` INT," +
												") ENGINE = INNODB;";
			
			int result = createTableStatement.executeUpdate(createMyPersonTable);
			createTableStatement.close();
		}
		catch(SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}
	
	
	
	public void insertPersonIntoDataBase(Person currentPerson)
	{
		try
		{
			Statement insertPersonStatement = databaseConnection.createStatement();
			int databaseIsMarried, databaseHasChildren;
			if(currentPerson.isMarried())
			{
				databaseIsMarried = 1;
			}
			else
			{
				databaseIsMarried = 0;
			}
			if(currentPerson.isHasChildren())
			{
				databaseHasChildren = 1;
			}
			else
			{
				databaseHasChildren = 0;
			}
			String insertString = "INSERT INTO `people`" + 
					"( `person_name`, `death_date`, `is_married`, `has_children`, `age`)" +
					"VALUES" + 
					"('" + currentPerson.getName() + "', '" + currentPerson.getDeathDate() + "', '" + currentPerson.getBirthDate() +
					"', ' " + databaseIsMarried + "', '" + databaseHasChildren + "', '" + currentPerson.getAge() +"');";
			
			int result = insertPersonStatement.executeUpdate(insertString);
			JOptionPane.showMessageDialog(null, "Successfully inserted" + result + " rows");
		}
		catch(SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}
	
}
