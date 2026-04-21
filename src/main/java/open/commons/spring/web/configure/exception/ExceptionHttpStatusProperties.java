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
 * Date  : 2026. 4. 21. 오후 4:10:00
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.configure.exception;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;

import open.commons.spring.web.configure.properties.Const;

/**
 * 예외 클래스 이름과 HTTP 상태 코드를 매핑하는 설정 레코드.
 *
 * @param properties
 *            예외 클래스(FQCN)를 키로, Spring의 HttpStatus Enum 이름을 값으로 가지는 매핑 정보입니다.
 *            설정이 누락된 경우 빈 맵(Empty Map)으로 초기화되어 NullPointerException을 방지합니다.
 *
 * @since 2026. 4. 21.
 * @version 4.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
@ConfigurationProperties(prefix = Const.PROPERTIES_OPEN_COMMONS_SPRING_WEB_ROOT_PATH + ".exception-httpstatus-binder")
public record ExceptionHttpStatusProperties(Map<String, HttpStatus> properties) {
    /**
     * 표준 생성자 재정의 (Compact Constructor). YAML에 해당 설정이 아예 누락되었을 경우 properties가
     * null이 되는 것을 방지합니다.
     */
    public ExceptionHttpStatusProperties {
        if (properties == null) {
            // 불변 빈 맵으로 초기화
            properties = Map.of();
        } else {
            // 외부 변경을 막기 위해 방어적 복사(Defensive Copy) 적용
            properties = Map.copyOf(properties);
        }
    }
}
