package application.reader;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import application.comment.Comment;
import application.ticket.Ticket;

/**
 * Reads in ticket data.
 */
public class TicketReader {
    /**
     * Reads in ticket data and returns a list of Ticket items.
     * @param localFilePath Local file path to read from.
     * @return List of tickets.
     */
    public List<Ticket> readTickets(String localFilePath) {
        List<Ticket> tickets = new ArrayList<Ticket>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(localFilePath));

            for (String line : lines) {
                String[] data = line.split(",");
                
                if (data.length < 2)
                   return tickets;
                
                tickets.add(new Ticket(data[0], data[1], data.length > 2 ? data[2] : "", new ArrayList<Comment>()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tickets;
    }
}