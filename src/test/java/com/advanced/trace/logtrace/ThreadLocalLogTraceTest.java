package com.advanced.trace.logtrace;

import com.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThreadLocalLogTraceTest {

    ThreadLocalLogTrace trace = new ThreadLocalLogTrace();

    /**
     * 23:35:32.249 [Test worker] INFO com.advanced.trace.logtrace.ThreadLocalLogTrace - [c37b5447] hello1
     * 23:35:32.255 [Test worker] INFO com.advanced.trace.logtrace.ThreadLocalLogTrace - [c37b5447] |-->hello2
     * 23:35:32.255 [Test worker] INFO com.advanced.trace.logtrace.ThreadLocalLogTrace - [c37b5447] |<--hello2 time= 0ms
     * 23:35:32.255 [Test worker] INFO com.advanced.trace.logtrace.ThreadLocalLogTrace - [c37b5447] hello1 time= 8ms
     * 23:35:32.269 [Test worker] INFO com.advanced.trace.logtrace.ThreadLocalLogTrace - [9f4598cb] hello1
     * 23:35:32.270 [Test worker] INFO com.advanced.trace.logtrace.ThreadLocalLogTrace - [9f4598cb] |-->hello2
     * 23:35:32.270 [Test worker] INFO com.advanced.trace.logtrace.ThreadLocalLogTrace - [9f4598cb] |<X-hello2 time= 0ms ex=java.lang.IllegalStateException
     * 23:35:32.270 [Test worker] INFO com.advanced.trace.logtrace.ThreadLocalLogTrace - [9f4598cb] hello1 time= 1ms ex=java.lang.IllegalStateException
     */
    @Test
    void begin_end_level2(){
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception_level2(){
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }
}