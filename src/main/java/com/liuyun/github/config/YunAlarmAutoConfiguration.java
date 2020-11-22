package com.liuyun.github.config;

import com.liuyun.github.AlarmPusher;
import com.liuyun.github.dingtalk.DingTalkPusher;
import com.liuyun.github.email.EmailPusher;
import com.liuyun.github.wechat.WeChatPusher;
import javax.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AlarmProperties.class)
public class YunAlarmAutoConfiguration {

    @Resource
    private AlarmProperties alarmProperties;

    @Bean
    @ConditionalOnMissingBean
    private EmailPusher getEmailPusher() {
        EmailPusher emailPusher = new EmailPusher();
        emailPusher.setEmailProperties(alarmProperties.getEmail());
        return emailPusher;
    }

    @Bean
    @ConditionalOnMissingBean
    private DingTalkPusher getDingTalkPusher() {
        DingTalkPusher dingTalkPusher = new DingTalkPusher();
        dingTalkPusher.setDingTalkProperties(alarmProperties.getDingTalk());
        return dingTalkPusher;
    }

    @Bean
    @ConditionalOnMissingBean
    private WeChatPusher getWeChatPusher() {
        WeChatPusher weChatPusher = new WeChatPusher();
        weChatPusher.setWeChatProperties(alarmProperties.getWeChat());
        return weChatPusher;
    }

    @Bean
    @ConditionalOnMissingBean
    private AlarmPusher getAlarmPusher() {
        AlarmPusher alarmPusher = new AlarmPusher();
        alarmPusher.setEmailPusher(getEmailPusher());
        alarmPusher.setDingTalkPusher(getDingTalkPusher());
        alarmPusher.setWeChatPusher(getWeChatPusher());
        alarmPusher.setAlarmProperties(alarmProperties);
        return alarmPusher;
    }

}
