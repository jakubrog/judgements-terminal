package project.Commands;

import project.Judge;
import project.judgement.JudgeInCase;
import project.judgement.Judgment;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by student18 on 2018-12-10.
 */
public class JudgesCommand {

    public  String get10BestJudges(LinkedHashSet<Judgment> judgmentList) {
        List<Judge> judgeList = new LinkedList<>();
        boolean find = false;

        for (Judgment element : judgmentList) {
            if(element.judges != null )
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
        for (int i = 0; i < 10 && i < judgeList.size(); i++) {
            judge = judgeList.get(i);
            result.append(judge.getName() + " " + judge.getNbOfCases() + "<br>");
        }
        return result.toString();
    }
}
