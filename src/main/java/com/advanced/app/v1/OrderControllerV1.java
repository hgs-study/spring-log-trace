package com.advanced.app.v1;

import com.advanced.trace.TraceStatus;
import com.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {
    private final OrderServiceV1 orderService;
    private final HelloTraceV1 trace;

    // v1
    // - OrderController.request(); 수작업
    // - try, catch문 추가해야하는 번거로움
    @GetMapping("/v1/request")
    public String request(String itemId){

        TraceStatus status = null;
        try {
            status = this.trace.begin("OrderController.request();");
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";
        }catch (Exception e){
            trace.exception(status,e);
            throw e;
        }
    }
}
