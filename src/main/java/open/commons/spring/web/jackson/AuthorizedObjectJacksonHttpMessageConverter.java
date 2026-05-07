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
 * Date  : 2025. 5. 26. 오후 4:26:48
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.jackson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotNull;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.MethodParameter;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.AbstractJacksonHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonInputMessage;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StreamUtils;
import org.springframework.util.TypeUtils;
import org.springframework.web.bind.annotation.RestController;

import open.commons.core.utils.AssertUtils2;
import open.commons.spring.web.autoconfigure.AuthorizedResourcesAutoConfiguration;
import open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata;
import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;
import open.commons.spring.web.configure.AuthorizedResourcesMetadataConfiguration;
import open.commons.spring.web.thread.AuthorizedResourceContext;

import com.fasterxml.jackson.annotation.JsonView;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonEncoding;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.PrettyPrinter;
import tools.jackson.core.util.DefaultIndenter;
import tools.jackson.core.util.DefaultPrettyPrinter;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ObjectReader;
import tools.jackson.databind.ObjectWriter;
import tools.jackson.databind.SerializationConfig;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.exc.InvalidDefinitionException;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.ser.FilterProvider;

/**
 * 사용자 권한에 기반하여 {@link RestController} 또는 {@link Controller}에서 반환하는 데이터(VO)의 값을 제어하는 클래스.<br>
 * 이 클래스를 사용하기 위해서는 {@link IFieldAccessAuthorityProvider}와 {@link IUnauthorizedFieldHandler}를 구현한
 * {@link Bean}이 제공되어야 합니다.
 * 
 * <pre>
 * [개정이력]
 *      날짜       | 작성자                   |   내용
 * -----------------------------------------------------
 * 2025. 5. 26.     parkjunhong77@gmail.com     최초 작성
 * 2026. 4. 15.     parkjunhong77@gmail.com     Jackson 3.0 현행화 ( com.fasterxml.jackson.xxx => tools.jackson.databind.xxx )
 * </pre>
 * 
 * @since 2025. 5. 26.
 * @version 4.0.0
 * @author parkjunhong77@gmail.com
 * 
 * @see IFieldAccessAuthorityProvider
 * @see IUnauthorizedFieldHandler
 * @see AuthorizedResourcesMetadataConfiguration
 */
public class AuthorizedObjectJacksonHttpMessageConverter extends JacksonJsonHttpMessageConverter {

    public static final String BEAN_QUALIFIER = "open.commons.spring.web.jackson.AuthorizedObjectJacksonHttpMessageConverter";

    /**
     * {@link AbstractJacksonHttpMessageConverter}의 <b><i>{@code JSON_VIEW_HINT}</i></b>가
     * <font color="red"><b><i>{@code private}</i></b></font>이어서 자체 설정.
     */
    private static final String JSON_VIEW_HINT = JsonView.class.getName();
    /**
     * {@link AbstractJacksonHttpMessageConverter}의 <b><i>{@code FILTER_PROVIDER_HINT}</i></b>가
     * <font color="red"><b><i>{@code private}</i></b></font>이어서 자체 설정.
     */
    private static final String FILTER_PROVIDER_HINT = FilterProvider.class.getName();

    private static final Map<String, JsonEncoding> ENCODINGS = Stream.of(JsonEncoding.values())
            .collect(Collectors.toMap(enc -> enc.getJavaName(), enc -> enc));
    static {
        ENCODINGS.put("US-ASCII", JsonEncoding.UTF8);
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final @NonNull Map<String, JsonMapper> allJsonMappers;

    private final @NonNull IAuthorizedResourcesMetadata authorizedResourcesMetadata;

    /**
     * {@link AbstractJacksonHttpMessageConverter}의 <b><i>{@code mapperRegistrations}</i></b>가
     * <font color="red"><b><i>{@code private}</i></b></font>이어서 자체 설정.
     */
    private @Nullable Map<Class<?>, Map<MediaType, JsonMapper>> mapperRegistrations;

    private final @Nullable PrettyPrinter ssePrettyPrinter;

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 5. 26.     parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 15.     parkjunhong77@gmail.com     {@link JsonMapper} 추가
     * </pre>
     *
     * @param defaultJsonMapper
     *            SpringFramework에서 제공하는 기본 {@link JsonMapper}
     * @param allJsonMappers
     *            <b><i>{@code JSON}</i></b>을 지원하는 {@link JsonMapper}.
     *            (<b><i>{@code defaultJsonMapper}</i></b>은 제외됨)
     * @param authorizedResourcesMetadata
     *
     * @since 2025. 5. 26.
     * @version 4.0.0
     */
    public AuthorizedObjectJacksonHttpMessageConverter(@NotNull JsonMapper defaultJsonMapper //
            , @NotNull Map<String, JsonMapper> allJsonMappers,
            @NotNull IAuthorizedResourcesMetadata authorizedResourcesMetadata) {
        AssertUtils2.notNulls(defaultJsonMapper, allJsonMappers, authorizedResourcesMetadata);

        super(defaultJsonMapper);

        this.allJsonMappers = allJsonMappers;
        this.authorizedResourcesMetadata = authorizedResourcesMetadata;
        this.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));

        DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
        prettyPrinter.indentObjectsWith(new DefaultIndenter("  ", "\ndata:"));

        this.ssePrettyPrinter = prettyPrinter;
    }

    /**
     * 자신 및 상위 클래스의 정보가 외부설정(메타정보)에서 설정되었는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 6. 13.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param clazz
     * @return
     *
     * @since 2025. 6. 13.
     * @version 0.8.0
     */
    protected boolean annotatedOnMetadata(Class<?> clazz) {
        while (!Object.class.equals(clazz)) {
            if (this.authorizedResourcesMetadata.isAuthorizedObject(clazz)) {
                return true;
            }
            clazz = clazz.getSuperclass();
        }
        return false;
    }

    /**
     * <p>
     * {@link AbstractJacksonHttpMessageConverter} 클래스의 <b><i>{@code ENCODINGS}</i></b> 및
     * <b><i>{@code mapperRegistrations}</i></b>가
     * <font color="red"><b><i>{@code private}</i></b></font>이어서 overriding 함.
     * </p>
     * 
     * {@inheritDoc}
     *
     * @since 2026. 4. 15.
     * @version 4.0.0
     *
     * @see org.springframework.http.converter.AbstractJacksonHttpMessageConverter#canRead(org.springframework.core.ResolvableType,
     *      org.springframework.http.MediaType)
     */
    @Override
    public boolean canRead(ResolvableType type, @Nullable MediaType mediaType) {
        if (!canRead(mediaType)) {
            return false;
        }
        return this.mapperRegistrations == null || selectMapper(type.toClass(), mediaType) != null;
    }

    /**
     * <p>
     * {@link AbstractJacksonHttpMessageConverter} 클래스의 <b><i>{@code ENCODINGS}</i></b> 및
     * <b><i>{@code mapperRegistrations}</i></b>가
     * <font color="red"><b><i>{@code private}</i></b></font>이어서 overriding 함.
     * </p>
     * 
     * {@inheritDoc}
     *
     * @since 2026. 4. 15.
     * @version 4.0.0
     *
     * @see org.springframework.http.converter.AbstractJacksonHttpMessageConverter#canWrite(org.springframework.core.ResolvableType,
     *      java.lang.Class, org.springframework.http.MediaType)
     */
    @Override
    @SuppressWarnings("removal")
    public boolean canWrite(ResolvableType type, Class<?> valueClass, @Nullable MediaType mediaType) {

        // Jackson이 건드리면 안 되는 Spring 고유 타입 및 순수 데이터 타입은 무조건 우회(Bypass)시킵니다.
        if (
        // org.springframework.http.converter.ByteArrayHttpMessageConverter
        byte[].class.isAssignableFrom(valueClass)
                // org.springframework.http.converter.StringHttpMessageConverter
                || String.class.isAssignableFrom(valueClass)
                // org.springframework.http.converter.ResourceHttpMessageConverter
                || org.springframework.core.io.Resource.class.isAssignableFrom(valueClass)
                // org.springframework.http.converter.ResourceRegionHttpMessageConverter
                || org.springframework.core.io.support.ResourceRegion.class.isAssignableFrom(valueClass)
                // org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter
                // -> org.springframework.http.converter.FormHttpMessageConverter
                || org.springframework.util.MultiValueMap.class.isAssignableFrom(valueClass)
        //
        ) {
            return false;
        }

        if (!canWrite(mediaType)) {
            return false;
        }
        if (mediaType != null && mediaType.getCharset() != null) {
            Charset charset = mediaType.getCharset();
            if (!ENCODINGS.containsKey(charset.name())) {
                return false;
            }
        }
        if (MappingJacksonValue.class.isAssignableFrom(valueClass)) {
            throw new UnsupportedOperationException("MappingJacksonValue is not supported, use hints instead");
        }
        return this.mapperRegistrations == null || selectMapper(valueClass, mediaType) != null;
    }

    /**
     * <p>
     * {@link AbstractJacksonHttpMessageConverter} 클래스의 <b><i>{@code ENCODINGS}</i></b> 가
     * <font color="red"><b><i>{@code private}</i></b></font>이어서 overriding 함.
     * </p>
     * {@inheritDoc}
     *
     * @since 2026. 4. 15.
     * @version 4.0.0
     *
     * @see org.springframework.http.converter.AbstractJacksonHttpMessageConverter#getJsonEncoding(org.springframework.http.MediaType)
     */
    @Override
    protected JsonEncoding getJsonEncoding(@Nullable MediaType contentType) {
        if (contentType != null && contentType.getCharset() != null) {
            Charset charset = contentType.getCharset();
            JsonEncoding encoding = ENCODINGS.get(charset.name());
            if (encoding != null) {
                return encoding;
            }
        }
        return JsonEncoding.UTF8;
    }

    /**
     * {@link AbstractJacksonHttpMessageConverter} 클래스가 {@link OjectMapper}를 선택하는 메커니즘을 최대한 따르기 위해서
     * {@link AbstractJacksonHttpMessageConverter} 클래스의
     * <b><i>{@code org.springframework.http.converter.AbstractJacksonHttpMessageConverter.getMapperRegistrations()}</i></b>를
     * 이관한 메소드입니다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 5. 27.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 5. 27.
     * @version 0.8.0
     */
    private Map<Class<?>, Map<MediaType, JsonMapper>> getMapperRegistrations() {
        return (this.mapperRegistrations != null ? this.mapperRegistrations : Collections.emptyMap());
    }

    /**
     * <p>
     * {@link AbstractJacksonHttpMessageConverter} 클래스의 <b><i>{@code mapperRegistrations}</i></b>
     * 정보를 제공하는 <b><i>{@code getMapperRegistrations()}</i></b> 메소드가
     * <font color="red"><b><i>{@code private}</i></b></font>이어서 overriding 함.
     * </p>
     * 
     * {@inheritDoc}
     *
     * @since 2026. 4. 15.
     * @version 4.0.0
     *
     * @see org.springframework.http.converter.AbstractJacksonHttpMessageConverter#getMappersForType(java.lang.Class)
     */
    @Override
    public Map<MediaType, JsonMapper> getMappersForType(Class<?> clazz) {
        for (Map.Entry<Class<?>, Map<MediaType, JsonMapper>> entry : getMapperRegistrations().entrySet()) {
            if (entry.getKey().isAssignableFrom(clazz)) {
                return entry.getValue();
            }
        }
        return Collections.emptyMap();
    }

    /**
     * <p>
     * {@link AbstractJacksonHttpMessageConverter} 클래스의 <b><i>{@code mapperRegistrations}</i></b>
     * 정보를 제공하는 <b><i>{@code getMapperRegistrations()}</i></b> 메소드가
     * <font color="red"><b><i>{@code private}</i></b></font>이어서 overriding 함.
     * </p>
     * 
     * {@inheritDoc}
     *
     * @since 2026. 4. 15.
     * @version 4.0.0
     *
     * @see org.springframework.http.converter.AbstractJacksonHttpMessageConverter#getSupportedMediaTypes(java.lang.Class)
     */
    @Override
    public List<MediaType> getSupportedMediaTypes(Class<?> clazz) {
        List<MediaType> result = null;
        for (Map.Entry<Class<?>, Map<MediaType, JsonMapper>> entry : getMapperRegistrations().entrySet()) {
            if (entry.getKey().isAssignableFrom(clazz)) {
                result = (result != null ? result : new ArrayList<>(entry.getValue().size()));
                result.addAll(entry.getValue().keySet());
            }
        }
        if (!CollectionUtils.isEmpty(result)) {
            return result;
        }
        return (ProblemDetail.class.isAssignableFrom(clazz) ? getMediaTypesForProblemDetail()
                : getSupportedMediaTypes());
    }

    @PostConstruct
    public void logAllJsonMappers() {
        this.allJsonMappers.forEach((name, jm) -> {
            logger.trace("JsonMapper 등록됨: {} -> {}", name, jm.getClass().getName());
        });
    }

    /**
     * <p>
     * {@link AbstractJacksonHttpMessageConverter} 클래스의
     * <i>{@code readJavaType(JavaType, HttpInputMessage, Map<String, Object>)}</i> 메소드가
     * <font color="red"><b><i>{@code private}</i></b></font>이어서 overriding 함.
     * </p>
     * 
     * {@inheritDoc}
     *
     * @since 2026. 4. 15.
     * @version 4.0.0
     *
     * @see org.springframework.http.converter.AbstractJacksonHttpMessageConverter#read(org.springframework.core.ResolvableType,
     *      org.springframework.http.HttpInputMessage, java.util.Map)
     */
    public Object read(ResolvableType type, HttpInputMessage inputMessage, Map<String, Object> hints)
            throws IOException, HttpMessageNotReadableException {
        Class<?> contextClass = (type.getSource() instanceof MethodParameter parameter ? parameter.getContainingClass()
                : (hints != null ? (Class<?>) hints.get("contextClass") : null));
        JavaType javaType = getJavaType(type.getType(), contextClass);

        return readJavaType(javaType, inputMessage, hints);
    }

    /**
     * <p>
     * {@link AbstractJacksonHttpMessageConverter} 클래스의
     * <i>{@code readJavaType(JavaType, HttpInputMessage, Map<String, Object>)}</i> 메소드가
     * <font color="red"><b><i>{@code private}</i></b></font>이어서 overriding 함.
     * </p>
     * 
     * {@inheritDoc}
     *
     * @since 2026. 4. 15.
     * @version 4.0.0
     *
     * @see org.springframework.http.converter.AbstractJacksonHttpMessageConverter#readInternal(java.lang.Class,
     *      org.springframework.http.HttpInputMessage)
     */
    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        JavaType javaType = getJavaType(clazz, null);
        return readJavaType(javaType, inputMessage, null);
    }

    /**
     * 
     * {@link AbstractJacksonHttpMessageConverter} 클래스의
     * <b><i>{@code selectMapper(Class<?>, MediaType)}</i></b> 메소드가
     * <font color="red"><b><i>{@code private}</i></b></font>이어서
     * {@link AuthorizedResourcesAutoConfiguration#BEAN_QUALIFIER_AUTHORIZED_JSON_MAPPER}
     * <b><i>{@code Bean}</i></b>을 적용하기 위해서 코드를 그대로 인용하여 구현한 메소드
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 9. 23.     parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 15.     parkjunhong77@gmail.com     Jackson 3.0 현행화 ( com.fasterxml.jackson.xxx => tools.jackson.databind.xxx )
     * </pre>
     *
     * @param javaType
     * @param inputMessage
     * @param hints
     * @return
     * @throws IOException
     *
     * @since 2025. 9. 23.
     * @version 4.0.0
     */
    @SuppressWarnings("removal")
    private Object readJavaType(JavaType javaType, HttpInputMessage inputMessage, @Nullable Map<String, Object> hints)
            throws IOException {
        MediaType contentType = inputMessage.getHeaders().getContentType();
        Charset charset = getCharset(contentType);

        JsonMapper jsonMapper = allJsonMappers
                .get(AuthorizedResourcesAutoConfiguration.BEAN_QUALIFIER_AUTHORIZED_JSON_MAPPER);

        Objects.requireNonNull(jsonMapper, "No JsonMapper for " + javaType);

        boolean isUnicode = ENCODINGS.containsKey(charset.name()) //
                || "UTF-16".equals(charset.name()) //
                || "UTF-32".equals(charset.name());
        try {
            InputStream inputStream = StreamUtils.nonClosing(inputMessage.getBody());
            if (inputMessage instanceof MappingJacksonInputMessage) {
                throw new UnsupportedOperationException(
                        "MappingJacksonInputMessage is not supported, use hints instead");
            }
            ObjectReader objectReader = jsonMapper.readerFor(javaType);
            if (hints != null && hints.containsKey(JSON_VIEW_HINT)) {
                objectReader = objectReader.withView((Class<?>) hints.get(JSON_VIEW_HINT));
            }

            objectReader = customizeReader(objectReader, javaType);
            if (isUnicode) {
                return objectReader.readValue(inputStream);
            } else {
                Reader reader = new InputStreamReader(inputStream, charset);
                return objectReader.readValue(reader);
            }
        } catch (InvalidDefinitionException ex) {
            throw new HttpMessageConversionException("Type definition error: " + ex.getType(), ex);
        } catch (JacksonException ex) {
            throw new HttpMessageNotReadableException("JSON parse error: " + ex.getMessage(), ex, inputMessage);
        }
    }

    /**
     * <p>
     * {@link AbstractJacksonHttpMessageConverter} 클래스의 <b>{@code ENCODINGS}</b> 및
     * <b>{@code mapperRegistrations}</b>가 <font color="red"><b><i>{@code private}</i></b></font>이어서
     * overriding 함.
     * </p>
     * 
     * {@inheritDoc}
     *
     * @since 2026. 4. 15.
     * @version 4.0.0
     *
     * @see org.springframework.http.converter.AbstractJacksonHttpMessageConverter#registerMappersForType(java.lang.Class,
     *      java.util.function.Consumer)
     */
    @Override
    public void registerMappersForType(Class<?> clazz, Consumer<Map<MediaType, JsonMapper>> registrar) {
        if (this.mapperRegistrations == null) {
            this.mapperRegistrations = new LinkedHashMap<>();
        }
        Map<MediaType, JsonMapper> registrations = this.mapperRegistrations.computeIfAbsent(clazz,
                _ -> new LinkedHashMap<>());
        registrar.accept(registrations);
    }

    /**
     * {@link AbstractJacksonHttpMessageConverter#write(Object, ResolvableType, MediaType, HttpOutputMessage, Map)}
     * 를 로직을 그대로 차용하기 위해서 내부적으로 사용되는
     * <b><i>{@code private ObjectMapper selectMapper(Class<?>, MediaType))}</i></b> 메소드에 대한 Wrapper
     * 메소드입니다.<br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 5. 27.    parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 15.     parkjunhong77@gmail.com     Jackson 3.0 현행화 ( com.fasterxml.jackson.xxx => tools.jackson.databind.xxx )
     * </pre>
     *
     * @param object
     * @param targetType
     * @param targetMediaType
     * @return
     *
     * @since 2025. 5. 27.
     * @version 0.8.0
     */
    private JsonMapper resolveMapper(Object object, Class<?> targetType, @Nullable MediaType targetMediaType) {
        JsonMapper jm = null;
        if (object == null
                // "민감 데이터 해제요청"이 허용된 경우 (권한기반 데이터 제어(Authorized-Resources)를
                // 비활성화), 2025. 6. 24.
                || AuthorizedResourceContext.isDisableAuthentication()) {
            jm = selectMapper(targetType, targetMediaType);
            return jm != null ? jm : getMapper();
        } else {
            return allJsonMappers.get(AuthorizedResourcesAutoConfiguration.BEAN_QUALIFIER_AUTHORIZED_JSON_MAPPER);
        }
    }

    /**
     * {@link AbstractJacksonHttpMessageConverter} 클래스의
     * <b>{@code selectMapper(Class<?>, MediaType)}</b>가 <font color="red">
     * <b><i>{@code private}</i></b></font>이어서 overriding 함. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 5. 27.     parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 15.     parkjunhong77@gmail.com     Jackson 3.0 현행화 ( com.fasterxml.jackson.xxx => tools.jackson.databind.xxx )
     * </pre>
     *
     * @param targetType
     * @param targetMediaType
     * @return
     *
     * @since 2025. 5. 27.
     * @version 0.8.0
     */
    private @Nullable JsonMapper selectMapper(Class<?> targetType, @Nullable MediaType targetMediaType) {
        if (targetMediaType == null || CollectionUtils.isEmpty(this.mapperRegistrations)) {
            return getMapper();
        }
        for (Map.Entry<Class<?>, Map<MediaType, JsonMapper>> typeEntry : getMapperRegistrations().entrySet()) {
            if (typeEntry.getKey().isAssignableFrom(targetType)) {
                for (Map.Entry<MediaType, JsonMapper> mapperEntry : typeEntry.getValue().entrySet()) {
                    if (mapperEntry.getKey().includes(targetMediaType)) {
                        return mapperEntry.getValue();
                    }
                }
                // No matching registrations
                return null;
            }
        }
        // No registrations
        return getMapper();
    }

    /**
     * <p>
     * {@link AbstractJacksonHttpMessageConverter}가 {@link ObjectMapper}를 선택하는 메커니즘을 최대한 따르기 위해서
     * {@link AbstractJacksonHttpMessageConverter#canRead(ResolvableType, MediaType)} 코드를 거의 그대로 차용한
     * 메소드입니다.<br>
     * 다른 부분은 {@link JsonMapper}( {@link AbstractJacksonHttpMessageConverter}에서는
     * <b><i>{@code T}</i></b>)를 선택하는 부분입니다.<br>
     * <ul>
     * <li>{@link AbstractJacksonHttpMessageConverter#write(Object, ResolvableType, MediaType, HttpOutputMessage, Map)}:
     * selectMapper(Class<?>, MediaType)
     * <li>{@link AuthorizedObjectJacksonHttpMessageConverter#writeInternal(Object, Type, HttpOutputMessage)}:
     * {@link #resolveMapper(Object, Class, MediaType)}
     * </ul>
     * </p>
     * 
     * {@inheritDoc}
     *
     * @since 2025. 5. 26.
     * @version 4.0.0
     *
     * @see org.springframework.http.converter.AbstractJacksonHttpMessageConverter#writeInternal(java.lang.Object,
     *      org.springframework.core.ResolvableType, org.springframework.http.HttpOutputMessage,
     *      java.util.Map)
     */
    @Override
    protected void writeInternal(Object object, ResolvableType resolvableType, HttpOutputMessage outputMessage,
            @Nullable Map<String, Object> hints) throws IOException, HttpMessageNotWritableException {

        MediaType contentType = outputMessage.getHeaders().getContentType();
        JsonEncoding encoding = getJsonEncoding(contentType);

        Class<?> clazz = object.getClass();

        JsonMapper jsonMapper = resolveMapper(object, clazz, contentType);
        Objects.requireNonNull(jsonMapper, () -> "No JsonMapper for " + clazz.getName());

        OutputStream outputStream = StreamUtils.nonClosing(outputMessage.getBody());
        Class<?> jsonView = null;
        FilterProvider filters = null;
        JavaType javaType = null;

        Type type = resolvableType.getType();
        if (TypeUtils.isAssignable(type, object.getClass())) {
            javaType = getJavaType(type, null);
        }

        if (hints != null) {
            jsonView = (Class<?>) hints.get(JSON_VIEW_HINT);
            filters = (FilterProvider) hints.get(FILTER_PROVIDER_HINT);
        }

        ObjectWriter objectWriter = jsonView != null ? jsonMapper.writerWithView(jsonView) : jsonMapper.writer();
        if (filters != null) {
            objectWriter = objectWriter.with(filters);
        }

        if (javaType != null && (javaType.isContainerType() || javaType.isTypeOrSubTypeOf(Optional.class))) {
            objectWriter = objectWriter.forType(javaType);
        }

        SerializationConfig config = objectWriter.getConfig();
        if (contentType != null //
                && contentType.isCompatibleWith(MediaType.TEXT_EVENT_STREAM) //
                && config.isEnabled(SerializationFeature.INDENT_OUTPUT) //
        ) {
            objectWriter = objectWriter.with(this.ssePrettyPrinter);
        }

        objectWriter = customizeWriter(objectWriter, javaType, contentType);

        try ( //
                ByteArrayOutputStream serializeBuffer = new ByteArrayOutputStream(); //
                JsonGenerator generator = objectWriter.createGenerator(serializeBuffer, encoding); //
        ) {
            writePrefix(generator, object);
            objectWriter.writeValue(generator, object);
            writeSuffix(generator, object);
            generator.flush();

            outputStream.write(serializeBuffer.toByteArray());

        } catch (InvalidDefinitionException ex) {
            throw new HttpMessageConversionException("Type definition error: " + ex.getType(), ex);
        } catch (JacksonException ex) {
            throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
        } finally {
            // 데이터 요청에 "민감데이터 해제요청"이 있는 경우 사용자 권한에 따라서 허용 여부를 전달하는 데이터 초기화
            // AuthorizedResourceContext.clear();
        }
    }
}