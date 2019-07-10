package com.xfmeet.websocket.executor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author meetzy
 * @date 2019-04-16 09:47
 */
public class ActionJobExecutor {
    
    public static final ScheduledExecutorService SCHEDULED = new ScheduledThreadPoolExecutor(4, new ActionFactory());
    
    /**
     * 考虑使用CPU核心数  未来需要控制阻塞队列的长度 目前使用默认拒绝策略
     */
    public static final ExecutorService FIXED = Executors.newFixedThreadPool(4);
    
    
    static class ActionFactory implements ThreadFactory {
        
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        
        @Override
        public Thread newThread(Runnable r) {
            return new Thread("socket thread" + POOL_NUMBER.getAndIncrement());
        }
        
    }
    
    
}
