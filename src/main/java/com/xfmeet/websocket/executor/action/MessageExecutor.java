package com.xfmeet.websocket.executor.action;

import com.alibaba.fastjson.JSON;
import com.xfmeet.websocket.db.service.GroupService;
import com.xfmeet.websocket.db.service.MessageService;
import com.xfmeet.websocket.executor.ActionJobExecutor;
import com.xfmeet.websocket.executor.task.GroupMessageTask;
import com.xfmeet.websocket.netty.listener.MessageFailedListener;
import com.xfmeet.websocket.netty.model.MessageInfo;
import com.xfmeet.websocket.netty.model.MsgBody;
import com.xfmeet.websocket.netty.utils.ChannelManager;
import com.xfmeet.websocket.netty.utils.SendUtils;
import com.xfmeet.websocket.utils.ActionConstants;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author meetzy
 * @date 2019-04-12 11:26
 */
@Slf4j
@Component
public class MessageExecutor implements ActionExecutor {
    
    
    @Autowired
    private MessageService messageService;
    
    @Autowired
    private GroupService groupService;
    
    private final static Integer SINGLE = 1;
    
    private final static Integer GROUP = 2;
    
    @Override
    public void executor(MsgBody t, Channel channel) {
        
        if (ActionConstants.COMMON.equals(t.getAction())) {
            if (!ChannelManager.getInstance().checkBind(channel)) {
                SendUtils.sendError(channel);
            }
            if (t.getAction().equals(ActionConstants.COMMON)) {
                MessageInfo message = JSON.parseObject(t.getData(), MessageInfo.class);
                if (message.getMessageType().equals(SINGLE)) {
                    executorSingle(message);
                } else if (message.getMessageType().equals(GROUP)) {
                    executorGroup(message);
                } else {
                    log.warn("不支持的消息类型");
                }
            }
        }
        
        // 暂时不使用 TypeReference
        // JSON.parseObject(",",new TypeReference<MsgBody<MessageInfo>>(){});
        
    }
    
    @SuppressWarnings("unchecked")
    private void executorSingle(MessageInfo info) {
        String toUserId = info.getToUser();
        Channel toUserChannel = ChannelManager.getInstance().getChannelById(toUserId);
        if (toUserChannel == null) {
            log.info("{}--用户不在线.", toUserId);
            // todo 记录聊天记录 未阅读
        } else {
            //增加失败监听
            SendUtils.send(new MsgBody(1, JSON.toJSONString(info)), toUserChannel)
                    .addListener(new MessageFailedListener(info, messageService));
            // todo 记录聊天记录 已经阅读
        }
    }
    
    private void executorGroup(MessageInfo info) {
        ActionJobExecutor.FIXED.submit(new GroupMessageTask(info, groupService));
    }
    
    
}

