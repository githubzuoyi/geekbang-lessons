package org.eric.mvc.servlet;

import org.apache.commons.lang.StringUtils;
import org.eric.mvc.controller.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;

import static org.apache.commons.lang.StringUtils.substringAfter;

/**
 * MyDispatcherServlet class
 *
 * @author 左飞
 * @date 2021/3/2
 */
public class MyDispatcherServlet extends HttpServlet {


    Map<String, Map<Controller, Method>> methodsMapping = new HashMap<>();

    /**
     * 请求路径和 Controller 的映射关系缓存
     */
    private Map<String, Controller> controllersMapping = new HashMap<>();


    /**
     * 初始化servlet
     * <p>
     * 使用 Java spi 技术加载path属性
     */
    public void init(ServletConfig servletConfig) {
        initHandleMethods();
    }

    /**
     * 读取所有的 RestController 的注解元信息 @Path
     * 利用 ServiceLoader 技术（Java SPI）
     */
    private void initHandleMethods() {
        for (Controller controller : ServiceLoader.load(Controller.class)) {
            Class<?> controllerClass = controller.getClass();
            Path pathFromClass = controllerClass.getAnnotation(Path.class);
            String requestPath = pathFromClass.value();
            Method[] publicMethods = controllerClass.getMethods();
            // 处理方法支持的 HTTP 方法集合
            for (Method method : publicMethods) {
                Path pathFromMethod = method.getAnnotation(Path.class);
                if (pathFromMethod != null) {
                    requestPath += pathFromMethod.value();
                }
            }
            controllersMapping.put(requestPath, controller);
        }
    }

    /**
     * @param request
     * @param response
     */
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取请求相对路径
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String prefixPath = contextPath;
        String annotationPath = substringAfter(uri, StringUtils.replace(prefixPath, "//", "/"));

        Controller controller = controllersMapping.get(annotationPath);
        String viewPath = null;
        try {
            viewPath = controller.execute(request,response);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        if (!viewPath.startsWith("/")) {
            viewPath = "/" + viewPath;
        }

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(viewPath);
        dispatcher.forward(request,response);

    }
}
