package project;

import jdk.nashorn.internal.parser.Scanner;

import java.util.List;

public class Judgment {

        public long id;

        public String courtType;

        public String judgmentType;

        public List<Judge> judges;

        public String textContent;

        public List<Regulation> referencedRegulations;

        public String judgmentDate;

        public Judgment(long signature){
            this.id = signature;
        }

        @Override
        public String toString(){
            return textContent;
        }


        public String getJudges(){
            StringBuilder result = new StringBuilder();
            for(Judge judge : judges)
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
            if(!(other instanceof project.Judgment))
                return false;
            project.Judgment that = (project.Judgment) other;
            return this.id == that.id;
        }


        public boolean containsJudge(String name){
            for(Judge judge : judges)
                if(judge.equals(name))
                    return true;
            return false;
        }

        public int getMonth(){
            Scanner scanner = new Scanner(judgmentDate);
        }

}

