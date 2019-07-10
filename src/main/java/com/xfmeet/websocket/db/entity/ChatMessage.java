package com.xfmeet.websocket.db.entity;

import com.xfmeet.websocket.db.support.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 聊天记录主体信息
 *
 * @author meetzy
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class ChatMessage extends BaseEntity {
    
    @Column(length = 30, nullable = false)
    private String fromUserId;
    
    @Column(length = 30, nullable = false)
    private String toUserId;
    
    @Column(length = 30, nullable = false)
    private String context;
    
    @Column(length = 2, nullable = false)
    private Integer messageType;
    
    private Short isRead;
    
}
