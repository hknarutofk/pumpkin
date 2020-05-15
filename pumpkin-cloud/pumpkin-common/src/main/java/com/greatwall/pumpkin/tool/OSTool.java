package com.greatwall.pumpkin.tool;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

/**
 * 操作系统工具
 * 
 * @author xingkong
 * 
 */
@Slf4j
public class OSTool {

    /**
     * 打印本机的IP地址
     */
    public static void printLocalIpAddress() {
        try {
            Enumeration<?> allNetInterfaces;

            allNetInterfaces = NetworkInterface.getNetworkInterfaces();

            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface)allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = (InetAddress)addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address) {
                    }
                }
            }
        } catch (Exception e) {
            log.error("printLocalIpAddress", e);
        }
    }

    private static boolean isOS(String osName) {
        Properties prop = System.getProperties();
        String osText = prop.getProperty("os.name");
        osText = osText.toUpperCase();
        if (osText.startsWith(osName.toUpperCase())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isWindows() {
        return isOS("win");
    }

    public static boolean isLinux() {
        return isOS("lin");
    }

    public static void sleep(int seconds) {
        int count = seconds;
        if (seconds > 0) {
            try {
                while (count > 0) {
                    Thread.sleep(1000);
                    count--;
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     * 休息毫秒，抛弃异常
     * 
     * @param ms
     */
    public static void sleepms(long ms) {

        try {
            Thread.sleep(ms);
        } catch (Exception e) {
        }

    }

    /**
     * 获取堆栈的错误信息
     * 
     * @param e
     * @return
     */
    public static String getErrorText(Exception e) {
        StackTraceElement[] stack = e.getStackTrace();
        StringBuilder lineInfo = new StringBuilder("\r\n<br>");
        for (int i = 0; i < stack.length; i++) {
            StackTraceElement s = stack[i];
            lineInfo.append(String.format("    [stack-%02d]%s - (%s:%s)<br>\r\n", i, s.getMethodName(), s.getFileName(),
                s.getLineNumber()));
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetimeText = "[Time]" + df.format(new Date()) + "<br>";
        return datetimeText + lineInfo.toString() + e.toString();
    }

    public static boolean chmod(String fileName, String mode) {
        boolean flag = false;
        if (isLinux()) {
            try {
                log.debug("chmod " + mode + " " + fileName);
                Process process = Runtime.getRuntime().exec("chmod " + mode + " " + fileName);

                InputStream stderr = process.getErrorStream();
                InputStreamReader isr = new InputStreamReader(stderr);
                BufferedReader br = new BufferedReader(isr);
                String line = null;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }

                int result = process.waitFor();
                if (result == 0) {
                    flag = true;
                }

            } catch (Exception e) {
                flag = false;
                log.error("chmod failed.", e);
            }
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 关闭具有Closeable接口的对象
     * 
     * @param obj
     */
    public static void closeObj(Closeable obj) {
        if (obj == null) {
            return;
        }

        try {
            obj.close();
        } catch (Exception e) {
            log.error("close object failed", e);
        }
    }
}
