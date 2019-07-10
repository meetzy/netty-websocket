package com.xfmeet.websocket.db.entity;

import com.xfmeet.websocket.db.support.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Formula;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 群组的基本信息
 *
 * @author meetzy
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class GroupInfo extends BaseEntity {
    
    // 群号暂时使用 自增ID主键  该表自增从1000开始 数据库修改
    
    @Column(length = 30, nullable = false)
    private String ownerId;
    
    @Column(length = 30, nullable = false)
    private String ownerName;
    
    @Column(length = 30, nullable = false)
    private String groupName;
    
    /**
     * 描述此群是否可以外人不可以通过任何渠道加入（邀请除外）
     */
    @Column(length = 1, nullable = false)
    private Short canJoin;
    
    @Column(length = 5, nullable = false)
    private Integer maxPeople;
    
    @Column(length = 200)
    private String description;
    
    @Formula("(select count(g.user_id) from group_user_info g where g.group_num = id)")
    private Integer peopleNum;
    
    
}
