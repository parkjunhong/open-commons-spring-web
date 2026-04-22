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
 * Date  : 2026. 4. 21. 오후 3:16:38
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.configure.concurrent;

import org.springframework.boot.context.properties.ConfigurationProperties;

import open.commons.spring.web.configure.concurrent.async.VirtualTaskExecuctorProperties;
import open.commons.spring.web.configure.concurrent.executor.ExecutorProperties;
import open.commons.spring.web.configure.concurrent.executor.ScheduledExecutorProperties;
import open.commons.spring.web.configure.concurrent.task.TaskExecutorProperties;
import open.commons.spring.web.configure.concurrent.task.TaskSchedulerProperties;
import open.commons.spring.web.configure.properties.Const;

/**
 * 'open-commons.spring.web.concurrent' 하위의 모든 병렬 처리 및 비동기 실행기 설정을 통합 관리하는 레코드. <br>
 * *
 * 
 * <pre>
 * [개정이력]
 * 날짜        | 작성자                   |   내용
 * -----------------------------------------------------
 * 2026. 4. 21.     parkjunhong77@gmail.com     최초 작성
 * </pre>
 * 
 * @param executor
 *            기본 스레드 풀 실행기({@link java.util.concurrent.ThreadPoolExecutor}) 설정 정보입니다.
 * @param scheduledExecutor
 *            지연 및 주기적 작업 실행기({@link java.util.concurrent.ScheduledThreadPoolExecutor}) 설정 정보입니다.
 * @param taskExecutor
 *            스프링의 비동기 작업
 *            실행기({@link org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor}) 설정
 *            정보입니다.
 * @param taskScheduler
 *            스프링의 스케줄링 작업
 *            실행기({@link org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler}) 설정
 *            정보입니다.
 * @param virtualTaskExecutor
 *            가상 스레드 기반의 비동기 작업 실행기({@link org.springframework.core.task.SimpleAsyncTaskExecutor})
 *            설정 정보입니다.
 * @param virtualTaskScheduler
 *            가상 스레드 기반의 스케줄링 작업 실행기({@link org.springframework.core.task.SimpleAsyncTaskScheduler})
 *            설정 정보입니다.
 *
 * @since 2026. 4. 21.
 * @version 4.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
@ConfigurationProperties(prefix = Const.PROPERTIES_OPEN_COMMONS_SPRING_WEB_ROOT_PATH + ".concurrent")
public record ConcurrentExecutorProperties( //
        ExecutorProperties executor //
        , ScheduledExecutorProperties scheduledExecutor //
        , TaskExecutorProperties taskExecutor //
        , TaskSchedulerProperties taskScheduler //
        , VirtualTaskExecuctorProperties virtualTaskExecutor //
        , VirtualTaskExecuctorProperties virtualTaskScheduler //
) {
}
