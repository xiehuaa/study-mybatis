package com.xiehua.study.mybatis.plugins;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * 自定义插件，允许插件来拦截的方法调用如下
 * 1、Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
 * 2、ParameterHandler (getParameterObject, setParameters)
 * 3、ResultSetHandler (handleResultSets, handleOutputParameters)
 * 4、StatementHandler (prepare, parameterize, batch, update, query)
 * <p>
 * 指定拦截的类、方法、参数类型
 * 注册到SqlSessionFactoryBean中
 * sqlSessionFactoryBean.setPlugins(new Interceptor[]{new MyPlugin()});
 *
 * @author xiehua
 * @date 2019/02/12
 */
@Intercepts({@Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class MyPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        BoundSql boundSql = mappedStatement.getBoundSql(invocation.getArgs()[1]);
        System.out.println(String.format("plugin output sql = %s, param = %s", boundSql.getSql(), boundSql.getParameterObject()));
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
