package com.xfmeet.websocket.netty.server;

import com.xfmeet.websocket.config.WebSocketConfig;
import com.xfmeet.websocket.netty.handler.CustomerChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author meetzy
 * @date 2019-04-10 14:28
 */
@Component
public class WebSocketServer implements InitializingBean, DisposableBean {
    
    private EventLoopGroup bossGroup;
    
    private EventLoopGroup workGroup;
    
    @Autowired
    private WebSocketConfig webSocketConfig;
    
    @Autowired
    private CustomerChannelInitializer customerChannelInitializer;
    
    public void init() {
        
        bossGroup = new NioEventLoopGroup();
        workGroup = new NioEventLoopGroup();
        
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(customerChannelInitializer);
        
        serverBootstrap.bind(webSocketConfig.getPort());
        
    }
    
    @Override
    public void destroy() throws Exception {
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        if (workGroup != null) {
            workGroup.shutdownGracefully();
        }
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
}
