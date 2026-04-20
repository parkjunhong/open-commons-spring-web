/*
 * Copyright 2019 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.rest;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.net.ssl.SSLContext;

import jakarta.validation.constraints.NotEmpty;

import org.apache.hc.client5.http.DnsResolver;
import org.apache.hc.client5.http.SchemePortResolver;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.BasicHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.DefaultHttpClientConnectionOperator;
import org.apache.hc.client5.http.impl.io.ManagedHttpClientConnectionFactory;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.io.ManagedHttpClientConnection;
import org.apache.hc.client5.http.ssl.DefaultClientTlsStrategy;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.TlsSocketStrategy;
import org.apache.hc.core5.http.NoHttpResponseException;
import org.apache.hc.core5.http.config.CharCodingConfig;
import org.apache.hc.core5.http.config.Http1Config;
import org.apache.hc.core5.http.config.Lookup;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.http.io.HttpConnectionFactory;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import open.commons.core.Result;
import open.commons.core.utils.AssertUtils2;
import open.commons.core.utils.ExceptionUtils;
import open.commons.core.utils.ThreadUtils;

/**
 * {@link RestTemplate}을 이용하는 유틸리티 클래스.
 * 
 * @since 2019. 10. 24.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class RestFacade {
    private static final int DEFAULT_RETRY_COUNT = 5;

    private static final Logger sLogger = LoggerFactory.getLogger(RestFacade.class);

    private RestFacade() {
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2026. 4. 10.     parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param headers
     * @param values
     * @return
     *
     * @since 2026. 4. 10.
     * @version 4.0.0
     */
    public static HttpEntity<Map<String, Object>> buildHttpEntity(@Nullable HttpHeaders headers, Object... values) {
        AssertUtils2.notNull(values);

        Map<String, Object> map = new HashMap<>();

        for (int i = 0; (i + 1) < values.length; i++) {
            map.put(String.valueOf(values[i++]), values[i]);
        }

        return new HttpEntity<Map<String, Object>>(map, headers);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 8. 25.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param headers
     *            HTTP Headers
     * @param values
     *            데이터. (키/값 으로 이루어진)
     * @return
     *
     * @since 2020. 8. 25.
     */
    public static HttpEntity<Map<String, Object>> buildHttpEntity(MultiValueMap<String, @Nullable String> headers, Object... values) {
        return buildHttpEntity(new HttpHeaders(headers), values);
    }

    /**
     * 기본 HTTP 연결 객체를 제공합니다. (단일 연결 및 Non-Pooling 방식)
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2016. 11. 21.    parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 10.     parkjunhong77@gmail.com     HC 5.4 정적 팩토리 및 DefaultHttpClientConnectionOperator 적용
     * </pre>
     *
     * @return {@link CloseableHttpClient} 객체
     *
     * @since 2016. 11. 21.
     * @version 4.0.0
     * @author parkjunhong77@gmail.com
     */
    public static CloseableHttpClient createClient() {
        // [PATCH] [오류-1] Args.notNull 통과를 위한 빈 Registry 생성
        Lookup<TlsSocketStrategy> emptyTlsStrategyLookup = RegistryBuilder.<TlsSocketStrategy> create().build();

        // [PATCH] [오류-1] 명시적 캐스팅을 통해 생성자 모호성 제거
        DefaultHttpClientConnectionOperator connectionOperator = new DefaultHttpClientConnectionOperator((SchemePortResolver) null, (DnsResolver) null, emptyTlsStrategyLookup);

        // [PATCH] [오류-2] 캐릭터셋 설정을 위해 ConnectionFactory 사용
        CharCodingConfig charCodingConfig = CharCodingConfig.custom().setCharset(StandardCharsets.UTF_8).build();

        HttpConnectionFactory<ManagedHttpClientConnection> connectionFactory = new ManagedHttpClientConnectionFactory(Http1Config.DEFAULT, charCodingConfig, null);

        BasicHttpClientConnectionManager manager = new BasicHttpClientConnectionManager(connectionOperator, connectionFactory);

        return HttpClientBuilder.create().setConnectionManager(manager).build();
    }

    /**
     * HTTPS 연결 객체를 제공합니다. 멀티스레드 환경을 지원하기 위해 Pooling 방식을 사용합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2016. 11. 21.    parkjunhong77@gmail.com     최초 작성
     * 2019. 4. 9.      parkjunhong77@gmail.com     사설 인증서(Private CA) 지원 확장
     * 2020. 12. 9.     parkjunhong77@gmail.com     Thread-Safe 지원 (Pooling 매니저 적용)
     * 2026. 4. 10.     parkjunhong77@gmail.com     HC 5.4 TlsSocketStrategy 및 Builder 패턴 현행화
     * </pre>
     *
     * @param allowPrivateCA
     *            사설 인증서 허용 여부
     * @return {@link CloseableHttpClient} 객체
     * @throws KeyManagementException
     *             키 관리 오류 시 발생
     * @throws KeyStoreException
     *             키 저장소 오류 시 발생
     * @throws NoSuchAlgorithmException
     *             암호화 알고리즘 부재 시 발생
     *
     * @since 2019. 4. 9.
     * @version 4.0.0
     * @author parkjunhong77@gmail.com
     */
    public static CloseableHttpClient createHttpsClient(boolean allowPrivateCA) throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException {

        TlsSocketStrategy tlsStrategy = createTlsSocketStrategy(allowPrivateCA);

        CharCodingConfig charCodingConfig = CharCodingConfig.custom().setCharset(StandardCharsets.UTF_8).build();

        // [CHECK] 확인된 3개 인자 생성자 사용
        HttpConnectionFactory<ManagedHttpClientConnection> connectionFactory = new ManagedHttpClientConnectionFactory(Http1Config.DEFAULT, charCodingConfig, null);

        // PoolingBuilder에 팩토리 주입
        HttpClientConnectionManager manager = PoolingHttpClientConnectionManagerBuilder.create().setTlsSocketStrategy(tlsStrategy).setConnectionFactory(connectionFactory).build();

        return HttpClientBuilder.create().setConnectionManager(manager).build();
    }

    /**
     * Apache HttpClient 5.4 최신 스펙인 {@link TlsSocketStrategy}를 생성합니다. 기존의 SSLConnectionSocketFactory를 대체합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 4. 9.      parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 10.     parkjunhong77@gmail.com     HC 5.4 DefaultClientTlsStrategy 및 정적 인스턴스 최적화
     * </pre>
     *
     * @param allowPrivateCA
     *            사설 인증서 자동 허용 여부
     * @return 설정된 {@link TlsSocketStrategy} 객체
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws KeyStoreException
     *
     * @since 2019. 4. 9.
     * @version 4.0.0
     * @author parkjunhong77@gmail.com
     */
    private static TlsSocketStrategy createTlsSocketStrategy(boolean allowPrivateCA) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {

        if (allowPrivateCA) {
            TrustStrategy trustStrategy = (_, _) -> true;
            SSLContext sslContext = SSLContextBuilder.create() //
                    .loadTrustMaterial(null, trustStrategy) //
                    .build();

            // [PATCH] [Design-Null] NoopHostnameVerifier.INSTANCE 싱글톤 적용
            return new DefaultClientTlsStrategy(sslContext, NoopHostnameVerifier.INSTANCE);
        } else {
            return new DefaultClientTlsStrategy(SSLContext.getDefault());
        }
    }

    /**
     * 요청 URL 를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2020. 8. 27.    parkjunhong77@gmail.com     최초 작성
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
     */
    public static String createUrl(@Nullable String context, String url, MultiValueMap<String, @Nullable Object> parameters) {
        AssertUtils2.notNulls(url, parameters);

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
     * URI 쿼리 파라미터 데이터를 인코딩합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 7. 2.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     * @return
     *
     * @since 2025. 7. 2.
     * @version 0.8.0
     */
    private static String encode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Encoding failed for: " + value, e);
        }
    }

    /**
     * Template 형태의 <code>Full Qualified URL</code>를 기반으로 REST API 연동을 지원합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 26.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param httpUrl
     *            Fully Qualified URL 패턴을 만족하는 정보
     * @param uriVariables
     *            URL 을 구성하는 정보
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
     * @since 2025. 8. 26.
     * @version 0.8.0
     */
    public static <REQ, RES, RET> Result<RET> exchange(RestTemplate restTemplate //
            , HttpMethod method, String httpUrl, Map<String, ? extends @Nullable Object> uriVariables //
            , @Nullable HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , Function<Exception, Result<RET>> onError//
    ) {
        try {
            return exchangeAsRaw(restTemplate, method, httpUrl, uriVariables, entity, responseType, onSuccess, DEFAULT_RETRY_COUNT);
        } catch (Exception e) {
            return onError.apply(e);
        }
    }

    /**
     * Template 형태의 <code>Full Qualified URL</code>를 기반으로 REST API 연동을 지원합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 26.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param httpUrl
     *            Fully Qualified URL 패턴을 만족하는 정보
     * @param uriVariables
     *            URL 을 구성하는 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     */
    public static <REQ, RES, RET> Result<RET> exchange(RestTemplate restTemplate //
            , HttpMethod method, String httpUrl, Map<String, ? extends @Nullable Object> uriVariables //
            , @Nullable HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , Function<Exception, Result<RET>> onError//
            , int retryCount //
    ) {
        try {
            return exchangeAsRaw(restTemplate, method, httpUrl, uriVariables, entity, responseType, onSuccess, retryCount);
        } catch (Exception e) {
            return onError.apply(e);
        }
    }

    /**
     * Template 형태의 <code>Full Qualified URL</code>를 기반으로 REST API 연동을 지원합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 26.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param httpUrl
     *            Fully Qualified URL 패턴을 만족하는 정보
     * @param uriVariables
     *            URL 을 구성하는 정보
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
     * @since 2025. 8. 26.
     * @version 0.8.0
     */
    public static <REQ, RES, RET> Result<RET> exchange(RestTemplate restTemplate //
            , HttpMethod method, String httpUrl, Map<String, ? extends @Nullable Object> uriVariables //
            , @Nullable HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , Function<Exception, Result<RET>> onError//
    ) {
        try {
            return exchangeAsRaw(restTemplate, method, httpUrl, uriVariables, entity, responseType, onSuccess, DEFAULT_RETRY_COUNT);
        } catch (Exception e) {
            return onError.apply(e);
        }
    }

    /**
     * Template 형태의 <code>Full Qualified URL</code>를 기반으로 REST API 연동을 지원합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 26.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param httpUrl
     *            Fully Qualified URL 패턴을 만족하는 정보
     * @param uriVariables
     *            URL 을 구성하는 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     */
    public static <REQ, RES, RET> Result<RET> exchange(RestTemplate restTemplate //
            , HttpMethod method, String httpUrl, Map<String, ? extends @Nullable Object> uriVariables //
            , @Nullable HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , Function<Exception, Result<RET>> onError//
            , int retryCount //
    ) {
        try {
            return exchangeAsRaw(restTemplate, method, httpUrl, uriVariables, entity, responseType, onSuccess, retryCount);
        } catch (Exception e) {
            return onError.apply(e);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 10. 24.    parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 10.      parkjunhong77@gmail.com     내부 데이터 타입 변경. {@link HttpStatus}::5.3.29 -> {@link HttpStatusCode}:7.0.5
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
     */
    public static <REQ, RES> Result<RES> exchange(RestTemplate restTemplate, HttpMethod method, String scheme, String host, int port, String path, @Nullable HttpEntity<REQ> entity,
            Class<RES> responseType) {
        return exchange(restTemplate, method, scheme, host, port, path, null, entity, responseType, response -> {
            Result<RES> result = null;
            HttpStatusCode status = response.getStatusCode();
            if (status.is2xxSuccessful()) {
                result = new Result<>(response.getBody(), true);
            } else {
                result = new Result<>(response.getBody(), false);
                result.setMessage(status instanceof HttpStatus hs ? hs.getReasonPhrase() : null);
            }
            return result;
        }, error -> {
            Result<RES> result = new Result<RES>();
            result.setMessage(error.getMessage());

            return result;
        });
    }

    /**
     * 기본 헤더에 새로운 헤더를 추가하여 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 8. 28.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param headers
     *            기존 header
     * @param headerEntries
     *            새로운 header 정보
     * @return
     *
     * @since 2020. 8. 28.
     */

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2021. 06. 11.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
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
     * @version 0.4.0
     */
    public static <REQ, RES, RET> Result<RET> exchange(RestTemplate restTemplate //
            , HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path //
            , @Nullable HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , Function<Exception, Result<RET>> onError //
    ) {
        return exchange(restTemplate, method, scheme, host, port, path, null, entity, responseType, onSuccess, onError);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2023. 03. 06.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
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
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2023. 03. 06.
     * @version 0.5.0
     */
    public static <REQ, RES, RET> Result<RET> exchange(RestTemplate restTemplate //
            , HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path //
            , @Nullable HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , Function<Exception, Result<RET>> onError //
            , int retryCount //
    ) {
        return exchange(restTemplate, method, scheme, host, port, path, null, entity, responseType, onSuccess, onError, retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2020. 11. 20.    parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 10.      parkjunhong77@gmail.com     내부 데이터 타입 변경. {@link HttpStatus}::5.3.29 -> {@link HttpStatusCode}:7.0.5
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
     * @since 2020. 11. 20.
     * @version 0.4.0
     */
    public static <REQ, RES> Result<RES> exchange(RestTemplate restTemplate, HttpMethod method, String scheme, String host, int port, String path, @Nullable HttpEntity<REQ> entity,
            ParameterizedTypeReference<RES> responseType) {
        return exchange(restTemplate, method, scheme, host, port, path, null, entity, responseType, response -> {
            Result<RES> result = null;
            HttpStatusCode status = response.getStatusCode();
            if (status.is2xxSuccessful()) {
                result = new Result<>(response.getBody(), true);
            } else {
                result = new Result<>(response.getBody(), false);
                result.setMessage(status instanceof HttpStatus hs ? hs.getReasonPhrase() : null);
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
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2021. 06. 11.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
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
     * @version 0.4.0
     */
    public static <REQ, RES, RET> Result<RET> exchange(RestTemplate restTemplate //
            , HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path //
            , @Nullable HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
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
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2023. 03. 06.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
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
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2023. 03. 06.
     * @version 0.5.0
     */
    public static <REQ, RES, RET> Result<RET> exchange(RestTemplate restTemplate //
            , HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path //
            , @Nullable HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , Function<Exception, Result<RET>> onError //
            , int retryCount //
    ) {
        return exchange(restTemplate, method, scheme, host, port, path, null, entity, responseType, onSuccess, onError, retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 10. 24.    parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 10.     parkjunhong77@gmail.com     내부 데이터 타입 변경. {@link HttpStatus}::5.3.29 -> {@link HttpStatusCode}:7.0.5
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
     */
    public static <REQ, RES> Result<RES> exchange(RestTemplate restTemplate, HttpMethod method, String scheme, String host, int port, String path, String query,
            HttpEntity<REQ> entity, Class<RES> responseType) {
        return exchange(restTemplate, method, scheme, host, port, path, query, entity, responseType, response -> {
            Result<RES> result = null;
            HttpStatusCode status = response.getStatusCode();
            if (status.is2xxSuccessful()) {
                result = new Result<>(response.getBody(), true);
            } else {
                result = new Result<>(response.getBody(), false);
                result.setMessage(status instanceof HttpStatus hs ? hs.getReasonPhrase() : null);
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
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2021. 06. 11.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
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
     * @version 0.4.0
     */
    public static <REQ, RES, RET> Result<RET> exchange(RestTemplate restTemplate //
            , HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path, String query //
            , @Nullable HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , Function<Exception, Result<RET>> onError//
    ) {
        try {
            return exchange(restTemplate, method, new URI(scheme, null, host, port, path, query, null), entity, responseType, onSuccess, onError);
        } catch (URISyntaxException e) {
            sLogger.warn("method={}, scheme={}, host={}, port={}, path={}, query={}, entity={}, response.type={}", method, scheme, host, port, path, query, entity, responseType);
            return onError.apply(e);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2023. 03. 06.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
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
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2023. 03. 06.
     * @version 0.5.0
     */
    public static <REQ, RES, RET> Result<RET> exchange(RestTemplate restTemplate //
            , HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path, String query //
            , @Nullable HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , Function<Exception, Result<RET>> onError //
            , int retryCount //
    ) {
        try {
            return exchange(restTemplate, method, new URI(scheme, null, host, port, path, query, null), entity, responseType, onSuccess, onError, retryCount);
        } catch (URISyntaxException e) {
            sLogger.warn("method={}, scheme={}, host={}, port={}, path={}, query={}, entity={}, response.type={}", method, scheme, host, port, path, query, entity, responseType);
            return onError.apply(e);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2020. 11. 20.    parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 10.     parkjunhong77@gmail.com     내부 데이터 타입 변경. {@link HttpStatus}::5.3.29 -> {@link HttpStatusCode}:7.0.5
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
     * @since 2020. 11. 20.
     * @version 0.4.0
     */
    public static <REQ, RES> Result<RES> exchange(RestTemplate restTemplate, HttpMethod method, String scheme, String host, int port, String path, String query,
            HttpEntity<REQ> entity, ParameterizedTypeReference<RES> responseType) {
        return exchange(restTemplate, method, scheme, host, port, path, query, entity, responseType, response -> {
            Result<RES> result = null;
            HttpStatusCode status = response.getStatusCode();
            if (status.is2xxSuccessful()) {
                result = new Result<>(response.getBody(), true);
            } else {
                result = new Result<>(response.getBody(), false);
                result.setMessage(status instanceof HttpStatus hs ? hs.getReasonPhrase() : null);
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
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2021. 06. 11.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
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
     * @version 0.4.0
     */
    public static <REQ, RES, RET> Result<RET> exchange(RestTemplate restTemplate //
            , HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path, String query //
            , @Nullable HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , Function<Exception, Result<RET>> onError//
    ) {
        try {
            return exchange(restTemplate, method, new URI(scheme, null, host, port, path, query, null), entity, responseType, onSuccess, onError);
        } catch (URISyntaxException e) {
            sLogger.warn("method={}, scheme={}, host={}, port={}, path={}, query={}, entity={}, response.type={}", method, scheme, host, port, path, query, entity, responseType);
            return onError.apply(e);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2023. 03. 06.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
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
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2023. 03. 06.
     * @version 0.5.0
     */
    public static <REQ, RES, RET> Result<RET> exchange(RestTemplate restTemplate //
            , HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path, String query //
            , @Nullable HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , Function<Exception, Result<RET>> onError//
            , int retryCount //
    ) {
        try {
            return exchange(restTemplate, method, new URI(scheme, null, host, port, path, query, null), entity, responseType, onSuccess, onError, retryCount);
        } catch (URISyntaxException e) {
            sLogger.warn("method={}, scheme={}, host={}, port={}, path={}, query={}, entity={}, response.type={}", method, scheme, host, port, path, query, entity, responseType);
            return onError.apply(e);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2021. 06. 11.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
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
     * @version 0.4.0
     */
    public static <REQ, RES, RET> Result<RET> exchange(RestTemplate restTemplate //
            , HttpMethod method, URI uri //
            , @Nullable HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , Function<Exception, Result<RET>> onError//
    ) {
        try {
            return exchangeAsRaw(restTemplate, method, uri, entity, responseType, onSuccess, DEFAULT_RETRY_COUNT);
        } catch (Exception e) {
            return onError.apply(e);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2023. 03. 06.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
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
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2023. 03. 06.
     * @version 0.5.0
     */
    public static <REQ, RES, RET> Result<RET> exchange(RestTemplate restTemplate //
            , HttpMethod method, URI uri //
            , @Nullable HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , Function<Exception, Result<RET>> onError//
            , int retryCount //
    ) {
        try {
            return exchangeAsRaw(restTemplate, method, uri, entity, responseType, onSuccess, retryCount);
        } catch (Exception e) {
            return onError.apply(e);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2021. 06. 11.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
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
     * @version 0.4.0
     */
    public static <REQ, RES, RET> Result<RET> exchange(RestTemplate restTemplate //
            , HttpMethod method, URI uri //
            , @Nullable HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , Function<Exception, Result<RET>> onError//
    ) {
        try {
            return exchangeAsRaw(restTemplate, method, uri, entity, responseType, onSuccess, DEFAULT_RETRY_COUNT);
        } catch (Exception e) {
            return onError.apply(e);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2023. 03. 06.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
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
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2023. 03. 06.
     * @version 0.5.0
     */
    public static <REQ, RES, RET> Result<RET> exchange(RestTemplate restTemplate //
            , HttpMethod method, URI uri //
            , @Nullable HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , Function<Exception, Result<RET>> onError//
            , int retryCount //
    ) {
        try {
            return exchangeAsRaw(restTemplate, method, uri, entity, responseType, onSuccess, retryCount);
        } catch (Exception e) {
            return onError.apply(e);
        }
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 26.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param httpUrl
     *            Fully Qualified URL 패턴을 만족하는 정보
     * @param uriVariables
     *            URL 을 구성하는 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @return
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(RestTemplate restTemplate //
            , HttpMethod method, String httpUrl, Map<String, ? extends @Nullable Object> uriVariables //
            , @Nullable HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , Function<ResponseEntity<RES>, RET> onSuccess //
    ) {
        AssertUtils2.notNulls(restTemplate, method, httpUrl, uriVariables, responseType, onSuccess);

        Supplier<ResponseEntity<RES>> sup = () -> restTemplate.exchange(httpUrl, method, entity, responseType, uriVariables);
        return exchangeAsRaw(sup, method, httpUrl, entity, responseType, onSuccess, DEFAULT_RETRY_COUNT);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 26.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param httpUrl
     *            Fully Qualified URL 패턴을 만족하는 정보
     * @param uriVariables
     *            URL 을 구성하는 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(RestTemplate restTemplate //
            , HttpMethod method, String httpUrl, Map<String, ? extends @Nullable Object> uriVariables //
            , @Nullable HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , Function<ResponseEntity<RES>, RET> onSuccess //
            , int retryCount //
    ) {
        AssertUtils2.notNulls(restTemplate, method, httpUrl, uriVariables, responseType, onSuccess);

        Supplier<ResponseEntity<RES>> sup = () -> restTemplate.exchange(httpUrl, method, entity, responseType, uriVariables);
        return exchangeAsRaw(sup, method, httpUrl, entity, responseType, onSuccess, retryCount);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 26.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param httpUrl
     *            Fully Qualified URL 패턴을 만족하는 정보
     * @param uriVariables
     *            URL 을 구성하는 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @return
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(RestTemplate restTemplate //
            , HttpMethod method, String httpUrl, Map<String, ? extends @Nullable Object> uriVariables //
            , @Nullable HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , Function<ResponseEntity<RES>, RET> onSuccess //
    ) {
        AssertUtils2.notNulls(restTemplate, method, httpUrl, uriVariables, responseType, onSuccess);

        Supplier<ResponseEntity<RES>> sup = () -> restTemplate.exchange(httpUrl, method, entity, responseType, uriVariables);
        return exchangeAsRaw(sup, method, httpUrl, entity, responseType, onSuccess, DEFAULT_RETRY_COUNT);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 26.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param httpUrl
     *            Fully Qualified URL 패턴을 만족하는 정보
     * @param uriVariables
     *            URL 을 구성하는 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(RestTemplate restTemplate //
            , HttpMethod method, String httpUrl, Map<String, ? extends @Nullable Object> uriVariables //
            , @Nullable HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , Function<ResponseEntity<RES>, RET> onSuccess //
            , int retryCount //
    ) {
        AssertUtils2.notNulls(restTemplate, method, httpUrl, uriVariables, responseType, onSuccess);

        Supplier<ResponseEntity<RES>> sup = () -> restTemplate.exchange(httpUrl, method, entity, responseType, uriVariables);
        return exchangeAsRaw(sup, method, httpUrl, entity, responseType, onSuccess, retryCount);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 06. 11.    parkjunhong77@gmail.com         최초 작성
     * 2025. 7. 14.         parkjunhong77@gmail.com     {@link RestFacade2#exchange(RestTemplate, HttpMethod, String, String, int, String, HttpEntity, Class, Function, Function)} 메소드의 반환데이터에서 {@link Result}를 제거함.
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
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
     * @return
     *
     * @since 2021. 06. 11.
     * @version 0.4.0
     * @throws URISyntaxException
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(RestTemplate restTemplate //
            , HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path //
            , @Nullable HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , Function<ResponseEntity<RES>, RET> onSuccess //
    ) throws URISyntaxException {
        return exchangeAsRaw(restTemplate, method, scheme, host, port, path, null, entity, responseType, onSuccess);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 03. 06.    parkjunhong77@gmail.com         최초 작성
     * 2025. 7. 14.         parkjunhong77@gmail.com     {@link RestFacade2#exchange(RestTemplate, HttpMethod, String, String, int, String, HttpEntity, Class, Function, Result, Function)} 메소드의 반환데이터에서 {@link Result}를 제거함.
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
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
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2025. 7. 14.
     * @version 0.8.0
     * @throws URISyntaxException
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(RestTemplate restTemplate //
            , HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path //
            , @Nullable HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , Function<ResponseEntity<RES>, RET> onSuccess //
            , int retryCount) throws URISyntaxException {
        return exchangeAsRaw(restTemplate, method, scheme, host, port, path, null, entity, responseType, onSuccess, retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 06. 11.    parkjunhong77@gmail.com         최초 작성
     * 2025. 7. 14.         parkjunhong77@gmail.com     {@link RestFacade2#exchange(RestTemplate, HttpMethod, String, String, int, String, HttpEntity, ParameterizedTypeReference, Function, Function)} 메소드의 반환데이터에서 {@link Result}를 제거함.
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
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
     * @return
     *
     * @since 2021. 06. 11.
     * @version 0.4.0
     * @throws URISyntaxException
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(RestTemplate restTemplate //
            , HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path //
            , @Nullable HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , Function<ResponseEntity<RES>, RET> onSuccess //
    ) throws URISyntaxException {
        return exchangeAsRaw(restTemplate, method, scheme, host, port, path, null, entity, responseType, onSuccess);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 03. 06.    parkjunhong77@gmail.com         최초 작성
     * 2025. 7. 14.         parkjunhong77@gmail.com     {@link RestFacade2#exchange(RestTemplate, HttpMethod, String, String, int, String, HttpEntity, ParameterizedTypeReference, Function, Function, int)} 메소드의 반환데이터에서 {@link Result}를 제거함.
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
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
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2025. 7. 14.
     * @version 0.8.0
     * @throws URISyntaxException
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(RestTemplate restTemplate //
            , HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path //
            , @Nullable HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , Function<ResponseEntity<RES>, RET> onSuccess //
            , int retryCount) throws URISyntaxException {
        return exchangeAsRaw(restTemplate, method, scheme, host, port, path, null, entity, responseType, onSuccess);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 06. 11.    parkjunhong77@gmail.com         최초 작성
     * 2025. 7. 14.         parkjunhong77@gmail.com     {@link RestFacade2#exchange(RestTemplate, HttpMethod, String, String, int, String, String, HttpEntity, Class, Function, Function)} 메소드의 반환데이터에서 {@link Result}를 제거함.
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
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
     * @return
     *
     * @since 2021. 06. 11.
     * @version 0.4.0
     * @throws URISyntaxException
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(RestTemplate restTemplate //
            , HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path, String query //
            , @Nullable HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , Function<ResponseEntity<RES>, RET> onSuccess //
    ) throws URISyntaxException {
        try {
            return exchangeAsRaw(restTemplate, method, new URI(scheme, null, host, port, path, query, null), entity, responseType, onSuccess);
        } catch (URISyntaxException e) {
            sLogger.warn("method={}, scheme={}, host={}, port={}, path={}, query={}, entity={}, response.type={}", method, scheme, host, port, path, query, entity, responseType);
            throw e;
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 03. 06.    parkjunhong77@gmail.com         최초 작성
     * 2025. 7. 14.         parkjunhong77@gmail.com     {@link RestFacade2#exchange(RestTemplate, HttpMethod, String, String, int, String, String, HttpEntity, Class, Function, Function, int)} 메소드의 반환데이터에서 {@link Result}를 제거함.
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
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
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2025. 7. 14.
     * @version 0.8.0
     * @throws URISyntaxException
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(RestTemplate restTemplate //
            , HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path, String query //
            , @Nullable HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , Function<ResponseEntity<RES>, RET> onSuccess //
            , int retryCount) throws URISyntaxException {
        try {
            return exchangeAsRaw(restTemplate, method, new URI(scheme, null, host, port, path, query, null), entity, responseType, onSuccess, retryCount);
        } catch (URISyntaxException e) {
            sLogger.warn("method={}, scheme={}, host={}, port={}, path={}, query={}, entity={}, response.type={}", method, scheme, host, port, path, query, entity, responseType);
            throw e;
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 06. 11.    parkjunhong77@gmail.com     최초 작성
     * 2025. 7. 15.     parkjunhong77@gmail.com    {@link RestFacade2#exchange(RestTemplate, HttpMethod, String, String, int, String, String, HttpEntity, ParameterizedTypeReference, Function, Function)}  메소드의 반환데이터에서 {@link Result}를 제거함.
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
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
     * @return
     *
     * @since 2021. 06. 11.
     * @version 0.4.0
     * @throws URISyntaxException
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(RestTemplate restTemplate //
            , HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path, String query //
            , @Nullable HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , Function<ResponseEntity<RES>, RET> onSuccess //
    ) throws URISyntaxException {
        try {
            return exchangeAsRaw(restTemplate, method, new URI(scheme, null, host, port, path, query, null), entity, responseType, onSuccess);
        } catch (URISyntaxException e) {
            sLogger.warn("method={}, scheme={}, host={}, port={}, path={}, query={}, entity={}, response.type={}", method, scheme, host, port, path, query, entity, responseType);
            throw e;
        }
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 03. 06.    parkjunhong77@gmail.com     최초 작성
     * 2025. 7. 14.         parkjunhong77@gmail.com     {@link RestFacade2#exchange(RestTemplate, HttpMethod, String, String, int, String, String, HttpEntity, ParameterizedTypeReference, Function, Function, int)} 메소드의 반환데이터에서 {@link Result}를 제거함.
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
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
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2025. 7. 14.
     * @version 0.8.0
     * @throws URISyntaxException
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(RestTemplate restTemplate //
            , HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path, String query //
            , @Nullable HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , Function<ResponseEntity<RES>, RET> onSuccess //
            , int retryCount) throws URISyntaxException {
        try {
            return exchangeAsRaw(restTemplate, method, new URI(scheme, null, host, port, path, query, null), entity, responseType, onSuccess);
        } catch (URISyntaxException e) {
            sLogger.warn("method={}, scheme={}, host={}, port={}, path={}, query={}, entity={}, response.type={}", method, scheme, host, port, path, query, entity, responseType);
            throw e;
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 06. 11.    parkjunhong77@gmail.com         최초 작성
     * 2025. 7. 14.         parkjunhong77@gmail.com     {@link RestFacade2#exchange(RestTemplate, HttpMethod, URI, HttpEntity, Class, Function, Function)} 메소드의 반환데이터에서 {@link Result}를 제거함.
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
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
     * @return
     *
     * @since 2021. 06. 11.
     * @version 0.4.0
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(RestTemplate restTemplate //
            , HttpMethod method, URI uri //
            , @Nullable HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , Function<ResponseEntity<RES>, RET> onSuccess //
    ) {
        AssertUtils2.notNulls(restTemplate, method, uri, responseType, onSuccess);

        Supplier<ResponseEntity<RES>> sup = () -> restTemplate.exchange(uri, method, entity, responseType);
        return exchangeAsRaw(sup, method, uri, entity, responseType, onSuccess, DEFAULT_RETRY_COUNT);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 03. 06.    parkjunhong77@gmail.com         최초 작성
     * 2025. 7. 14.         parkjunhong77@gmail.com     {@link RestFacade2#exchange(RestTemplate, HttpMethod, URI, HttpEntity, Class, Function, Function, int)} 메소드의 반환데이터에서 {@link Result}를 제거함.
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
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
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2025. 7. 14.
     * @version 0.8.0
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(RestTemplate restTemplate //
            , HttpMethod method, URI uri //
            , @Nullable HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , Function<ResponseEntity<RES>, RET> onSuccess //
            , int retryCount //
    ) {
        AssertUtils2.notNulls(restTemplate, method, uri, responseType, onSuccess);

        Supplier<ResponseEntity<RES>> sup = () -> restTemplate.exchange(uri, method, entity, responseType);
        return exchangeAsRaw(sup, method, uri, entity, responseType, onSuccess, retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 06. 11.    parkjunhong77@gmail.com         최초 작성
     * 2025. 7. 14.         parkjunhong77@gmail.com     {@link RestFacade2#exchange(RestTemplate, HttpMethod, URI, HttpEntity, ParameterizedTypeReference, Function, Function)} 메소드의 반환데이터에서 {@link Result}를 제거함.
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
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
     * @return
     *
     * @since 2021. 06. 11.
     * @version 0.4.0
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(RestTemplate restTemplate //
            , HttpMethod method, URI uri //
            , @Nullable HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , Function<ResponseEntity<RES>, RET> onSuccess //
    ) {
        AssertUtils2.notNulls(restTemplate, method, uri, responseType, onSuccess);

        Supplier<ResponseEntity<RES>> sup = () -> restTemplate.exchange(uri, method, entity, responseType);
        return exchangeAsRaw(sup, method, uri, entity, responseType, onSuccess, DEFAULT_RETRY_COUNT);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 03. 06.    parkjunhong77@gmail.com         최초 작성
     * 2025. 7. 14.         parkjunhong77@gmail.com     {@link RestFacade2#exchange(RestTemplate, HttpMethod, URI, HttpEntity, ParameterizedTypeReference, Function, Function, int)} 메소드의 반환데이터에서 {@link Result}를 제거함.
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
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
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2025. 7. 14.
     * @version 0.8.0
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(RestTemplate restTemplate //
            , HttpMethod method, URI uri //
            , @Nullable HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , Function<ResponseEntity<RES>, RET> onSuccess //
            , int retryCount //
    ) {
        AssertUtils2.notNulls(restTemplate, method, uri, responseType, onSuccess);

        Supplier<ResponseEntity<RES>> sup = () -> restTemplate.exchange(uri, method, entity, responseType);
        return exchangeAsRaw(sup, method, uri, entity, responseType, onSuccess, retryCount);
    }

    /**
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2026. 4. 9.      parkjunhong77@gmail.com     내부 데이터 타입 변경. {@link HttpStatus}::5.3.29 -> {@link HttpStatusCode}:7.0.5
     * </pre>
     */
    private static <REQ, RES, RET> RET exchangeAsRaw(Supplier<ResponseEntity<RES>> sup //
            , HttpMethod method, Object url //
            , @Nullable Object entity, Object responseType //
            , Function<ResponseEntity<RES>, RET> onSuccess //
            , int retryCount //
    ) {
        AssertUtils2.notNulls(sup, method, url, entity, responseType, onSuccess);

        final int RETRY_MAX_COUNT = retryCount;
        int retrial = 0;

        Exception unhandled = null;
        while (retrial < RETRY_MAX_COUNT) {
            try {
                ResponseEntity<RES> response = sup.get();

                HttpStatusCode resStatusCode = response.getStatusCode();

                // redirection
                if (resStatusCode.is3xxRedirection()) {
                    sLogger.info("URL is redirectioned. status={}, information={}", resStatusCode, response.getBody());
                } else
                // success
                if (resStatusCode.is2xxSuccessful()) {
                    sLogger.debug("Success to send information. target={}", url.toString());
                } else
                // informational...
                if (resStatusCode.is1xxInformational()) {
                    sLogger.debug("Information. status={}, information={}", resStatusCode, response.getBody());
                }

                return onSuccess.apply(response);
            } catch (HttpClientErrorException | HttpServerErrorException e) {

                sLogger.warn("'Request' -> method={}, uri={}, req.entity={}, res.type={}", method, url, entity, responseType);

                HttpStatusCode exStatusCode = e.getStatusCode();
                String occurs = null;
                // request error
                if (exStatusCode.is4xxClientError()) {
                    occurs = "Request Client Error.";
                } else
                // remote server internal error
                if (exStatusCode.is5xxServerError()) {
                    occurs = "Remote Server Error.";
                }

                sLogger.warn("'{}' -> res.status={}, res.status.raw={}, res.status.text={}, res.body={}", occurs, exStatusCode, exStatusCode.value(), e.getStatusText(),
                        e.getResponseBodyAsString());

                throw e;
            } catch (Exception e) {
                unhandled = e;
                sLogger.warn("{} Occured {}", "* * * * * ", e.getClass().getName());
                if (NoHttpResponseException.class.isAssignableFrom(e.getClass()) //
                        || ResourceAccessException.class.isAssignableFrom(e.getClass()) //
                ) {
                    retrial++;
                    sLogger.warn("{} Retry {} by {}", "* * * * * ", retrial, e.getClass().getName());
                    sLogger.warn("{} Request -> method={}, uri={}, req.entity={}, res.type={}", "* * * * * ", method, url, entity, responseType);
                    ThreadUtils.sleep(1000);
                } else {
                    throw ExceptionUtils.newException(RuntimeException.class, e, "예상하지 못한 에러가 발생하였습니다. 원인=%s, parent=%s", e.getMessage(), e);
                }
            }
        }

        if (unhandled != null) {
            if (RuntimeException.class.isAssignableFrom(unhandled.getClass())) {
                throw (RuntimeException) unhandled;
            } else {
                throw ExceptionUtils.newException(RuntimeException.class, unhandled, "서비스연동에 실패했습니다. 원인=%s, parent=%s", unhandled.getMessage(), unhandled);
            }
        } else {
            throw ExceptionUtils.newException(UnsupportedOperationException.class, "예상하지 못한 에러가 발생하였습니다.");
        }
    }

    /**
     * 기본 헤더에 새로운 헤더를 추가하여 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2026. 4. 10.     parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param headers
     *            기존 header
     * @param headerEntries
     *            새로운 header 정보
     * @return
     *
     * @since 2026. 4. 10.
     * @version 4.0.0
     */
    public static final HttpHeaders headers(HttpHeaders headers, String... headerEntries) {
        AssertUtils2.notNulls(IllegalArgumentException.class, (Object[]) headerEntries);

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

    /**
     * 기본 헤더에 새로운 헤더를 추가하여 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 8. 28.     parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 10.     parkjunhong77@gmail.com     {@link HttpHeaders}::7.0.5 상속관계 변경({@link MultiValueMap<K,V>}을 상속받지 않음)에 따른 수정
     * </pre>
     *
     * @param headers
     *            기존 header
     * @param headerEntries
     *            새로운 header 정보
     * @return
     *
     * @since 2020. 8. 28.
     */
    public static final HttpHeaders headers(MultiValueMap<String, String> headers, String... headerEntries) {
        AssertUtils2.notNulls(IllegalArgumentException.class, (Object[]) headerEntries);

        return headers(new HttpHeaders(headers), headerEntries);
    }

    /**
     * 쿼리 파라미터 데이터를 하나의 문자열로 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2020. 10. 21.    parkjunhong77@gmail.com     최초 작성
     * 2025. 7. 2.          parkjunhong77@gmail.com     key=value에 {@link URLEncoder#encode(String)} 적용
     * </pre>
     *
     * @param parameters
     * @return
     *
     * @since 2020. 10. 21.
     */
    public static String queryParameters(MultiValueMap<String, Object> parameters) {
        AssertUtils2.notNull(parameters);

        List<String> paramBuf = new ArrayList<>();
        parameters.entrySet().stream()//
                .map(e -> {
                    if (e.getValue() == null || e.getValue().isEmpty()) {
                        return null;
                    }

                    StringBuffer param = new StringBuffer();
                    String name = e.getKey();
                    for (Object o : e.getValue()) {
                        if (o == null) {
                            continue;
                        }
                        param.append(encode(name));
                        param.append('=');
                        param.append(encode(o.toString()));
                    }
                    return param.toString();
                }) //
                .filter(p -> p != null) //
                .forEach(param -> paramBuf.add(param));

        return String.join("&", paramBuf);
    }

    /**
     * 쿼리 파라미터 데이터를 하나의 문자열로 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2020. 10. 21.    parkjunhong77@gmail.com     최초 작성
     * 2025. 7. 2.          parkjunhong77@gmail.com     key=value에서 key 의 <code>null</code> 여부 확인.
     * </pre>
     *
     * @param parameters
     *            쿼리 파라미터. 반드시 <name=value>의 쌍을 이루어야 한다.
     * @return
     *
     * @since 2020. 10. 21.
     */
    public static String queryParameters(@Nullable String @Nullable... parameters) {
        if (parameters == null) {
            return "";
        }
        if (parameters.length % 2 != 0) {
            throw ExceptionUtils.newException(IllegalArgumentException.class, "올바르지 않은 파라미터 입니다. paramters=%s", Arrays.toString(parameters));
        }
        LinkedMultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        for (int i = 0; i < parameters.length; i += 2) {
            if (parameters[i] == null) {
                continue;
            }
            paramMap.add(parameters[i], parameters[i + 1]);
        }
        return queryParameters(paramMap);
    }
}
