package com.apollo.demo.spring.refresh;

import com.ctrip.framework.apollo.core.ConfigConsts;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Created by wangwei on 2019/8/30 0030.
 */
//@Component
public class SpringBootApolloRefreshConfig {
    @Autowired
    private TestBeanConfig testBeanConfig;
    @Autowired
    private RefreshScope refreshScope;

    @ApolloConfigChangeListener(value = {ConfigConsts.NAMESPACE_APPLICATION,"TEST1.ww-public"}
    )
    public void onchange(ConfigChangeEvent changeEvent){
        System.err.println(String.format("before refresh {%s}",testBeanConfig.toString()));
//        refreshScope.refresh("testBeanConfig");
        refreshScope.refreshAll();
        System.err.println(String.format("after refresh {%s}",testBeanConfig.toString()));

    }
}
