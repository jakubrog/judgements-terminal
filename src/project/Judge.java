package project;


public class Judge {
    private String name;
    private int nbOfCases = 1;

    public Judge(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object other){
        if((other instanceof Judge))
             return name.equals(((Judge) other).getName());

        if((other instanceof JudgeInCase))
            return name.equals(((JudgeInCase) other).getName());

        return false;
    }

    public void addCase(){
        nbOfCases++;
    }

    public int getNbOfCases(){return nbOfCases;}

    public String getName() {
        return name;
    }
}
