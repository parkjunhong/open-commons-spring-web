/*
 * Copyright 2019 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2019. 10. 8. 오후 1:26:57
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotBlank;

import open.commons.core.utils.AssertUtils2;

/**
 * {@link Validator} 기능 지원 클래스.
 * 
 * @since 2019. 6. 18.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class ValidationUtils {

    private static final Validator VALIDATOR;
    static {
        VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
    }

    // prevent to be created
    private ValidationUtils() {
    }

    /**
     * ConstraintViolation 집합을 문자열 에러 메시지 컬렉션으로 변환합니다.
     * 
     * @param <T>
     *            검증 대상 객체의 제네릭 타입
     * @param violations
     *            검증 실패 결과 집합 (null을 허용하지만, 정상적인 흐름에서는 null이 전달되지 않음)
     * 
     * @return 에러 메시지 컬렉션 (절대 null을 반환하지 않으며, 실패 결과가 없으면 빈 컬렉션을 반환)
     */
    private static <T> Collection<String> makeErrorMessage(Set<ConstraintViolation<T>> violations) {
        if (violations.size() < 1) {
            return Collections.emptyList();
        }

        return violations.stream() //
                .map(//
                        v -> String.join(" => " //
                                , String.join("." //
                                        , v.getRootBeanClass().getCanonicalName() //
                                        , v.getPropertyPath().toString()) //
                                , v.getMessage()) //
                ) //
                .toList();
    }

    /**
     * 객체를 검증합니다. <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2019. 10. 15.    parkjunhong77@gmail.com     최초 작성
     * 2026.  4. 16.    parkjunhong77@gmail.com     미구현된 nested 논리에 대해 Fail-Fast 예외 적용
     * </pre>
     *
     * @param <T>
     *            검증 대상 객체의 타입
     * @param object
     *            검증 대상 객체
     * @param nested
     *            내부 필드 검증 여부 (true 지원 안 함 - 호출 시 UnsupportedOperationException 발생)
     * @param groups
     *            적용할 검증 그룹 배열 (배열 자체 및 배열 내 요소 모두 null을 허용하지 않음)
     * 
     * @return 검증 실패 결과 집합
     *
     * @since 2019. 10. 15.
     */
    public static <T> Set<ConstraintViolation<T>> validate(T object, boolean nested, Class<?>... groups) {
        AssertUtils2.notNull(object);
        AssertUtils2.notNulls((Object[]) groups);

        // [PATCH] 불필요한 분기 및 TODO 방치 제거. 미구현 기능에 대한 명시적 Fail-Fast 적용
        if (nested) {
            throw new UnsupportedOperationException("Nested validation is not implemented yet.");
        }
        return validate(object, groups);
    }

    /**
     * 객체를 검증합니다. <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 18.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            검증 대상 객체의 타입
     * @param object
     *            검증 대상 객체
     * @param groups
     *            적용할 검증 그룹 배열 (배열 자체 및 배열 내 요소 모두 null을 허용하지 않음)
     * 
     * @return 검증 실패 결과 집합
     *
     * @since 2019. 6. 18.
     * 
     * @see Validator#validate(Object, Class...)
     */
    public static <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {
        AssertUtils2.notNull(object);
        AssertUtils2.notNulls((Object[]) groups);

        return VALIDATOR.validate(object, groups);
    }

    /**
     * 객체를 검증한 후 검증통과 실패 원인 메시지를 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2019. 10. 15.    parkjunhong77@gmail.com     최초 작성
     * 2026.  4. 16.    parkjunhong77@gmail.com     반환값 Null-Safety 적용 (null -> 빈 컬렉션 반환)
     * </pre>
     *
     * @param <T>
     *            검증 대상 객체의 타입
     * @param object
     *            검증 대상 객체
     * @param nested
     *            내부필드 검증 여부 (true 입력 시 예외 발생)
     * @param groups
     *            적용할 검증 그룹 배열
     * 
     * @return 검증통과 실패 원인 메시지 컬렉션
     *
     * @since 2019. 10. 15.
     */
    public static <T> Collection<String> validateAndErrorMsg(T object, boolean nested, Class<?>... groups) {
        return makeErrorMessage(validate(object, nested, groups));
    }

    /**
     * 객체를 검증한 후 검증통과 실패 원인 메시지를 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2019. 10. 15.    parkjunhong77@gmail.com     최초 작성
     * 2026.  4. 16.    parkjunhong77@gmail.com     반환값 Null-Safety 적용 (null -> 빈 컬렉션 반환)
     * </pre>
     *
     * @param <T>
     *            검증 대상 객체의 타입
     * @param object
     *            검증 대상 객체
     * @param nested
     *            내부필드 검증 여부 (true 입력 시 예외 발생)
     * @param groups
     *            적용할 검증 그룹 배열
     * 
     * @return 검증통과 실패 원인 메시지 컬렉션
     *
     * @since 2019. 10. 15.
     */
    public static <T> Collection<String> validateAndErrorMsg(T object, Class<?>... groups) {
        return makeErrorMessage(validate(object, groups));
    }

    /**
     * 객체의 특정 속성(Property)을 검증합니다. <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 18.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            검증 대상 객체의 타입
     * @param object
     *            검증 대상 객체
     * @param propertyName
     *            속성이름
     * @param groups
     *            적용할 검증 그룹 배열
     * 
     * @return 검증 실패 결과 집합
     *
     * @since 2019. 6. 18.
     * @see Validator#validateProperty(Object, String, Class...)
     */
    public static <T> Set<ConstraintViolation<T>> validateProperty(T object, @NotBlank String propertyName, Class<?>... groups) {
        AssertUtils2.notNull(object);
        AssertUtils2.notBlank(propertyName, "속성이름은 '빈 문자열'을 허용하지 않습니다.");
        AssertUtils2.notNulls((Object[]) groups);

        return VALIDATOR.validateProperty(object, propertyName, groups);
    }

    /**
     * 객체의 특정 속성을 검증한 후 실패 원인 메시지를 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 18.     parkjunhong77@gmail.com         최초 작성
     * 2026.  4. 16.    parkjunhong77@gmail.com         반환값 Null-Safety 적용 (null -> 빈 컬렉션 반환)
     * </pre>
     *
     * @param <T>
     *            검증 대상 객체의 타입
     * @param object
     *            검증 대상 객체
     * @param propertyName
     *            속성이름
     * @param groups
     *            적용할 검증 그룹 배열
     * 
     * @return 검증통과 실패 원인 메시지 컬렉션
     * 
     * @since 2019. 6. 18.
     * @see Validator#validateProperty(Object, String, Class...)
     */
    public static <T> Collection<String> validatePropertyAndErrorMsg(T object, @NotBlank String propertyName, Class<?>... groups) {
        return makeErrorMessage(validateProperty(object, propertyName, groups));
    }

    /**
     * 지정된 값(value)이 해당 클래스의 속성에 유효한지 검증합니다. <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 18.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            검증 대상 객체의 타입
     * @param beanType
     *            검증 대상 클래스 정보
     * @param propertyName
     *            속성이름
     * @param value
     *            검증할 실제 값 (검증 로직에 따라 null 허용 여부가 달라짐)
     * @param groups
     *            적용할 검증 그룹 배열
     * 
     * @return 검증 실패 결과 집합
     *
     * @since 2019. 6. 18.
     * @see Validator#validateValue(Class, String, Object, Class...)
     */
    public static <T> Set<ConstraintViolation<T>> validateValue(Class<T> beanType, @NotBlank String propertyName, Object value, Class<?>... groups) {
        AssertUtils2.notNull(beanType);
        AssertUtils2.notBlank(propertyName, "속성이름은 '빈 문자열'을 허용하지 않습니다.");
        AssertUtils2.notNulls((Object[]) groups);

        return VALIDATOR.validateValue(beanType, propertyName, value, groups);
    }

    /**
     * 
     * 지정된 값(value)이 해당 클래스의 속성에 유효한지 검증한 후 실패 원인 메시지를 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 18.     parkjunhong77@gmail.com         최초 작성
     * 2026.  4. 16.    parkjunhong77@gmail.com         반환값 Null-Safety 적용 (null -> 빈 컬렉션 반환)
     * </pre>
     *
     * @param <T>
     *            검증 대상 객체의 타입
     * @param beanType
     *            검증 대상 클래스 정보
     * @param propertyName
     *            속성이름
     * @param value
     *            검증할 실제 값 (검증 로직에 따라 null 허용 여부가 달라짐)
     * @param groups
     *            적용할 검증 그룹 배열
     * 
     * @return 검증통과 실패 원인 메시지 컬렉션
     *
     * @since 2019. 6. 18.
     * @see Validator#validateValue(Class, String, Object, Class...)
     */
    public static <T> Collection<String> validateValueAndErrorMsg(Class<T> beanType, @NotBlank String propertyName, Object value, Class<?>... groups) {
        return makeErrorMessage(validateValue(beanType, propertyName, value, groups));
    }
}
