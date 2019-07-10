package com.xfmeet.websocket.netty.handler;

import com.xfmeet.websocket.config.WebSocketConfig;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author meetzy
 * @date 2019-04-10 14:42
 */
@Component
public class CustomerChannelInitializer extends ChannelInitializer<SocketChannel> {
    
    @Autowired
    private WebSocketConfig webSocketConfig;
    
    @Autowired
    private SimpleWebSocketHandler simpleWebSocketHandler;
    
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        
        ChannelPipeline pipeline = socketChannel.pipeline();
        
        pipeline.addLast(new IdleStateHandler(webSocketConfig.getCheckTime(),
                webSocketConfig.getCheckTime(), webSocketConfig.getCheckTime(), TimeUnit.SECONDS));
        
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(Integer.MAX_VALUE));
        pipeline.addLast(new WebSocketServerProtocolHandler(webSocketConfig.getPrefix()));
        
        pipeline.addLast(simpleWebSocketHandler);
        
    }
    
}
