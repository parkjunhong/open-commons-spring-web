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
 * Date  : 2020. 11. 26. 오후 5:41:31
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.spring.web.mvc.service;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.AsyncResult;

import open.commons.Result;
import open.commons.concurrent.AsyncJobManager;

/**
 * 
 * @since 2020. 11. 26.
 * @version _._._
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public interface IAsyncHandlerService {

    /**
     * 주어진 데이터를 가지고 {@link Future} 객체를 생성한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 10.        박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param value
     * @return
     *
     * @since 2020. 11. 10.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    default <T> Future<T> future(T value) {
        return new AsyncResult<>(value);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param value
     * @param result
     * @param msg
     * @return
     *
     * @since 2020. 11. 26.
     * @version _._._
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    default <T> Future<Result<T>> futureAsResult(T value, boolean result, String msg) {
        return new AsyncResult<>(new Result<T>(value, result).setMessage(msg));
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 10.        박준홍         최초 작성
     * </pre>
     *
     * @param <H>
     * @param holder
     * @param key
     *
     * @since 2020. 11. 10.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    default <H, K> void unregisterAsyncJob(H holder, K key) {
        // 비동기 작업 제거
        AsyncJobManager<K, ?> manager = AsyncJobManager.Builder.getManager(holder);
        if (manager != null) {
            manager.unregister(key);
        }
    }

}
