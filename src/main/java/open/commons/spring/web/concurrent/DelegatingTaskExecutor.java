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
 * Date  : 2025. 8. 6. 오후 1:13:17
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.concurrent;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.task.AsyncTaskExecutor;

import open.commons.core.utils.StringUtils;
import open.commons.spring.web.mdc.MdcWrappedJob;

/**
 * <pre>
 * [개정이력]
 *      날짜       | 작성자                   |   내용
 * -----------------------------------------------------
 * 2025. 8. 6.      parkjunhong77@gmail.com     최초 작성
 * 2026. 4. 14.     parkjunhong77@gmail.com     Spring Boot:2.7.15 -> 4.0.3, Spring Framework: 5.3.29 -> 7.0.5. , ({@code AsyncListenableTaskExecutor, ListenableFuture}) 클래스가 폐기되고 다른 클래스로 통합({@link AsyncTaskExecutor}, {@link CompletableFuture}) 됨
 * </pre>
 * 
 * @since 2025. 8. 6.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class DelegatingTaskExecutor<S extends AsyncTaskExecutor> implements AsyncTaskExecutor {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /** <S> 기능을 제공하는 객체. */
    protected final S delegate;
    /** {@link Thread} 이름 뒤에 붙여서 식별정보로 활용 */
    protected final String symbol;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 8. 6.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param delegate
     * @param symbol
     *
     * @since 2025. 8. 6.
     * @version 0.8.0
     */
    public DelegatingTaskExecutor(S delegate, String symbol) {
        this.delegate = delegate;
        this.symbol = symbol;
    }

    /**
     *
     * @since 2025. 8. 6.
     * @version 0.8.0
     *
     * @see org.springframework.core.task.TaskExecutor#execute(java.lang.Runnable)
     */
    @Override
    public void execute(Runnable task) {
        this.delegate.submit(wrap(task));
    }

    /**
     * 현재 시점의 MDC 정보를 복제해서 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 8. 6.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param symbol
     * @return
     *
     * @since 2025. 8. 6.
     * @version 0.8.0
     */
    protected final Map<String, String> getCopyOfContextMap(String symbol) {
        Map<String, String> copiedMDC = MDC.getCopyOfContextMap();
        if (copiedMDC != null && !StringUtils.isNullOrEmptyString(symbol)) {
            copiedMDC.put(MdcWrappedJob.MDC_PROPERTY_THREAD_SYMBOL, symbol);
        }

        return copiedMDC;
    }

    /**
     *
     * @since 2025. 8. 6.
     * @version 0.8.0
     *
     * @see org.springframework.core.task.AsyncTaskExecutor#submit(java.util.concurrent.Callable)
     */
    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return this.delegate.submit(wrap(task));
    }

    /**
     *
     * @since 2025. 8. 6.
     * @version 0.8.0
     *
     * @see org.springframework.core.task.AsyncTaskExecutor#submit(java.lang.Runnable)
     */
    @Override
    public Future<?> submit(Runnable task) {
        return this.delegate.submit(wrap(task));
    }

    /**
     * 
     * {@inheritDoc}
     *
     * @since 2026. 4. 14.
     * @version 4.0.0
     *
     * @see org.springframework.core.task.AsyncTaskExecutor#submitCompletable(java.util.concurrent.Callable)
     */
    @Override
    public <T> CompletableFuture<T> submitCompletable(Callable<T> task) {
        return (CompletableFuture<T>) this.delegate.submitCompletable(wrap(task));
    }

    /**
     * 
     * {@inheritDoc}
     *
     * @since 2026. 4. 14.
     * @version 4.0.0
     *
     * @see org.springframework.core.task.AsyncTaskExecutor#submitCompletable(java.lang.Runnable)
     */
    @Override
    public CompletableFuture<Void> submitCompletable(Runnable task) {
        return (CompletableFuture<Void>) this.delegate.submitCompletable(wrap(task));
    }

    /**
     * 전달받은 {@link Callable} 객체가 {@link MDC} 정보를 사용할 수 있도록 감싼 {@link Callable} 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 6.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param callable
     * @return
     *
     * @since 2025. 8. 6.
     * @version 0.8.0
     */
    protected <T> Callable<T> wrap(Callable<T> callable) {
        return MdcWrappedJob.wrap(getCopyOfContextMap(this.symbol), callable);
    }

    /**
     * 전달받은 {@link Runnable} 객체가 {@link MDC} 정보를 사용할 수 있도록 감싼 {@link Runnable} 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 6.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param runnable
     * @return
     *
     * @since 2025. 8. 6.
     * @version 0.8.0
     */
    protected Runnable wrap(Runnable runnable) {
        return MdcWrappedJob.wrap(getCopyOfContextMap(this.symbol), runnable, false);
    }
}
