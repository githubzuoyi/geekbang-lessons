package org.geektimes.projects.user.web.listener;

import org.geektimes.projects.user.context.ComponentContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ComponentContextInitializerListener class
 * Component上下文监听器
 *
 * @author 左飞
 * @date 2021/3/6
 */
public class ComponentContextInitializerListener implements ServletContextListener {

    private ComponentContext componentContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        componentContext = new ComponentContext();
        componentContext.init(sc);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        componentContext.distory();
    }
}
