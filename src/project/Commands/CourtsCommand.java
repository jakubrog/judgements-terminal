package project.Commands;

import project.CourtType;
import project.Judgment;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by student18 on 2018-12-10.
 */
public class CourtsCommand {

    public String typeStats(List<Judgment> judgmentList){
        Map<String, CourtType> types = new HashMap<>();
        LinkedList<CourtType> typesList = new LinkedList<>();
        for(Judgment judgment : judgmentList){
            if(types.containsKey(judgment.courtType))
                types.get(judgment.courtType).add();
            else {
                CourtType courtType = new CourtType(judgment.courtType);
                types.put(judgment.courtType, courtType);
                typesList.add(courtType);
            }
        }
        StringBuilder str = new StringBuilder();
        for(CourtType court : typesList){
            str.append(court.getCourtType() + " " + court.getNb() + "<br>");
        }
        return str.toString();
    }

}
