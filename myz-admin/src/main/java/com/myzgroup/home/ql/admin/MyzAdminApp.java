package com.myzgroup.home.ql.admin;

import com.myzgroup.home.ql.basic.bean.util.LogUtil;
import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.message.config.AuthConfigFactory;


/**
 * @author Administrator
 * @version V1.0
 * @className MyzAdminApp
 * @description: admin 后端控制中心
 * @date 2020/4/7
 **/
@SpringBootApplication
@EnableEurekaClient
@RestController
public class MyzAdminApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyzAdminApp.class);

    public static void main( String[] args )
    {
        if (AuthConfigFactory.getFactory() == null) {
            AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
        }
        Environment env  =  new SpringApplicationBuilder().sources(
                MyzAdminApp.class).web(true).run(args).getEnvironment();
        LOGGER.info(LogUtil.startupLog(env));
    }
}
