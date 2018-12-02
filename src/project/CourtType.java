package project;

public class CourtType {
    private String courtType;
    private int nb = 0;

    public CourtType(String name){
        courtType = name;
        nb++;
    }
    public void add(){
        nb++;
    }

    public String getCourtType() {
        return courtType;
    }

    public int getNb() {
        return nb;
    }
}
