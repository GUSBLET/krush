package krush.ua.technical.mail.service;


import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import krush.ua.technical.mail.service.dto.MailMessage;
import krush.ua.technical.mail.service.dto.MailMessageHTML;
import krush.ua.technical.mail.service.dto.MailMessageWithAttachmentFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

@Service
@Slf4j
public class MailService {

    @Value("${spring.mail.username}")
    private  String email;

    @Value("${spring.mail.password}")
    private  String password;

    @Value("${spring.mail.host}")
    private  String host;

    @Value("${spring.mail.port}")
    private  String port;

    public boolean sendMail(MailMessage mailMassage) {
        Session session = getSession();

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailMassage.receiverEmail()));
            message.setSubject(mailMassage.subject());
            message.setText(mailMassage.text());

            Transport.send(message);
        } catch (MessagingException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean sendMail(MailMessageHTML parameters) {
        Session session = getSession();

        try {
            String html = new String(Files.readAllBytes(parameters.htmlFile().toPath()));
            Multipart multipart = new MimeMultipart();

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(html, "text/html; charset=utf-8");
            multipart.addBodyPart(mimeBodyPart);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(parameters.receiverEmail()));
            message.setSubject(parameters.subject());
            message.setContent(multipart);


            Transport.send(message);
        } catch (MessagingException | IOException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean sendMail(MailMessageWithAttachmentFile parameters) {
        Session session = getSession();

        try {
            Multipart multipart = new MimeMultipart();
            for (File file : parameters.files()) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.attachFile(file);

                multipart.addBodyPart(attachmentPart);
            }

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(parameters.receiverEmail()));
            message.setSubject(parameters.subject());
            message.setText(parameters.text());
            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException | IOException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }



    private Session getSession() {
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.host", host);
        mailProperties.put("mail.smtp.port", port); // SMTP port (587 for TLS, 465 for SSL, 25 for non-secure)
        mailProperties.put("mail.smtp.auth", true);
        mailProperties.put("mail.smtp.starttls.enable", true); // Use TLS;

        return Session.getInstance(mailProperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });
    }
}
