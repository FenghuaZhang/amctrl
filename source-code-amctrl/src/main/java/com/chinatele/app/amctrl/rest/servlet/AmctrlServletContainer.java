package com.chinatele.app.amctrl.rest.servlet;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.chinatele.app.amctrl.service.ControllerService;
import com.sun.jersey.spi.container.servlet.ServletContainer;

public class AmctrlServletContainer extends ServletContainer{

    private static final long serialVersionUID = -1191748115626996357L;

    public void destroy() {
        ControllerService controllerService = WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean(ControllerService.class);
        // 清理session
        controllerService.clean();
        super.destroy();
    }
}
