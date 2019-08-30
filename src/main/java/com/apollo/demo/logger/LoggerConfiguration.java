package com.apollo.demo.logger;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * Created by wangwei on 2019/8/31 0031.
 */
@Service
public class LoggerConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(LoggerConfiguration.class);
    private static final String LOGGER_TAG = "logging.level.";
    @Autowired
    private LoggingSystem loggingSystem;
    @ApolloConfig
    private Config config;

    @ApolloConfigChangeListener
    private void onchange(ConfigChangeEvent changeEvent){
        refreshLoggingLevels();
    }

    /**
     * 被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，
     * 并且只会被服务器调用一次，类似于Servlet的inti()方法。
     * 被@PostConstruct修饰的方法会在构造函数之后，init()方法之前运行。
     */
    @PostConstruct
    private void refreshLoggingLevels(){
        Set<String> keyNames = config.getPropertyNames();
        for(String key : keyNames){
            if(containsIgnoreCase(key,LOGGER_TAG)){
                String strLevel = config.getProperty(key, "info");
                LogLevel level = LogLevel.valueOf(strLevel.toUpperCase());
                loggingSystem.setLogLevel(key.replace(LOGGER_TAG,""),level);
                logger.info("{}:{}", key, strLevel);
            }
        }
    }

    private static boolean containsIgnoreCase(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        int len = searchStr.length();
        int max = str.length() - len;
        for (int i = 0; i <= max; i++) {
            if (str.regionMatches(true, i, searchStr, 0, len)) {
                return true;
            }
        }
        return false;
    }
}
