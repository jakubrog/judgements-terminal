package project;

import java.util.*;



//// wykorzystac klase judge do przechowywania wysztsich sedziow razem z ich nuemrem wyrokow
//// zmienic metody equals aby sedziowie z sprawy oraz nie ze sprawy wiedzieli ze sa tymi smsymi

public class Command {
    private List<Judgment> judgmentList = new ReadFiles().read();
    private Map<Long, Judgment> judgmentMap = new HashMap<>();
    private TreeMap<Integer,Judge> judges = new TreeMap<>();

    public Command() {
        for (Judgment element : judgmentList) {
            judgmentMap.put(element.id, element);

            }
        }

    private String substantiation(long signature) {
        return judgmentMap.containsKey(signature) ? judgmentMap.get(signature).textContent :
                "Brak orzeczenia o sygnaturze: " + signature;
    }


    private String getJudgment(long signature) {
        if (judgmentMap.containsKey(signature)) {

            StringBuilder str = new StringBuilder();

            Judgment judgment = judgmentMap.get(signature);


            str.append("Signature: " + judgment.id);

            str.append("\nDate: " + judgment.judgmentDate);

            str.append("\nCourt Type: " + judgment.courtType);

            str.append("\nJudges: \n");

            for (Judge judge : judgment.judges) {
                str.append(judge.getName() + '\t');
                if (judge.getFunction()!= null)
                    str.append('\t' + judge.getFunction() + '\n');
                else
                    str.append('\n');
            }
            return str.toString();
        }
        return "Brak orzeczenia o sygnaturze: " + signature;
    }

    private int nbOfSentences(String name){
        return 0;
    }


    public String realize(Scanner input){
        switch(input.next()){
            case "judgment":
                return getJudgment(input.nextInt());
            case "substantion" :
                return substantiation(input.nextInt());
        }
        return "";
    }



}
