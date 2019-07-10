package com.xfmeet.websocket.db.service;

import com.xfmeet.websocket.api.dto.GroupApplyInfoDTO;
import com.xfmeet.websocket.api.dto.GroupInfoDTO;
import com.xfmeet.websocket.db.domain.GroupApplyInfoRepository;
import com.xfmeet.websocket.db.domain.GroupInfoRepository;
import com.xfmeet.websocket.db.domain.GroupUserInfoRepository;
import com.xfmeet.websocket.db.domain.UserInfoRepository;
import com.xfmeet.websocket.db.entity.GroupApplyInfo;
import com.xfmeet.websocket.db.entity.GroupInfo;
import com.xfmeet.websocket.db.entity.GroupUserInfo;
import com.xfmeet.websocket.db.entity.UserInfo;
import com.xfmeet.websocket.exception.CommonException;
import com.xfmeet.websocket.netty.model.MessageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author meetzy
 */
@Service
public class GroupService {
    
    @Autowired
    private GroupInfoRepository groupInfoRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private GroupApplyInfoRepository groupApplyInfoRepository;
    @Autowired
    private GroupUserInfoRepository groupUserInfoRepository;
    
    public void createGroup(GroupInfoDTO infoDTO) throws CommonException {
        // 校验创建者信息
        UserInfo userInfo = userInfoRepository.findByUserId(
                infoDTO.getOwnerId()).orElseThrow(() -> new CommonException("用户无效！"));
        if (userInfo.getNowGroupCount() >= userInfo.getMaxGroupNum()) {
            throw new CommonException("无法创建更多的群组");
        }
        GroupInfo info = new GroupInfo();
        BeanUtils.copyProperties(infoDTO, info);
        groupInfoRepository.save(info);
    }
    
    public void joinGroup(GroupApplyInfoDTO infoDTO) throws CommonException {
        // 暂时不真正的审核
        GroupApplyInfo info = new GroupApplyInfo();
        BeanUtils.copyProperties(infoDTO, info);
        groupApplyInfoRepository.save(info);
        
        // 检查是否已经加入该群
        groupUserInfoRepository.findByGroupNumAndUserId(infoDTO.getGroupNum(), info.getUserId())
                .orElseThrow(() -> new CommonException("已经在该群了！"));
        
        // 默认直接通过
        GroupUserInfo userInfo = new GroupUserInfo();
        userInfo.setGroupNum(infoDTO.getGroupNum());
        userInfo.setUserId(infoDTO.getUserId());
        groupUserInfoRepository.save(userInfo);
        
        // todo 如果需要审核 推送到群管理者
    }
    
    
    public List<GroupUserInfo> getOnlineUsersByGroupNum(MessageInfo info) {
        return groupUserInfoRepository.findOnlineUsers(Integer.parseInt(info.getToUser()));
    }
}
