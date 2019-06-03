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
 * This file is generated under this project, "open-commons-spring5".
 *
 * Date  : 2019. 6. 3. 오후 5:44:34
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.spring5.config;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import open.commons.spring5.annotation.RequestValueSupported;
import open.commons.spring5.enums.EnumConverter;
import open.commons.spring5.enums.EnumConverterFactory;

/**
 * 사용자 정의 {@link Enum} 타입을 HTTP 요청 데이터로 사용하기 위해서 자동으로 변환기를 등록해주는 클래스.
 * 
 * <h1>{@link Enum} 클래스 정보가 있는 패키지 정의</h1>
 * 
 * Sprig Boot Application 설정 파일에 "bean.package.enums" 항목에 대한 값으로 패키지 정보 설정.<br>
 * 
 * 예) application.yml 인 경우
 * 
 * <pre>
 * ...
 * bean.package.name=a.b.c
 * ...
 * </pre>
 * 
 * <h1>사용자 정의 {@link Enum} 작성법</h1>
 * 
 * <pre>
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 * import open.commons.spring5.annotation.RequestValueConverter;
 * import open.commons.spring5.annotation.RequestValueSupported;
 * 
 * &#64;RequestValueSupported
 * public enum Service {
 *     NORMAL("normal"), PREMIUM("premium"), PLATINUM("Platinum");
 * 
 *     private String service;
 * 
 *     private Service(String service) {
 *         this.service = service;
 *     }
 * 
 *     public String get() {
 *         return this.service;
 *     }
 * 
 *     public static Service get(String service) {
 *         return get(service, false);
 *     }
 * 
 *     &#64;RequestValueConverter(hasIgnoreCase = true)
 *     public static Service get(String service, boolean ignoreCase) {
 * 
 *         if (service == null) {
 *             throw new IllegalArgumentException("'service' MUST NOT be null. input: " + service);
 *         }
 * 
 *         if (ignoreCase) {
 *             for (Service value : values()) {
 *                 if (value.service.equalsIgnoreCase(service)) {
 *                     return value;
 *                 }
 *             }
 *         } else {
 *             for (Service value : values()) {
 *                 if (value.service.equals(service)) {
 *                     return value;
 *                 }
 *             }
 *         }
 * 
 *         throw new IllegalArgumentException(
 *                 "Unexpected 'service' value of 'Service'. expected: " + values0() + " & Ignore case-sensitive: " + ignoreCase + ", input: " + service);
 *     }
 * 
 *     private static List<String> values0() {
 * 
 *         List<String> valuesStr = new ArrayList<>();
 * 
 *         for (Service value : values()) {
 *             valuesStr.add(value.get());
 *         }
 * 
 *         return valuesStr;
 *     }
 * }
 * </pre>
 * 
 * @since 2019. 6. 3.
 * @version
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
@EnableWebMvc
@Configuration
public class CustomEnumRegister implements WebMvcConfigurer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Environment env;

    /**
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addFormatters(org.springframework.format.FormatterRegistry)
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void addFormatters(FormatterRegistry registry) {

        EnumConverterFactory factory = new EnumConverterFactory();

        String enumPkg = env.getProperty("bean.package.enums");

        if (enumPkg == null) {
            logger.debug("There is no configuration for 'bean.package.enums'.");
            return;
        } else {
            logger.info("Registered WebMvcConfigurere for 'Custom Enumeration' for HTTP Request/Response. configuration={}, Package={}.", "bean.package.enums", enumPkg);
        }

        Reflections r = new Reflections(enumPkg);
        r.getSubTypesOf(Enum.class)//
                .stream() //
                .filter(type -> type.getAnnotation(RequestValueSupported.class) != null) //
                .forEach(type -> {
                    EnumConverter c = new EnumConverter<>(type);
                    factory.register(type, c);

                    logger.info("Register a Converter {}.", c);
                });

        registry.addConverterFactory(factory);
    }
}
