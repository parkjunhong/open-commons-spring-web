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
 * Date  : 2025. 8. 3. 오후 3:49:42
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.concurrent;

import java.lang.annotation.Annotation;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledFuture;

import org.jspecify.annotations.Nullable;
import org.slf4j.MDC;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.ScheduledMethodRunnable;

import open.commons.core.utils.StringUtils;
import open.commons.spring.web.aspect.LogFeatureAspect;
import open.commons.spring.web.log.LogFeature;
import open.commons.spring.web.mdc.MdcWrappedJob;

/**
 * <pre>
 * [개정이력]
 *      날짜       | 작성자                   |   내용
 * -----------------------------------------------------
 * 2025. 8. 3.      parkjunhong77@gmail.com     최초 작성
 * 2026. 4. 14.     parkjunhong77@gmail.com     Spring Boot:2.7.15 -> 4.0.3, Spring Framework: 5.3.29 -> 7.0.5. , ({@code AsyncListenableTaskExecutor, ListenableFuture}) 클래스가 폐기되고 다른 클래스로 통합({@link AsyncTaskExecutor}, {@link CompletableFuture}) 됨
 * </pre>
 * 
 * @since 2025. 8. 3.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class DelegatingTaskScheduler<S extends TaskScheduler & AsyncTaskExecutor> extends DelegatingTaskExecutor<S>
        implements TaskScheduler {

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 8. 3.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     * 
     * @param delegate
     *            {@link TaskScheduler} 기능을 제공하는 객체.
     * @param symbol
     *            {@link Thread} 이름 뒤에 붙여서 식별정보로 활용
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     */
    public DelegatingTaskScheduler(S delegate, @Nullable String symbol) {
        super(delegate, symbol);
    }

    /**
     * @since 2025. 8. 3.
     * @version 0.8.0
     *
     * @see org.springframework.scheduling.TaskScheduler#schedule(java.lang.Runnable,
     *      java.util.Date)
     * 
     * @deprecated {@link #schedule(Runnable, Instant)}를 사용하기 바랍니다.
     * 
     */
    @Override
    @Deprecated(since = "4.0.0", forRemoval = true)
    public ScheduledFuture<?> schedule(Runnable task, Date startTime) {
        return this.delegate.schedule(wrap(task), startTime.toInstant());
    }

    /**
     * {@inheritDoc}
     *
     * @since 2026. 4. 14.
     * @version 4.0.0
     *
     * @see org.springframework.scheduling.TaskScheduler#schedule(java.lang.Runnable,
     *      java.time.Instant)
     */
    @Override
    public ScheduledFuture<?> schedule(Runnable task, Instant startTime) {
        return this.delegate.schedule(wrap(task), startTime);
    }

    /**
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     *
     * @see org.springframework.scheduling.TaskScheduler#schedule(java.lang.Runnable,
     *      org.springframework.scheduling.Trigger)
     */
    @Override
    public ScheduledFuture<?> schedule(Runnable task, Trigger trigger) {
        return this.delegate.schedule(wrap(task), trigger);
    }

    /**
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     *
     * @see org.springframework.scheduling.TaskScheduler#scheduleAtFixedRate(java.lang.Runnable,
     *      java.util.Date, long)
     * 
     * @deprecated {@link #scheduleAtFixedRate(Runnable, Instant, Duration)}를
     *             사용하기 바랍니다.
     */
    @Override
    @Deprecated(since = "4.0.0", forRemoval = true)
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, Date startTime, long period) {
        return this.delegate.scheduleAtFixedRate(wrap(task), startTime.toInstant(), Duration.ofMillis(period));
    }

    /**
     * {@inheritDoc}
     *
     * @since 2026. 4. 14.
     * @version 4.0.0
     *
     * @see org.springframework.scheduling.TaskScheduler#scheduleAtFixedRate(java.lang.Runnable,
     *      java.time.Duration)
     */
    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, Duration period) {
        return this.delegate.scheduleAtFixedRate(wrap(task), period);
    }

    /**
     * {@inheritDoc}
     *
     * @since 2026. 4. 14.
     * @version 4.0.0
     *
     * @see org.springframework.scheduling.TaskScheduler#scheduleAtFixedRate(java.lang.Runnable,
     *      java.time.Instant, java.time.Duration)
     */
    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, Instant startTime, Duration period) {
        return this.delegate.scheduleAtFixedRate(wrap(task), startTime, period);
    }

    /**
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     *
     * @see org.springframework.scheduling.TaskScheduler#scheduleAtFixedRate(java.lang.Runnable,
     *      long)
     * 
     * @deprecated {@link #scheduleAtFixedRate(Runnable, Duration)}를 사용하기 바랍니다.
     */
    @Override
    @Deprecated(since = "4.0.0", forRemoval = true)
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long period) {
        return this.delegate.scheduleAtFixedRate(wrap(task), Duration.ofMillis(period));
    }

    /**
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     *
     * @see org.springframework.scheduling.TaskScheduler#scheduleWithFixedDelay(java.lang.Runnable,
     *      java.util.Date, long)
     * 
     * @deprecated {@link #scheduleWithFixedDelay(Runnable, Instant, Duration)}를
     *             사용하기 바랍니다.
     */
    @Override
    @Deprecated(since = "4.0.0", forRemoval = true)
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, Date startTime, long delay) {
        return this.delegate.scheduleWithFixedDelay(wrap(task), startTime.toInstant(), Duration.ofMillis(delay));
    }

    /**
     * {@inheritDoc}
     *
     * @since 2026. 4. 14.
     * @version 4.0.0
     *
     * @see org.springframework.scheduling.TaskScheduler#scheduleWithFixedDelay(java.lang.Runnable,
     *      java.time.Duration)
     */
    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, Duration delay) {
        return this.delegate.scheduleWithFixedDelay(wrap(task), delay);
    }

    /**
     * {@inheritDoc}
     *
     * @since 2026. 4. 14.
     * @version 4.0.0
     *
     * @see org.springframework.scheduling.TaskScheduler#scheduleWithFixedDelay(java.lang.Runnable,
     *      java.time.Instant, java.time.Duration)
     */
    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, Instant startTime, Duration delay) {
        return this.delegate.scheduleWithFixedDelay(wrap(task), startTime, delay);
    }

    /**
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     *
     * @see org.springframework.scheduling.TaskScheduler#scheduleWithFixedDelay(java.lang.Runnable,
     *      long)
     * 
     * @deprecated {@link #scheduleWithFixedDelay(Runnable, Duration)}를 사용하기
     *             바랍니다.
     */
    @Override
    @Deprecated(since = "4.0.0", forRemoval = true)
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, long delay) {
        return this.delegate.scheduleWithFixedDelay(wrap(task), Duration.ofMillis(delay));
    }

    /**
     * {@link LogFeature#thread()} 설정된 값을 {@link MDC} 복제정보체 추가하여 전달합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *     날짜        | 작성자                   |   내용
     * -----------------------------------------------------
     * 2025. 8. 3.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param runnable
     * @return
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     */
    protected Runnable wrap(Runnable runnable) {
        if (!isAnnotatedByLogFeature(runnable, LogFeature.class)) {
            return runnable;
        }

        String tn = findSpecifiedThreadName(runnable);
        Map<String, String> executorMDC = MdcWrappedJob.getCopyOfContextMap(symbol);
        if (executorMDC == null) {
            executorMDC = new HashMap<>();
        }
        executorMDC.put(LogFeatureAspect.FORWARDED_THREAD_NAME, tn);
        Runnable wrapped = MdcWrappedJob.wrap(executorMDC, runnable, true);

        logger.trace("Wrap {} to 'executor' MDC.", runnable);

        return wrapped;
    }

    protected static String findSpecifiedThreadName(Runnable runnable) {
        // ScheduledMethodRunnable
        if (runnable instanceof ScheduledMethodRunnable) {
            ScheduledMethodRunnable r = (ScheduledMethodRunnable) runnable;
            LogFeature anno = AnnotationUtils.findAnnotation(r.getMethod(), LogFeature.class);
            if (anno != null) {
                return StringUtils.isNullOrEmptyString(anno.thread()) ? null : anno.thread().trim();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    protected static <A extends Annotation> boolean isAnnotatedByLogFeature(Runnable runnable, Class<A> annotation) {
        // ScheduledMethodRunnable
        if (runnable instanceof ScheduledMethodRunnable) {
            ScheduledMethodRunnable r = (ScheduledMethodRunnable) runnable;
            A anno = AnnotationUtils.findAnnotation(r.getMethod(), annotation);
            return anno != null;
        } else {
            return false;
        }
    }

}
