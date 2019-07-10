package com.xfmeet.websocket.netty.model;

import lombok.Data;

import java.util.List;

/**
 * @author meetzy
 * @date 2019-04-11 18:27
 */
@Data
public class MessageInfo {
    
    /**
     * 1是单聊 2是群聊 如果是群聊 toUser为群号
     */
    private Integer messageType;
    
    private String fromUser;
    
    private String toUser;
    
    private String content;
    
    private List<String> images;
    
}
