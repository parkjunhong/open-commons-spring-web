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
 * Date  : 2026. 4. 21. 오후 4:47:59
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.configure.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import open.commons.spring.web.servlet.filter.PathPatternRequest;

/**
 * {@link org.springframework.web.filter.OncePerRequestFilter}의 처리 대상에서 제외할 요청
 * 패턴 설정 정보 레코드.
 *
 * @param oncePerRequestShouldNotFilters
 *            HTTP 메서드와 URL 경로 기반의 필터 제외 패턴 목록입니다.
 *
 * @since 2026. 4. 21.
 * @version 4.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
@ConfigurationProperties(prefix = Const.PROPERTIES_OPEN_COMMONS_SPRING_WEB_ROOT_PATH)
public record OncePerRequestFilterProperties( //
        List<PathPatternRequest> oncePerRequestShouldNotFilters //
) {
    public OncePerRequestFilterProperties {
        oncePerRequestShouldNotFilters = oncePerRequestShouldNotFilters == null ? List.of()
                : List.copyOf(oncePerRequestShouldNotFilters);
    }
}