package controller;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * @Author novy
 * @Date 2021/7/15 14:16
 * @Version 1.0
 */
public class SerializeUtil implements Serializable {
    public void deserialize(HttpServletRequest request) throws Exception {
        ServletInputStream inputStream = request.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        objectInputStream.readObject();
        objectInputStream.close();
    }
}
