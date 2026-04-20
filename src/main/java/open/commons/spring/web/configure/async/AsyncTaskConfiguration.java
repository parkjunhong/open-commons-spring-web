/*
 * Copyright 2025 Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 *
 * This file is generated under this project, "open-commons-spring-web".
 *
 * Date  : 2025. 7. 30. 오후 5:13:29
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.configure.async;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import open.commons.spring.web.async.MdcTaskDecorator;
import open.commons.spring.web.configure.ResourceConfiguration;
import open.commons.spring.web.resources.ThreadPoolTaskExecutorConfig;

/**
 * {@link Async} 어노테이션이 적용된 메소드가 실행될 때 기본값으로 사용되는 {@link Executor}를 제공하는 클래스.
 * 
 * @since 2025. 7. 30.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Configuration
@EnableConfigurationProperties({ VirtualAsyncTaskExeuctorProperties.class, ThreadPoolTaskExecutorConfig.class })
public class AsyncTaskConfiguration implements AsyncConfigurer {

    public static final String PLATFORM_THREAD_ASYNC_TASK_EXECUTOR = "open.commons.spring.web.configure.async.AsyncTaskConfiguration#PLATFORM_THREAD_ASYNC_TASK_EXECUTOR";
    public static final String VIRTUAL_THREAD_ASYNC_TASK_EXECUTEOR = "open.commons.spring.web.configure.async.AsyncTaskConfiguration#VIRTUAL_THREAD_ASYNC_TASK_EXECUTEOR";

    private final Logger logger = LoggerFactory.getLogger(AsyncTaskConfiguration.class);
    /** 'virtual thread' 기반 {@link SimpleAsyncTaskExecutor} 설정 */
    private final VirtualAsyncTaskExeuctorProperties props;
    /** 'platform thread' 기반 {@link ThreadPoolTaskExecutor} 설정 */
    private final ThreadPoolTaskExecutorConfig config;

    /**
     *
     * @param config
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     */
    public AsyncTaskConfiguration(VirtualAsyncTaskExeuctorProperties props, ThreadPoolTaskExecutorConfig config) {
        this.props = props;
        this.config = config;
    }

    /**
     * {@link Async} 어노테이션이 적용된 메소드에 사용할 'platform thread' 기반 {@link Executor}을 제공합니다. <br>
     * 
     * @since 2025. 7. 30.
     * @version 0.8.0
     *
     * @see org.springframework.scheduling.annotation.AsyncConfigurer#getAsyncExecutor()
     */
    @Override
    public Executor getAsyncExecutor() {
        // (C)ontext (S)haring (T)hread

        ThreadPoolTaskExecutor executor = ResourceConfiguration.createThreadPoolTaskExecutor(this.config, "@async");
        executor.initialize();

        logger.info("[async-exeuctor-service] Registered! -> {}", executor);

        return executor;
    }

    /**
     * {@link Async} 어노테이션이 적용된 메소드에 사용할 'virtual thread' 기반 {@link Executor}을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2026. 4. 20.     parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2026. 4. 20.
     * @version 4.0.0
     */
    @Bean(VIRTUAL_THREAD_ASYNC_TASK_EXECUTEOR)
    Executor getVirtualThreadAsyncExecutor() {

        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
        executor.setTaskDecorator(new MdcTaskDecorator("@async"));
        
        executor.setCancelRemainingTasksOnClose(this.props.cancelRemainingTasksOnClose());
        executor.setConcurrencyLimit(this.props.concurrencyLimit());
        executor.setDaemon(this.props.daemon());
        executor.setRejectTasksWhenLimitReached(this.props.rejectTasksWhenLimitReached());
        executor.setTaskTerminationTimeout(this.props.taskTerminationTimeout());
        executor.setThreadGroupName(this.props.threadGroupName());
        executor.setThreadNamePrefix(this.props.threadNamePrefix());
        executor.setThreadPriority(this.props.threadPriority());
        // 설정은 존재하나 이 '빈'에서는 무조건 'virtual thread' 기반 Executor 을 제공
        executor.setVirtualThreads(true); //

        return executor;
    }
}
