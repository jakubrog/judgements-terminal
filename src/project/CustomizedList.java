package project;


import sun.util.resources.cldr.so.CurrencyNames_so;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/// TODO: dokladna obsluga strzalek

public class CustomizedList {
    private List<String> list = new LinkedList<>();
    private int index = -1;
    private Command commands = new Command();
    List <Judgment> judgmentList = new ReadFiles().read();


    public String arrowUp() {
       if(index > 0)
           index--;
       if(!list.isEmpty())
           return list.get(index);
       else return "";
    }

    public String arrowDown() {
        if(index < list.size() - 1){
            index++;
            return list.get(index);
        }
        return "";

    }

    public String enter(String arg) {
            list.add(arg);
            index = list.size();

        Scanner scanner = new Scanner(arg);
        scanner.useDelimiter("/");
        StringBuilder result = new StringBuilder();

        if(scanner.next().equals("judgement")) {
            while(scanner.hasNextInt()){
                result.append(commands.getJudgment(scanner.nextInt()) + '\n');
            }
            return result.toString();
        }
        return "";

    }

}
