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
 * Date  : 2026. 4. 20. 오후 7:23:31
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.configure.resttemplate;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.hc.client5.http.classic.HttpClient;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import open.commons.spring.web.client.CloseableRestTemplate;
import open.commons.spring.web.rest.RestFacade;

/**
 * {@link RestTemplate} 관련 빈(Bean)을 전담하여 생성하는 설정 클래스.
 * 
 * @since 2026. 4. 20.
 * @version 4.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
@Configuration
public class RestTemplateConfiguration {
    /**
     * 기본 {@link RestTemplate}<br>
     * <li>공인 인증서만 허용
     */
    public static final String BEAN_QUALIFIER_RESTTEMPLATE = "open.commons.spring.web.configure.resttemplate.RestTemplateConfiguration#RESTTEMPLATE";
    /**
     * 기본 {@link RestTemplate}<br>
     * <li>'공인 + 비공인' 인증서 허용
     */
    public static final String BEAN_QUALIFIER_RESTTEMPLATE_ALLOW_PRIVATE_CA = "open.commons.spring.web.configure.resttemplate.RestTemplateConfiguration#RESTTEMPLATE_ALLOW_PRIVATE_CA";
    /**
     * 기본 {@link RestTemplate}<br>
     * <li>공인 인증서만 허용
     */
    public static final String BEAN_QUALIFIER_RESTTEMPLATE_PROXY_MODE = "open.commons.spring.web.configure.resttemplate.RestTemplateConfiguration#RESTTEMPLATE_PROXY)MODE";
    /**
     * 기본 {@link RestTemplate}<br>
     * <li>'공인 + 비공인' 인증서 허용
     */
    public static final String BEAN_QUALIFIER_RESTTEMPLATE_PROXY_MODE_ALLOW_PRIVATE_CA = "open.commons.spring.web.configure.resttemplate.RestTemplateConfiguration#RESTTEMPLATE_PROXY_MODE_ALLOW_PRIVATE_CA";

    private final RestTemplateProperties props;

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
     *
     * @since 2026. 4. 20.
     * @version 4.0.0
     */
    public RestTemplateConfiguration(RestTemplateProperties props) {
        this.props = props;
    }

    /**
     * 기본 RestTemplate (공인 인증서만 허용)
     */
    @Bean(name = BEAN_QUALIFIER_RESTTEMPLATE)
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Primary
    CloseableRestTemplate beanRestTemplate()
            throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException {
        return createRestTemplate(false);
    }

    /**
     * 공인 + 비공인 인증서 허용 RestTemplate
     */
    @Bean(name = BEAN_QUALIFIER_RESTTEMPLATE_ALLOW_PRIVATE_CA)
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    CloseableRestTemplate beanRestTemplateAllowPrivateCA()
            throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException {
        return createRestTemplate(true);
    }

    /**
     * 프록시 모드 적용 RestTemplate (공인 인증서만 허용)
     */
    @Bean(name = BEAN_QUALIFIER_RESTTEMPLATE_PROXY_MODE)
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    CloseableRestTemplate beanRestTemplateProxyMode()
            throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException {
        return createRestTemplate(false);
    }

    /**
     * 프록시 모드 적용 + 공인/비공인 인증서 허용 RestTemplate
     */
    @Bean(name = BEAN_QUALIFIER_RESTTEMPLATE_PROXY_MODE_ALLOW_PRIVATE_CA)
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    CloseableRestTemplate beanRestTemplateProxyModeAllowPrivateCA()
            throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException {
        return createRestTemplate(true);
    }

    /**
     * 설정을 기반으로 RestTemplate 인스턴스를 생성하는 공통 로직.
     */
    private CloseableRestTemplate createRestTemplate(boolean allowPrivateCA)
            throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException {

        // 1. SSL 설정에 따른 HttpClient 생성
        HttpClient httpClient = RestFacade.createHttpsClient(allowPrivateCA);

        // 2. 레코드 프로퍼티를 사용하여 RequestFactory 생성
        HttpComponentsClientHttpRequestFactory reqFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        reqFactory.setConnectionRequestTimeout(props.connectionRequestTimeout());
        reqFactory.setReadTimeout(props.readTimeout());

        return new CloseableRestTemplate(reqFactory);
    }

}
