package project.Commands;

import java.util.Scanner;
///TODO : add commands support here, and  exceptions checking
public enum CommandNames {
    CONTENT, COURTS, JUDGE, JUDGES, JURY, MONTHS, REGULATIONS, RUBRUM;

    public CommandNames command(Scanner command) throws IllegalArgumentException{
        switch(command.next()){
            case "content" : return CONTENT;
            case "courts" : return COURTS;
            case "judge" : return JUDGE;
            case "judges" : return JUDGES;
            case "jury" : return JURY;
            case "months" : return MONTHS;
            case "regulations" : return REGULATIONS;
            case "rubrum" : return RUBRUM;
        }

        throw new IllegalArgumentException();
    }

}
