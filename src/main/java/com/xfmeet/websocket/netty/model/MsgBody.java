package com.xfmeet.websocket.netty.model;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 目前采用JSON方式 尚未采用二进制序列化
 *
 * @author meetzy
 * @date 2019-04-11 17:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MsgBody {
    
    /**
     * 动作消息码 心跳 错误等
     */
    private Integer action;
    
    /**
     * 消息
     */
    private String data;
    
    public static String toJsonString(MsgBody msgBody) {
        return JSON.toJSONString(msgBody);
    }
    
}
