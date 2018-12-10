package project.Commands;

import project.Judgment;

import java.time.Month;
import java.util.List;

/**
 * Created by student18 on 2018-12-10.
 */
public class MonthsCommand {
    public  String monthStats(List<Judgment> judgmentList) {
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
}
