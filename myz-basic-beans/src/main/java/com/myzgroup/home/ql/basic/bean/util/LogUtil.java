package com.myzgroup.home.ql.basic.bean.util;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * @author Administrator
 * @version V1.0
 * @className LogUtil
 * @description: 启动日志
 * @date 2020/4/7
 **/

public class LogUtil {
    private static final Logger LOGGER		= LoggerFactory.getLogger(LogUtil.class);

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

    public static String startupLog(Environment env, Map<String,Object> versionMetadata) {

        try {
            String msg = "\n\tconsumer versions:\t";
            if(versionMetadata!=null && !versionMetadata.isEmpty()){
                for(Map.Entry<String,Object> entry : versionMetadata.entrySet()){
                    msg = msg + "\n\t\t\t\t\t\t"+entry.getKey()+": "+entry.getValue();
                }
            }else{
                msg = msg+" no consumer versions set";
            }

            return "\n----------------------------------------------------------"+
                    "\n\tApplication '"+env.getProperty("spring.application.name")+"' is running! active-profile:"+env.getProperty("spring.profiles.active")+" Access URLs:"+
                    "\n\t" + "Local: \t\thttp://127.0.0.1:"+env.getProperty("server.port")+
                    "\n\tExternal: \thttp://"+ InetAddress.getLocalHost().getHostAddress()+":"+env.getProperty("server.port")+
                    "\n\teureka:\t"+(env.getProperty("eureka.client.service-url.defaultZone")==null?"no eureka set":env.getProperty("eureka.client.service-url.defaultZone"))+
                    "\n\tprovider versions:\t"+(env.getProperty("eureka.instance.metadata-map.versions")==null?"no provider versions set":env.getProperty("eureka.instance.metadata-map.versions"))+
                    msg+
                    "\n----------------------------------------------------------";
        } catch (UnknownHostException e) {
            LOGGER.error("启动打印异常",e);
            return "";
        }
    }


    public static String loginSuccessLog(String loginFrom,String userId,String username,String name,String mobile,String authorityStr) {


        return "\n------------------------- 登陆信息 ---------------------------------"+
                "\n\t loginFrom: "+loginFrom+
                "\n\t userId: "+userId+
                "\n\t username: "+username+
                "\n\t name: "+ name+
                "\n\t mobile: "+mobile+
                "\n\t authority: "+authorityStr+
                "\n----------------------------------------------------------";
    }
}
