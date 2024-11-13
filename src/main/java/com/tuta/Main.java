package com.tuta;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Email> emails;

        try {
            emails = InputReader.readFilesToEmails(args[0]);
        } catch (IOException e) {
            System.out.println("Failed to process input emails.");
            throw new RuntimeException(e);
        }

        for (Email email : emails) {

        }


        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}