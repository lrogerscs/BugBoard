package application.writer;

import java.io.FileOutputStream;
import java.util.List;

import application.project.Project;

/**
 * Writes project data.
 */
public class ProjectWriter {
   /**
    * Writes project data.
    * @param projects Projects to write.
    * @param localFile Destination file.
    */
   public void writeProjects(List<Project> projects, String localFile) {
      try {
         FileOutputStream stream = new FileOutputStream(getClass().getClassLoader().getResource(localFile).getFile());
         for (Project project : projects)
            stream.write((project.getName() + "," + project.getDate().toString() + "," + project.getDescription() + "\n").getBytes());
         stream.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}