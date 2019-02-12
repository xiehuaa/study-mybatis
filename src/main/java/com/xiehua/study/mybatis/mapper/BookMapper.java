package com.xiehua.study.mybatis.mapper;

import com.xiehua.study.mybatis.entity.Book;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * This is Description
 *
 * @author xiehua
 * @date 2019/02/12
 */
public interface BookMapper {
    /**
     * 获取列表
     *
     * @param offset   起始
     * @param pageSize 大小
     * @return Book集合
     */
    List<Book> list(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
}
