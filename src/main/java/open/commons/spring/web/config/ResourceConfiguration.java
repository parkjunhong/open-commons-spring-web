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

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import open.commons.spring.web.resources.RestTemplateRequestFactoryResource;
import open.commons.spring.web.resources.ThreadPoolTaskExecutorConfig;

/**
 * 
 * @since 2019. 6. 27.
 * @version
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
@Configuration
public class ResourceConfiguration {

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

    @Bean
    @Primary
    @ConfigurationProperties("open-commons.spring.async.thread-pool-task-executor")
    public ThreadPoolTaskExecutorConfig getThreadPoolTaskExecutorConfig() {
        return new ThreadPoolTaskExecutorConfig();
    }
}
