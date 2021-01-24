package com.liuyun.github.wechat;

import com.google.common.collect.Maps;
import com.liuyun.github.config.AlarmPusher;
import com.liuyun.github.utils.ErrorContext;
import com.liuyun.github.utils.HttpUtils;
import com.liuyun.github.utils.TemplateUtils;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WeChatPusher implements AlarmPusher {

    private WeChatProperties weChatProperties;

    public WeChatPusher(WeChatProperties weChatProperties) {
        this.weChatProperties = weChatProperties;
    }

    @Override
    public void push(ErrorContext errorContext) {
        if(weChatProperties == null) { return; }
        String url = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=ebd51a77-77b0-4336-a7b5-6972e56a9f9c";
        String param;
        if("MARKDOWN".equals(weChatProperties.getMsgType())) {
            param = buildMarkdown(errorContext);
        } else {
            param = buildText(errorContext);
        }
        HttpUtils.httpPost().setUrl(url).setBody(param).doPost();
    }

    private String buildText(ErrorContext errorContext) {
        String context = "你好呀";
        List<String> atMobiles = weChatProperties.getReceivers();
        Boolean isAtAll = null;
        Map<String, Object> root = Maps.newHashMap();
        root.put("content", context);
        root.put("atMobiles", atMobiles);
        root.put("isAtAll", isAtAll);
        String param = TemplateUtils.getRenderString("param/wechat/Text.ftl", "Text", root);
        return param;
    }

    private String buildMarkdown(ErrorContext errorContext) {
        String title = "异常告警消息";
        String context = errorContext.toString();
        List<String> atMobiles = weChatProperties.getReceivers();
        Boolean isAtAll = false;
        Map<String, Object> root = Maps.newHashMap();
        root.put("title", title);
        root.put("text", context);
        root.put("atMobiles", atMobiles);
        root.put("isAtAll", isAtAll);
        String param = TemplateUtils.getRenderString("param/wechat/Markdown.ftl", "Markdown", root);
        return param;
    }

}
