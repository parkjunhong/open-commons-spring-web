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
 * Date  : 2026. 4. 21. 오전 9:51:24
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.configure.concurrent.executor;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * {@link ThreadPoolExecutor}의 실행 환경을 설정하기 위한 프로퍼티 레코드.
 *
 * <pre>
 * [개정이력]
 * 날짜        | 작성자                   |   내용
 * -----------------------------------------------------
 * 2025. 8. 13.    parkjunhong77@gmail.com     최초 작성 (class ThreadPoolExecutorConfig)
 * 2026. 4. 21.    parkjunhong77@gmail.com     Record 기반의 ThreadPoolExecutorProperties로 전환
 * </pre>
 *
 * @param corePoolSize
 *            풀에 유지할 기본 스레드 수입니다. 유휴 상태여도 유지되지만, allowCoreThreadTimeOut이 설정된 경우 예외입니다. (기본값: 8)<br>
 *            (@see {@link ThreadPoolExecutor#setCorePoolSize(int)})
 * @param maximumPoolSize
 *            풀에 허용되는 최대 스레드 수입니다. (기본값: 1024)<br>
 *            (@see {@link ThreadPoolExecutor#setMaximumPoolSize(int)})
 * @param keepAliveTime
 *            코어 스레드 수를 초과하여 생성된 유휴 스레드가 종료 전까지 새 작업을 기다리는 최대 시간입니다. (기본값: 60)<br>
 *            (@see {@link ThreadPoolExecutor#setKeepAliveTime(long, TimeUnit)})
 * @param timeUnit
 *            keepAliveTime 값의 시간 단위입니다. (기본값: "SECOND")<br>
 *            (@see {@link ThreadPoolExecutor#setKeepAliveTime(long, TimeUnit)})
 * @param allowCoreThreadTimeOut
 *            기본값은 false이며 코어 스레드는 유휴 상태여도 유지됩니다. true이면 코어 스레드도 keepAliveTime을 적용받아 유휴 시 종료됩니다.
 *            (기본값: false)<br>
 *            (@see {@link ThreadPoolExecutor#allowCoreThreadTimeOut(boolean)})
 * 
 * @since 2026. 4. 21.
 * @version 4.0.0
 * @author parkjunhong77@gmail.com
 */
public record ExecutorProperties( //
        @DefaultValue("8") int corePoolSize //
        , @DefaultValue("1024") int maximumPoolSize //
        , @DefaultValue("60") long keepAliveTime //
        , @DefaultValue("SECONDS") TimeUnit timeUnit //
        , @DefaultValue("false") boolean allowCoreThreadTimeOut //
) {
    /**
     * 속성값을 복사하여 새로운 레코드를 생성합니다.<br>
     *
     * @param config
     *            복사할 원본 {@link ExecutorProperties} 객체
     *
     * @since 2026. 4. 21.
     * @version 4.0.0
     */
    public static ExecutorProperties copyOf(ExecutorProperties props) {
        return new ExecutorProperties(props.corePoolSize(), props.maximumPoolSize(), props.keepAliveTime(),
                props.timeUnit(), props.allowCoreThreadTimeOut());
    }
}