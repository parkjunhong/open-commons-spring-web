/*
 * Copyright 2025 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2025. 6. 10. 오후 5:44:40
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.autoconfigure;

import jakarta.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import open.commons.spring.web.configure.AuthorizedObjectMessageConfiguration;
import open.commons.spring.web.handler.AuthorizedModelAndViewHandlerInterceptor;
import open.commons.spring.web.jackson.AuthorizedObjectJacksonHttpMessageConverter;

/**
 * 
 * 
 * <pre>
 * [개정이력]
 *      날짜       | 작성자                   |   내용
 * -----------------------------------------------------
 * 2025. 6. 10.     parkjunhong77@gmail.com     최초 작성
 * 2026. 5. 7.      parkjunohng77@gmail.com     Spring Boot 4.x 에서는 커스텀 {@link HttpMessageConverter}가 Spring 내부에서 제공하는 것보다 앞에 위치시킴에 따라
 *                                              수동으로 커스텀 {@link HttpMessageConverter}의 위치를 변경할 필요가 없어짐.     
 * </pre>
 * 
 * @since 2025. 6. 10.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 * 
 * @deprecated Spring Boot 4.x 에서는 커스텀 {@link HttpMessageConverter}가 Spring 내부에서 제공하는 것보다 앞에 위치시킴에
 *             따라 수동으로 커스텀 {@link HttpMessageConverter}의 위치를 변경할 필요가 없어짐.
 */
//@AutoConfiguration(value = AuthorizedObjectMessageConfigureAutoConfiguration.BEAN_QUALIFIER //
//        , after = { OpenCommonsSpringWebCoreAutoConfiguration.class,
//                AuthorizedObjectMessageConverterAutoConfiguration.class } //
//)
//@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
//@Validated
@Deprecated(since = "4.0.0", forRemoval = true)
public class AuthorizedObjectMessageConfigureAutoConfiguration {

    static final String BEAN_QUALIFIER = "open.commons.spring.web.autoconfigure.AuthorizedObjectMessageConfigureAutoConfiguration";

    private Logger logger = LoggerFactory.getLogger(AuthorizedObjectMessageConfigureAutoConfiguration.class);

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 6. 10.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @since 2025. 6. 10.
     * @version 0.8.0
     */
    public AuthorizedObjectMessageConfigureAutoConfiguration() {
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2026. 5. 7.     parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param environment
     * @param messageConverter
     * @param authorizedModelAndViewHandlerInterceptor
     * @return
     *
     * @since 2025. 6. 10.
     * @version 0.8.0
     * 
     * @deprecated Spring Boot 4.x 에서는 커스텀 {@link HttpMessageConverter}가 Spring 내부에서 제공하는 것보다 앞에
     *             위치시킴에 따라 수동으로 커스텀 {@link HttpMessageConverter}의 위치를 변경할 필요가 없어짐.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
//    @Bean
//    @ConditionalOnBean(name = { AuthorizedObjectJacksonHttpMessageConverter.BEAN_QUALIFIER })
    WebMvcConfigurer authorizedObjectMessageConfigure(Environment environment,
            @Qualifier(AuthorizedObjectJacksonHttpMessageConverter.BEAN_QUALIFIER) @NotNull AuthorizedObjectJacksonHttpMessageConverter messageConverter //
            , AuthorizedModelAndViewHandlerInterceptor authorizedModelAndViewHandlerInterceptor) {
        WebMvcConfigurer configure = new AuthorizedObjectMessageConfiguration(messageConverter);
        logger.info("[authorized-resources] authorized-object-message-configure={}", configure);
        return configure;
    }

}
