package application.reader;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import application.ticket.Ticket;

/**
 * Reads in ticket data.
 */
public class TicketReader {
    /**
     * Reads in ticket data and returns a list of Ticket items.
     *
     * @param localFilePath Local file path to read from.
     * @return List of tickets.
     */
    public List<Ticket> readTickets(String localFilePath) {
        List<Ticket> tickets = new ArrayList<Ticket>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(localFilePath));

            if (lines.size() < 1)
                return tickets;

            for (String line : lines) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    Ticket ticket = new Ticket();
                    ticket.setName(data[0]);
                    ticket.setTitle(data[1]);
                    ticket.setDescription(data[2]);

                    if (data.length > 3) {
                        List<String> comments = new ArrayList<>();
                        for (int i = 3; i < data.length; i++) {
                            comments.add(data[i]);
                        }
                        ticket.setComments(comments);
                    }

                    tickets.add(ticket);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tickets;
    }
}