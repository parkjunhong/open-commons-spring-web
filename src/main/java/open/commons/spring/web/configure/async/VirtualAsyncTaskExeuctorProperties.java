/*
 * Copyright 2026 Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"),
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
 * Date  : 2026. 4. 20. 오후 5:59:32
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.configure.async;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.util.CustomizableThreadCreator;

import open.commons.spring.web.configure.ResourceConfiguration;

/**
 * {@link SimpleAsyncTaskExecutor}의 실행 환경을 설정하기 위한 데이터 객체.
 * 
 * @param daemon
 *            {@link CustomizableThreadCreator#setDaemon(boolean)}에 사용될 값.
 * @param threadGroupName
 *            {@link CustomizableThreadCreator#setThreadGroupName(String)}에 사용될 값.
 * @param threadNamePrefix
 *            {@link CustomizableThreadCreator#setThreadNamePrefix(String)}에 사용될 값.
 * @param threadPriority
 *            {@link CustomizableThreadCreator#setThreadPriority(int)}에 사용될 값.
 * @param cancelRemainingTasksOnClose
 *            {@link SimpleAsyncTaskExecutor#setCancelRemainingTasksOnClose(boolean)}에 사용할 값.
 * @param concurrencyLimit
 *            {@link SimpleAsyncTaskExecutor#setConcurrencyLimit(int)}에 사용할 값.
 * @param rejectTasksWhenLimitReached
 *            {@link SimpleAsyncTaskExecutor#setRejectTasksWhenLimitReached(boolean)}에 사용할 값.
 * @param taskTerminationTimeout
 *            {@link SimpleAsyncTaskExecutor#setTaskTerminationTimeout(long)}에 사용할 값.
 * @param virtualThreads
 *            {@link SimpleAsyncTaskExecutor#setVirtualThreads(boolean)}에 사용할 값.
 *
 * @since 2026. 4. 20.
 * @version 4.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
@ConfigurationProperties(prefix = ResourceConfiguration.PROPERTIES_OPEN_COMMONS_SPRING_WEB_ROOT_PATH + ".concurrent.virtual-async-task-executor")
public record VirtualAsyncTaskExeuctorProperties(

        // --- org.springframework.util.CustomizableThreadCreator --- //
        /**
         * {@link CustomizableThreadCreator#setDaemon(boolean)}에 사용될 값.
         */
        @DefaultValue("true") boolean daemon,

        /**
         * {@link CustomizableThreadCreator#setThreadGroupName(String)}에 사용될 값.
         */
        @DefaultValue("virtual") String threadGroupName,
        /**
         * {@link CustomizableThreadCreator#setThreadNamePrefix(String)}에 사용될 값.
         */
        @DefaultValue("thread-") String threadNamePrefix,
        /**
         * {@link CustomizableThreadCreator#setThreadPriority(int)}에 사용될 값.<br>
         * 기본값: {@link Thread#NORM_PRIORITY}({@value Thread#NORM_PRIORITY})
         */
        @DefaultValue("5") int threadPriority,

        // --------------------------------------------- //

        // --- org.springframework.core.task.SimpleAsyncTaskExecutor --- //
        /**
         * {@link SimpleAsyncTaskExecutor#setCancelRemainingTasksOnClose(boolean)}에 사용할 값.
         */
        boolean cancelRemainingTasksOnClose,
        /**
         * {@link SimpleAsyncTaskExecutor#setConcurrencyLimit(int)}에 사용할 값.
         */
        @DefaultValue("-1") int concurrencyLimit,
        /**
         * {@link SimpleAsyncTaskExecutor#setRejectTasksWhenLimitReached(boolean)}에 사용할 값.
         */
        boolean rejectTasksWhenLimitReached,
        /**
         * {@link SimpleAsyncTaskExecutor#setTaskTerminationTimeout(long)}에 사용할 값.
         */
        long taskTerminationTimeout,
        /**
         * {@link SimpleAsyncTaskExecutor#setVirtualThreads(boolean)}에 사용할 값.
         */
        @DefaultValue("true") boolean virtualThreads
// --------------------------------------------- //
) {
}