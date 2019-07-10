package com.xfmeet.websocket.api.advice;

import com.xfmeet.websocket.utils.Result;
import com.xfmeet.websocket.exception.CommonException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author meetzy
 */
@ControllerAdvice
public class ErrorAdvice {
    
    @ExceptionHandler(CommonException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result dataSaveException(CommonException common) {
        return Result.error(common.getMsg());
    }
    
    
}
