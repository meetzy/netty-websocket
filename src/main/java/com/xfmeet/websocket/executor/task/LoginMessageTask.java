package com.xfmeet.websocket.executor.task;

import com.xfmeet.websocket.db.entity.ChatMessage;
import com.xfmeet.websocket.db.service.LoginService;
import io.netty.channel.Channel;

import java.util.List;

/**
 * @author meetzy
 */
public class LoginMessageTask implements Runnable {
    
    private Channel channel;
    
    private LoginService loginService;
    
    private String userId;
    
    public LoginMessageTask(Channel channel, LoginService loginService, String userId) {
        this.channel = channel;
        this.loginService = loginService;
        this.userId = userId;
    }
    
    /**
     * 暂时没有使用
     */
    @Override
    public void run() {
        // 如果历史消息过多 需要分开查询
        List<ChatMessage> chatMessages = loginService.notReadMessage(userId);
        if (chatMessages.size() > 0) {
            for (ChatMessage chatMessage : chatMessages) {
                //todo 发送 更新已读
                //todo 如果发送过程中 发送方channel断开连接 需要取消任务线程
                
            }
        }
    }
}