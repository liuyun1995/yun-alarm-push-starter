package com.liuyun.github;

import com.liuyun.github.config.AlarmProperties;
import com.liuyun.github.config.AlarmPusher;
import com.liuyun.github.config.AlarmService;
import com.liuyun.github.config.YunAlarmAutoConfiguration;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = YunAlarmAutoConfiguration.class)
public class MyTest {

    @Autowired
    AlarmService alarmService;

    @Test
    public void test() {
        AlarmProperties alarmProperties = alarmService.getAlarmProperties();
        List<AlarmPusher> alarmPusherList = alarmService.getAlarmPusherList();
        for (AlarmPusher alarmPusher : alarmPusherList) {
            alarmPusher.push(null);
        }
    }

}
