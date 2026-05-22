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
 * Date  : 2026. 5. 22. 오전 11:10:31
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.http.converter.form;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;

import open.commons.spring.web.http.converter.json.AuthorizedObjectJsonHttpMessageConverter;

/**
 * '권한제어 데이터'를 포함한 {@link MediaType#APPLICATION_FORM_URLENCODED},
 * {@link MediaType#MULTIPART_FORM_DATA} 유형의 데이터를 변환하는 클래스입니다. <br>
 * 
 * <pre>
 * [개정이력]
 *     날짜        | 작성자                   |   내용
 * -----------------------------------------------------
 * 2026. 5. 22.     parkjunhong77@gmail.com     최초 작성
 * </pre>
 *
 * @since 2026. 5. 22.
 * @version 4.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 * @see AuthorizedObjectJsonHttpMessageConverter
 */
public class AuthorizedObjectFormHttpMessageConverter extends AllEncompassingFormHttpMessageConverter {

    /**
     * 
     *
     * @param customJsonConverter
     *            TODO
     * @since 2026. 5. 22.
     * @version 4.0.0
     */
    public AuthorizedObjectFormHttpMessageConverter(AuthorizedObjectJsonHttpMessageConverter customJsonConverter) {
        super();

        // #1. 부모 클래스가 기본으로 세팅해둔 partConverters 리스트를 가져옵니다.
        List<HttpMessageConverter<?>> partConverters = new ArrayList<>(getPartConverters());

        // #2. 기본 JacksonJsonHttpMessageConverter 를 'AuthorizedObjectJsonHttpMessageConverter'로 교체
        for (int i = 0; i < partConverters.size(); i++) {
            HttpMessageConverter<?> converter = partConverters.get(i);
            if (converter instanceof JacksonJsonHttpMessageConverter) {
                partConverters.set(i, customJsonConverter);
                break;
            }
        }

        // #3. 교체된 리스트를 다시 세팅합니다.
        setPartConverters(partConverters);
    }
}
