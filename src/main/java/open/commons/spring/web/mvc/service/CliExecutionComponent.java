/*
 * Copyright 2021 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2021. 11. 16. 오후 1:24:58
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.mvc.service;

import java.io.IOException;
import java.util.Arrays;

import open.commons.Result;

/**
 * CLI(Command Line Interface) 관련 기능을 제공하는 콤포넌드.
 * 
 * @since 2021. 11. 16.
 * @version 0.4.0
 * @author parkjunhong77@gmail.com
 */
public abstract class CliExecutionComponent extends AbstractComponent {

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 16.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2021. 11. 16.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     */
    public CliExecutionComponent() {
    }

    /**
     * 작업 명령을 실행합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 16.        박준홍         최초 작성
     * </pre>
     *
     * @param cmdarray
     *            명령줄
     * @param job
     *            작업명
     * @return
     *
     * @since 2021. 11. 16.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     */
    protected Result<Boolean> executeNoWait(String[] cmdarray, String job) throws IOException {
        return execute(() -> {
            try {
                Runtime.getRuntime().exec(cmdarray);
                return Result.success(true);
            } catch (IOException e) {
                String errMsg = String.format("작업('%s')을/를 실패하였습니다. 명령어=%s, 원인=%s", job, Arrays.toString(cmdarray), e.getMessage());
                logger.error(errMsg, e);
                return Result.error(errMsg);
            }
        }, job);
    }
}
