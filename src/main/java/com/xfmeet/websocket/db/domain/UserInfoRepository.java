package com.xfmeet.websocket.db.domain;

import com.xfmeet.websocket.db.entity.UserInfo;
import com.xfmeet.websocket.db.support.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author meetzy
 */
@Repository
public interface UserInfoRepository extends BaseRepository<UserInfo> {
    
    /**
     * 通过userId查询
     *
     * @param userId 用户id
     * @return option
     */
    Optional<UserInfo> findByUserId(String userId);
}
