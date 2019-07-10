package com.xfmeet.websocket.executor.action;

import com.alibaba.fastjson.JSON;
import com.xfmeet.websocket.netty.model.MsgBody;
import io.netty.channel.Channel;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author meetzy
 * @date 2019-04-12 11:30
 */
@Component
public class ActionHandler implements ApplicationContextAware {
    
    private List<ActionExecutor> actionExecutors;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        actionExecutors = new ArrayList<>(applicationContext.getBeansOfType(ActionExecutor.class).values());
    }
    
    /**
     * 收到来自channel的消息 触发回调
     *
     * @param msg     消息内容
     * @param channel 所属channel
     */
    public void call(String msg, Channel channel) {
        MsgBody msgBody = JSON.parseObject(msg, MsgBody.class);
        for (ActionExecutor actionExecutor : actionExecutors) {
            actionExecutor.executor(msgBody, channel);
        }
    }
    
}
