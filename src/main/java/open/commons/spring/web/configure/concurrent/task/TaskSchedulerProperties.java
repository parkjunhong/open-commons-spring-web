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
 * Date  : 2026. 4. 21. 오전 10:40:52
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.configure.concurrent.task;

import org.jspecify.annotations.Nullable;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * {@link ThreadPoolTaskScheduler} 설정 정보 레코드.
 *
 * <pre>
 * [개정이력]
 * 날짜        | 작성자                   |   내용
 * -----------------------------------------------------
 * 2025. 8. 3.     parkjunhong77@gmail.com     최초 작성 (class ThreadPoolTaskSchedulerConfig)
 * 2026. 4. 21.    parkjunhong77@gmail.com     Record 기반의 ThreadPoolTaskSchedulerProperties로 전환
 * </pre>
 *
 * @param poolSize
 *            스케줄러 풀에서 유지할 스레드 수입니다. (기본값: 8)<br>
 *            (@see {@link ThreadPoolTaskScheduler#setPoolSize(int)})
 * @param removeOnCancelPolicy
 *            작업 취소 시 해당 작업을 큐에서 즉시 제거할지 여부입니다. (기본값: false)<br>
 *            (@see
 *            {@link ThreadPoolTaskScheduler#setRemoveOnCancelPolicy(boolean)})
 * @param continueExistingPeriodicTasksAfterShutdownPolicy
 *            스케줄러 종료(shutdown) 후에도 기존의 주기적인 작업을 계속 실행할지 여부입니다. (기본값: false)<br>
 *            (@see
 *            {@link ThreadPoolTaskScheduler#setContinueExistingPeriodicTasksAfterShutdownPolicy(boolean)})
 * @param executeExistingDelayedTasksAfterShutdownPolicy
 *            스케줄러 종료(shutdown) 후에도 기존의 지연된 작업을 실행할지 여부입니다. (기본값: true)<br>
 *            (@see
 *            {@link ThreadPoolTaskScheduler#setExecuteExistingDelayedTasksAfterShutdownPolicy(boolean)})
 * @param awaitTerminationMillis
 *            종료 시 작업 완료를 기다릴 최대 밀리초(ms) 시간입니다. (기본값: 0)<br>
 *            (@see
 *            {@link org.springframework.scheduling.concurrent.ExecutorConfigurationSupport#setAwaitTerminationMillis(long)})
 * @param beanName
 *            이 스케줄러의 빈(Bean) 이름입니다.<br>
 *            (@see
 *            {@link org.springframework.scheduling.concurrent.ExecutorConfigurationSupport#setBeanName(String)})
 * @param waitForTasksToCompleteOnShutdown
 *            종료 시 실행 중인 작업이 완료될 때까지 기다릴지 여부입니다. (기본값: false)<br>
 *            (@see
 *            {@link org.springframework.scheduling.concurrent.ExecutorConfigurationSupport#setWaitForTasksToCompleteOnShutdown(boolean)})
 * @param daemon
 *            생성된 스레드를 데몬 스레드로 설정할지 여부입니다. (기본값: true) <br>
 *            (@see
 *            {@link org.springframework.util.CustomizableThreadCreator#setDaemon(boolean)})
 * @param threadGroupName
 *            스레드가 속할 스레드 그룹의 이름입니다.<br>
 *            (@see
 *            {@link org.springframework.util.CustomizableThreadCreator#setThreadGroupName(String)})
 * @param threadNamePrefix
 *            생성되는 스레드 이름에 사용할 접두사입니다. (기본값: "task-scheduler-pool-") <br>
 *            (@see
 *            {@link org.springframework.util.CustomizableThreadCreator#setThreadNamePrefix(String)})
 * @param threadPriority
 *            생성된 스레드에 부여할 우선순위입니다. (기본값: {@link Thread#NORM_PRIORITY}) <br>
 *            (@see
 *            {@link org.springframework.util.CustomizableThreadCreator#setThreadPriority(int)})
 *
 * @since 2026. 4. 21.
 * @version 4.0.0
 * @author parkjunhong77@gmail.com
 */
public record TaskSchedulerProperties( //
        @DefaultValue("8") int poolSize //
        , @DefaultValue("false") boolean removeOnCancelPolicy //
        , @DefaultValue("false") boolean continueExistingPeriodicTasksAfterShutdownPolicy //
        , @DefaultValue("true") boolean executeExistingDelayedTasksAfterShutdownPolicy //
        , @DefaultValue("0") long awaitTerminationMillis //
        , @Nullable String beanName //
        , @DefaultValue("false") boolean waitForTasksToCompleteOnShutdown //
        , @DefaultValue("true") boolean daemon //
        , @Nullable String threadGroupName //
        , @DefaultValue("task-scheduler-pool-") @Nullable String threadNamePrefix //
        , @DefaultValue("5") int threadPriority //
) {
    /**
     * 기존 속성값을 복사하여 새로운 레코드를 생성하는 생성자. <br>
     *
     * @param config
     *            복사할 원본 {@link TaskSchedulerProperties} 객체
     *
     * @since 2026. 4. 21.
     * @version 4.0.0
     */
    public static TaskSchedulerProperties copyOf(TaskSchedulerProperties config) {
        return new TaskSchedulerProperties(config.poolSize(), config.removeOnCancelPolicy(),
                config.continueExistingPeriodicTasksAfterShutdownPolicy(),
                config.executeExistingDelayedTasksAfterShutdownPolicy(), config.awaitTerminationMillis(),
                config.beanName(), config.waitForTasksToCompleteOnShutdown(), config.daemon(), config.threadGroupName(),
                config.threadNamePrefix(), config.threadPriority());
    }
}