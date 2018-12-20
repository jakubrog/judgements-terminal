package project.Commands;

import project.Judgment;
import java.util.List;

/**
 * Created by student18 on 2018-12-10.
 */
public class JuryCommand {
    public String jury(int value, List<Judgment> judgmentList){
        int result = 0;
        for(Judgment judgment : judgmentList)
            if(judgment.judges.size() == value)
                result++;

        return Integer.toString(result);
    }
}
