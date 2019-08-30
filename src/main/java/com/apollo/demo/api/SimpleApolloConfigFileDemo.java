package com.apollo.demo.api;

import com.ctrip.framework.apollo.*;
import com.ctrip.framework.apollo.core.enums.ConfigFileFormat;
import com.ctrip.framework.apollo.model.ConfigFileChangeEvent;
import com.google.common.base.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by wangwei on 2019/8/29 0029.
 */
public class SimpleApolloConfigFileDemo {
    private static final Logger logger = LoggerFactory.getLogger(SimpleApolloConfigFileDemo.class);
    private String DEFAULT_VALUE = "undefined";
    private Config config;
    private ConfigFile configFile;

    public SimpleApolloConfigFileDemo(){
        /**
         * 改变的namespace：application.properties
         * 改变的->旧值：ww.apollo.num=2
         *             ww.apollo.name=apollo-study-v3
         ,新值：ww.apollo.num=3
               ww.apollo.name=apollo-study-v3
         ,changeType: MODIFIED
         */
        ConfigFileChangeListener changeListener = new ConfigFileChangeListener(){
            @Override
            public void onChange(ConfigFileChangeEvent configFileChangeEvent) {
                logger.info("改变的namespace：{}",configFileChangeEvent.getNamespace());
                logger.info("改变的->旧值：{},新值：{},changeType: {}",
                        configFileChangeEvent.getOldValue(),
                        configFileChangeEvent.getNewValue(),
                        configFileChangeEvent.getChangeType());
            }
        };
//        config = ConfigService.getAppConfig();// 默认namespace：application
//        config = ConfigService.getConfig("application");
        configFile = ConfigService.getConfigFile("application", ConfigFileFormat.Properties);
        configFile.addChangeListener(changeListener);

    }

    private String getConfig(String key) {
        String result = config.getProperty(key, DEFAULT_VALUE);
        logger.info(String.format("Loading key : %s with value: %s", key, result));
        return result;
    }

    private String getConfigFile() {
        String result = configFile.getContent();
        /**
         * 获取的是全部配置，以key=value展示，形如properties文件格式
         */
        System.err.println("result: \n"+result);
        return result;
    }

    public static void main(String[] args) throws Exception {
        configFileDemo();
    }

    private static void configDemo() throws IOException{
        SimpleApolloConfigFileDemo apolloConfigDemo = new SimpleApolloConfigFileDemo();
        System.out.println(
                "Apollo Config Demo. Please input key to get the value. Input quit to exit.");
        while (true) {
            System.out.print("> ");
            String input = new BufferedReader(new InputStreamReader(System.in, Charsets.UTF_8)).readLine();
            if (input == null || input.length() == 0) {
                continue;
            }
            input = input.trim();
            if (input.equalsIgnoreCase("quit")) {
                System.exit(0);
            }
//            apolloConfigDemo.getConfig(input);
        }
    }

    private static void configFileDemo() throws Exception{
        SimpleApolloConfigFileDemo apolloConfigDemo = new SimpleApolloConfigFileDemo();
        apolloConfigDemo.getConfigFile();
        Thread.sleep(60*60*1000);

    }
}
