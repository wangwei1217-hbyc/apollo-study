package com.apollo.demo.spring.refresh;

import com.ctrip.framework.apollo.core.ConfigConsts;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by wangwei on 2019/8/30 0030.
 */
@Component
public class SpringBootApolloRefreshConfig2 implements ApplicationContextAware {
    public static final Logger log = LoggerFactory.getLogger(SpringBootApolloRefreshConfig2.class);
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    @ApolloConfigChangeListener(value = {ConfigConsts.NAMESPACE_APPLICATION,"TEST1.ww-public"})
    public void onChange(ConfigChangeEvent changeEvent){
        for(String key : changeEvent.changedKeys()){
            ConfigChange change = changeEvent.getChange(key);
            System.err.println(String.format("change->namespace:{%s},key:{%s},oldValue:{%s},newValue:{%s},changeType:{%s}",
                    change.getNamespace(),change.getPropertyName(),
                    change.getOldValue(),
                    change.getNewValue(),change.getChangeType()));
        }
        this.applicationContext.publishEvent(new EnvironmentChangeEvent(changeEvent.changedKeys()));
    }
}
