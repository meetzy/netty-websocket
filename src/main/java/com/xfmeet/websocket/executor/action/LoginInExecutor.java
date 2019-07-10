package com.xfmeet.websocket.executor.action;

import com.alibaba.fastjson.JSON;
import com.xfmeet.websocket.db.service.LoginService;
import com.xfmeet.websocket.listener.event.LoginInEvent;
import com.xfmeet.websocket.netty.model.LoginInfo;
import com.xfmeet.websocket.netty.model.MsgBody;
import com.xfmeet.websocket.netty.utils.ChannelManager;
import com.xfmeet.websocket.utils.ActionConstants;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


/**
 * @author meetzy
 * @date 2019-04-12 08:45
 */
@Component
public class LoginInExecutor implements ActionExecutor {
    
    private final ApplicationContext applicationContext;
    private final LoginService loginService;
    
    @Autowired
    public LoginInExecutor(ApplicationContext applicationContext, LoginService loginService) {
        this.applicationContext = applicationContext;
        this.loginService = loginService;
    }
    
    /**
     * 执行器只负责鉴权 管理 不负责推送历史消息等
     *
     * @param t       MsgBody
     * @param channel netty channel
     * @see LoginInEvent 处理
     */
    @Override
    public void executor(MsgBody t, Channel channel) {
        if (t.getAction().equals(ActionConstants.LOGIN)) {
            LoginInfo loginInfo = JSON.parseObject(t.getData(), LoginInfo.class);
            // todo 不应该阻塞检查  后续需要更改
            if (!checkLogin(loginInfo)) {
                return;
            }
            ChannelManager.getInstance().addClient(loginInfo.getUserId(), channel);
            applicationContext.publishEvent(new LoginInEvent<LoginInfo>(loginInfo, channel));
        }
    }
    
    
    private boolean checkLogin(LoginInfo loginInfo) {
        try {
            loginService.login(loginInfo.getUserId());
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
}
