/*
 * Copyright 2026 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2026. 4. 20. 오후 8:39:54
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.configure.concurrent.task;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import open.commons.spring.web.configure.concurrent.ConcurrentExecutorProperties;

/**
 * <br>
 * 
 * <pre>
 * [개정이력]
 *     날짜        | 작성자                   |   내용
 * -----------------------------------------------------
 * 2026. 4. 20.     parkjunhong77@gmail.com     최초 작성
 * </pre>
 *
 * @since 2026. 4. 20.
 * @version 4.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
@Configuration
public class TaskSchedulerConfiguration {

    /** 기본적으로 제공되는 {@link ThreadPoolTaskScheduler} */
    public static final String BEAN_QUALIFIER_DEFAULT_TASK_SCHEDULER = "open.commons.spring.web.configure.concurrent.task.TaskSchedulerConfiguration#DEFAULT_TASK_SCHEDULER";
    /** 내부적으로 사용되는 {@link ThreadPoolTaskExecutor} 설정 */
    public static final String CONFIGURATION_TASK_SCHEDULER_PROPERTIES = "open.commons.spring.web.configure.concurrent.task.TaskSchedulerConfiguration#CONFIGURATION_TASK_SCHEDULER_PROPERTIES";
    /** {@link Scheduled} 어노테이션에 기본적으로 사용될 {@link ThreadPoolTaskScheduler} 설정 */
    public static final String CONFIGURATION_TASK_SCHEDULER_PROPERTIES_FOR_SCHEDULED = "open.commons.spring.web.configure.concurrent.task.TaskSchedulerConfiguration#CONFIGURATION_TASK_SCHEDULER_PROPERTIES_FOR_SCHEDULED";

    /** {@link ThreadPoolTaskScheduler} 설정 */
    private final TaskSchedulerProperties props;

    /**
     * 
     * @param props
     *            모든 병렬 처리 및 비동기 실행기 설정을 통합 관리하는 레코드.
     *
     * @since 2026. 4. 21.
     * @version 4.0.0
     */
    public TaskSchedulerConfiguration(ConcurrentExecutorProperties props) {
        this.props = props.taskScheduler();
    }

    /**
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2026. 4. 21.     parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2026. 4. 21.
     * @version 4.0.0
     */
    @Bean(name = CONFIGURATION_TASK_SCHEDULER_PROPERTIES)
    @ConditionalOnMissingBean(name = { CONFIGURATION_TASK_SCHEDULER_PROPERTIES })
    TaskSchedulerProperties taskSchedulerProperties() {
        return TaskSchedulerProperties.copyOf(this.props);
    }

    /**
     * {@link Scheduled} 어노테이션이 적용된 메소드를 실행하는 내부
     * {@link ThreadPoolTaskScheduler}의 설정값을 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2026. 4. 21.     parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2026. 4. 21.
     * @version 4.0.0
     */
    @Bean(name = CONFIGURATION_TASK_SCHEDULER_PROPERTIES_FOR_SCHEDULED)
    @ConditionalOnMissingBean(name = { CONFIGURATION_TASK_SCHEDULER_PROPERTIES_FOR_SCHEDULED })
    TaskSchedulerProperties taskSchedulerPropertiesForScheduled() {
        TaskSchedulerProperties props = new TaskSchedulerProperties( //
                this.props.poolSize() //
                , this.props.removeOnCancelPolicy() //
                , this.props.continueExistingPeriodicTasksAfterShutdownPolicy() //
                , this.props.executeExistingDelayedTasksAfterShutdownPolicy() //
                , this.props.awaitTerminationMillis() //
                , this.props.beanName() //
                , this.props.waitForTasksToCompleteOnShutdown() //
                , true // <-- 무조건 'true'
                , this.props.threadGroupName() //
                , this.props.threadNamePrefix() //
                , this.props.threadPriority() //
        );
        return props;
    }

    /**
     * {@link ThreadPoolTaskScheduler}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2026. 4. 21.     parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2026. 4. 21.
     * @version 4.0.0
     */
    @Bean(name = BEAN_QUALIFIER_DEFAULT_TASK_SCHEDULER)
    @Primary
    ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return createTaskScheduler(this.props);
    }

    /**
     * 전달받을 설정값을 적용한 {@link ThreadPoolTaskScheduler} 객체를 생성합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2026. 4. 21.     parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param props
     * @return
     *
     * @since 2026. 4. 21.
     * @version 4.0.0
     */
    public static ThreadPoolTaskScheduler createTaskScheduler(TaskSchedulerProperties props) {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();

        // -- org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
        // --//
        scheduler.setPoolSize(props.poolSize());
        scheduler.setRemoveOnCancelPolicy(props.removeOnCancelPolicy());
        scheduler.setContinueExistingPeriodicTasksAfterShutdownPolicy(
                props.continueExistingPeriodicTasksAfterShutdownPolicy());
        scheduler.setExecuteExistingDelayedTasksAfterShutdownPolicy(
                props.executeExistingDelayedTasksAfterShutdownPolicy());
        // --------------------------------------------- //

        // ---
        // org.springframework.scheduling.concurrent.ExecutorConfigurationSupport
        // --- //
        scheduler.setAwaitTerminationMillis(props.awaitTerminationMillis());
        scheduler.setBeanName(props.beanName());
        scheduler.setWaitForTasksToCompleteOnShutdown(props.waitForTasksToCompleteOnShutdown());
        // ---------------------------------------------------------- //

        // --- org.springframework.util.CustomizableThreadCreator --- //
        scheduler.setDaemon(props.daemon());
        scheduler.setThreadGroupName(props.threadGroupName());
        scheduler.setThreadNamePrefix(props.threadNamePrefix());
        scheduler.setThreadPriority(props.threadPriority());
        // -------------------------------------------------- //

        return scheduler;
    }

}
