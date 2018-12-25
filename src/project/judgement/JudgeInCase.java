package project.judgement;

import project.Judge;

public class JudgeInCase {
        public String name;
        public String function;
        public  String [] specialRoles;


        @Override
        public boolean equals(Object other){
            if((other instanceof Judge))
                return name.equals(((Judge) other).getName());
            if((other instanceof JudgeInCase))
                return name.equals(((JudgeInCase) other).getName());
            return false;
        }

        public String getName() {
            return name;
        }

        public String getFunction() {
            return function;
        }

}
