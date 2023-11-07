package application.comment;
import java.time.LocalDateTime;

/**
 * Comment class stores comments that belong to a ticket within a project to keep track of notes needed for that given ticket
 */
public class Comment
{
	private String ticketName;
	private LocalDateTime dateTime;
	private String desc;
	
	public Comment(String ticketName, LocalDateTime date, String desc) {
		this.ticketName = ticketName;
		this.dateTime = date;
		this.desc = desc;
	}
	
	public String getTicketName()
	{
		return ticketName;
	}
	
	public void setTicketName(String ticketName)
	{
		this.ticketName = ticketName;
	}
	
	public LocalDateTime getDateTime()
	{
		return dateTime;
	}
	
	public void setDateTime(LocalDateTime date)
	{
		this.dateTime = date;
	}
	
	public String getDesc()
	{
		return desc;
	}
	
	public void setDesc(String text)
	{
		this.desc = text;
	}
	
}
