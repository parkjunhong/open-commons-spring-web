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
 * Date  : 2025. 5. 26. 오후 4:41:00
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.configure;

import jakarta.validation.constraints.NotNull;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverters;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import open.commons.spring.web.authority.AuthorizedObject;
import open.commons.spring.web.jackson.AuthorizedObjectJacksonHttpMessageConverter;
import open.commons.spring.web.jackson.serialization.AuthorizedFieldSerializerModifier;

/**
 * {@link AuthorizedObject} 어노테이션이 설정된 타입을 serialize 처리하는 {@link HttpMessageConverter}를 등록하는 서비스. *
 * 
 * <pre>
 * [개정이력]
 * 날짜        | 작성자                   | 내용
 * -----------------------------------------------------
 * 2025. 5. 26.    parkjunhong77@gmail.com     최초 작성 (Jackson 2.x)
 * 2026. 4. 15.    parkjunhong77@gmail.com     Spring Boot 4.0 / SF 7.0 현행화: ServerBuilder 기반 configureMessageConverters 적용
 * </pre>
 *
 * @since 2025. 5. 26.
 * @version 4.0.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedObjectMessageConfiguration implements WebMvcConfigurer {
    
    private final AuthorizedObjectJacksonHttpMessageConverter authorizeObjectMessageConverter;

    public AuthorizedObjectMessageConfiguration(
            @NotNull AuthorizedObjectJacksonHttpMessageConverter authorizeObjectMessageConverter) {
        this.authorizeObjectMessageConverter = authorizeObjectMessageConverter;
    }

    /**
     * <p>
     * 내부 로직은 Spring 기반 웹 서비스 구동시 내부적으로 생성되는 기본 {@link HttpMessageConverter} 목록을 기반으로 합니다.<br>
     * {@link AuthorizedObjectJacksonHttpMessageConverter}는 {@link JacksonJsonHttpMessageConverter}를
     * 상속받아 구현되었고 상위 클래스 기능을 모두 제공하기 때문에, 기본 JSON 컨버터인 {@link JacksonJsonHttpMessageConverter}를
     * 대체합니다.
     * </p>
     * <p>
     * Spring은 배열에서 순차적으로 {@link HttpMessageConverter} 구현체를 꺼내고,
     * {@link HttpMessageConverter#canWrite(Class, org.springframework.http.MediaType)} 의 결과에 따라서
     * 사용여부를 결정합니다.<br>
     * 기본 컨버터보다 앞에 위치해야 {@link AuthorizedObject} 어노테이션이 설정된 데이터 유형을 먼저 가로채어
     * {@link AuthorizedFieldSerializerModifier}를 통해 권한 기반 처리를 수행할 수 있습니다.
     * </p>
     * 
     * @param builder
     *            Spring 7.0부터 제공되는 HttpMessageConverters 빌더 객체
     * 
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#configureMessageConverters(org.springframework.http.converter.HttpMessageConverters.ServerBuilder)
     */
    @Override
    public void configureMessageConverters(HttpMessageConverters.ServerBuilder builder) {

        // #1. 기존 JacksonJsonHttpMessageConverter 들 중에 제일 앞에 위치
        // builder.configureMessageConvertersList(converters -> {
        // int index = 0;
        // for (HttpMessageConverter<?> c : converters) {
        // if (c instanceof JacksonJsonHttpMessageConverter) {
        // break;
        // }
        // index++;
        // }
        // // 찾은 위치(또는 맨 끝)에 커스텀 컨버터 삽입
        // converters.add(index, this.authorizeObjectMessageConverter);
        // });

        // #2. 'application/json' Converter로 설정
        builder.withJsonConverter(this.authorizeObjectMessageConverter);
    }
}