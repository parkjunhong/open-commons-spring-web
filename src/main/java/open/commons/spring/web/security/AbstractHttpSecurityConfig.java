/*
 * Copyright 2025 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2025. 10. 23. 오후 12:59:04
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.security;

import java.lang.reflect.Method;

import jakarta.servlet.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity.RequestMatcherConfigurer;
import org.springframework.security.config.annotation.web.configurers.AnonymousConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpsRedirectConfigurer;
import org.springframework.security.config.annotation.web.configurers.JeeConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.annotation.web.configurers.PasswordManagementConfigurer;
import org.springframework.security.config.annotation.web.configurers.PortMapperConfigurer;
import org.springframework.security.config.annotation.web.configurers.RememberMeConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer;
import org.springframework.security.config.annotation.web.configurers.ServletApiConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.annotation.web.configurers.X509Configurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2ClientConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.annotation.web.configurers.saml2.Saml2LoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.saml2.Saml2LogoutConfigurer;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import open.commons.core.function.ThrowableFunction;
import open.commons.core.utils.AssertUtils2;
import open.commons.core.utils.ExceptionUtils;

/**
 * 
 * @since 2025. 10. 23.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public abstract class AbstractHttpSecurityConfig {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @see #httpBasic(HttpBasicConfigurer)
     * @deprecated {@link #httpBasic(HttpBasicConfigurer)} 메소드 <b><i>{@code override}</i></b> 여부로
     *             판단함.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    protected boolean enableHttpBasic;
    /**
     * @see #formLogin(FormLoginConfigurer)
     * @deprecated {@link #formLogin(FormLoginConfigurer)} 메소드 <b><i>{@code override}</i></b> 여부로
     *             판단함.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    protected boolean enableFormLogin;
    /**
     * 이 옵션을 <code>true</code>로 설정하는 경우
     * "org.springframework.security.oauth2.client.registration.ClientRegistrationRepository" 등을 구현한
     * {@link Bean}이 필요할 수 있습니다.
     * 
     * @see #oauth2Login(OAuth2LoginConfigurer)
     * 
     * @deprecated {@link #oauth2Login(OAuth2LoginConfigurer)} 메소드 <b><i>{@code override}</i></b>
     *             여부로 판단함.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    protected boolean enableOauth2Login;
    /**
     * 이 옵션을 <code>true</code>로 설정하는 경우
     * "org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager" 등을 구현한
     * {@link Bean}이 필요할 수 있습니다.
     * 
     * @see #oauth2Client(OAuth2ClientConfigurer)
     * 
     * @deprecated {@link #oauth2Client(OAuth2ClientConfigurer)} 메소드 <b><i>{@code override}</i></b>
     *             여부로 판단함.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    protected boolean enableOauth2Client;
    /**
     * 이 옵션을 <code>true</code>로 설정하는 경우 기능에 필요한 {@link Bean}을 확인하기 바랍니다.
     * 
     * @see #oauth2ResourceServer(OAuth2ResourceServerConfigurer)
     * 
     * @deprecated {@link #oauth2ResourceServer(OAuth2ResourceServerConfigurer)} 메소드
     *             <b><i>{@code override}</i></b> 여부로 판단함.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    protected boolean enableOauth2ResourceServer;
    /**
     * @see #x509(X509Configurer)
     * 
     * @deprecated {@link #x509(X509Configurer)} 메소드 <b><i>{@code override}</i></b> 여부로 판단함.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    protected boolean enableX509;
    /**
     * @see #jee(JeeConfigurer)
     * 
     * @deprecated {@link #jee(JeeConfigurer)} 메소드 <b><i>{@code override}</i></b> 여부로 판단함.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    protected boolean enableJee;

    // [PATCH] enableAuthorizeHttpRequests 플래그 제거 (SS 7.0에서는 항상
    // authorizeHttpRequests만 사용)

    /**
     * 이 옵션을 <code>true</code>로 설정하는 경우 기능에 필요한 {@link Bean}을 확인하기 바랍니다.
     * 
     * @see #passwordManagement(PasswordManagementConfigurer)
     * 
     * @deprecated {@link #passwordManagement(PasswordManagementConfigurer)} 메소드
     *             <b><i>{@code override}</i></b> 여부로 판단함.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    protected boolean enablePasswordManagement;

    /**
     * 이 옵션을 <code>true</code>로 설정하는 경우 기능에 필요한 {@link Bean}을 확인하기 바랍니다.
     * 
     * @see #portMapper(PortMapperConfigurer)
     * 
     * @deprecated {@link #portMapper(PortMapperConfigurer)} 메소드 <b><i>{@code override}</i></b> 여부로
     *             판단함.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    protected boolean enablePortMapper;

    /**
     * 이 옵션을 <code>true</code>로 설정하는 경우 기능에 필요한 {@link Bean}을 확인하기 바랍니다.
     * 
     * @see #rememberMe(RememberMeConfigurer)
     * 
     * @deprecated {@link #rememberMe(RememberMeConfigurer)} 메소드 <b><i>{@code override}</i></b> 여부로
     *             판단함.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    protected boolean enableRememberMe;
    /**
     * 이 옵션을 <code>true</code>로 설정하는 경우 기능에 필요한 {@link Bean}을 확인하기 바랍니다.
     * 
     * @see #saml2Login(Saml2LoginConfigurer)
     * @see #saml2Logout(Saml2LogoutConfigurer)
     * 
     * @deprecated {@link #saml2Login(Saml2LoginConfigurer)},
     *             {@link #saml2Logout(Saml2LogoutConfigurer)} 메소드 <b><i>{@code override}</i></b>
     *             여부로 판단함.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    protected boolean enableSaml2;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     */
    public AbstractHttpSecurityConfig() {
    }

    /**
     * {@link HttpSecurity#anonymous(org.springframework.security.config.Customizer)}에 전달되는 정보를
     * 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#anonymous(org.springframework.security.config.Customizer)
     */
    protected void anonymous(AnonymousConfigurer<HttpSecurity> configurer) {
    }

    private final <T> void applyIfOverridden(Customizer<T> configurer,
            ThrowableFunction<Customizer<T>, HttpSecurity> applier, String methodName, Class<?>... argTypes)
            throws Exception {
        if (isOverrided(methodName, argTypes)) {
            try {
                applier.apply(configurer);
            } catch (Throwable e) {
                throw new Exception("", e);
            }
        }
    }

    /**
     * {@link HttpSecurity}에서 {@link AuthenticationManager}, {@link AuthenticationProvider}를
     * 설정합니다.<br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param http
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#authenticationManager(org.springframework.security.authentication.AuthenticationManager)
     * @see HttpSecurity#authenticationProvider(org.springframework.security.authentication.AuthenticationProvider)
     */
    protected void authenticationProviders(HttpSecurity http) {
    }

    /**
     * {@link HttpSecurity#authorizeHttpRequests(org.springframework.security.config.Customizer)}에
     * 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#authorizeHttpRequests(org.springframework.security.config.Customizer)
     */
    protected void authorizeHttpRequests(
            AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry configurer) {
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 14.     parkjunhong77@gmail.com     {@link HttpSecurity}::7.0.3의 내부 구현 변경 및 {@link AntPathMatcher} 미지원에 따른 수정
     * 2026. 5. 27.     parkjunohng77@gmail.com     <code>override</code> 기반 적용과 자동 적용 분류 개선.
     * </pre>
     *
     * @param http
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     */
    protected final void configure(HttpSecurity http) throws Exception {
        AssertUtils2.notNull(http);

        // #1. (선택) 체인 범위/요청 매처 — 필요 시 사용
        applyIfOverridden(this::securityMatcher, http::securityMatchers, "securityMatcher",
                RequestMatcherConfigurer.class);
        // #1-1. 추후 제거될 예정
        applyIfOverridden(this::requestMatchers, http::securityMatchers, "requestMatchers",
                RequestMatcherConfigurer.class);

        // #2. 인증 메커니즘
        applyIfOverridden(this::httpBasic, http::httpBasic, "httpBasic", HttpBasicConfigurer.class);
        applyIfOverridden(this::formLogin, http::formLogin, "formLogin", FormLoginConfigurer.class);
        applyIfOverridden(this::oauth2Login, http::oauth2Login, "oauth2Login", OAuth2LoginConfigurer.class);
        applyIfOverridden(this::oauth2Client, http::oauth2Client, "oauth2Client", OAuth2ClientConfigurer.class);
        applyIfOverridden(this::oauth2ResourceServer, http::oauth2ResourceServer, "oauth2ResourceServer",
                OAuth2ResourceServerConfigurer.class);
        applyIfOverridden(this::x509, http::x509, "x509", X509Configurer.class);
        applyIfOverridden(this::jee, http::jee, "jee", JeeConfigurer.class);
        applyIfOverridden(this::saml2Login, http::saml2Login, "saml2Login", Saml2LoginConfigurer.class);
        applyIfOverridden(this::saml2Logout, http::saml2Logout, "saml2Logout", Saml2LogoutConfigurer.class);

        http.logout(this::logout);

        // #3. 예외처리 (전역 EntryPoint/DeniedHandler 통일)
        http.exceptionHandling(this::exceptionHandling);

        // #4. 권한 규칙 (좁은 규칙 -> 넓은 규칙)
        applyIfOverridden(this::authorizeHttpRequests, http::authorizeHttpRequests, "authorizeHttpRequests",
                AuthorizationManagerRequestMatcherRegistry.class);

        // #5. AuthentationProvider, AuthenticationManager 'Hook'
        this.authenticationProviders(http);

        // #6. 필터 'Hook'
        this.filters(http);

        // #7. 익명 사용자 처리
        applyIfOverridden(this::anonymous, http::anonymous, "anonymous", AnonymousConfigurer.class);

        // #7. 이하
        applyIfOverridden(this::sessionManagement, http::sessionManagement, "sessionManagement",
                SessionManagementConfigurer.class);
        applyIfOverridden(this::headers, http::headers, "headers", HeadersConfigurer.class);
        applyIfOverridden(this::csrf, http::csrf, "csrf", CsrfConfigurer.class);
        applyIfOverridden(this::cors, http::cors, "cors", CorsConfigurer.class);
        applyIfOverridden(this::servletApi, http::servletApi, "servletApi", ServletApiConfigurer.class);
        applyIfOverridden(this::passwordManagement, http::passwordManagement, "passwordManagement",
                PasswordManagementConfigurer.class);
        applyIfOverridden(this::requestCache, http::requestCache, "requestCache", RequestCacheConfigurer.class);
        applyIfOverridden(this::portMapper, http::portMapper, "portMapper", PortMapperConfigurer.class);
        applyIfOverridden(this::rememberMe, http::rememberMe, "rememberMe", RememberMeConfigurer.class);
        applyIfOverridden(this::securityContext, http::securityContext, "securityContext",
                SecurityContextConfigurer.class);
        applyIfOverridden(this::redirectToHttps, http::redirectToHttps, "redirectToHttps",
                HttpsRedirectConfigurer.class);
    }

    /**
     * {@link HttpSecurity#cors(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다.
     * <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#cors(org.springframework.security.config.Customizer)
     */
    protected void cors(CorsConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#csrf(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다.
     * <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#csrf(org.springframework.security.config.Customizer)
     */
    protected void csrf(CsrfConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#exceptionHandling(org.springframework.security.config.Customizer)}에 전달되는
     * 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#exceptionHandling(org.springframework.security.config.Customizer)
     */
    protected void exceptionHandling(ExceptionHandlingConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity}에서 {@link Filter}를 설정합니다.<br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#addFilter(javax.servlet.Filter)
     * @see HttpSecurity#addFilterAfter(javax.servlet.Filter, Class)
     * @see HttpSecurity#addFilterAt(javax.servlet.Filter, Class)
     * @see HttpSecurity#addFilterBefore(javax.servlet.Filter, Class)
     */
    protected void filters(HttpSecurity http) {
    }

    /**
     * {@link HttpSecurity#formLogin(org.springframework.security.config.Customizer)}에 전달되는 정보를
     * 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#formLogin(org.springframework.security.config.Customizer)
     */
    protected void formLogin(FormLoginConfigurer<HttpSecurity> configurer) {
    }

    /**
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param methodName
     *            메소드 이름
     * @param argTypes
     *            메소드 파라미터 유형
     * @return
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     */
    private final Method getMethod(String methodName, Class<?>... argTypes) {

        Class<?> userClass = ClassUtils.getUserClass(this);

        Method method = ReflectionUtils.findMethod(userClass, methodName, argTypes);
        if (method == null) {
            throw ExceptionUtils.newException(RuntimeException.class, "'%s'클래스 계층 구조에 '%s' 메소드가 존재하지 않습니다.",
                    userClass.getName(), methodName);
        }

        return method;
    }

    /**
     * {@link HttpSecurity#headers(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다.
     * <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#headers(org.springframework.security.config.Customizer)
     */
    protected void headers(HeadersConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#httpBasic(org.springframework.security.config.Customizer)}에 전달되는 정보를
     * 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#httpBasic(org.springframework.security.config.Customizer)
     */
    protected void httpBasic(HttpBasicConfigurer<HttpSecurity> configurer) {
    }

    /**
     * 메소드가 'overriding' 되었는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param methodName
     *            {@link SecurityConfigurer}를 설정하는 메소드 이름
     * @param argTypes
     *            메소드 파라미터 유형
     * @return
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     */
    private final boolean isOverrided(String methodName, Class<?>... argTypes) {
        return getMethod(methodName, argTypes).getDeclaringClass() != AbstractHttpSecurityConfig.class;
    }

    /**
     * {@link HttpSecurity#jee(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다.
     * <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#jee(org.springframework.security.config.Customizer)
     */
    protected void jee(JeeConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#logout(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다.
     * <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#logout(org.springframework.security.config.Customizer)
     */
    protected void logout(LogoutConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#oauth2Client(org.springframework.security.config.Customizer)}에 전달되는 정보를
     * 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.<br>
     * "org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager" 등을 구현한
     * {@link Bean}이 필요할 수 있습니다.
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#oauth2Client(org.springframework.security.config.Customizer)
     */
    protected void oauth2Client(OAuth2ClientConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#oauth2Login(org.springframework.security.config.Customizer)}에 전달되는 정보를
     * 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.<br>
     * "org.springframework.security.oauth2.client.registration.ClientRegistrationRepository" 등을 구현한
     * {@link Bean}이 필요할 수 있습니다.
     * 
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#oauth2Login(org.springframework.security.config.Customizer)
     */
    protected void oauth2Login(OAuth2LoginConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#oauth2ResourceServer(org.springframework.security.config.Customizer)}에
     * 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#oauth2ResourceServer(org.springframework.security.config.Customizer)
     */
    protected void oauth2ResourceServer(OAuth2ResourceServerConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#passwordManagement(org.springframework.security.config.Customizer)}에 전달되는
     * 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#passwordManagement(org.springframework.security.config.Customizer)
     */
    protected void passwordManagement(PasswordManagementConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#portMapper(org.springframework.security.config.Customizer)}에 전달되는 정보를
     * 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#portMapper(org.springframework.security.config.Customizer)
     */
    protected void portMapper(PortMapperConfigurer<HttpSecurity> configurer) {
    }

    /**
     * 
     * {@link HttpSecurity#redirectToHttps(Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2026. 4. 14.     parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     *
     * @since 2026. 4. 14.
     * @version 4.0.0
     */
    protected void redirectToHttps(HttpsRedirectConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#rememberMe(org.springframework.security.config.Customizer)}에 전달되는 정보를
     * 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#rememberMe(org.springframework.security.config.Customizer)
     */
    protected void rememberMe(RememberMeConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#requestCache(org.springframework.security.config.Customizer)}에 전달되는 정보를
     * 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#requestCache(org.springframework.security.config.Customizer)
     */
    protected void requestCache(RequestCacheConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#requestMatchers(org.springframework.security.config.Customizer)}에 전달되는
     * 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * 2026. 5. 27.     parkjunohng77@gmail.com     Spring Boot 4.0.3 로 현행화하면서 메소드 변경에 따른 <code>deprecated</code>
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#requestMatchers(org.springframework.security.config.Customizer)
     * 
     * @deprecated {@link #securityMatcher(RequestMatcherConfigurer)} 를 사용하기 바랍니다. 더 이상 작용 적용되지 않음.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    protected void requestMatchers(RequestMatcherConfigurer configurer) {
    }

    /**
     * {@link HttpSecurity#saml2Login(org.springframework.security.config.Customizer)}에 전달되는 정보를
     * 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     *
     * @return
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#saml2Login(org.springframework.security.config.Customizer)
     */
    protected void saml2Login(Saml2LoginConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#saml2Logout(org.springframework.security.config.Customizer)}에 전달되는 정보를
     * 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#saml2Logout(org.springframework.security.config.Customizer)
     */
    protected void saml2Logout(Saml2LogoutConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#securityContext(org.springframework.security.config.Customizer)}에 전달되는
     * 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#securityContext(org.springframework.security.config.Customizer)
     */
    protected void securityContext(SecurityContextConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#securityContext(org.springframework.security.config.Customizer)}에 전달되는
     * 정보를 제공합니다.<br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2026. 5. 27.     parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     *
     * @since 2026. 5. 27.
     * @version 4.0.0
     */
    protected void securityMatcher(RequestMatcherConfigurer configurer) {
    }

    /**
     * {@link HttpSecurity#servletApi(org.springframework.security.config.Customizer)}에 전달되는 정보를
     * 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#servletApi(org.springframework.security.config.Customizer)
     */
    protected void servletApi(ServletApiConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#sessionManagement(org.springframework.security.config.Customizer)}에 전달되는
     * 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#sessionManagement(org.springframework.security.config.Customizer)
     */
    protected void sessionManagement(SessionManagementConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#x509(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다.
     * <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 10. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * 
     * @see HttpSecurity#x509(org.springframework.security.config.Customizer)
     */
    protected void x509(X509Configurer<HttpSecurity> configurer) {
    }
}
