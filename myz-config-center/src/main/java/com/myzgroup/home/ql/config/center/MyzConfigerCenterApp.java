package com.myzgroup.home.ql.config.center;

import com.myzgroup.home.ql.config.center.util.LogUtil;
import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.Environment;

import javax.security.auth.message.config.AuthConfigFactory;

/**
 * @author Administrator
 * @version V1.0
 * @className MyzEurekaApp
 * @description: eureka 注册中心
 * @date 2020/4/1
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class MyzConfigerCenterApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyzConfigerCenterApp.class);

    public static void main( String[] args )
    {
        if (AuthConfigFactory.getFactory() == null) {
            AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
        }
        Environment env  =  new SpringApplicationBuilder().sources(
                MyzConfigerCenterApp.class).web(true).run(args).getEnvironment();
        LOGGER.info(LogUtil.startupLog(env));
    }

}
