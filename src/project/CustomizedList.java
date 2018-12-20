package project;


import sun.util.resources.cldr.so.CurrencyNames_so;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class CustomizedList {

    private List<String> list = new LinkedList<>();
    private int index = -1;
    private Command commands = new Command();
    private List<Judgment> judgmentList;  /// dodać obsługe wyjatku przy ladowaniu plik gdy ktoś poda ścieżkę



    public String arrowUp() {
        if (index > 0)
            index--;
        if (!list.isEmpty())
            return list.get(index);
        else return "";
    }

    public String arrowDown() {
        if (index < list.size() - 1) {
            index++;
            return list.get(index);
        }
        return "";

    }

    public String enter(String arg) {
        arg = arg.trim(); // removing spaces in the beginning and in the end
        if (!arg.isEmpty()) {
            list.add(arg);
            index = list.size();
            arg = arg.replaceAll("( )+", " ");  /// changing multiple spaces into one
            Scanner scanner = new Scanner(arg);
            scanner.useDelimiter(" ");

            return commands.realize(scanner);
        }
        return "";
    }

}
