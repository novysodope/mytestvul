package org.rmitest1;

/**
 * @author: novy
 * @date: 2020/12/29
 * @time: 14:37
 **/
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMITestServiceImpl extends UnicastRemoteObject implements RMITestService {

    private static final long serialVersionUID = 1L;

    protected RMITestServiceImpl() throws RemoteException {
        super();
    }

    /**
     * RMI测试方法
     *
     * @return 返回测试字符串
     */
    @Override
    public String test() throws RemoteException {

        return "123";
    }

}