package project;


import project.Commands.*;

import java.util.*;

public class Command {
    private List<Judgment> judgmentList;

    private Map<String, Judgment> judgmentMap = new HashMap<>();

    private void updateJudgments(){
        for (Judgment element : judgmentList)
            if(judgmentMap.containsKey(element.getSignature()))
                 judgmentMap.put(element.getSignature(), element);
    }


    public String realize(Scanner input) {
        if(judgmentList != null)
        switch (input.next()) {
            case "judgment": {
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
            case "sbs": {
                if(input.hasNext())
                    return new ContentCommand().substantiation(input.nextLine().trim(), judgmentMap);
                return "";
            }
            case "nb": {
                if (input.hasNext())
                    return new JudgeCommand().nbOfSentences(input.next(), judgmentList);
                return "";
            }
            case "bestj": {
                return new JudgesCommand().get10BestJudges(judgmentList);
            }
            case "monthstats": {
                return new MonthsCommand().monthStats(judgmentList);
            }
            case "typestats": {
                return new CourtsCommand().typeStats(judgmentList);
            }
            case "jury" :{
                if (input.hasNextInt())
                     return new JuryCommand().jury(input.nextInt(), judgmentList);
            }
            case "bestr" :{
                return new RegulationsCommand().bestRegulations(judgmentList);
            }
            case "load" :{
                if(input.hasNext())
                    judgmentList.addAll(new ReadFiles().read(input.next()));
            }
        }
        return "";
    }
}