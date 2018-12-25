package project.Commands;

import project.judgement.JudgeInCase;
import project.judgement.Judgment;

import java.util.Map;

/**
 * Created by student18 on 2018-12-10.
 */
public class RubrumCommand {

    public  String getJudgment(String signature,  Map<String, Judgment> judgmentMap) {
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

}
