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
 * Date  : 2019. 10. 24. 오후 1:04:31
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.spring.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import open.commons.Result;
import open.commons.utils.AssertUtils;

/**
 * {@link RestTemplate}을 이용하는 유틸리티 클래스.
 * 
 * @since 2019. 10. 24.
 * @version
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class RestUtils {

    private static final Logger logger = LoggerFactory.getLogger(RestUtils.class);

    private RestUtils() {
    }

    /**
     * 헤더에 새로운 정보를 추가한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 10. 24.		박준홍			최초 작성
     * </pre>
     *
     * @param headers
     *            기존 헤더 객체
     * @param headerPairs
     *            헤더 정보
     * @return
     * @throws IllegalArgumentException
     *
     * @since 2019. 10. 24.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static HttpHeaders addHeaders(HttpHeaders headers, String... headerPairs) throws IllegalArgumentException {
        AssertUtils.assertNull(headerPairs, IllegalArgumentException.class);
        AssertUtils.assertNulls(IllegalArgumentException.class, (Object[]) headerPairs);

        if (headers == null) {
            headers = new HttpHeaders();
        }

        if (headerPairs.length == 0 || headerPairs.length % 2 != 0) {
            throw new IllegalArgumentException("Header 설정 정보가 올바르지 않습니다. 길이=" + headerPairs.length);
        }

        Iterator<String> itr = Arrays.asList(headerPairs).iterator();
        String name = null;
        while (itr.hasNext()) {
            name = itr.next();
            headers.add(name, itr.next());
        }

        return headers;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 10. 24.        박준홍         최초 작성
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
     * @return
     *
     * @since 2019. 10. 24.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <REQ, RES> Result<RES> exchange(RestTemplate restTemplate, HttpMethod method, String scheme, String host, int port, String path, HttpEntity<REQ> entity,
            Class<RES> responseType) {
        return exchange(restTemplate, method, scheme, host, port, path, null, entity, responseType, response -> {
            Result<RES> result = null;
            HttpStatus status = response.getStatusCode();
            if (status.is2xxSuccessful()) {
                result = new Result<>(response.getBody(), true);
            } else {
                result = new Result<>(response.getBody(), false);
                result.setMessage(status.getReasonPhrase());
            }
            return result;
        }, error -> {
            Result<RES> result = new Result<RES>();
            result.setMessage(error.getMessage());

            return result;
        });
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 10. 24.		박준홍			최초 작성
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
     * @since 2019. 10. 24.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <REQ, RES> Result<RES> exchange(RestTemplate restTemplate, HttpMethod method, String scheme, String host, int port, String path, HttpEntity<REQ> entity,
            Class<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RES>> onSuccess //
            , Function<Exception, Result<RES>> onError //
    ) {
        return exchange(restTemplate, method, scheme, host, port, path, null, entity, responseType, onSuccess, onError);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 10. 24.        박준홍         최초 작성
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
     * @return
     *
     * @since 2019. 10. 24.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <REQ, RES> Result<RES> exchange(RestTemplate restTemplate, HttpMethod method, String scheme, String host, int port, String path, String query,
            HttpEntity<REQ> entity, Class<RES> responseType) {
        return exchange(restTemplate, method, scheme, host, port, path, query, entity, responseType, response -> {
            Result<RES> result = null;
            HttpStatus status = response.getStatusCode();
            if (status.is2xxSuccessful()) {
                result = new Result<>(response.getBody(), true);
            } else {
                result = new Result<>(response.getBody(), false);
                result.setMessage(status.getReasonPhrase());
            }
            return result;
        }, error -> {
            Result<RES> result = new Result<RES>();
            result.setMessage(error.getMessage());

            return result;
        });
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 10. 24.		박준홍			최초 작성
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
     * @since 2019. 10. 24.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <REQ, RES> Result<RES> exchange(RestTemplate restTemplate, HttpMethod method, String scheme, String host, int port, String path, String query,
            HttpEntity<REQ> entity, Class<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RES>> onSuccess //
            , Function<Exception, Result<RES>> onError//
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
     * 2019. 10. 24.		박준홍			최초 작성
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
     * @since 2019. 10. 24.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <REQ, RES> Result<RES> exchange(RestTemplate restTemplate, HttpMethod method, URI uri, HttpEntity<REQ> entity, Class<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RES>> onSuccess //
            , Function<Exception, Result<RES>> onError//
    ) {
        try {
            ResponseEntity<RES> response = restTemplate.exchange(uri, method, entity, responseType);
            return onSuccess.apply(response);
        } catch (HttpClientErrorException e) {
            logger.warn("method={}, uri={}, request.entity={}, response.type={}", method, uri, entity, responseType);
            return onError.apply(e);
        }
    }

}
