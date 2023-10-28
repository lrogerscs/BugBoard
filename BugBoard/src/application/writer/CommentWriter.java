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
     *
     * @param comments      Comments to write.
     * @param localFilePath Destination file path.
     */
    public void writeComments(List<Comment> comments, String localFilePath) {
        try {
            FileOutputStream stream = new FileOutputStream(localFilePath);
            for (Comment comment : comments) {
                StringBuilder data = new StringBuilder();
                data.append(comment.getText()).append(",");
                data.append(comment.getDate().toString()).append("\n");

                stream.write(data.toString().getBytes());
            }
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

