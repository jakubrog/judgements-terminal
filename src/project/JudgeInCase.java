package project;

public class JudgeInCase {
        private String name;
        private String function;
        private  String [] specialRoles;


        @Override
        public boolean equals(Object other){
            if(!(other instanceof Judge)|| other instanceof JudgeInCase))
                return false;
            return name.equals(((project.JudgeInCase) other).name);
        }

        public String getName() {
            return name;
        }

        public String getFunction() {
            return function;
        }

}
