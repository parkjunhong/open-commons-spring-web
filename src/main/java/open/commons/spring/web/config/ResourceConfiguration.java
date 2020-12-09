/*
 * Copyright 2019 Park Jun-Hong_(parkjunhong77/google/com)
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
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.spring.web.config;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import open.commons.concurrent.DefaultThreadFactory;
import open.commons.spring.web.resources.RestTemplateRequestFactoryResource;
import open.commons.spring.web.resources.ThreadPoolTaskExecutorConfig;
import open.commons.spring.web.rest.RestUtils;

/**
 * 
 * @since 2019. 6. 27.
 * @version
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
@Configuration
public class ResourceConfiguration {

    public static final String BEAN_QUALIFIER_RESTTEMPLATE = "open.commons.spring.web.config.ResourceConfiguration#RESTTEMPLATE";
    public static final String BEAN_QUALIFIER_RESTTEMPLATE_ALLOW_PRIVATE_CA = "open.commons.spring.web.config.ResourceConfiguration#RESTTEMPLATE_ALLOW_PRIVATE_CA";
    public static final String BEAN_QUALIFIER_THREAD_POOL = "open.commons.spring.web.config.ResourceConfiguration#THREADPOOL_TASK_EXECUTOR";

    @Autowired
    private RestTemplateRequestFactoryResource reqFactoryResource;

    @Autowired
    private ThreadPoolTaskExecutorConfig taskExecConfig;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 27.		박준홍			최초 작성
     * </pre>
     *
     * @since 2019. 6. 27.
     * @version
     */
    public ResourceConfiguration() {
    }

    @Bean(name = BEAN_QUALIFIER_RESTTEMPLATE)
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Primary
    public RestTemplate getRestTemplate() throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException {
        HttpClient httpClient = RestUtils.createHttpsClient(false);
        HttpComponentsClientHttpRequestFactory reqFactory = getRequestFactory(httpClient, reqFactoryResource);

        RestTemplate tpl = new RestTemplate(reqFactory);
        return tpl;
    }

    @Bean(name = BEAN_QUALIFIER_RESTTEMPLATE_ALLOW_PRIVATE_CA)
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public RestTemplate getRestTemplateAllowPrivateCA() throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException {
        HttpClient httpClient = RestUtils.createHttpsClient(true);
        HttpComponentsClientHttpRequestFactory reqFactory = getRequestFactory(httpClient, reqFactoryResource);

        RestTemplate tpl = new RestTemplate(reqFactory);
        return tpl;
    }

    /**
     * {@link RestTemplate}에 사용되는 {@link ClientHttpRequestFactory} 설정 정보를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 27.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2019. 6. 27.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    @Bean
    @Primary
    @ConfigurationProperties("open-commons.spring.web.resttemplate.requestfactory")
    public RestTemplateRequestFactoryResource getRestTemplateRequestFactoryResource() {
        return new RestTemplateRequestFactoryResource();
    }

    /**
     * ThreadPool을 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 20.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 1. 20.
     * @version _._._
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    @Bean(name = BEAN_QUALIFIER_THREAD_POOL, destroyMethod = "destroy")
    @Primary
    public ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        String threadPrefix = taskExecConfig.getThreadNamePrefix();
        executor.setThreadFactory(new DefaultThreadFactory(threadPrefix == null ? "async-method" : threadPrefix));

        executor.setCorePoolSize(taskExecConfig.getCorePoolSize());
        executor.setKeepAliveSeconds(taskExecConfig.getKeepAliveSeconds());
        executor.setMaxPoolSize(taskExecConfig.getMaxPoolSize());
        executor.setQueueCapacity(taskExecConfig.getQueueCapacity());
        executor.setAllowCoreThreadTimeOut(taskExecConfig.isAllowCoreThreadTimeOut());
        executor.setAwaitTerminationSeconds(taskExecConfig.getAwaitTerminationSeconds());
        executor.setWaitForTasksToCompleteOnShutdown(taskExecConfig.isWaitForTasksToCompleteOnShutdown());
        executor.setDaemon(taskExecConfig.isDaemon());
        executor.setThreadPriority(taskExecConfig.getThreadPriority());

        return executor;
    }

    /**
     * ThreadPool 설정정보를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 27.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2019. 6. 27.
     * @version _._._
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    @Bean
    @Primary
    @ConfigurationProperties("open-commons.spring.async.thread-pool-task-executor")
    public ThreadPoolTaskExecutorConfig getThreadPoolTaskExecutorConfig() {
        return new ThreadPoolTaskExecutorConfig();
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 27.		박준홍			최초 작성
     * 2020. 12. 9.		박준홍			access modifier 변경 (private -> public static)
     * </pre>
     *
     * @param httpClient
     * @param reqFactoryResource
     * @return
     *
     * @since 2020. 12. 9.
     * @version _._._
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static HttpComponentsClientHttpRequestFactory getRequestFactory(HttpClient httpClient, RestTemplateRequestFactoryResource reqFactoryResource) {
        HttpComponentsClientHttpRequestFactory reqFactory = httpClient != null //
                ? new HttpComponentsClientHttpRequestFactory(httpClient)//
                : new HttpComponentsClientHttpRequestFactory();
        reqFactory.setBufferRequestBody(reqFactoryResource.isBufferRequestBody());
        reqFactory.setConnectionRequestTimeout(reqFactoryResource.getConnectionRequestTimeout());
        reqFactory.setConnectTimeout(reqFactoryResource.getConnectionTimeout());
        reqFactory.setReadTimeout(reqFactoryResource.getReadTimeout());

        return reqFactory;
    }

}
