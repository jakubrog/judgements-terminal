package project;

import javax.swing.text.html.HTMLDocument;
import java.time.Month;
import java.util.*;



public class Command {
    private List<Judgment> judgmentList = new ReadFiles().read();
    private Map<Long, Judgment> judgmentMap = new HashMap<>();
    private LinkedHashMap<String, Judge> judgesMap = new LinkedHashMap<>();
    List<Judge> judgeList = new LinkedList<>();

    public Command() {
        boolean find;

        for (Judgment element : judgmentList) {

            judgmentMap.put(element.id, element);

            for(JudgeInCase judge : element.judges){

                find = false;

                for(Judge judge1 : judgeList) {
                    if (judge1.getName().equals(judge.getName())) {
                        judge1.addCase();
                        find = true;
                        break;
                    }
                }
                if(!find) {
                    Judge judgeToAdd = new Judge(judge.getName());
                    judgeList.add(judgeToAdd);
                }
            }
        }

        judgeList.sort(new Comparator<Judge>() {
            @Override
            public int compare(Judge o1, Judge o2) {
                if(o1.getNbOfCases() < o2.getNbOfCases())
                    return 1;
                if(o1.getNbOfCases() > o2.getNbOfCases())
                    return -1;
                else return 0;
            }});

        for(Judge judge : judgeList)
            judgesMap.put(judge.getName() ,judge);

    }



    private String substantiation(long signature) {
        return judgmentMap.containsKey(signature) ? judgmentMap.get(signature).textContent :
                "Brak orzeczenia o sygnaturze: " + signature;
    }
    private String statisticalDistributionOfJudgments(){
        int [] results = new int[12];

        for(Judgment judgment : judgmentList){
            switch (judgment.getMonth()) {
                case JANUARY: results[0]++;
                    break;
                case FEBRUARY: results[1]++;
                    break;
                case MARCH: results[2]++;
                    break;
                case APRIL: results[3]++;
                    break;
                case MAY: results[4]++;
                    break;
                case JUNE: results[5]++;
                    break;
                case JULY: results[6]++;
                    break;
                case AUGUST: results[7]++;
                    break;
                case SEPTEMBER: results[8]++;
                    break;
                case OCTOBER: results[9]++;
                    break;
                case NOVEMBER: results[10]++;
                    break;
                case DECEMBER: results[11]++;
                    break;
            }
        }
            StringBuilder result = new StringBuilder();
            Month months;
            for(int i = 0; i < 12; i++)
                result.append(Month.of(i+1) + "  " + results[i] + "<br>");
            return result.toString();
    }

    private String getJudgment(long signature) {
        if (judgmentMap.containsKey(signature)) {

            StringBuilder str = new StringBuilder();

            Judgment judgment = judgmentMap.get(signature);


            str.append("Signature: " + judgment.id);

            str.append("\nDate: " + judgment.judgmentDate);

            str.append("\nCourt Type: " + judgment.courtType);

            str.append("\nJudges: \n");

            for (JudgeInCase judge : judgment.judges) {
                str.append(judge.getName() + '\t');
                if (judge.getFunction()!= null)
                    str.append('\t' + judge.getFunction() + '\n');
                else
                    str.append('\n');
            }
            return str.toString();
        }
        return "Brak orzeczenia o sygnaturze: " + signature;
    } /**** zmienić obłusge ID na obsługę sygnatury caseNb */

    private String nbOfSentences(String name){
        return judgesMap.containsKey(name) ? Integer.toString(judgesMap.get(name).getNbOfCases()) : "Brak takiego sędziego.";
    }

    private String get10BestJudges(){
        StringBuilder str = new StringBuilder();
        Judge judge;
        for(int i = 0;i<10;i++) {
            judge = judgeList.get(i);
            str.append("<b>" + judge.getName() + "</b>    " + judge.getNbOfCases() + "<br>");
        }
        return str.toString();
    }

    public String realize(Scanner input){
        switch(input.next()){
            case "judgment":
                return getJudgment(input.nextInt());
            case "substantion" :  // uzasadnienie sprawy
                return substantiation(input.nextInt());
            case "nbOfSentences": {
                StringBuilder str = new StringBuilder(input.next());
                while(input.hasNext()) {
                    str.append(" ");
                    str.append(input.next());
                }
                return nbOfSentences(str.toString());
            }
            case "bestJudges":{
                return get10BestJudges();
            }
            case "statistical":{
                return statisticalDistributionOfJudgments();
            }
        }
        return "";
    }

}