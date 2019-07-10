package com.xfmeet.websocket.db.entity;

import com.xfmeet.websocket.db.support.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * 用户登陆基本信息
 *
 * @author meetzy
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class UserLoginInfo extends BaseEntity {
    
    @Column(length = 30)
    private String userId;
    
    @Column(length = 30)
    private String nickName;
    
    private LocalDateTime endTime;
    
    private Integer isOnline;
    
}
