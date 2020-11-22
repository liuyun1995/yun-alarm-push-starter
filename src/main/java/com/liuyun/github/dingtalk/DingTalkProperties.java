package com.liuyun.github.dingtalk;

import java.util.List;
import lombok.Data;

@Data
public class DingTalkProperties {

    /** 回调钩子 */
    private String webhook;

    /** 密钥 */
    private String secret;

    /** 接收者列表 */
    private List<String> receivers;

}
