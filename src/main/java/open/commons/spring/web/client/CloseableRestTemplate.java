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
 * Date  : 2025. 8. 26. 오전 11:07:06
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.client;

import java.io.Closeable;
import java.net.URI;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PreDestroy;
import jakarta.validation.constraints.NotEmpty;

import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import open.commons.spring.web.utils.CloseableUtils;

/**
 * {@link RestTemplate}에 {@link Closeable} 인터페이스를 적용하여, 내부적으로 사용하는 {@link ClientHttpRequestFactory}
 * 자원을 해제하는 기능을 지원하는 Wrapper 클래스.
 * 
 * @since 2025. 8. 26.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class CloseableRestTemplate extends RestTemplate implements Closeable {

    private boolean closed = false;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 8. 26.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     */
    public CloseableRestTemplate() {
        super();
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 8. 26.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param requestFactory
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     */
    public CloseableRestTemplate(ClientHttpRequestFactory requestFactory) {
        super(requestFactory);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 8. 26.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param messageConverters
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     */
    public CloseableRestTemplate(@NotEmpty List<HttpMessageConverter<?>> messageConverters) {
        super(messageConverters);
    }

    /**
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     *
     * @see java.io.Closeable#close()
     */
    @Override
    @PreDestroy
    public void close() {
        CloseableUtils.close(getRequestFactory());
        this.closed = true;
    }

    /**
     * {@inheritDoc}
     *
     * @since 2026. 5. 20.
     * @version 4.0.0
     *
     * @see org.springframework.web.client.RestTemplate#getForEntity(java.lang.String,
     *      java.lang.Class, java.util.Map)
     */
    @Override
    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType,
            Map<String, ? extends @Nullable Object> uriVariables) throws RestClientException {

        if (closed) {
            throw new IllegalStateException("CloseableRestTemplate이 이미 종료되었습니다.");
        }

        return super.getForEntity(url, responseType, uriVariables);
    }

    /**
     * {@inheritDoc}
     *
     * @since 2026. 5. 20.
     * @version 4.0.0
     *
     * @see org.springframework.web.client.RestTemplate#getForEntity(java.lang.String,
     *      java.lang.Class, java.lang.Object[])
     */
    @Override
    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, @Nullable Object... uriVariables)
            throws RestClientException {

        if (closed) {
            throw new IllegalStateException("CloseableRestTemplate이 이미 종료되었습니다.");
        }

        return super.getForEntity(url, responseType, uriVariables);
    }

    /**
     * {@inheritDoc}
     *
     * @since 2026. 5. 20.
     * @version 4.0.0
     *
     * @see org.springframework.web.client.RestTemplate#getForEntity(java.net.URI, java.lang.Class)
     */
    @Override
    public <T> ResponseEntity<T> getForEntity(URI url, Class<T> responseType) throws RestClientException {

        if (closed) {
            throw new IllegalStateException("CloseableRestTemplate이 이미 종료되었습니다.");
        }

        return super.getForEntity(url, responseType);
    }

    /**
     * {@inheritDoc}
     *
     * @since 2026. 5. 20.
     * @version 4.0.0
     *
     * @see org.springframework.web.client.RestTemplate#getForObject(java.lang.String,
     *      java.lang.Class, java.util.Map)
     */
    @Override
    public <T> @Nullable T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables)
            throws RestClientException {

        if (closed) {
            throw new IllegalStateException("CloseableRestTemplate이 이미 종료되었습니다.");
        }

        return super.getForObject(url, responseType, uriVariables);
    }

    /**
     * {@inheritDoc}
     *
     * @since 2026. 5. 20.
     * @version 4.0.0
     *
     * @see org.springframework.web.client.RestTemplate#getForObject(java.lang.String,
     *      java.lang.Class, java.lang.Object[])
     */
    @Override
    public <T> @Nullable T getForObject(String url, Class<T> responseType, @Nullable Object... uriVariables)
            throws RestClientException {

        if (closed) {
            throw new IllegalStateException("CloseableRestTemplate이 이미 종료되었습니다.");
        }

        return super.getForObject(url, responseType, uriVariables);
    }

    /**
     * {@inheritDoc}
     *
     * @since 2026. 5. 20.
     * @version 4.0.0
     *
     * @see org.springframework.web.client.RestTemplate#getForObject(java.net.URI, java.lang.Class)
     */
    @Override
    public <T> @Nullable T getForObject(URI url, Class<T> responseType) throws RestClientException {

        if (closed) {
            throw new IllegalStateException("CloseableRestTemplate이 이미 종료되었습니다.");
        }

        return super.getForObject(url, responseType);
    }
}
