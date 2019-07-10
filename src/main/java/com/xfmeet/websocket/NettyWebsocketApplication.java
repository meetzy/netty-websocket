package com.xfmeet.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author meetzy
 */
@EnableJpaAuditing
@SpringBootApplication
public class NettyWebsocketApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(NettyWebsocketApplication.class, args);
    }
    
}
