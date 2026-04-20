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
 * Date  : 2025. 8. 4. 오후 4:48:40
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.servlet.filter;

import java.util.Objects;

import jakarta.servlet.Filter;
import jakarta.validation.constraints.NotNull;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpMethod;

import open.commons.core.utils.ExceptionUtils;
import open.commons.spring.web.servlet.exception.InvalidPathPatternUrlException;
import open.commons.spring.web.utils.PathUtils;

/**
 * HTTP 요청 정보를 Spring 7.0 표준인 "PathPattern" 기반으로 제공하는 설정 클래스. *
 * 
 * <pre>
 * [개정이력]
 * 날짜        | 작성자                    | 내용
 * ----------------------------------------------------------------------
 * 2025. 8. 4.      parkjunhong77@gmail.com     최초 작성 (AntPathRequest)
 * 2026. 4. 10.     parkjunhong77@gmail.com     Spring 7.0 현행화: PathPatternRequest로 클래스명 변경 및 JSpecify 적용
 * </pre>
 *
 * @since 2025. 8. 4.
 * @version 4.0.0
 * @author parkjunhong77@gmail.com
 */
public class PathPatternRequest {

    /** URL 패턴 */
    @NotNull
    private String pattern;

    /** HTTP 요청 메소드 (Null 허용: 모든 메소드 매칭) */
    private @Nullable HttpMethod httpMethod;

    /**
     * * URL 패턴 대/소문자 구분 여부 (참고: Spring 7.0의 PathPatternRequestMatcher는 기본적으로 MVC 글로벌 설정을 따르는 것을 권장합니다.)
     */
    private boolean caseSensitive = false;

    /** 패턴을 적용하는 방식 */
    private Scheme targetType = Scheme.CLASS;

    /** {@link Filter} 구현 클래스. 특정 필터에만 룰을 적용할 때 사용합니다. */
    private @Nullable Class<?> filterClass;

    /**
     * 기본 생성자 (Spring Configuration Properties 바인딩용) *
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2025. 8. 4.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @since 2025. 8. 4.
     * @version 4.0.0
     */
    public PathPatternRequest() {
    }

    /**
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2025. 8. 4.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param pattern
     *            URL 패턴
     *
     * @since 2025. 8. 4.
     * @version 4.0.0
     */
    public PathPatternRequest(@NotNull String pattern) {
        this(pattern, null, false, null, null);
    }

    /**
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2025. 8. 4.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param pattern
     *            URL 패턴
     * @param httpMethod
     *            요청 {@link HttpMethod}
     *
     * @since 2025. 8. 4.
     * @version 4.0.0
     */
    public PathPatternRequest(@NotNull String pattern, @NotNull HttpMethod httpMethod) {
        this(pattern, httpMethod, false, null, null);
    }

    /**
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2025. 8. 4.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param pattern
     *            URL 패턴
     * @param httpMethod
     *            요청 {@link HttpMethod}
     * @param caseSensitive
     *            URL 대소문자 비교여부
     *
     * @since 2025. 8. 4.
     * @version 4.0.0
     */
    public PathPatternRequest(@NotNull String pattern, @NotNull HttpMethod httpMethod, boolean caseSensitive) {
        this(pattern, httpMethod, caseSensitive, null, null);
    }

    /**
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2025. 8. 20.     parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 10.     parkjunhong77@gmail.com     PathPatternRequest로 변경 및 JSpecify 적용
     * </pre>
     *
     * @param pattern
     *            URL 패턴
     * @param httpMethod
     *            요청 {@link HttpMethod}
     * @param caseSensitive
     *            URL 대소문자 비교여부
     * @param targetType
     *            비교 방식
     * @param filterClass
     *            {@link Filter} 구현 클래스 * @since 2025. 8. 20.
     * @version 4.0.0
     */
    public PathPatternRequest(@NotNull String pattern, @Nullable HttpMethod httpMethod, boolean caseSensitive, @Nullable Scheme targetType,
            @Nullable Class<? extends Filter> filterClass) {
        this.pattern = pattern;
        this.httpMethod = httpMethod;
        this.caseSensitive = caseSensitive;
        if (targetType != null) {
            this.targetType = targetType;
        }
        this.filterClass = filterClass;
    }

    /**
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2025. 8. 20.     parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param pattern
     *            URL 패턴
     * @param targetType
     *            비교 방식
     * @param filterClass
     *            {@link Filter} 구현 클래스
     *
     * @since 2025. 8. 20.
     * @version 4.0.0
     */
    public PathPatternRequest(@NotNull String pattern, @NotNull Scheme targetType, @NotNull Class<? extends Filter> filterClass) {
        this(pattern, null, false, targetType, filterClass);
    }

