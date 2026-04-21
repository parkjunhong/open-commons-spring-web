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
 * Date  : 2026. 4. 21. 오후 4:41:40
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.configure.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import open.commons.spring.web.handler.InterceptorIgnoreUrlProperties;

/**
 * {@link org.springframework.web.servlet.HandlerInterceptor}의 처리 대상에서 제외할 URL
 * 패턴 설정 정보 레코드.
 *
 * @param interceptorIgnoreUrlPatterns
 *            대상별 제외/포함 URL 패턴 목록입니다.
 *
 * @since 2026. 4. 21.
 * @version 4.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
@ConfigurationProperties(prefix = Const.PROPERTIES_OPEN_COMMONS_SPRING_WEB_ROOT_PATH)
public record InterceptorIgnoreProperties( //
        List<InterceptorIgnoreUrlProperties> interceptorIgnoreUrlPatterns //
) {
    public InterceptorIgnoreProperties {
        interceptorIgnoreUrlPatterns = interceptorIgnoreUrlPatterns == null //
                ? List.of() //
                : List.copyOf(interceptorIgnoreUrlPatterns);
    }
}