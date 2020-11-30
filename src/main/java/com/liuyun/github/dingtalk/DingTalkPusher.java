package com.liuyun.github.dingtalk;

import com.liuyun.github.config.AlarmPusher;
import com.liuyun.github.utils.ErrorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class DingTalkPusher implements AlarmPusher {

    private DingTalkProperties dingTalkProperties;

    public DingTalkPusher(DingTalkProperties dingTalkProperties) {
        this.dingTalkProperties = dingTalkProperties;
    }

    @Override
    public void push(ErrorContext errorContext) {
        if(dingTalkProperties == null) { return; }

        System.out.println(String.format("推送钉钉消息 %s", errorContext.toString()));
    }

}