    /**
     * @return the filterClass
     *
     * @since 2025. 8. 19.
     * @version 4.0.0
     * @see #filterClass
     */
    public @Nullable Class<?> getFilterClass() {
        return filterClass;
    }

    /**
     * @return the httpMethod
     *
     * @since 2025. 8. 4.
     * @version 4.0.0
     * @see #httpMethod
     */
    public @Nullable HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    /**
     * @return the HTTP method string representation, or null if not set * @since 2025. 8. 4.
     * @version 4.0.0
     */
    public @Nullable String getHttpMethodString() {
        return this.httpMethod != null ? this.httpMethod.toString() : null;
    }

    /**
     * @return the pattern
     *
     * @since 2025. 8. 4.
     * @version 4.0.0
     * @see #pattern
     */
    public String getPattern() {
        if (this.pattern == null) {
            throw ExceptionUtils.newException(IllegalStateException.class, "'pattern'값이 설정이 되지 않았습니다. this=%s", this);
        }
        return this.pattern;
    }

    /**
     * @return the targetType
     *
     * @since 2025. 8. 19.
     * @version 4.0.0
     * @see #targetType
     */
    public Scheme getTargetType() {
        return targetType;
    }

    /**
     * @return the caseSensitive
     *
     * @since 2025. 8. 4.
     * @version 4.0.0
     * @see #caseSensitive
     */
    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    /**
     * 이 객체가 {@link Filter}를 구현한 클래스에 적용 가능한지 여부를 제공합니다.
     *
     * @param <T>
     *            필터 타입
     * @param filterClass
     *            {@link Filter} 구현 클래스
     * @return 적용 가능 여부
     *
     * @since 2025. 8. 19.
     * @version 4.0.0
     */
    public <T extends Filter> boolean matches(Class<T> filterClass) {
        if (this.filterClass == null) {
            return true;
        } else {
            switch (this.targetType) {
                case CLASS:
                    return this.filterClass.equals(filterClass);
                case INSTANCE:
                    return this.filterClass.isAssignableFrom(filterClass);
                default:
                    return false;
            }
        }
    }

    /**
     * @param caseSensitive
     *            the caseSensitive to set
     *
     * @since 2025. 8. 4.
     * @version 4.0.0
     * @see #caseSensitive
     */
    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    /**
     * @param filterClass
     *            the filterClass to set
     *
     * @since 2025. 8. 19.
     * @version 4.0.0
     * @see #filterClass
     */
    public void setFilterClass(@Nullable Class<?> filterClass) {
        this.filterClass = filterClass;
    }

    /**
     * @param httpMethod
     *            the httpMethod to set
     *
     * @since 2025. 8. 4.
     * @version 4.0.0
     * @see #httpMethod
     */
    public void setHttpMethod(@Nullable HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    /**
     * URL 패턴을 설정합니다. 입력된 패턴의 유효성을 검사합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2026. 4. 10.     parkjunhong77@gmail.com     PathPattern 기반으로 검증 및 에러 메시지 개선 권장 (주석 추가)
     * </pre>
     * 
     * * @param pattern the pattern to set
     *
     * @since 2025. 8. 4.
     * @version 4.0.0
     * @see #pattern
     */
    public void setPattern(@NotNull String pattern) {
        if (!PathUtils.isValidPathPattern(pattern)) {
            throw ExceptionUtils.newException(InvalidPathPatternUrlException.class, "exclude.invalid=%s", pattern);
        }
        this.pattern = pattern;
    }

    /**
     * @param targetType
     *            the targetType to set
     *
     * @since 2025. 8. 19.
     * @version 4.0.0
     * @see #targetType
     */
    public void setTargetType(Scheme targetType) {
        Objects.requireNonNull(targetType);

        this.targetType = targetType;
    }

    /**
     * @since 2025. 8. 19.
     * @version 4.0.0
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("PathPatternRequest [pattern=");
        builder.append(pattern);
        builder.append(", httpMethod=");
        builder.append(httpMethod);
        builder.append(", caseSensitive=");
        builder.append(caseSensitive);
        builder.append(", targetType=");
        builder.append(targetType);
        builder.append(", filterClass=");
        builder.append(filterClass);
        builder.append("]");

        return builder.toString();
    }

    /**
     * {@link PathPatternRequest}가 적용될 대상을 결정하는 기준. * @since 2025. 8. 19.
     * 
     * @version 4.0.0
     */
    public static enum Scheme {
        /** {@link Class} 값이 일치 */
        CLASS,
        /** <code>instanceof</code> 결과가 <code>true</code>. */
        INSTANCE
    }
}