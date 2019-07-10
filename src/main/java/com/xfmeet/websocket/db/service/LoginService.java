package com.xfmeet.websocket.db.service;

import com.xfmeet.websocket.db.domain.ChatMessageRepository;
import com.xfmeet.websocket.db.domain.UserInfoRepository;
import com.xfmeet.websocket.db.domain.UserLoginInfoRepository;
import com.xfmeet.websocket.db.entity.ChatMessage;
import com.xfmeet.websocket.db.entity.UserInfo;
import com.xfmeet.websocket.db.entity.UserLoginInfo;
import com.xfmeet.websocket.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author meetzy
 */
@Service
public class LoginService {
    
    private final UserLoginInfoRepository userLoginInfoRepository;
    
    private final UserInfoRepository userInfoRepository;
    
    private final ChatMessageRepository chatMessageRepository;
    
    @Autowired
    public LoginService(UserLoginInfoRepository userLoginInfoRepository, UserInfoRepository userInfoRepository, ChatMessageRepository chatMessageRepository) {
        this.userLoginInfoRepository = userLoginInfoRepository;
        this.userInfoRepository = userInfoRepository;
        this.chatMessageRepository = chatMessageRepository;
    }
    
    
    public void login(String userId) throws UserNotFoundException {
        
        // 判断用户信息是否存在
        UserInfo userInfo = userInfoRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException("用户不存在！"));
        
        UserLoginInfo loginInfo = new UserLoginInfo();
        loginInfo.setUserId(userInfo.getUserId());
        loginInfo.setNickName(userInfo.getNickName());
        loginInfo.setIsOnline(1);
        userLoginInfoRepository.save(new UserLoginInfo());
        
    }
    
    public List<ChatMessage> notReadMessage(String userId) {
        return chatMessageRepository.findByToUserIdAndIsRead(userId, (short) 1);
    }
}
