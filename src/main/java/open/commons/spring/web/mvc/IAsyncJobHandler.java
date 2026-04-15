/*
 * Copyright 2020 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.mvc;

import java.util.concurrent.CompletableFuture; // [PATCH] CompletableFuture 임포트
import java.util.concurrent.Future;

import open.commons.core.Result;
import open.commons.core.concurrent.AsyncJobManager;

/**
 * <pre>
 * [개정이력]
 * 날짜        | 작성자    |    내용
 * ------------------------------------------
 * 2020. 11. 26.    parkjunhong77@gmail.com     최초 작성
 * 2021. 1. 13.     parkjunhong77@gmail.com     클래스 이관.
 * 2026. 4. 15.     parkjunhong77@gmail.com     Spring Boot:2.7.15 -> 4.0.3, Spring Framework: 5.3.29 -> 7.0.5.
 * </pre>
 * 
 * * @since 2020. 11. 26.
 * 
 * @version 4.0.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public interface IAsyncJobHandler {

    /**
     * 주어진 데이터를 가지고 {@link CompletableFuture} 객체를 생성한다. <br>
     * *
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 10.    parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 15.     parkjunhong77@gmail.com         AsyncResult 폐기에 따른 CompletableFuture.completedFuture 적용
     * </pre>
     *
     * @param <T>
     * @param value
     * 
     * @return 완료된 상태의 CompletableFuture 객체
     *
     * @since 2020. 11. 10.
     * @version 4.0.0
     */
    default <T> CompletableFuture<T> future(T value) {
        // [PATCH] AsyncResult 생성자 대체
        return CompletableFuture.completedFuture(value);
    }

    /**
     * * <br>
     * *
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2020. 11. 26.    parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 15.     parkjunhong77@gmail.com     AsyncResult 폐기에 따른 CompletableFuture.completedFuture 적용
     * </pre>
     *
     * @param <T>
     * @param value
     *            데이터
     * @param result
     *            결과
     * @param msg
     *            메시지
     * 
     * @return 완료된 상태의 CompletableFuture 객체
     *
     * @since 2020. 11. 26.
     * @version 4.0.0
     */
    default <T> CompletableFuture<Result<T>> futureAsResult(T value, boolean result, String msg) {
        // [PATCH] AsyncResult 생성자 대체
        return CompletableFuture.completedFuture(new Result<T>(value, result).setMessage(msg));
    }

    /**
     * 비동기 작업관리자 식별정보를 제공한다. <br>
     * *
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2021. 4. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <T>
     * @return
     *
     * @since 2021. 4. 23.
     * @version 0.3.0
     * @see #register(Object, Future)
     * @see #unregister(Object)
     */
    public Object getAsyncManagerHolder();

    /**
     * 비동기 작업을 관리자에게 등록한다. <br>
     * *
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2021. 1. 13.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <H>
     * @param <K>
     * @param <V>
     * @param holder
     *            context holder
     * @param key
     *            작업 식별정보
     * @param job
     *            작업
     * @return 작업 소유 객체
     *
     * @since 2021. 1. 13.
     * @version 0.3.0
     */
    default <H, K> H register(H holder, K key, Future<?> job) {
        AsyncJobManager<K, ?> manager = AsyncJobManager.Builder.getManager(holder);
        manager.register(key, job);
        return holder;
    }

    /**
     * 비동기 작업을 관리자에 등록한다. <br>
     * *
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2021. 4. 23.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <K>
     * @param key
     *            비동기 작업 식별정보
     * @param job
     *            작업
     * @return 작업 소유 객체
     *
     * @since 2021. 4. 23.
     * @version 0.3.0
     * @see #register(Object, Future)
     */
    default <K> Object register(K key, Future<?> job) {
        return register(getAsyncManagerHolder(), key, job);
    }

    /**
     * 비동기 작업등록을 해제한다. <br>
     * *
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 10.    parkjunhong77@gmail.com         최초 작성
     * 2023. 11. 02.    parkjunhong77@gmail.com         데이터 반환 추가
     * </pre>
     *
     * @param <H>
     * @param holder
     *            비동기 작업을 요청한 대상
     * @param key
     *            비동기 작업 식별정보
     * @return 기존에 등록된 작업.
     *
     * @since 2020. 11. 10.
     */
    default <H, K> Object unregister(H holder, K key) {
        // 비동기 작업 제거
        AsyncJobManager<K, ?> manager = AsyncJobManager.Builder.getManager(holder);
        if (manager != null) {
            return manager.unregister(key);
        } else {
            return null;
        }
    }

    /**
     * 비동기 작업등록을 해제한다. <br>
     * *
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2021. 4. 23.    parkjunhong77@gmail.com     최초 작성
     * 2023. 11. 02.    parkjunhong77@gmail.com         데이터 반환 추가
     * </pre>
     *
     * @param <K>
     * @param key
     *            비동기 작업 식별 정보
     * @return 기존에 등록된 작업.
     *
     * @since 2021. 4. 23.
     * @version 0.3.0
     * @see #unregister(Object)
     */
    default <K> Object unregister(K key) {
        return unregister(getAsyncManagerHolder(), key);
    }
}