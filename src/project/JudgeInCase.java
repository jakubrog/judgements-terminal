package project;

public class JudgeInCase {
        private String name;
        private String function;
        private  String [] specialRoles;


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
