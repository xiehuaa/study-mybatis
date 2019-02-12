package com.xiehua.study.mybatis.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiehua.study.mybatis.entity.Book;
import com.xiehua.study.mybatis.service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * This is Description
 *
 * @author xiehua
 * @date 2019/02/12
 */
@Controller
@RequestMapping(value = "/v1/book")
public class BookController {
    @Resource
    private BookService bookService;

    @ApiOperation(value = "列表", httpMethod = "GET", notes = "列表")
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@ApiParam("页码") @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                       @ApiParam("数据数量") @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Integer offset = (pageNo - 1) * pageSize;
        List<Book> bookList = bookService.list(offset, pageSize);
        return JSONObject.toJSONString(bookList);
    }
}
