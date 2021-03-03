package org.eric.mvc.servlet;

import org.eric.mvc.controller.Controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.Path;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * MyDispatcherServlet class
 *
 * @author 左飞
 * @date 2021/3/2
 */
public class MyDispatcherServlet extends HttpServlet {


    Map<String, Map<Controller, Method>> methodsMapping = new HashMap<>();

    /**
     * 初始化servlet
     * <p>
     * 使用 Java spi 技术加载path属性
     */
    @Override
    public void init() {

        for (Controller controller : ServiceLoader.load(Controller.class)) {
            Class<?> controllerClazz = controller.getClass();
            Path annotionFromClass = controllerClazz.getAnnotation(Path.class);
            String pathFromClass = annotionFromClass.value();
            Method[] methods = controllerClazz.getMethods();
            for (Method method : methods) {
                methodsMapping.put(pathFromClass + method.getAnnotation(Path.class).value(), new HashMap<Controller, Method>() {{
                    put(controller, method);
                }});
            }
        }
    }


    /**
     * @param request
     * @param response
     */
    @Override
    public void service(ServletRequest request, ServletResponse response) {
    }

}
