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
 * Date  : 2026. 7. 9. 오후 5:48:06
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.configure.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import open.commons.spring.web.environment.resolve.EnvironmentResolver;
import open.commons.spring.web.environment.resolve.strategy.DockerHostGatewayStrategy;

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
@Component(DataSourceUrlProcessor.BEAN_QUALIFIER)
public class DataSourceUrlProcessor implements BeanPostProcessor {

    static final String BEAN_QUALIFIER = "open.commons.spring.web.configure.jdbc.DataSourceUrlProcessor";

    // 라이브러리 기본값 (하드코딩)
    private static final List<String> DEFAULT_SETTERS = List.of("url", "jdbcUrl", "connectionUrl");
    private final EnvironmentResolver resolver;

    private final JdbcDataSourceSettersProperties properties;

    /**
     * 
     * @param resolver
     *            환경설정 리졸버
     * @param properties
     *
     * @since 2026. 7. 9.
     * @version 4.0.0
     */
    public DataSourceUrlProcessor(EnvironmentResolver resolver, JdbcDataSourceSettersProperties properties) {
        this.resolver = resolver;
        this.properties = properties;
    }

    /**
     * {@inheritDoc}
     *
     * @since 2026. 7. 9.
     * @version 4.0.0
     *
     * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object,
     *      java.lang.String)
     */
    @Override
    public @Nullable Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DataSource) {
            BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(bean);

            // 기본값과 사용자가 추가한 값을 합쳐서 리스트 생성
            var allSetters = new ArrayList<>(DEFAULT_SETTERS);
            if (properties.getAdditionalSetters() != null) {
                allSetters.addAll(properties.getAdditionalSetters());
            }

            for (String propName : allSetters) {
                if (wrapper.isWritableProperty(propName)) {
                    Object originalValue = wrapper.getPropertyValue(propName);

                    if (originalValue instanceof String originalUrl) {
                        String resolvedUrl = resolver.resolve(DockerHostGatewayStrategy.NAME, originalUrl);
                        if (!originalUrl.equals(resolvedUrl)) {
                            wrapper.setPropertyValue(propName, resolvedUrl);
                        }
                    }
                }
            }
        }
        return bean;
    }
}
