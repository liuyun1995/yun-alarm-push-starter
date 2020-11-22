package com.liuyun.github.email;

import com.alibaba.fastjson.JSON;
import com.liuyun.github.utils.ErrorContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class EmailPusher {

    private EmailProperties emailProperties;

    public void push(ErrorContext errorContext) {
        System.out.println(String.format("推送邮件消息", JSON.toJSONString(emailProperties)));
    }

}
