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
 * Date  : 2026. 4. 20. 오후 8:41:26
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.configure.concurrent.executor;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import open.commons.spring.web.configure.concurrent.ConcurrentExecutorProperties;

/**
 * {@link ThreadPoolExecutor}를 제공하는 클래스. <br>
 * 
 * <pre>
 * [개정이력]
 *     날짜        | 작성자                   |   내용
 * -----------------------------------------------------
 * 2026. 4. 20.     parkjunhong77@gmail.com     최초 작성. 
 * </pre>
 *
 * @since 2026. 4. 20.
 * @version 4.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
@Configuration
public class ExecutorConfiguration {

    /** 기본 {@link ThreadPoolExecutor} 식별정보 */
    public static final String BEAN_QUALIFIER_DEFAULT_EXECUTOR = "open.commons.spring.web.configure.concurrent.executor.ExecutorConfiguration#DEFAULT_EXECUTOR";
    /** 내부적으로 사용되는 {@link ThreadPoolExecutor} */
    public static final String CONFIGURATION_EXECUTOR_PROPERTIES = "open.commons.spring.web.configure.concurrent.executor.ExecutorConfiguration#CONFIGURATION_EXECUTOR_PROPERTIES";

    /** {@link ThreadPoolExecutor} 설정 */
    private final ExecutorProperties props;

    /**
     * @param props
     *            모든 병렬 처리 및 비동기 실행기 설정을 통합 관리하는 레코드.
     * 
     * @since 2026. 4. 20.
     * @version 4.0.0
     */
    public ExecutorConfiguration(ConcurrentExecutorProperties props) {
        this.props = props.executor();
    }

    /**
     * 내부적인 용도로 사용되는 {@link ThreadPoolExecutor} 설정값을 제공합니다.<br>
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
    @Bean(name = CONFIGURATION_EXECUTOR_PROPERTIES)
    @ConditionalOnMissingBean(name = { CONFIGURATION_EXECUTOR_PROPERTIES })
    ExecutorProperties executorPropertiesForMDC() {
        return ExecutorProperties.copyOf(this.props);
    }

    /**
     * 
     * <br>
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
    @Bean(name = BEAN_QUALIFIER_DEFAULT_EXECUTOR)
    @Primary
    ThreadPoolExecutor threadPoolExecutor() {
        return createThreadPoolExecutor(this.props);
    }

    /**
     * 전달받은 설정값을 적용한 {@link ThreadPoolExecutor} 객체를 제공합니다. <br>
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
    public static ThreadPoolExecutor createThreadPoolExecutor(ExecutorProperties props) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor( //
                props.corePoolSize() //
                , props.maximumPoolSize() //
                , props.keepAliveTime() //
                , props.timeUnit() //
                , new SynchronousQueue<>() //
        );

        executor.allowCoreThreadTimeOut(props.allowCoreThreadTimeOut());

        return executor;
    }

}
