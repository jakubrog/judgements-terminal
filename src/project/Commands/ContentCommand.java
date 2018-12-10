package project.Commands;

import project.Judgment;

import java.util.Map;

/**
 * Created by student18 on 2018-12-10.
 */
public class ContentCommand {
    public String substantiation(String signature, Map<String, Judgment> judgmentMap) {
        return judgmentMap.containsKey(signature) ? judgmentMap.get(signature).textContent :
                "Brak orzeczenia o sygnaturze: " + signature;
    }

}
