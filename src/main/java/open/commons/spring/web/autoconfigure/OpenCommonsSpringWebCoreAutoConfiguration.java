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
 * Date  : 2026. 4. 20. 오후 1:06:23
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Import;

import open.commons.spring.web.configure.AspectConfiguration;
import open.commons.spring.web.configure.BeansConfiguration;
import open.commons.spring.web.configure.CustomWebMvcConfiguration;
import open.commons.spring.web.configure.LogFeatureDecorationConfiguration;
import open.commons.spring.web.configure.OpenApiConfiguration;
import open.commons.spring.web.configure.ServletConfiguration;
import open.commons.spring.web.configure.concurrent.async.AsyncTaskExecutorConfiguration;
import open.commons.spring.web.configure.concurrent.async.ScheduledTaskSchedulerConfiguration;
import open.commons.spring.web.configure.concurrent.executor.ExecutorConfiguration;
import open.commons.spring.web.configure.concurrent.executor.ScheduledExecutorConfiguration;
import open.commons.spring.web.configure.concurrent.task.TaskExecutorConfiguration;
import open.commons.spring.web.configure.concurrent.task.TaskSchedulerConfiguration;
import open.commons.spring.web.configure.exception.ExceptionHttpStatusBinderConfiguration;
import open.commons.spring.web.configure.resttemplate.RestTemplateConfiguration;

/**
 * <b><i>{@code open.commons.spring.web.autoconfigure}</i></b> 패키지에 선언된 공통 라이브러리(Starter)의 메인
 * {@link AutoConfiguration} 진입점 클래스.<br>
 * 
 * <p>
 * <b>🎯 Spring Boot Starter 아키텍처 설계 원칙 (Best Practice)</b>
 * </p>
 * 
 * <p>
 * 이 자동 구성 클래스는 블랙박스 형태의 무분별한 컴포넌트 스캔(Component Scan)을 배제하고, 프레임워크의 생명주기 안정성과 라이브러리 사용자의 제어권을 보장하기
 * 위해 다음과 같은 설계 원칙을 따릅니다.
 * </p>
 * 
 * <ul>
 * <li><b>1. 명시적 컴포넌트 조립 (Explicit Component Assembly)</b><br>
 * 자동 스캔으로 인한 빈(Bean) 생명주기 충돌 및 의도치 않은 중복 등록을 원천 차단하기 위해, 모든 공통 빈은 용도별(Aspect, WebMvc, Async 등) 커스텀
 * {@code @Configuration} 클래스에 명시적으로 선언되며 {@link Import}를 통해 중앙에서 안전하게 조립됩니다.</li>
 * 
 * <li><b>2. 관심사의 분리 및 조건부 평가 최적화 (Conditional Evaluation)</b><br>
 * 논리적인 도메인 단위로 분리된 설정 클래스들은 클래스 레벨에서 {@code @ConditionalOnProperty} 등을 통해 평가됩니다. 이를 통해 라이브러리 사용자는
 * {@code application.yml} 속성 하나만으로 거대한 모듈의 구동 여부를 손쉽게 On/Off 할 수 있으며, 불필요한 빈 로드를 막아 애플리케이션 기동 속도를
 * 최적화합니다.</li>
 * 
 * <li><b>3. 사용자 주도권 보장 (Safe Overriding)</b><br>
 * 제공되는 핵심 빈들은 {@code @ConditionalOnMissingBean}을 동반하여 등록됩니다. 라이브러리를 도입하는 각 서비스에서 고유한 커스텀 빈을 등록할 경우,
 * 프레임워크가 제공하는 기본값은 우아하게 양보되어 충돌(NoUniqueBeanDefinitionException) 없이 완벽한 유연성을 제공합니다.</li>
 * </ul>
 * 
 * <pre>
 * [개정이력]
 * 날짜       | 작성자                   |   내용
 * -----------------------------------------------------
 * 2026. 4. 20.     parkjunhong77@gmail.com     최초 작성
 * 2026. 5. 6.      parkjunhong77@gmail.com     Spring Boot Starter 아키텍처 정석에 따른 ComponentScan 제거 및 기능별 Configuration 명시적 Import 구조로 전면 리팩토링
 * 2026. 5. 11.     parkjunohng77@gmail.com     `Authorized` 관련 설정 이관.
 * </pre>
 * 
 * @since 2026. 4. 20.
 * @version 4.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 * @see AuthorizedObjectJsonMessageConverterAutoConfiguration
 * @see AuthorizedResourceBuiltinHandlerAutoConfiguration
 * @see AuthorizedResourcesAutoConfiguration
 * @see GlobalServletAutoConfiguration
 * @see LogFeatureAutoConfiguration
 */
@AutoConfiguration(value = OpenCommonsSpringWebCoreAutoConfiguration.BEAN_QUALIFIER)
// 1. 설정 레코드들이 모여있는 패키지를 스캔하도록 지정합니다.
@ConfigurationPropertiesScan(basePackages = { //
        "open.commons.spring.web.configure" //
})
// 2. [명시적 로드] 순서와 조건부 평가가 중요한 @Configuration 클래스들은 @Import로 관리합니다.
@Import({ //
        AspectConfiguration.class //
        , BeansConfiguration.class //
        , CustomWebMvcConfiguration.class//
        , LogFeatureDecorationConfiguration.class //
        , OpenApiConfiguration.class //
        , AsyncTaskExecutorConfiguration.class //
        , ScheduledTaskSchedulerConfiguration.class //
        , ExecutorConfiguration.class //
        , ScheduledExecutorConfiguration.class //
        , TaskExecutorConfiguration.class //
        , TaskSchedulerConfiguration.class //
        , ExceptionHttpStatusBinderConfiguration.class //
        , RestTemplateConfiguration.class //
        , ServletConfiguration.class //
})
public class OpenCommonsSpringWebCoreAutoConfiguration {

    static final String BEAN_QUALIFIER = "open.commons.spring.web.autoconfigure.OpenCommonsSpringWebCoreAutoConfiguration";

    public OpenCommonsSpringWebCoreAutoConfiguration() {
    }
}