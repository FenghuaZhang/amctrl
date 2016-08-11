package com.chinatele.app.amctrl.rest.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import com.chinatele.app.amctrl.util.ConfigUtil;

public class AmCtrlContextLoaderListener extends ContextLoaderListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        ConfigUtil.loadConfigFile("/config.properties", ConfigUtil.LoadType.ConfigUtilClass);
    }
}
