package com.tomosia.miniappintegration.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


/**
 * Cấu hình thread pool executor cho các tác vụ xử lý bất đồng bộ (@Async).
 * Dành riêng cho các tác vụ gửi dữ liệu (ví dụ: gửi message lên LINE, gọi API, v.v...).
 *
 * @author namhoang.tms
 */
@EnableAsync
@Configuration
public class AsyncConfig {

    @Value("${spring.threads.pool-core-size}")
    private int POOL_CORE_SIZE;

    @Value("${spring.threads.max-pool-size}")
    private int MAX_POOL_SIZE;

    @Value("${spring.threads.queue-capacity}")
    private int QUEUE_CAPACITY;

    @Value("${spring.threads.name-prefix}")
    private String PREFIX;


    /**
     * Mặc định được Spring @Async sử dụng nếu không chỉ định.
     */
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(POOL_CORE_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setThreadNamePrefix(PREFIX);
        executor.initialize();
        return executor;
    }
}

