package com.liuyun.github.utils;

import com.liuyun.github.config.AlarmService;

public class AlarmHelper {

    private static AlarmService alarmService;

    private static AlarmService getAlarmService() {
        if(alarmService == null) {
            alarmService = SpringBeanUtils.getBean(AlarmService.class);
        }
        return alarmService;
    }

    public static void pushMsg(ErrorContext errorContext) {
        AlarmService alarmService = getAlarmService();
        if(alarmService == null) { return; }
        alarmService.pushMsg(errorContext);
    }

}
