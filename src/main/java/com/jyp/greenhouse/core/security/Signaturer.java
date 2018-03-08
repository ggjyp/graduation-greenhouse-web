package com.jyp.greenhouse.core.security;

import com.jyp.greenhouse.core.redis.RedisAPI;
import com.jyp.greenhouse.core.util.StringUtil;
import com.jyp.greenhouse.web.constants.Constants;
import redis.clients.jedis.Jedis;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

/**
 * Created by oplus on 2017/4/10.
 */
public class Signaturer {

    private static final String sufix = "AES";
    private static final int expire_in = 2592000;//3600*24*30 一个月有效期

    /**
     * 1、获取RSA公钥pubKey和私钥priKey
     * 2、使用RSA私钥priKey加密plaintext
     * 3、使用AES加密plaintext得到enryptstr
     * 4、以sign为key存储RSA公钥pubKey，以signstr为key存储enryptstr
     * 5、返回signstr
     *
     * @param plaintext 明文
     * @return
     */
    public static String sign(String plaintext) {
        String signstr = null;
        Map keymap = KeyGenerater.generater();
        byte[] priKey = (byte[]) keymap.get("priKey");
        byte[] pubKey = (byte[]) keymap.get("pubKey");
        // RSA加密生成数字签名
        byte[] sign = signGenerate(priKey, plaintext);
        Jedis jedis = null;
        try {
            jedis = RedisAPI.getPool().getResource();
            signstr = new String(sign);
            jedis.setex(sign, expire_in, pubKey);//以数字签名为key存储RSA公钥pubKey
            String enryptstr = EnryptUtil.encrypt(plaintext);//AES加密明文
            jedis.setex(signstr + sufix, expire_in, enryptstr);//以数字签名为key存储AES密文
        } catch (Exception ex) {
            ex.printStackTrace();
            signstr = null;
            System.out.println("数字签名失败");
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
        return signstr;
    }

    /**
     * Description:数字签名
     *
     * @param priKeyText
     * @param plainText
     * @return
     * @author oplus
     */
    public static byte[] signGenerate(byte[] priKeyText, String plainText) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64
                    .getDecoder().decode(priKeyText));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey prikey = keyf.generatePrivate(priPKCS8);

            // 用私钥对信息生成数字签名
            Signature signet = Signature
                    .getInstance("MD5withRSA");
            signet.initSign(prikey);
            signet.update(plainText.getBytes());
            byte[] signed = Base64.getEncoder().encode(signet.sign());
            return signed;
        } catch (Exception e) {
            System.out.println("签名失败");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Description:校验数字签名,此方法不会抛出任务异常,成功返回true,失败返回false,要求全部参数不能为空
     *
     * @param pubKeyText 公钥,base64编码
     * @param plainText  明文
     * @param signText   数字签名的密文,base64编码
     * @return
     */
    public static boolean verifyRSA(byte[] pubKeyText, String plainText,
                                    byte[] signText) {
        try {
            // 解密由base64编码的公钥,并构造X509EncodedKeySpec对象
            java.security.spec.X509EncodedKeySpec bobPubKeySpec = new java.security.spec.X509EncodedKeySpec(
                    Base64.getDecoder().decode(pubKeyText));
            // RSA对称加密算法
            KeyFactory keyFactory = KeyFactory
                    .getInstance("RSA");
            // 取公钥匙对象
            PublicKey pubKey = keyFactory
                    .generatePublic(bobPubKeySpec);
            // 解密由base64编码的数字签名
            byte[] signed = Base64.getDecoder().decode(signText);
            Signature signatureChecker = Signature
                    .getInstance("MD5withRSA");
            signatureChecker.initVerify(pubKey);
            signatureChecker.update(plainText.getBytes());
            // 验证签名是否正常
            if (signatureChecker.verify(signed))
                return true;
            else
                return false;
        } catch (Throwable e) {
            System.out.println("校验签名失败");
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 1、根据sign和signstr获取RSA公钥pubKey和enryptstr
     * 2、AES解密enryptstr获取plaintext
     * 3、verifyRSA（pubkey,plaintext,sign）校验数字签名
     * 4、若正确返回plaintxt不正确返回null
     *
     * @param signstr 数字签名
     * @return
     */
    public static String verify(String signstr) {
        String plaintext = null;
        Jedis jedis = null;
        try {
            jedis = RedisAPI.getPool().getResource();

            //数字签名
            byte[] sign = signstr.getBytes(Constants.ENCODING_UTF_8);

            String enryptstr = (String) jedis.get(signstr + sufix);
            byte[] pubKey = (byte[]) jedis.get(sign);
            if (!StringUtil.isBlank(enryptstr) && pubKey != null) {

                String plaintextAES = EnryptUtil.decrypt(enryptstr);
                if (verifyRSA(pubKey, plaintextAES, sign)) {
                    plaintext = plaintextAES;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("校验数字签名失败");
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
        return plaintext;
    }


    public static void main(String[] args) throws Exception {
        String oriplaintext = "111111";
        System.out.println("oriplaintext=" + oriplaintext);
        String signstr = sign(oriplaintext);
        System.out.println("signstr=" + signstr);
        String base64code = Base64Codec.encode(signstr);
        System.out.println("signstr_base64code=" + base64code);
        String base64encode = Base64Codec.decodeString(base64code);
        System.out.println("signstr_base64encode=" + base64encode);
        System.out.println("plaintext=" + verify(signstr));

    }
}
