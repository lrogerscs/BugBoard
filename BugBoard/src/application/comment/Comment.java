package application.comment;
import java.time.LocalDate;

/**
 * Comment class stores comments that belong to a ticket within a project to keep track of notes needed for that given ticket
 */
public class Comment
{
	private String text;
	private LocalDate date;
	
	public String getText()
	{
		return text;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public LocalDate getDate()
	{
		return date;
	}
	
	public void setDescription(LocalDate date)
	{
		this.date = date;
	}
	
}
