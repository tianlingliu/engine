package com.thinkbit.engine.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan ( {"com.thinkbit.engine.service"} )
//启用注解事务
@EnableTransactionManagement
public class TransactionConfig {
}
