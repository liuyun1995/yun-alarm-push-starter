package com.liuyun.github.wechat;

import com.liuyun.github.utils.ErrorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WeChatPusher {

    private WeChatProperties weChatProperties;

    public WeChatPusher(WeChatProperties weChatProperties) {
        this.weChatProperties = weChatProperties;
    }

    public void push(ErrorContext errorContext) {
        if(weChatProperties == null) { return; }
        System.out.println(String.format("推送微信消息 %s", errorContext.toString()));
    }

}
