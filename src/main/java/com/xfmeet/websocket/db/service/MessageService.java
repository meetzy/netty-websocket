package com.xfmeet.websocket.db.service;

import com.xfmeet.websocket.db.domain.ChatMessageRepository;
import com.xfmeet.websocket.db.entity.ChatMessage;
import com.xfmeet.websocket.netty.model.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author meetzy
 */
@Service
public class MessageService {
    
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    
    public void saveMessage(MessageInfo info) {
        ChatMessage chatMessage = infoToChat(info);
        chatMessageRepository.save(chatMessage);
    }
    
    private ChatMessage infoToChat(MessageInfo info) {
        ChatMessage message = new ChatMessage();
        message.setContext(info.getContent());
        message.setFromUserId(info.getFromUser());
        message.setToUserId(info.getToUser());
        return message;
    }
    
    
}
