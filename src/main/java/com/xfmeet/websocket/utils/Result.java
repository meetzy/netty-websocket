package com.xfmeet.websocket.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

/**
 * @author meetzy
 */
@Data
@Builder
public class Result {
    
    private Integer code;
    
    @JsonInclude(Include.NON_NULL)
    private Object data;
    
    @JsonInclude(Include.NON_NULL)
    private String msg;
    
    public static Result ok(Object data) {
        return Result.builder().code(200).data(data).msg("success!").build();
    }
    
    public static Result ok() {
        return Result.builder().code(200).data(null).msg("success!").build();
    }
    
    public static Result error(String msg) {
        return Result.builder().code(500).data(null).msg(msg).build();
    }
    
    public static Result error() {
        return Result.builder().code(500).data(null).msg("error!").build();
    }
    
}
