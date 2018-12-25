package project;


import project.Commands.*;
import project.judgement.Judgment;

import java.io.IOException;
import java.util.*;

public class Command {
    private LinkedHashSet<Judgment> judgmentList = new LinkedHashSet<>();

    private Map<String, Judgment> judgmentMap = new HashMap<>();

    private boolean readyToSave = false;

    private SaveToFile saveToFile;

    private ShowHelpMenu menu = new ShowHelpMenu();



    private String updateJudgments(Scanner input) {
        try {
            judgmentList.addAll(new ReadFiles().read(input.next()));
            for (Judgment element : judgmentList)
                if (!judgmentMap.containsKey(element.getSignature())) {
                    judgmentMap.put(element.getSignature(), element);
                }
        } catch (Exception p){
            return "Files did not loaded correctly, check path or try <i>help</i> for more information.<br>";
        }

        return "Files successfully loaded.";
    }


    private String realize(Scanner input) {
        if (!judgmentList.isEmpty()) {
            switch (input.next()) {
                case "rubrum": {
                    input.useDelimiter("\"");
                    StringBuilder str = new StringBuilder();
                    while (input.hasNext()) {
                        String s = input.next();
                        if (!s.replaceAll(" ", "").isEmpty()) {
                            str.append(new RubrumCommand().getJudgment(s, judgmentMap));
                        }
                    }
                    return str.toString();
                }
                case "content": {
                    if (input.hasNext())
                        return new ContentCommand().substantiation(input.nextLine().trim(), judgmentMap);
                    return "";
                }
                case "judge": {
                    if (input.hasNext())
                        return new JudgeCommand().nbOfSentences(input.next(), judgmentList);
                    return "Argument was not detected, try <i> judge JUDGE NAME </i>";
                }
                case "judges": {
                    return new JudgesCommand().get10BestJudges(judgmentList);
                }
                case "months": {
                    return new MonthsCommand().monthStats(judgmentList);
                }
                case "courts": {
                    return new CourtsCommand().typeStats(judgmentList);
                }
                case "jury": {
                    if (input.hasNextInt())
                        return new JuryCommand().jury(input.nextInt(), judgmentList);
                    return "Correct argument was not detected, try <i>jury INTEGER</i> or type <i>help</i> for more information.";
                }
                case "regulations": {
                    return new RegulationsCommand().bestRegulations(judgmentList);
                }
                case "load": {
                    if (input.hasNext())
                        return updateJudgments(input);
                    return "Path was not detected.";
                }
                case "save": {
                    if(input.hasNext()) {
                        saveToFile = new SaveToFile();
                        readyToSave = saveToFile.addWriter(input.next());
                    }
                    return readyToSave ? "File to save results was correctly added." : "Incorrect file path.";
                }
                case "help":{
                    return menu.helpMenu();
                }
            }
        } else if (input.hasNext()) {
            String command = input.next();
            if(command.equals("load") && input.hasNext())
                return updateJudgments(input);
            if(command.equals("help"))
                return menu.helpMenu();
            else
                return
                    "Something went wrong, try to load your data files correctly or type <i>help</i> to " +
                            "get more information.";
        }
        return "";
    }


    public String realizeWithSave(Scanner input){
        String result = realize(input);
        if(readyToSave)
            saveToFile.writeToFile(result);
        return result;
    }



}
