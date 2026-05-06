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
 * Date  : 2025. 5. 19. 오후 4:33:07
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.autoconfigure;

import java.util.List;

import jakarta.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.jackson.autoconfigure.JacksonAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.validation.annotation.Validated;

import open.commons.spring.web.aspect.AuthorizedMethodAspect;
import open.commons.spring.web.aspect.AuthorizedRequestAspect;
import open.commons.spring.web.beans.authority.IAuthorizedRequestDataMetadata;
import open.commons.spring.web.beans.authority.IAuthorizedResourceAuthenticationPause;
import open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata;
import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IMethodAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IRequestAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;
import open.commons.spring.web.beans.authority.ResourceHandle;
import open.commons.spring.web.beans.authority.internal.AuthorizedResourceHandler;
import open.commons.spring.web.handler.AuthorizedModelAndViewHandlerInterceptor;
import open.commons.spring.web.jackson.deserialization.AuthorizedFieldDeserializerModifier;
import open.commons.spring.web.jackson.serialization.AuthorizedFieldSerializerModifier;
import open.commons.spring.web.servlet.filter.AuthorizedResourceFilter;

import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;

/**
 * <pre>
 * [개정이력]
 *      날짜       | 작성자                   |   내용
 * -----------------------------------------------------
 * 2025. 5. 19.     parkjunhong77@gmail.com     최초 작성
 * 2026. 4. 15.     parkjunhong77@gmail.com     Spring Boot:2.7.15 -> 4.0.3, Spring Framework: 5.3.29 -> 7.0.5.
 * </pre>
 * 
 * @since 2025. 5. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@AutoConfiguration(value = AuthorizedResourcesAutoConfiguration.BEAN_QUALIFIER //
        , after = { JacksonAutoConfiguration.class, OpenCommonsSpringWebCoreAutoConfiguration.class,
                AuthorizedResourceBuiltinHandlerAutoConfiguration.class } //
)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@Validated
public class AuthorizedResourcesAutoConfiguration {

    static final String BEAN_QUALIFIER = "open.commons.spring.web.autoconfigure.AuthorizedResourcesAutoConfiguration";

    public static final String BEAN_QUALIFIER_AUTHORIZED_JSON_MAPPER = "open.commons.spring.web.autoconfigure.AuthorizedResourcesConfiguration#AUTHORIZED_JSON_MAPPER";

    private static final Logger logger = LoggerFactory.getLogger(AuthorizedResourcesAutoConfiguration.class);

    public AuthorizedResourcesAutoConfiguration() {
    }

    @Bean
    @ConditionalOnBean(IMethodAccessAuthorityProvider.class)
    @ConditionalOnMissingBean
    AuthorizedMethodAspect authorizedMethodAspect(ApplicationContext context) {
        AuthorizedMethodAspect aspect = new AuthorizedMethodAspect(context);
        logger.info("[Registered] authorized-method-aspect={}", aspect);
        return aspect;
    }

    @Bean
    @Primary
    @ConditionalOnBean({ IFieldAccessAuthorityProvider.class, IUnauthorizedFieldHandler.class })
    AuthorizedModelAndViewHandlerInterceptor authorizedModelAndViewHandlerInterceptor(ApplicationContext context //
            , @NotNull IAuthorizedResourcesMetadata authorizedResourcesMetadata //
    ) {
        AuthorizedModelAndViewHandlerInterceptor h = new AuthorizedModelAndViewHandlerInterceptor(context,
                authorizedResourcesMetadata);
        logger.info("[authorized-resources] authorized-model_and_view-handler-interceptor={}", h);
        return h;
    }

    /**
     * 기본 JsonMapper의 설정을 그대로 상속받으면서, 데이터 보안(난독/암복호화) 모듈만 추가된 특수 목적의 JsonMapper를 생성합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 5. 19.     parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 14.     parkjunohng77@gmail.com     Jackson 3.0 적용.
     * </pre>
     *
     * @param context
     * @param authorizedResourcesMetadata
     * @param authorizedRequestDataMetadata
     * @param defaultJsonMapper
     * @param objectMapperBuilder
     * @return
     *
     * @since 2025. 5. 19.
     * @version 4.0.0
     */
    @Bean(name = BEAN_QUALIFIER_AUTHORIZED_JSON_MAPPER)
    @ConditionalOnBean({ IFieldAccessAuthorityProvider.class, IUnauthorizedFieldHandler.class })
    JsonMapper authorizedObjectMapper(ApplicationContext context //
            , @NotNull IAuthorizedResourcesMetadata authorizedResourcesMetadata //
            , @NotNull IAuthorizedRequestDataMetadata authorizedRequestDataMetadata //
            , @Qualifier("jacksonJsonMapper") @NotNull JsonMapper defaultJsonMapper //
    ) {
        // #1. 기본 Mapper의 설정을 그대로 물려받는 Builder 생성
        JsonMapper.Builder builder = defaultJsonMapper.rebuild();

        // #2. AuthorizedObject 처리 모듈 생성
        SimpleModule module = new SimpleModule();
        module.setSerializerModifier(new AuthorizedFieldSerializerModifier(context, authorizedResourcesMetadata));
        module.setDeserializerModifier(new AuthorizedFieldDeserializerModifier(context, authorizedRequestDataMetadata));

        // #3. 빌더에 보안 모듈 추가 후 새로운 JsonMapper 완성
        builder.addModule(module);

        JsonMapper authorizedMapper = builder.build();

        logger.info("[authorized-resources] authorized-json-mapper={}", authorizedMapper);

        return authorizedMapper;
    }

    @Bean
    @ConditionalOnBean(IRequestAccessAuthorityProvider.class)
    @ConditionalOnMissingBean
    AuthorizedRequestAspect authorizedRequestAspect(ApplicationContext context) {
        AuthorizedRequestAspect aspect = new AuthorizedRequestAspect(context);
        logger.info("[authorized-resources] authorized-request-aspect={}", aspect);
        return aspect;
    }

    @Bean
    @ConditionalOnBean({ IFieldAccessAuthorityProvider.class, IUnauthorizedFieldHandler.class })
    @Order(Integer.MAX_VALUE)
    AuthorizedResourceFilter authorizedResourceFilter(ApplicationContext context) {
        IAuthorizedResourceAuthenticationPause auth = null;
        try {
            auth = context.getBean(IAuthorizedResourceAuthenticationPause.class);
        } catch (BeansException ignored) {
        }
        AuthorizedResourceFilter f = new AuthorizedResourceFilter(auth);
        logger.info("[authorized-resources] authorized-resources-filter={}", f);
        return f;
    }

    @Bean(AuthorizedResourceHandler.BEAN_QUALIFIER)
    @ConditionalOnBean(name = {
            AuthorizedResourceBuiltinHandlerAutoConfiguration.BEAN_QUALIFIER_AUTHORIZED_RESOURCE_HANDLERS })
    AuthorizedResourceHandler authorizedResourceHandlers(
            @Qualifier(AuthorizedResourceBuiltinHandlerAutoConfiguration.BEAN_QUALIFIER_AUTHORIZED_RESOURCE_HANDLERS) List<ResourceHandle> handlers) {
        AuthorizedResourceHandler h = new AuthorizedResourceHandler();
        h.setAuthorizedResourceHandlers(handlers);
        logger.info("[authorized-resource-handlers] authorized-resources-handlers={}", h);
        return h;
    }
}
