package xingkong.tool.core;

import java.io.File;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author:星空
 */
@Slf4j
public class PathTool {

    /**
     * 判断文件或者文件夹是否存在
     * 
     * @param fileName
     * @return
     */
    public static boolean isExists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    /**
     * 获取环境变量
     * 
     * @return
     */
    public static String getEnv() {

        String env = System.getProperty("env");
        if (StringTool.isEmpty(env)) {
            env = "dev";
            log.info("[获取env配置] 没有配置环境变量,默认采用开发环境配置dev");
        }

        return env;
    }

    /**
     * 获取APP路径
     * 
     * @param path
     * @return
     */
    public static String getAppPath(String path) {
        String appPath = "";
        String confPath = "";
        try {

            appPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

            // 判断conf文件夹是否在同一个目录
            confPath = appPath + "/conf";
            if (isExists(confPath)) {
                log.info("[确定配置文件夹]conf在类的同一个目录，运行在服务器上。");
                appPath = appPath + path;
                appPath = URLDecoder.decode(appPath, "utf-8");
                return appPath;
            }

            // 判断conf文件夹是否在上一个目录
            confPath = appPath + "../conf";
            if (isExists(confPath)) {
                log.info("[确定配置文件夹]conf在类的上一个目录，运行在本地调试环境。");
                appPath = appPath + "../" + path;
                appPath = URLDecoder.decode(appPath, "utf-8");
                return appPath;
            }
        } catch (Exception e1) {
            appPath = "./";
        }
        return appPath;
    }
}