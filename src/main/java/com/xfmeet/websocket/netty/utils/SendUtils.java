package com.xfmeet.websocket.netty.utils;

import com.xfmeet.websocket.netty.model.MsgBody;
import com.xfmeet.websocket.utils.ActionConstants;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @author meetzy
 * @date 2019-04-12 09:38
 */
public class SendUtils {
    
    public static ChannelFuture send(MsgBody msgBody, Channel channel) {
        return channel.writeAndFlush(new TextWebSocketFrame(MsgBody.toJsonString(msgBody)));
    }
    
    public static void sendHeart(Channel channel) {
        send(new MsgBody(ActionConstants.HEART, null), channel);
    }
    
    
    public static void sendError(Channel channel) {
        send(new MsgBody(ActionConstants.ERROR, null), channel);
    }
    
}
