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
 * Date  : 2025. 8. 26. 오전 11:41:05
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.utils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

import org.springframework.http.client.AbstractClientHttpRequestFactoryWrapper;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import open.commons.core.utils.AssertUtils2;
import open.commons.spring.web.client.CloseableRestTemplate;

/**
 * 
 * @since 2025. 8. 26.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class CloseableUtils {
    private CloseableUtils() {
    }

    /**
     * {@link ClientHttpRequestFactory} 자원을 해제합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 8. 26.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param chrf
     *            {@link ClientHttpRequestFactory} 객체.
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     */
    public static void close(ClientHttpRequestFactory chrf) {
        close(chrf, Collections.newSetFromMap(new IdentityHashMap<>()));
    }

    /**
     * {@link ClientHttpRequestFactory} 자원을 해제합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 8. 26.    parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 16.    parkjunhong77@gmail.com     JDK 25 현행화: Deprecated된 isAccessible() 폐기 및 trySetAccessible() 적용
     * </pre>
     *
     * @param chrf
     *            {@link ClientHttpRequestFactory} 객체.
     * @param visited
     *            동일 객체에 대한 중복방지를 위한 기록
     *
     * @since 2025. 8. 26.
     * @version 4.0.0
     */
    public static void close(ClientHttpRequestFactory chrf, Set<Object> visited) {
        AssertUtils2.notNull(visited);

        if (chrf == null || !visited.add(chrf)) {
            return;
        }

        switch (chrf) {
            case AbstractClientHttpRequestFactoryWrapper wrapper -> {
                for (Field f : ClassInspector.getAllFields(wrapper.getClass())) {
                    // 현재 컨텍스트에서 해당 객체 필드에 실제 접근 가능한지 확인하는 canAccess() 사용
                    boolean alreadyAccessible = f.canAccess(wrapper);

                    // 안전하게 접근 권한 획득을 시도하는 trySetAccessible() 사용
                    if (alreadyAccessible || f.trySetAccessible()) {
                        try {
                            if (ClientHttpRequestFactory.class.isAssignableFrom(f.getType())) {
                                close((ClientHttpRequestFactory) f.get(wrapper), visited);
                            }
                        } catch (IllegalArgumentException | IllegalAccessException e) {
                            // ignored: 필드 값 읽기 실패 시 건너뜀
                        } finally {
                            // 원래 접근 불가능했던 상태라면, 읽은 후 다시 닫아줌
                            if (!alreadyAccessible) {
                                try {
                                    f.setAccessible(false);
                                } catch (Exception e) {
                                    // ignored: 상태 복구 실패 시 무시
                                }
                            }
                        }
                    }
                }
            }
            case HttpComponentsClientHttpRequestFactory hcFactory -> {
                try {
                    hcFactory.destroy();
                } catch (Exception e) {
                    // ignored: 내부 자원 파기(destroy) 중 발생하는 예외 무시
                }
            }
            default -> {
                // 처리할 타입이 아닌 경우 (안전한 스킵)
            }
        }
    }

    /**
     * {@link RestTemplate}가 사용하는 {@link ClientHttpRequestFactory} 자원을 해제합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 8. 26.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param restTemplate
     *            {@link RestTemplate} 객체
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     */
    public static void close(RestTemplate restTemplate) {
        AssertUtils2.notNull(restTemplate);

        if (restTemplate instanceof CloseableRestTemplate) {
            ((CloseableRestTemplate) restTemplate).close();
        } else {
            close(restTemplate.getRequestFactory());
        }
    }
}
