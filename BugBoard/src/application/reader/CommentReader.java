package application.reader;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import application.comment.Comment;

/**
 * Reads in comment data.
 */
public class CommentReader {
    /**
     * Reads in comment data and returns a list of Comment items.
     * @param localFilePath Local file path to read from.
     * @return List of comments.
     */
    public List<Comment> readComments(String localFilePath) {
        List<Comment> comments = new ArrayList<Comment>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(localFilePath));
            
            // TODO: Read comment data here.
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return comments;
    }
}


