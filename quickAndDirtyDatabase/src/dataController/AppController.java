package dataController;

import java.util.ArrayList;

import dataView.DatabaseFrame;
import dataModel.GraveMarker;
import dataModel.Person;

public class AppController
{
	private DBController myDataController;
	private DatabaseFrame myAppFrame;
	private ArrayList<GraveMarker> myGraveMarkers;
	private ArrayList<Person> myGraveyardPeople;
	
	public AppController()
	{
		myDataController = new DBController();
		myGraveMarkers = new ArrayList<GraveMarker>();
		myGraveyardPeople = new ArrayList<Person>();
	}
	
	public void start()
	{
		myAppFrame = new DatabaseFrame(this);
	}

	public DBController getMyDataController()
	{
		return myDataController;
	}

	public void setMyDataController(DBController myDataController)
	{
		this.myDataController = myDataController;
	}

	public DatabaseFrame getMyAppFrame()
	{
		return myAppFrame;
	}

	public void setMyAppFrame(DatabaseFrame myAppFrame)
	{
		this.myAppFrame = myAppFrame;
	}

	public ArrayList<GraveMarker> getMyGraveMarkers()
	{
		return myGraveMarkers;
	}

	public void setMyGraveMarkers(ArrayList<GraveMarker> myGraveMarkers)
	{
		this.myGraveMarkers = myGraveMarkers;
	}

	public ArrayList<Person> getMyGraveyardPeople()
	{
		return myGraveyardPeople;
	}

	public void setMyGraveyardPeople(ArrayList<Person> myGraveyardPeople)
	{
		this.myGraveyardPeople = myGraveyardPeople;
	}
}
