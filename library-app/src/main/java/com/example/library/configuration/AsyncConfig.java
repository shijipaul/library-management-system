package com.example.library.configuration;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

//@Configuration
//@EnableAsync
public class AsyncConfig implements AsyncConfigurer{

	@Value("${async.audit.core-pool-size}")
	private String corePoolSize;
	
	@Value("${async.audit.max-ppol-size}")
	private String maxPoolSize;
	
	@Value("${async.audit.que-capacity}")
	private String queueCapacity;
	
	@Override
	@Bean(name = "auditTaskExecutor")
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor =new ThreadPoolTaskExecutor();
		executor.setMaxPoolSize(Integer.parseInt(corePoolSize));
		executor.setCorePoolSize(Integer.parseInt(corePoolSize));
		executor.setQueueCapacity(Integer.parseInt(queueCapacity));
		executor.setThreadNamePrefix("AsyncAuditThread-");
		executor.initialize();
		return executor;
	}
	
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return(ex,method,params)->{
			System.err.println("Async error in method: "+method.getName());
			ex.printStackTrace();
		};
	}

}
