package controller;

import java.io.IOException;

/**
 * @author: novy
 * @date: 2021/3/10
 * @time: 16:49
 **/
public class UrlClassLoaderTest {
    public UrlClassLoaderTest(String address) throws IOException {
         Runtime a = Runtime.getRuntime();
        a.exec("calc");
        System.out.println("测试参数"+address);
    }
}
