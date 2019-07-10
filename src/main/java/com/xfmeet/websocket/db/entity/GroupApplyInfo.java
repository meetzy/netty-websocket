package com.xfmeet.websocket.db.entity;

import com.xfmeet.websocket.db.support.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author meetzy
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class GroupApplyInfo extends BaseEntity {
    
    private Integer groupNum;
    
    @Column(length = 30)
    private String userId;
    
    @Column(length = 30)
    private String userName;
    
    private Integer isJoin;
    
    private Integer isInvitation;
    
    private String invitationId;
    
    private String invitationName;
    
}
