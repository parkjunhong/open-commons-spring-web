/*
 * Copyright 2026 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2026. 4. 20. 오후 7:29:46
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.configure.resttemplate;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import open.commons.spring.web.configure.properties.Const;

/**
 * {@link RestTemplate}의 {@link HttpComponentsClientHttpRequestFactory} 설정을 위한
 * 프로퍼티 레코드.
 * 
 * <pre>
 * [개정이력]
 *      날짜       | 작성자                   |   내용
 * -----------------------------------------------------
 * 2026. 4. 20.		parkjunohng77@gmail.com        최초 작성
 * </pre>
 * 
 * @since 2026. 4. 20.
 * @version 4.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
@ConfigurationProperties(prefix = Const.PROPERTIES_OPEN_COMMONS_SPRING_WEB_ROOT_PATH + ".resttemplate.requestfactory")
public record RestTemplateProperties(
        /** 연결 요청 타임아웃 (ms) */
        @DefaultValue("30000") int connectionRequestTimeout, /** 읽기 타임아웃 (ms) */
        @DefaultValue("300000") int readTimeout) {
    /**
     * 속성값을 복사하여 새로운 레코드를 생성합니다. <br>
     * 
     * @param props
     *            복사할 {@link RestTemplateProperties} 객체
     *
     * @since 2026. 4. 21.
     * @version 4.0.0
     */
    public static RestTemplateProperties copyOf(RestTemplateProperties props) {
        return new RestTemplateProperties(props.connectionRequestTimeout(), props.readTimeout());
    }
}
