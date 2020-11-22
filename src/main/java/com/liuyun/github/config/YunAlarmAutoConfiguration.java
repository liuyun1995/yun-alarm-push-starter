package com.liuyun.github.config;

import com.liuyun.github.AlarmPusher;
import com.liuyun.github.dingtalk.DingTalkPusher;
import com.liuyun.github.email.EmailPusher;
import com.liuyun.github.wechat.WeChatPusher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

@Configuration
@EnableConfigurationProperties(AlarmProperties.class)
public class YunAlarmAutoConfiguration {

    @Autowired
    private AlarmProperties alarmProperties;
    @Autowired
    private MailSender mailSender;

    @Bean
    @ConditionalOnMissingBean
    public EmailPusher getEmailPusher() {
        return new EmailPusher(alarmProperties.getEmail(), mailSender);
    }

    @Bean
    @ConditionalOnMissingBean
    public DingTalkPusher getDingTalkPusher() {
        return new DingTalkPusher(alarmProperties.getDingTalk());
    }

    @Bean
    @ConditionalOnMissingBean
    public WeChatPusher getWeChatPusher() {
        return new WeChatPusher(alarmProperties.getWeChat());
    }

    @Bean
    @ConditionalOnMissingBean
    public AlarmPusher getAlarmPusher() {
        AlarmPusher alarmPusher = new AlarmPusher();
        alarmPusher.setEmailPusher(getEmailPusher());
        alarmPusher.setDingTalkPusher(getDingTalkPusher());
        alarmPusher.setWeChatPusher(getWeChatPusher());
        alarmPusher.setAlarmProperties(alarmProperties);
        return alarmPusher;
    }

}
