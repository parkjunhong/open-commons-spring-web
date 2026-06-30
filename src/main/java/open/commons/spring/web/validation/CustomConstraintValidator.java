/*
 * Copyright 2021 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2021. 7. 5. 오후 6:57:18
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.validation;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Set;
import java.util.function.BiFunction;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import open.commons.core.utils.AssertUtils2;

/**
 * Jakarta Validation(JSR-380) 기반의 커스텀 검증기(Validator)를 손쉽게 구현하기 위한 추상 기반 클래스(Abstract Base
 * Class)입니다.
 * <p>
 * <b>[설계 목적 및 주요 기능]</b><br>
 * 커스텀 검증기 로직 내부에서 복합 객체를 프로그래밍 방식으로 재검증하거나 특정 검증 그룹(Validation Groups)을 적용할 때, 발생하는 기본 제약조건
 * 비활성화({@code disableDefaultConstraintViolation}) 및 속성 경로 매핑 등의 보일러플레이트 코드를 제거합니다. 주입받은
 * {@link Validator}를 활용하여 검증을 수행하고, 위반(Violation) 내역을 스프링의 에러 컨텍스트에 자동으로 매핑해 줍니다.
 * </p>
 * 
 * <p>
 * <b>[사용 예시 (Usage)]</b><br>
 * 하위 클래스에서는 이 클래스를 상속받아 {@code isValid} 구현 시, 제공되는 유틸리티 메소드({@link #validateSingleNode} 또는
 * {@link #validateMultiNodes})와 {@code this::getViolation} 등을 결합하여 다음과 같이 간결하게 작성할 수 있습니다.
 * </p>
 * 
 * <pre>
 * &#64;Component
 * public class MyCustomValidator extends CustomConstraintValidator&lt;MyAnnotation, MyObject&gt; {
 * 
 *     public MyCustomValidator(Validator validator) {
 *         super(validator);
 *     }
 * 
 *     &#64;Override
 *     public boolean isValid(MyObject value, ConstraintValidatorContext context) {
 *         // 특정 로직 수행 후, value 내부의 표준 제약조건들을 수동으로 검사하고 결과를 컨텍스트에 자동 위임
 *         return validateMultiNodes(context, this::getViolations, value, Default.class);
 *     }
 * }
 * </pre>
 * 
 * *
 * 
 * <pre>
 * [개정이력]
 * 날짜        | 작성자                   |   내용
 * -----------------------------------------------------
 * 2021. 7. 5.    parkjunhong77@gmail.com     최초 작성
 * 2026. 6. 30.   parkjunhong77@gmail.com     API 문서(JavaDoc) 목적 및 사용법 상세화
 * </pre>
 * 
 * @since 2021. 7. 5.
 * @version 0.3.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 * @param <A>
 *            처리할 커스텀 어노테이션 타입
 * @param <T>
 *            검증 대상이 되는 객체 타입
 */
