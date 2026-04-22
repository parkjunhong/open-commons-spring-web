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

package open.commons.spring.web.configure.concurrent.async;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import open.commons.spring.web.async.MdcTaskDecorator;
import open.commons.spring.web.configure.ResourceConfiguration;
import open.commons.spring.web.configure.concurrent.ConcurrentExecutorProperties;
import open.commons.spring.web.configure.concurrent.task.TaskExecutorConfiguration;
import open.commons.spring.web.configure.concurrent.task.TaskExecutorProperties;

/**
 * {@link Async} 어노테이션이 적용된 메소드가 실행될 때 기본값으로 사용되는 {@link Executor}를 제공하는 클래스.
 * 
 * <pre>
 * [개정이력]
 *      날짜       | 작성자                   |   내용
 * -----------------------------------------------------
 * 2026. 4. 21.		parkjunohng77@gmail.com    최초 작성.(기존 {@link ResourceConfiguration}에서 분리)
 * </pre>
 * 
 * @since 2025. 7. 30.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Configuration
public class AsyncTaskExecutorConfiguration implements AsyncConfigurer {

    public static final String BEAN_QUALIFIER_VIRTUAL_THREAD_TASK_EXECUTEOR = "open.commons.spring.web.configure.concurrent.async.AsyncTaskExecutorConfiguration#VIRTUAL_THREAD_TASK_EXECUTEOR";

    private final Logger logger = LoggerFactory.getLogger(AsyncTaskExecutorConfiguration.class);

    /** 'virtual thread' 기반 {@link SimpleAsyncTaskExecutor} 설정 */
    private final VirtualTaskExecuctorProperties virtualProps;
    /** 'platform thread' 기반 {@link ThreadPoolTaskExecutor} 설정 */
    private final TaskExecutorProperties poolProps;

    /**
     * 
     * @param props
     *            모든 병렬 처리 및 비동기 실행기 설정을 통합 관리하는 레코드.
     * 
     * @since 2026. 4. 21.
     * @version 4.0.0
     */
    public AsyncTaskExecutorConfiguration(ConcurrentExecutorProperties props) {
        this.virtualProps = props.virtualTaskExecutor();
        this.poolProps = configureDaemon(props.taskExecutor());
    }

    private TaskExecutorProperties configureDaemon(TaskExecutorProperties props) {
        TaskExecutorProperties propsForAsync = new TaskExecutorProperties(//
                props.corePoolSize() //
                , props.keepAliveSeconds() //
                , props.maxPoolSize() //
                , props.queueCapacity() //
                , props.allowCoreThreadTimeOut() //
                , props.prestartAllCoreThreads() //
                , props.awaitTerminationMillis() //
                , props.beanName() //
                , props.waitForTasksToCompleteOnShutdown() //
                , true // <-- 무조건 'daemon' 설정
                , props.threadGroupName() //
                , props.threadNamePrefix() //
                , props.threadPriority() //
        );
        return propsForAsync;
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

        ThreadPoolTaskExecutor executor = TaskExecutorConfiguration.createTaskExecutor(this.poolProps, "@async");
        executor.initialize();

        logger.info("[platform-thread-async-exeuctor] Registered! -> {}", executor);

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
    @Bean(name = BEAN_QUALIFIER_VIRTUAL_THREAD_TASK_EXECUTEOR)
    Executor virtualThreadAsyncTaskExecutor() {

        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
        executor.setTaskDecorator(new MdcTaskDecorator("virtual"));

        executor.setCancelRemainingTasksOnClose(this.virtualProps.cancelRemainingTasksOnClose());
        executor.setConcurrencyLimit(this.virtualProps.concurrencyLimit());
        executor.setDaemon(this.virtualProps.daemon());
        executor.setRejectTasksWhenLimitReached(this.virtualProps.rejectTasksWhenLimitReached());
        executor.setTaskTerminationTimeout(this.virtualProps.taskTerminationTimeout());
        executor.setThreadGroupName(this.virtualProps.threadGroupName());
        executor.setThreadNamePrefix(this.virtualProps.threadNamePrefix());
        executor.setThreadPriority(this.virtualProps.threadPriority());
        // 설정은 존재하나 이 '빈'에서는 무조건 'virtual thread' 기반 Executor 을 제공
        executor.setVirtualThreads(true); //

        logger.info("[virtual-thread-async-task-exeuctor] Registered! -> {}", executor);

        return executor;
    }
}
