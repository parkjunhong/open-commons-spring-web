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
 * Date  : 2025. 9. 29. 오후 12:55:02
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.configure;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import open.commons.spring.web.beans.authority.ResourceHandle;
import open.commons.spring.web.beans.authority.internal.AuthorizedHandles;

/**
 * 
 * @since 2025. 9. 29.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Configuration(value = AuthorizedHandlesConfiguration.BEAN_QUALIFIER, proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "open-commons.spring.web.configuration.enabled", name = "authorized-handles", matchIfMissing = true)
public class AuthorizedHandlesConfiguration {

    static final String BEAN_QUALIFIER = "open.commons.spring.web.configure.AuthorizedHandlesConfiguration";

    /**
     * @since 2025. 9. 29.
     * @version 0.8.0
     */
    public AuthorizedHandlesConfiguration() {
    }

    @Bean
    List<ResourceHandle> authorizedResourcesHandles() {
        return AuthorizedHandles.authorizedResourcesHandles();
    }
}
