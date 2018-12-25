package project;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import project.judgement.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ReadFiles {

    private Items regulations = new Items();
    private Gson gson = new GsonBuilder().create();


    public List<Judgment> read(String rootPath) throws IOException {

        Files.walk(Paths.get(rootPath))
                .filter(path -> path.toFile().isFile())
                .forEach(this::visit);

        return regulations.items;
    }

    private void visit(Path path) {
        try {
            if (getExtension(path.toString()).equals("json")) {
                parseJSON(path.toString());
            } else if (getExtension(path.toString()).equals("html")) {
                parseHTML(path.toString());
            }
        } catch (IOException e) {
            System.out.print("Parsing error");
        }
    }

    private String getExtension(String path) {
        String result = "";

        int i = path.lastIndexOf('.');

        if (i > 0 && i < path.length() - 1)
            result = path.substring(i + 1);

        return result;
    }

    private void parseJSON(String path) {
        try {
            FileReader reader = new FileReader(path);
            Items p = gson.fromJson(reader, Items.class);
            regulations.items.addAll(p.items);
        } catch (FileNotFoundException e) {
            System.out.println(path + " - file not found.");
        }
    }

    private void parseHTML(String path) throws IOException {
        Judgment judgment = new Judgment();

        File reader = new File(path);
        Document doc = Jsoup.parse(reader, "UTF-8");
        Elements links = doc.select("td.info-list-value");

        Elements labels = doc.select("td.info-list-label");


        int index = 0;
        String result;
        for (Element element : links) {
            Scanner scan = new Scanner(element.toString()).useDelimiter("<br>");
            result = Jsoup.parse(scan.next(), "UTF-8").text();

            switch (index) {

                case 0: {
                    if (result.indexOf(" ") > 0)
                        judgment.judgmentDate = result.substring(0, result.indexOf(" "));
                    else
                        judgment.judgmentDate = result;
                    break;

                }
                case 2: {
                    judgment.courtType = result;
                    break;
                }
                case 3: {
                    judgment.judges = new LinkedList<>();

                    while (true) {
                        JudgeInCase judge = new JudgeInCase();
                        String[] split = result.split("/");
                        if (split.length >= 2) {
                            judge.name = split[0].trim();
                            judge.function = split[1].trim();
                        } else if (split.length == 1)
                            judge.name = split[0].trim();
                        judgment.judges.add(judge);
                        if (scan.hasNext())
                            result = Jsoup.parse(scan.next(), "UTF-8").text().trim();
                        else break;
                    }
                    break;
                }


                case 7: {
                    judgment.referencedRegulations = new LinkedList<>();
                    int everyOther = 0;
                    while (true) {
                        if (result.contains("Odrzucono"))
                            System.out.println(path + " odrzucono");
                        Regulation regulation = new Regulation();
                        if (everyOther % 2 == 1) {
                            regulation.text = result;
                            judgment.referencedRegulations.add(regulation);
                        }
                        if (scan.hasNext())
                            result = Jsoup.parse(scan.next(), "UTF-8").text().trim();
                        else break;
                        everyOther++;
                    }
                }
            }
            index++;
        }


        Elements content = doc.select("td.info-list-label-uzasadnienie");
        if (content.size() > 1)
            judgment.textContent = content.get(1).text();
        else if (content.size() > 0)
            judgment.textContent = content.get(0).text();

        Elements title = doc.select("title");
        if (title.size() > 0) {
            judgment.courtCases = new CourtCases[1];
            judgment.courtCases[0] = new CourtCases();
            String signature = title.get(0).text();
            judgment.courtCases[0].caseNumber = signature.substring(0, signature.indexOf("-")).trim();
        }

        regulations.items.add(judgment);
    }
}
