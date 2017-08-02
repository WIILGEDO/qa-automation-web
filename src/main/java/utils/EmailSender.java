package utils;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.MessageBody;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;

import java.net.URI;
import java.util.List;

import static consts.PropertiesNames.EMAIL_LOGIN;
import static consts.PropertiesNames.EMAIL_PASS;
import static org.slf4j.LoggerFactory.getLogger;

public class EmailSender {

    private static final Logger LOG = getLogger(EmailSender.class);
    private static Options options;

    static {
        options = new Options();
        options.addOption("d", "domain", true, "domain-part of email address");
        options.addOption("u", "username", true, "local-part of email address");
        options.addOption("p", "password", true, "password");
        options.addOption("to", true, "recipient (you can use multiple)");
        options.addOption("s", "subject", true, "the mail subject");
        options.addOption("t", "text", true, "the mail text");
    }

    public static void sendEmail(String subject, String body,
                                 String attachments, List<String> recipients) throws Exception {

        String userName = PropertyReader.getGlobalProperty(EMAIL_LOGIN);
        String password = PropertyReader.getGlobalProperty(EMAIL_PASS);
        String domain = "dbg";
        ExchangeService service = new ExchangeService();
        service.setTraceEnabled(true);
        service.setUrl(URI.create("To Add Later"));
        service.setCredentials(new WebCredentials(userName, password, domain));

        EmailMessage msg = new EmailMessage(service);
        msg.setSubject(subject);
        msg.setBody(new MessageBody(body));
        msg.getAttachments().addFileAttachment(attachments);
       /* for (String reportFile : attachments) {
            msg.getAttachments().addFileAttachment(reportFile);
        }*/
        //msg.setBody(MessageBody.getMessageBodyFromText(text));
        for (String recipient : recipients) {
            msg.getToRecipients().add(recipient);
        }
        msg.send();
        LOG.info("Report has been sent");
    }

}


