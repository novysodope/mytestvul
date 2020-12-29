package org.rmitest1;

/**
 * @author: novy
 * @date: 2020/12/29
 * @time: 14:36
 **/
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RMI测试接口
 */
public interface RMITestService extends Remote {

    /**
     * RMI测试方法
     *
     * @return 返回测试字符串
     */
    String test() throws RemoteException;

}
