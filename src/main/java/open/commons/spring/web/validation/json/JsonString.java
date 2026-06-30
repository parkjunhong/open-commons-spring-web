/*
 * Copyright 2026 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2026. 6. 30. 오전 11:16:56
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.validation.json;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * 대상 문자열이 유효한 JSON 형식인지 검증하는 어노테이션입니다.<br>
 * 1개의 문자열 및 Collection 내부의 문자열들에 대해 일괄 적용 가능합니다. <br>
 * 
 * <pre>
 * [개정이력]
 *     날짜        | 작성자                   |   내용
 * -----------------------------------------------------
 * 2026. 6. 30.     parkjunhong77@gmail.com     최초 작성
 * </pre>
 *
 * @since 2026. 6. 30.
 * @version 4.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
@Documented
@Constraint(validatedBy = { JsonStringValidatorForString.class, JsonStringValidatorForCollection.class })
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonString {

    Class<?>[] groups() default {};

    String message() default "유효한 JSON 문자열 형식이 아닙니다.";

    Class<? extends Payload>[] payload() default {};

}
