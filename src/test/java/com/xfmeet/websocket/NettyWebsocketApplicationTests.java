package com.xfmeet.websocket;

import com.xfmeet.websocket.db.domain.ChatMessageRepository;
import com.xfmeet.websocket.db.entity.ChatMessage;
import com.xfmeet.websocket.listener.event.LoginInEvent;
import com.xfmeet.websocket.netty.model.MsgBody;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NettyWebsocketApplicationTests {
    
    
    @Autowired
    private ApplicationContext applicationContext;
    
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    
    @Test
    public void contextLoads() {
        applicationContext.publishEvent(new LoginInEvent<MsgBody>(new MsgBody(1, null)));
    }
    
    
    @Test
    public void testIdCreate() {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContext("test");
        chatMessage.setFromUserId("1");
        chatMessage.setMessageType(2);
        chatMessage.setToUserId("2");
        chatMessageRepository.save(chatMessage);
    }
    
    
    
}
