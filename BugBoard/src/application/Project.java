package application;

import java.time.LocalDate;
import java.util.List;

public class Project 
{
	private String name;
	private LocalDate startDate;	//the Date type may be useful, but may be better simply as a String or int
	private String description;
	private List<Ticket> tickets;	//list of ticket objects
	private boolean empty;	//denotes if a project has tickets or not
	
	public Project(String name, LocalDate startDate, String description)
	{
		//May be better to directly assign the instance variable values as opposed to using the getters/setters?
		this.setName(name);
		this.setDate(startDate);
		this.setDescription(description);
	}

	protected String getName() 
	{
		return name;
	}
	
	private void setName(String name) 
	{
		this.name = name;
	}
	
	protected LocalDate getDate()
	{
		return startDate;
	}
	
	private void setDate(LocalDate startDate)
	{
		this.startDate = startDate;
	}
	
	protected String getDescription()
	{
		return description;
	}
	
	private void setDescription(String description)
	{
		this.description = description;
	}
	
	protected List<Ticket> getTickets()
	{
		return tickets;
	}
	
	private void setTickets()
	{
		//Needs logic to add and remove ticket objects from the tickets List
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
