package com.apollo.demo.spring.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wangwei on 2019/8/30 0030.
 */
@Configuration
//这个是稍微复杂一些的配置形式，指示Apollo注入FX.apollo和application.yml namespace的配置到Spring环境中
@EnableApolloConfig(value = {"TEST1.ww-public"})
public class AnotherAppConfig {
}
