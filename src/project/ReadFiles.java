package project;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class ReadFiles {

    private String [] files = {
            "judgments-348.json", "judgments-356.json", "judgments-520.json", "judgments-924.json",
            "judgments-995.json", "judgments-1117.json", "judgments-1287.json","judgments-1324.json",
            "judgments-1338.json", "judgments-1912.json"
    };

    List<Judgment> read(String path){   /// dodać wczytywanie tych plikow ze względu na wszystko
        Items result = new Items();
        result.items = new LinkedList<>();

        Gson gson = new GsonBuilder().create();

        try {
            for (String arg : files) {
                FileReader reader = new FileReader(arg);
                Items p = gson.fromJson(reader, Items.class);
                result.items.addAll(p.items);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e + "File not found.");
        }
        return result.items;
    }
}
