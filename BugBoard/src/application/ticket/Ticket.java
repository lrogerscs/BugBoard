package application.ticket;
import java.util.List;

import application.comment.Comment;

/**
 * Ticket class stores bug information pertaining to a given project including a title, description, and any comments
 */
public class Ticket 
{
	private String projectName;
	private String title;
	private String description;
	private List<Comment> comments;
	
	public Ticket(String projectName, String title, String description, List<Comment> comments) {
	   this.projectName = projectName;
	   this.title = title;
	   this.description = description;
	   this.comments = comments;
	}
	
	public String getProjectName() 
	{
		return projectName;
	}

	public void setProjectName(String name) 
	{
		this.projectName = name;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getDesc()
	{
		return description;
	}
	
	public void setDesc(String description)
	{
		this.description = description;
	}
	
	public List<Comment> getComments()
	{
		return comments;
	}
	
	public void setComments(List<Comment> comments)
	{
		this.comments = comments;
	}
	
	public boolean equals(Ticket ticket)
	{
		boolean equals;
		
		if (projectName.equals(ticket.getProjectName()) & title.equals(ticket.getTitle()) & description.equals(ticket.getDesc()))
		{
			equals = true;
		}
		else
		{
			equals = false;
		}
		
		return equals;
	}
	
	public boolean nameEquals(String string)
	{
		boolean equals;
		
		if (projectName.equals(string))
		{
			equals = true;
		}
		else
		{
			equals = false;
		}
		
		return equals;
	}

}
