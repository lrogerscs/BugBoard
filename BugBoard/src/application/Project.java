package application;

import java.util.Date;
import java.util.List;

public class Project 
{
	private String name;
	private Date startDate;	//the Date type may be useful, but may be better simply as a String or int
	private String description;
	private List<Ticket> tickets;	//list of ticket objects
	private boolean empty;	//denotes if a project has tickets or not
	
	private String getName() 
	{
		return name;
	}
	
	private void setName(String name) 
	{
		this.name = name;
	}
	
	private String getDescription()
	{
		return description;
	}
	
	private void setDescription(String description)
	{
		this.description = description;
	}
	
	private List<Ticket> getTickets()
	{
		return tickets;
	}
	
	private void setTickets()
	{
		//Needs logic to add and remove ticket objects from the List tickets
	}
	
	private boolean getEmpty()
	{
		return empty;
	}
	
	private void setEmpty(boolean empty)
	{
		this.empty = empty;
	}
}
