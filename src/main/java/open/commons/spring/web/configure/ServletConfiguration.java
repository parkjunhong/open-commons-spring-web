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
 * Date  : 2026. 5. 6. 오후 2:35:21
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.configure;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import open.commons.spring.web.servlet.binder.ExceptionHttpStatusBinder;
import open.commons.spring.web.servlet.method.annotation.DefaultGlobalExceptionHandler;

/**
 * <br>
 * 
 * <pre>
 * [개정이력]
 *     날짜        | 작성자                   |   내용
 * -----------------------------------------------------
 * 2026. 5. 6.     parkjunhong77@gmail.com     최초 작성
 * </pre>
 *
 * @since 2026. 5. 6.
 * @version 4.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
@Configuration(value = ServletConfiguration.BEAN_QUALIFIER)
@ConditionalOnProperty(prefix = "open-commons.spring.web.configuration.enabled", name = "servlet", matchIfMissing = true)
@Validated
public class ServletConfiguration {

    static final String BEAN_QUALIFIER = "open.commons.spring.web.configure.ServletConfiguration";

    /**
     * 
     *
     * @since 2026. 5. 6.
     * @version 4.0.0
     */
    public ServletConfiguration() {
    }

    @Bean(value = DefaultGlobalExceptionHandler.BEAN_QUALIFIER)
    @ConditionalOnMissingBean(DefaultGlobalExceptionHandler.class)
    @ConditionalOnProperty(prefix = "open-commons.spring.web.exception-handler", name = "enabled", matchIfMissing = true)
    DefaultGlobalExceptionHandler defaultGlobalExceptionHandler(
            @Qualifier(ExceptionHttpStatusBinder.BEAN_QUALIFIER) @Nullable ExceptionHttpStatusBinder exceptionHttpStatusBinder) {
        return new DefaultGlobalExceptionHandler(exceptionHttpStatusBinder);
    }

}
