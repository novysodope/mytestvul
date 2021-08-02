package controller;

import org.springframework.beans.factory.config.PreferencesPlaceholderConfigurer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: novy
 * @date: 2021/3/10
 * @time: 16:32
 **/
public class TestRun {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
       // Test obj = new Test();
       // String document1=obj.document;
       // System.out.println(document1);
        //无参的new相当于
        Class test1 = Class.forName("controller.Test");
        Object test2 = test1.newInstance();
        asd();
        asdq();
        //如果有参的话就这样：
      //  Class test1 = Class.forName("controller.UrlClassLoaderTest"); //获取要加载的类
       // test1.getConstructors()[0].newInstance("address");//实例化类的有参构造方法

    }
 //获取当前路径并把单斜杠替换成双斜杠
public static void asd() {
        File directory = new File("");
        String novy = directory.getAbsolutePath();
        System.out.println(novy);
        System.out.println(novy.replace("\\","\\\\"));


        }
        //生成一个1.exe隐藏 
public static void asdq(){
    File file = new File("D:" + File.separator + "1.exe");
    try {
        file.createNewFile();
        // R ： 只读文件属性。A：存档文件属性。S：系统文件属性。H：隐藏文件属性。
        String sets = "attrib +H \"" + file.getAbsolutePath() + "\"";
        System.out.println(sets);
        Runtime.getRuntime().exec(sets);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
