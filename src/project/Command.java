package project;

import javax.swing.text.html.HTMLDocument;
import java.time.Month;
import java.util.*;


public class Command {
    private List<Judgment> judgmentList = new ReadFiles().read();

    private Map<String, Judgment> judgmentMap = new HashMap<>();

    /**
     * Dodac funcke która obliczy które ustawy były kiedy używane
     */


    public Command() {
        for (Judgment element : judgmentList)
            judgmentMap.put(element.getSignature(), element);
    }


    private String substantiation(String signature) {
        return judgmentMap.containsKey(signature) ? judgmentMap.get(signature).textContent :
                "Brak orzeczenia o sygnaturze: " + signature;
    }

    private String statisticalDistributionOfJudgments() {
        int[] results = new int[12];

        for (Judgment judgment : judgmentList) {
            results[judgment.getMonth().getValue() - 1]++;
        }
        StringBuilder result = new StringBuilder();
        Month months;
        for (int i = 0; i < 12; i++)
            result.append(Month.of(i + 1) + "  " + results[i] + "<br>");
        return result.toString();
    }

    private String getJudgment(String signature) {
        if (judgmentMap.containsKey(signature)) {

            StringBuilder str = new StringBuilder();

            Judgment judgment = judgmentMap.get(signature);


            str.append("Signature: " + judgment.getSignature());

            str.append("<br>Date: " + judgment.judgmentDate);

            str.append("<br>Court Type: " + judgment.courtType);

            str.append("<br>Judges: <br>");

            for (JudgeInCase judge : judgment.judges) {
                str.append(judge.getName() + '\t');

                if (judge.getFunction() != null)
                    str.append(judge.getFunction());

                str.append("<br>");
            }
            return str.toString() + "<br>";
        }
        return "Brak orzeczenia o sygnaturze: " + signature + "<br><br>";
    }

    private String nbOfSentences(String name) {
        int result = 0;
        for (Judgment element : judgmentList) {
            if (element.containsJudge(name))
                result++;
        }
        return result > 0 ? Integer.toString(result) : "Brak sędziego: " + name;
    }


    private String get10BestJudges() {
        List<Judge> judgeList = new LinkedList<>();
        boolean find = false;

        for (Judgment element : judgmentList) {
            for (JudgeInCase judgeInCase : element.judges) {
                find = false;
                for (Judge judge : judgeList) {
                    if (judge.getName().equals(judgeInCase.getName())) {
                        judge.addCase();
                        find = true;
                        break;
                    }
                }
                if (!find) {
                    Judge judgeToAdd = new Judge(judgeInCase.getName());
                    judgeList.add(judgeToAdd);
                }
            }
        }
        judgeList.sort(new Comparator<Judge>() {
            @Override
            public int compare(Judge o1, Judge o2) {
                if (o1.getNbOfCases() < o2.getNbOfCases())
                    return 1;
                if (o1.getNbOfCases() > o2.getNbOfCases())
                    return -1;
                else return 0;
            }
        });
        StringBuilder result = new StringBuilder();
        Judge judge;
        for (int i = 0; i < 10; i++) {
            judge = judgeList.get(i);
            result.append(judge.getName() + " " + judge.getNbOfCases() + "<br>");
        }
        return result.toString();
    }

    public String realize(Scanner input) {
        switch (input.next()) {
            case "judgment": {
                input.useDelimiter("'");
                StringBuilder str = new StringBuilder();
                while (input.hasNext()) {
                    String s = input.next();
                    if (!s.replaceAll(" ", "").isEmpty()) {
                        str.append(getJudgment(s));
                    }
                }
                return str.toString();
            }
            case "substantion":
                return substantiation(input.next());
            case "nb": {
                input.useDelimiter("'");
                System.out.println(input.next());
                return nbOfSentences(input.next());
            }
            case "bestJudges": {
                return get10BestJudges();
            }
            case "statistical": {
                return statisticalDistributionOfJudgments();
            }
        }
        return "";
    }

}