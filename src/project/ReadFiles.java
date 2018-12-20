package project;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class ReadFiles {

    private Items regulations = new Items();
    private Gson gson = new GsonBuilder().create();


    public List<Judgment> read(String rootPath) throws IOException {

        Files.walk(Paths.get(rootPath))
                .filter(path -> path.toFile().isFile())
                .forEach(this::visit);

        return regulations.items;
    }

    private void visit(Path path) {
        if(getExtension(path.toString()).equals("json")){
            parseJSON(path.toString());
        }else if(getExtension(path.toString()).equals("html")){
            parseHTML(path.toString());
        }
    }

    private String getExtension(String path) {
        String result = "";

        int i = path.lastIndexOf('.');

        if(i > 0 && i < path.length()-1)
            result = path.substring(i+1);

        return result;
    }

    private void parseJSON(String path){
        try {
            FileReader reader = new FileReader(path);
            Items p = gson.fromJson(reader, Items.class);
            regulations.items.addAll(p.items);
        }catch(FileNotFoundException e) {
            System.out.println(path + " - file not found.");
        }
    }

    private void parseHTML(String path){
            // dodać całą obsługę parsowania HTML razem z ladowaniem do klas
    }

}
