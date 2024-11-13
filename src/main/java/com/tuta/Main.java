package com.tuta;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        if(args.length != 1){
            System.out.println("Please provide only one argument, the full path of the directory with e-mails to inspect");
            System.out.println("Follow the README.MD example.");
        }
        List<Email> emails;

        try {
            emails = InputReader.readFilesToEmails(args[0]);
        } catch (IOException e) {
            System.out.println("Failed to process input emails.");
            throw new RuntimeException(e);
        }

        List<Email> verifiedEmails = SpamDetection.verifyEmailsForSpam(emails);
        for (Email email : verifiedEmails) {
            System.out.println(email.toString());
        }
    }
}