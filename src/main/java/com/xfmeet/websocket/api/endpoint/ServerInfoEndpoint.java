package com.xfmeet.websocket.api.endpoint;

import com.xfmeet.websocket.api.dto.GroupApplyInfoDTO;
import com.xfmeet.websocket.api.dto.GroupInfoDTO;
import com.xfmeet.websocket.db.service.GroupService;
import com.xfmeet.websocket.exception.CommonException;
import com.xfmeet.websocket.netty.utils.ChannelManager;
import com.xfmeet.websocket.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * 多服务节点 分发？ 还是单独的分发服务器？
 *
 * @author meetzy
 */
@RestController
public class ServerInfoEndpoint {
    
    @Autowired
    private GroupService groupService;
    
    @GetMapping("/clients")
    public Set<String> getClient() {
        return ChannelManager.getInstance().getOnlineClient();
    }
    
    // 创建群组
    
    @PostMapping("/createGroup")
    public Result createGroup(@Validated @RequestBody GroupInfoDTO infoDTO) throws CommonException {
        groupService.createGroup(infoDTO);
        return Result.ok();
    }
    
    // 加入群组
    
    @PostMapping("/joinGroup")
    public Result joinGroup(@Validated @RequestBody GroupApplyInfoDTO infoDTO) throws CommonException {
        groupService.joinGroup(infoDTO);
        return Result.ok();
    }
    
    
}
