package com.xfmeet.websocket.listener.event;

import io.netty.channel.Channel;
import org.springframework.context.ApplicationEvent;

/**
 * @author meetzy
 * @date 2019-04-15 15:36
 */
public class LoginInEvent<T> extends ApplicationEvent {
    
    private Channel channel;
    
    public LoginInEvent(Object source) {
        super(source);
    }
    
    public LoginInEvent(Object source, Channel channel) {
        super(source);
        this.channel = channel;
    }
    
    @SuppressWarnings("unchecked")
    public T getValue() {
        return (T) this.source;
    }
    
    public Channel getChannel() {
        return this.channel;
    }
    
}
