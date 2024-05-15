package krush.ua.technical.mail.service.dto;

import java.io.File;

public record MailMessageHTML (
        String receiverEmail,
        File htmlFile,
        String subject
) { }
