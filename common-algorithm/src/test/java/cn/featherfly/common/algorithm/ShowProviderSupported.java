package cn.featherfly.common.algorithm;

import java.security.Provider;
import java.security.Security;

import org.testng.annotations.Test;

public class ShowProviderSupported {

    @Test
    public void test() {
        System.out.println("-------列出加密服务提供者-----");
        Provider[] pro = Security.getProviders();
        for (Provider p : pro) {
            System.out.println("Provider:" + p.getName() + " - version:" + p.getVersion());
            System.out.println(p.getInfo());
        }
        System.out.println("");
        System.out.println("-------列出系统支持的消息摘要算法：");
        for (String s : Security.getAlgorithms("MessageDigest")) {
            System.out.println(s);
        }
        System.out.println("-------列出系统支持的Mac算法：");
        for (String s : Security.getAlgorithms("Mac")) {
            System.out.println(s);
        }
        System.out.println("-------列出系统支持的SecretKeyFactory算法：");
        for (String s : Security.getAlgorithms("SecretKeyFactory")) {
            System.out.println(s);
        }
        System.out.println("-------列出系统支持的生成公钥和私钥对的算法：");
        for (String s : Security.getAlgorithms("KeyPairGenerator")) {
            System.out.println(s);
        }
        System.out.println("-------列出系统支持的Cipher算法：");
        for (String s : Security.getAlgorithms("Cipher")) {
            System.out.println(s);
        }
    }
}