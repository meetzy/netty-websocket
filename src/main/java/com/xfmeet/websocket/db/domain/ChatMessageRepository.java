package com.xfmeet.websocket.db.domain;

import com.xfmeet.websocket.db.entity.ChatMessage;
import com.xfmeet.websocket.db.support.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author meetzy
 */
@Repository
public interface ChatMessageRepository extends BaseRepository<ChatMessage> {
    /**
     * 查询聊天记录
     *
     * @param userId userId
     * @param isRead 是否阅读
     * @return message
     */
    List<ChatMessage> findByToUserIdAndIsRead(String userId, Short isRead);
}
