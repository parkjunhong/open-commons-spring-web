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
 * Date  : 2026. 7. 9. 오후 4:36:40
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.environment.resolve.strategy;

import java.util.function.Function;
import java.util.regex.Pattern;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import open.commons.spring.web.environment.resolve.ResolveStrategy;

/**
 * 서비스가 'docker 환경'에서 구동될 때, 연결정보에 <code>localhost</code> 또는 <code>127.0.0.1</code>을
 * <code>host-gateway</code>에 의해서 설정된 <code>docker bridge</code> 네트워크의 Gateway IP 에 해당하는 '도메인'으로
 * 변환하는 기능을 제공합니다. <br>
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
@Component(DockerHostGatewayStrategy.BEAN_QUALIFIER)
@ConditionalOnProperty(name = "open-commons.spring.web.environment.resolve.strategy.docker-host-gateway.enable", havingValue = "true", matchIfMissing = true)
@Validated
public class DockerHostGatewayStrategy implements ResolveStrategy {

    /** Strategy 이름 */
    public static final String NAME = "docker.host-gateway";

    static final String BEAN_QUALIFIER = "open.commons.spring.web.environment.resolve.DockerHostGatewayStrategy";

    /** 서비스를 실행할 때, <code>--infra.deployment.type</code>값으로 전달받는 정보. */
    public final String DOCKER_DEPLOYMENT_TYPE = "container";

    private final Function<String, String> resolverFunction;

    /**
     * 
     *
     * @param env
     *            TODO
     * @since 2026. 7. 9.
     * @version 4.0.0
     */
    public DockerHostGatewayStrategy(Environment env) {
        var deploymentType = env.getProperty("infra.deployment.type");
        boolean isContainer = DOCKER_DEPLOYMENT_TYPE.equalsIgnoreCase(deploymentType);
        var hostGateway = env.getProperty("open-commons.docker.extra_hosts.host_gateway");

        var pattern = Pattern.compile("(?i)(://|@|^)(localhost|127\\.0\\.0\\.1)(:|/|$)");

        // 생성자 시점에 Environment를 평가하여, 변환용 Function을 미리 구성해 둡니다.
        this.resolverFunction = target -> {
            if (!isContainer || hostGateway == null || hostGateway.isBlank() || target == null || target.isBlank()) {
                return target;
            }
            return pattern.matcher(target).replaceAll("$1" + hostGateway + "$3");
        };
    }

    /**
     * {@inheritDoc}
     *
     * @since 2026. 7. 9.
     * @version 4.0.0
     *
     * @see open.commons.spring.web.environment.resolve.ResolveStrategy#getResolver()
     */
    @Override
    public Function<String, String> getResolver() {
        return this.resolverFunction;
    }

    /**
     * {@inheritDoc}
     *
     * @since 2026. 7. 9.
     * @version 4.0.0
     *
     * @see open.commons.spring.web.environment.resolve.ResolveStrategy#getStrategy()
     */
    @Override
    public String getStrategy() {
        return DockerHostGatewayStrategy.NAME;
    }

}
