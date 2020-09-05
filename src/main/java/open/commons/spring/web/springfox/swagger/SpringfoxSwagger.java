/*
 *
 * This file is generated under this project, "open-commons-spring-web".
 *
 * Date  : 2020. 9. 5. 오후 11:52:59
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.spring.web.springfox.swagger;

/**
 * springfox-swagger, springfox-swagger-ui 를 지원하기 위한 설정.
 * 
 * @since 2020. 9. 5.
 * @version
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class SpringfoxSwagger {
    /** /v2/api-docs */
    public static final String URL_V2_API_DOCS = "/v2/api-docs";
    /** configurations */
    public static final String URL_CONFIGURATION = "/configuration/**";
    /** configurations security */
    public static final String URL_CONFIGURATION_SECURITY = "/configuration/security";
    /** configurations ui */
    public static final String URL_CONFIGURATION_UI = "/configuration/ui";
    /** swagger main page */
    public static final String URL_HTML = "/swagger-ui.html";
    /** configurations */
    public static final String URL_RESOURCES = "/swagger-resources/**";
    /** swagger main page css, font, js, etc ... */
    public static final String URL_WEBJARS = "/webjars/**";
    /** use to frowarindg to 'swagger-ui' to 'swagger-ui.html' */
    public static final String URL_UI = "/swagger-ui";
    /** html 위치 */
    public static final String RESOURCE_HTML = "classpath:/META-INF/resources/";
    /** html 에서 사용되는 css, font, js, etct ... */
    public static final String RESOURCE_WEBJARS = "classpath:/META-INF/resources/webjars/";

    private SpringfoxSwagger() {
    }

    /**
     * Springfox Swagger, Swagger-UI 를 사용하기 위해 제공되는 URL 목록을 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 9. 5.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 9. 5.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static final String[] getUrlList() {
        return new String[] { URL_V2_API_DOCS, //
                URL_CONFIGURATION, //
                URL_CONFIGURATION_SECURITY, //
                URL_CONFIGURATION_UI, //
                URL_HTML, //
                URL_RESOURCES, //
                URL_WEBJARS, //
                URL_UI, //
        };
    }

}
