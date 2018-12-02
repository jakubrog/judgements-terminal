package project;
import java.util.Scanner;
public class Regulation {


    public String journalTitle;
    public long journalNo;
    public long journalYear;
    public long journalEntry;
    public String text;
    private int occursNb = 0;

    public void add() {
        occursNb++;
    }

    public int getOccursNb() {
        return occursNb;
    }

    @Override
    public int hashCode() {
        return (int) this.journalYear * 100 + (int) journalNo;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof project.Regulation))
            return false;
        project.Regulation that = (project.Regulation) other;

        return this.journalNo == that.journalNo && this.journalYear == that.journalYear
                && this.journalEntry == that.journalEntry;
    }
    @Override
    public String toString(){
        String s = text;
        return s.substring(0, s.indexOf("("));
    }

}