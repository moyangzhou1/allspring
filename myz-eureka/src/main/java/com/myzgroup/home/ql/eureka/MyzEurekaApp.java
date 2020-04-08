package com.myzgroup.home.ql.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Administrator
 * @version V1.0
 * @className MyzEurekaApp
 * @description: eureka 注册中心
 * @date 2020/4/1
 **/
@EnableEurekaServer
@SpringBootApplication
public class MyzEurekaApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyzEurekaApp.class);

    public static void main( String[] args ){
        SpringApplication.run(MyzEurekaApp.class, args);
//        if (AuthConfigFactory.getFactory() == null) {
//            AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
//        }
//        Environment env  =  new SpringApplicationBuilder().sources(
//                MyzEurekaApp.class).web(true).run(args).getEnvironment();
//        LOGGER.info(LogUtil.startupLog(env));
    }

}
