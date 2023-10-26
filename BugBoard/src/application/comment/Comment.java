package application.comment;
import java.time.LocalDateTime;

/**
 * Comment class stores comments that belong to a ticket within a project to keep track of notes needed for that given ticket
 */
public class Comment
{
	private String desc;
	private LocalDateTime dateTime;
	
	public Comment(String desc, LocalDateTime date) {
	   this.desc = desc;
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
	
	public LocalDateTime getDateTime()
	{
		return dateTime;
	}
	
	public void setDateTime(LocalDateTime date)
	{
		this.dateTime = date;
	}
	
}
