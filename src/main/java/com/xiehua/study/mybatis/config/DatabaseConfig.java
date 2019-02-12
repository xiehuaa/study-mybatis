package com.xiehua.study.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.StringUtils;
import com.xiehua.study.mybatis.plugins.MyPlugin;
import com.xiehua.study.mybatis.typehandler.MyTypeHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 数据库连接配置
 * <p>
 * 被Spring管理的类，实现接口EnvironmentAware，可以在工程启动时，获取到系统环境变量和application配置
 *
 * @author xiehua
 * @date 2019/02/12
 */
@Configuration
@EnableTransactionManagement
@MapperScan(value = "com.xiehua.study.mybatis.mapper")
public class DatabaseConfig implements EnvironmentAware {

    private Environment environment;

    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        // 用来获取application中数据库连接参数
        this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();

        String url = propertyResolver.getProperty("url");
        if (StringUtils.isEmpty(url)) {
            throw new ApplicationContextException("Database connection pool is not configured correctly");
        }
        druidDataSource.setUrl(url);
        druidDataSource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));
        druidDataSource.setUsername(propertyResolver.getProperty("username"));
        druidDataSource.setPassword(propertyResolver.getProperty("password"));

        return druidDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        // 注册自定义的typeHandler
        sqlSessionFactoryBean.setTypeHandlers(new TypeHandler[]{new MyTypeHandler()});
        // 自定义插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{new MyPlugin()});

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/**/*Mapper.xml"));

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        return sqlSessionFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
