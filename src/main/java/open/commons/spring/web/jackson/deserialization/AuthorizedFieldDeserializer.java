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
 * Date  : 2025. 9. 22. 오후 5:11:16
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.jackson.deserialization;

import java.lang.reflect.Field;

import jakarta.validation.constraints.NotBlank;

import open.commons.spring.web.authority.AuthorizedRequestData;
import open.commons.spring.web.beans.authority.IAuthorizedRequestDataHandler;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

/**
 * {@link AuthorizedRequestData}가 선언된 {@link Field} 또는 데이터의 'deserialization'을 처리하는 클래스. *
 * 
 * <pre>
 * [개정이력]
 * 날짜        | 작성자                   |   내용
 * -----------------------------------------------------
 * 2025. 9. 22.    parkjunhong77@gmail.com     최초 작성 (Jackson 2.x)
 * 2026. 4. 14.    parkjunhong77@gmail.com     Jackson 3.0 현행화: ContextualDeserializer 병합 및 ValueDeserializer 적용
 * </pre>
 *
 * @since 2025. 9. 22.
 * @version 4.0.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedFieldDeserializer extends ValueDeserializer<Object> {

    private final IAuthorizedRequestDataHandler handle;
    private final String handleType;

    public AuthorizedFieldDeserializer(IAuthorizedRequestDataHandler handle, @NotBlank String handleType) {
        this.handle = handle;
        this.handleType = handleType;
    }

    /**
     * 
     * {@inheritDoc}
     *
     * @since 2026. 4. 14.
     * @version 4.0.0
     *
     * @see tools.jackson.databind.ValueDeserializer#deserialize(tools.jackson.core.JsonParser,
     *      tools.jackson.databind.DeserializationContext)
     */
    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
        Object raw = p.readValueAs(Object.class);
        return this.handle.restoreValue(this.handleType, raw);
    }

}