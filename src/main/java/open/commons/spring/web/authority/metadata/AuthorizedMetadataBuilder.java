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
 * Date  : 2025. 6. 18. 오후 2:30:05
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.authority.metadata;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import jakarta.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import open.commons.core.utils.ExceptionUtils;
import open.commons.core.utils.StringUtils;
import open.commons.spring.web.authority.AuthorizedField;
import open.commons.spring.web.authority.AuthorizedObject;
import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;
import open.commons.spring.web.beans.authority.internal.ForcedUnintelligibleJudge;
import open.commons.spring.web.servlet.InternalServerException;
import open.commons.spring.web.utils.ClassInspector;

/**
 * {@link AuthorizedObjectMetadata} 객체를 생성하는 "계층형 Builder".
 * 
 * @since 2025. 6. 18.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedMetadataBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizedMetadataBuilder.class);

    private AuthorizedMetadataBuilder() {
    }

    /**
     * 진입점
     */
    public static Builder builder() {
        return new BuilderImpl();
    }

    /**
     * Stage 1: 시작점
     */
    public interface Builder {

        /**
         * 1개의 {@link AuthorizedObjectMetadata}를 생성하는 빌더를 제공합니다. <br>
         * 
         * <pre>
        * [개정이력]
        *     날짜        | 작성자                   |   내용
        * -----------------------------------------------------
         * 2025. 6. 20.    parkjunhong77@gmail.com     최초 작성
         * </pre>
         *
         * @return
         *
         * @since 2025. 6. 18.
         * @version 0.8.0
         */
        ObjectBuilder object();

        /**
         * 여러 개의 {@link AuthorizedObjectMetadata}를 생성하는 빌더를 제공합니다. <br>
         * <br>
         * 
         * <pre>
        * [개정이력]
        *     날짜        | 작성자                   |   내용
        * -----------------------------------------------------
         * 2025. 6. 20.    parkjunhong77@gmail.com     최초 작성
         * </pre>
         *
         * @return
         *
         * @since 2025. 6. 20.
         * @version 0.8.0
         */
        ObjectsBuilder objects();
    }

    /**
     * 단계별 내부 클래스 구조: Stage 인터페이스 별로 개별 클래스
     */
    private static class BuilderImpl implements Builder {

        @Override
        public ObjectBuilder object() {
            return new ObjectBuilderImpl();
        }

        @Override
        public ObjectsBuilder objects() {
            return new ObjectsBuilderImpl();
        }

        /**
         * 
         * <br>
         * 
         * <pre>
        * [개정이력]
        *     날짜        | 작성자                   |   내용
        * -----------------------------------------------------
         * 2025. 6. 20.    parkjunhong77@gmail.com     최초 작성
         * </pre>
         *
         * @param targetClass
         * @param builder
         *
         * @since 2025. 6. 20.
         * @version 0.8.0
         */
        private static <T> T newObject(Class<T> targetClass, Object builder) {
            return newObject(targetClass, builder, null);
        }

        /**
         * 
         * <br>
         * 
         * <pre>
        * [개정이력]
        *     날짜        | 작성자                   |   내용
        * -----------------------------------------------------
         * 2025. 6. 19.    parkjunhong77@gmail.com     최초 작성
         * 2026. 4. 14.    parkjunhong77@gmail.com     JDK 25 현행화 (canAccess 및 trySetAccessible 적용, 무의미한 targetField 접근제어 로직 제거)
         * </pre>
         * 
         * @param targetClass
         *            생성할 객체
         * @param builder
         *            빌더 객체
         * @param postprocessors
         *            <li>key: 필드 이름
         *            <li>value: 후처리 모듈
         *
         * @since 2025. 6. 19.
         * @version 0.8.0
         */
        private static <T> T newObject(Class<T> targetClass, Object builder, Map<String, Function<Object, Object>> postprocessors) {
            T newObject = null;
            Method targetMethod = null;

            Class<?> builderClass = builder.getClass();
            Field builderField = null;
            Object value = null;

            try {
                newObject = targetClass.getDeclaredConstructor().newInstance();

                for (Field targetField : targetClass.getDeclaredFields()) {
                    String targetFieldName = targetField.getName();

                    // #1. builder 객체에서 동일한 이름의 필드 정보 조회
                    builderField = ClassInspector.getDeclaredFieldIfExist(builderClass, targetFieldName);
                    if (builderField == null) {
                        continue;
                    }

                    // #2. [JDK 25 표준] builderField 접근 가능 여부 확인 및 안전한 해제
                    if (!builderField.canAccess(builder)) {
                        // setAccessible(true) 대신 모듈 시스템에서 안전한 trySetAccessible() 사용
                        builderField.trySetAccessible();
                    }

                    value = builderField.get(builder);

                    // #3. builderField.get(builder) 후처리
                    if (postprocessors != null && postprocessors.containsKey(targetFieldName)) {
                        value = postprocessors.get(targetFieldName).apply(value);
                    }

                    if (value == null) {
                        continue;
                    }

                    // #4. 대상 객체의 Setter 메소드 호출
                    targetMethod = targetClass.getMethod(String.join("", "set", StringUtils.toUpperCase(targetFieldName, 0)), targetField.getType());
                    targetMethod.invoke(newObject, value);
                }
            } catch (Exception e) {
                String errMsg = String.format("데이터 처리 도중 오류가 발생하였습니다. target.class=%s, target.method=%s, target.object=%s / builder.class=%s, builder.field=%s, builder.object=%s" //
                        , targetClass, targetMethod, newObject, builderClass, builderField, builder);
                LOGGER.error(errMsg, e);
                throw ExceptionUtils.newException(InternalServerException.class, e, errMsg);
            }

            return newObject;
        }

        /**
         * 내부 FieldMetadata Builder
         */
        @SuppressWarnings("unused")
        private static class AuthorizedFieldMetadataBuilder {
            private String authorityBean = ForcedUnintelligibleJudge.BEAN_QUALIFIER;
            private String fieldHandleBean;
            private String handleType = AuthorizedField.NO_ASSINGED_HANDLE_TYPE;
            private String name;

            public void authorityBean(String authorityBean) {
                this.authorityBean = authorityBean;
            }

            public AuthorizedFieldMetadata build() {
                return newObject(AuthorizedFieldMetadata.class, this);
            }

            public void fieldHandleBean(String fieldHandleBean) {
                Objects.requireNonNull(fieldHandleBean);

                this.fieldHandleBean = fieldHandleBean;
            }

            public void handleType(String handleType) {
                Objects.requireNonNull(handleType);

                this.handleType = handleType;
            }

            public void name(String name) {
                Objects.requireNonNull(name);

                this.name = name;
            }
        }

        /**
         * 내부 ObjectMetadata Builder
         */
        @SuppressWarnings({ "unused", "unchecked" })
        private static class AuthorizedObjectMetadataBuilder {
            static Map<String, Function<Object, Object>> pp = new HashMap<>();
            static {
                pp.put("fields", o -> ((List<FieldBuilder>) o).stream().map(b -> b.build()).collect(Collectors.toList()));
            }
            private String authorityBean = ForcedUnintelligibleJudge.BEAN_QUALIFIER;
            private String fieldHandleBean;
            private Class<?> type;
            private List<FieldBuilder> fields;

            public void authorityBean(String authorityBean) {
                Objects.requireNonNull(authorityBean);

                this.authorityBean = authorityBean;
            }

            public AuthorizedObjectMetadata build() {
                return newObject(AuthorizedObjectMetadata.class, this, pp);
            }

            public void fieldHandleBean(String fieldHandleBean) {
                Objects.requireNonNull(fieldHandleBean);

                this.fieldHandleBean = fieldHandleBean;
            }

            public void fields(List<FieldBuilder> fields) {
                Objects.requireNonNull(fields);

                this.fields = fields;
            }

            public void type(Class<?> type) {
                Objects.requireNonNull(type);

                this.type = type;
            }
        }

        /**
         * FieldBuilder 단계 전용 클래스
         */
        private static class FieldBuilderImpl implements FieldBuilder {

            private final AuthorizedFieldMetadataBuilder afmBuilder = new AuthorizedFieldMetadataBuilder();

            public FieldBuilderImpl() {
            }

            @Override
            public FieldBuilder authorityBean(String authorityBean) {
                afmBuilder.authorityBean(authorityBean);
                return this;
            }

            @Override
            public AuthorizedFieldMetadata build() {
                return afmBuilder.build();
            }

            @Override
            public FieldBuilder fieldHandleBean(String fieldHandleBean) {
                afmBuilder.fieldHandleBean(fieldHandleBean);
                return this;
            }

            @Override
            public FieldBuilder handleType(String handleType) {
                afmBuilder.handleType(handleType);
                return this;
            }

            @Override
            public FieldBuilder name(String name) {
                afmBuilder.name(name);
                return this;
            }
        }

        /**
         * ObjectBuilder 단계 전용 클래스
         */
        private static class ObjectBuilderImpl implements ObjectBuilder {

            private final AuthorizedObjectMetadataBuilder aomBuilder = new AuthorizedObjectMetadataBuilder();
            private final List<FieldBuilder> fields = new ArrayList<>();

            @Override
            public ObjectBuilder authorityBean(String authorityBean) {
                aomBuilder.authorityBean(authorityBean);
                return this;
            }

            @Override
            public AuthorizedObjectMetadata build() {
                aomBuilder.fields(fields);
                return aomBuilder.build();
            }

            @Override
            public ObjectBuilder field(Function<FieldBuilder, FieldBuilder> consumer) {
                Objects.requireNonNull(consumer);

                FieldBuilderImpl builder = new FieldBuilderImpl();
                fields.add(consumer.apply(builder));
                return this;
            }

            @Override
            public ObjectBuilder fieldHandleBean(String fieldHandleBean) {
                aomBuilder.fieldHandleBean(fieldHandleBean);
                return this;
            }

            @Override
            public ObjectBuilder type(@NotNull Class<?> type) {
                aomBuilder.type(type);
                return this;
            }
        }

        private static class ObjectsBuilderImpl implements ObjectsBuilder {

            private final List<ObjectBuilder> objects = new ArrayList<>();

            /**
             *
             * @since 2025. 6. 20.
             * @version 0.8.0
             *
             * @see open.commons.spring.web.authority.metadata.AuthorizedMetadataBuilder.ObjectsBuilder#build()
             */
            @Override
            public List<AuthorizedObjectMetadata> build() {
                return objects.stream().map(b -> b.build()).collect(Collectors.toList());
            }

            /**
             *
             * @since 2025. 6. 20.
             * @version 0.8.0
             *
             * @see open.commons.spring.web.authority.metadata.AuthorizedMetadataBuilder.ObjectsBuilder#object(java.util.function.Function)
             */
            @Override
            public ObjectsBuilder object(Function<ObjectBuilder, ObjectBuilder> consumer) {
                Objects.requireNonNull(consumer);

                ObjectBuilder builder = new ObjectBuilderImpl();
                objects.add(consumer.apply(builder));
                return this;
            }
        }
    }

    /**
     * Stage 3: Field 단계
     */
    public interface FieldBuilder {
        /**
         * {@link AuthorizedField#authorityBean()}에 해당하는 값을 설정합니다.
         * 
         * <pre>
        * [개정이력]
        *     날짜        | 작성자                   |   내용
        * -----------------------------------------------------
         * 2025. 6. 18.    parkjunhong77@gmail.com     최초 작성
         * </pre>
         *
         * @param authorityBean
         *            {@link IFieldAccessAuthorityProvider}를 구현함 {@link Bean} 이름.
         * @return
         *
         * @since 2025. 6. 18.
         * @version 0.8.0
         * 
         * @see AuthorizedFieldMetadata#setAuthorityBean(String)
         */
        FieldBuilder authorityBean(String authorityBean);

        /**
         * {@link AuthorizedFieldMetadata} 객체를 생성합니다. <br>
         * 
         * <pre>
        * [개정이력]
        *     날짜        | 작성자                   |   내용
        * -----------------------------------------------------
         * 2025. 6. 18.    parkjunhong77@gmail.com     최초 작성
         * </pre>
         *
         * @return
         *
         * @since 2025. 6. 18.
         * @version 0.8.0
         */
        AuthorizedFieldMetadata build();

        /**
         * {@link AuthorizedField#fieldHandleBean()}에 해당하는 값을 설정합니다.
         * 
         * <pre>
        * [개정이력]
        *     날짜        | 작성자                   |   내용
        * -----------------------------------------------------
         * 2025. 6. 18.    parkjunhong77@gmail.com     최초 작성
         * </pre>
         *
         * @param fieldHandleBean
         *            {@link IUnauthorizedFieldHandler}를 구현한 {@link Bean} 이름
         * @return
         *
         * @since 2025. 6. 18.
         * @version 0.8.0
         */
        FieldBuilder fieldHandleBean(String fieldHandleBean);

        /**
         * {@link AuthorizedField#handleType()}에 해당하는 값을 설정합니다.<br>
         * 
         * <pre>
        * [개정이력]
        *     날짜        | 작성자                   |   내용
        * -----------------------------------------------------
         * 2025. 6. 18.    parkjunhong77@gmail.com     최초 작성
         * </pre>
         *
         * @param handleType
         *            데이터 처리 유형
         * @return
         *
         * @since 2025. 6. 18.
         * @version 0.8.0
         */
        FieldBuilder handleType(String handleType);

        /**
         * {@link AuthorizedField#name()}에 해당하는 값을 설정합니다.<br>
         * 
         * <pre>
        * [개정이력]
        *     날짜        | 작성자                   |   내용
        * -----------------------------------------------------
         * 2025. 6. 18.    parkjunhong77@gmail.com     최초 작성
         * </pre>
         *
         * @param name
         * @return
         *
         * @since 2025. 6. 18.
         * @version 0.8.0
         */
        FieldBuilder name(String name);
    }

    /**
     * Stage 2: Object 단계
     */
    public interface ObjectBuilder {

        /**
         * {@link AuthorizedField#authorityBean()}에 해당하는 값을 설정합니다.
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2025. 6. 18.     parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @param authorityBean
         *            {@link IFieldAccessAuthorityProvider}를 구현함 {@link Bean} 이름.
         * @return
         *
         * @since 2025. 6. 18.
         * @version 0.8.0
         */
        ObjectBuilder authorityBean(String authorityBean);

        /**
         * {@link AuthorizedObjectMetadata} 객체를 생성합니다. <br>
         * 
         * <pre>
        * [개정이력]
        *     날짜        | 작성자                   |   내용
        * -----------------------------------------------------
         * 2025. 6. 18.    parkjunhong77@gmail.com     최초 작성
         * </pre>
         *
         * @return
         *
         * @since 2025. 6. 18.
         * @version 0.8.0
         */
        AuthorizedObjectMetadata build();

        /**
         * {@link AuthorizedFieldMetadata} Builder를 생성합니다. <br>
         * 
         * <pre>
        * [개정이력]
        *     날짜        | 작성자                   |   내용
        * -----------------------------------------------------
         * 2025. 6. 19.    parkjunhong77@gmail.com     최초 작성
         * </pre>
         *
         * @param function
         * @return
         *
         * @since 2025. 6. 19.
         * @version 0.8.0
         */
        ObjectBuilder field(Function<FieldBuilder, FieldBuilder> function);

        /**
         * {@link AuthorizedObject#fieldHandleBean()}에 해당하는 값을 설정합니다.
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2025. 6. 18.     parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @param fieldHandleBean
         *            {@link IUnauthorizedFieldHandler}를 구현한 {@link Bean} 이름
         * @return
         *
         * @since 2025. 6. 18.
         * @version 0.8.0
         */
        ObjectBuilder fieldHandleBean(String fieldHandleBean);

        /**
         * {@link AuthorizedObject}가 적용되는 데이터 유형을 설정합니다.<br>
         * 
         * <pre>
        * [개정이력]
        *     날짜        | 작성자                   |   내용
        * -----------------------------------------------------
         * 2025. 6. 18.    parkjunhong77@gmail.com     최초 작성
         * </pre>
         *
         * @param type
         *            데이터 유형
         * @return
         *
         * @since 2025. 6. 18.
         * @version 0.8.0
         */
        ObjectBuilder type(@NotNull Class<?> type);
    }

    public interface ObjectsBuilder {

        List<AuthorizedObjectMetadata> build();

        ObjectsBuilder object(Function<ObjectBuilder, ObjectBuilder> function);
    }
}
