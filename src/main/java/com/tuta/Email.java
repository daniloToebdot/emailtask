package com.tuta;

import info.debatty.java.stringsimilarity.*;

public class Email {

    private final String body;
    private final String title;
    private Boolean isSpamEmail;

    public Email(String body, String title) {
        this.body = body;
        this.title = title;
    }

    public Email(Email sourceEmail, boolean isSpam) {
        this.body = sourceEmail.body;
        this.title = sourceEmail.title;
        this.isSpamEmail = isSpam;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return this.title;
    }

    public Boolean isSpam() {
        return this.isSpamEmail;
    }



    @Override
    public String toString() {
        if(isSpamEmail == null){
            return  String.format("%s - Not classified.", this.title);
        }

        String classificationText = isSpamEmail ? "yes" : "no";
        return  String.format("%s isSpam: %s ", this.title, classificationText);
    }
}
