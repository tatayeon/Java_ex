package com.ll.spring_boot_exam_2.exceptions;

import com.ll.spring_boot_exam_2.RsData;
import com.ll.spring_boot_exam_2.dto.Empty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
    //여기서는 더욱 자세한게 먼저 실행이 되고 포괄적인것은 가장 나중에 걸린다.
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public String handleException1(Exception ex){
//        log.debug("handle exception1");
//        return ex.getMessage();
//    }

    @ExceptionHandler(GlobalException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<String> handleException2(GlobalException ex){
        RsData<Empty> rsData = ex.getRsData();

        rsData.getStatusCode();

        log.debug("handle exception2");
        return ResponseEntity.status(rsData.getStatusCode()).body(rsData.getMsg());
    }

}
