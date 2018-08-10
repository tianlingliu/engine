package com.thinkbit.engine.config;

import com.thinkbit.common.rwdb.DynamicDataInterceptor;
import com.thinkbit.common.rwdb.DynamicDataSource;
import com.thinkbit.common.rwdb.DynamicTransactionManager;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;


@Configuration
@MapperScan ( basePackages = DataSourceConfig.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory" )
public class DataSourceConfig extends AbstractDataSourceConfig {
    static final String PACKAGE = "com.thinkbit.engine.repository";
    static final String MAPPER_LOCATION = "classpath*:mapper/*.xml";

    @Value ( "${tbex.master.datasource.type}" )
    private Class<? extends DataSource> masterDataSourceType;

    @Value ( "${tbex.slave.datasource.type}" )
    private Class<? extends DataSource> slaveDataSourceType;

    @Primary
    @ConfigurationProperties ( prefix = "tbex.master.datasource" )
    @Bean ( name = "masterDataSource" )
    public DataSource masterDataSource() {
        return DataSourceBuilder.create()
            .type(this.masterDataSourceType)
            .build();
    }

    @ConfigurationProperties ( prefix = "tbex.slave.datasource" )
    @Bean ( name = "slaveDataSource" )
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create()
            .type(this.slaveDataSourceType)
            .build();
    }

    @Bean ( name = "tbexDataSource" )
    public DataSource dataSource(
        @Qualifier ( "masterDataSource" ) final DataSource masterDataSource,
        @Qualifier ( "slaveDataSource" ) final DataSource slaveDataSource) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setWriteDataSource(masterDataSource);
        dynamicDataSource.setReadDataSource(slaveDataSource);
        return dynamicDataSource;
    }
    @Primary
    @Bean ( name = "transactionManager" )
    public DataSourceTransactionManager transactionManager(
        @Qualifier ( "tbexDataSource" ) final DataSource dataSource) {
        return new DynamicTransactionManager(dataSource);
    }

    @Primary
    @Bean ( name = "sqlSessionFactory" )
    public SqlSessionFactory sqlSessionFactory(
        @Qualifier ( "tbexDataSource" ) final DataSource dataSource)
        throws Exception {
        final SqlSessionFactoryBean sessionFactory = this.createSqlSessionFactoryBean(dataSource,
            MAPPER_LOCATION);
        sessionFactory.setPlugins(new Interceptor[] {new DynamicDataInterceptor()});
        return sessionFactory.getObject();
    }

    @Primary
    @Bean ( name = "sqlSessionTemplate" )
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier ( "sqlSessionFactory" ) final
    SqlSessionFactory sqlSessionFactory) throws Exception {
        return this.createSqlSessionTemplate(sqlSessionFactory);
    }

    @Primary
    @Bean ( name = "transactionTemplate" )
    public TransactionTemplate transactionTemplate(
        @Qualifier ( "transactionManager" ) final DataSourceTransactionManager transactionManager)
        throws Exception {
        return this.createTransactionTemplate(transactionManager);
    }
}
