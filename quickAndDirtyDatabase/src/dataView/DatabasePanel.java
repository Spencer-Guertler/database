package dataView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import dataController.AppController;
import dataModel.Person;

public class DatabasePanel extends JPanel
{

	private AppController baseController;
	private SpringLayout baseLayout;
	private JButton createDatabasebutton;
	private JButton createPeopleTablebutton;
	private JButton insertPersonButton;
	private JTextField ageField;
	private JTextField nameField;
	private JTextField deathDateField;
	private JTextField birthDateField;
	private JTextField textField5;
	private JTextArea textArea;

	public DatabasePanel(AppController baseController)
	{
		this.baseController = baseController;
		
		baseLayout = new SpringLayout();
		createDatabasebutton = new JButton("Create Database");	
		createPeopleTablebutton = new JButton("Create People");	
		ageField = new JTextField(5);
		nameField = new JTextField(30);				
		deathDateField = new JTextField(15);		
		birthDateField = new JTextField(15);
	
		
		
		setupPanel();
		setupLayout();
		setupListeners();
		
		
	}

	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.add(createDatabasebutton);
		this.add(createPeopleTablebutton);

		this.add(ageField);
		this.add(nameField);
		this.add(deathDateField);
		this.add(birthDateField);

	}

	private boolean checkInteger(String current)
	{
		boolean isInteger = false;
		
		try
		{
			Integer.parseInt(current);
			isInteger = true;
		}
		catch(NumberFormatException currentException)
		{
			JOptionPane.showMessageDialog(this, "Make sure you typed in a number for the age ;)");
		}
		
		return isInteger;
	}
	
	private Person createPersonFromInput()
	{
		Person deadPerson = null;
		
		int deadPersonAge = 0;
		
		if(checkInteger(ageField.getText()))
		{
			deadPersonAge = Integer.parseInt(ageField.getText());
		}
		String name = nameField.getText();
		String deathDate = deathDateField.getText();
		
		deadPerson = new Person(name, deathDate);
		deadPerson.setAge(deadPersonAge);
		//other .sets as needed
		
		return deadPerson;
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.WEST, createDatabasebutton, 0, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, createDatabasebutton, -40, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, createPeopleTablebutton, 0, SpringLayout.WEST, createDatabasebutton);
		baseLayout.putConstraint(SpringLayout.SOUTH, createPeopleTablebutton, -19, SpringLayout.NORTH, createDatabasebutton);
		baseLayout.putConstraint(SpringLayout.WEST, nameField, 0, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, nameField, -23, SpringLayout.NORTH, createPeopleTablebutton);
		baseLayout.putConstraint(SpringLayout.WEST, deathDateField, 0, SpringLayout.WEST, createDatabasebutton);
		baseLayout.putConstraint(SpringLayout.SOUTH, deathDateField, -52, SpringLayout.NORTH, nameField);
		baseLayout.putConstraint(SpringLayout.SOUTH, birthDateField, -6, SpringLayout.NORTH, deathDateField);
		baseLayout.putConstraint(SpringLayout.EAST, birthDateField, 0, SpringLayout.EAST, deathDateField);
	}

	private void setupListeners()
	{
		createDatabasebutton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				baseController.getMyDataController().createDatabase();
			}
		});
		
		createPeopleTablebutton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				baseController.getMyDataController().createPeopleTable("Graveyard");
			}
		});
		
		insertPersonButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				Person currentPerson = new Person();
				currentPerson.setName(nameField.getText());
				currentPerson.setDeathDate(deathDateField.getText());
				currentPerson.setAge(Integer.parseInt(ageField.getText()));
				
				baseController.getMyGraveyardPeople().add(currentPerson);
				baseController.getMyDataController().insertPersonIntoDataBase(currentPerson);
			}
		});
	}

}
