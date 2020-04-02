package com.myzgroup.home.ql.config.center.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Administrator
 * @version V1.0
 * @className LogUtil
 * @description: 记录项目启动打印日志类
 * @date 2020/4/1
 **/
public class LogUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogUtil.class);
    private LogUtil(){}
    public static String startupLog(Environment env) {
        try {
            return "\n----------------------------------------------------------"+
                    "\n\tApplication '"+env.getProperty("spring.application.name")+"' is running! Access URLs:"+
                    "\n\t" + "Local: \t\thttp://127.0.0.1:"+env.getProperty("server.port")+
                    "\n\tExternal: \thttp://"+ InetAddress.getLocalHost().getHostAddress()+":"+env.getProperty("server.port")+
                    "\n\teureka:\t"+(env.getProperty("eureka.client.service-url.defaultZone")==null?"no eureka set":env.getProperty("eureka.client.service-url.defaultZone"))+
                    "\n\tprovider versions:\t"+(env.getProperty("eureka.instance.metadata-map.versions")==null?"no provider versions set":env.getProperty("eureka.instance.metadata-map.versions"))+
                    "\n----------------------------------------------------------";
        } catch (UnknownHostException e) {
            LOGGER.error("启动打印异常",e);
            return "";
        }
    }
}
