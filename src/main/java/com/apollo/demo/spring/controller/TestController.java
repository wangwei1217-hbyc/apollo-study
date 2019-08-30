package com.apollo.demo.spring.controller;

import com.alibaba.fastjson.JSONArray;
import com.apollo.demo.spring.bean.AnnotatedBean;
import com.apollo.demo.spring.refresh.TestBeanConfig;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloJsonValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * Created by wangwei on 2019/8/30 0030.
 */
@RestController
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(AnnotatedBean.class);
    @Value("${timeout:200}")
    private int timeout;
    private int batch;
    private List<JsonBean> jsonBeans;
    @Value("${server.port:0}")
    private int port;

    @Autowired
    private TestBeanConfig testBeanConfig;

    @ApolloConfig(value = "application")
    private Config config;

    /**
     * ApolloJsonValue annotated on fields example, the default value is specified as empty list - []
     * <br />
     * jsonBeanProperty=[{"someString":"hello","someInt":100},{"someString":"world!","someInt":200}]
     */
    @ApolloJsonValue("${jsonBeanProperty:[]}")
    private List<JsonBean> anotherJsonBean;

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Value("${batch:100}")
    public void setBatch(int batch) {
        logger.info("updating batch, old value: {}, new value: {}", this.batch, batch);
        this.batch = batch;
    }

    /**
     * ApolloJsonValue annotated on methods example, the default value is specified as empty list - []
     * <br />
     * jsonBeanProperty=[{"someString":"hello","someInt":100},{"someString":"world!","someInt":200}]
     */
    @ApolloJsonValue("${jsonBeanProperty:[]}")
    public void setJsonBeans(List<JsonBean> jsonBeans) {
        this.jsonBeans = jsonBeans;
    }

    @RequestMapping("/config/data")
    public String configData(){
        return toString();
    }

    @RequestMapping("/config/refresh")
    public String configRefresh(){
        return testBeanConfig.toString();
    }

    @RequestMapping("/config/api")
    public Object configApi(){
        Set<String> propertyNames = config.getPropertyNames();
        JSONArray jsonArray = new JSONArray();

        for(String key : propertyNames){
            String value = config.getProperty(key, "undefined");
            System.err.println(String.format("%s=%s",key,value));
            jsonArray.add(String.format("%s=%s",key,value));
        }
        return jsonArray;
    }

    @Override
    public String toString() {
        return String.format("[AnnotatedBean] timeout: %d, batch: %d,server.port: %s, jsonBeans: %s,anotherJsonBean: %s", timeout, batch,port, jsonBeans,anotherJsonBean);
    }

    private static class JsonBean{
        private String someString;
        private String someInt;

        @Override
        public String toString() {
            return "JsonBean{" +
                    "someString='" + someString + '\'' +
                    ", someInt='" + someInt + '\'' +
                    '}';
        }
    }
}
