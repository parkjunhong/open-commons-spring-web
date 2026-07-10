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

import java.util.ArrayList;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <br>
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
public class JdbcDataSourceSettersProperties {

    /**
     * 기본 URL setter(<code>url</code>, <code>jdbcUrl</code>, <code>connectionUrl</code>) 외에 사용자가 추가로
     * 지정할 setter 명칭들
     * 
     * @see DataSourceUrlProcessor
     */
    private List<String> additionalSetters = new ArrayList<>();

    /**
     * 추가로 지정된 <code>setter</code> 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2026. 7. 9.     parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2026. 7. 9.
     * @version 4.0.0
     */
    public List<String> getAdditionalSetters() {
        return additionalSetters;
    }

    public void setAdditionalSetters(@Nullable List<String> additionalSetters) {
        if (additionalSetters == null) {
            return;
        }
        this.additionalSetters = additionalSetters;
    }

}
