package com.liuyun.github.dingtalk;

import com.alibaba.fastjson.JSON;
import com.liuyun.github.utils.ErrorContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class DingTalkPusher {

    private DingTalkProperties dingTalkProperties;

    public void push(ErrorContext errorContext) {
        System.out.println(String.format("推送钉钉消息", JSON.toJSONString(dingTalkProperties)));
    }

}
