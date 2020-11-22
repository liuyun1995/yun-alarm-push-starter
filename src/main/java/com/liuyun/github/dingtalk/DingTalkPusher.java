package com.liuyun.github.dingtalk;

import com.liuyun.github.utils.ErrorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DingTalkPusher {

    private DingTalkProperties dingTalkProperties;

    public DingTalkPusher(DingTalkProperties dingTalkProperties) {
        this.dingTalkProperties = dingTalkProperties;
    }

    public void push(ErrorContext errorContext) {
        if(dingTalkProperties == null) { return; }
        System.out.println(String.format("推送钉钉消息 %s", errorContext.toString()));
    }

}
