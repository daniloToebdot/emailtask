import com.tuta.Email;
import com.tuta.SpamDetection;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SpamDetectionTest {

    @Test
    public void testDetectionIdenticalEmails() {
        List<Email> emails = Arrays.asList(
                new Email("content", "title one"),
                new Email("content", "title two")
        );

        List<Email> result = SpamDetection.verifyEmailsForSpam(emails);

        for(Email email: result) {
            assertEquals(true, email.isSpam());
        }
    }

    @Test
    public void testDetectionValidEmails() {
        List<Email> emails = Arrays.asList(
                new Email("a first email content without any similarity", "title one"),
                new Email("Lorem ipsum dolor sit amet, consectetur adipiscing elit", "title two")
        );

        List<Email> result = SpamDetection.verifyEmailsForSpam(emails);

        for(Email email: result) {
            assertEquals(false, email.isSpam());
        }
    }

    @Test
    public void testDetectionMultipleMixedEmails() {
        List<Email> emails = Arrays.asList(
                new Email("The quick brown fox jumps over the lazy dog.", "first"),
                new Email("Sphinx of black quartz, judge my vow.", "title two"),
                new Email("How vexingly quick daft zebras jump!", "title three"),
                new Email("Jaded zombies acted quaintly but kept driving their oxen forward.", "title four"),
                new Email("Jaded zombies acted quaintly but kept driving their oxen backward.", "title five")
        );

        List<Email> result = SpamDetection.verifyEmailsForSpam(emails);

        long spamCount = result.stream().filter(email -> Boolean.TRUE.equals(email.isSpam())).count();
        long nonSpamCount = result.stream().filter(email -> Boolean.FALSE.equals(email.isSpam())).count();

        assertEquals(2, spamCount);
        assertEquals(3, nonSpamCount);
    }

    @Test
    public void testWithRealisticData() {
        String realisticBody1 = "Dear Privacy Fan,\n\nBefore the year is ending, we'd like to hear your thoughts" +
                " on existing and upcoming features for Tuta Mail and Tuta Calendar!...";
        String realisticBody2 = "Dear Privacy Fan,\n\nWe are excited to announce the launch of our all new " +
                "independent Tuta Calendar app!...";
        String realisticBody3 =  "Dear sir \n, I am a prince abcd From Country, Yor help is very appreciated, I want to transfer my fortune";
        String realisticBody4 =  "Dear Madam \n, I am a prince abcd From Country, Yor help is very appreciated, I want to transfer my fortune";
        String realisticBody5 =  "Dear Sir or Madam \n, I am a princess abcd From Country, Yor aid is very appreciated, I want to transfer my inheritance";
        List<Email> emails = Arrays.asList(
                new Email(realisticBody1, "first"),
                new Email(realisticBody2, "title two"),
                new Email(realisticBody3, "title three"),
                new Email(realisticBody4, "title four"),
                new Email(realisticBody5, "title five")
        );

        List<Email> result = SpamDetection.verifyEmailsForSpam(emails);

        long spamCount = result.stream().filter(email -> Boolean.TRUE.equals(email.isSpam())).count();
        long nonSpamCount = result.stream().filter(email -> Boolean.FALSE.equals(email.isSpam())).count();

        assertEquals(3, spamCount);
        assertEquals(2, nonSpamCount);
    }

    @Test
    public void testEmailOrderDoesNotInfluenceResults() {
        List<Email> emails = Arrays.asList(
                new Email("a b c d e f g h i j k l m n o p q r s t u v 1 2 3", "similar to second"),
                new Email("9 8 7 a b c d e f g h i j k l m n o p q r s t u v 1 2 3", "similar to first and third"),
                new Email("9 8 7 a b c d e f g h i j k l m n o p q r s t u v", "similar to second but not first"),
                new Email("it is a completely unique email body", "title four"),
                new Email("this is also not marked as spam", "title five")
        );

        List<Email> result = SpamDetection.verifyEmailsForSpam(emails);

        long spamCount = result.stream().filter(email -> Boolean.TRUE.equals(email.isSpam())).count();
        long nonSpamCount = result.stream().filter(email -> Boolean.FALSE.equals(email.isSpam())).count();

        assertEquals(3, spamCount);
        assertEquals(2, nonSpamCount);
    }
}
