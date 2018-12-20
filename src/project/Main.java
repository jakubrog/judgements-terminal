package project;


/// jezeli sędzia pojawia się w kiliu sprawach to tworzona jest kolejna instancja?

// sygnatura to caseNumber


import java.io.IOException;
import java.nio.file.*;
/*****
 * zmienić wczytywanie plików aby program działał z komendą wczytaj pliki i wczystywał wszytskie pliki z podanego katalogu
 * dodać nowe funkcjonalności ze strony
 * jak zaladowac i parsowac htmla
 * https://javarevisited.blogspot.com/2014/09/how-to-parse-html-file-in-java-jsoup-example.html
 */
public class Main{

        public static void main(String[] args) {
            //try{
            System.out.print(Files.exists(Paths.get("/home/jakubrog/Documents")));
           //MyGUIForm g = new MyGUIForm();
            try{
                Files.walk(Paths.get("/home/jakubrog/Documents"))
                        .filter(Files::isRegularFile)
                        .forEach(System.out::println);
            } catch(IOException x){
                System.out.print(x);
            }

        }
}
