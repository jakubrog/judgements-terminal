package project.judgement;

import project.CourtType;

import java.time.Month;
import java.util.*;

import static project.CourtType.*;

public class Judgment {

        public long id;

        public String courtType; //ok

        public List<JudgeInCase> judges; //ok

        public String textContent;

        public List <Regulation> referencedRegulations; //ok

        public String judgmentDate;  //ok

        public CourtCases[] courtCases;



        @Override
        public String toString(){
            return textContent;
        }

        @Override
        public int hashCode(){
            return (int)id;
        }

        @Override
        public boolean equals(Object other){
            if(other == this)
                return true;
            if(!(other instanceof Judgment))
                return false;
            Judgment that = (Judgment) other;
            return this.getSignature().equals(that.getSignature());
        }
        public Month getMonth(){
            return Month.of(Integer.parseInt(judgmentDate.substring(5,7)));
        }

        public boolean containsJudge(String name){
            for(JudgeInCase judge : judges)
                if(judge.getName().equals(name))
                    return true;
            return false;
        }

        public String getSignature(){
            if(courtCases.length == 0)
                return null;
            return courtCases[0].caseNumber;
        }


        public CourtType getCourtType(){
            switch(this.courtType){
                case "COMMON" : return COMMON;
                case "CONSTITUTIONAL_TRIBUNAL" : return CONSTITUTIONAL_TRIBUNAL;
                case "SUPREME" : return SUPREME;
                case "NATIONAL_APPEAL_CHAMBER" : return NATIONAL_APPEAL_CHAMBER;
                case "PROVINCIAL_ADMINISTARTIVE_COURT" : return PROVINCIAL_ADMINISTARTIVE_COURT;
                case "SUPREME_ADMINISTARTIVE_COURT" : return SUPREME_ADMINISTARTIVE_COURT;
            }

            if(courtType.toLowerCase().contains("powszechny"))
                return COMMON;
            if(courtType.toLowerCase().contains("najwyższy"))
                return SUPREME;
            if(courtType.toLowerCase().contains("naczelny"))
                return SUPREME_ADMINISTARTIVE_COURT;
            if(courtType.toLowerCase().contains("administracyjny"))
                return PROVINCIAL_ADMINISTARTIVE_COURT;
            if(courtType.toLowerCase().contains("odwoławcza"))
                return NATIONAL_APPEAL_CHAMBER;

            return null;
        }

}

