package krush.ua.technical.mail.service.dto;

import java.io.File;
import java.util.List;

public record MailMessageWithAttachmentFile (
        String receiverEmail,
        String text,
        String subject,
        List<File> files
) { }
