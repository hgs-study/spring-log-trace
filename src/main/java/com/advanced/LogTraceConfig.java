package com.advanced;

import com.advanced.trace.logtrace.FieldLogTrace;
import com.advanced.trace.logtrace.LogTrace;
import com.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    // 구현체만 바꿈으로써 이점:
    // 의존관계를 주입하는데에 클라이언트 코드를 바꾸지 않고 OCP를 지키면서 바꿀 수 있다
    @Bean
    public LogTrace logTrace(){
        return new ThreadLocalLogTrace();
    }
}
