package com.apollo.demo.spring.refresh;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 使用@ConfigurationProperties注入apollo配置中心的属性。默认当服务端属性变化时，不会自动更新。
 * 如果需要在Apollo配置变化时自动更新注入的值，方案有：
 * ---方案一：RefreshScope+@ApolloConfigChangeListener+RefreshScope.refresh(String beanName)或RefreshScope.refreshAll()
 *     参见本地示例：SpringBootApolloRefreshConfig.class
 * ---方案二：@ApolloConfigChangeListener
 *          +ApplicationContext上下文的publishEvent（EnvironmentChangeEvent changeEvent）；
 *     参见本地示例：SpringBootApolloRefreshConfig2.class
 */
@ConfigurationProperties()
@Component("testBeanConfig")
//@RefreshScope   //方案一需要配合该注解.方案二不需要
public class TestBeanConfig {
    private int timeout;
    private int batch;

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    @Override
    public String toString() {
        return "TestBeanConfig{" +
                "timeout=" + timeout +
                ", batch=" + batch +
                '}';
    }
}
