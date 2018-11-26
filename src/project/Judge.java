package project;


public class Judge {
    private String name;
    private int nbOfCases = 0;

    public Judge(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Judge))
            return false;
        return name.equals(((Judge) other).name);
    }

    public void addCase(){
        nbOfCases++;
    }

    public int getNbOfCases(){return nbOfCases;}

    public String getName() {
        return name;
    }
}
