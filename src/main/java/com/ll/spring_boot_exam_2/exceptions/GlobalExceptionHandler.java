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
    public ResponseEntity<RsData<Empty>> handleException2(GlobalException ex){
        log.debug("handler Exception start");

        RsData<Empty> rsData = ex.getRsData();

        return ResponseEntity.status(rsData.getStatusCode()).body(rsData);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
//    public ResponseEntity<RsData<Empty>> handleException(MethodArgumentNotValidException ex){
//        String resultCode = "400-VALIDATION_ERROR";
//
//        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
//                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
//                .collect(Collectors.toList());
//
//        String msg = String.join(", ", errors);
//
//        return handleGlobalException(
//                new GlobalException(
//                        resultCode,
//                        msg
//                )
//        );
//
//    }


}
