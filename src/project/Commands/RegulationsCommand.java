package project.Commands;

import project.Judge;
import project.judgement.Judgment;
import project.judgement.Regulation;

import java.util.*;

/**
 * Created by student18 on 2018-12-10.
 */
public class RegulationsCommand {

    public String bestRegulations(LinkedHashSet<Judgment> judgmentList) {
        Map<Integer, Regulation> regulationMap = new HashMap<>();
        List<Regulation> regulationList = new LinkedList<>();
        for (Judgment judgment : judgmentList) {
            if (judgment.referencedRegulations != null)
                for (Regulation regulation : judgment.referencedRegulations) {
                    if (regulationMap.containsKey(regulation.hashCode()))
                        regulationMap.get(regulation.hashCode()).add();
                    else {
                        regulationList.add(regulation);
                        regulationMap.put(regulation.hashCode(), regulation);
                        regulation.add();
                    }
                }
        }
        regulationList.sort(new Comparator<Regulation>() {
            @Override
            public int compare(Regulation o1, Regulation o2) {
                if (o1.getOccursNb() < o2.getOccursNb())
                    return 1;
                if (o1.getOccursNb() > o2.getOccursNb())
                    return -1;
                else return 0;
            }
        });
        StringBuilder result = new StringBuilder();
        Judge judge;
        for (int i = 0; i < 10 && i < regulationList.size(); i++) {
            Regulation regulation = regulationList.get(i);
            result.append(i + 1 + ". " + regulation.toString() + "<br>Powtórzenia: " + regulation.getOccursNb() + "<br><br>");
        }
        return result.toString();
    }
}
