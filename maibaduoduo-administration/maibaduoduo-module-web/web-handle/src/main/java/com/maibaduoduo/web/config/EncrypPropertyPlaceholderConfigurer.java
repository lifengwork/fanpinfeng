package com.maibaduoduo.web.config;

import com.maibaduoduo.common.utils.SymmetricEncoder;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyOverrideConfigurer;

import java.util.Properties;

/**
 * Created by Administrator on 2019/10/20 0020.
 */
public class EncrypPropertyPlaceholderConfigurer extends PropertyOverrideConfigurer {
    private Logger log= Logger.getLogger(EncrypPropertyPlaceholderConfigurer.class);
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        String username = props.getProperty("jdbc.username");
        if (username != null) {
            props.setProperty("jdbc.username", SymmetricEncoder.AESDncode(username));
        }
        String password = props.getProperty("jdbc.password");
        if (username != null) {
            props.setProperty("jdbc.password", SymmetricEncoder.AESDncode(password));
        }
        super.processProperties(beanFactoryToProcess, props);
    }
}