/*
 * Copyright 2020 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2020. 1. 17. 오후 2:55:32
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.servlet.method.annotation;

import jakarta.validation.ConstraintViolationException;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import open.commons.core.collection.concurrent.ConcurrentLinkedHashMap;
import open.commons.core.function.TripleFunction;
import open.commons.core.utils.AssertUtils2;
import open.commons.spring.web.servlet.binder.ExceptionHttpStatusBinder;
import open.commons.spring.web.utils.ExceptionHttpStatusUtils;
import open.commons.spring.web.utils.WebUtils;

import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * <pre>
 * [개정이력]
 *      날짜        | 작성자            |    내용
 * ------------------------------------------
 * 2020. 1. 17.     parkjunohng77@gmail.com     최초 작성
 * 2026. 4. 9.      parkjunhong77@gmail.com     Spring Boot:2.7.15 -> 4.0.3, Spring Framework: 5.3.29 -> 7.0.5
 * </pre>
 * 
 * @since 2020. 1. 17.
 * @version 0.2.3
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class DefaultGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String BEAN_QUALIFIER = "open.commons.spring.web.servlet.method.annotation.DefaultGlobalExceptionHandler";

    public static final TripleFunction<WebRequest, Exception, HttpStatusCode, ConcurrentLinkedHashMap<String, Object>> FN_CREATE_ENTITY_DEFAULT = WebUtils::createEntity;

    /** {@link RequestBody} 정보 생성 함수 */
    private final TripleFunction<WebRequest, Exception, HttpStatusCode, ConcurrentLinkedHashMap<String, Object>> FN_CREATE_ENTITY;

    /** {@link Throwable}과 {@link HttpStatus} 매핑 정보 */
    private @Nullable ExceptionHttpStatusBinder exceptionHttpStatusBinder;

    /***
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 5. 28.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param exceptionHttpStatusBinder
     *            {@link Throwable}과 {@link HttpStatus} 매핑 정보
     *
     * @since 2025. 5. 28.
     * @version 0.8.0
     */
    public DefaultGlobalExceptionHandler(
            @Qualifier(ExceptionHttpStatusBinder.BEAN_QUALIFIER) ExceptionHttpStatusBinder exceptionHttpStatusBinder) {
        AssertUtils2.notNull(exceptionHttpStatusBinder);

        this(exceptionHttpStatusBinder, FN_CREATE_ENTITY_DEFAULT);
    }

    /**
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 4. 17.     parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 9.      parkjunhong77@gmail.com     파라미터 변경. {@link HttpStatus}::5.3.29 -> {@link HttpStatusCode}:7.0.5
     * </pre>
     * 
     * @param exceptionHttpStatusBinder
     *            {@link Throwable}과 {@link HttpStatus} 매핑 정보
     * @param funcCreateEntity
     *            응답 객체 생성 함수.
     *
     * @since 2025. 4. 17.
     * @version 0.8.0
     */
    public DefaultGlobalExceptionHandler(@Nullable ExceptionHttpStatusBinder exceptionHttpStatusBinder,
            @Nullable TripleFunction<WebRequest, Exception, HttpStatusCode, ConcurrentLinkedHashMap<String, Object>> funcCreateEntity) {
        this.exceptionHttpStatusBinder = exceptionHttpStatusBinder;
        this.FN_CREATE_ENTITY = funcCreateEntity != null ? funcCreateEntity : FN_CREATE_ENTITY_DEFAULT;
    }

    /**
     * 상태에 맞는 메시지를 생성한 후 예외처리를 진행한다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2020. 1. 17.    parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 9.      parkjunhong77@gmail.com     파라미터 변경. {@link HttpStatus}::5.3.29 -> {@link HttpStatusCode}:7.0.5
     * </pre>
     *
     * @param status
     * @param ex
     * @param request
     * @return
     *
     * @since 2020. 1. 17.
     * @version 0.2.3
     */
    protected ResponseEntity<Object> createEntity(HttpStatusCode status, Exception ex, WebRequest request) {
        ConcurrentLinkedHashMap<String, Object> entity = this.FN_CREATE_ENTITY.apply(request, ex, status);
        return handleExceptionInternal(ex, entity, new HttpHeaders(), status, request);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 5. 28.    parkjunhong77@gmail.com        최초 작성
     * 2026. 4. 9.      parkjunhong77@gmail.com     내부 데이터 타입 변경. {@link HttpStatus}::5.3.29 -> {@link HttpStatusCode}:7.0.5
     * </pre>
     *
     * @param ex
     * @param request
     * @return
     *
     * @since 2025. 5. 28.
     * @version 0.8.0
     * 
     * @see #resolveAnnotatedResponseStatus
     */
    @ExceptionHandler(value = { Throwable.class })
    public @Nullable ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

        HttpStatusCode status = resolveAnnotatedResponseStatus(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        if (status == null) {
            // start - 기존에 CaseByCase로 처리되던 예외클래스 지원. : 2025. 5. 28. 오후 4:46:04
            // 4xx:
            if (ConstraintViolationException.class.equals(ex.getClass())) {
                status = HttpStatus.BAD_REQUEST;
            }
            // 5xx
            else if (UnsupportedOperationException.class.equals(ex.getClass())) {
                status = HttpStatus.SERVICE_UNAVAILABLE;
            } //
        }
        // end - 기존에 CaseByCase로 처리되던 예외클래스 지원. : 2025. 5. 28. 오후 4:46:04

        ConcurrentLinkedHashMap<String, Object> entity = this.FN_CREATE_ENTITY.apply(request, ex, status);

        return handleExceptionInternal(ex, entity, new HttpHeaders(), status, request);
    }

    /**
     * 
     * @since 2020. 1. 17.
     * @version 0.2.3
     *
     * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleExceptionInternal(java.lang.Exception,
     *      java.lang.Object, org.springframework.http.HttpHeaders,
     *      org.springframework.http.HttpStatus, org.springframework.web.context.request.WebRequest)
     */
    @Override
    protected @Nullable ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        AssertUtils2.notNulls(ex, headers, status, request);

        if (body == null) {
            body = this.FN_CREATE_ENTITY.apply(request, ex, status);
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler({ JsonMappingException.class })
    public @Nullable ResponseEntity<Object> handleJsonMappingException(JsonMappingException ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ConcurrentLinkedHashMap<String, Object> entity = this.FN_CREATE_ENTITY.apply(request, ex, status);
        return handleExceptionInternal(ex, entity, new HttpHeaders(), status, request);
    }

    /**
     * 사용자 정의 예외 클래스에 선언된 {@link HttpStatus} 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 5. 28.    parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 9.      parkjunhong77@gmail.com     파라미터 변경. {@link HttpStatus}::5.3.29 -> {@link HttpStatusCode}:7.0.5
     * </pre>
     *
     * @param ex
     *            예외클래스.
     * @param defaultStatus
     *            설정된 {@link HttpStatus} 정보가 없는 경우 제공될 기본 {@link HttpStatus}
     * @return
     *
     * @since 2025. 5. 28.
     * @version 0.8.0
     * 
     * @see ResponseStatus
     */
    protected HttpStatusCode resolveAnnotatedResponseStatus(Exception ex, HttpStatusCode defaultStatus) {
        return ExceptionHttpStatusUtils.resolveResponseStatus(this.exceptionHttpStatusBinder, ex, defaultStatus);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 5. 28.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param exceptionHttpStatusBinders
     *            the exceptionHttpStatusBinder to set
     *
     * @since 2025. 5. 28.
     * @version 0.8.0
     *
     * @see #exceptionHttpStatusBinder
     */
//    @Qualifier(ExceptionHttpStatusBinder.BEAN_QUALIFIER)
//    @Autowired
//    public void setExceptionHttpStatusBinder(ExceptionHttpStatusBinder exceptionHttpStatusBinder) {
//        AssertUtils2.notNull(exceptionHttpStatusBinder);
//
//        this.exceptionHttpStatusBinder = exceptionHttpStatusBinder;
//    }
}
