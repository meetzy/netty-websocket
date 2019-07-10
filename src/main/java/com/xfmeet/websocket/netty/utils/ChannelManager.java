package com.xfmeet.websocket.netty.utils;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务channel 管理 后续可能拓展redis
 *
 * @author meetzy
 * @date 2019-04-11 17:48
 */
public class ChannelManager {
    
    private final static AttributeKey<String> ATTRIBUTE_KEY = AttributeKey.valueOf(ChannelManager.class.getSimpleName());
    
    private static ChannelManager channelManager;
    
    private Map<String, Channel> channelInfo;
    
    private ChannelGroup channelGroup;
    
    
    private ChannelManager() {
        channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        channelInfo = new ConcurrentHashMap<>();
    }
    
    public static ChannelManager getInstance() {
        if (channelManager == null) {
            synchronized (ChannelManager.class) {
                if (channelManager == null) {
                    channelManager = new ChannelManager();
                }
            }
        }
        return channelManager;
    }
    
    public void addClient(String id, Channel channel) {
        if (channelInfo.keySet().contains(id)) {
            return;
        }
        channelInfo.put(id, channel);
        Optional.of(channelGroup.find(channel.id())).get().attr(ATTRIBUTE_KEY).set(id);
    }
    
    public void removeClient(Channel channel) {
        // 直接关闭channel 会触发自定义handler的handlerRemoved方法 在handlerRemoved方法里处理掉用户信息
        channel.close();
    }
    
    public boolean checkBind(Channel channel) {
        return channelInfo.values().contains(channel);
    }
    
    public void addChannel(Channel channel) {
        channelGroup.add(channel);
    }
    
    public void removeChannel(Channel channel) {
        channelGroup.remove(channel);
        if (channel.attr(ATTRIBUTE_KEY).get() != null) {
            channelInfo.remove(channel.attr(ATTRIBUTE_KEY).get());
        }
    }
    
    public Channel getChannelById(String id) {
        return channelInfo.get(id);
    }
    
    public Set<String> getOnlineClient() {
        return this.channelInfo.keySet();
    }
    
    
    public String getAttr(Channel channel) {
        return channel.attr(ATTRIBUTE_KEY).get();
    }
}
