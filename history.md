[2021/06/11]
- Add
  + open.commons.spring.web.rest.RestUtils2 추가
    - 기존 RestUtils의 메소드 정의 확장: REQ, RES -> REQ, RES, RET
      - REQ: Http Reqeust Entity T ype 
      - RES: Http Response Type
      - RET: REST API를 연동한 메소드에거 제공할 데이타 타입.
- deprecated
  + open.commons.spring.web.rest.RestUtils
    - exchange(RestTemplate, HttpMethod, String, String, int, String, HttpEntity&lt;REQ&gt;, Class&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RES&gt;&gt;, Function&lt;Exception, Result&lt;RES&gt;&gt;)
    - exchange(RestTemplate, HttpMethod, String, String, int, String, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RES&gt;&gt;, Function&lt;Exception, Result&lt;RES&gt;&gt;)
    - exchange(RestTemplate, HttpMethod, String, String, int, String, String, HttpEntity&lt;REQ&gt;, Class&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RES&gt;&gt;, Function&lt;Exception, Result&lt;RES&gt;&gt;)
    - exchange(RestTemplate, HttpMethod, String, String, int, String, String, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RES&gt;&gt;, Function&lt;Exception, Result&lt;RES&gt;&gt;)
    - exchange(RestTemplate, HttpMethod, URI, HttpEntity&lt;REQ&gt;, Class&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RES&gt;&gt;, Function&lt;Exception, Result&lt;RES&gt;&gt;)
    - exchange(RestTemplate, HttpMethod, URI, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RES&gt;&gt;, Function&lt;Exception, Result&lt;RES&gt;&gt;)

[2021/04/23]
- Add
  + open.commons.spring.web.mvn.IAsyncJobHandler
    - getAsyncManagerHolder()
    - register(K, Future&lt;?&gt;)
    - unregister(K) 

    
[2021/01/13]
- New
  + open.commons.spring.web.mvc.IAsyncJobHanlder
- Deprecated
  + open.commons.spring.web.mvc.service.IAsyncHandlerService


[2020/12/09]
- Update
  + open.commons.spring.web.config.ResourceConfiguration
    - getRequestFactory(HttpClient, RestTemplateRequestFactoryResource): access modifier 변경 (private -> public static)
    - getRestTemplate(): @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS) 적용
    - getRestTemplateAllowPrivateCA(): @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS) 적용
  + open.commons.spring.web.rest.RestUtils
    - createHttpsClient(boolean): HttpClientConnection을 Thread-Safe 하게 생성하기 위한  HttpClientConnectionManager 변경
      * BasicHttpClientConnectionManager -> PoolingHttpClientConnectionManager

[2020/11/26]
- New
  + open.commons.spring.web.mvc.service.IAsyncHandlerService
  + open.commons.spring.web.mvc.service.AbstractSshService
- Add
  + open.commons.spring.web.rest.RestUtils
    - exchange(Supplier&lt;ResponseEntity&lt;RES&gt;&gt;, HttpMethod, URI, HttpEntity&lt;REQ&gt;, Object, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RES&gt;&gt;, Function&lt;Exception, Result&lt;RES&gt;&gt;)
- Update
  + open.commons.spring.web.rest.RestUtils 
    - exchange(RestTemplate, HttpMethod, URI, HttpEntity&lt;REQ&gt;, Class&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RES&gt;&gt;, Function&lt;Exception, Result&lt;RES&gt;&gt;)
    - exchange(RestTemplate, HttpMethod, URI, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RES&gt;&gt;, Function&lt;Exception, Result&lt;RES&gt;&gt;)
- Delete
  + open.commons.spring.web.rest.RestUtils
    - createArrayResponseType(Class&lt;T&gt;)
    - createResponseType(Class&lt;T&gt;)    
- Deprecated
  + open.commons.spring.web.mvc.service.AsyncHandlerService

[2020/11/23]
- Add
  + open.commons.spring.web.rest.RestUtils
    - createArrayResponseType(Class&lt;T&gt;)
    - createClient()
    - createHttpsClient(boolean)
    - createRegistryBuilder(boolean)
    - createResponseType(Class&lt;T&gt;)
- Update
  + open.commons.spring.web.config.ResourceConfiguration
    - getRestTemplateAllowPrivateCA() <- getRestTemplateIgnoreHostNameVerification(): 메소드 이름변경
    
[2020/11/21]
- Add
  + open.commons.spring.web.config.ResourceConfiguration
    - getRequestFactory(HttpClient): ClientHttpRequestFactory  제공함수 별도 분리
    - getRestTemplateIgnoreHostNameVerification(): 호스트명 확인 무시 RestTemplate 제공


[2020/11/19]
- Add
  + open.commons.spring.web.rest.RestUtils
    - exchange(RestTemplate, HttpMethod, String, String, int, String, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;)
    - exchange(RestTemplate, HttpMethod, String, String, int, String, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RES&gt;&gt;, Function&lt;Exception, Result&lt;RES&gt;&gt;)
    - exchange(RestTemplate, HttpMethod, String, String, int, String, String, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;)
    - exchange(RestTemplate, HttpMethod, String, String, int, String, String, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RES&gt;&gt;, Function&lt;Exception, Result&lt;RES&gt;&gt;)
    - exchange(RestTemplate, HttpMethod, URI, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RES&gt;&gt;, Function&lt;Exception, Result&lt;RES&gt;&gt;)

[2020/11/11]
- Add
  + open.commons.spring.web.mvc.service.AsyncHandlerService: 비동기(Future&lt;V&gt; 반환)로 수행하는 메소드를 제어하는 기능 제공
