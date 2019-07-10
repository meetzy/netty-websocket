package com.xfmeet.websocket.db.entity;

import com.xfmeet.websocket.db.support.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Formula;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 用户基本信息
 *
 * @author meetzy
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class UserInfo extends BaseEntity {
    
    @Column(length = 30)
    private String userId;
    
    @Column(length = 30)
    private String nickName;
    
    @Column(length = 4)
    private Integer maxGroupNum;
    
    @Formula("(select count(id) form group_info g where g.owner_id = user_id )")
    private Integer nowGroupCount;
    
}
