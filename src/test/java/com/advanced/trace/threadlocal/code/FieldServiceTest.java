package com.advanced.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {
    private FieldService fieldService = new FieldService();

    /**
     * 00:44:56.812 [thread-A] INFO com.advanced.trace.threadlocal.code.FieldService - 저장 name = userA -> nameStore=null
     * 00:44:56.917 [thread-B] INFO com.advanced.trace.threadlocal.code.FieldService - 저장 name = userB -> nameStore=userA
     * 00:44:57.819 [thread-A] INFO com.advanced.trace.threadlocal.code.FieldService - 조회 nameStoreuserB
     * 00:44:57.926 [thread-B] INFO com.advanced.trace.threadlocal.code.FieldService - 조회 nameStoreuserB
     */
    @Test
    void field(){
        log.info("main start");
        Thread threadA = new Thread(() -> fieldService.logic("userA"));
        threadA.setName("thread-A");
        Thread threadB = new Thread(() -> fieldService.logic("userB"));
        threadB.setName("thread-B");

        threadA.start();
//        sleep(2000);
        sleep(100);
        threadB.start();
        sleep(2000); // 메인 쓰레드 종료 대기
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
