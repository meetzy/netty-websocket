package com.xfmeet.websocket.netty.handler;

import com.xfmeet.websocket.executor.action.ActionHandler;
import com.xfmeet.websocket.listener.event.LoginOutEvent;
import com.xfmeet.websocket.netty.utils.ChannelManager;
import com.xfmeet.websocket.netty.utils.SendUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author meetzy
 * @date 2019-04-10 14:41
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class SimpleWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    
    @Autowired
    private ActionHandler actionHandler;
    @Autowired
    private ApplicationContext applicationContext;
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame text) throws Exception {
        actionHandler.call(text.text(), ctx.channel());
    }
    
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 只是单纯的建立连接 把Channel记录起来 此时并没有绑定用户信息
        // 在用户绑定信息的时候 ActionExecutor 执行器来添加用户操作
        log.debug("连接建立-{}", ctx.channel().remoteAddress());
        ChannelManager.getInstance().addChannel(ctx.channel());
    }
    
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.debug("断开建立-{}", ctx.channel().remoteAddress());
        String userId = ChannelManager.getInstance().getAttr(ctx.channel());
        if (userId != null) {
            // 通知持久层 记录用户下线时间
            applicationContext.publishEvent(new LoginOutEvent<String>(userId));
        }
        ChannelManager.getInstance().removeChannel(ctx.channel());
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // todo 需要处理 客户端异常断开
        ChannelManager.getInstance().removeChannel(ctx.channel());
        // 打印异常信息
        cause.printStackTrace();
    }
    
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        
        // 客户端需要心跳
        if (IdleStateEvent.class.isAssignableFrom(evt.getClass())) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                ctx.close();
                log.info("close channel->{}", ctx.channel().remoteAddress());
            }
            
            if (event.state() == IdleState.WRITER_IDLE) {
                SendUtils.sendHeart(ctx.channel());
            }
        }
    }
    
}
