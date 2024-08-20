package com.ll.spring_boot_exam_2.exceptions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
    //여기서는 더욱 자세한게 먼저 실행이 되고 포괄적인것은 가장 나중에 걸린다.
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException1(Exception ex){
        log.debug("handle exception1");
        return ex.getMessage();
    }

    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public String handleException2(GlobalException ex){
        log.debug("handle exception2");
        return ex.getMessage();
    }

}
