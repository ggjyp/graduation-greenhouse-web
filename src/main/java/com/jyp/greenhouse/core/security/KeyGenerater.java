package com.jyp.greenhouse.core.security;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by oplus on 2017/4/11.
 */
public class KeyGenerater {


    public static Map generater() {

        try {
            java.security.KeyPairGenerator keygen = java.security.KeyPairGenerator
                    .getInstance("RSA");
            SecureRandom secrand = new SecureRandom();
            secrand.setSeed("syj".getBytes()); // 初始化随机产生器
            keygen.initialize(1024, secrand);
            KeyPair keys = keygen.genKeyPair();

            PublicKey pubkey = keys.getPublic();
            PrivateKey prikey = keys.getPrivate();

            byte[] pubKey = Base64.getEncoder().encode(pubkey.getEncoded());
            byte[] priKey = Base64.getEncoder().encode(prikey.getEncoded());

//            System.out.println("pubKey = " + new String(pubKey));
//            System.out.println("priKey = " + new String(priKey));
            Map mp = new HashMap();
            mp.put("pubKey", pubKey);
            mp.put("priKey", priKey);
            return mp;
        } catch (Exception e) {
            System.out.println("生成密钥对失败");
            e.printStackTrace();
            return null;
        }
    }


}
