package application.project;

import java.time.LocalDate;
import java.util.List;

import application.ticket.Ticket;

public class Project 
{
	private String name;
	private LocalDate startDate; //using the LocalDate type because the new_project.fxml calendar object returns user input as a LocalDate type
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
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public List<Ticket> getTickets()
	{
		return tickets;
	}
	
	public void setTickets()
	{
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