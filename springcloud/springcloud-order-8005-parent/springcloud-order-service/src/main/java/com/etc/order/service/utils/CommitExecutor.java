package com.etc.order.service.utils;

import com.etc.order.service.constants.Constant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableAsync
public class CommitExecutor {

    //最大的缓存任务数
    private static final int MAXIMUM_CACHED_AMOUNT = 10000;
    //核数
    int processorAmount = Runtime.getRuntime().availableProcessors();
    //核心线程数量
    int coreThreadSize = processorAmount * 2 + 1;
    //最大线程数量
    int maxThreadSize = coreThreadSize + (coreThreadSize / 2);

    @Bean(Constant.SYNC_EXECUTOR)
    public Executor syncExecutor(){
        return new ThreadPoolExecutor(
                coreThreadSize,
                maxThreadSize,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(MAXIMUM_CACHED_AMOUNT),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r,Constant.SYNC_EXECUTOR);
                    }
                },
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }

}
