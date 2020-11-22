package com.liuyun.github.config;

import com.liuyun.github.utils.ErrorContext;

public interface AlarmPusher {

    void push(ErrorContext errorContext);

}
