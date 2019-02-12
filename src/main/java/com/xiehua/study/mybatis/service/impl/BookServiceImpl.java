package com.xiehua.study.mybatis.service.impl;

import com.xiehua.study.mybatis.entity.Book;
import com.xiehua.study.mybatis.mapper.BookMapper;
import com.xiehua.study.mybatis.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * This is Description
 *
 * @author xiehua
 * @date 2019/02/12
 */
@Service
public class BookServiceImpl implements BookService {
    private static Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Resource
    private BookMapper bookMapper;

    @Override
    public List<Book> list(Integer offset, Integer pageSize) {
        return bookMapper.list(offset, pageSize);
    }
}
