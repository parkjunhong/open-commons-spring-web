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
 * Date  : 2025. 8. 1. 오후 4:06:59
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.configure.concurrent.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.SimpleAsyncTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import open.commons.spring.web.async.MdcTaskDecorator;
import open.commons.spring.web.concurrent.DelegatingExecutorSupportor;
import open.commons.spring.web.configure.concurrent.ConcurrentExecutorProperties;
import open.commons.spring.web.configure.concurrent.task.TaskSchedulerConfiguration;
import open.commons.spring.web.configure.concurrent.task.TaskSchedulerProperties;

/**
 * {@link Scheduled} 어노테이션이 적용된 메소드가 실행될 때 기본값으로 사용되는 {@link TaskScheduler}를 설정하는 클래스.
 * 
 * @since 2025. 8. 1.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Configuration
public class ScheduledTaskSchedulerConfiguration implements SchedulingConfigurer {

    /**
     * 'virtual thread'로 동작하는 {@link TaskScheduler} (구현객체는 {@link SimpleAsyncTaskScheduler}) 를
     * 제공합니다.
     */
    public static final String BEAN_QUALIFIER_VIRTUAL_THREAD_TASK_SCHEDULER = "open.commons.spring.web.configure.concurrent.async.ScheduledTaskSchedulerConfiguration#VIRTUAL_THREAD_TASK_SCHEDULER";

    private final Logger logger = LoggerFactory.getLogger(ScheduledTaskSchedulerConfiguration.class);

    /** 'virtual thread' 기반 {@link TaskScheduler} 설정 */
    private final VirtualTaskExecuctorProperties virtualProps;
    /** 'platform thread' 기반 {@link TaskScheduler} 설정 */
    private final TaskSchedulerProperties poolProps;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 8. 1       parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 21.     parkjunohng77@gmail.com     'virtual thread' 기반 {@link TaskScheduler} 지원
     * </pre>
     * 
     * @param props
     *            모든 병렬 처리 및 비동기 실행기 설정을 통합 관리하는 레코드.
     * 
     * @since 2025. 8. 1.
     * @version 0.8.0
     */
    public ScheduledTaskSchedulerConfiguration(ConcurrentExecutorProperties props) {
        this.virtualProps = props.virtualTaskScheduler();
        this.poolProps = props.taskScheduler();
    }

    /**
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     *
     * @see org.springframework.scheduling.annotation.SchedulingConfigurer#configureTasks(org.springframework.scheduling.config.ScheduledTaskRegistrar)
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        // 작업 실행 Executor
        ThreadPoolTaskScheduler delegate = TaskSchedulerConfiguration.createTaskScheduler(this.poolProps);
        delegate.initialize();
        // MDC를 전파할 수 있는 ExecutorService
        TaskScheduler decorator = DelegatingExecutorSupportor.taskScheduler(delegate);
        // 등록
        taskRegistrar.setScheduler(decorator);

        logger.info("[delegating-scheduled-executor-service] Registered! -> {}", decorator);
    }

    @Bean(name = BEAN_QUALIFIER_VIRTUAL_THREAD_TASK_SCHEDULER)
    TaskScheduler virtualThreadTaskScheduler() {

        SimpleAsyncTaskScheduler scheduler = new SimpleAsyncTaskScheduler();
        scheduler.setTaskDecorator(new MdcTaskDecorator("@virtual"));

        scheduler.setCancelRemainingTasksOnClose(this.virtualProps.cancelRemainingTasksOnClose());
        scheduler.setConcurrencyLimit(this.virtualProps.concurrencyLimit());
        scheduler.setDaemon(this.virtualProps.daemon());
        scheduler.setRejectTasksWhenLimitReached(this.virtualProps.rejectTasksWhenLimitReached());
        scheduler.setTaskTerminationTimeout(this.virtualProps.taskTerminationTimeout());
        scheduler.setThreadGroupName(this.virtualProps.threadGroupName());
        scheduler.setThreadNamePrefix(this.virtualProps.threadNamePrefix());
        scheduler.setThreadPriority(this.virtualProps.threadPriority());
        // 설정은 존재하나 이 '빈'에서는 무조건 'virtual thread' 기반 Executor 을 제공
        scheduler.setVirtualThreads(true); //

        logger.info("[virtual-thread-async-task-scheduler] Registered! -> {}", scheduler);

        return scheduler;
    }

}
