package org.geektimes.projects.user.context;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import java.util.NoSuchElementException;

/**
 * ComponentContext class
 *
 * @author 左飞
 * @date 2021/3/6
 */
public class ComponentContext {

    private static ServletContext servletContext;

    private static final String CONTEXT_NAME = ComponentContext.class.getName();

    private Context context;

    /**
     * 初始化 ComponentContext
     * @param servletContext
     * @throws NamingException
     */
    public void init(ServletContext servletContext) {
        try {
            this.context = (Context) new InitialContext().lookup("java:comp/env");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        servletContext.setAttribute(CONTEXT_NAME, this);
        ComponentContext.servletContext = servletContext;
    }

    /**
     * 获取ComponentContext
     *
     * @return
     */
    public static ComponentContext getInstance() {
        return (ComponentContext) servletContext.getAttribute(CONTEXT_NAME);
    }

    /**
     * 根据名称进行依赖查找
     *
     * @param componentName 依赖名称
     * @param <C> 泛型自动类型转换
     * @return
     */
    public <C> C getComponent(String componentName) {
        C component = null;
        try {
            component = (C) this.context.lookup(componentName);
        } catch (NamingException e) {
            servletContext.log("未找到依赖：" + e);
            throw new NoSuchElementException(componentName);
        }

        return component;
    }

    /**
     * 销毁Component上下文
     */
    public void distory() {
        if (context != null) {
            try {
                context.close();
            } catch (NamingException e) {
                throw new RuntimeException();
            }
        }
    }

}
