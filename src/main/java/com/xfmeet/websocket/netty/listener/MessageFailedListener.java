package com.xfmeet.websocket.netty.listener;

import com.xfmeet.websocket.db.service.MessageService;
import com.xfmeet.websocket.netty.model.MessageInfo;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author meetzy
 */
public class MessageFailedListener implements GenericFutureListener {
    
    private MessageInfo info;
    
    private MessageService messageService;
    
    public MessageFailedListener(MessageInfo info, MessageService messageService) {
        this.info = info;
        this.messageService = messageService;
    }
    
    @Override
    public void operationComplete(Future future) throws Exception {
        if (!future.isSuccess()) {
        
        }
        
    }
}
