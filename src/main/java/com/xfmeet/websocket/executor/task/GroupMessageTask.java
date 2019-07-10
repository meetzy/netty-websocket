package com.xfmeet.websocket.executor.task;

import com.alibaba.fastjson.JSON;
import com.xfmeet.websocket.db.entity.GroupUserInfo;
import com.xfmeet.websocket.db.service.GroupService;
import com.xfmeet.websocket.netty.model.MessageInfo;
import com.xfmeet.websocket.netty.model.MsgBody;
import com.xfmeet.websocket.netty.utils.ChannelManager;
import com.xfmeet.websocket.netty.utils.SendUtils;
import io.netty.channel.Channel;

import java.util.List;

/**
 * 消息任务
 *
 * @author meetzy
 */
public class GroupMessageTask implements Runnable {
    
    private MessageInfo info;
    
    private GroupService groupService;
    
    public GroupMessageTask(MessageInfo info, GroupService groupService) {
        this.info = info;
        this.groupService = groupService;
    }
    
    @Override
    public void run() {
        List<GroupUserInfo> onlineUsers = groupService.getOnlineUsersByGroupNum(info);
        for (GroupUserInfo onlineUser : onlineUsers) {
            Channel channel = ChannelManager.getInstance().getChannelById(onlineUser.getUserId());
            if (channel != null) {
                SendUtils.send(new MsgBody(1, JSON.toJSONString(info)), channel);
            }
        }
    }
}