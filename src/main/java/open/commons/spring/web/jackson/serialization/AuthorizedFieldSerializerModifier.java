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
 * Date  : 2025. 5. 25. 오전 11:58:20
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.jackson.serialization;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import jakarta.validation.constraints.NotNull;

import org.springframework.context.ApplicationContext;

import open.commons.spring.web.authority.AuthorizedField;
import open.commons.spring.web.authority.AuthorizedObject;
import open.commons.spring.web.authority.AuthorizedResourceUtils;
import open.commons.spring.web.authority.metadata.AuthorizedFieldMetadata;
import open.commons.spring.web.authority.metadata.AuthorizedObjectMetadata;
import open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata;
import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;
import open.commons.spring.web.utils.BeanUtils;

import tools.jackson.databind.BeanDescription;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.SerializationConfig;
import tools.jackson.databind.introspect.AnnotatedClass;
import tools.jackson.databind.introspect.AnnotatedField;
import tools.jackson.databind.introspect.BeanPropertyDefinition;
import tools.jackson.databind.ser.BeanPropertyWriter;
import tools.jackson.databind.ser.ValueSerializerModifier;

/**
 * <pre>
 * [개정이력]
 *      날짜       | 작성자                   |   내용
 * -----------------------------------------------------
 * 2025. 5. 25.     parkjunhong77@gmail.com     최초 작성
 * 2026. 4. 14.     parkjunhong77@gmail.com     Jackson 3.0 현행화: Supplier<BeanDescription> 지연 평가 적용 및 {@link ValueSerializerModifier} 적용
 * </pre>
 *
 * @since 2025. 5. 25.
 * @version 4.0.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedFieldSerializerModifier extends ValueSerializerModifier {

    private static final long serialVersionUID = 1L;

    /** 메타데이터 형태로 기술된 {@link AuthorizedObject}, {@link AuthorizedField} 설정 정보 제공 서비스 */
    private final IAuthorizedResourcesMetadata authorizedResourcesMetadata;

    /** 동적으로 bean을 제공 */
    private final BeanUtils BEANS;

    /**
     * <br>
     * *
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 5. 25.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     * 
     * * @param context
     * 
     * @param authorizedResourcesMetadata
     *            {@link AuthorizedObject}, {@link AuthorizedField} 설정 정보 제공 서비스
     *
     * @since 2025. 5. 25.
     * @version 0.8.0
     */
    public AuthorizedFieldSerializerModifier(@NotNull ApplicationContext context, IAuthorizedResourcesMetadata authorizedResourcesMetadata) {
        this.BEANS = BeanUtils.context(context);
        this.authorizedResourcesMetadata = authorizedResourcesMetadata;
    }

    /**
     *
     * @see tools.jackson.databind.ser.ValueSerializerModifier#changeProperties(tools.jackson.databind.SerializationConfig,
     *      java.util.function.Supplier, java.util.List)
     */
    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription.Supplier beanDescSupplier, List<BeanPropertyWriter> beanProperties) {

        // 지연 평가(Lazy Evaluation) 해제: 필요한 순간에 get()을 호출하여 BeanDescription 획득
        BeanDescription beanDesc = beanDescSupplier.get();

        final AnnotatedClass annoClass = beanDesc.getClassInfo();
        final Class<?> serializedType = annoClass.getAnnotated();
        // #1. 타입에 정의된 어노테이션 조회
        final AuthorizedObject annoAuthorizedObject = annoClass.getAnnotation(AuthorizedObject.class);

        // #2. 필드 처리.
        AnnotatedField annoField = null;
        AuthorizedField annoAuthorizedField = null;
        IFieldAccessAuthorityProvider fieldAccessor = null;
        IUnauthorizedFieldHandler fieldHandler = null;

        Supplier<String> authorityBeanNameOnObject = null;
        Supplier<String> authorityBeanNameOnField = null;
        Supplier<String> fieldHandleBeanNamOnObject = null;
        Supplier<String> fieldHandleBeanNamOnField = null;

        for (BeanPropertyWriter writer : beanProperties) {
            annoField = beanDesc.findProperties().stream() //
                    .filter(p -> p.getName().equals(writer.getName())) //
                    .map(BeanPropertyDefinition::getField) //
                    .filter(Objects::nonNull) //
                    .findFirst() //
                    .orElse(null);

            // AuthorizedField가 없는 경우
            if (annoField == null) {
                continue;
            }

            String fieldName = annoField.getName();
            annoAuthorizedField = annoField.getAnnotation(AuthorizedField.class);

            // 조건1: ao 와 af 가 반드시 있어야 합니다.
            if (annoAuthorizedObject != null && annoAuthorizedField != null) {
                authorityBeanNameOnObject = annoAuthorizedObject::authorityBean;
                authorityBeanNameOnField = annoAuthorizedField::authorityBean;
                fieldHandleBeanNamOnObject = annoAuthorizedObject::fieldHandleBean;
                fieldHandleBeanNamOnField = annoAuthorizedField::fieldHandleBean;
            }
            // serialize 대상 데이터 유형(class)을 기준으로 검색
            else if (this.authorizedResourcesMetadata.isAuthorizedField(serializedType, fieldName)) {
                AuthorizedObjectMetadata aom = this.authorizedResourcesMetadata.getAuthorizedObjectMetadata(serializedType);
                authorityBeanNameOnObject = () -> aom.getAuthorityBean();
                fieldHandleBeanNamOnObject = () -> aom.getFieldHandleBean();

                AuthorizedFieldMetadata afm = this.authorizedResourcesMetadata.getAuthorizedFieldMetadata(serializedType, fieldName);
                authorityBeanNameOnField = () -> afm.getAuthorityBean();
                fieldHandleBeanNamOnField = () -> afm.getFieldHandleBean();
            } // 실제 field가 선언된 데이터 유형(class)을 기준으로 검색
            else if (this.authorizedResourcesMetadata.isAuthorizedField(annoField.getDeclaringClass(), fieldName)) {
                Class<?> declaringClass = annoField.getDeclaringClass();

                AuthorizedObjectMetadata aom = this.authorizedResourcesMetadata.getAuthorizedObjectMetadata(declaringClass);
                authorityBeanNameOnObject = () -> aom.getAuthorityBean();
                fieldHandleBeanNamOnObject = () -> aom.getFieldHandleBean();

                AuthorizedFieldMetadata afm = this.authorizedResourcesMetadata.getAuthorizedFieldMetadata(declaringClass, fieldName);
                authorityBeanNameOnField = () -> afm.getAuthorityBean();
                fieldHandleBeanNamOnField = () -> afm.getFieldHandleBean();
            } else {
                continue;
            }

            fieldAccessor = AuthorizedResourceUtils.getBean(this.BEANS, IFieldAccessAuthorityProvider.class, authorityBeanNameOnObject, authorityBeanNameOnField, false);
            fieldHandler = AuthorizedResourceUtils.getBean(this.BEANS, IUnauthorizedFieldHandler.class, fieldHandleBeanNamOnObject, fieldHandleBeanNamOnField, true);

            JavaType fieldType = writer.getType();
            Class<?> raw = fieldType.getRawClass();

            // 단순 타입
            if (AuthorizedFieldDecisionUtil.isSimpleType(raw)) {
                writer.assignSerializer(new AuthorizedFieldSerializer(BEANS.context(), serializedType, annoField, fieldAccessor, fieldHandler, this.authorizedResourcesMetadata));
            }
            // 배열, Collection
            else if (fieldType.isArrayType() || fieldType.isCollectionLikeType()) {
                writer.assignSerializer(new ContainerSimpleTypeElementWrappingSerializer(BEANS.context(), serializedType, annoField, fieldAccessor, fieldHandler,
                        this.authorizedResourcesMetadata));
            }
            // Map
            else if (fieldType.isMapLikeType()) {
                writer.assignSerializer(
                        new MapSimpleTypeValueWrappingSerializer(BEANS.context(), serializedType, annoField, fieldAccessor, fieldHandler, this.authorizedResourcesMetadata));
            }
            // POJO 필드는 assign하지 않음 → 내부 필드의 @AuthorizedField 가 처리
        }

        return beanProperties;
    }
}