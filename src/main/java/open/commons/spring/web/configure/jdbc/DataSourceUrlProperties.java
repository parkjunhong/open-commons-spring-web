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
 * Date  : 2026. 7. 9. 오후 5:41:13
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.configure.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.jspecify.annotations.Nullable;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {@link DataSource}를 구현한 클래스들에 대해서 <code>JDBC URL</code>정보를 수정하는 메소드 이름을 제공합니다. <br>
 * 기본값은 {@link DataSourcePostProcessor#DEFAULT_URL_SETTERS}
 * <code>("url", "jdbcUrl", "connectionUrl")</code>이며, <code>JDBC URL</code> 값을 해당하는 새로운 설정을 추가하려면
 * 설정파일(yml)에 아래와 같이 설정합니다.
 * 
 * <pre>
 * open-commons:
 *   spring:
 *     jdbc:
 *       datasource:
 *         url-properties:
 *           - dbmsUrl    
 *           - conUrl
 * </pre>
 * 
 * <pre>
 * [개정이력]
 *     날짜        | 작성자                   |   내용
 * -----------------------------------------------------
 * 2026. 7. 9.     parkjunhong77@gmail.com     최초 작성
 * </pre>
 *
 * @since 2026. 7. 9.
 * @version 4.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
@ConfigurationProperties(prefix = "open-commons.spring.jdbc.datasource")
public record DataSourceUrlProperties(
        /**
         * 기본 URL setter(<code>url</code>, <code>jdbcUrl</code>, <code>connectionUrl</code>) 외에 사용자가
         * 추가로 지정할 setter 명칭들
         */
        @Nullable List<String> urlProperties) {

    /**
     * 데이터 바인딩 시 null 방어 및 불변(Immutable) 리스트 보장을 위한 컴팩트 생성자
     */
    public DataSourceUrlProperties {
        if (urlProperties == null) {
            urlProperties = List.of();
        } else {
            urlProperties = List.copyOf(urlProperties);
        }
    }
}