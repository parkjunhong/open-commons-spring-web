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

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Value;

import open.commons.core.Result;
import open.commons.core.function.TripleFunction;
import open.commons.core.utils.IOUtils;
import open.commons.core.utils.MapUtils;
import open.commons.spring.web.servlet.InternalServerException;
import open.commons.ssh.SshConnection;
import open.commons.ssh.file.FileTransfer;

import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.Session;

/**
 * SSH기반의 통신 기능을 제공한다.
 * 
 * @since 2020. 11. 26.
 * @version 0.4.0
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
    @Value("${application.ssh.connect-timeout:10000}")
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

    /**
     * 파일을 다운로드 합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 11. 20.		박준홍			최초 작성
     * </pre>
     * 
     * @param host
     *            sftp host
     * @param port
     *            sftp port
     * @param username
     *            sftp 접속 계정
     * @param password
     *            sftp 접속 비밀번호
     * @param srcFile
     *            작업 대상 파일경로 (절대경로)
     * @param dstFile
     *            작업 결과 파일경로 (절대경로)
     *
     * @return
     *
     * @since 2023. 11. 20.
     * @version 0.7.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected Result<Boolean> download(String host, int port, String username, String password, String srcFile, String dstFile) {
        try ( //
                FileTransfer sftp = new FileTransfer(getConnection(username, password, host, port));
        //
        ) {
            // #1. 대상파일 존재 확인
            Result<List<LsEntry>> resultList = sftp.list(srcFile);
            if (resultList.isError()) {
                // 연결실패 등의 에러
                String errMsg = String.format("[%s:%s] %s => %s", host, port, resultList.getMessage(), srcFile);
                return Result.error(errMsg);
            } else if (resultList.getData().isEmpty()) {
                // 파일이 없는 경우
                return Result.success(false).setMessage("[%s:%s] No File. => %s", host, port, srcFile);
            }
            IOUtils.close(sftp);
            return transfer(host, port, username, password, srcFile, dstFile, false);
        } catch (Exception e) {
            String errMsg = String.format("'%s' 파일 다운로드를 실패하였습니다. 원인=%s", srcFile, e.getMessage());
            logger.error("[{}] {}", errMsg, e);
            return Result.error(errMsg);
        }
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

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 11. 20.		박준홍			최초 작성
     * </pre>
     * 
     * @param host
     *            sftp host
     * @param port
     *            sftp port
     * @param username
     *            sftp 접속 계정
     * @param password
     *            sftp 접속 비밀번호
     * @param srcPath
     *            작업 대상 파일경로 (절대경로)
     * @param dstPath
     *            작업 결과 파일경로 (절대경로)
     * @param isUpload
     *            업/다운로드.
     *
     * @return
     *
     * @since 2023. 11. 20.
     * @version 0.7.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected Result<Boolean> transfer(String host, int port, String username, String password, String srcPath, String dstPath, boolean isUpload) {
        return execute(() -> {
            Result<Boolean> resultXfer = null;
            try ( //
                    FileTransfer sftp = new FileTransfer(getConnection(username, password, host, port));
            //
            ) {
                // #2. 존재하는 경우 다운로드
                resultXfer = isUpload //
                        ? sftp.upload(srcPath, dstPath) //
                        : sftp.download(srcPath, dstPath);
                return resultXfer;
            } catch (Exception e) {
                String errMsg = String.format("파일 %s를 실패하였습니다. 원인=%s", isUpload ? "업로드" : "다운로드", e.getMessage());
                logger.error("{}", errMsg, e);
                return Result.error(errMsg);
            } finally {
                logger.debug("파일 {}를 {} 하였습니다. Source={}, Destination={}", isUpload ? "업로드" : "다운로드", resultXfer.isSuccess() ? "성공" : "실패", srcPath, dstPath, resultXfer.getData());
            }
        }, "파일 " + (isUpload ? "업로드" : "다운로드"));
    }

    /**
     * 파일을 업로드 합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 11. 20.		박준홍			최초 작성
     * </pre>
     * 
     * @param host
     *            sftp host
     * @param port
     *            sftp port
     * @param username
     *            sftp 접속 계정
     * @param password
     *            sftp 접속 비밀번호
     * @param srcFile
     *            작업 대상 파일경로 (절대경로)
     * @param dstFile
     *            작업 결과 파일경로 (절대경로)
     *
     * @return
     *
     * @since 2023. 11. 20.
     * @version 0.7.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected Result<Boolean> upload(String host, int port, String username, String password, String srcFile, String dstFile) {
        // #1. 대상파일 존재 확인
        if (!Files.exists(Paths.get(srcFile))) {
            // 파일이 없는 경우
            return Result.success(false);
        }
        return transfer(host, port, username, password, srcFile, dstFile, true);
    }
}
