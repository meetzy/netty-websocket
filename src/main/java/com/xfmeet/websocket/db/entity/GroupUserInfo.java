package com.xfmeet.websocket.db.entity;

import com.xfmeet.websocket.db.support.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 群组用户关系
 *
 * @author meetzy
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class GroupUserInfo extends BaseEntity {
    
    private Integer groupNum;
    
    @Column(length = 30)
    private String userId;
    
}
