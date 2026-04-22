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
 * Date  : 2026. 4. 20. 오후 8:39:48
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.configure.concurrent.task;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import open.commons.spring.web.async.MdcTaskDecorator;
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
public class TaskExecutorConfiguration {

    /** 기본적으로 제공되는 {@link ThreadPoolTaskExecutor} */
    public static final String BEAN_QUALIFIER_DEFAULT_TASK_EXECUTOR = "open.commons.spring.web.configure.concurrent.task.TaskExecutorConfiguration#DEFAULT_TASK_EXECUTOR";
    /** 내부적으로 사용되는 {@link ThreadPoolTaskExecutor} 설정 */
    public static final String CONFIGURATION_TASK_EXECUTOR_PROPERTIES = "open.commons.spring.web.configure.concurrent.task.TaskExecutorConfiguration#CONFIGURATION_TASK_EXECUTOR_PROPERTIES";
    /** {@link Async} 어노테이션이 적용된 메소드가 실행될 때 기본값으로 사용되는 {@link Executor} 설정 */
    public static final String CONFIGURATION_TASK_EXECUTOR_PROPERTIES_FOR_ASYNC = "open.commons.spring.web.configure.concurrent.task.TaskExecutorConfiguration#CONFIGURATION_TASK_EXECUTOR_PROPERTIES_FOR_ASYNC";

    /** {@link ThreadPoolTaskExecutor} 설정 */
    private final TaskExecutorProperties props;

    /**
     * 
     * @param props
     *            모든 병렬 처리 및 비동기 실행기 설정을 통합 관리하는 레코드.
     *
     * @since 2026. 4. 21.
     * @version 4.0.0
     */
    public TaskExecutorConfiguration(ConcurrentExecutorProperties props) {
        this.props = props.taskExecutor();
    }

    /**
     * 내부적인 용도로 사용되는 {@link ThreadPoolTaskExecutor} 설정값을 제공합니다.<br>
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
    @Bean(name = CONFIGURATION_TASK_EXECUTOR_PROPERTIES)
    @ConditionalOnMissingBean(name = { CONFIGURATION_TASK_EXECUTOR_PROPERTIES })
    TaskExecutorProperties taskExecutorProperties() {
        return TaskExecutorProperties.copyOf(this.props);
    }

    /**
     * {@link ThreadPoolTaskExecutor} 제공합니다. <br>
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
    @Bean(name = BEAN_QUALIFIER_DEFAULT_TASK_EXECUTOR, destroyMethod = "destroy")
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Primary
    ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        return createTaskExecutor(this.props, "@builtin");
    }

    /**
     * 전달받은 설정값을 적용한 {@link ThreadPoolTaskExecutor} 객체를 생성합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2026. 4. 21.     parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param props
     * @param threadNameSymbol
     * @return
     *
     * @since 2026. 4. 21.
     * @version 4.0.0
     */
    public static ThreadPoolTaskExecutor createTaskExecutor(TaskExecutorProperties props, String threadNameSymbol) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        // ---- org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor --- //
        executor.setCorePoolSize(props.corePoolSize());
        executor.setKeepAliveSeconds(props.keepAliveSeconds());
        executor.setMaxPoolSize(props.maxPoolSize());
        executor.setQueueCapacity(props.queueCapacity());
        executor.setAllowCoreThreadTimeOut(props.allowCoreThreadTimeOut());
        executor.setPrestartAllCoreThreads(props.prestartAllCoreThreads());
        // Runnable 에 대한 decoration 적용.
        executor.setTaskDecorator(new MdcTaskDecorator(threadNameSymbol));
        // -------------------------------------------------- //
        // --- org.springframework.scheduling.concurrent.ExecutorConfigurationSupport --- //
        executor.setAwaitTerminationMillis(props.awaitTerminationMillis());
        executor.setWaitForTasksToCompleteOnShutdown(props.waitForTasksToCompleteOnShutdown());
        executor.setWaitForTasksToCompleteOnShutdown(props.waitForTasksToCompleteOnShutdown());
        // -------------------------------------------------- //
        // --- org.springframework.util.CustomizableThreadCreator --- //
        executor.setDaemon(props.daemon());
        executor.setThreadNamePrefix(props.threadNamePrefix());
        executor.setThreadGroupName(props.threadGroupName());
        executor.setThreadPriority(props.threadPriority());
        // -------------------------------------------------- //

        return executor;
    }
}
