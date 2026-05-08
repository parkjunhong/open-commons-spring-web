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
 * Date  : 2025. 6. 10. 오후 5:45:30
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.autoconfigure;

import java.util.Map;
import java.util.stream.Collectors;

import jakarta.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverters;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata;
import open.commons.spring.web.http.converter.json.AuthorizedObjectJsonHttpMessageConverter;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

/**
 * <pre>
 * [개정이력]
 *      날짜       | 작성자                   |   내용
 * -----------------------------------------------------
 * 2025. 6. 10.     parkjunhong77@gmail.com     최초 작성
 * 2026. 4. 15.     parkjunhong77@gmail.com     Jackson 3.0 현행화 ( com.fasterxml.jackson.xxx => tools.jackson.databind.xxx )
 * </pre>
 * 
 * @since 2025. 6. 10.
 * @version 4.0.0
 * @author parkjunhong77@gmail.com
 */
@AutoConfiguration(value = AuthorizedObjectJsonMessageConverterAutoConfiguration.BEAN_QUALIFIER //
        , after = { OpenCommonsSpringWebCoreAutoConfiguration.class, AuthorizedResourcesAutoConfiguration.class } //
)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@Validated
public class AuthorizedObjectJsonMessageConverterAutoConfiguration {

    static final String BEAN_QUALIFIER = "open.commons.spring.web.autoconfigure.AuthorizedObjectJsonMessageConverterAutoConfiguration";

    private Logger logger = LoggerFactory.getLogger(AuthorizedObjectJsonMessageConverterAutoConfiguration.class);

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
    public AuthorizedObjectJsonMessageConverterAutoConfiguration() {
    }

    /**
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 6. 10.     parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 15.     parkjunhong77@gmail.com     {@link ObjectMapper} -> {@link JsonMapper}
     * </pre>
     *
     * @param defaultJsonMapper
     * @param allJsonMappers
     * @param authorizedResourcesMetadataProvider
     * 
     * @return
     *
     * @since 2025. 6. 10.
     * @version 4.0.0
     */
    @Bean
    @ConditionalOnBean({ JsonMapper.class, IAuthorizedResourcesMetadata.class })
    WebMvcConfigurer authorizedObjectMessageConverter(@NotNull JsonMapper defaultJsonMapper //
            , @NotNull Map<String, JsonMapper> allJsonMappers //
            , @NotNull IAuthorizedResourcesMetadata authorizedResourcesMetadataProvider) {

        // 1. defaultJsonMapper 객체와 참조(Reference)가 동일한 엔트리만 완벽하게 제외하여 새로운 Map 생성
        Map<String, JsonMapper> customJsonMappersOnly = allJsonMappers.entrySet().stream() //
                .filter(entry -> entry.getValue() != defaultJsonMapper) // 객체
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // 2. 필터링된 맵(customJsonMappersOnly)을 생성자에 전달
        AuthorizedObjectJsonHttpMessageConverter customConverter = new AuthorizedObjectJsonHttpMessageConverter(
                defaultJsonMapper, customJsonMappersOnly, authorizedResourcesMetadataProvider);

        logger.info("[authorized-resources] 제외 후 순수 커스텀 매퍼 개수: {}", customJsonMappersOnly.size());

        // 3. WebMvcConfigurer를 '빈'으로 제공
        return new WebMvcConfigurer() {
            @Override
            public void configureMessageConverters(HttpMessageConverters.ServerBuilder builder) {
                // 4. JsonConverter를 스왑(Swap)
                builder.withJsonConverter(customConverter);
            }
        };
    }
}
