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
 * Date  : 2026. 6. 30. 오전 11:19:42
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.validation.json;

import java.util.Collection;

import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Validator;

import open.commons.core.utils.StringUtils;
import open.commons.spring.web.validation.CustomConstraintValidator;

import tools.jackson.databind.json.JsonMapper;

/**
 * {@link Collection}&lt;{@link String}&gt; 타입에 대해 포함된 모든 요소가 유효한 JSON 인지 일괄 검증하는 Validator 입니다.
 * <p>
 * <b>[사용 방식 및 TYPE_USE 와의 차이점 가이드]</b><br>
 * 개발자의 요구사항과 에러 리포팅 수준(Granularity)에 따라 아래 두 가지 방식 중 하나를 선택하여 사용할 수 있습니다.
 * </p>
 * 
 * <ul>
 * <li><b>1. 필드(Field) 레벨 선언 - 일괄 검증 (본 클래스 가동)</b>
 * <ul>
 * <li><b>선언:</b> {@code @JsonString private List<String> eventDataList;}</li>
 * <li><b>동작:</b> 컬렉션 내부 요소 중 하나라도 JSON 파싱에 실패하면 즉시(Fast-Fail) 검증을 중단합니다.</li>
 * <li><b>결과:</b> 정확히 몇 번째 인덱스에서 실패했는지 알 수 없으며, 컬렉션 전체에 대한 1개의 에러(ConstraintViolation)만 반환됩니다. 빠른
 * 실패와 단순한 예외 처리가 필요할 때 적합합니다.</li>
 * </ul>
 * </li> <br>
 * <li><b>2. TYPE_USE 레벨 선언 - 개별 요소 검증 (권장)</b>
 * <ul>
 * <li><b>선언:</b> {@code private List<@JsonString String> eventDataList;}</li>
 * <li><b>동작:</b> 본 클래스가 아닌 {@link JsonStringValidatorForString}이 컬렉션의 요소 개수만큼 반복하여 가동됩니다.</li>
 * <li><b>결과:</b> 스프링 프레임워크가 {@code eventDataList[2]}와 같이 실패한 정확한 인덱스 경로(Path)를 BindingResult에
 * 제공합니다. 프론트엔드 연동 시 정밀한 폼(Form) 디버깅이 필요할 때 매우 강력합니다.</li>
 * </ul>
 * </li>
 * </ul>
 * 
 * <br>
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
public class JsonStringValidatorForCollection extends CustomConstraintValidator<JsonString, Collection<String>> {

    private final JsonMapper jsonMapper;

    public JsonStringValidatorForCollection(Validator validator, JsonMapper jsonMapper) {
        super(validator);
        this.jsonMapper = jsonMapper;
    }

    /**
     * 
     * {@inheritDoc}
     *
     * @since 2026. 6. 30.
     * @version 4.0.0
     *
     * @see jakarta.validation.ConstraintValidator#isValid(java.lang.Object,
     *      jakarta.validation.ConstraintValidatorContext)
     */
    @Override
    public boolean isValid(Collection<String> values, ConstraintValidatorContext context) {
        if (values == null || values.isEmpty()) {
            return true;
        }

        for (String value : values) {
            if (StringUtils.isNullOrEmptyString(value)) {
                continue; // 빈 값 검증은 @NotBlank의 영역이므로 통과시킴
            }
            try {
                this.jsonMapper.readTree(value);
            } catch (Exception e) {
                this.logger.debug("Collection 내에 유효하지 않은 JSON 문자열이 존재합니다. value={}", value, e);
                return false;
            }
        }
        return true;
    }
}
