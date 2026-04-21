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
 * Date  : 2026. 4. 21. 오후 4:12:05
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.configure.exception;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import open.commons.spring.web.servlet.binder.ExceptionHttpStatusBinder;

/**
 * <br>
 * 
 * <pre>
 * [개정이력]
 *     날짜        | 작성자                   |   내용
 * -----------------------------------------------------
 * 2026. 4. 21.     parkjunhong77@gmail.com     최초 작성
 * </pre>
 *
 * @since 2026. 4. 21.
 * @version 4.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
@Configuration
public class ExceptionHttpStatusBinderConfiguration {

    private final ExceptionHttpStatusProperties props;

    /**
     * 
     * @param props
     * 
     * @since 2026. 4. 21.
     * @version 4.0.0
     */
    public ExceptionHttpStatusBinderConfiguration(ExceptionHttpStatusProperties props) {
        this.props = props;
    }

    @Bean(name = ExceptionHttpStatusBinder.BEAN_QUALIFIER)
    @Primary
    ExceptionHttpStatusBinder beanExceptionHttpStatusBinder() {
        return new ExceptionHttpStatusBinder(this.props.properties());
    }
}
