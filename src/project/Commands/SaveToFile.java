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
        }catch(IOException e){
            return false;
        }
        return true;
    }


    public void  writeToFile(String argument){
        try {
            writer = new FileWriter(file, true);
            writer.write(argument);
            writer.close();
        }catch(IOException e){
            System.out.print("blad zapisu");
        }catch(NullPointerException x) {
            System.out.print("blad zapisu");
        }

    }
}
