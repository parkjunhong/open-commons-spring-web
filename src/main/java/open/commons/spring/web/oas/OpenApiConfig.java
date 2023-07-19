/*
 * Copyright 2023 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2023. 7. 19. 오전 11:09:41
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.oas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * <a href="https://springdoc.org/">Spring Docs</a> {@link OpenAPI}를 사용하기 위한 설정.<br>
 * 
 * <p>
 * application.yml 내 속성정의는 다음과 같습니다.<br>
 * 
 * 설정 내용 중에 "open-commons.springdoc.open-api.info.contact.[email, name]"은 필수항목입니다.<br>
 * 
 * <pre>
 * open-commons:
 *   springdoc:
 *    open-api:
 *      info:
 *        contact:
 *          email: "user@a.b.c"
 *          name: "user"
 *          url: "..."
 *          extensions:
 *            key: "contact's extensions"
 *        description: "..."
 *        extensions:
 *          key: "value"
 *        license:
 *          identifier: "..."
 *          name: "..."
 *          url: "..."
 *          extensions:
 *            key: "value"
 *        summary: "..."
 *        terms-of-service: "..."
 *        title: "..."
 *      external-docs:
 *        description: "SpringDocs Open API Documentation..."
 *        url: "https://springdoc.org/"
 * </pre>
 * </p>
 * 
 * @since 2023. 7. 19.
 * @version 0.6.0
 * @author parkjunhong77@gmail.com
 * @see <a href="https://springdoc.org/">springdoc.org</a>
 */
@Configuration(OpenApiConfig.BEAN_QUALIFIER)
public class OpenApiConfig {
    public static final String BEAN_QUALIFIER = "open.commons.spring.web.oas.OpenApiConfig";
    public static final String OPEN_API_INFO = "open.commons.spring.web.oas.OpenApiConfig#OPEN_API_INFO";
    public static final String OPEN_API_EXT_DOCS = "open.commons.spring.web.oas.OpenApiConfig#OPEN_API_EXT_DOCS";

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ApplicationContext context;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 7. 19.		박준홍			최초 작성
     * </pre>
     * 
     * @param context
     *            TODO
     *
     *
     * @since 2023. 7. 19.
     * @version 0.6.0
     * @author parkjunhong77@gmail.com
     */
    public OpenApiConfig(@Autowired ApplicationContext context) {
        this.context = context;
    }

    /**
     * Open API 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 7. 19.		박준홍			최초 작성
     * </pre>
     *
     * @return Open API 정보. 단 필수정보(email, name)가 없는 경우 생성되지 않습니다.
     *
     * @since 2023. 7. 19.
     * @version 0.6.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean
    @ConditionalOnProperty(prefix = "open-commons.springdoc.open-api.info", name = { "contact.email", "contact.name" })
    public OpenAPI createOpenAPIInfo() {

        OpenAPI api = new OpenAPI();

        if (this.context.containsBeanDefinition(OPEN_API_INFO)) {
            Info info = this.context.getBean(OPEN_API_INFO, Info.class);
            api.setInfo(info);
        }

        if (this.context.containsBeanDefinition(OPEN_API_EXT_DOCS)) {
            ExternalDocumentation extDoc = this.context.getBean(OPEN_API_EXT_DOCS, ExternalDocumentation.class);
            api.setExternalDocs(extDoc);
        }

        logger.info("[Registered] Open API: {}", api);

        return api;
    }

    /**
     * Open API 외부 문서 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 7. 19.		박준홍			최초 작성
     * </pre>
     *
     * @return Open API 외부 문서 정보. 단, 필수정보(url)가 없는 경우 생성되지 않습니다.
     *
     * @since 2023. 7. 19.
     * @version 0.6.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean(name = OPEN_API_EXT_DOCS)
    @ConditionalOnProperty(prefix = "open-commons.springdoc.open-api.external-docs", name = "url")
    @ConfigurationProperties("open-commons.springdoc.open-api.external-docs")
    public ExternalDocumentation getOpenAPIExternalDocumentation() {
        return new ExternalDocumentation();
    }

    /**
     * Open API 정보를 생성합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 7. 19.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2023. 7. 19.
     * @version 0.6.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see #getOpenAPIInfo()
     * @see #getOpenAPIExternalDocumentation()
     */
    @Bean(name = OPEN_API_INFO)
    @ConditionalOnProperty(prefix = "open-commons.springdoc.open-api.info", name = { "contact.email", "contact.name" })
    @ConfigurationProperties("open-commons.springdoc.open-api.info")
    public Info getOpenAPIInfo() {
        return new Info();
    }

}
