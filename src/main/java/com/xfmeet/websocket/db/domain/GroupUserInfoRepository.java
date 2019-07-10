package com.xfmeet.websocket.db.domain;

import com.xfmeet.websocket.db.entity.GroupUserInfo;
import com.xfmeet.websocket.db.support.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author meetzy
 */
@Repository
public interface GroupUserInfoRepository extends BaseRepository<GroupUserInfo> {
    
    /**
     * 通过群号查询在线用户
     *
     * @param groupNum groupNum
     * @return list
     */
    @Query(value = "select g.* from group_user_info g left user_info u on g.user_id= u.user_id where g.group_num=?1 and u.is_online=1", nativeQuery = true)
    List<GroupUserInfo> findOnlineUsers(Integer groupNum);
    
    /**
     * 通过群号跟用户ID查询是否加入某群
     *
     * @param groupNum groupNum
     * @param userId   userId
     * @return optional
     */
    Optional<GroupUserInfo> findByGroupNumAndUserId(Integer groupNum, String userId);
}
