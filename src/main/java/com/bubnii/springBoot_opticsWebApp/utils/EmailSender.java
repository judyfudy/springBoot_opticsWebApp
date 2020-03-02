package com.bubnii.springBoot_opticsWebApp.utils;

import com.bubnii.springBoot_opticsWebApp.service.implementation.CartServiceImpl;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public final class EmailSender {
    private static final String PASSWORD = "567tyu567tyua";
    private static final String EMAIL_FROM = "bubniyo@gmail.com";
    private static final String MAIL_PROPERTIES_FILE = "mail.properties";


    public static void sendEmail(final String email, final String emailMessage) {
        final Properties properties = new Properties();

         try {
            properties.load(EmailSender.class.getClassLoader().getResourceAsStream(MAIL_PROPERTIES_FILE));
            Session mailSession = Session.getDefaultInstance(properties);
            MimeMessage message = prepareMessage(email, mailSession, emailMessage);

            Transport tr = mailSession.getTransport();
            tr.connect(null, PASSWORD);
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static MimeMessage prepareMessage(final String emailTo, final Session mailSession, final String emailMessage) throws MessagingException {
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(EMAIL_FROM));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
        message.setSubject("Дякуємо за замовлення!");
        message.setText(emailMessage);
        return message;
    }
}
