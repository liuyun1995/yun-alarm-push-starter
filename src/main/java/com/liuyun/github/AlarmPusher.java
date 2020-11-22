package com.liuyun.github;

import com.liuyun.github.config.AlarmProperties;
import com.liuyun.github.dingtalk.DingTalkPusher;
import com.liuyun.github.email.EmailPusher;
import com.liuyun.github.utils.ErrorContext;
import com.liuyun.github.wechat.WeChatPusher;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Slf4j
public class AlarmPusher {

    @Autowired
    private EmailPusher emailPusher;
    @Autowired
    private DingTalkPusher dingTalkPusher;
    @Autowired
    private WeChatPusher weChatPusher;

    private AlarmProperties alarmProperties;

    /**
     * 推送消息
     * @param errorContext
     */
    public void pushMsg(ErrorContext errorContext) {
        pushEmailMsg(errorContext);
        pushDingTalkMsg(errorContext);
        pushWeChatMsg(errorContext);
    }

    /**
     * 推送邮件消息
     * @param errorContext
     */
    private void pushEmailMsg(ErrorContext errorContext) {
        emailPusher.push(errorContext);
    }

    /**
     * 推送钉钉消息
     * @param errorContext
     */
    private void pushDingTalkMsg(ErrorContext errorContext) {
        dingTalkPusher.push(errorContext);
    }

    /**
     * 推送微信消息
     * @param errorContext
     */
    private void pushWeChatMsg(ErrorContext errorContext) {
        weChatPusher.push(errorContext);
    }

}