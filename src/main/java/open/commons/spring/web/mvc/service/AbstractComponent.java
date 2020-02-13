/*
 * Copyright 2020 Park Jun-Hong_(parkjunhong77/google/com)
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
 * Date  : 2020. 1. 21. 오후 1:20:51
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.spring.web.mvc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import open.commons.spring.web.config.ResourceConfiguration;

/**
 * 
 * @since 2020. 1. 21.
 * @version
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class AbstractComponent {

    /** Logger */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /** ThreadPool Executor */
    @Autowired
    @Qualifier(ResourceConfiguration.BEAN_QUALIFIER_THREAD_POOL)
    protected ThreadPoolTaskExecutor threadpool;

    /** 공통 설정 정보 */
    @Autowired
    protected Environment env;

    /** Context */
    @Autowired
    protected ApplicationContext context;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 21.		박준홍			최초 작성
     * </pre>
     *
     * @since 2020. 1. 21.
     * @version
     */
    public AbstractComponent() {
    }
}
