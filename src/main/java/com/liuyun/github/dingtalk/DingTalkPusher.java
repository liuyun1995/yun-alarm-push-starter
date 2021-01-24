package com.liuyun.github.dingtalk;

import com.google.common.collect.Maps;
import com.liuyun.github.config.AlarmPusher;
import com.liuyun.github.utils.ErrorContext;
import com.liuyun.github.utils.HttpUtils;
import com.liuyun.github.utils.TemplateUtils;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DingTalkPusher implements AlarmPusher {

    private DingTalkProperties dingTalkProperties;

    public DingTalkPusher(DingTalkProperties dingTalkProperties) {
        this.dingTalkProperties = dingTalkProperties;
    }

    @Override
    public void push(ErrorContext errorContext) {
        if(dingTalkProperties == null) { return; }
        String url = getUrl();
        String param;
        if("MARKDOWN".equals(dingTalkProperties.getMsgType())) {
            param = buildMarkdown(errorContext);
        } else {
            param = buildText(errorContext);
        }
        HttpUtils.httpPost().setUrl(url).setBody(param).doPost();
    }

    private String buildText(ErrorContext errorContext) {
        String context = "sdfasgsg";
        List<String> atMobiles = dingTalkProperties.getReceivers();
        Boolean isAtAll = null;
        Map<String, Object> root = Maps.newHashMap();
        root.put("content", context);
        root.put("atMobiles", atMobiles);
        root.put("isAtAll", isAtAll);
        String param = TemplateUtils.getRenderString("param/dingtalk/Text.ftl", "Text", root);
        return param;
    }

    private String buildMarkdown(ErrorContext errorContext) {
        String title = "异常告警消息";
        String context = errorContext.toString();
        List<String> atMobiles = dingTalkProperties.getReceivers();
        Boolean isAtAll = false;
        Map<String, Object> root = Maps.newHashMap();
        root.put("title", title);
        root.put("text", context);
        root.put("atMobiles", atMobiles);
        root.put("isAtAll", isAtAll);
        String param = TemplateUtils.getRenderString("param/dingtalk/Markdown.ftl", "Markdown", root);
        return param;
    }

    private String getUrl() {
        String webhook = dingTalkProperties.getWebhook();
        Long timestamp = System.currentTimeMillis();
        String sign = getSign(timestamp, dingTalkProperties.getSecret());
        return String.format("%s&timestamp=%s&sign=%s", webhook, timestamp, sign);
    }

    private String getSign(Long timestamp, String secret) {
        try {
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
            String sign = URLEncoder.encode(new String(Base64.getEncoder().encode(signData)),"UTF-8");
            return sign;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

}
