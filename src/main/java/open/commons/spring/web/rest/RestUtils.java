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
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
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
     * 
     * @deprecated Use {@link #headers(MultiValueMap, String...)}.
     */
    public static HttpHeaders addHeaders(HttpHeaders headers, String... headerPairs) throws IllegalArgumentException {
        return headers(headers, headerPairs);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 8. 25.     박준홍         최초 작성
     * </pre>
     *
     * @param headers
     *            HTTP Headers
     * @param values
     *            데이터. (키/값 으로 이루어진)
     * @return
     *
     * @since 2020. 8. 25.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static HttpEntity<Map<String, Object>> buildHttpEntity(MultiValueMap<String, String> headers, Object... values) {

        Map<String, Object> map = new HashMap<>();

        for (int i = 0; (i + 1) < values.length; i++) {
            map.put(String.valueOf(values[i++]), values[i]);
        }

        return new HttpEntity<Map<String, Object>>(map, headers);
    }

    /**
     * 요청 URL 를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 8. 27.		박준홍			최초 작성
     * </pre>
     *
     * @param context
     *            REST API Context
     * @param url
     *            REST API URL
     * @param parameters
     *            Query Parameters
     * @return
     *
     * @since 2020. 8. 27.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static String createUrl(String context, String url, MultiValueMap<String, Object> parameters) {

        StringBuffer requestUrl = new StringBuffer();

        if (context != null && !context.trim().isEmpty()) {
            requestUrl.append('/');
            requestUrl.append(context);
        }
        requestUrl.append(url);

        if (parameters == null || parameters.isEmpty()) {
            return requestUrl.toString();
        }

        StringBuffer paramBuf = new StringBuffer();
        parameters.entrySet().stream()//
                .map(e -> {
                    if (e.getValue() == null || e.getValue().isEmpty()) {
                        return null;
                    }

                    StringBuffer param = new StringBuffer();
                    String name = e.getKey();
                    for (Object o : e.getValue()) {
                        param.append('&');
                        param.append(name);
                        param.append('=');
                        param.append(o);
                    }
                    return param.toString();
                }) //
                .filter(p -> p != null) //
                .forEach(param -> paramBuf.append(param));

        if (paramBuf.length() > 0) {
            String paramStr = paramBuf.toString().substring(1);
            if (!paramStr.isEmpty()) {
                requestUrl.append('?');
                requestUrl.append(paramStr);
            }
        }

        return requestUrl.toString();
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
        }
    }

    /**
     * 기본 헤더에 새로운 헤더를 추가하여 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 8. 28.     박준홍         최초 작성
     * </pre>
     *
     * @param headers
     *            기존 header
     * @param headerEntries
     *            새로운 header 정보
     * @return
     *
     * @since 2020. 8. 28.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static final HttpHeaders headers(MultiValueMap<String, String> headers, String... headerEntries) {
        AssertUtils.assertNulls(IllegalArgumentException.class, (Object[]) headerEntries);

        if (headerEntries == null) {
            return new HttpHeaders(headers);
        } else {
            HttpHeaders newHeaders = new HttpHeaders();
            for (int i = 0; (i + 1) < headerEntries.length; i++) {
                newHeaders.add(String.valueOf(headerEntries[i++]), headerEntries[i]);
            }
            newHeaders.addAll(headers);
            return newHeaders;
        }
    }
}
