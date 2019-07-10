package com.xfmeet.websocket.listener;

import com.xfmeet.websocket.db.service.LoginService;
import com.xfmeet.websocket.executor.ActionJobExecutor;
import com.xfmeet.websocket.executor.task.LoginMessageTask;
import com.xfmeet.websocket.listener.event.LoginInEvent;
import com.xfmeet.websocket.listener.event.LoginOutEvent;
import com.xfmeet.websocket.netty.model.LoginInfo;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author meetzy
 * @date 2019-04-12 11:06
 */
@Component
public class ActionListener {
    
    private final LoginService loginService;
    
    @Autowired
    public ActionListener(LoginService loginService) {
        this.loginService = loginService;
    }
    
    @EventListener
    public void loginInAction(LoginInEvent<LoginInfo> loginInEvent) {
        LoginInfo value = loginInEvent.getValue();
        Channel channel = loginInEvent.getChannel();
        ActionJobExecutor.FIXED.submit(new LoginMessageTask(channel, loginService, value.getUserId()));
    }
    
    @EventListener
    public void loginOutAction(LoginOutEvent<String> outEvent) {
        // todo 记录下线信息
    }
    
    
}
