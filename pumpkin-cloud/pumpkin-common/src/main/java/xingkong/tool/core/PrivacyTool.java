package xingkong.tool.core;

import org.springframework.util.StringUtils;

/**
 * 处理敏感信息，进行脱敏
 * 
 * @author 798603812
 * @since 2019/12/24 11:35
 */
public class PrivacyTool {
    public class Parameter {
        /**
         * 默认进行脱敏替换的符号
         */
        public static final String DEFAULT_REPLACEMENT_STR = "*";
    }

    // 身份证号码：显示前四后二，范例：1106************25
    public static String encryptIdCard(String idCard) {
        if (StringTool.isEmpty(idCard)) {
            return null;
        }
        return replaceStr(idCard, 4, idCard.length() - 2, null);
    }

    // 姓名：显示前0后1，范例：*山
    public static String encryptName(String name) {
        if (StringTool.isEmpty(name)) {
            return null;
        }
        return replaceStr(name, 0, name.length() - 1, null);
    }

    // 手机号：显示前3后4，范例 183****9254
    public static String encryptPhoneNo(String name) {
        if (StringTool.isEmpty(name)) {
            return null;
        }
        return replaceStr(name, 3, name.length() - 4, null);
    }

    public static String replaceStr(String str, Integer start, Integer end, String replacement) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        if (replacement == null) {
            replacement = Parameter.DEFAULT_REPLACEMENT_STR;;

        }
        int replaceLength = end - start;
        if (!StringUtils.isEmpty(str) && replaceLength > 0) {
            StringBuilder sbf = new StringBuilder(str);
            // 获取匹配的字符串
            sbf.replace(start, end, StringTool.stringCopy(replacement, replaceLength));
            return sbf.toString();
        } else {
            return str;
        }
    }
}
