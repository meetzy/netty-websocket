package com.xfmeet.websocket.listener.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author meetzy
 */
public class ApplyGroupEvent extends ApplicationEvent {
    
    public ApplyGroupEvent(Object source) {
        super(source);
    }
}
