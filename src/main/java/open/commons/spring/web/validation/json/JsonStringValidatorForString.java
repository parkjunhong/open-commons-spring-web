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
 * Date  : 2026. 6. 30. 오전 11:12:28
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.validation.json;

import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Validator;

import open.commons.core.utils.StringUtils;
import open.commons.spring.web.validation.CustomConstraintValidator;

import tools.jackson.databind.json.JsonMapper;

/**
 * 단일 {@link String} 타입에 대해 대상 값이 유효한 JSON 형식인지 검증하는 Validator 입니다.
 * <p>
 * <b>[작동 범위 및 활용 가이드]</b><br>
 * 이 클래스는 아래의 두 가지 상황에서 프레임워크에 의해 자동으로 선택되어 가동됩니다.
 * </p>
 * 
 * <ul>
 * <li><b>1. 단일 필드 검증:</b>
 * <ul>
 * <li>선언: {@code @JsonString private String eventData;}</li>
 * <li>설명: 일반적인 단일 문자열 필드가 JSON 규격을 만족하는지 검사합니다.</li>
 * </ul>
 * </li>
 * <li><b>2. 컬렉션 요소 개별 검증 (TYPE_USE):</b>
 * <ul>
 * <li>선언: {@code private List<@JsonString String> eventDataList;}</li>
 * <li>설명: 컬렉션 전용 Validator({@link JsonStringValidatorForCollection}) 대신, 본 클래스가 컬렉션의 요소 개수만큼 반복
 * 실행되어 정확한 에러 경로(예: {@code eventDataList[2]})를 리포팅합니다.</li>
 * </ul>
 * </li>
 * </ul>
 * 
 * <p>
 * <b>[Null 및 빈 값 처리 정책]</b><br>
 * Jakarta Validation 표준 원칙에 따라 {@code null} 또는 빈 문자열({@code ""})은 검증을 통과(true)시킵니다. 필수 값 여부는
 * {@link jakarta.validation.constraints.NotNull @NotNull} 또는
 * {@link jakarta.validation.constraints.NotBlank @NotBlank}를 병행하여 제어하십시오.
 * </p>
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
 * 
 * @see JsonString
 */
public class JsonStringValidatorForString extends CustomConstraintValidator<JsonString, String> {

    private final JsonMapper jsonMapper;

    /**
     * 
     *
     * @since 2026. 6. 30.
     * @version 4.0.0
     */
    public JsonStringValidatorForString(Validator validator, JsonMapper jsonMapper) {
        super(validator);
        this.jsonMapper = jsonMapper;
    }

    /**
     * {@inheritDoc}
     *
     * @since 2026. 6. 30.
     * @version 4.0.0
     *
     * @see jakarta.validation.ConstraintValidator#isValid(java.lang.Object,
     *      jakarta.validation.ConstraintValidatorContext)
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isNullOrEmptyString(value)) {
            return true;
        }

        try {
            this.jsonMapper.readTree(value);
            return true;
        } catch (Exception e) {
            this.logger.debug("유효하지 않은 JSON 문자열입니다. value={}", value, e);
            return false;
        }
    }

}
