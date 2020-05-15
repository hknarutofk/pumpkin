package com.greatwall.pumpkin.tool;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 字符串工具类
 * 
 * @Author xingkong
 */
@Slf4j
public class StringTool {

    private final static String NULL_STR = "null";
    private final static String XSTR = "XXXXXXXXXXXXXXXXXXXX";
    private final static String SSTR = "********************";
    private final static Pattern MOBILE_PATTERN = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
    private final static int EVEN_LEN = 2;
    private final static Pattern CHINA_MOBILE_PATTERN = Pattern.compile(
        "/^((134)|(135)|(136)|(137)|(138)|(139)|(147)|(150)|(151)|(152)|(157)|(158)|(159)|(178)|(182)|(183)|(184)|(187)|(188)|(198))\\d{8}$/g");
    private static Pattern LINE_PATTERN = Pattern.compile("_(\\w)");

    public static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs.append("0" + stmp);
            } else {
                hs.append(stmp);
            }
        }
        return hs.toString().toUpperCase();
    }

    public static byte[] hex2byte(byte[] b) {
        if ((b.length % EVEN_LEN) != 0) {
            throw new IllegalArgumentException("len can't %2.");
        }
        byte[] b2 = new byte[b.length / EVEN_LEN];
        for (int n = 0; n < b.length; n += EVEN_LEN) {
            String item = new String(b, n, EVEN_LEN);
            b2[n / EVEN_LEN] = (byte)Integer.parseInt(item, 16);
        }
        return b2;
    }

    public static boolean isEmpty(String text) {
        if (text == null || text.trim().length() == 0 || NULL_STR.toLowerCase().trim().equals(text)
            || "".equals(text)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(Date date) {
        if (date == null) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isNotEmpty(String text) {
        return !isEmpty(text);
    }

    public static boolean isEmpty(Integer number) {
        if (number == null || "".equals(number + "")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(Integer number) {
        return !isEmpty(number);
    }

    public static boolean isEmpty(Long number) {
        if (number == null || "".equals(number + "")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(Long number) {
        return !isEmpty(number);
    }

    public static boolean isEmpty(BigDecimal number) {
        if (number == null || "".equals(number + "")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(BigDecimal number) {
        return !isEmpty(number);
    }

    public static String gbkToUtf8(String gbkText) throws UnsupportedEncodingException {
        String isoText = new String(gbkText.getBytes("UTF-8"), "ISO-8859-1");
        String utf8Text = new String(isoText.getBytes("ISO-8859-1"), "UTF-8");
        return utf8Text;
    }

    /**
     * 判断字符串中是否有中文字符
     * 
     * @param text
     * @return
     */
    public static boolean containsChinese(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= (char)0x4E00 && text.charAt(i) <= (char)0x9FA5) {
                return true;
            }
        }

        return false;
    }

    /**
     * 解码中文字符串
     * 
     * @param text
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decodeChinese(String text) throws UnsupportedEncodingException {
        String decodeText = URLDecoder.decode(text, "utf-8");
        if (!StringTool.containsChinese(decodeText)) {
            decodeText = URLDecoder.decode(text, "GBK");
        }
        return decodeText;
    }

    public static String getUrlParam(String url, String key) {
        String result = "";
        String temp = url;
        key = key + "=";
        int start = 0;
        int end = 0;
        start = temp.indexOf(key);
        if (start >= 0) {
            temp = temp.substring(start + key.length());
            end = temp.indexOf("&");

            if (end > 0) {
                result = temp.substring(0, end);
            } else {
                result = temp;
            }
        }
        return result;
    }

    /**
     * 检验是否是合法的IP地址
     * 
     * @param ip
     *            String IP地址
     * @return boolean IP地址是否合法
     */
    public static boolean isIpAddress(String ip) {
        String regex =
            "^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$";

        Matcher matcher = Pattern.compile(regex).matcher(ip);

        if (matcher.find()) {
            return true;
        }

        return false;
    }

    /**
     * 判断是否是身份证号
     * 
     * @param idCard
     * @return
     */
    public static boolean isIdCard(String idCard) {
        String regex = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$";
        // 身份证校验码
        char[] verifyCode = "10X98765432".toCharArray();
        Matcher matcher = Pattern.compile(regex).matcher(idCard);
        Integer[] arrInt = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        char[] c = idCard.toCharArray();
        int sum = 0;
        for (int i = 0; i < arrInt.length; i++) {
            sum += Integer.parseInt(c[i] + "") * arrInt[i];
        }
        char ch = verifyCode[sum % 11];
        if (matcher.find() && c[idCard.length() - 1] == ch) {
            return true;
        }
        return false;
    }

    /**
     * 判断是不是中国移动的号码
     * 
     * @param mobile
     * @return
     */
    public static boolean isChinaMobileNO(String mobile) {
        Matcher m = CHINA_MOBILE_PATTERN.matcher(mobile);
        log.info(m.matches() + "---");
        return m.matches();
    }

    /**
     * Description: 判断是不是手机号码
     * 
     * @param mobile
     * @return
     */
    public static boolean isMobileNO(String mobile) {
        /*
         * 根据实际开发于2009年9月7日最新统计：
         * 中国电信发布中国3G号码段:中国联通185,186;中国移动188,187;中国电信189,180共6个号段。
         * 3G业务专属的180-189号段已基本分配给各运营商使用,
         * 其中180、189分配给中国电信,187、188归中国移动使用,185、186属于新联通。
         * 中国移动拥有号码段：139、138、137、136、135
         * 、134、159、158、157（3G）、152、151、150、188（3G）、187（3G）;14个号段
         * 中国联通拥有号码段：130、131、132、155、156（3G）、186（3G）、185（3G）;6个号段
         * 中国电信拥有号码段：133、153、189（3G）、180（3G）;4个号码段 移动:
         * 2G号段(GSM网络)有139,138,137,136,135,134(0-8),159,158,152,151,150
         * 3G号段(TD-SCDMA网络)有157,188,187 147是移动TD上网卡专用号段. 联通:
         * 2G号段(GSM网络)有130,131,132,155,156 3G号段(WCDMA网络)有186,185 电信:
         * 2G号段(CDMA网络)有133,153 3G号段(CDMA网络)有189,180
         */

        Matcher m = MOBILE_PATTERN.matcher(mobile);
        log.info(m.matches() + "---");
        return m.matches();
    }

    /**
     * Description: 判断是否是Email
     * 
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str =
            "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        log.info(m.matches() + "---");
        return m.matches();
    }

    /**
     * Description: 获取马赛克手机号码
     * 
     * @param mobile
     * @return
     */
    public static String getMosaicMobile(String mobile) {
        int maxLength = mobile.length();
        String lastNumText = mobile.substring(maxLength - 2, maxLength);
        int sLength = maxLength - 6;
        String mosaicMobile = mobile.substring(0, 4) + SSTR.substring(0, sLength) + lastNumText;
        return mosaicMobile;
    }

    /**
     * Description: 获取马赛克银行号码
     * 
     * @param cardNo
     * @return
     */
    public static String getMosaicBankCardNo(String cardNo) {
        /*
         * 银行卡的卡号长度及结构符合ISO7812-1有关规定,由13-19位数字表示,具体由以下几部分组成: 9 XXXXX X......X X
         * 发卡银行标识代码 自定义位 校验位
         * 发卡行标识代码指发卡行标识代码标识发卡机构,由6位数字表示,第一位固定为"9",后5位由BIN注册管理机构分配.
         * 自定义位是指发卡行自定义位,由6-12位数字组成. 校验位是指卡号的最后一位数字,根据校验位前的数字计算得到.
         * BIN注册管理机构是指负责BIN注册管理的机构.
         */
        if (StringTool.isEmpty(cardNo)) {
            cardNo = "XXXXXXXXXXXXX";
        }

        int maxLength = cardNo.length();
        String lastNumText = cardNo.substring(maxLength - 4, maxLength);
        int xLength = maxLength - 11;

        String mosaicMobile = cardNo.substring(0, 7) + XSTR.substring(0, xLength) + lastNumText;
        return mosaicMobile;
    }

    /**
     * 复制一个字符串n次
     * 
     * @param str
     * @param count
     * @return
     */
    public static String stringCopy(String str, Integer count) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < count; i++) {
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    /**
     * 把文件中的内容读到一个字符串中，编码使用VM的缺省编码。
     * 
     * @param fileName
     * @return
     * @throws Exception
     */
    public static String readFileToString(String fileName) throws Exception {
        File file = new File(fileName);
        String content = FileUtils.readFileToString(file, Charset.defaultCharset());
        return content;
    }

    /**
     * 写一个字符串到文件
     * 
     * @param fileName
     * @param data
     * @throws IOException
     */
    public static void writeStringToFile(String fileName, String data) throws IOException {
        File file = new File(fileName);
        FileUtils.writeStringToFile(file, data, Charset.defaultCharset());

    }

    /**
     * 驼峰转下划线
     * 
     * @param text
     * @return
     */
    public static String toUnderline(String text) {

        if (text == null || "".equals(text.trim())) {
            return "";
        }

        int len = text.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = text.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     *
     * @param text
     * @return
     */
    public static String toCamel(String text) {
        if (text == null || "".equals(text.trim())) {
            return "";
        }
        text = text.toLowerCase();
        Matcher matcher = LINE_PATTERN.matcher(text);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static void main(String[] args) {
        log.info("15387571204");
        log.info(getMosaicMobile("15387571204"));

        log.info("622700382838388838");
        log.info(getMosaicBankCardNo("622700382838388838"));
    }

}
