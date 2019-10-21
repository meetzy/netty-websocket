package com.xfmeet.websocket.listener.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author meetzy
 */
public class LoginOutEvent<T> extends ApplicationEvent {
    
    public LoginOutEvent(Object source) {
        super(source);
    }
    
    @SuppressWarnings("unchecked")
    public T getContext() {
        return (T) this.source;
    }
}
