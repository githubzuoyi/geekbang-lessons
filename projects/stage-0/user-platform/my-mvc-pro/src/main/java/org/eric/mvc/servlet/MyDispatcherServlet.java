package org.eric.mvc.servlet;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * mvc servlet
 */
public class MyDispatcherServlet extends HttpServlet {

    /**
     * 映射集合
     */
    Map<String, Object> collectionMapping = new HashMap<>();

    /**
     * Servlet 初始化
     * <p>
     * 根据 Java spi 机制，将路径和信息提前准备好
     */
    @Override
    public void init() {
        Object object = ServiceLoader.load(Object.class);
    }

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void service(ServletRequest request, ServletResponse response) {

    }

}
