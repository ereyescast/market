package com.sys.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.sys.market.entity.Email;

@Service("emailSample")
public class EmailSample {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine; // From Thymeleaf
    
    public void initiateEmailSend(Email email) {
        String processedHTMLTemplate = this.constructHTMLTemplate(email);

        // Start preparing the email
        MimeMessagePreparator preparator = message -> {
             MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED, "UTF-8");
             helper.setFrom("Admin");
             helper.setTo("minimarket.contactenos@gmail.com");
             helper.setSubject(email.getMotivo() +" / Cliente: "+ email.getApellido() +" "+ email.getNombre());
             helper.setText(processedHTMLTemplate, true);
        };

        mailSender.send(preparator); //send the email
    }

  
    // Fills up the HTML file
    private String constructHTMLTemplate(Email email) {
        Context context = new Context();
        context.setVariable("sampleText", email);
        return templateEngine.process("contact", context);
    }
    
 
}
