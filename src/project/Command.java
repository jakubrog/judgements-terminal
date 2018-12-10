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

    private String avgJudge(){
        double judgesNb = 0;

        for(Judgment judgment : judgmentList)
            judgesNb+=judgment.judges.size();

        return Double.toString(judgesNb/judgmentList.size());
    }

    public String realize(Scanner input) {
        switch (input.next()) {

            // wyswietlanie kilku (rowniez jednego) rubrum orzeczenia

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

            // uzasadnienie orzeczenia

            case "sbs": {
                if(input.hasNext())
                    return new ContentCommand().substantiation(input.nextLine().trim(), judgmentMap);
                return "";
            }

            // liczba orzeczeń dla wybranego sędziego

            case "nb": {
                if (input.hasNext())
                    return new JudgeCommand().nbOfSentences(input.next(), judgmentList);
                return "";
            }

            // 10 sędziów którzy wydali największa liczbę orzeczeń

            case "bestj": {
                return new JudgesCommand().get10BestJudges(judgmentList);
            }

            // ilosc wyrokow w zależności od miesiąca

            case "monthstats": {
                return new MonthsCommand().monthStats(judgmentList);
            }

            // ilosc wyrokow w zależności od rodzaju sądu

            case "typestats": {
                return new CourtsCommand().typeStats(judgmentList);
            }

            // srednia ilosc sedziego na orzeczenie

            case "avgjudge" :{
                return avgJudge();
            }

            //wyświetlanie najlepszych ustaw

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