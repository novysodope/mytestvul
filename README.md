# MTV介绍
mytestvul-自己写的一个用来做漏洞复现/验证的小框架
# 环境准备
推荐使用jdk<=1.8.0_181

# 使用
idea导入就可以直接运行了

可以自定义添加其他漏洞环境
# ps:
如果出现“程序包org.springframework.web.bind.annotation不存在”就百度，实在不行就点右边的release下载，可以直接运行，如果TestController的@Slf4j注解爆红可以不用理
# 更新日志
## 20201230更新mtv1.1
新增rmi
### rmi启动：
java -cp jar包 启动类
```bash
java -cp rmitest-1.0-SNAPSHOT.jar org.rmitest1.RMIServerTest
```
## 20200205更新mtv1.2
增加spring boot Thymeleaf模板注入，参考veracode-research的[spring-view-manipulation](https://github.com/veracode-research/spring-view-manipulation "spring-view-manipulation")项目

## 20210324更新mtv1.3
增加以SpringMessaging RCE为例子的SpEL表达式注入项目；
增加一个SnakeYaml反序列化项目

## 20210802更新mtv1.3.1
添加各种平时自己学习留下的东西，姑且称为垃圾桶，哈哈哈
