package com.xfmeet.websocket.api.dto;

import lombok.Data;

/**
 * @author meetzy
 */
@Data
public class GroupApplyInfoDTO {
    
    private Integer groupNum;
    
    private String userId;
    
    private String userName;
    
    private Integer isInvitation;
    
    private String invitationId;
    
    private String invitationName;
    
}
