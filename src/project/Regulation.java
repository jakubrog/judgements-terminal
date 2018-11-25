package project;

public class Regulation {


        public String journalTitle;
        public long journalNo;
        public long journalYear;
        public long journalEntry;
        public String text;


        @Override
        public int hashCode(){
            return (int)this.journalYear * 100 + (int)journalNo;
        }

        @Override
        public boolean equals(Object other){
            if (this == other)
                return true;
            if (!(other instanceof project.Regulation))
                return false;
            project.Regulation that = (project.Regulation) other;

            return this.journalNo == that.journalNo && this.journalYear == that.journalYear
                    && this.journalEntry == that.journalEntry;
    }

}