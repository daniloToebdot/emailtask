package com.tuta;

import info.debatty.java.stringsimilarity.NormalizedLevenshtein;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpamDetection {

    private static final double CUTOFF_DISTANCE = 0.2;


    public static List<Email> verifyEmailsForSpam(List<Email> emails) {
        Map<String, Email> verifiedEmails = new HashMap<>();
        for (int i = 0; i < emails.size(); i++) {
            Email first = emails.get(i);
            if(verifiedEmails.containsKey(first.getTitle())){
                continue;
                }
                boolean firstDetectedAsSpam = false;
                for (int j = i + 1; j < emails.size(); j++) {
                    Email second = emails.get(j);
                    double distance = getNormalizedLevenshteinDistance(emails.get(i), emails.get(j));
                    if(distance <= CUTOFF_DISTANCE){
                        firstDetectedAsSpam = true;

                        verifiedEmails.put(second.getTitle(), new Email(second, true));
                }
            }

            verifiedEmails.put(first.getTitle(), new Email(first, firstDetectedAsSpam));
        }

        return new ArrayList<>(verifiedEmails.values());
    }

    private static double getNormalizedLevenshteinDistance(Email first, Email second){
        NormalizedLevenshtein levenshtein = new NormalizedLevenshtein();

        return levenshtein.distance(first.getBody(), second.getBody());
    }
}
