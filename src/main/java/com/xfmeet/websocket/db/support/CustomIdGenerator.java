package com.xfmeet.websocket.db.support;

import com.xfmeet.websocket.utils.SnowFlakeGenerator;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * Description: 未来用于拓展使用 雪花算法生成Id
 *
 * @author meetzy
 */
public class CustomIdGenerator implements IdentifierGenerator {
    
    private SnowFlakeGenerator snowFlakeGenerator = new SnowFlakeGenerator.Factory().create(1, 1);
    
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return String.valueOf(snowFlakeGenerator.nextId());
    }
    
}
