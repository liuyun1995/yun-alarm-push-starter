package com.liuyun.github.wechat;

import com.alibaba.fastjson.JSON;
import com.liuyun.github.utils.ErrorContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class WeChatPusher {

    private WeChatProperties weChatProperties;

    public void push(ErrorContext errorContext) {
        System.out.println(String.format("推送微信消息", JSON.toJSONString(weChatProperties)));
    }

}
