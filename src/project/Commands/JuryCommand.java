package project.Commands;

import project.judgement.Judgment;

import java.util.LinkedHashSet;

/**
 * Created by student18 on 2018-12-10.
 */
public class JuryCommand {
    public String jury(int value, LinkedHashSet<Judgment> judgmentList){
        int result = 0;
        for(Judgment judgment : judgmentList)
            if(judgment.judges.size() == value)
                result++;

        return Integer.toString(result);
    }
}
