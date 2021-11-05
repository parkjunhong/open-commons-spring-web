/*
 * Copyright 2020 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2020. 11. 26. 오후 5:33:33
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.mvc.service;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Value;

import open.commons.function.TripleFunction;
import open.commons.spring.web.servlet.InternalServerException;
import open.commons.ssh.SshConnection;
import open.commons.utils.MapUtils;

import com.jcraft.jsch.Session;

/**
 * SSH기반의 통신 기능을 제공한다.
 * 
 * @since 2020. 11. 26.
 * @version _._._
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class AbstractSshService extends AbstractGenericService {

    /**
     * {@link SshConnection} 식별정보 제공 함수.
     * <ul>
     * <li>user: username
     * <li>host: Host IP or Domain Name
     * <li>port: Port
     * </ul>
     */
    protected static final TripleFunction<String, String, Integer, String> SSH_CONNECTION_KEY_GEN = (user, host, port) -> {
        return String.join(":", user, host, String.valueOf(port));
    };

    /** {@link Session} 생성을 위한 Mutex 객체 */
    protected final ReentrantLock mutexSession = new ReentrantLock();

    /**
     * 접속 서버별로 관리되는 {@link Session} 정보.
     * <ul>
     * <li>key: sessin key
     * <li>value: {@link Session} instance
     * </ul>
     */
    protected ConcurrentSkipListMap<String, SshConnection> sessions = new ConcurrentSkipListMap<>();

    /**
     * SSH 접속대기 제한시간. 단위: ms
     */
    @Value("${application.ssh.connect-timeout}")
    protected int connectTimeout;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 26.        박준홍         최초 작성
     * </pre>
     *
     * @since 2020. 11. 26.
     */
    public AbstractSshService() {
    }

    protected SshConnection getConnection(String username, String password, String host, int port) {
        ReentrantLock lock = this.mutexSession;
        try {
            lock.lock();
            Supplier<SshConnection> supplier = () -> new SshConnection(username, password, host, port);
            return MapUtils.getOrDefault(this.sessions, SSH_CONNECTION_KEY_GEN.apply(username, host, port), supplier, true);
        } catch (Exception e) {
            logger.error("SSH 세션 생성 도중 에러가 발생하였습니다. username={}, host={}, port={}, 원인={}", username, host, port, e.getMessage(), e);
            throw new InternalServerException(e);
        } finally {
            lock.unlock();
        }
    }
}
