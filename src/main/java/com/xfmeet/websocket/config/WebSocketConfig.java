package com.xfmeet.websocket.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author meetzy
 * @date 2019-04-11 16:55
 */
@Data
@Component
@ConfigurationProperties("netty.websocket")
public class WebSocketConfig {
    
    /**
     * socket 绑定端口
     */
    private Integer port = 8166;
    
    /**
     * 心跳检测时间 单位S
     */
    private Long checkTime = 60L;
    
    /**
     * 请求路径 例如  ws://127.0.0.1:8166/ws
     */
    private String prefix = "/ws";
    
}
