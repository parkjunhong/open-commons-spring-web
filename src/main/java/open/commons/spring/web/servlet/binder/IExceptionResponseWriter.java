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
 * Date  : 2025. 10. 30. 오후 2:55:27
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.servlet.binder;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;

import open.commons.core.utils.AssertUtils2;
import open.commons.spring.web.utils.WebUtils;

/**
 * 예외 클래스를 응답 데이터로 제공하는 기능을 정의.
 * 
 * <pre>
 * [개정이력]
 * 날짜           | 작성자                   |   내용
 * ------------------------------------------------------
 * 2025. 10. 30.    parkjunohng77@gmail.com        최초 작성
 * 2026. 4. 9.      parkjunhong77@gmail.com     Spring Boot:2.7.15 -> 4.0.3, Spring Framework: 5.3.29 -> 7.0.5
 * </pre>
 * 
 * @since 2025. 10. 30.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public interface IExceptionResponseWriter {

    /**
     * 예외상황에 연결된 {@link HttpStatus} 를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 30.    parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 9.      parkjunhong77@gmail.com     반환타입 변경. {@link HttpStatus}::5.3.29 -> {@link HttpStatusCode}:7.0.5
     * </pre>
     * 
     * @param ex
     *            발생한 예외 상황
     *
     * @return
     *
     * @since 2025. 10. 30.
     * @version 2.1.0
     */
    default HttpStatusCode bind(Exception ex) {
        return getBinder().resolveHttpStatus(ex.getClass(), defaultHttpStatus());
    }

    /**
     * 요청정보({@link HttpServletRequest})와 오류 정보({@link Exception})를 이용하여 응답 데이터를 생성합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 30.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param req
     *            요청 정보
     * @param ex
     *            예외 정보
     * @return
     *
     * @since 2025. 10. 30.
     * @version 2.1.0
     */
    default Object createResponseEntity(HttpServletRequest req, Exception ex) {
        return WebUtils.createEntity(req, ex, bind(ex));
    }

    /**
     * {@link Exception}에 연결된 {@link HttpStatus}가 없는 경우 사용할 {@link HttpStatus}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 30.    parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 9.      parkjunhong77@gmail.com     반환타입 변경. {@link HttpStatus}::5.3.29 -> {@link HttpStatusCode}:7.0.5
     * </pre>
     *
     * @return
     *
     * @since 2025. 10. 30.
     * @version 2.1.0
     */
    default HttpStatusCode defaultHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * {@link Exception}과 {@link HttpStatus} 관계를 제공하는 객체를 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 30.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 10. 30.
     * @version 2.1.0
     */
    ExceptionHttpStatusBinder getBinder();

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 30.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param o
     * @return
     *
     * @since 2025. 10. 30.
     * @version 2.1.0
     */
    String writeAsString(@Nullable Object o);

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 30.    parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 9.      parkjunhong77@gmail.com     내부 데이터 타입 변경. {@link HttpStatus}::5.3.29 -> {@link HttpStatusCode}:7.0.5
     * </pre>
     *
     * @param request
     * @param response
     * @param exception
     * @throws IOException
     * @throws ServletException
     *
     * @since 2025. 10. 30.
     * @version 2.1.0
     */
    default void writeExceptionResponse(HttpServletRequest request, HttpServletResponse response, Exception exception)
            throws IOException, ServletException {
        AssertUtils2.notNulls(request, response, exception);

        HttpStatusCode status = bind(exception);
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        Object entity = createResponseEntity(request, exception);
        response.getWriter().write(writeAsString(entity));
    }

}
