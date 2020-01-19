package io.swagger.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created on 16.01.20.
 *
 * @author Max
 */
@Component
public class MailHandler {

    @Autowired
    private MailSender emailSender;

    private static final Random random = new Random();


    private static String generatePassword () {
        String randomPass = "";

        for (int i = 0; i < 10; i++) {
            int type = random.nextInt(3); // type: 0-> generate number, 1 -> uppercase, 2-> lowercase
            int asciiChar = random.nextInt (type == 0 ? 10 : 26) + (type == 0 ? '0' : (type == 1? 'A' : 'a'));
            randomPass += (char)asciiChar;
        }
        return randomPass;
    }

    public String sendRandomPassword (String to, boolean reset) {
        // creates a random pass & sends it to user mail. It is hard to check if the message was well delivered,
        // an upgrade would be to store a timestamp and delete the user if he didn't connect in x hours.
        String randomPass = generatePassword();

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("Your password for AMT Auth API" + (reset ? " was reset." : "."));
            message.setText("Your new random generated password is: "+ randomPass + "\nYou can change it once connected.");
            message.setTo(to);


            if (emailSender == null) {
                System.out.println("shit, null.");
                return "";
            }


            emailSender.send(message);
            System.out.println("hurra : "+randomPass);
            return randomPass;
        }
        catch (MailSendException e) {
            return "";
        }

    }
}
