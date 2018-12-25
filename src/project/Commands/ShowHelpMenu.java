package project.Commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ShowHelpMenu {

    private String menu = "";
    boolean ready = false;

    public String helpMenu() {
        if (!ready) {
            try {
                menu = new String(Files.readAllBytes(Paths.get("help_menu")));
            } catch (Exception e) {
                return "Cannot find help-menu file. Try to reinstall program.";
            }
        }
        return menu;

    }
}
