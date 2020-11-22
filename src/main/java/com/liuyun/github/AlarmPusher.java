package com.liuyun.github;

import com.liuyun.github.config.AlarmService;
import com.liuyun.github.utils.ErrorContext;
import com.liuyun.github.utils.SpringBeanUtils;

public class AlarmPusher {

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
