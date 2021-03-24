# MTV介绍
mytestvul-自己写的一个用来做漏洞复现/验证的小框架
# 环境准备
推荐使用jdk<=1.8.0_181

可以自定义添加其他漏洞环境
# ps:
如果运行的时候提示缺少什么什么的话就手动导入一下src\main\webapp\WEB-INF\lib里的jar包，如果出现“程序包org.springframework.web.bind.annotation不存在”就百度，实在不行就点右边的release下载，可以直接运行，如果有修改请先clean
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

## 20200205更新mtv1.3
增加以SpringMessaging RCE为例子的SpEL表达式注入项目；
增加一个SnakeYaml反序列化项目
