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
 * Date  : 2025. 5. 23. 오후 4:57:07
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.jackson.serialization;

import org.springframework.context.ApplicationContext;

import open.commons.spring.web.authority.AuthorizedField;
import open.commons.spring.web.authority.AuthorizedObject;
import open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata;
import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;
import tools.jackson.databind.introspect.AnnotatedField;

/**
 * {@link SecureField}가 적용된 Field를 JSON 문자열로 변한해 주는 클래스.
 * 
 * 
 * <pre>
 * [개정이력]
 *      날짜       | 작성자                   |   내용
 * -----------------------------------------------------
 * 2025. 5. 25.     parkjunhong77@gmail.com     최초 작성
 * 2026. 4. 14.     parkjunhong77@gmail.com     Jackson 3.0 현행화 ( com.fasterxml.jackson.xxx => tools.jackson.databind.xxx )
 * </pre>
 * 
 * 
 * @since 2025. 5. 23.
 * @version 4.0.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedFieldSerializer extends AbstractWrappingSerializer {

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 5. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     * 
     * @param context
     *            TODO
     * @param serializedType
     *            데이터 유형
     * @param authority
     *            필드 접근권한 서비스
     * @param fieldHandler
     *            데이터 처리 서비스
     * @param authorizedResourcesMetadata
     *            {@link AuthorizedObject}, {@link AuthorizedField} 외부 설정 정보 제공 서비스
     * 
     * @since 2025. 5. 23.
     * @version 0.8.0
     */
    public AuthorizedFieldSerializer(ApplicationContext context, Class<?> serializedType, AnnotatedField field,
            IFieldAccessAuthorityProvider authority, IUnauthorizedFieldHandler fieldHandler,
            IAuthorizedResourcesMetadata authorizedResourcesMetadata) {
        super(context, serializedType, field, authority, fieldHandler, authorizedResourcesMetadata);
    }

    /**
     * 
     * {@inheritDoc}
     *
     * @since 2026. 4. 14.
     * @version 4.0.0
     *
     * @see tools.jackson.databind.ValueSerializer#serialize(java.lang.Object,
     *      tools.jackson.core.JsonGenerator, tools.jackson.databind.SerializationContext)
     */
    @Override
    public void serialize(Object rawValue, JsonGenerator gen, SerializationContext context) throws JacksonException {

        // #1. Null 처리 (기존 로직 유지 - Jackson 3.0에서도 가장 깔끔한 방식입니다)
        if (rawValue == null) {
            gen.writeNull();
            return;
        }

        // #2. 단순타입이 아니면 컨테이너/POJO → 명시적 위임
        if (!isSimpleType(rawValue.getClass())) {
            // [PATCH] defaultSerializeValue 대체: 실제 타입에 맞는 직렬화기를 찾아 위임
            ValueSerializer<Object> serializer = context.findValueSerializer(rawValue.getClass());
            serializer.serialize(rawValue, gen, context);
            return;
        }

        // #3. 보안/권한 처리 로직 적용 (난독화, 암복호화 등)
        Object value = handleValue(rawValue, decide());

        // #4. 조작된 결과값에 대한 Null 방어 (handleValue의 결과가 null일 수 있으므로 안전장치 추가)
        if (value == null) {
            gen.writeNull();
            return;
        }

        // #5. 최종 처리된 값에 대한 명시적 위임
        // [PATCH] defaultSerializeValue 대체
        ValueSerializer<Object> finalSerializer = context.findValueSerializer(value.getClass());
        finalSerializer.serialize(value, gen, context);
    }
}
