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
 * Date  : 2020. 1. 21. 오후 1:41:35
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.mvc.service;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import open.commons.Result;
import open.commons.function.QuadFunction;
import open.commons.function.TripleFunction;

/**
 * 
 * @since 2020. 1. 21.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public abstract class AbstractGenericService extends AbstractComponent implements InitializingBean, DisposableBean, IConvertingService {

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
    public AbstractGenericService() {
    }

    /**
     * @since 2020. 8. 29.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
    }

    /**
     * @since 2020. 8. 29.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see org.springframework.beans.factory.DisposableBean#destroy()
     */
    @Override
    public void destroy() throws Exception {
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 9.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param <P>
     *            파라미터 타입
     * @param type
     *            검색 유형.
     * @param funcAll
     *            전체 검색 함수
     * @param param
     *            검색 파라미터
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @param orderByArgs
     *            정렬 기준.<br>
     *            <b>데이터 정의</b><br>
     *            <li>포맷: {column} {direction}<br>
     *            <li>예: name asc
     * @return
     *
     * @since 2021. 12. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    protected <E, P> Result<List<E>> selectMulti(SearchResultType type //
            , BiFunction<P, String[], Result<List<E>>> funcAll //
            , P param //
            , QuadFunction<P, Integer, Integer, String[], Result<List<E>>> funcPagination, int offset, int limit //
            , String... orderByArgs) {
        switch (type) {
            case ALL:
                return funcAll.apply(param, orderByArgs);
            case PAGINATION:
                return funcPagination.apply(param, offset, limit, orderByArgs);
            default:
                throw new UnsupportedOperationException(String.format("지원하지 않음. type=%s", type.get()));
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 6.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param <P>
     *            파라미터 타입
     * @param type
     *            검색 유형.
     * @param funcAll
     *            전체 검색 함수
     * @param param
     *            검색 파라미터
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @param orderByArgs
     *            정렬 기준.<br>
     *            <b>데이터 정의</b><br>
     *            <li>포맷: {column} {direction}<br>
     *            <li>예: name asc
     * @param dtoType
     *            DTO class
     * @param converter
     *            Entity -> DTO 변환 함수
     * @return
     *
     * @since 2021. 12. 6.
     * @version 0.4.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    protected <E, D, P> Result<List<D>> selectMulti(SearchResultType type //
            , BiFunction<P, String[], Result<List<E>>> funcAll //
            , P param //
            , QuadFunction<P, Integer, Integer, String[], Result<List<E>>> funcPagination, int offset, int limit //
            , String[] orderByArgs //
            , Class<D> dtoType, Function<E, D> converter) {
        return convertMultiResult(selectMulti(type, funcAll, param, funcPagination, offset, limit, orderByArgs), dtoType, converter);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 8.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param <P>
     *            파라미터 타입
     * @param type
     *            검색 유형.
     * @param funcAll
     *            전체 검색 함수
     * @param param
     *            검색 파라미터
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @return
     *
     * @since 2021. 12. 8.
     * @version 0.4.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    protected <E, P> Result<List<E>> selectMulti(SearchResultType type //
            , Function<P, Result<List<E>>> funcAll //
            , P param //
            , TripleFunction<P, Integer, Integer, Result<List<E>>> funcPagination, int offset, int limit //
    ) {
        switch (type) {
            case ALL:
                return funcAll.apply(param);
            case PAGINATION:
                return funcPagination.apply(param, offset, limit);
            default:
                throw new UnsupportedOperationException(String.format("지원하지 않음. type=%s", type.get()));
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 6.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param <P>
     *            파라미터 타입
     * @param type
     *            검색 유형.
     * @param funcAll
     *            전체 검색 함수
     * @param param
     *            검색 파라미터
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @param dtoType
     *            DTO class
     * @param converter
     *            Entity -> DTO 변환 함수
     * @return
     *
     * @since 2021. 12. 6.
     * @version 0.4.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    protected <E, D, P> Result<List<D>> selectMulti(SearchResultType type //
            , Function<P, Result<List<E>>> funcAll //
            , P param //
            , TripleFunction<P, Integer, Integer, Result<List<E>>> funcPagination, int offset, int limit //
            , Class<D> dtoType, Function<E, D> converter) {
        return convertMultiResult(selectMulti(type, funcAll, param, funcPagination, offset, limit), dtoType, converter);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 9.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param type
     *            검색 유형.
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @param orderByArgs
     *            정렬 기준.<br>
     *            <b>데이터 정의</b><br>
     *            <li>포맷: {column} {direction}<br>
     *            <li>예: name asc
     * @return
     *
     * @since 2021. 12. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    protected <E> Result<List<E>> selectMulti(SearchResultType type //
            , Function<String[], Result<List<E>>> funcAll //
            , TripleFunction<Integer, Integer, String[], Result<List<E>>> funcPagination, int offset, int limit //
            , String... orderByArgs) {
        switch (type) {
            case ALL:
                return funcAll.apply(orderByArgs);
            case PAGINATION:
                return funcPagination.apply(offset, limit, orderByArgs);
            default:
                throw new UnsupportedOperationException(String.format("지원하지 않음. type=%s", type.get()));
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 6.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param type
     *            검색 유형.
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @return
     *
     * @since 2021. 12. 6.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com) 정렬 기준.<br>
     *         <b>데이터 정의</b><br>
     *         <li>포맷: {column} {direction}<br>
     *         <li>예: name asc
     */
    protected <E> Result<List<E>> selectMulti(SearchResultType type //
            , Supplier<Result<List<E>>> funcAll //
            , BiFunction<Integer, Integer, Result<List<E>>> funcPagination, int offset, int limit //
    ) {
        switch (type) {
            case ALL:
                return funcAll.get();
            case PAGINATION:
                return funcPagination.apply(offset, limit);
            default:
                throw new UnsupportedOperationException(String.format("지원하지 않음. type=%s", type.get()));
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 6.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param type
     *            검색 유형.
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @param dtoType
     *            DTO class
     * @param converter
     *            Entity -> DTO 변환 함수
     * @return
     *
     * @since 2021. 12. 6.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    protected <E, D> Result<List<D>> selectMulti(SearchResultType type //
            , Supplier<Result<List<E>>> funcAll //
            , BiFunction<Integer, Integer, Result<List<E>>> funcPagination, int offset, int limit //
            , Class<D> dtoType, Function<E, D> converter) {
        return convertMultiResult(selectMulti(type, funcAll, funcPagination, offset, limit), dtoType, converter);
    }
}
