package fileNotFound;

import java.io.File;
import java.io.FileReader;

public class filenotFound {
    File file;
    FileReader fr;
    public void wBug()
    {
        try {
            fr = new FileReader(file);
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }
    public filenotFound(String input)
    {
        file = new File(input);
    }
}
