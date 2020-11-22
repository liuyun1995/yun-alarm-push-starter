package com.liuyun.github.config;

import com.liuyun.github.utils.ErrorContext;
import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class AlarmService {

    private AlarmProperties alarmProperties;
    private List<AlarmPusher> alarmPusherList;

    /**
     * 推送消息
     * @param errorContext
     */
    public void pushMsg(ErrorContext errorContext) {
        for (AlarmPusher alarmPusher : alarmPusherList) {
            alarmPusher.push(errorContext);
        }
    }

}
