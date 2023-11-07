package application.writer;

import java.io.FileOutputStream;
import java.util.List;

import application.comment.Comment;

/**
 * Writes comment data.
 */
public class CommentWriter {
    /**
     * Writes comment data.
     * @param comments Comments to write.
     * @param localFilePath Destination file path.
     */
    public void writeComments(List<Comment> comments, String localFilePath) {
        try {
            FileOutputStream stream = new FileOutputStream(localFilePath);
            // TODO: Write comment data here.
            for (Comment comment : comments)
            {
            	stream.write((comment.getProjectName() + "," + comment.getTicketName() + "," + comment.getDateTime() + "," + comment.getDesc() + "\n").getBytes());
            }
            stream.close();
            //End of TODO
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

