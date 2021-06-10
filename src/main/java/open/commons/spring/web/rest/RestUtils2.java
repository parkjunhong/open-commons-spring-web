/*
 * Copyright 2019 Park Jun-Hong_(parkjunhong77/google/com)
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
 * Date  : 2021. 06. 11. 오전 12:04:31
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.spring.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.function.Function;
import java.util.function.Supplier;

import org.apache.http.NoHttpResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import open.commons.Result;
import open.commons.utils.ExceptionUtils;
import open.commons.utils.ThreadUtils;

/**
 * {@link RestTemplate}을 이용하는 유틸리티 클래스2.
 * 
 * @since 2021. 06. 11.
 * @version
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class RestUtils2 {

    private static final Logger logger = LoggerFactory.getLogger(RestUtils2.class);

    private RestUtils2() {
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 06. 11.		박준홍			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @return
     *
     * @since 2021. 06. 11.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <REQ, RES, RET> Result<RET> exchange(RestTemplate restTemplate, HttpMethod method, String scheme, String host, int port, String path, HttpEntity<REQ> entity,
            Class<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , Function<Exception, Result<RET>> onError //
    ) {
        return exchange(restTemplate, method, scheme, host, port, path, null, entity, responseType, onSuccess, onError);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 06. 11.		박준홍			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @return
     *
     * @since 2021. 06. 11.
     * @version _._._
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <REQ, RES, RET> Result<RET> exchange(RestTemplate restTemplate, HttpMethod method, String scheme, String host, int port, String path, HttpEntity<REQ> entity,
            ParameterizedTypeReference<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , Function<Exception, Result<RET>> onError //
    ) {
        return exchange(restTemplate, method, scheme, host, port, path, null, entity, responseType, onSuccess, onError);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 06. 11.		박준홍			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param query
     *            URL Query Parameters
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @return
     *
     * @since 2021. 06. 11.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <REQ, RES, RET> Result<RET> exchange(RestTemplate restTemplate, HttpMethod method, String scheme, String host, int port, String path, String query,
            HttpEntity<REQ> entity, Class<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , Function<Exception, Result<RET>> onError//
    ) {
        try {
            return exchange(restTemplate, method, new URI(scheme, null, host, port, path, query, null), entity, responseType, onSuccess, onError);
        } catch (URISyntaxException e) {
            logger.warn("method={}, scheme={}, host={}, port={}, path={}, query={}, entity={}, response.type={}", method, scheme, host, port, path, query, entity, responseType);
            return onError.apply(e);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 06. 11.		박준홍			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param query
     *            URL Query Parameters
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @return
     *
     * @since 2021. 06. 11.
     * @version _._._
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <REQ, RES, RET> Result<RET> exchange(RestTemplate restTemplate, HttpMethod method, String scheme, String host, int port, String path, String query,
            HttpEntity<REQ> entity, ParameterizedTypeReference<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , Function<Exception, Result<RET>> onError//
    ) {
        try {
            return exchange(restTemplate, method, new URI(scheme, null, host, port, path, query, null), entity, responseType, onSuccess, onError);
        } catch (URISyntaxException e) {
            logger.warn("method={}, scheme={}, host={}, port={}, path={}, query={}, entity={}, response.type={}", method, scheme, host, port, path, query, entity, responseType);
            return onError.apply(e);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 06. 11.		박준홍			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param uri
     *            대상 URI 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @return
     *
     * @since 2021. 06. 11.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <REQ, RES, RET> Result<RET> exchange(RestTemplate restTemplate, HttpMethod method, URI uri, HttpEntity<REQ> entity, Class<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , Function<Exception, Result<RET>> onError//
    ) {
        Supplier<ResponseEntity<RES>> sup = () -> restTemplate.exchange(uri, method, entity, responseType);
        return exchange(sup, method, uri, entity, responseType, onSuccess, onError);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 06. 11.		박준홍			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param uri
     *            대상 URI 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @return
     *
     * @since 2021. 06. 11.
     * @version _._._
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <REQ, RES, RET> Result<RET> exchange(RestTemplate restTemplate, HttpMethod method, URI uri, HttpEntity<REQ> entity, ParameterizedTypeReference<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , Function<Exception, Result<RET>> onError//
    ) {
        Supplier<ResponseEntity<RES>> sup = () -> restTemplate.exchange(uri, method, entity, responseType);
        return exchange(sup, method, uri, entity, responseType, onSuccess, onError);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 06. 11.		박준홍			최초 작성
     * </pre>
     *
     * @param <REQ>
     * @param <RES>
     * @param sup
     * @param method
     * @param uri
     * @param entity
     * @param responseType
     * @param onSuccess
     * @param onError
     * @return
     *
     * @since 2021. 06. 11.
     * @version _._._
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    private static <REQ, RES, RET> Result<RET> exchange(Supplier<ResponseEntity<RES>> sup, HttpMethod method, URI uri, HttpEntity<REQ> entity, Object responseType //
            , Function<ResponseEntity<RES>, Result<RET>> onSuccess, Function<Exception, Result<RET>> onError) {
        final int RETRY_MAX_COUNT = 5;
        int retrial = 0;

        Exception unhandled = null;
        while (retrial < RETRY_MAX_COUNT) {
            try {
                ResponseEntity<RES> response = sup.get();

                HttpStatus statusCode = response.getStatusCode();

                // redirection
                if (statusCode.is3xxRedirection()) {
                    logger.info("URL is redirectioned. status={}, information={}", statusCode, response.getBody());
                } else
                // success
                if (statusCode.is2xxSuccessful()) {
                    logger.debug("Success to send information. target={}", uri.toString());
                } else
                // informational...
                if (statusCode.is1xxInformational()) {
                    logger.debug("Information. status={}, information={}", statusCode, response.getBody());
                }

                return onSuccess.apply(response);
            } catch (HttpClientErrorException e) {

                logger.warn("method={}, uri={}, req.entity={}, res.type={}", method, uri, entity, responseType);

                HttpStatus statusCode = e.getStatusCode();

                // remote server internal error
                if (statusCode.is5xxServerError()) {
                    logger.warn("Remote Server Error. status={}", statusCode);
                } else
                // request error
                if (statusCode.is4xxClientError()) {
                    logger.warn("Request Client Error. status={}", statusCode);
                }

                logger.warn("res.status={}, res.status.raw={}, res.status.text={}, res.body={}", e.getStatusCode(), e.getRawStatusCode(), e.getStatusText(),
                        e.getResponseBodyAsString());

                return onError.apply(e);
            } catch (Exception e) {
                unhandled = e;
                logger.warn("{} Occured {}", "* * * * * ", e.getClass().getName());
                if (NoHttpResponseException.class.isAssignableFrom(e.getClass()) //
                        || ResourceAccessException.class.isAssignableFrom(e.getClass()) //
                ) {
                    retrial++;
                    logger.warn("{} Retry {} by {}", "* * * * * ", retrial, e.getClass().getName());
                    ThreadUtils.sleep(1000);
                } else {
                    throw ExceptionUtils.newException(RuntimeException.class, e, "예상하지 못한 에러가 발생하였습니다. 원인=%s, parent=%s", e.getMessage(), e);
                }
            }
        }
        return onError.apply(unhandled);
    }
}
