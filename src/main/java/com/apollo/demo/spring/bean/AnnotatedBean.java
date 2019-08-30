package com.apollo.demo.spring.bean;

import com.ctrip.framework.apollo.spring.annotation.ApolloJsonValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangwei on 2019/8/30 0030.
 */
@Component
public class AnnotatedBean {
    private static final Logger logger = LoggerFactory.getLogger(AnnotatedBean.class);
    @Value("${timeout:200}")
    private int timeout;
    private int batch;
    private List<JsonBean> jsonBeans;

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

    @Override
    public String toString() {
        return String.format("[AnnotatedBean] timeout: %d, batch: %d, jsonBeans: %s,anotherJsonBean: %s", timeout, batch, jsonBeans,anotherJsonBean);
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
