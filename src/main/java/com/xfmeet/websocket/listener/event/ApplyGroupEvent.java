package com.xfmeet.websocket.listener.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author meetzy
 */
public class ApplyGroupEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public ApplyGroupEvent(Object source) {
        super(source);
    }
}
