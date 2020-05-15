package xingkong.tool.core;

import java.math.BigDecimal;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 数字转换 千+、万+、k+、w+、9999w+
 * 
 * @author Zhuyd
 * @date 2019/11/08 16:34:27
 */
@Data
@Slf4j
public class RelativeNumberFormatTool {

    /**
     * 中文显示
     */
    public static final String CH = "CH";
    /**
     * 拼音显示
     */
    public static final String PY = "PY";

    private static final Long THOUSAND = 1000L;
    private static final Long TEN_THOUSAND = 10000L;
    private static final Long ONE_HUNDRED_MILLION = 100000000L;

    /**
     * 数据转换
     * 
     * @param num
     *            需要转换的数据 支持Long、BigDecimal、Integer、String、int、long类型
     * @param type
     *            需要转换的方式 RelativeNumberFormatTool.CH:中文显示 RelativeNumberFormatTool.PY:拼音显示
     * @return
     */
    public static String relativeNumberFormat(Object temp, String type) {
        Long num = numberFormat(temp);
        if (null == num) {
            return null;
        }
        if (type.equals(PY)) {
            if (num.compareTo(ONE_HUNDRED_MILLION) == 1 || num.compareTo(ONE_HUNDRED_MILLION) == 0) {
                return "9999w+";
            }
            if (num.compareTo(TEN_THOUSAND) == 1 || num.compareTo(TEN_THOUSAND) == 0) {
                return num / TEN_THOUSAND + "w+";
            }
            if (num.compareTo(THOUSAND) == 1 || num.compareTo(THOUSAND) == 0) {
                return num / THOUSAND + "k+";
            }
        } else if (type.equals(CH)) {
            if (num.compareTo(ONE_HUNDRED_MILLION) == 1 || num.compareTo(ONE_HUNDRED_MILLION) == 0) {
                return "9999万+";
            }
            if (num.compareTo(TEN_THOUSAND) == 1 || num.compareTo(TEN_THOUSAND) == 0) {
                return num / TEN_THOUSAND + "万+";
            }
            if (num.compareTo(THOUSAND) == 1 || num.compareTo(THOUSAND) == 0) {
                return num / THOUSAND + "千+";
            }
        }
        return num + "";
    }

    /**
     * 格式化数据为Long类型
     */
    public static Long numberFormat(Object number) {
        if (number != null && !"".equals(number)) {
            if (number instanceof BigDecimal) {
                return ((BigDecimal)number).longValue();
            }
            if (number instanceof Integer) {
                return ((Integer)number).longValue();
            }
            if (number instanceof Long) {
                return (Long)number;
            }
            if (number instanceof String) {
                try {
                    return Long.valueOf(number + "");
                } catch (Exception e) {
                    log.info("字符串数字转换失败，请检查！" + e.getMessage(), e);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(103);
        int c = 8350;
        Integer c1 = 1000000;
        String str = "1000";
        long bb = 13544l;
        Long bb1 = 3624l;

        System.out.println(relativeNumberFormat(a, CH));
        System.out.println(relativeNumberFormat(c, CH));
        System.out.println(relativeNumberFormat(c1, CH));
        System.out.println(relativeNumberFormat(str, CH));
        System.out.println(relativeNumberFormat(bb, CH));
        System.out.println(relativeNumberFormat(bb1, CH));
        System.out.println(relativeNumberFormat(a, PY));
        System.out.println(relativeNumberFormat(c, PY));
        System.out.println(relativeNumberFormat(c1, PY));
        System.out.println(relativeNumberFormat(str, PY));
        System.out.println(relativeNumberFormat(bb, PY));
        System.out.println(relativeNumberFormat(bb1, PY));
    }
}
