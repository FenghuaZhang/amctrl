package com.chinatele.app.amctrl.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigUtil {
    /** 日志工具 实例 */
    private static Logger log = LoggerFactory.getLogger(ConfigUtil.class);

    /** 配置相关信息 */
    private static Properties properties = new Properties();

    public static enum LoadType {
        ConfigUtilClass, ConfigUtilClassLoader, SystemClassLoader, File
    }

    public static void loadConfigFile(String configFile, LoadType loadType) {
        InputStream in = null;
        try {
            switch (loadType) {
            case ConfigUtilClass:
                in = ConfigUtil.class.getResourceAsStream(configFile);
                break;
            case ConfigUtilClassLoader:
                in = ConfigUtil.class.getClassLoader().getResourceAsStream(configFile);
                break;
            case SystemClassLoader:
                in = ClassLoader.getSystemResourceAsStream(configFile);
                break;
            case File:
                in = new FileInputStream(new File(configFile));
                break;
            default:
                log.info("Load Configuration from file[" + configFile + "], illegal loadType");
                break;
            }
            if (in != null) {
                properties.load(in);
                log.info("Load Configuration from file[" + configFile + "] complete");
            } else {
                log.info("Load Configuration from file[" + configFile + "] but file does not exist");
            }
        } catch (Exception e) {
            log.error("Load Configuration from file[" + configFile + "] error", e);
        } finally {
            try {
                in.close();
            } catch (Exception e) {
            }
        }
    }

    public static String getValue(String key) {
        if (key == null || "".equals(key.trim())) {
            log.error("error key");
            return "";
        }
        return properties.getProperty(key.trim().toUpperCase());
    }
}
