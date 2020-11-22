package com.liuyun.github.wechat;

import java.util.List;
import lombok.Data;

@Data
public class WeChatProperties {

    /** 接收者列表 */
    private List<String> receivers;

}
