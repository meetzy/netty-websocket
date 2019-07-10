package com.xfmeet.websocket.executor.action;

import com.xfmeet.websocket.netty.model.MsgBody;
import io.netty.channel.Channel;

/**
 * @author meetzy
 * @date 2019-04-12 08:33
 */
public interface ActionExecutor {
    
    /**
     * 接收到text文本消息 触发 可添加自定义执行器
     *
     * @param t       @see MsgBody
     * @param channel netty channel
     */
    void executor(MsgBody t, Channel channel);
    
}
