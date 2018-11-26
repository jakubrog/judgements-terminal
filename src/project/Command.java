package project;

import java.util.*;



public class Command {
    private List<Judgment> judgmentList = new ReadFiles().read();
    private Map<Long, Judgment> judgmentMap = new HashMap<>();
    private LinkedList<Judge> judges = new LinkedList<>();


    public Command() {
        boolean find;

        for (Judgment element : judgmentList) {

            judgmentMap.put(element.id, element);

            for(JudgeInCase judge : element.judges){

                find = false;

                for(Judge judge1 : judges) {
                    if (judge1.getName().equals(judge.getName())) {
                        judge1.addCase();
                        find = true;
                        break;
                    }
                }
                if(!find)
                    judges.add(new Judge(judge.getName()));

            }
        }

        judges.sort(new Comparator<Judge>() {
            @Override
            public int compare(Judge o1, Judge o2) {
                if(o1.getNbOfCases() < o2.getNbOfCases())
                    return 1;
                if(o1.getNbOfCases() > o2.getNbOfCases())
                    return -1;
                else return 0;
            }});
        System.out.print(judges.get(10).getNbOfCases());
    }


    private String substantiation(long signature) {
        return judgmentMap.containsKey(signature) ? judgmentMap.get(signature).textContent :
                "Brak orzeczenia o sygnaturze: " + signature;
    }


    private String getJudgment(long signature) {
        if (judgmentMap.containsKey(signature)) {

            StringBuilder str = new StringBuilder();

            Judgment judgment = judgmentMap.get(signature);


            str.append("Signature: " + judgment.id);

            str.append("\nDate: " + judgment.judgmentDate);

            str.append("\nCourt Type: " + judgment.courtType);

            str.append("\nJudges: \n");

            for (JudgeInCase judge : judgment.judges) {
                str.append(judge.getName() + '\t');
                if (judge.getFunction()!= null)
                    str.append('\t' + judge.getFunction() + '\n');
                else
                    str.append('\n');
            }
            return str.toString();
        }
        return "Brak orzeczenia o sygnaturze: " + signature;
    }

    private int nbOfSentences(String name){
        return 0;
    }


    public String realize(Scanner input){
        switch(input.next()){
            case "judgment":
                return getJudgment(input.nextInt());
            case "substantion" :
                return substantiation(input.nextInt());
        }
        return "";
    }



}