- Modify
  + open.commons.spring.web.handler.DefaultGlobalInterceptor: 상위 클래스 변경
    - HandlerInterceptorAdapter(Deprecated) -> AsyncHandlerInterceptor
- Update
  + open.commons.spring.web.swagger.SpringfoxSwaggerConfig:
    - getSwaggerApiInfo(): 직접 구현

[2020/11/10]
- Dependencies
  + open.commons.core: 1.8.0-SNAPSHOT


[2020/10/21]
- Add
  + open.commons.spring.web.swagger
    - SpringfoxSwaggerConfig: API 설정 클래스.
    - SwaggerApiInfo: API 정의 클래스.

[2020/10/21]
- Add
  + open.commons.spring.web.rest.RestUtils
    - queryParameters(MultiValueMap&lt;String, Object&gt;)
    - queryParameters(String...)    

[2020/09/05]
- Add
  + open.commons.spring.web.springfox.swagger.SpringfoxSwagger
    - springfox-swagger, springfox-swagger-ui 를 위한 설정
- Update
  + open.commons.spring.web.config.CustomWebMvcConfigurer
    - Spring Security 자동 설정 방지 추가: @SpringBootApplication(exclude = { SecurityAutoConfiguration.class })

[2020/09/04]
- Add
  + open.commons.spring.web.config.SpringfoxSwaggerWebSecurityConfigurer 


[2020/09/03]
- Add
  + pom.xml: swagger2 적용
- Update
  + open.commons.spring.web.config
    - swagger2 적용  

[2020/08/27]
- Add
  + open.commons.spring.web.rest
    - RestApiDecl.java
    - RestApiServer.java
- Update
  + open.commons.spring.web.rest
    - RestUtils.java
        
[2020/07/30]
- Tag: 0.3.0-SNAPSHOT
- Add
  + open.commons.spring.web.servlet.BadRequestException
  + open.commons.spring.web.servlet.InternalServerException
- Update
  + open.commons.spring.web.servlet.method.annotation.DefaultGlobalExceptionHandler
    + handle4xxException(Exception, WebRequest): 대상 추가
      + open.commons.spring.web.servlet.BadRequestException
    + handle5xxException(Exception, WebRequest): 대상 추가
      + open.commons.spring.web.servlet.InternalServerException


[2020/02/13]
- Tag: 0.2.4-SNAPSHOT

[2020/02/13]
- Release: 0.2.3
- Add
  + open.commons.spring.web.BasePackageMarker
  + open.commons.spring.web.handler.DefaultGlobalInterceptor
  + open.commons.spring.web.mvc.service.AbstractComponent
  + open.commons.spring.web.mvc.service.AbstractGenericService
  + open.commons.spring.web.servlet.method.annotation.DefaultGlobalExceptionHandler
- Update
  + open.commons.spring.web.OpenCommonsSpringWeb
  + open.commons.spring.web.annotation.CustomHttpMessageconverter
  + open.commons.spring.web.config.ResourceConfiguration
  + open.commons.spring.web.mvc.support.UrlInfo  
  + open.commons.spring.web.resources.ThreadPoolTaskExecutorConfig

[2019/10/23]
- Add
  + open.commons.spring.web.rest.RestUtils


[2019/10/15]
- Add
  + open.commons.spring.web.validation.ValidationTarget

[2019/10/10]
- Add
  + open.commons.spring.web.validation.Validational<C extends List\<E>, E extends Validational<List\<E>, E>>

[2019/10/08]
- Release: 0.2.2-RELEASE
- Tag: 0.2.3-SNAPSHOT
- Add
  + open.commons.spring.web.utils.ValidationUtils
 
[2019/9/20]
- Update
  + open.commons.spring.web.resources.ThreadPoolTaskExecutorConfig.maxPoolSize 기본값 변경
    - 30 -> Integer.MAX_VALUE

[2019/9/18]
- Release: 0.2.1
- Tag: 0.2.2-SNAPSHOT

[2019/9/9]
- Add
  + open.commons.spring.web.OpenCommonsSpringWeb 

[2019/9/8]
- Tag: 0.2.1-SNAPSHOT
- Add
  + open.commons.spring.web.config.CustomWebMvcConfigurer.extendMessageConverters(List<HttpMessageConverter<?>>)

[2019/8/7]
- Dependency
  + open.commons.core: 1.6.12

[2019/7/17]
- Release: 0.2.0
- Add
  + open.commons.spring.web.resources.ThreadPoolTaskExecutorConfig
- Dependency
  + open.commons.core: 1.6.11

[2019/7/4]
- Dependency
  + open.commons.core: 1.6.10


[2019/7/1]
- Bugfix
  + open.commons.spring.web.config.CustomWebMvcConfigurer.addFormatters(FormatterRegistry)

[2019/6/28]
- Release: 0.1.1
- Add
  + open.commons.spring.web.servlet.mvn.support
  + open.commons.spring.web.utils.WebUtils
  
- Dependency
  + javax.servlet.servlet-api
  + open.commons.core

[2019/6/27]
- Release: 0.1.0
- Add
  + open.commons.spring.web.config.ResourceConfiguration
  + open.commons.spring.web.resources.RestTempalteReqeust

[2019/06/11]
- Release: 0.0.3
  + 사용자 정의 HandlerInterceptor 자동 등록 추가

[2019/06/07]
- Release: 0.0.2
  + 다중 패키지 지원
  + application.yml (.properties or ...) 항목명 수정.
  + Bean 등록방법 추가

[2019/06/03]
- Release: 0.0.1
