/*
 * Copyright 2026 Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"),
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
 * Date  : 2026. 4. 20. 오후 5:59:32
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.configure.concurrent.async;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.util.CustomizableThreadCreator;

/**
 * {@link SimpleAsyncTaskExecutor}의 실행 환경을 설정하기 위한 데이터 객체.
 *
 * @param daemon
 *            이 팩토리가 생성하는 스레드를 데몬 스레드로 설정할지 여부입니다. 데몬 스레드는 애플리케이션이 실행 중인 동안에만
 *            동작합니다.
 *            <p>
 *            기본값은 "false"이며, 보통 구체적인 팩토리들은 명시적인 취소 기능을 지원합니다. 따라서 애플리케이션이 종료될
 *            때, 실행 중인 Runnable 작업들은 기본적으로 실행을 끝마칠 때까지 유지됩니다.
 *            <p>
 *            애플리케이션 종료 시점에 활발히 실행 중인 {@link Runnable} 작업이 있더라도 스레드를 강제로 즉시
 *            종료하려면 "true"를 지정하십시오. (기본값: true)<br>
 *            (@see {@link CustomizableThreadCreator#setDaemon(boolean)})
 * @param threadGroupName
 *            스레드가 생성될 스레드 그룹의 이름을 지정합니다. (기본값: "virtual")<br>
 *            (@see
 *            {@link CustomizableThreadCreator#setThreadGroupName(String)})
 * @param threadNamePrefix
 *            새로 생성되는 스레드 이름에 사용할 접두사를 지정합니다. (기본값: "async-task-executor-")<br>
 *            (@see
 *            {@link CustomizableThreadCreator#setThreadNamePrefix(String)})
 * @param threadPriority
 *            이 팩토리가 생성하는 스레드에 부여할 우선순위를 설정합니다. (기본값: 5) <br>
 *            (@see {@link CustomizableThreadCreator#setThreadPriority(int)})
 * @param cancelRemainingTasksOnClose
 *            실행기를 닫을 때 남은 작업을 취소할지 여부를 지정합니다. 즉, {@link #close()} 호출 시 실행 중인 활성
 *            스레드에 인터럽트(중단)를 보낼지 결정합니다.
 *            <p>
 *            기본값은 {@code false}로, 활성 스레드를 별도로 추적하지 않거나
 *            {@link #setTaskTerminationTimeout taskTerminationTimeout}에 지정된 시간이
 *            지난 후에도 종료되지 않은 스레드만 인터럽트합니다. 종료 시 즉시 인터럽트를 발생시키려면 {@code true}로
 *            설정하십시오. 이는 설정된 종료 타임아웃 대기 여부와 결합하여 동작합니다. (기본값: false) <br>
 *            (@see
 *            {@link SimpleAsyncTaskExecutor#setCancelRemainingTasksOnClose(boolean)})
 * @param concurrencyLimit
 *            허용되는 최대 병렬 작업 실행 수를 설정합니다. 기본값인 -1은 동시성 제한이 전혀 없음을 의미합니다.
 *            <p>
 *            이 설정은 스레드 풀의 최대 풀 크기와 유사한 역할을 하며, 스레드 관리 시스템에 일시적인 과부하가 걸리는 것을
 *            방지합니다. 하지만 관리되는 큐가 있는 스레드 풀과 달리, 이 실행기는 동시성 제한에 도달하면 작업을 수용할 수 있을
 *            때까지 제출자(Submitter)를 대기(Blocking)시킵니다. 만약 이러한 대기 방식 대신 큐 기반의 처리를
 *            선호한다면 {@code ThreadPoolTaskExecutor} 사용을 고려하십시오. (기본값: -1)<br>
 *            (@see {@link SimpleAsyncTaskExecutor#setConcurrencyLimit(int)})
 * @param rejectTasksWhenLimitReached
 *            동시성 제한에 도달했을 때 새 작업을 거부하고 {@link TaskRejectedException}을 던질지 여부를
 *            지정합니다.
 *            <p>
 *            기본값은 {@code false}이며, 작업 제출이 수용될 때까지 호출자를 블로킹(대기)시킵니다. 대기 대신 즉시 거부
 *            처리를 원한다면 {@code true}로 전환하십시오. (기본값: false)<br>
 *            (@see
 *            {@link SimpleAsyncTaskExecutor#setRejectTasksWhenLimitReached(boolean)})
 * @param taskTerminationTimeout
 *            이 실행기를 닫을 때 작업 종료를 기다릴 타임아웃(밀리초 단위)을 지정합니다. 기본값은 0으로, 작업 종료를 전혀
 *            기다리지 않습니다.
 *            <p>
 *            타임아웃을 0보다 큰 값으로 설정하면 모든 제출된 작업을 작업 추적용 Runnable로 감싸게 되며, 작업 수가 많을
 *            경우 상당한 오버헤드가 발생할 수 있음에 유의하십시오. 하지만 실행 시간이 긴 작업이 포함된 적절한 수준의 작업 제출
 *            환경에서는 안전한 종료(Graceful Shutdown)를 위해 유용한 설정입니다.
 *            <p>
 *            {@code SimpleAsyncTaskExecutor}는 프레임워크의 조율된 라이프사이클 중단에 참여하지 않고, 오직
 *            {@link #close()} 호출 시에만 작업 종료를 기다립니다. (기본값: 0)<br>
 *            (@see
 *            {@link SimpleAsyncTaskExecutor#setTaskTerminationTimeout(long)})
 * @param virtualThreads
 *            이 실행기를 가상 스레드(Virtual Threads) 방식으로 전환합니다. Java 21 이상이 필요합니다.
 *            <p>
 *            기본값은 {@code false}이며 일반적인 플랫폼 스레드를 생성합니다. 가상 스레드를 생성하여 사용하려면 이
 *            플래그를 {@code true}로 설정하십시오. (기본값: true)<br>
 *            (@see {@link SimpleAsyncTaskExecutor#setVirtualThreads(boolean)})
 *
 * @since 2026. 4. 20.
 * @version 4.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public record VirtualTaskExecuctorProperties( //
        @DefaultValue("true") boolean daemon //
        , @DefaultValue("virtual") String threadGroupName //
        , @DefaultValue("async-task-executor-pool-") String threadNamePrefix //
        , @DefaultValue("5") int threadPriority //
        , @DefaultValue("false") boolean cancelRemainingTasksOnClose //
        , @DefaultValue("-1") int concurrencyLimit //
        , @DefaultValue("false") boolean rejectTasksWhenLimitReached //
        , @DefaultValue("0") long taskTerminationTimeout //
        , @DefaultValue("true") boolean virtualThreads) {
    /**
     * 
     * 속성값을 복사하여 새로운 레코드를 제공합니다.<br>
     * 
     * @param props
     *            복사할 원본 {@link VirtualTaskExecuctorProperties} 객체
     *
     * @since 2026. 4. 21.
     * @version 4.0.0
     */
    public static VirtualTaskExecuctorProperties copyOf(VirtualTaskExecuctorProperties props) {
        return new VirtualTaskExecuctorProperties(props.daemon(), props.threadGroupName(), props.threadNamePrefix(),
                props.threadPriority(), props.cancelRemainingTasksOnClose(), props.concurrencyLimit(),
                props.rejectTasksWhenLimitReached(), props.taskTerminationTimeout(), props.virtualThreads());
    }
}