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
 * Date  : 2025. 6. 5. 오후 12:55:54
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.autoconfigure;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import open.commons.spring.web.autoconfigure.configuration.AuthorizedObjectMessageConfigureConfiguration;
import open.commons.spring.web.autoconfigure.configuration.AuthorizedObjectMessageConverterConfiguration;
import open.commons.spring.web.autoconfigure.configuration.AuthorizedResourceBuiltinHandlerConfiguration;
import open.commons.spring.web.autoconfigure.configuration.AuthorizedResourcesConfiguration;
import open.commons.spring.web.autoconfigure.configuration.GlobalServletConfiguration;
import open.commons.spring.web.autoconfigure.configuration.LogFeatureConfiguration;

/**
 * Open-Commons Spring Web에서 제공하는 기능을 활성화합니다.
 *
 * <pre>
 * [개정이력]
 * 날짜        | 작성자                    | 내용
 * ----------------------------------------------------------------------
 * 2026. 4. 9.      parkjunhong77@gmail.com     JDK 25 및 Spring 7 Javadoc 현행화
 * </pre>
 *
 * @since 2025. 6. 5.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 * 
 * @see AuthorizedResourceBuiltinHandlerConfiguration
 * @see AuthorizedObjectMessageConfigureConfiguration
 * @see AuthorizedObjectMessageConverterConfiguration
 * @see AuthorizedResourcesConfiguration
 * @see LogFeatureConfiguration
 * @see GlobalServletConfiguration
 */
@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(OpenCommonsSpringWebAutoConfigurationImportSelector.class)
public @interface EnableOpenCommonsSpringWeb {

    /**
     * 자동 설정에서 제외할 설정 클래스 목록을 지정합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2025. 6. 5.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @return 제외할 클래스들의 배열 (기본값: 빈 배열)
     *
     * @since 2025. 6. 5.
     * @version 0.8.0
     */
    Class<?>[] exclude() default {};
}
