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

import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import open.commons.Result;
import open.commons.spring.web.config.ResourceConfiguration;
import open.commons.test.StopWatch;

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

    /**
     * 에러 결과 객체를 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 8. 24.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param msg
     *            에러 메시지
     * @return
     *
     * @since 2021. 8. 24.
     * @version 0.3.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public final <T> Result<T> error(String msg) {
        return Result.error(msg);
    }

    /**
     * 에러 결과 객체를 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 8. 24.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param format
     *            에러 메시지 포맷
     * @param args
     *            메시지 데이터
     * @return
     *
     * @since 2021. 8. 24.
     * @version 0.3.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public final <T> Result<T> error(String format, Object... args) {
        return Result.error(format, args);
    }

    /**
     * 에러 결과 객체를 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 8. 24.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param data
     *            데이터
     * @param data
     *            메시지
     * @return
     *
     * @since 2021. 8. 24.
     * @version 0.3.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    @SuppressWarnings("unchecked")
    public final <T> Result<T> error(T data, String msg) {
        return (Result<T>) Result.error(msg).setData(data);
    }

    /**
     * 에러 결과 객체를 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 8. 24.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param data
     *            데이터
     * @param format
     *            메시지 포맷
     * @param args
     *            메시지 데이터
     * @return
     *
     * @since 2021. 8. 24.
     * @version 0.3.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    @SuppressWarnings("unchecked")
    public final <T> Result<T> error(T data, String format, Object... args) {
        return (Result<T>) Result.error(format, args).setData(data);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 10. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     *            반환 데이터 타입.
     * @param action
     *            실행할 작업
     * @param job
     *            작업명. (로그를 위해서)
     * @return
     *
     * @since 2021. 10. 4.
     * @version 0.4.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public final <T> T execute(Supplier<T> action, String job) {
        StopWatch watch = new StopWatch();
        watch.start();
        try {
            return action.get();
        } finally {
            watch.stop();
            logger.info("[{} 완료] elapsed={}", job, watch.getAsPretty());
        }
    }

    /**
     * 성공 결과 객체를 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 8. 24.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param data
     *            데이터
     * @param message
     *            메시지
     * @return
     *
     * @since 2021. 8. 24.
     * @version 0.3.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public final <T> Result<T> success(T data, String message) {
        return Result.success(data).setMessage(message);
    }

    /**
     * 성공 결과 객체를 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 8. 24.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param data
     *            데이터
     * @param format
     *            메시지 포맷
     * @param args
     *            메시지 데이터
     * @return
     *
     * @since 2021. 8. 24.
     * @version 0.3.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public final <T> Result<T> success(T data, String format, Object... args) {
        return Result.success(data).setMessage(format, args);
    }
}
