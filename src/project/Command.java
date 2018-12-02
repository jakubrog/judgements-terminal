package project;

import javax.swing.text.html.HTMLDocument;
import java.time.Month;
import java.util.*;


public class Command {
    private List<Judgment> judgmentList = new ReadFiles().read();

    private Map<String, Judgment> judgmentMap = new HashMap<>();


    public Command() {
        for (Judgment element : judgmentList)
            judgmentMap.put(element.getSignature(), element);
    }

    private String bestRegulations(){
        Map<Integer, Regulation> regulationMap = new HashMap<>();
        List<Regulation> regulationList = new LinkedList<>();
        for(Judgment judgment : judgmentList){
            for(Regulation regulation : judgment.referencedRegulations){
                if(regulationMap.containsKey(regulation.hashCode()))
                    regulationMap.get(regulation.hashCode()).add();
                else{
                    regulationList.add(regulation);
                    regulationMap.put(regulation.hashCode(), regulation);
                    regulation.add();
                }
            }
        }
        regulationList.sort(new Comparator<Regulation>() {
            @Override
            public int compare(Regulation o1, Regulation o2) {
                if(o1.getOccursNb() < o2.getOccursNb())
                    return 1;
                if(o1.getOccursNb() > o2.getOccursNb())
                    return -1;
                else return 0;
            }
        });
        StringBuilder result = new StringBuilder();
        Judge judge;
        for (int i = 0; i < 10; i++) {
            Regulation regulation = regulationList.get(i);
            result.append(i+1 + ". " + regulation.toString() + "<br>Powtórzenia: " + regulation.getOccursNb() + "<br><br>");
        }
        return result.toString();
    }
    private String avgJudge(){
        double judgesNb = 0;
        for(Judgment judgment : judgmentList)
            judgesNb+=judgment.judges.size();

        return Double.toString(judgesNb/judgmentList.size());
    }

    private String substantiation(String signature) {
        return judgmentMap.containsKey(signature) ? judgmentMap.get(signature).textContent :
                "Brak orzeczenia o sygnaturze: " + signature;
    }

    private String typeStats(){
        Map <String, CourtType> types = new HashMap<>();
        LinkedList<CourtType> typesList = new LinkedList<>();
        for(Judgment judgment : judgmentList){
            if(types.containsKey(judgment.courtType))
                types.get(judgment.courtType).add();
            else {
                CourtType courtType = new CourtType(judgment.courtType);
                types.put(judgment.courtType, courtType);
                typesList.add(courtType);
            }
        }
        StringBuilder str = new StringBuilder();
        for(CourtType court : typesList){
            str.append(court.getCourtType() + " " + court.getNb() + "<br>");
        }
        return str.toString();
    }

    private String monthStats() {
        int[] results = new int[12];

        for (Judgment judgment : judgmentList) {
            results[judgment.getMonth().getValue() - 1]++;
        }
        StringBuilder result = new StringBuilder();
        Month months;
        for (int i = 0; i < 12; i++)
            result.append(Month.of(i + 1) + "  " + results[i] + "<br>");
        return result.toString();
    }

    private String getJudgment(String signature) {
        if (judgmentMap.containsKey(signature)) {

            StringBuilder str = new StringBuilder();

            Judgment judgment = judgmentMap.get(signature);


            str.append("Signature: " + judgment.getSignature());

            str.append("<br>Date: " + judgment.judgmentDate);

            str.append("<br>Court Type: " + judgment.courtType);

            str.append("<br>Judges: <br>");

            for (JudgeInCase judge : judgment.judges) {
                str.append(judge.getName() + '\t');

                if (judge.getFunction() != null)
                    str.append(judge.getFunction());

                str.append("<br>");
            }
            return str.toString() + "<br>";
        }
        return "Brak orzeczenia o sygnaturze: " + signature + "<br><br>";
    }

    private String nbOfSentences(String name) {
        int result = 0;
        for (Judgment element : judgmentList) {
            if (element.containsJudge(name))
                result++;
        }
        return result > 0 ? Integer.toString(result) : "Brak sędziego: " + name;
    }

    private String get10BestJudges() {
        List<Judge> judgeList = new LinkedList<>();
        boolean find = false;

        for (Judgment element : judgmentList) {
            for (JudgeInCase judgeInCase : element.judges) {
                find = false;
                for (Judge judge : judgeList) {
                    if (judge.getName().equals(judgeInCase.getName())) {
                        judge.addCase();
                        find = true;
                        break;
                    }
                }
                if (!find) {
                    Judge judgeToAdd = new Judge(judgeInCase.getName());
                    judgeList.add(judgeToAdd);
                }
            }
        }
        judgeList.sort(new Comparator<Judge>() {
            @Override
            public int compare(Judge o1, Judge o2) {
                if (o1.getNbOfCases() < o2.getNbOfCases())
                    return 1;
                if (o1.getNbOfCases() > o2.getNbOfCases())
                    return -1;
                else return 0;
            }
        });
        StringBuilder result = new StringBuilder();
        Judge judge;
        for (int i = 0; i < 10; i++) {
            judge = judgeList.get(i);
            result.append(judge.getName() + " " + judge.getNbOfCases() + "<br>");
        }
        return result.toString();
    }

    public String realize(Scanner input) {
        switch (input.next()) {

            // wyswietlanie kilku (rowniez jednego) rubrum orzeczenia

            case "judgment": {
                input.useDelimiter("'");
                StringBuilder str = new StringBuilder();
                while (input.hasNext()) {
                    String s = input.next();
                    if (!s.replaceAll(" ", "").isEmpty()) {
                        str.append(getJudgment(s));
                    }
                }
                return str.toString();
            }

            // uzasadnienie orzeczenia

            case "sbs": {
                if(input.hasNext())
                    return substantiation(input.nextLine().trim());
                return "";
            }

            // liczba orzeczeń dla wybranego sędziego

            case "nb": {
                if (input.hasNext())
                    return nbOfSentences(input.nextLine().trim());
                return "";
            }

            // 10 sędziów którzy wydali największa liczbę orzeczeń

            case "bestj": {
                return get10BestJudges();
            }

            // ilosc wyrokow w zależności od miesiąca

            case "monthstats": {
                return monthStats();
            }

            // ilosc wyrokow w zależności od rodzaju sądu

            case "typestats": {
                return typeStats();
            }

            // srednia ilosc sedziego na orzeczenie

            case "avgjudge" :{
                return avgJudge();
            }

            //wyświetlanie najlepszych ustaw

            case "bestr" :{
                return bestRegulations();
            }

        }
        return "";
    }
}