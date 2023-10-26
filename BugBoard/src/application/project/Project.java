package application.project;

import java.time.LocalDate;
import java.util.List;

import application.ticket.Ticket;


/**
 * Project class stores project name, start date, description, and list of Ticket objects (and an empty boolean if none in the list)
 */
public class Project 
{
	private String name;
	private LocalDate startDate; //using the LocalDate type because the new_project.fxml calendar object returns user input as a LocalDate type
	private String desc;
	private List<Ticket> tickets;	//list of ticket objects
	private boolean empty;	//denotes if a project has tickets or not
	
	public Project(String name, LocalDate startDate, String desc)
	{
	   this.name = name;
	   this.startDate = startDate;
	   this.desc = desc;
	}

	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public LocalDate getDate()
	{
		return startDate;
	}
	
	public void setDate(LocalDate startDate)
	{
		this.startDate = startDate;
	}
	
	public String getDesc()
	{
		return desc;
	}
	
	public void setDesc(String description)
	{
		this.desc = description;
	}
	
	public List<Ticket> getTickets()
	{
		return tickets;
	}
	
	public void setTickets()
	{
		//TODO
		//Needs logic to add and remove ticket objects from the tickets List
	}
	
	public boolean getEmpty()
	{
		return empty;
	}
	
	public void setEmpty(boolean empty)
	{
		this.empty = empty;
	}
}
