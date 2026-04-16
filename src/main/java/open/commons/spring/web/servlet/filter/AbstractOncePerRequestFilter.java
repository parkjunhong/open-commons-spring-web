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
 * Date  : 2025. 8. 19. 오후 5:40:30
 *
 * Author: parkjunhong77@gmail.com
 * 
 */
package open.commons.spring.web.servlet.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import open.commons.spring.web.autoconfigure.configuration.GlobalServletConfiguration;

/**
 * 기본 요청 당 한 번 실행되는 필터의 추상 클래스입니다. 지정된 URL 패턴에 대해 필터링을 건너뛰는(Ignore) 기능을 제공합니다.
 *
 * <pre>
 * [개정이력]
 * 날짜        | 작성자                    | 내용
 * ----------------------------------------------------------------------
 * 2025. 8. 19.     parkjunhong77@gmail.com     최초 작성
 * 2026. 4. 10.     parkjunhong77@gmail.com     JDK 25 및 Spring 7.0 현행화 (PathPatternRequestMatcher 도입 및 캐싱 최적화)
 * </pre>
 *
 * @since 2025. 8. 19.
 * @version 4.0.0
 * @author parkjunhong77@gmail.com
 */
public abstract class AbstractOncePerRequestFilter extends OncePerRequestFilter {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected List<PathPatternRequest> ignoredUrl = new ArrayList<>();

    // Spring 7.0 환경에서 가장 빠르고 안전한 매처들의 캐시
    protected List<RequestMatcher> cachedMatchers = List.of();

    public AbstractOncePerRequestFilter() {
    }

    /**
     * 필터링에서 제외할 URL 패턴 목록을 설정하고, 성능 최적화를 위해 최신 매처를 사전 생성합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2025. 8. 4.      parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 10.     parkjunhong77@gmail.com     PathPatternRequestMatcher 사전 컴파일(캐싱) 로직 적용
     * </pre>
     *
     * @param ignoredUrl
     *            설정할 제외 URL 패턴 목록
     *
     * @since 2025. 8. 4.
     * @version 4.0.0
     * @author parkjunhong77@gmail.com
     */
    @Autowired
    public void setIgnoredUrl(@Qualifier(GlobalServletConfiguration.BEAN_QUALIFIER_PRIMARY_ONCE_PER_REQUEST_SHOULD_NOT_PATTERNS) @Nullable List<PathPatternRequest> ignoredUrl) {
        if (ignoredUrl != null) {
            this.ignoredUrl = ignoredUrl.stream().filter(p -> p.matches(getClass())).collect(Collectors.toUnmodifiableList());

            // [아키텍처 최종 개선] Spring 7.0의 권장 방식: PathPatternRequestMatcher
            this.cachedMatchers = this.ignoredUrl.stream().map(p -> {
                HttpMethod httpMethod = null;
                if (p.getHttpMethodString() != null) {
                    httpMethod = HttpMethod.valueOf(p.getHttpMethodString());
                }

                // Spring Security 7.0 API 스펙: new 연산자 대신 정적 팩토리 메소드(pathPattern) 사용
                if (httpMethod != null) {
                    return PathPatternRequestMatcher.pathPattern(httpMethod, p.getPattern());
                } else {
                    return PathPatternRequestMatcher.pathPattern(p.getPattern());
                }
            }).collect(Collectors.toUnmodifiableList());
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return this.cachedMatchers.stream().anyMatch(matcher -> matcher.matches(request));
    }
}