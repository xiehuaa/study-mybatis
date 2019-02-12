package com.xiehua.study.mybatis.entity;

import lombok.Data;

/**
 * This is Description
 *
 * @author xiehua
 * @date 2019/02/12
 */
@Data
public class Book {
    private Integer id;

    private String name;

    private String author;

    private String score;

    private String scoreCount;

    private String context;
}
