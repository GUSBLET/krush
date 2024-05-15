package krush.ua.technical.mail.service.dto;

public record MailMessage (
        String receiverEmail,
        String text,
        String subject
) { }
