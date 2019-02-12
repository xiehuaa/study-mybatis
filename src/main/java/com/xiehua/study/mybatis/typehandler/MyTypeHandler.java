package com.xiehua.study.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自定义typeHandler，用来处理某个对应类型的字段
 * 需要在SqlSessionFactoryBean中注册并在mapper.xml中指定 typeHandler="com.xiehua.study.mybatis.typehandler.MyTypeHandler"
 *
 * @author xiehua
 * @date 2019/02/12
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
public class MyTypeHandler extends BaseTypeHandler<String> {
    public MyTypeHandler() {

    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter + "with myTypeHandler");
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getString(columnName) + "分";
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getString(columnIndex) + "分";
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getString(columnIndex) + "分";
    }
}
