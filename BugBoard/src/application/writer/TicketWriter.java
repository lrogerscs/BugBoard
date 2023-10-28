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
     *
     * @param tickets       Tickets to write.
     * @param localFilePath Destination file path.
     */
    public void writeTickets(List<Ticket> tickets, String localFilePath) {
        try {
            FileOutputStream stream = new FileOutputStream(localFilePath);
            for (Ticket ticket : tickets) {
                StringBuilder data = new StringBuilder();
                data.append(ticket.getName()).append(",");
                data.append(ticket.getTitle()).append(",");
                data.append(ticket.getDescription());

                List<String> comments = ticket.getComments();
                if (comments != null && !comments.isEmpty()) {
                    for (String comment : comments) {
                        data.append(",").append(comment);
                    }
                }

                data.append("\n");

                stream.write(data.toString().getBytes());
            }
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
