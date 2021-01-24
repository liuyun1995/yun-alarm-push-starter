package com.liuyun.github.config;

import com.google.common.collect.Lists;
import com.liuyun.github.dingtalk.DingTalkPusher;
import com.liuyun.github.email.EmailPusher;
import com.liuyun.github.wechat.WeChatPusher;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

@Configuration
@ConditionalOnProperty(prefix = AlarmProperties.PREFIX, name = "enable", havingValue = "true")
@EnableConfigurationProperties(AlarmProperties.class)
public class YunAlarmAutoConfiguration {

    @Autowired
    private AlarmProperties alarmProperties;
//    @Autowired
//    private MailSender mailSender;

    public EmailPusher getEmailPusher() {
//        return new EmailPusher(alarmProperties.getEmail(), mailSender);
        return null;
    }

    public DingTalkPusher getDingTalkPusher() {
        return new DingTalkPusher(alarmProperties.getDingTalk());
    }

    public WeChatPusher getWeChatPusher() {
        return new WeChatPusher(alarmProperties.getWeChat());
    }

    @Bean
    @ConditionalOnMissingBean
    public AlarmService getAlarmService() {
        List<AlarmPusher> alarmPusherList = Lists.newArrayList();
        AlarmService alarmService = new AlarmService();
        if(alarmProperties.getEmail() != null) {
            alarmPusherList.add(getEmailPusher());
        }
        if(alarmProperties.getDingTalk() != null) {
            alarmPusherList.add(getDingTalkPusher());
        }
        if(alarmProperties.getWeChat() != null) {
            alarmPusherList.add(getWeChatPusher());
        }
        alarmService.setAlarmProperties(alarmProperties);
        alarmService.setAlarmPusherList(alarmPusherList);
        return alarmService;
    }

}
