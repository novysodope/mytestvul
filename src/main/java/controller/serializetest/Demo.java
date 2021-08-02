package controller.serializetest;

/**
 * @Author novy
 * @Date 2021/7/29 17:00
 * @Version 1.0
 * 
 */
import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
public class Demo implements Externalizable{
    private int i ;
    private String s;
    public Demo() {}
    public Demo(String x, int a) {
        System.out.println("Demo对象已构造完成");
        s = x;
        i = a;
    }
    public String toString() {
        return s+" "+i;
    }
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        //规定了对象中哪些成员会进行序列化，同时添加一些进行序列化时的其他行为
        System.out.println("Demo.writeExternal");
        out.writeObject(s);
        out.writeInt(i);
        System.out.println(out);
    }
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Demo.readExternal");
        s = (String)in.readObject();
        i = in.readInt();
    }
    public static void main(String[] args)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        System.out.println("Constructing objects");
        Demo b = new Demo("a", 47);
        System.out.println(b);
        ObjectOutputStream o =
                new ObjectOutputStream(new FileOutputStream("D:\\test\\test.txt"));
        System.out.println("保存对象");
        o.writeObject(b);
        o.close();
        //获得对象
        System.out.println("获取对象");
        ObjectInputStream in =
                new ObjectInputStream(new FileInputStream("D:\\tools\\Me\\study\\mytestvul\\反序列化工具\\ysoserial\\cn.txt"));
        System.out.println("Recovering b");
        b = (Demo)in.readObject();
        in.close();
        System.out.println(b);
    }
}
