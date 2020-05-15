package xingkong.tool.core;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 编码与解码操作工具类
 * 
 * @author xingkong
 * 
 */
@Slf4j
public class CodecTool {

    /**
     * 将 URL 编码
     */
    public static String encodeURL(String str) {
        String target;
        try {
            target = URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            log.error("编码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将 URL 解码
     */
    public static String decodeURL(String str) {
        String target;
        try {
            target = URLDecoder.decode(str, "UTF-8");
        } catch (Exception e) {
            log.error("解码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将字符串 Base64 编码
     */
    public static String encodeBASE64(String str) {
        String target;
        try {
            target = Base64.encodeBase64URLSafeString(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("编码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将字符串 Base64 解码
     */
    public static String decodeBASE64(String str) {
        String target;
        try {
            target = new String(Base64.decodeBase64(str), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("解码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将字符串 MD5 加密
     */
    public static String encryptMD5(String str) {
        return DigestUtils.md5Hex(str);
    }

    /**
     * 将字符串 SHA 加密
     */
    public static String encryptSHA(String str) {
        return DigestUtils.sha1Hex(str);
    }

    /**
     * 创建随机数
     */
    public static String createRandom(int count) {
        return RandomStringUtils.randomNumeric(count);
    }

    /**
     * 获取 UUID（32位）
     */
    public static String createUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    /**
     * 生成UUID 小写的
     * 
     * @return
     */
    public static String genUUID() {
        String id = UUID.randomUUID().toString().replace("-", "");
        return id;
    }

    public static List<String> generateIdList(int count) {
        List<String> idList = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            idList.add(genUUID());
        }
        return idList;
    }

    /**
     * 生成6位的UUID的盐
     * 
     * @return
     */
    public static String genUUIDSALT() {
        String id = UUID.randomUUID().toString().replace("-", "");
        return id.substring(0, 6);
    }

    /**
     * 生成6位的UUID的盐
     *
     * @return
     */
    public static String genUUIDSALT(int length) {
        String id = UUID.randomUUID().toString().replace("-", "");
        return id.substring(0, length);
    }

    public static int genNumCode(int length) {
        // 生成随机类
        Random random = new Random();
        int code = 0;
        for (int i = 0; i < length; i++) {
            code = (10 * code) + (random.nextInt(9) + 1);
        }
        log.info("curCode:" + code);
        return code;
    }

    public static String genNumCodeText(int length) {
        return genNumCode(length) + "";
    }

}