public abstract class CustomConstraintValidator<A extends Annotation, T> implements ConstraintValidator<A, T> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected Validator validator;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2021. 7. 5.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param validator
     *            스프링 컨테이너로부터 주입받을 표준 Validator 인스턴스
     *
     * @since 2021. 7. 5.
     * @version 0.3.0
     */
    public CustomConstraintValidator(Validator validator) {
        AssertUtils2.notNull(validator);

        this.validator = validator;
    }

    /**
     * 제한조건 위반 메시지를 생성합니다. <br>
     * 기본 제공되는 제약조건 위반 메시지를 템플릿과 프로퍼티 경로(Property Path)를 활용하여 컨텍스트에 래핑(Wrapping)합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2021. 7. 5.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param context
     *            현재 검증 컨텍스트
     * @param v
     *            발생한 제약조건 위반 객체
     *
     * @since 2021. 7. 5.
     * @version 0.3.0
     */
    protected void buildViolationMessage(ConstraintValidatorContext context, ConstraintViolation<T> v) {
        context.buildConstraintViolationWithTemplate(v.getMessageTemplate()) //
                .addPropertyNode(v.getPropertyPath().toString()) //
                .addConstraintViolation();
    }

    /**
     * 제한조건을 위반한 단일 정보를 제공합니다. (Fast-Fail 방식 적용 시 유용) <br>
     * 제공된 검증 그룹(groups)을 기반으로 대상을 검증하고, 여러 위반 내역 중 첫 번째 요소만 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 7. 5.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param value
     *            검증 대상 객체
     * @param groups
     *            적용할 검증 그룹 클래스 목록
     * 
     * @return 첫 번째로 발견된 {@link ConstraintViolation} 객체, 위반 사항이 없으면 {@code null}
     *
     * @since 2021. 7. 5.
     */
    protected ConstraintViolation<T> getViolation(T value, Class<?>... groups) {
        AssertUtils2.notNull(value);
        AssertUtils2.notNulls((Object[]) groups);

        Set<ConstraintViolation<T>> vs = this.validator.validate(value, groups);
        if (vs.isEmpty()) {
            return null;
        } else {
            return vs.iterator().next();
        }
    }

    /**
     * 제한조건을 위반한 모든 정보(Set)를 제공합니다. (전체 스캔 검증 방식) <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 7. 5.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param value
     *            검증 대상 객체
     * @param groups
     *            적용할 검증 그룹 클래스 목록
     * 
     * @return 위반 사항이 담긴 {@link Set}, 위반 사항이 없으면 {@code null}
     *
     * @since 2021. 7. 5.
     */
    protected Set<ConstraintViolation<T>> getViolations(T value, Class<?>... groups) {
        AssertUtils2.notNull(value);
        AssertUtils2.notNulls((Object[]) groups);

        logger.debug("value: {}, groups:{}", value, groups != null ? Arrays.toString(groups) : null);

        Set<ConstraintViolation<T>> vs = this.validator.validate(value, groups);
        if (vs.isEmpty()) {
            return null;
        } else {
            return vs;
        }
    }

    /**
     * 대상 객체에 대해 검증을 수행하고, 1개 이상의 위반 발생 시 컨텍스트에 메시지를 자동 할당한 후 결과를 반환합니다. <br>
     * 내부적으로 기존 디폴트 메시지를 끄고({@code disableDefaultConstraintViolation}), 발생한 모든 에러 메시지를 컨텍스트에 주입합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2021. 7. 5.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param context
     *            검증 컨텍스트
     * @param provider
     *            다중 위반 검증 기능을 수행할 함수형 인터페이스 (통상적으로 {@code this::getViolations} 전달)
     * @param value
     *            검증 대상 데이터
     * @param groups
     *            적용할 검증 그룹
     * 
     * @return 유효할 경우 {@code true}, 위반 항목이 존재할 경우 {@code false}
     *
     * @since 2021. 7. 5.
     * @version 0.3.0
     */
    protected boolean validateMultiNodes(ConstraintValidatorContext context,
            BiFunction<T, Class<?>[], Set<ConstraintViolation<T>>> provider, T value, Class<?>... groups) {
        AssertUtils2.notNulls(context, provider, value);
        AssertUtils2.notNulls((Object[]) groups);

        logger.debug("value: {}, groups:{}", value, groups != null ? Arrays.toString(groups) : null);

        Set<ConstraintViolation<T>> vs = provider.apply(value, groups);
        if (vs != null) {
            context.disableDefaultConstraintViolation();
            vs.forEach(v -> buildViolationMessage(context, v));
            return false;
        }
        return true;
    }

    /**
     * 대상 객체에 대해 단일 위반 사항 검증을 수행하고, 위반 시 컨텍스트에 메시지를 자동 할당한 후 결과를 반환합니다. <br>
     * Fast-Fail 형태의 검증을 원할 때 주로 사용됩니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2021. 7. 5.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param context
     *            검증 컨텍스트
     * @param provider
     *            단일 위반 검증 기능을 수행할 함수형 인터페이스 (통상적으로 {@code this::getViolation} 전달)
     * @param value
     *            검증 대상 데이터
     * @param groups
     *            적용할 검증 그룹
     * @return 유효할 경우 {@code true}, 위반 항목이 존재할 경우 {@code false}
     *
     * @since 2021. 7. 5.
     * @version 0.3.0
     */
    protected boolean validateSingleNode(ConstraintValidatorContext context,
            BiFunction<T, Class<?>[], ConstraintViolation<T>> provider, T value, Class<?>... groups) {
        AssertUtils2.notNulls(context, provider, value);
        AssertUtils2.notNulls((Object[]) groups);

        logger.debug("value: {}, groups:{}", value, groups != null ? Arrays.toString(groups) : null);

        ConstraintViolation<T> v = provider.apply(value, groups);
        if (v != null) {
            context.disableDefaultConstraintViolation();
            buildViolationMessage(context, v);
            return false;
        }
        return true;
    }
}
