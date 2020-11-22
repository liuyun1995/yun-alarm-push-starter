package com.liuyun.github.email;

import java.util.List;
import lombok.Data;

@Data
public class EmailProperties {

    /** 是否禁用 */
    private Boolean disable;
    /** 服务器主机 */
    private String serverHost;
    /** 服务器端口 */
    private String serverPort;
    /** 发送者邮箱 */
    private String fromAddress;
    /** 发送者用户名 */
    private String userName;
    /** 发送者密码 */
    private String password;
    /** 接收者列表 */
    private List<String> receivers;

}
