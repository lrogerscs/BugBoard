package application.reader;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import application.project.Project;

/**
 * Reads in project data.
 */
public class ProjectReader {
   /**
    * Reads in project data and returns a list of Project items.
    * @param localFilePath Local file path to read from.
    * @return List of projects.
    */
   public List<Project> readProjects(String localFilePath) {
      List<Project> projects = new ArrayList<Project>();
      
      try {
         List<String> lines = Files.readAllLines(Paths.get(localFilePath));
         String[] data;
         
         if (lines.size() < 1)
            return projects;
         
         for (String line : lines) {
            data = line.split(",");
            projects.add(new Project(data[0], LocalDate.parse(data[1]), data.length > 2 ? data[2] : ""));
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return projects;
   }
}
