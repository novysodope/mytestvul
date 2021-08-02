package controller.certtest;

/**
 * @Author jy0921_ https://blog.csdn.net/ca1m0921/article/details/82894183
 * @Date 2021/7/12 11:21
 * @Version 1.0
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

public class Verify_cert {

    /**
     * 验证 CA 颁发的证书
     * 1. 读取 CA 证书的公钥
     * 2. 读取已签名的证书
     * 3. 使用 公钥验证证书
     *
     * 4. 附加，一个假的证书测试，是否有效
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //ca_path为上级证书
        //ra_path为当前ca证书（末端证书）
        //ra_path_fake是测试对象
        String ca_path = "D:\\tools\\test\\csdnroot.cer";
        String ra_path = "D:\\tools\\test\\csdnca.cer";
        String ra_path_fake = "D:\\tools\\test\\hemawebshellca.cer";

        verify_cert(ca_path, ra_path, ra_path_fake);
    }
    /**
     * 1. 使用 CA 的公钥 验证 证书
     * @param ca_52_path
     * @param user_52_path
     * @param user_csdn_path
     * @throws InvalidKeyException
     * @throws CertificateException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws FileNotFoundException
     */
    public static void verify_cert(String ca_52_path,String user_52_path,String user_csdn_path) throws InvalidKeyException, CertificateException, NoSuchAlgorithmException, NoSuchProviderException, FileNotFoundException{

        //  证书实例，将文件输入流 转换为 证书
        CertificateFactory cf = CertificateFactory.getInstance("X.509");

        // 读取 ca 证书 文件，获取证书的 公钥
        FileInputStream fis_ca = new FileInputStream(new File(ca_52_path));
        Certificate ca_52_cert = cf.generateCertificate(fis_ca);
        PublicKey ca_52_publicKey = ca_52_cert.getPublicKey();

        // 读取末端证书，获取证书
        FileInputStream fis_ra = new FileInputStream(new File(user_52_path));
        FileInputStream fis_ra_fake = new FileInputStream(new File(user_csdn_path));
        Certificate user_52_cert = cf.generateCertificate(fis_ra);
        Certificate user_csdn_cert = cf.generateCertificate(fis_ra_fake);

        // 验证证书是由 某公钥签发的：使用公钥对 该证书信息解密得到签名值 一致，或者 使用公钥对 签名值 解密，得到 证书信息一致
        try {
            user_52_cert.verify(ca_52_publicKey);
        } catch (SignatureException e) {
            System.out.println("该证书不属于 此CA签发.");
        }
        System.out.println("该证书 属于 此CA签发");
        try {
            user_csdn_cert.verify(ca_52_publicKey);
        } catch (SignatureException e) {
            System.out.println("该证书不属于 此CA签发.");
        }
    }
}
