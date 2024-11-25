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

            Email current = emails.get(i);
            boolean firstDetectedAsSpam = false;

            for (int j = i + 1; j < emails.size(); j++) {
                Email comparisonEmail = emails.get(j);
                double distance = getNormalizedLevenshteinDistance(current, comparisonEmail);

                if(distance <= CUTOFF_DISTANCE){
                    firstDetectedAsSpam = true;
                    verifiedEmails.put(comparisonEmail.getTitle(), new Email(comparisonEmail, true));
                }
            }

            boolean isEmailAlreadyVerified = verifiedEmails.containsKey(current.getTitle());
            if(!isEmailAlreadyVerified) {
                verifiedEmails.put(current.getTitle(), new Email(current, firstDetectedAsSpam));
            }
        }

        return new ArrayList<>(verifiedEmails.values());
    }

    private static double getNormalizedLevenshteinDistance(Email first, Email second){
        NormalizedLevenshtein levenshtein = new NormalizedLevenshtein();

        return levenshtein.distance(first.getBody(), second.getBody());
    }
}
