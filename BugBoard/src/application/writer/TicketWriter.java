package application.writer;

import java.io.FileOutputStream;
import java.util.List;

import application.ticket.Ticket;

/**
 * Writes ticket data.
 */
public class TicketWriter {
    /**
     * Writes ticket data.
     * @param tickets Tickets to write.
     * @param localFilePath Destination file path.
     */
    public void writeTickets(List<Ticket> tickets, String localFilePath) {
        try {
            FileOutputStream stream = new FileOutputStream(localFilePath);
            for (Ticket ticket : tickets)
               stream.write((ticket.getProjectName() + "," + ticket.getTitle() + "," + ticket.getDesc() + "\n").getBytes());
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
