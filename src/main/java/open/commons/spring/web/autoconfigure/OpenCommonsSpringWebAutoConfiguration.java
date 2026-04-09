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
 * Date  : 2026. 4. 9. 오후 6:59:41
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Import;

import open.commons.spring.web.autoconfigure.configuration.AuthorizedObjectMessageConfigureConfiguration;
import open.commons.spring.web.autoconfigure.configuration.AuthorizedObjectMessageConverterConfiguration;
import open.commons.spring.web.autoconfigure.configuration.AuthorizedResourceBuiltinHandlerConfiguration;
import open.commons.spring.web.autoconfigure.configuration.AuthorizedResourcesConfiguration;
import open.commons.spring.web.autoconfigure.configuration.GlobalServletConfiguration;
import open.commons.spring.web.autoconfigure.configuration.LogFeatureConfiguration;

/**
 * Open-Commons Spring Web 모듈의 기능들을 자동으로 설정합니다.<br>
 * 클래스패스에 웹 애플리케이션 환경이 구성되어 있을 때만 동작합니다.
 *
 * <pre>
 * [개정이력]
 * 날짜        | 작성자                    | 내용
 * ----------------------------------------------------------------------
 * 2026. 4. 9.      parkjunhong77@gmail.com     순수 자동 설정 아키텍처로 개편
 * </pre>
 *
 * @since 2026. 4.9.
 * @version 4.0.0
 * @author parkjunhong77@gmail.com
 */
@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@Import({ //
        AuthorizedResourceBuiltinHandlerConfiguration.class //
        , AuthorizedObjectMessageConfigureConfiguration.class //
        , AuthorizedObjectMessageConverterConfiguration.class //
        , AuthorizedResourcesConfiguration.class //
        , LogFeatureConfiguration.class //
        , GlobalServletConfiguration.class //
})
public class OpenCommonsSpringWebAutoConfiguration {
    // 내부적으로 빈을 직접 생성하지 않고, @Import를 통해 기존 설정 클래스들을 위임 로드합니다.
}