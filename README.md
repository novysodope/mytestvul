# mytestvul
mytestvul-自己写的一个用来做漏洞复现/验证的小框架
# 环境准备
推荐使用jdk<=1.8.0_181

rmi反序列化要低于1.8

# rmi启动：
java -cp jar包 启动类
```bash
java -cp rmitest-1.0-SNAPSHOT.jar org.rmitest1.RMIServerTest
```
![](https://s3.ax1x.com/2020/12/29/rbNLzn.png)
# 项目运行
启动框架查看效果

![](https://assets.baklib.com/t/3f5cfd6c-1cc9-46dd-b035-da429b349cd0/u/233c4a2d-9207-42d6-9b81-cfb0436aae6f/image1606977207105.png)

测试fastjson反序列化漏洞

![](https://assets.baklib.com/t/3f5cfd6c-1cc9-46dd-b035-da429b349cd0/u/233c4a2d-9207-42d6-9b81-cfb0436aae6f/image1606895403689.png)

axis远程命令执行漏洞

![](https://assets.baklib.com/t/3f5cfd6c-1cc9-46dd-b035-da429b349cd0/u/233c4a2d-9207-42d6-9b81-cfb0436aae6f/image1606977356446.png)

可以自定义添加其他漏洞环境


# ps:
如果运行的时候提示缺少什么什么的话就手动导入一下src\main\webapp\WEB-INF\lib里的jar包，如果出现“程序包org.springframework.web.bind.annotation不存在”就百度

# 实在不行就点右边的release下载，可以直接运行，如果有修改请先clean
