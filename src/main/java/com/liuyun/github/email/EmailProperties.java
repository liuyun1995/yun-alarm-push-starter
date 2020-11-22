package com.liuyun.github.email;

import lombok.Data;

@Data
public class EmailProperties {

    /** 发送者邮箱 */
    private String from;
    /** 接收者列表 */
    private String[] to;
    /** 抄送人列表 */
    private String[] cc;

}
