package com.xfmeet.websocket.executor.action;

import com.xfmeet.websocket.netty.model.MsgBody;
import com.xfmeet.websocket.utils.ActionConstants;
import com.xfmeet.websocket.netty.utils.ChannelManager;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

/**
 * @author meetzy
 * @date 2019-04-15 15:02
 */
@Component
public class LoginOutExecutor implements ActionExecutor {
    
    @Override
    public void executor(MsgBody t, Channel channel) {
        if (t.getAction().equals(ActionConstants.LOGIN_OUT)) {
            ChannelManager.getInstance().removeClient(channel);
        }
    }
}
