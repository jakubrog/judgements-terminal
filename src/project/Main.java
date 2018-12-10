package project;


/// jezeli sędzia pojawia się w kiliu sprawach to tworzona jest kolejna instancja?

// sygnatura to caseNumber

/*****
 * zmienić wczytywanie plików aby program działał z komendą wczytaj pliki i wczystywał wszytskie pliki z podanego katalogu
 * dodać nowe funkcjonalności ze strony
 * jak zaladowac i parsowac htmla
 * https://javarevisited.blogspot.com/2014/09/how-to-parse-html-file-in-java-jsoup-example.html
 */
public class Main{

        public static void main(String[] args) {

            String arg = "dasdkjasj";
            String arg1 = "d<s;dakj/>asj";

            arg1 = arg1.replaceAll("<[^/>]*", "").replaceAll("/>", ""); //// ignorowanie tagów html
            System.out.println(arg);
            System.out.println(arg1);

            //MyGUIForm g = new MyGUIForm();

        }
}
