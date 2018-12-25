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



    private String updateJudgments(Scanner input) {
        try {
            judgmentList.addAll(new ReadFiles().read(input.next()));
            for (Judgment element : judgmentList)
                if (!judgmentMap.containsKey(element.getSignature())) {
                    judgmentMap.put(element.getSignature(), element);
                }
        } catch (IOException e) {
            return "Błąd odzcytu plików<br>";
        }

        return "Sukces";
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
                    return "";
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
                    return "";
                }
                case "regulations": {
                    return new RegulationsCommand().bestRegulations(judgmentList);
                }
                case "load": {
                    if (input.hasNext())
                        return updateJudgments(input);
                    return "";
                }
                case "save": {
                    if(input.hasNext()) {
                        saveToFile = new SaveToFile();
                        readyToSave = saveToFile.addWriter(input.next());
                    }
                    return readyToSave ? "Sukces" : "Błędna ścieżka do pliku";
                }
            }
        }
        if (input.hasNext() && input.next().equals("load")) {
            if (input.hasNext())
                return updateJudgments(input);
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
