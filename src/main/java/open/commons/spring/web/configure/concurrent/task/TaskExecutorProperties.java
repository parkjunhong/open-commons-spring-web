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
 * Date  : 2026. 4. 21. 오전 10:23:57
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.configure.concurrent.task;

import org.jspecify.annotations.Nullable;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * {@link ThreadPoolTaskExecutor} 설정 정보 레코드.
 *
 * <pre>
 * [개정이력]
 * 날짜        | 작성자                   |   내용
 * -----------------------------------------------------
 * 2019. 7. 17.    parkjunhong77@gmail.com     최초 작성 (class ThreadPoolTaskExecutorConfig)
 * 2026. 4. 21.    parkjunhong77@gmail.com     Record 기반의 ThreadPoolTaskExecutorProperties로 전환
 * </pre>
 * 
 * @param corePoolSize
 *            실행기에 설정된 코어 스레드 수입니다. (기본값: 8)<br>
 *            (@see {@link ThreadPoolTaskExecutor#setCorePoolSize(int)})
 * @param keepAliveSeconds
 *            코어 스레드 수 초과 시 생성된 유휴 스레드가 종료 전 대기하는 초 단위 시간입니다. (기본값: 60)<br>
 *            (@see {@link ThreadPoolTaskExecutor#setKeepAliveSeconds(int)})
 * @param maxPoolSize
 *            실행기에 허용되는 최대 스레드 수입니다. (기본값: 1024)<br>
 *            (@see {@link ThreadPoolTaskExecutor#setMaxPoolSize(int)})
 * @param queueCapacity
 *            작업이 실행되기 전 대기하는 큐의 용량입니다. (기본값: 5000)<br>
 *            (@see {@link ThreadPoolTaskExecutor#setQueueCapacity(int)})
 * @param allowCoreThreadTimeOut
 *            코어 스레드에 대해 타임아웃 종료를 허용할지 여부입니다. (기본값: false)<br>
 *            (@see
 *            {@link ThreadPoolTaskExecutor#setAllowCoreThreadTimeOut(boolean)})
 * @param prestartAllCoreThreads
 *            모든 코어 스레드를 미리 시작할지 여부입니다. (기본값: false)<br>
 *            (@see
 *            {@link ThreadPoolTaskExecutor#setPrestartAllCoreThreads(boolean)})
 * @param awaitTerminationMillis
 *            종료(shutdown) 시 작업 완료를 기다릴 최대 밀리초 시간입니다. (기본값: 0)<br>
 *            (@see
 *            {@link org.springframework.scheduling.concurrent.ExecutorConfigurationSupport#setAwaitTerminationMillis(long)})
 * @param beanName
 *            이 실행기의 빈(Bean) 이름입니다.<br>
 *            (@see
 *            {@link org.springframework.scheduling.concurrent.ExecutorConfigurationSupport#setBeanName(String)})
 * @param waitForTasksToCompleteOnShutdown
 *            종료(shutdown) 시 대기 중인 작업이 완료될 때까지 기다릴지 여부입니다. (기본값: false)<br>
 *            (@see
 *            {@link org.springframework.scheduling.concurrent.ExecutorConfigurationSupport#setWaitForTasksToCompleteOnShutdown(boolean)})
 * @param daemon
 *            이 실행기가 데몬 스레드를 생성할지 여부입니다. (기본값: true)<br>
 *            (@see
 *            {@link org.springframework.util.CustomizableThreadCreator#setDaemon(boolean)})
 * @param threadGroupName
 *            생성된 스레드가 속할 스레드 그룹의 이름입니다. (기본값: "async")<br>
 *            (@see
 *            {@link org.springframework.util.CustomizableThreadCreator#setThreadGroupName(String)})
 * @param threadNamePrefix
 *            새로 생성되는 스레드 이름에 사용할 접두사입니다. (기본값: "task-executor-pool-")<br>
 *            (@see
 *            {@link org.springframework.util.CustomizableThreadCreator#setThreadNamePrefix(String)})
 * @param threadPriority
 *            생성된 스레드에 부여할 우선순위입니다. (기본값: {@link Thread#NORM_PRIORITY})<br>
 *            (@see
 *            {@link org.springframework.util.CustomizableThreadCreator#setThreadPriority(int)})
 *
 * @since 2026. 4. 21.
 * @version 4.0.0
 * @author parkjunhong77@gmail.com
 */
public record TaskExecutorProperties( //
        @DefaultValue("8") int corePoolSize //
        , @DefaultValue("60") int keepAliveSeconds //
        , @DefaultValue("1024") int maxPoolSize //
        , @DefaultValue("5000") int queueCapacity //
        , @DefaultValue("false") boolean allowCoreThreadTimeOut //
        , @DefaultValue("false") boolean prestartAllCoreThreads //
        , @DefaultValue("0") long awaitTerminationMillis //
        , @Nullable String beanName //
        , @DefaultValue("false") boolean waitForTasksToCompleteOnShutdown //
        , @DefaultValue("true") boolean daemon //
        , @DefaultValue("async") @Nullable String threadGroupName //
        , @DefaultValue("task-executor-pool-") @Nullable String threadNamePrefix //
        , @DefaultValue("5") int threadPriority //
) {
    /**
     * 속성값을 복사하여 새로운 레코드를 생성합니다. <br>
     *
     * @param config
     *            복사할 원본 {@link TaskExecutorProperties} 객체
     *
     * @since 2026. 4. 21.
     * @version 4.0.0
     */
    public static TaskExecutorProperties copyOf(TaskExecutorProperties config) {
        return new TaskExecutorProperties(config.corePoolSize(), config.keepAliveSeconds(), config.maxPoolSize(),
                config.queueCapacity(), config.allowCoreThreadTimeOut(), config.prestartAllCoreThreads(),
                config.awaitTerminationMillis(), config.beanName(), config.waitForTasksToCompleteOnShutdown(),
                config.daemon(), config.threadGroupName(), config.threadNamePrefix(), config.threadPriority());
    }
}