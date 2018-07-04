package com.shandong.human.resource.util.config;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * 配置文件符合查询类
 *
 * @author tyee.noprom@qq.com
 * @time 2/5/16 9:13 AM.
 */
public class CompositeFactory {

    private static CompositeConfiguration configuration;

    private CompositeFactory(){}

    public static CompositeConfiguration getInstance(){
        if(configuration == null){
            synchronized (CompositeFactory.class) {
                configuration = new CompositeConfiguration();
                try {
                    PropertiesConfiguration pc = new PropertiesConfiguration();
                    pc.setEncoding("utf8");
                    pc.load("sysconfig.properties");
                    configuration.addConfiguration(pc);
                } catch (ConfigurationException e) {
                    e.printStackTrace();
                }
            }
        }
        return configuration;
    }

    public static void addConfiguration(Configuration config){
        configuration = getInstance();
        configuration.addConfiguration(config);
    }

    public static String getString(String key){
        return configuration.getString(key);
    }
}