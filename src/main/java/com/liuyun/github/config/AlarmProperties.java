package com.liuyun.github.config;

import com.liuyun.github.dingtalk.DingTalkProperties;
import com.liuyun.github.email.EmailProperties;
import com.liuyun.github.wechat.WeChatProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Data
@ConfigurationProperties(prefix = AlarmProperties.PREFIX)
public class AlarmProperties {

    public static final String PREFIX = "yun.alarm";

    /** 是否开启 */
    private Boolean enable = false;

    /** 推送周期(毫秒) */
    private long period;

    /** 推送阀值 */
    private int threshold;

    /** 邮件配置信息 */
    @NestedConfigurationProperty
    private EmailProperties email;

    /** 钉钉配置信息 */
    @NestedConfigurationProperty
    private DingTalkProperties dingTalk;

    /** 微信配置信息 */
    @NestedConfigurationProperty
    private WeChatProperties weChat;

}
