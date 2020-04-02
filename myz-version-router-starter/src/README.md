## 针对springcloud框架的微服务，提供服务访问的版本控制，实现灰度发布

### 服务提供者

#### 1.pom引入依赖

    <dependency>
        <groupId>com.auxgroup.homeappliance</groupId>
        <artifactId>lms-version-router-starter</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
    
#### 2.配置文件配置

    eureka:
        instance:
            metadata-map:
                versions: snapshot-20190620
    
至此服务提供者启动时以snapshot-20190620版本提供服务
    
### 服务消费者

#### 1.pom引入依赖和添加启动类配置

    <dependency>
        <groupId>com.auxgroup.homeappliance</groupId>
        <artifactId>lms-version-router-starter</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
    
    启动类添加注解，指定每个feignClient的ribbon配置，主要是rule规则
    @RibbonClients(value = {
        @RibbonClient(name = "lms-provider",configuration = FeignClientConfigduration.class),
        @RibbonClient(name = "lms-provider2",configuration = FeignClientConfigduration.class)
    })
    或者用配置文件制定  
    lms-provider:
        ribbon:
            NFLoadBalancerRuleClassName: com.auxgroup.homeappliance.lms.version.router.ribbon.loadbalancer.VersioningZoneAvoidanceRule  
    lms-provider2:
        ribbon:
            NFLoadBalancerRuleClassName: com.auxgroup.homeappliance.lms.version.router.ribbon.loadbalancer.VersioningZoneAvoidanceRule
            
    
#### 2.提供多种版本控制策略

##### a. URL query参数控制版本 
配置：

    @Bean
    public RequestVersionExtractor requestVersionExtractor(){
        return new RequestVersionExtractor.Default();
    }
    
如http://127.0.0.1:8080/lms-provider/hello?rq_version=snapshot-20190620
##### b. Header参数控制版本
配置：

    @Bean
    public RequestVersionExtractor requestVersionExtractor(){
        return new RequestVersionExtractor.RequestHeaderVersionExtractor();
    }
        
如请求服务时header添加 rq_version=release-20180301
            
##### c. 配置文件映射服务名和版本（默认这个策略）
配置文件添加配置文件：

    versioning:
      server-version:
        metadata:
          lms-provider: 1.0
          base-provider: 2.0
          order-provider: 3.0
          
metadata后面的格式为{服务提供者的服务名}:{版本号}，如order-provider服务请求3.0版本的。
{服务提供者的服务名}即为provider的application-name也即@FeignClient的value，
@FeignClient(value = "order-provider",fallbackFactory = MyFallBackFactory.class)
                
策略a使用场景：通过API网关给第三方调用，放在URL上，清晰明了  
策略b使用场景：网页请求，只要塞过这个header后面的服务路由就会受版本号控制，缺陷是如果consumer需要调用N个服务且版本号各不相同，则无法满足  
策略c使用场景：版本号控制到服务应用级别，不同服务指定不同版本，更符合生产情况。
            

        