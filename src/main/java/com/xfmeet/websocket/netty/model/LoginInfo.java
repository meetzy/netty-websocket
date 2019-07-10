package com.xfmeet.websocket.netty.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author meetzy
 * @date 2019-04-12 08:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo {
    
    private String userId;
    
    private String nickName;
    
}

