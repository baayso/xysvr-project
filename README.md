# xysvr-project
<br/>
注：项目中所有与业务逻辑相关的代码已经全部删除，只保留项目架构和基本代码。


项目采用 Spring + Spring MVC + Mybatis 开发，分为 APP服务端 和 APP服务端后台管理系统 两部分（客户端由另外的同事开发，我提供建议以减小服务器的压力）。所有服务器的架设和配置是我来完成的。

服务器配置请转到：https://github.com/baayso/xysvr-config

### 服务端架构示意图：
![服务端架构示意图](http://7xkgtt.com1.z0.glb.clouddn.com/%E6%9C%8D%E5%8A%A1%E7%AB%AF%E6%9E%B6%E6%9E%84%E7%A4%BA%E6%84%8F%E5%9B%BE.png)

两台Nginx负责接收客户端发来的请求(实际使用的是阿里的[Tengine](http://tengine.taobao.org "The Tengine Web Server"))，接收到请求后，将请求随机丢给一台Tomcat，所有的Tomcat都从Redis中获取Session数据（Session数据集中存储），请求处理完成后返回给客户端。

由于使用两台Nginx来负责接收请求（两台Nginx的域名例如：svr1.xy-space.cn 和 svr2.xy-space.cn），所以需要在多个三级域名下共享Session。由于二级域名相同，所以只需设置Session的domain即可，不必使用单点登录。

项目使用了[Shiro安全框架](https://github.com/waylau/apache-shiro-1.2.x-reference/blob/master/I.%20Overview%20%E6%80%BB%E8%A7%88/1.%20Introduction%20%E4%BB%8B%E7%BB%8D.md "What is Apache Shiro?")，Shiro可以帮助我们完成：认证、授权、加密、会话管理、与Web集成、缓存等。还也可以很容易的实现：控制同一用户并发登录人数、限制密码重试次数。

Shiro框架的Session相关部分配置如下：

```xml
<bean id="sessionIdCookie" class="org.apache.shiro.web.servlet.SimpleCookie">
	<!-- 设置cookie的name，默认是 JSESSIONID -->
	<constructor-arg name="name" value="sid" />
	<!-- 设置cookie的path，用于多个系统共享session -->
	<property name="path" value="/xysvr" />
	<!-- 设置cookie的domain，用于多个三级域名共享session -->
	<property name="domain" value=".xy-space.cn" />
	<property name="httpOnly" value="true" />
	<property name="maxAge" value="-1" />
</bean>
```

[查看全部配置](https://github.com/baayso/xysvr-project/blob/master/xysvr-web-project/xysvr-web-api/src/main/resources/spring/spring-shiro.xml "spring-shiro.xml")

设置了Session的domain之后，进行本地访问（调试）之前需要在hosts文件中加入如下条目：

```
127.0.0.1       localhost.xy-space.cn
```

然后使用 **localhost.xy-space.cn** 代替 **localhost** 进行访问（注意 **也不能** 使用 **127.0.0.1**）。如果不加上，Session的domain则为 **localhost** ，而服务端需要从domain为 **.xy-space.cn** 的Session中获取数据。由于domain对应不上，服务端获取不到Session数据，最后造成在登录成功后再次进行操作时服务端却提示未登录。

****
<br/>
### Session集中存储
Shiro提供了完整的企业级会话管理功能，不依赖于底层容器（如Web容器Tomcat），不管JavaSE还是JavaEE环境都可以使用，提供了会话管理、会话事件监听、会话存储/持久化、容器无关的集群、失效/过期支持、对Web的透明支持、SSO单点登录的支持等特性。即直接使用Shiro的会话管理可以直接替换如Web容器的会话管理。

项目中使用[shiro-redis](https://github.com/baayso/shiro-redis "shiro-redis")库将Session数据集中存储在Redis中。

****
<br/>
### WebSocket
项目中用户之间的私信功能是通过WebSocket实现的，可以做到用户之间进行实时通信。具体代码可以点下面的链接进行查看。

https://github.com/baayso/xysvr-project/blob/master/xysvr-function-project/xysvr-function-api/src/main/java/cn/xyspace/xysvr/function/websocket/handler/MsgWebsocketEndPoint.java

****
<br/>
### 项目依赖的第三方库：
https://github.com/baayso/shiro-redis （我将这个工具中的序列化修改成了使用[FST](http://www.oschina.net/p/fst "Java快速序列化库")库实现，并在 RedisManager 类中加入了 JedisPoolConfig 成员变量和相应的 getter 和 setter 以方便自定义连接池。）

https://github.com/springside/springside4  
