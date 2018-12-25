package project.Commands;

import project.CourtType;
import project.judgement.*;

import java.util.LinkedHashSet;

/**
 * Created by student18 on 2018-12-10.
 */
public class CourtsCommand { /// TODO: fix this with courtType

    public String typeStats(LinkedHashSet<Judgment> judgmentList){

        int [] result = new int[CourtType.values().length];

        for(Judgment judgment : judgmentList){
            if(judgment.getCourtType() != null)
                result[judgment.getCourtType().ordinal()]++;
        }
        StringBuilder str = new StringBuilder();
        for(CourtType court : CourtType.values()){
            str.append(court + " " + result[court.ordinal()] + "<br>");
        }
        return str.toString();
    }

}
