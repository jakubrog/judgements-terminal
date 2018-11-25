package project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Command {
    private List<Judgment> judgmentList = new ReadFiles().read();
    private Map<Long, Judgment> judgmentMap = new HashMap<>();
    private Map<String, Judge> judgesMap = new HashMap<>();
    private boolean sentencesGenerated = false;


    public Command() {
        for (Judgment element : judgmentList) {
            judgmentMap.put(element.id, element);
        }
    }

    public String substantiation(long signature) {
        return judgmentMap.containsKey(signature) ? judgmentMap.get(signature).textContent :
                "Brak orzeczenia o sygnaturze: " + signature;
    }


    public String getJudgment(long signature) {
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



}
