/*
 * Copyright 2019 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2019. 6. 27. 오후 1:16:53
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.configure;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;

import open.commons.spring.web.configure.concurrent.executor.ExecutorConfiguration;
import open.commons.spring.web.configure.concurrent.executor.ScheduledExecutorConfiguration;
import open.commons.spring.web.configure.concurrent.task.TaskExecutorConfiguration;
import open.commons.spring.web.configure.concurrent.task.TaskSchedulerConfiguration;
import open.commons.spring.web.configure.exception.ExceptionHttpStatusProperties;
import open.commons.spring.web.configure.properties.Const;
import open.commons.spring.web.configure.resttemplate.RestTemplateConfiguration;

/**
 * <pre>
 * [개정이력]
 * 날짜            | 작성자                   |   내용
 * -----------------------------------------------------
 * 2019. 6. 27.     parkjunhong77@gmail.com     최초 작성
 * 2026. 4. 10.     parkjunhong77@gmail.com     Spring Boot:2.7.15 -> 4.0.3, Spring Framework: 5.3.29 -> 7.0.5.
 * 2026. 4. 20.     parkjunohng77@gmail.com     'virtual thread'기반 {@link Async}, {@link Scheduled} 추가.
 * </pre>
 * 
 * @since 2019. 6. 27.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
@Deprecated(since = "4.0.0", forRemoval = true)
public class ResourceConfiguration {

    /**
     * <b><i>{@code /META-INF/open-commons/open-commons-spring-web.yml}</i></b>
     * 파일 내에서의 루트 경로.
     * 
     * <pre>
     * open-commons:
     *   spring:
     *     web:
     * </pre>
     * 
     * @deprecated {@link Const#PROPERTIES_OPEN_COMMONS_SPRING_WEB_ROOT_PATH}를
     *             사용하세요.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    public static final String PROPERTIES_OPEN_COMMONS_SPRING_WEB_ROOT_PATH = "open-commons.spring.web";

    // --- java.util.concurrent.ScheduledThreadPoolExecutor --- //
    /**
     * 기본 {@link RestTemplate}<br>
     * <li>공인 인증서만 허용
     * 
     * @deprecated {@link RestTemplateConfiguration#BEAN_QUALIFIER_RESTTEMPLATE}
     *             를 사용하세요.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    public static final String BEAN_QUALIFIER_RESTTEMPLATE = RestTemplateConfiguration.BEAN_QUALIFIER_RESTTEMPLATE;
    /**
     * 기본 {@link RestTemplate}<br>
     * <li>'공인 + 비공인' 인증서 허용
     * 
     * @deprecated {@link RestTemplateConfiguration#BEAN_QUALIFIER_RESTTEMPLATE_ALLOW_PRIVATE_CA}
     *             를 사용하세요.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    public static final String BEAN_QUALIFIER_RESTTEMPLATE_ALLOW_PRIVATE_CA = RestTemplateConfiguration.BEAN_QUALIFIER_RESTTEMPLATE_ALLOW_PRIVATE_CA;
    /**
     * 기본 {@link RestTemplate}<br>
     * <li>공인 인증서만 허용
     * 
     * @deprecated {@link RestTemplateConfiguration#BEAN_QUALIFIER_RESTTEMPLATE_PROXY_MODE}
     *             를 사용하세요.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    public static final String BEAN_QUALIFIER_RESTTEMPLATE_PROXY_MODE = RestTemplateConfiguration.BEAN_QUALIFIER_RESTTEMPLATE_PROXY_MODE;
    /**
     * 기본 {@link RestTemplate}<br>
     * <li>'공인 + 비공인' 인증서 허용
     * 
     * @deprecated {@link RestTemplateConfiguration#BEAN_QUALIFIER_RESTTEMPLATE_PROXY_MODE_ALLOW_PRIVATE_CA}
     *             를 사용하세요.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    public static final String BEAN_QUALIFIER_RESTTEMPLATE_PROXY_MODE_ALLOW_PRIVATE_CA = RestTemplateConfiguration.BEAN_QUALIFIER_RESTTEMPLATE_PROXY_MODE_ALLOW_PRIVATE_CA;
    // --------------------------------------------------------- //

    // --- org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor ---
    // //
    /**
     * 기본적으로 제공되는 {@link ThreadPoolTaskExecutor}
     * 
     * @deprecated {@link TaskExecutorConfiguration#BEAN_QUALIFIER_DEFAULT_TASK_EXECUTOR}
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    public static final String BEAN_QUALIFIER_DEFAULT_THREAD_POOL_TASK_EXECUTOR = TaskExecutorConfiguration.BEAN_QUALIFIER_DEFAULT_TASK_EXECUTOR;
    /**
     * 내부적으로 사용되는 {@link ThreadPoolTaskExecutor} 설정
     * 
     * @deprecated {@link TaskExecutorConfiguration#CONFIGURATION_TASK_EXECUTOR_PROPERTIES}
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    public static final String CONFIGURATION_THREAD_POOL_TASK_EXECUTOR_CONFIG_ON_MDC = TaskExecutorConfiguration.CONFIGURATION_TASK_EXECUTOR_PROPERTIES;
    // --------------------------------------------------------------------------
    // //

    /**
     * {@link Async} 어노테이션이 적용된 메소드가 실행될 때 기본값으로 사용되는 {@link Executor} 설정값
     * 
     * @deprecated {@link TaskExecutorConfiguration#CONFIGURATION_TASK_EXECUTOR_PROPERTIES_FOR_ASYNC}
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    public static final String CONFIGURATION_THREAD_POOL_TASK_EXECUTOR_CONFIG_ON_ASYNC = TaskExecutorConfiguration.CONFIGURATION_TASK_EXECUTOR_PROPERTIES_FOR_ASYNC;

    // --- org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler ---
    /**
     * 기본적으로 제공되는 {@link ThreadPoolTaskScheduler}
     * 
     * @deprecated {@link TaskSchedulerConfiguration#BEAN_QUALIFIER_DEFAULT_TASK_SCHEDULER}
     *             를 사용하세요.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    public static final String BEAN_QUALIFIER_DEFAULT_THREAD_POOL_TASK_SCHEDULER = TaskSchedulerConfiguration.BEAN_QUALIFIER_DEFAULT_TASK_SCHEDULER;
    /**
     * 내부적으로 사용되는 {@link ThreadPoolTaskExecutor} 설정
     * 
     * @deprecated {@link TaskSchedulerConfiguration#CONFIGURATION_TASK_SCHEDULER_PROPERTIES}
     *             를 사용하세요.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    public static final String CONFIGURATION_THREAD_POOL_TASK_SCHEDULER_CONFIG_ON_MDC = TaskSchedulerConfiguration.CONFIGURATION_TASK_SCHEDULER_PROPERTIES;

    // --------------------------------------------------------------------------
    // //

    // --- java.util.concurrent.ScheduledThreadPoolExecutor --- //
    /**
     * 기본적으로 제공되는 {@link ScheduledThreadPoolExecutor}
     * 
     * @deprecated {@link ScheduledExecutorConfiguration#BEAN_QUALIFIER_DEFAULT_SCHEDULED_EXECUTOR}
     *             를 사용하세요.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    public static final String BEAN_QUALIFIER_DEFAULT_SCHEDULED_THREAD_POOL_EXECUTOR = ScheduledExecutorConfiguration.BEAN_QUALIFIER_DEFAULT_SCHEDULED_EXECUTOR;
    /** 기본적으로 제공되는 {@link ScheduledThreadPoolExecutor} */
    /**
     * 내부적으로 사용되는 {@link ScheduledThreadPoolExecutor}
     * 
     * @deprecated {@link ScheduledExecutorConfiguration#CONFIGURATION_SCHEDULED_EXECUTOR_PROPERTIES}를
     *             사용하세요.
     */
    public static final String CONFIGURATION_SCHEDULED_THREAD_POOL_EXECUTOR_CONFIG_ON_MDC = ScheduledExecutorConfiguration.CONFIGURATION_SCHEDULED_EXECUTOR_PROPERTIES;
    // --------------------------------------------------------------------------
    // //

    // --- java.util.concurrent.ThreadPoolExecutor --- //
    /**
     * 기본적으로 제공되는 {@link ThreadPoolExecutor}
     * 
     * @deprecated {@link ExecutorConfiguration#BEAN_QUALIFIER_DEFAULT_EXECUTOR}
     *             를 사용하세요.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    public static final String BEAN_QUALIFIER_DEFAULT_THREAD_POOL_EXECUTOR = ExecutorConfiguration.BEAN_QUALIFIER_DEFAULT_EXECUTOR;
    /**
     * 내부적으로 사용되는 {@link ThreadPoolExecutor}
     * 
     * @deprecated {@link ExecutorConfiguration#CONFIGURATION_EXECUTOR_PROPERTIES}
     *             를 사용하세요.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    public static final String CONFIGURATION_THREAD_POOL_EXECUTOR_CONFIG_ON_MDC = ExecutorConfiguration.CONFIGURATION_EXECUTOR_PROPERTIES;
    // --------------------------------------------------------------------------
    // //

    /**
     * {@link Throwable} 과 그에 따르는 {@link HttpStatus} 매핑 제공 서비스
     * 
     * @deprecated '식별자' 기반의 참조가 아닌 단일 객체 (
     *             {@link ExceptionHttpStatusProperties} 를 참조하는 방식으로 변경되었습니다.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    public static final String CONFIGURATION_DEFAULT_EXCETPION_HTTPSTATUS_PROPERTIES = "open.commons.spring.web.config.ResourceConfiguration#EXCETPION_HTTPSTATUS_PROPERTIES";
    /**
     * {@link Throwable} 과 그에 따르는 {@link HttpStatus} 매핑 설정 경로
     * 
     * @deprecated '식별자' 기반의 참조가 아닌 단일 객체 (
     *             {@link ExceptionHttpStatusProperties} 를 참조하는 방식으로 변경되었습니다.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    public static final String PROPERTIES_DEFAULT_EXCETPION_HTTPSTATUS_PROPERTIES = PROPERTIES_OPEN_COMMONS_SPRING_WEB_ROOT_PATH
            + ".exception-httpstatus-binder.properties";

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2019. 6. 27.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @since 2019. 6. 27.
     */
    public ResourceConfiguration() {
    }
}
