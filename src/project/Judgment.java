package project;

import jdk.nashorn.internal.parser.Scanner;

import java.time.Month;
import java.util.*;

public class Judgment {

        public long id;

        public String courtType;

        public String judgmentType;

        public List<JudgeInCase> judges;

        public String textContent;

        public List<Regulation> referencedRegulations;

        public String judgmentDate;

        public CourtCases [] courtCases;


        public Judgment(long signature){
            this.id = signature;
        }

        @Override
        public String toString(){
            return textContent;
        }


        public String getJudges(){
            StringBuilder result = new StringBuilder();
            for(JudgeInCase judge : judges)
                result.append(judge);
            return result.toString();
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
            project.Judgment that = (Judgment) other;
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

}

