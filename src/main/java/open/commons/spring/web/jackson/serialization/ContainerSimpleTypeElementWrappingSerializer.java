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
 * Date  : 2025. 9. 25. 오후 8:03:54
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.jackson.serialization;

import org.springframework.context.ApplicationContext;

import open.commons.spring.web.beans.authority.FieldAccessAuthorityDecision;
import open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata;
import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.introspect.AnnotatedField;

/**
 * 배열/Collection의 단순타입 원소에 AuthorizedField 처리를 적용하는 Serializer.
 * <li>- Jackson 2.12+ 권장 API 사용(writeStartArray(Object,int) / writeStartArray(Object))
 * <li>- 요소 출력은 serializers.defaultSerializeValue(...) 사용
 * <li>- 내부가 컨테이너일 경우 ContextualSerializer로 '중첩 래퍼 체인'을 구성하여 재귀 처리.
 * 
 * <pre>
 * [개정이력]
 *      날짜       | 작성자                   |   내용
 * -----------------------------------------------------
 * 2025. 9. 25.     parkjunhong77@gmail.com     최초 작성
 * 2026. 4. 14.     parkjunhong77@gmail.com     Jackson 3.0 현행화 ( com.fasterxml.jackson.xxx => tools.jackson.databind.xxx )
 * </pre>
 * 
 * 
 * @since 2025. 9. 25.
 * @version 4.0.0
 * @author parkjunhong77@gmail.com
 */
public class ContainerSimpleTypeElementWrappingSerializer extends AbstractWrappingSerializer {

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 9. 25.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     * 
     * @param context
     * @param serializedType
     *            데이터 유형
     * @param annotatedField
     *            필드 어노테이션
     * @param fieldAccessor
     *            필드 접근제어 서비스
     * @param fieldHandler
     *            필드 데이터 처리 서비스
     * @param authorizedResourcesMetadata
     *            메타데이터 제공 서비스
     *
     * @since 2025. 9. 25.
     * @version 0.8.0
     */
    public ContainerSimpleTypeElementWrappingSerializer(ApplicationContext context, Class<?> serializedType,
            AnnotatedField annotatedField, IFieldAccessAuthorityProvider fieldAccessor,
            IUnauthorizedFieldHandler fieldHandler, IAuthorizedResourcesMetadata authorizedResourcesMetadata) {
        super(context, serializedType, annotatedField, fieldAccessor, fieldHandler, authorizedResourcesMetadata);
    }

    /**
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                   | 내용
     * -----------------------------------------------------
     * 2025. 9. 25.    parkjunhong77@gmail.com     최초 작성 (Jackson 2.x)
     * 2026. 4. 14.    parkjunhong77@gmail.com     Jackson 3.0 현행화: SerializationContext, JacksonException, API 명칭 변경(writeName 등) 적용
     * </pre>
     *
     * @since 2025. 9. 25.
     * @version 4.0.0
     *
     * @see tools.jackson.databind.ValueSerializer#serialize(java.lang.Object,
     *      tools.jackson.core.JsonGenerator, tools.jackson.databind.SerializationContext)
     */
    @Override
    public void serialize(Object value, JsonGenerator gen, SerializationContext context) throws JacksonException {

        if (value == null) {
            gen.writeNull();
            return;
        }

        // 현재 필드 정책 계산(한 번만)
        FieldAccessAuthorityDecision decision = decide();
        // 최상위가 배열/컬렉션이 아니더라도 방어적으로 재귀 진입 가능
        writeValueRecursive(value, gen, context, decision);
    }

    /**
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                   | 내용
     * -----------------------------------------------------
     * 2025. 9. 25.    parkjunhong77@gmail.com     최초 작성 (Jackson 2.x)
     * 2026. 4. 14.    parkjunhong77@gmail.com     Jackson 3.0 현행화: SerializationContext, JacksonException, API 명칭 변경(writeName 등) 적용
     * </pre>
     *
     * @param rawValue
     * @param gen
     * @param context
     * @param decision
     * @throws JacksonException
     *
     * @since 2025. 9. 25.
     * @version 4.0.0
     */
    private void writeValueRecursive(Object rawValue, JsonGenerator gen, SerializationContext context,
            FieldAccessAuthorityDecision decision) throws JacksonException {

        if (rawValue == null) {
            gen.writeNull();
            return;
        }

        Class<?> rawClass = rawValue.getClass();

        // 1) 단순 타입 → 바로 변환 후 출력
        if (isSimpleType(rawClass)) {
            Object value = handleValue(rawValue, decision);

            if (value == null) {
                gen.writeNull();
                return;
            }

            // [PATCH] defaultSerializeValue 대체
            setDefaultSerializeValue(value, gen, context);
            return;
        }

        // 2) 배열
        if (rawClass.isArray()) {
            int len = java.lang.reflect.Array.getLength(rawValue);
            try {
                gen.writeStartArray(rawValue, len);
            } catch (Exception e) {
                // [PATCH] Jackson 3.0 표준: setCurrentValue() ->
                // assignCurrentValue()
                gen.assignCurrentValue(rawValue);
                gen.writeStartArray();
            }
            for (int i = 0; i < len; i++) {
                Object e = java.lang.reflect.Array.get(rawValue, i);
                writeValueRecursive(e, gen, context, decision);
            }
            gen.writeEndArray();
            return;
        }

        // 3) Collection
        if (rawValue instanceof java.util.Collection<?>) {
            java.util.Collection<?> col = (java.util.Collection<?>) rawValue;
            try {
                gen.writeStartArray(rawValue, col.size());
            } catch (Exception e) {
                // [PATCH] Jackson 3.0 표준: setCurrentValue() ->
                // assignCurrentValue()
                gen.assignCurrentValue(rawValue);
                gen.writeStartArray();
            }
            for (Object e : col) {
                writeValueRecursive(e, gen, context, decision);
            }
            gen.writeEndArray();
            return;
        }

        // 4) Map → 값만 재귀 처리(키는 문자열화)
        if (rawValue instanceof java.util.Map<?, ?>) {
            java.util.Map<?, ?> map = (java.util.Map<?, ?>) rawValue;
            try {
                gen.writeStartObject(rawValue);
            } catch (Exception e) {
                // [PATCH] Jackson 3.0 표준: setCurrentValue() ->
                // assignCurrentValue()
                gen.assignCurrentValue(rawValue);
                gen.writeStartObject();
            }
            for (java.util.Map.Entry<?, ?> e : map.entrySet()) {
                // [PATCH] Jackson 3.0 핵심 변경: writeFieldName() -> writeName()
                gen.writeName(String.valueOf(e.getKey()));
                writeValueRecursive(e.getValue(), gen, context, decision);
            }
            gen.writeEndObject();
            return;
        }

        // 5) POJO → 기본 직렬화기에 명시적 위임 (내부 @AuthorizedField가 처리)
        setDefaultSerializeValue(rawValue, gen, context);
    }
}
