package dataView;

import javax.swing.JFrame;

import dataController.AppController;

public class DatabaseFrame extends JFrame
{
	private DatabasePanel myDatabasePanel;
	public DatabaseFrame(AppController baseController)
	{
		myDatabasePanel = new DatabasePanel(baseController);
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setContentPane(myDatabasePanel);
		this.setSize(400, 400);
		this.setVisible(true);
	}
	
}
