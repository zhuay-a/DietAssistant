package com.example.dietAssistant.ThreadPool;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class NutrientThreadPoolExecutor {

    @Bean(name = "nutrientThreadPool")
    public ThreadPoolExecutor getNutrientThreadPool() {
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
