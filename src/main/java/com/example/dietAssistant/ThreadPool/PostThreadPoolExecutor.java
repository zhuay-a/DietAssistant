package com.example.dietAssistant.ThreadPool;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class PostThreadPoolExecutor {

    @Bean(name = "postThreadPool")
    public ThreadPoolExecutor getPostThreadPool() {
        return new ThreadPoolExecutor(
                6,
                12,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(1000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }
}
