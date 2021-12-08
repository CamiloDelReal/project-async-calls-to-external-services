package com.example.consumerservice.services.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfiguration {

    @Bean(name = "customerAsyncExecutor")
    public Executor asyncExecutorForCustomerData() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(3);
        pool.setMaxPoolSize(3);
        pool.setQueueCapacity(500);
        pool.setThreadNamePrefix("AsyncExecutor-");
        pool.initialize();
        return pool;
    }

}
