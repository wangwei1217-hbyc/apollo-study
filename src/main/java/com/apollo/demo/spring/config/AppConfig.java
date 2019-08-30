package com.apollo.demo.spring.config;

import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wangwei on 2019/8/30 0030.
 */
@Configuration
//这个是最简单的配置形式，一般应用用这种形式就可以了，用来指示Apollo注入application namespace的配置到Spring环境中
@EnableApolloConfig
//@EnableApolloConfig(value = "application",order = 10)
public class AppConfig {
}
