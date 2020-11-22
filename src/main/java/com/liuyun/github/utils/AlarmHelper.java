package com.liuyun.github.utils;

import com.liuyun.github.config.AlarmService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlarmHelper {

    private static AlarmService alarmService;

    private static AlarmService getAlarmService() {
        try {
            if(alarmService == null) {
                alarmService = SpringBeanUtils.getBean(AlarmService.class);
            }
            return alarmService;
        } catch (Exception e) {
            log.info("告警消息推送服务不存在！");
            return null;
        }
    }

    public static void pushMsg(ErrorContext errorContext) {
        AlarmService alarmService = getAlarmService();
        if(alarmService == null) { return; }
        alarmService.pushMsg(errorContext);
    }

}
