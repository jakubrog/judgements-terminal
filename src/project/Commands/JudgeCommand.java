package project.Commands;

import project.judgement.Judgment;

import java.util.LinkedHashSet;

/**
 * Created by student18 on 2018-12-10.
 */
public class JudgeCommand {
    public  String nbOfSentences(String name,LinkedHashSet<Judgment> judgmentList) {
        int result = 0;
        for (Judgment element : judgmentList) {
            if (element.containsJudge(name))
                result++;
        }
        return result > 0 ? Integer.toString(result) : "Brak sędziego: " + name;
    }

}
