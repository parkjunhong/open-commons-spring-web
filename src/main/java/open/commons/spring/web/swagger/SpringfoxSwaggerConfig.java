/*
 *
 * This file is generated under this project, "open-commons-spring-web".
 *
 * Date  : 2020. 10. 30. 오후 4:54:58
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.spring.web.swagger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

/**
 * 
 * @since 2020. 10. 30.
 * @version
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public abstract class SpringfoxSwaggerConfig implements InitializingBean {

    public static final String SWAGGER_API_INFO = "open.commons.spring.web.swagger.SpringfoxSwaggerConfig#SWAGGER_API_INFO";

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private Map<String, SwaggerApiInfo> swaggerApis;

    @Autowired
    private ApplicationContext context;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 30.		박준홍			최초 작성
     * </pre>
     *
     * @since 2020. 10. 30.
     */
    public SpringfoxSwaggerConfig() {
    }

    /**
     * @since 2020. 10. 30.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @SuppressWarnings("unchecked")
    @Override
    public void afterPropertiesSet() throws Exception {
        this.swaggerApis = (Map<String, SwaggerApiInfo>) this.context.getBean(SWAGGER_API_INFO);
        this.swaggerApis.forEach((k, v) -> {
            logger.info("[loaded] swagger-apiinfo. {} = {}", k, v);
        });
    }

    /**
     * 그룹명에 해당하는 {@link ApiInfo} 를 제공한다. 없는 경우 default 정보를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param groupName
     * @return
     *
     * @since 2020. 10. 30.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    protected ApiInfo api(String groupName) {
        SwaggerApiInfo conf = this.swaggerApis.get(groupName);

        return conf == null //
                ? new ApiInfo("default", null, "default", null, null, null, null, new ArrayList<>()) //
                : new ApiInfo(conf.getTitle(), conf.getDescription(), conf.getVersion(), conf.getTermsOfServiceUrl(), toContact(conf.getContactName()), conf.getLicense(),
                        conf.getLicenseUrl(), new ArrayList<>());
    }

    /**
     * {@link ApiInfo} 정보를 제공한다. <br>
     * 아래 2개의 어노테이션을 적용하여 구현한다.
     * <ul>
     * <li>{@link Bean}: Qualifer 로는 {@link #SWAGGER_API_INFO} 를 이용한다.
     * <li>{@link ConfigurationProperties}: yml 속성경로를 설정한다.
     * </ul>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 30.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 10. 30.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    @Bean(SWAGGER_API_INFO)
    @ConfigurationProperties("springfox.swagger.api-config")
    public Map<String, SwaggerApiInfo> getSwaggerApiInfo() {
        return new HashMap<>();
    }

    /**
     * {@link Contact} 를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param contact
     * @return
     *
     * @since 2020. 10. 30.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    private Contact toContact(io.swagger.models.Contact contact) {
        return new Contact(contact.getName(), contact.getUrl(), contact.getEmail());
    }

}
