package project;


public class Judge {
    private String name;
    private String function;
    private  String [] specialRoles;

    public Judge(String name){
        this.name = name;
    }
    @Override
    public boolean equals(Object other){
        if(!(other instanceof Judge))
            return false;
        return name.equals(((Judge) other).name);
    }

    public String getName() {
        return name;
    }

    public String getFunction() {
        return function;
    }
}
