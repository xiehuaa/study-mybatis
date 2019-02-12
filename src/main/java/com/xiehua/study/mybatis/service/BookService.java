package com.xiehua.study.mybatis.service;

import com.xiehua.study.mybatis.entity.Book;

import java.util.List;

/**
 * This is Description
 *
 * @author xiehua
 * @date 2019/02/12
 */
public interface BookService {
    List<Book> list(Integer offset, Integer pageSize);
}
