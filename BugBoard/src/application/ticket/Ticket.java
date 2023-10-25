package application.ticket;
import java.util.List;

/**
 * Ticket class stores bug information pertaining to a given project including a title, description, and any comments
 */
public class Ticket 
{
	private String name;
	private String title;
	private String description;
	private List<String> comments;
	
	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public List<String> getComments()
	{
		return comments;
	}
	
	public void setComments(List<String> comments)
	{
		this.comments = comments;
	}

}
