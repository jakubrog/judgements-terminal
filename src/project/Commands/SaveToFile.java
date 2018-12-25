package project.Commands;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveToFile {

    private File file;
    private FileWriter writer;



    public Boolean addWriter(String path){
        try{
            file = new File(path);
            file.getParentFile().mkdirs();
            writer = new FileWriter(file);
        }catch(Exception e){
            return false;
        }
        return true;
    }


    public void  writeToFile(String argument){
        try {
            writer = new FileWriter(file, true);
            writer.write(argument);
            writer.close();
        }catch(Exception e){
            System.out.print("Error in writing result to file.");
        }

    }
}
