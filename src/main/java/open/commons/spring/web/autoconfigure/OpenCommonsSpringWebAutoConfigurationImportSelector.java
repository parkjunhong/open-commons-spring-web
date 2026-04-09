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
 * Date  : 2025. 6. 5. 오후 1:04:50
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.autoconfigure;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.boot.autoconfigure.AutoConfigurationImportSelector;
import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * {@code @EnableOpenCommonsSpringWeb} 어노테이션이 선언되었을 때 관련된 설정 클래스들을 로드합니다.
 *
 * <pre>
 * [개정이력]
 * 날짜        | 작성자                    | 내용
 * ----------------------------------------------------------------------
 * 2025. 6. 5.      parkjunhong77@gmail.com     최초 작성
 * 2026. 4. 9.      parkjunohng77@gmail.com     상속관계 수정. ( {@link AutoConfigurationImportSelector} => {@link DeferredImportSelector}, {@link BeanClassLoaderAware} )  
 * </pre>
 * 
 * @since 2025. 6. 5.
 * @version 4.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class OpenCommonsSpringWebAutoConfigurationImportSelector implements DeferredImportSelector, BeanClassLoaderAware {

    private ClassLoader classLoader;

    /**
     * 클래스 로더를 주입받아 설정합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2026. 4. 9.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param classLoader
     *            주입받을 대상 클래스 로더
     *
     * @since 2026. 4. 9.
     * @version 4.0.0
     */
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        Objects.requireNonNull(classLoader, "classLoader는 null일 수 없습니다.");

        this.classLoader = classLoader;
    }

    /**
     * 설정 파일에서 대상 클래스들을 읽어와 조건에 맞게 필터링한 후 임포트할 클래스 목록을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2026. 4. 9.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param importingClassMetadata
     *            임포트 대상 클래스의 어노테이션 메타데이터
     *
     * @return 임포트 처리될 후보 설정 클래스 이름의 배열
     *
     * @since 2026. 4. 9.
     * @version 4.0.0
     *
     * @see org.springframework.context.annotation.DeferredImportSelector#selectImports(AnnotationMetadata)
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // Fail-Fast: JSpecify 정책 준수
        Objects.requireNonNull(importingClassMetadata, "importingClassMetadata는 null일 수 없습니다.");

        AnnotationAttributes attributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableOpenCommonsSpringWeb.class.getName()));

        // 1. '.imports' 파일에서 후보군 로드 (Spring 3.0 이상 권장 방식)
        List<String> candidates = new ArrayList<>();
        ImportCandidates.load(EnableOpenCommonsSpringWeb.class, this.classLoader).forEach(candidates::add);

        if (candidates.isEmpty()) {
            throw new IllegalArgumentException("설정 클래스를 찾을 수 없습니다. META-INF/spring/open.commons.spring.web.autoconfigure.EnableOpenCommonsSpringWeb.imports 파일을 확인하세요.");
        }

        // 2. 어노테이션 속성에서 제외(exclude) 대상 필터링 적용 (가드 클로즈 패턴)
        if (attributes != null && attributes.containsKey("exclude")) {
            Set<String> exclusions = new LinkedHashSet<>(List.of(attributes.getStringArray("exclude")));
            candidates.removeAll(exclusions);
        }

        return candidates.toArray(String[]::new);
    }
}