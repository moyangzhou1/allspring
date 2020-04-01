package com.myzgroup.home.ql.eureka;

import com.myzgroup.home.ql.eureka.util.LogUtil;
import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.Environment;

import javax.security.auth.message.config.AuthConfigFactory;

/**
 * @author Administrator
 * @version V1.0
 * @className MyzEurekaApp
 * @description: TODO
 * @date 2020/4/1
 **/
@EnableEurekaServer
@SpringBootApplication
public class MyzEurekaApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyzEurekaApp.class);

    public static void main( String[] args )
    {
        if (AuthConfigFactory.getFactory() == null) {
            AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
        }
        Environment env  =  new SpringApplicationBuilder().sources(
                MyzEurekaApp.class).web(true).run(args).getEnvironment();
        LOGGER.info(LogUtil.startupLog(env));
    }

}
