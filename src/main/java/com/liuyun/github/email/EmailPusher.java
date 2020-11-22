package com.liuyun.github.email;

import com.liuyun.github.config.AlarmPusher;
import com.liuyun.github.utils.ErrorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@Slf4j
public class EmailPusher implements AlarmPusher {

    private EmailProperties emailProperties;
    private MailSender mailSender;

    public EmailPusher(EmailProperties emailProperties, MailSender mailSender) {
        this.emailProperties = emailProperties;
        this.mailSender = mailSender;
    }

    @Override
    public void push(ErrorContext errorContext) {
        if(emailProperties == null) { return; }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailProperties.getFrom());
        message.setTo(emailProperties.getTo());
        message.setSubject("告警邮件");
        message.setText(errorContext.toString());
        mailSender.send(message);
    }

}
