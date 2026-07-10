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
 * Date  : 2026. 7. 9. 오후 5:03:41
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.environment.resolve;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

/**
 * 주입받은 ResolveStrategy 빈(Bean)들을 이용하여 데이터를 변환하는 환경 리졸버 <br>
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
@Component(EnvironmentResolver.BEAN_QUALIFIER)
public class EnvironmentResolver {

    static final String BEAN_QUALIFIER = "open.commons.spring.web.environment.resolve.EnvironmentResolver";

    /** 식별정보(Type)를 Key로, 변환함수(Function)를 Value로 가지는 불변 맵 */
    private final Map<String, Function<String, String>> strategyRegistry;

    /**
     * 
     * @param strategies
     *            변환기능
     *
     * @since 2026. 7. 9.
     * @version 4.0.0
     */
    public EnvironmentResolver(List<ResolveStrategy> strategies) {
        this.strategyRegistry = strategies.stream()
                .collect(Collectors.toUnmodifiableMap(ResolveStrategy::getStrategy, ResolveStrategy::getResolver));
    }

    /**
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2026. 7. 9.     parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param type
     *            변환기능 식별정보
     * @param target
     *            변환할 정보
     * @return
     *
     * @since 2026. 7. 9.
     * @version 4.0.0
     */
    public String resolve(String type, String target) {
        if (target == null || target.isBlank()) {
            return target;
        }

        var resolver = strategyRegistry.get(type);
        return resolver != null ? resolver.apply(target) : target;
    }
}
