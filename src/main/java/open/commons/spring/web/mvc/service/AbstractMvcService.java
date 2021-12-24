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
 * Date  : 2021. 12. 9. 오후 1:12:40
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.mvc.service;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;

import open.commons.Result;
import open.commons.annotation.Getter;
import open.commons.annotation.Setter;
import open.commons.function.QuadFunction;
import open.commons.function.TripleFunction;
import open.commons.utils.ObjectUtils;

/**
 * 
 * @since 2021. 12. 9.
 * @version 0.4.0
 * @author parkjunhong77@gmail.com
 */
public abstract class AbstractMvcService extends AbstractGenericService {

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 9.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2021. 12. 9.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     */
    public AbstractMvcService() {
    }

    /**
     * <code>DAO(Data Access Object)</code> 조회 결과가 중복인지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 9.        박준홍         최초 작성
     * </pre>
     *
     * @param <D>
     * @param resultDao
     * @param data
     * @return
     *
     * @since 2021. 12. 9.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     */
    protected <D> Result<Boolean> exists(Result<?> resultDao, D data) {
        if (resultDao.isError()) {
            return Result.error(resultDao.getMessage());
        }

        if (resultDao.getData() != null) {
            String errMsg = String.format("동일한 식별정보를 갖는 데이터가 이미 존재합니다. 식별정보=%s", data);
            logger.warn(errMsg);
            return Result.success(true).setMessage(errMsg);
        }
        return Result.success(false);
    }

    /**
     * {@link Pageable}에서 읽을 데이터 개수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 9.     박준홍         최초 작성
     * </pre>
     *
     * @param pageable
     * @return
     *
     * @since 2021. 12. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    protected int limit(Pageable pageable) {
        return pageable.getPageSize();
    }

    /**
     * {@link Pageable}에서 데이터 시작위치를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 9.     박준홍         최초 작성
     * </pre>
     *
     * @param pageable
     * @return
     *
     * @since 2021. 12. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    protected int offset(Pageable pageable) {
        return (pageable.getPageNumber() - 1) * pageable.getPageSize();
    }

    /**
     * {@link Pageable}에서 쿼리 조회 결과 정렬 기준을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 9.     박준홍         최초 작성
     * </pre>
     *
     * @param pageable
     * @return
     *
     * @since 2021. 12. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    protected String[] orderBy(Pageable pageable) {
        return pageable.getSort().stream() //
                .map(sort -> String.join(" ", sort.getProperty(), sort.getDirection().toString())) //
                .collect(Collectors.toList())//
                .toArray(new String[0]);
    }

    /**
     * DTO 데이터를 Entity로 변환하여 DB에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 24.		박준홍			최초 작성
     * </pre>
     *
     * @param <D>
     *            DTO 데이터 타입.
     * @param <E>
     *            Entity 데이터 타입.
     * @param data
     *            DB에 추가할 데이터.
     * @param entityType
     *            DB Table 데이터 타입.
     * @param transformer
     *            DTO -> Entity 변환 함수.
     * @param action
     *            DB Table 추가 함수.
     * @return
     *
     * @since 2021. 12. 24.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     */
    protected <D, E> Result<Integer> save(List<D> data, Class<E> entityType, Function<D, E> transformer, Function<List<E>, Result<Integer>> action) {
        return action.apply(convertMultiResult(data, entityType, transformer));
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
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
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
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    protected <E, D> Result<List<D>> selectMulti(SearchResultType type //
            , Function<String[], Result<List<E>>> funcAll //
            , TripleFunction<Integer, Integer, String[], Result<List<E>>> funcPagination, int offset, int limit //
            , String[] orderByArgs //
            , Class<D> dtoType, Function<E, D> converter) {
        return convertMultiResult(selectMulti(type, funcAll, funcPagination, offset, limit, orderByArgs), dtoType, converter);
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
     * @param param
     *            검색 파라미터
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
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    protected <E, P> Result<List<E>> selectMulti(SearchResultType type //
            , P param //
            , BiFunction<P, String[], Result<List<E>>> funcAll //
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
     * @param param
     *            검색 파라미터
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
     * @param dtoType
     *            DTO class
     * @param converter
     *            Entity -> DTO 변환 함수
     * @return
     *
     * @since 2021. 12. 6.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    protected <E, D, P> Result<List<D>> selectMulti(SearchResultType type //
            , P param //
            , BiFunction<P, String[], Result<List<E>>> funcAll //
            , QuadFunction<P, Integer, Integer, String[], Result<List<E>>> funcPagination, int offset, int limit //
            , String[] orderByArgs //
            , Class<D> dtoType, Function<E, D> converter) {
        return convertMultiResult(selectMulti(type, param, funcAll, funcPagination, offset, limit, orderByArgs), dtoType, converter);
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
     * @param param
     *            검색 파라미터
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
     * @since 2021. 12. 8.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    protected <E, P> Result<List<E>> selectMulti(SearchResultType type //
            , P param //
            , Function<P, Result<List<E>>> funcAll //
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
     * @param param
     *            검색 파라미터
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
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    protected <E, D, P> Result<List<D>> selectMulti(SearchResultType type //
            , P param //
            , Function<P, Result<List<E>>> funcAll //
            , TripleFunction<P, Integer, Integer, Result<List<E>>> funcPagination, int offset, int limit //
            , Class<D> dtoType, Function<E, D> converter) {
        return convertMultiResult(selectMulti(type, param, funcAll, funcPagination, offset, limit), dtoType, converter);
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
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
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
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    protected <E, D> Result<List<D>> selectMulti(SearchResultType type //
            , Supplier<Result<List<E>>> funcAll //
            , BiFunction<Integer, Integer, Result<List<E>>> funcPagination, int offset, int limit //
            , Class<D> dtoType, Function<E, D> converter) {
        return convertMultiResult(selectMulti(type, funcAll, funcPagination, offset, limit), dtoType, converter);
    }

    /**
     * {@link Getter}, {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 22.		박준홍			최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param srcObj
     *            입력 데이타
     * @param lookupSrcSuper
     *            입력 데이타 클래스 상위 인터페이스/클래스 확장 여부
     * @param targetType
     *            데이터를 전달받은 객체.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @return
     *
     * @since 2021. 12. 22.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     */
    protected static <S, T> T transform(S srcObj, boolean lookupSrcSupper, Class<T> targetType, boolean lookupTargetSupper) {
        return ObjectUtils.getTransformer(srcObj, lookupSrcSupper, targetType, lookupTargetSupper).apply(srcObj);
    }

    /**
     * {@link Getter}, {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 22.		박준홍			최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param srcObj
     *            입력 데이타
     * @param targetType
     *            데이터를 전달받은 객체.
     * @return
     *
     * @since 2021. 12. 22.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     */
    protected static <S, T> T transform(S srcObj, Class<T> targetType) {
        return transform(srcObj, true, targetType, true);
    }
}
